package org.firstonlineuniversity.controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.firstonlineuniversity.domains.auth.custom.CustomUser;
import org.firstonlineuniversity.domains.custom.CourseWrapper;
import org.firstonlineuniversity.exceptions.customexceptions.ConstraintViolationExceptionCustom;
import org.firstonlineuniversity.exceptions.customexceptions.EmptyFormElementsException;
import org.firstonlineuniversity.exceptions.customexceptions.EntityNotFoundException;
import org.firstonlineuniversity.exceptions.customexceptions.SomethingWentWrongException;
import org.firstonlineuniversity.formvalidation.rest.AddCourseFormValidation;
import org.firstonlineuniversity.formvalidation.rest.EditCourseFormValidation;
import org.firstonlineuniversity.formvalidation.rest.Errors;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.commons.ParentNode;
import org.firstonlineuniversity.models.courses.CourseInformation;
import org.firstonlineuniversity.models.courses.CourseProviders;
import org.firstonlineuniversity.models.courses.CoursesTags;
import org.firstonlineuniversity.models.courses.Providers;
import org.firstonlineuniversity.models.courses.Tags;
import org.firstonlineuniversity.models.helper.TagsManager;
import org.firstonlineuniversity.models.profiles.Organization;
import org.firstonlineuniversity.models.status.Status;
import org.firstonlineuniversity.services.CustomEditableService;
import org.firstonlineuniversity.services.CustomServices;
import org.firstonlineuniversity.services.DataServices;
import org.firstonlineuniversity.services.EntitySummaryServices;
import org.firstonlineuniversity.sftp.courses.CoursesSftp;
import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;
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
@RequestMapping("/api/coursesadmin/courses")
@PropertySource("classpath:message.properties")
public class CourseAdminControllerRest {
	@Autowired
	Environment env;

	@Autowired
	DataServices<AbstractEntity> dataServices;

	@Autowired
	CustomServices customServices;

	@Autowired
	EditCourseFormValidation editCourseFormValidation;

	@Autowired
	AddCourseFormValidation addCourseFormValidation;

	@Autowired
	CustomEditableService customEditableService;

	@Autowired
	EntitySummaryServices entitySummaryServices;

	static final Logger logger = Logger.getLogger(CoursesControllerRest.class);

	/*
	 * Add Courses
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Status add(@RequestBody CourseInformation courseInformation)
			throws HibernateException, Exception {
		Long courseId = 0l;
		if (courseInformation == null)
			throw new EntityNotFoundException();

		List<Errors> errorsList = addCourseFormValidation
				.validateForm(courseInformation);
		if (!errorsList.isEmpty()) {
			return new Status(HttpStatus.NOT_ACCEPTABLE.toString(),
					env.getProperty("exception.validationfailed"), errorsList);
		}

		try {
			courseInformation.setCD(new Date());
			courseInformation.setUD(new Date());
			CustomUser customUser = (CustomUser) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			courseInformation.setUB(customUser.getId());
			courseInformation.setCB(customUser.getId());
			CoursesSftp.execute(courseInformation.getCourseKey());

			String tagArray[] = courseInformation.getTagString().trim()
					.split("\\|");

			if (tagArray.length > 0 && !tagArray[0].trim().equals("")) {
				for (String tag : tagArray) {
					try {
						Tags t = new Tags();
						t.setName(tag);
						t.setCB(customUser.getId());
						t.setCD(new Date());
						dataServices.addEntity(new Tags(tag, ""));
					} catch (ConstraintViolationException e) {
						logger.info("Tag already exists ", e);
					} catch (Exception e) {
						logger.info("Error is adding tag :  ", e);
					}
				}

				List<Tags> tagsSet = customServices.getListByLike("Tags",
						tagArray, "name");
				for (Tags t : tagsSet) {
					t.setCB(customUser.getId());
					t.setCD(new Date());
					courseInformation.getTags().add(t);
				}
			}
			dataServices.updateEntity(courseInformation);
			if (courseInformation.getProviderId() == 0) {
				courseInformation.setProviderId(customServices
						.getProviderByUserId(customUser.getId()).getId());
			}
			logger.info("course id: " + courseInformation.getId());
			CourseProviders courseProviders = new CourseProviders(true,
					"INSTRUCTOR", courseInformation.getId(), new Providers(
							courseInformation.getProviderId()),
					customUser.getId(), new Date());
			dataServices.addEntity(courseProviders);
			courseId = courseInformation.getId();
		} catch (ConstraintViolationException e) {
			throw new ConstraintViolationExceptionCustom();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException();
		}
		return new Status(courseId, HttpStatus.ACCEPTED.toString(),
				env.getProperty("courses.add"));
	}

	/*
	 * Edit Courses
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> edit(@PathVariable long id)
			throws HibernateException, Exception {

		CourseInformation courseInformationDB = (CourseInformation) dataServices
				.getEntity(id, CourseInformation.class);

		CourseInformation information = customEditableService
				.getCoursesEditableData(id);

		if (courseInformationDB == null || information == null)
			throw new EntityNotFoundException();

		CourseInformation courseInformation = new CourseInformation(
				courseInformationDB.getCourseName(),
				courseInformationDB.getCourseDescription(),
				courseInformationDB.getCourseLongDescription(),
				courseInformationDB.getCourseDuration(),
				courseInformationDB.getPreRequisite(),
				courseInformationDB.getDifficultyLevel(),
				courseInformationDB.getVersionNumber(),
				courseInformationDB.getVersionDescription(),
				courseInformationDB.getCourseResources(),
				courseInformationDB.getCourseLogistics(),
				courseInformationDB.getCourseType(),
				courseInformationDB.getCourseFaq(),
				courseInformationDB.isNewCourse(),
				courseInformationDB.isPopularCourse(),
				courseInformationDB.isFeaturedCourse(),
				courseInformationDB.getCourseImageLink(),
				courseInformationDB.getPromoVideoLink(),
				courseInformationDB.getCourseLoad(),
				courseInformationDB.isStartingSoon(), 0,
				courseInformationDB.getPrimaryLanguage(), null);
		courseInformation.setId(id);
		courseInformation.setPaidCourse(courseInformationDB.isPaidCourse());
		try {
			courseInformation.setCB(courseInformationDB.getCB());
			courseInformation.setCD(courseInformationDB.getCD());
			courseInformation.setUD(courseInformationDB.getUD());
			courseInformation.setUB(courseInformationDB.getUB());
			courseInformation.setCredits(courseInformationDB.getCredits());
			courseInformation.setOrganizationId(courseInformationDB
					.getOrganizationId());
			courseInformation.setOrganizationName(courseInformationDB
					.getOrganizationName());
		} catch (Exception e) {
			logger.info("Unable to add editing columns: " + e);
		}
		courseInformation.setCourseKey(courseInformationDB.getCourseKey());

		Set<Tags> tagSet = new HashSet<Tags>();
		for (Tags tag : courseInformationDB.getTags()) {
			tagSet.add(new Tags(tag.getName(), tag.getId()));
		}

		courseInformation.setTags(tagSet);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("courseInformation", courseInformation);
		map.put("courseCategory", new ParentNode(information
				.getCourseCategory().getId(), information.getCourseCategory()
				.getCategoryName()));

		return map;
	}

	/*
	 * Update Courses
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status updateCourses(
			@RequestBody CourseInformation courseInformation)
			throws HibernateException, Exception {

		if (courseInformation == null) {
			throw new EmptyFormElementsException();
		}
		try {
			courseInformation.setUD(new Date());
			CustomUser customUser = (CustomUser) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			courseInformation.setUB(customUser.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Errors> errorsList = editCourseFormValidation
				.validateForm(courseInformation);
		if (!errorsList.isEmpty()) {
			return new Status(HttpStatus.NOT_ACCEPTABLE.toString(),
					env.getProperty("exception.validationfailed"), errorsList);

		}
		dataServices.updateEntity(courseInformation);

		return new Status(HttpStatus.OK.toString(),
				env.getProperty("course.update"));
	}

	/*
	 * Delete Courses
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Status delete(@PathVariable long id) throws HibernateException,
			Exception {
		if (id == 0)
			throw new EntityNotFoundException();

		dataServices.deleteEntity(id, CourseInformation.class);

		return new Status(HttpStatus.ACCEPTED.toString(),
				env.getProperty("courses.delete"));

	}

	/*
	 * Courses List by Providers
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public List<CourseWrapper> list(HttpServletRequest request)
			throws HibernateException, Exception {
		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		Long userId = customUser.getId();
		List<CourseWrapper> courseInformations = customServices
				.getCourseListByProvider(userId);
		return courseInformations;
	}

	/*
	 * Get Course Summary
	 */

	@RequestMapping(value = "/summary/{id}", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getCoursesSummary(
			@PathVariable("id") long id) {
		Map<String, Object> courseInformationMap = null;

		try {
			courseInformationMap = entitySummaryServices.courseHeirarchy(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (courseInformationMap == null) {
			throw new EntityNotFoundException();
		}
		return courseInformationMap;
	}

	/*
	 * Edit Course Tags
	 */
	@RequestMapping(value = "/tags/{courseId}", method = RequestMethod.GET)
	@ResponseBody
	public List<Tags> editTag(@PathVariable long courseId)
			throws HibernateException, Exception {
		List<Tags> tags = new ArrayList<Tags>();
		List<Tags> tagsDB = customServices.tagsByCourseId(courseId);
		if (tagsDB == null)
			throw new EntityNotFoundException();

		for (Tags tag : tagsDB) {
			tags.add(new Tags(tag.getName(), tag.getId()));
		}

		return tags;
	}

	/*
	 * All Tags
	 */
	@RequestMapping(value = "/tags", method = RequestMethod.GET)
	@ResponseBody
	public List<Tags> alltags() throws HibernateException, Exception {
		List<Tags> tags = new ArrayList<Tags>();
		List<AbstractEntity> tagsDB = dataServices.getEntityList(Tags.class);
		if (tagsDB == null)
			throw new EntityNotFoundException();

		for (AbstractEntity tag : tagsDB) {
			Tags t = (Tags) tag;
			tags.add(new Tags(t.getName(), t.getId(), t.getDescription()));
		}

		return tags;
	}

	/*
	 * All Organization
	 */
	@RequestMapping(value = "/org", method = RequestMethod.GET)
	@ResponseBody
	public List<Organization> allOrg() throws HibernateException, Exception {
		List<Organization> organizations = new ArrayList<Organization>();
		List<AbstractEntity> orgDB = dataServices
				.getEntityList(Organization.class);
		if (orgDB == null)
			throw new EntityNotFoundException();

		for (AbstractEntity or : orgDB) {
			Organization o = (Organization) or;
			organizations.add(new Organization(o.getOrganizationType(), o
					.getOrganizationName(), o.getOrganizationDescription(), o
					.getPhotoLink(), o.getPhotoLink(), o.getId()));
		}

		return organizations;
	}

	/*
	 * Manage Course Tags
	 */
	@RequestMapping(value = "/tags/{courseId}", method = RequestMethod.PUT)
	@ResponseBody
	public List<Tags> manageTags(@RequestBody TagsManager tagsManager,
			@PathVariable Long courseId) throws HibernateException, Exception {
		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		if (tagsManager.getAddTagNames() != null) {
			String addedTags = tagsManager.getAddTagNames();
			String[] tagsArr = addedTags.split("\\|");
			for (String tag : tagsArr) {
				try {
					Tags t = new Tags();
					t.setName(tag);
					t.setCB(customUser.getId());
					t.setCD(new Date());

					dataServices.addEntity(new Tags(tag, ""));
				} catch (Exception e) {
					logger.info(e);
				}

			}
			List<Tags> tagsSet = customServices.getListByLike("Tags", tagsArr,
					"name");
			for (Tags t : tagsSet) {
				try {
					CoursesTags ct = new CoursesTags(courseId, t.getId());
					ct.setCB(customUser.getId());
					ct.setCD(new Date());
					dataServices.addEntity(ct);
				} catch (Exception e) {
					logger.info(e);
				}
			}
		}
		if (tagsManager.getDeleteTagIds() != null) {
			String addedTags = tagsManager.getDeleteTagIds();
			String[] tagsArr = addedTags.split("\\|");
			for (String tag : tagsArr) {
				try {
					long courseTagId = customServices
							.courseTagsByCourseAndTagId(courseId,
									Long.parseLong(tag)).getId();
					dataServices.deleteEntity(courseTagId, CoursesTags.class);
				} catch (Exception e) {
					logger.info(e);
				}
			}
		}
		return null;
	}
}
