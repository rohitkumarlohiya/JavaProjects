package org.firstonlineuniversity.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.firstonlineuniversity.domains.auth.custom.CustomUser;
import org.firstonlineuniversity.exceptions.customexceptions.EmptyFormElementsException;
import org.firstonlineuniversity.exceptions.customexceptions.EntityNotFoundException;
import org.firstonlineuniversity.formvalidation.rest.AddCoursesFilesFormValidation;
import org.firstonlineuniversity.formvalidation.rest.EditCoursesFilesFormValidation;
import org.firstonlineuniversity.formvalidation.rest.Errors;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.commons.ParentNode;
import org.firstonlineuniversity.models.courses.CourseContent;
import org.firstonlineuniversity.models.status.Status;
import org.firstonlineuniversity.services.CustomEditableService;
import org.firstonlineuniversity.services.DataServices;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/coursesadmin/courses/files")
@PropertySource("classpath:message.properties")
public class ContentAdminControllerRest {
	@Autowired
	Environment env;

	@Autowired
	DataServices<AbstractEntity> dataServices;

	@Autowired
	AddCoursesFilesFormValidation addCoursesFilesFormValidation;

	@Autowired
	EditCoursesFilesFormValidation editCoursesFilesFormValidation;

	@Autowired
	CustomEditableService customEditableService;

	static final Logger logger = Logger
			.getLogger(ContentAdminControllerRest.class);

	/*
	 * Add Files
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Status add(@RequestBody CourseContent courseFiles)
			throws HibernateException, Exception {
		if (courseFiles == null)
			throw new EntityNotFoundException();

		List<Errors> errorsList = addCoursesFilesFormValidation
				.validateForm(courseFiles);
		if (!errorsList.isEmpty()) {
			return new Status(HttpStatus.NOT_ACCEPTABLE.toString(),
					env.getProperty("exception.validationfailed"), errorsList);

		}
		try {
			courseFiles.setCD(new Date());
			courseFiles.setUD(new Date());
			CustomUser customUser = (CustomUser) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			courseFiles.setUB(customUser.getId());
			courseFiles.setCB(customUser.getId());
			courseFiles.setEnabled(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		dataServices.addEntity(courseFiles);

		return new Status(HttpStatus.ACCEPTED.toString(),
				env.getProperty("courses.files.add"));

	}

	/*
	 * Edit Files
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> edit(@PathVariable long id)
			throws HibernateException, Exception {

		CourseContent filesDB = customEditableService
				.getCourseFilesEditableDate(id);
		if (filesDB == null)
			throw new EntityNotFoundException();

		CourseContent files = new CourseContent(filesDB.getId(),
				filesDB.getFileName(), filesDB.getFileDisplayName(),
				filesDB.getFileType(), filesDB.getFileDescription(),
				filesDB.getFileSize(), filesDB.getFileFormat(),
				filesDB.getFilePath(), filesDB.getFileIndex(),
				filesDB.getFileDuration(), null, null, filesDB.getContentKey());
		files.setCB(filesDB.getCB());
		files.setCD(filesDB.getCD());
		files.setEnabled(filesDB.isEnabled());
		files.setDownload(filesDB.isDownload());

		Map<String, Object> filesMap = new HashMap<String, Object>();
		filesMap.put("courseInformation", new ParentNode(filesDB.getCourse()
				.getId(), filesDB.getCourse().getCourseName()));
		filesMap.put("courseLectures", new ParentNode(filesDB.getLecture()
				.getId(), filesDB.getLecture().getLectureName()));
		filesMap.put("files", files);

		return filesMap;

	}

	/*
	 * Update Files
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status updateCourses(
			@RequestBody CourseContent courseFiles) throws HibernateException,
			Exception {
		if (courseFiles == null) {
			throw new EmptyFormElementsException();
		}

		List<Errors> errorsList = editCoursesFilesFormValidation
				.validateForm(courseFiles);
		if (!errorsList.isEmpty()) {
			return new Status(HttpStatus.NOT_ACCEPTABLE.toString(),
					env.getProperty("exception.validationfailed"), errorsList);

		}
		try {
			courseFiles.setUD(new Date());
			CustomUser customUser = (CustomUser) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			courseFiles.setUB(customUser.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		dataServices.updateEntity(courseFiles);

		return new Status(HttpStatus.OK.toString(),
				env.getProperty("file.successupdate"));
	}

	/*
	 * Delete Files
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Status delete(@PathVariable long id) throws HibernateException,
			Exception {
		if (id == 0)
			throw new EntityNotFoundException();

		dataServices.deleteEntity(id, CourseContent.class);

		return new Status(HttpStatus.ACCEPTED.toString(),
				env.getProperty("files.delete"));

	}

}