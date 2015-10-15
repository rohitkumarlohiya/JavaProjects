package org.firstonlineuniversity.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.firstonlineuniversity.domains.custom.CoursesNavigationGroups;
import org.firstonlineuniversity.domains.custom.CustomCategory;
import org.firstonlineuniversity.domains.custom.CustomCourseProviders;
import org.firstonlineuniversity.domains.custom.CustomCoursesFilterNav;
import org.firstonlineuniversity.domains.custom.CustomCoursesInformation;
import org.firstonlineuniversity.exceptions.customexceptions.BadRequestException;
import org.firstonlineuniversity.exceptions.customexceptions.EntityNotFoundException;
import org.firstonlineuniversity.exceptions.customexceptions.ListNotFoundException;
import org.firstonlineuniversity.models.courses.CourseCategory;
import org.firstonlineuniversity.models.courses.CourseInformation;
import org.firstonlineuniversity.models.courses.CourseProviders;
import org.firstonlineuniversity.models.courses.Providers;
import org.firstonlineuniversity.models.courses.Tags;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.commons.AbstractEntityList;
import org.firstonlineuniversity.models.login.Accounts;
import org.firstonlineuniversity.models.status.Status;
import org.firstonlineuniversity.services.CustomServices;
import org.firstonlineuniversity.services.DataServices;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/courses")
@PropertySource("classpath:message.properties")
public class CoursesControllerRest {

	public static final String ROOT_URI = "/api/categories";

	@Autowired
	Environment env;

	@Autowired
	DataServices<AbstractEntity> dataServices;

	@Autowired
	CustomServices customServices;

	static final Logger logger = Logger.getLogger(CoursesControllerRest.class);

	/*
	 * Get Courses List
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody AbstractEntityList getCurseListByFilter(
			@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer perPage,
			@RequestParam(required = false) String category,
			@RequestParam(required = false) String difficultyLevel,
			@RequestParam(required = false) String courseType,
			@RequestParam(required = false) boolean popularCourse,
			@RequestParam(required = false) boolean featuredCourse,
			@RequestParam(required = false) boolean startingSoon,
			@RequestParam(required = false) Long organizationId,
			@RequestParam(required = false) String primaryLanguage,
			@RequestParam(required = false) Long authorId)
			throws HibernateException, Exception {

		AbstractEntityList abstractEntityList = null;
		List<AbstractEntity> abstractEntities = null;
		Map<String, Object> queryMap = new HashMap<String, Object>();

		if (page != null && perPage == null)
			throw new BadRequestException(
					env.getProperty("exception.pagination.perpage"));
		if (page == null && perPage != null)
			throw new BadRequestException(
					env.getProperty("exception.pagination.page"));
		if (popularCourse == true) {
			queryMap.put("popularCourse", popularCourse);
		}
		if (authorId != null) {
			queryMap.put("authorId", authorId);
		}
		if (featuredCourse == true) {
			queryMap.put("featuredCourse", featuredCourse);
		}
		if (startingSoon == true) {
			queryMap.put("startingSoon", startingSoon);
		}
		if (organizationId != null) {
			queryMap.put("organizationId", organizationId);
		}
		if (primaryLanguage != null) {
			queryMap.put("primaryLanguage", primaryLanguage);
		}
		if (courseType != null) {
			String[] courseTypes = null;
			if (courseType.contains(","))
				courseTypes = courseType.split("\\,");
			else {
				courseTypes = new String[1];
				courseTypes[0] = courseType;
			}
			queryMap.put("courseType", courseTypes);
		}
		if (category != null) {
			String[] categories = null;
			if (category.contains(","))
				categories = category.split("\\,");
			else {
				categories = new String[1];
				categories[0] = category;
			}
			queryMap.put("category", categories);
		}
		if (difficultyLevel != null) {
			String[] difficultyLevelArray = null;
			if (difficultyLevel.contains(","))
				difficultyLevelArray = difficultyLevel.split("\\,");
			else {
				difficultyLevelArray = new String[1];
				difficultyLevelArray[0] = difficultyLevel;
			}
			queryMap.put("difficultyLevel", difficultyLevelArray);
		}

		abstractEntityList = dataServices.getEntityListMultipleFilter(
				CourseInformation.class, page, perPage, queryMap);

		if (abstractEntityList == null) {
			throw new ListNotFoundException();
		}

		abstractEntities = new ArrayList<AbstractEntity>();
		for (AbstractEntity abEntity : abstractEntityList.getEntityList()) {
			CourseInformation courseInformation = (CourseInformation) abEntity;
			abstractEntities.add(new CustomCoursesInformation(courseInformation
					.getId(), courseInformation.getCourseName(),
					courseInformation.getCourseDescription(), courseInformation
							.getCourseLongDescription(), courseInformation
							.getCourseDuration(), courseInformation
							.getPreRequisite(), courseInformation
							.getDifficultyLevel(), courseInformation
							.getVersionNumber(), courseInformation
							.getVersionDescription(), courseInformation
							.getCourseResources(), courseInformation
							.getCourseLogistics(), courseInformation
							.getCourseType(), courseInformation.getCourseFaq(),
					courseInformation.isNewCourse(), courseInformation
							.isPopularCourse(), courseInformation
							.isFeaturedCourse(), courseInformation
							.getCourseImageLink(), courseInformation
							.getPromoVideoLink(), courseInformation
							.getCourseLoad(), courseInformation
							.getOrganizationId(), courseInformation
							.getPrimaryLanguage()));

			abstractEntityList.setEntityList(abstractEntities);
		}

		return abstractEntityList;

	}

	/*
	 * Get Individual Course
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody CustomCoursesInformation getCourse(
			@PathVariable("id") long id) throws HibernateException, Exception {
		CourseInformation courseInformation = null;
		CustomCoursesInformation customCoursesInformation = null;

		courseInformation = (CourseInformation) dataServices.getEntity(id,
				CourseInformation.class);

		if (courseInformation == null) {
			throw new EntityNotFoundException();
		}

		customCoursesInformation = new CustomCoursesInformation(

		courseInformation.getId(), courseInformation.getCourseName(),
				courseInformation.getCourseDescription(),
				courseInformation.getCourseLongDescription(),
				courseInformation.getCourseDuration(),
				courseInformation.getPreRequisite(),
				courseInformation.getDifficultyLevel(),
				courseInformation.getVersionNumber(),
				courseInformation.getVersionDescription(),
				courseInformation.getCourseResources(),
				courseInformation.getCourseLogistics(),
				courseInformation.getCourseType(),
				courseInformation.getCourseFaq(),
				courseInformation.isNewCourse(),
				courseInformation.isPopularCourse(),
				courseInformation.isFeaturedCourse(),
				courseInformation.getCourseImageLink(),
				courseInformation.getPromoVideoLink(),
				courseInformation.getCourseLoad(),
				courseInformation.getOrganizationId(),
				courseInformation.getPrimaryLanguage());

		return customCoursesInformation;
	}

	/*
	 * Get Individual Course All Information
	 */
	@RequestMapping(value = "/full/{id}", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getCourseFull(
			@PathVariable("id") long id) throws HibernateException, Exception {
		Map<String, Object> map = customServices.getFullCourseSingle(id);

		CourseInformation courseInformation = (CourseInformation) map
				.get("courseInformation");

		if (courseInformation == null) {
			throw new EntityNotFoundException();
		}

		CustomCoursesInformation customCourseInformation = new CustomCoursesInformation(
				courseInformation.getId(), courseInformation.getCourseName(),
				courseInformation.getCourseDescription(),
				courseInformation.getCourseLongDescription(),
				courseInformation.getCourseDuration(),
				courseInformation.getPreRequisite(),
				courseInformation.getDifficultyLevel(),
				courseInformation.getVersionNumber(),
				courseInformation.getVersionDescription(),
				courseInformation.getCourseResources(),
				courseInformation.getCourseLogistics(),
				courseInformation.getCourseType(),
				courseInformation.getCourseFaq(),
				courseInformation.isNewCourse(),
				courseInformation.isPopularCourse(),
				courseInformation.isFeaturedCourse(),
				courseInformation.getCourseImageLink(),
				courseInformation.getPromoVideoLink(),
				courseInformation.getCourseLoad(),
				courseInformation.getOrganizationId(),
				courseInformation.getPrimaryLanguage());
		customCourseInformation.setCredits(courseInformation.getCredits());
		customCourseInformation.setPaidCourse(customCourseInformation
				.isPaidCourse());

		map.put("courseInformation", customCourseInformation);

		List<Tags> tags = new ArrayList<Tags>();
		List<Tags> tagsDB = customServices.tagsByCourseId(id);
		if (tagsDB == null)
			throw new EntityNotFoundException();

		for (Tags tag : tagsDB) {
			tags.add(new Tags(tag.getName(), tag.getId()));
		}

		map.put("tags", tags);

		return map;

	}

	/*
	 * Get Categories List
	 */
	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public @ResponseBody List<CustomCategory> getCurseCategoriesList()
			throws HibernateException, Exception {
		List<CourseCategory> courseCategoryList = null;
		List<CustomCategory> catList = null;

		courseCategoryList = customServices.getCategoryList();

		if (courseCategoryList == null) {
			throw new ListNotFoundException();
		}

		logger.info("CoursesCategory List size: " + courseCategoryList.size());

		catList = new ArrayList<CustomCategory>();
		for (CourseCategory abstractEntity : courseCategoryList) {
			CourseCategory category = (CourseCategory) abstractEntity;

			CustomCategory entity = new CustomCategory(
					category.getCategoryName(), category.getId(),
					category.getCategoryDescription(),
					category.getSubjectAreaId(), category.getCategoryCode(),
					category.isActiveFlag(), category.getCategoryImageLink());
			entity.setCourseSize(abstractEntity.getCourseCount());
			catList.add(entity);
		}

		return catList;

	}

	/*
	 * Get Individual Category
	 */
	@RequestMapping(value = "/categories/{id}", method = RequestMethod.GET)
	public @ResponseBody CustomCategory getCourseCategories(
			@PathVariable("id") long id) throws HibernateException, Exception {
		CourseCategory category = null;
		CustomCategory entity = null;

		category = (CourseCategory) dataServices.getEntity(id,
				CourseCategory.class);

		if (category == null) {
			throw new EntityNotFoundException();
		}

		entity = new CustomCategory(category.getCategoryName(),
				category.getId(), category.getCategoryDescription(),
				category.getSubjectAreaId(), category.getCategoryCode(),
				category.isActiveFlag(), category.getCategoryImageLink());

		return entity;
	}

	@RequestMapping(value = "/providers", method = RequestMethod.GET)
	public @ResponseBody List<AbstractEntity> getCurseProvidersList()
			throws HibernateException, Exception {

		List<AbstractEntity> providersList = new ArrayList<AbstractEntity>();
		List<Providers> providerDBList = customServices.allProviders();
		if (providerDBList == null)
			throw new EntityNotFoundException();

		for (AbstractEntity ae : providerDBList) {
			Providers providerDB = (Providers) ae;
			Accounts acDb = providerDB.getAccounts();

			Accounts ac = new Accounts(acDb.getId());

			Providers provider = new Providers(null,
					providerDB.getProviderType(), providerDB.getProviderName(),
					providerDB.getExperience(), providerDB.getIntroduction(),
					providerDB.getContactId(),
					providerDB.getProviderPhotoLink(),
					providerDB.getDesignation(), providerDB.getDepartment(),
					providerDB.isEnabled(), null, null, providerDB.getCD(),
					providerDB.getCB() == null ? 0l : providerDB.getCB());
			provider.setId(providerDB.getId());
			provider.setAccounts(ac);

			providersList.add(provider);
		}
		return providersList;
	}

	/*
	 * Get Individual Provider
	 */
	@RequestMapping(value = "/providers/{id}", method = RequestMethod.GET)
	public @ResponseBody List<CustomCourseProviders> getCurseProvidersList(
			@PathVariable("id") long id) throws HibernateException, Exception {
		CustomCourseProviders providers = null;
		List<CustomCourseProviders> courseProviders = null;
		List<AbstractEntity> abstractEntitiesDB = dataServices
				.getProvidersByCourseId(id);
		if (abstractEntitiesDB == null)
			throw new EntityNotFoundException();

		courseProviders = new ArrayList<CustomCourseProviders>();
		for (AbstractEntity abstractEntity : abstractEntitiesDB) {
			Providers providersDB = (Providers) abstractEntity;

			providers = new CustomCourseProviders(
					providersDB.getProviderType(),
					providersDB.getProviderName(), providersDB.getExperience(),
					providersDB.getIntroduction(), providersDB.getContactId(),
					providersDB.getProviderPhotoLink(),
					providersDB.getDesignation(), providersDB.getDepartment(),
					providersDB.getId());

			Set<CourseProviders> cpSet = providersDB.getCourseProviders();
			if (cpSet != null) {
				for (CourseProviders cpDb : cpSet) {
					CourseProviders cp = new CourseProviders();
					cp.setId(cpDb.getId());
					providers.setCourseProvidersId(cp.getId());
					break;
				}
			}
			courseProviders.add(providers);
		}
		return courseProviders;
	}

	/*
	 * Get Right Navigation
	 */
	@RequestMapping(value = "/nav", method = RequestMethod.GET)
	public @ResponseBody CoursesNavigationGroups getCourseNav()
			throws HibernateException, Exception {
		List<CustomCoursesFilterNav> list = dataServices.getCoursesFilterNav();

		if (list == null)
			throw new EntityNotFoundException();

		Map<String, String> difficultyLevelMap = new HashMap<String, String>();
		Map<String, String> languageMap = new HashMap<String, String>();
		Map<String, String> courseTypeMap = new HashMap<String, String>();
		Map<String, String> courseFormatlMap = new HashMap<String, String>();
		Map<String, String> categoriesMap = new HashMap<String, String>();
		Map<String, String> organizationMap = new HashMap<String, String>();
		Map<String, String> authorMap = new HashMap<String, String>();

		for (CustomCoursesFilterNav coursesFilterNav : list) {
			if (coursesFilterNav.getType().equalsIgnoreCase("DIFFICULTY_LEVEL"))
				difficultyLevelMap.put(coursesFilterNav.getCode(),
						coursesFilterNav.getName());

			if (coursesFilterNav.getType().equalsIgnoreCase("LANGUAGE"))
				languageMap.put(coursesFilterNav.getCode(),
						coursesFilterNav.getName());

			if (coursesFilterNav.getType().equalsIgnoreCase("COURSE_TYPE"))
				courseTypeMap.put(coursesFilterNav.getCode(),
						coursesFilterNav.getName());

			if (coursesFilterNav.getType().equalsIgnoreCase("COURSE_FORMAT"))
				courseFormatlMap.put(coursesFilterNav.getCode(),
						coursesFilterNav.getName());

			if (coursesFilterNav.getType().equalsIgnoreCase("CATEGORIES"))
				categoriesMap.put(coursesFilterNav.getCode(),
						coursesFilterNav.getName());

			if (coursesFilterNav.getType().equalsIgnoreCase("ORGANIZATIONS"))
				organizationMap.put(coursesFilterNav.getCode(),
						coursesFilterNav.getName());

			if (coursesFilterNav.getType().equalsIgnoreCase("AUTHORS"))
				authorMap.put(coursesFilterNav.getCode(),
						coursesFilterNav.getName());
		}

		CoursesNavigationGroups coursesNavigationGroups = new CoursesNavigationGroups(
				difficultyLevelMap, languageMap, courseTypeMap,
				courseFormatlMap, categoriesMap, organizationMap, authorMap);

		return coursesNavigationGroups;
	}

}
