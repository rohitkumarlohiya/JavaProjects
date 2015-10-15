package org.firstonlineuniversity.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.firstonlineuniversity.domains.auth.custom.CustomUser;
import org.firstonlineuniversity.exceptions.customexceptions.EntityNotFoundException;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.courses.CourseContent;
import org.firstonlineuniversity.models.courses.CourseInformation;
import org.firstonlineuniversity.models.courses.CourseLectures;
import org.firstonlineuniversity.models.courses.CourseResources;
import org.firstonlineuniversity.models.status.Status;
import org.firstonlineuniversity.services.DataServices;
import org.firstonlineuniversity.sftp.courses.CoursesSftp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/users/files")
@PropertySource("classpath:message.properties")
public class FileUploadController {

	@Autowired
	ServletContext context;

	@Autowired
	DataServices<AbstractEntity> dataServices;

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public @ResponseBody String provideUploadInfo() {
		return "You can upload a file by posting to this same URL.";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody Status upload(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		Status status = new Status("500", "File Upload Failed");
		try {
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if (isMultipart) {
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				List<FileItem> items = upload.parseRequest(request);
				Iterator<FileItem> iterator = items.iterator();
				InputStream is = null;
				String filePath = null;
				String fileName = null;
				while (iterator.hasNext()) {

					FileItem item = iterator.next();

					if (item.isFormField()) {
						filePath = item.getString();
					} else {
						is = new ByteArrayInputStream(item.get());
						fileName = item.getName();
					}
				}
				CoursesSftp.upload(filePath, fileName, is);
			}
			status = new Status("200", "File Uploaded Successfully");
		} catch (Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			e.printStackTrace();

		}
		return status;
	}

	@RequestMapping(value = "/upload-contents", method = RequestMethod.POST)
	public @ResponseBody Status uploadContent(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CourseContent courseFiles = null;
		Status status = null;
		try {
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if (isMultipart) {
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				List items = upload.parseRequest(request);
				Iterator iterator = items.iterator();
				InputStream is = null;
				String courseKey = "unknown";
				String fileName = null;
				String fileDisplayName = null;
				String fileType = null;
				String fileDescription = null;
				String fileSize = null;
				String fileFormat = null;
				String fileIndex = null;
				String fileDuration = "1000";
				String paid = null;
				String course = null;
				String lecture = null;
				boolean download = true;
				boolean enabled = false;

				while (iterator.hasNext()) {
					FileItem item = (FileItem) iterator.next();
					if (item.getFieldName().equals("fileDisplayName")) {
						fileDisplayName = item.getString();
					} else if (item.getFieldName().equals("fileDescription")) {
						fileDescription = item.getString();
					} else if (item.getFieldName().equals("fileFormat")) {
						fileFormat = item.getString();
					} else if (item.getFieldName().equals("fileIndex")) {
						fileIndex = item.getString();
					} else if (item.getFieldName().equals("paid")) {
						paid = item.getString();
					} else if (item.getFieldName().equals("download")) {
						download = Boolean.getBoolean(item.getString());
					} else if (item.getFieldName().equals("courseId")) {
						course = item.getString();
						CourseInformation courseInformation = (CourseInformation) dataServices
								.getEntity(Long.parseLong(course),
										CourseInformation.class);
						if (courseInformation != null
								&& courseInformation.getCourseKey() != null)
							courseKey = courseInformation.getCourseKey();
					} else if (item.getFieldName().equals("lectureId")) {
						lecture = item.getString();
					} else if (item.getFieldName().equals("file1")) {
						is = new ByteArrayInputStream(item.get());
						fileName = item.getName();
						fileType = item.getContentType();
						fileSize = String.valueOf(item.getSize());
					}
				}
				CourseInformation courseInformation = new CourseInformation();
				courseInformation.setId(Long.parseLong(course));
				CourseLectures courseLectures = new CourseLectures();
				courseLectures.setId(Long.parseLong(lecture));

				CoursesSftp.upload("learn/" + courseKey + "/lectures",
						fileName, is);
				status = new Status("200", "File Uploaded Successfully");

				courseFiles = new CourseContent(Boolean.parseBoolean(paid),
						fileName, fileDisplayName, fileType, fileDescription,
						Integer.parseInt(fileSize), fileFormat, courseKey,
						Short.parseShort(fileIndex),
						Short.parseShort(fileDuration), courseInformation,
						courseLectures, "learn/" + courseKey + "/lectures/"
								+ fileName);
				courseFiles.setFilePath("learn/" + courseKey + "/lectures/");
				courseFiles.setDownload(download);
			}
		} catch (Exception e) {
			status = new Status("500", "Error in file upload");
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			e.printStackTrace();
		}
		try {
			courseFiles.setCD(new Date());
			courseFiles.setUD(new Date());
			CustomUser customUser = (CustomUser) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			courseFiles.setUB(customUser.getId());
			courseFiles.setCB(customUser.getId());
			dataServices.addEntity(courseFiles);
		} catch (Exception e) {
			status = new Status("500", "Error in file upload");
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			e.printStackTrace();
		}
		return status;
	}

	@RequestMapping(value = "/update-contents", method = RequestMethod.POST)
	public @ResponseBody Status editContent(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CourseContent courseFiles = null;
		Status status = null;
		String cb = null;
		String cd = null;
		try {
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if (isMultipart) {
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				List items = upload.parseRequest(request);
				Iterator iterator = items.iterator();
				InputStream is = null;
				String courseKey = "unknown";
				String id = null;
				String fileName = null;
				String fileDisplayName = null;
				String fileType = null;
				String fileDescription = null;
				String fileSize = null;
				String fileFormat = null;
				String fileIndex = null;
				String fileDuration = "1000";
				String contentKey = null;
				String paid = null;
				String course = null;
				String lecture = null;
				boolean enabled = false;
				while (iterator.hasNext()) {
					FileItem item = (FileItem) iterator.next();
					if (item.getFieldName().equals("fileDisplayName")) {
						fileDisplayName = item.getString();
					} else if (item.getFieldName().equals("fileDescription")) {
						fileDescription = item.getString();
					} else if (item.getFieldName().equals("cb")) {
						cb = item.getString();
					} else if (item.getFieldName().equals("cd")) {
						cd = item.getString();
					} else if (item.getFieldName().equals("enabled")) {
						if (item.getString().equals("true"))
							enabled = true;
						else
							enabled = false;
					} else if (item.getFieldName().equals("id")) {
						id = item.getString();
					} else if (item.getFieldName().equals("fileFormat")) {
						fileFormat = item.getString();
					} else if (item.getFieldName().equals("fileIndex")) {
						fileIndex = item.getString();
					} else if (item.getFieldName().equals("contentKey")) {
						contentKey = item.getString();
					} else if (item.getFieldName().equals("paid")) {
						paid = item.getString();
					} else if (item.getFieldName().equals("courseId")) {
						course = item.getString();
						CourseInformation courseInformation = (CourseInformation) dataServices
								.getEntity(Long.parseLong(course),
										CourseInformation.class);
						if (courseInformation != null
								&& courseInformation.getCourseKey() != null)
							courseKey = courseInformation.getCourseKey();
					} else if (item.getFieldName().equals("lectureId")) {
						lecture = item.getString();
					} else if (item.getFieldName().equals("file1")) {
						try {
							is = new ByteArrayInputStream(item.get());
							fileName = item.getName();
							fileType = item.getContentType();
							fileSize = String.valueOf(item.getSize());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				CourseInformation courseInformation = new CourseInformation();
				courseInformation.setId(Long.parseLong(course));
				CourseLectures courseLectures = new CourseLectures();
				courseLectures.setId(Long.parseLong(lecture));

				if (is != null)
					CoursesSftp.upload("learn/" + courseKey + "/lectures",
							fileName, is);
				status = new Status("200", "File Updated Successfully");
				courseFiles = (CourseContent) dataServices.getEntity(
						Long.parseLong(id), CourseContent.class);
				if (fileName != null) {
					courseFiles.setFileName(fileName);
					courseFiles.setFileType(fileType);
					courseFiles.setFileDuration(Short.parseShort(fileDuration));
					courseFiles.setFileSize(Integer.parseInt(fileSize));
				}
				courseFiles.setPaid(Boolean.parseBoolean(paid));
				courseFiles.setFileDisplayName(fileDisplayName);
				courseFiles.setFileDescription(fileDescription);
				courseFiles.setContentKey("learn/" + courseKey + "/lectures/"
						+ fileName);
				courseFiles.setFilePath("learn/" + courseKey + "/lectures/");
				courseFiles.setFileIndex(Short.parseShort(fileIndex));
				courseFiles.setCourse(courseInformation);
				courseFiles.setLecture(courseLectures);
				courseFiles.setId(Long.parseLong(id));
				courseFiles.setEnabled(enabled);
			}
		} catch (Exception e) {
			status = new Status("500", "Error in file updation");
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			e.printStackTrace();
		}
		try {
			courseFiles.setCD(new Date(Long.parseLong(cd)));
			courseFiles.setUD(new Date());
			CustomUser customUser = (CustomUser) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			courseFiles.setUB(customUser.getId());
			courseFiles.setCB(Long.parseLong(cb));
			dataServices.updateEntity(courseFiles);
		} catch (Exception e) {
			status = new Status("500", "Error in file updation");
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			e.printStackTrace();
		}
		return status;
	}

	@RequestMapping(value = "/delete-contents/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Status deleteContent(HttpServletRequest request,
			HttpServletResponse response, @PathVariable long id)
			throws Exception {
		CourseContent content = (CourseContent) dataServices.getEntity(id,
				CourseContent.class);
		if (content == null)
			throw new EntityNotFoundException();

		Status status = new Status("500", "Error in content deletion");
		try {
			CoursesSftp.delete(content.getFilePath(), content.getFileName());
			dataServices.deleteEntity(id, CourseContent.class);
			status = new Status("200", "Content deleted successfull !");
		} catch (Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			e.printStackTrace();
		}
		return status;
	}

	@RequestMapping(value = "/upload-resources", method = RequestMethod.POST)
	public @ResponseBody Status uploadResource(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CourseResources courseFiles = null;
		Status status = null;
		try {
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if (isMultipart) {
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				List items = upload.parseRequest(request);
				Iterator iterator = items.iterator();
				InputStream is = null;
				String courseKey = "unknown";
				String fileName = null;
				String fileDisplayName = null;
				String fileType = null;
				String fileDescription = null;
				String fileSize = null;
				String fileFormat = null;
				String fileIndex = null;
				String paid = null;
				String course = null;
				String lecture = null;

				while (iterator.hasNext()) {
					FileItem item = (FileItem) iterator.next();
					if (item.getFieldName().equals("fileDisplayName")) {
						fileDisplayName = item.getString();
					} else if (item.getFieldName().equals("fileDescription")) {
						fileDescription = item.getString();
					} else if (item.getFieldName().equals("fileFormat")) {
						fileFormat = item.getString();
					} else if (item.getFieldName().equals("fileIndex")) {
						fileIndex = item.getString();
					} else if (item.getFieldName().equals("paid")) {
						paid = item.getString();
					} else if (item.getFieldName().equals("courseId")) {
						course = item.getString();
						CourseInformation courseInformation = (CourseInformation) dataServices
								.getEntity(Long.parseLong(course),
										CourseInformation.class);
						if (courseInformation != null
								&& courseInformation.getCourseKey() != null)
							courseKey = courseInformation.getCourseKey();
					} else if (item.getFieldName().equals("lectureId")) {
						lecture = item.getString();
					} else if (item.getFieldName().equals("file1")) {
						try {
							is = new ByteArrayInputStream(item.get());
							fileName = item.getName();
							fileType = item.getContentType();
							fileSize = String.valueOf(item.getSize());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				CourseInformation courseInformation = new CourseInformation();
				courseInformation.setId(Long.parseLong(course));
				CourseLectures courseLectures = new CourseLectures();
				courseLectures.setId(Long.parseLong(lecture));

				CoursesSftp.upload("learn/" + courseKey + "/resources/",
						fileName, is);
				status = new Status("200", "Resource Uploaded Successfully");
				courseFiles = new CourseResources();
				courseFiles.setFileDisplayName(fileDisplayName);
				courseFiles.setFileDescription(fileDescription);
				courseFiles.setFileFormat(fileFormat);
				courseFiles.setFileIndex(Short.parseShort(fileIndex));
				courseFiles.setFileName(fileName);
				courseFiles.setFileType(fileType);
				courseFiles.setFileSize(Integer.parseInt(fileSize));
				courseFiles.setPaid(Boolean.parseBoolean(paid));
				courseFiles.setFilePath("learn/" + courseKey + "/resources/");

				courseFiles.setFileDescription(fileDescription);
				courseFiles.setResourceKey("learn/" + courseKey + "/resources/"
						+ fileName);
				courseFiles.setFilePath("learn/" + courseKey + "/resources/");
				courseFiles.setFileIndex(Short.parseShort(fileIndex));
				courseFiles.setCourse(courseInformation);
				courseFiles.setLecture(courseLectures);
				courseFiles.setCD(new Date());
				courseFiles.setUD(new Date());
				CustomUser customUser = (CustomUser) SecurityContextHolder
						.getContext().getAuthentication().getPrincipal();
				courseFiles.setUB(customUser.getId());
				courseFiles.setCB(customUser.getId());
				dataServices.addEntity(courseFiles);
			}
		} catch (Exception e) {
			status = new Status("500", "Error in Resource upload");
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			e.printStackTrace();
		}
		return status;
	}

	@RequestMapping(value = "/update-resources", method = RequestMethod.POST)
	public @ResponseBody Status editResources(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CourseResources courseFiles = null;
		Status status = null;
		String cb = null;
		String cd = null;
		try {
			boolean isMultipart = ServletFileUpload.isMultipartContent(request);
			if (isMultipart) {
				FileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				List items = upload.parseRequest(request);
				Iterator iterator = items.iterator();
				InputStream is = null;
				String courseKey = "unknown";
				String id = null;
				String fileName = null;
				String fileDisplayName = null;
				String fileType = null;
				String fileDescription = null;
				String fileSize = null;
				String fileFormat = null;
				String fileIndex = null;
				String fileDuration = "1000";
				String contentKey = null;
				String paid = null;
				String course = null;
				String lecture = null;
				Boolean enabled = false;
				while (iterator.hasNext()) {
					FileItem item = (FileItem) iterator.next();
					if (item.getFieldName().equals("fileDisplayName")) {
						fileDisplayName = item.getString();
					} else if (item.getFieldName().equals("fileDescription")) {
						fileDescription = item.getString();
					} else if (item.getFieldName().equals("cb")) {
						cb = item.getString();
					} else if (item.getFieldName().equals("cd")) {
						cd = item.getString();
					} else if (item.getFieldName().equals("id")) {
						id = item.getString();
					} else if (item.getFieldName().equals("enabled")) {
						if (item.getString().equals("true"))
							enabled = true;
						else
							enabled = false;
					} else if (item.getFieldName().equals("fileFormat")) {
						fileFormat = item.getString();
					} else if (item.getFieldName().equals("fileIndex")) {
						fileIndex = item.getString();
					} else if (item.getFieldName().equals("contentKey")) {
						contentKey = item.getString();
					} else if (item.getFieldName().equals("paid")) {
						paid = item.getString();
					} else if (item.getFieldName().equals("courseId")) {
						course = item.getString();
						CourseInformation courseInformation = (CourseInformation) dataServices
								.getEntity(Long.parseLong(course),
										CourseInformation.class);
						if (courseInformation != null
								&& courseInformation.getCourseKey() != null)
							courseKey = courseInformation.getCourseKey();
					} else if (item.getFieldName().equals("lectureId")) {
						lecture = item.getString();
					} else if (item.getFieldName().equals("file1")) {
						try {
							is = new ByteArrayInputStream(item.get());
							fileName = item.getName();
							fileType = item.getContentType();
							fileSize = String.valueOf(item.getSize());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				CourseInformation courseInformation = new CourseInformation();
				courseInformation.setId(Long.parseLong(course));
				CourseLectures courseLectures = new CourseLectures();
				courseLectures.setId(Long.parseLong(lecture));

				if (is != null)
					CoursesSftp.upload("learn/" + courseKey + "/resources/",
							fileName, is);
				status = new Status("200", "Resource Updated Successfully");
				courseFiles = (CourseResources) dataServices.getEntity(
						Long.parseLong(id), CourseResources.class);
				courseFiles.setEnabled(enabled);
				if (fileName != null) {
					courseFiles.setFileName(fileName);
					courseFiles.setFileType(fileType);
					courseFiles.setFileSize(Integer.parseInt(fileSize));
				}
				courseFiles.setPaid(Boolean.parseBoolean(paid));
				courseFiles.setFileDisplayName(fileDisplayName);
				courseFiles.setFileDescription(fileDescription);
				courseFiles.setResourceKey("learn/" + courseKey + "/resources/"
						+ fileName);
				courseFiles.setFilePath("learn/" + courseKey + "/resources/");
				courseFiles.setFileIndex(Short.parseShort(fileIndex));
				courseFiles.setCourse(courseInformation);
				courseFiles.setLecture(courseLectures);
				courseFiles.setId(Long.parseLong(id));
			}
		} catch (Exception e) {
			status = new Status("500", "Error in Resource updation");
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			e.printStackTrace();
		}
		try {
			courseFiles.setCD(new Date(Long.parseLong(cd)));
			courseFiles.setUD(new Date());
			CustomUser customUser = (CustomUser) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			courseFiles.setUB(customUser.getId());
			courseFiles.setCB(Long.parseLong(cb));

			dataServices.updateEntity(courseFiles);
		} catch (Exception e) {
			status = new Status("500", "Error in Resource updation");
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			e.printStackTrace();
		}
		return status;
	}

	@RequestMapping(value = "/delete-resources/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Status deleteResources(HttpServletRequest request,
			HttpServletResponse response, @PathVariable long id)
			throws Exception {
		CourseResources content = (CourseResources) dataServices.getEntity(id,
				CourseResources.class);
		if (content == null)
			throw new EntityNotFoundException();

		Status status = new Status("500", "Error in Resource deletion");
		try {
			CoursesSftp.delete(content.getFilePath(), content.getFileName());
			dataServices.deleteEntity(id, CourseResources.class);
			status = new Status("200", "Resource deleted successfully !");
		} catch (Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			e.printStackTrace();
		}
		return status;
	}
}
