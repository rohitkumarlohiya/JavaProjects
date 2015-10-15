package org.firstonlineuniversity.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.firstonlineuniversity.domains.auth.custom.CustomUser;
import org.firstonlineuniversity.domains.custom.CustomCategory;
import org.firstonlineuniversity.exceptions.customexceptions.EntityNotFoundException;
import org.firstonlineuniversity.exceptions.customexceptions.ListNotFoundException;
import org.firstonlineuniversity.exceptions.customexceptions.SomethingWentWrongException;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.courses.CourseCategory;
import org.firstonlineuniversity.models.status.Status;
import org.firstonlineuniversity.services.CustomServices;
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
@RequestMapping("/api/coursesadmin/categories")
@PropertySource("classpath:message.properties")
public class CategoriesControllerRest {

	static final Logger logger = Logger
			.getLogger(CategoriesControllerRest.class);

	@Autowired
	Environment env;

	@Autowired
	DataServices<AbstractEntity> dataServices;

	@Autowired
	CustomServices customServices;

	/*
	 * Add Category
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Status add(@RequestBody CourseCategory courseCategory)
			throws HibernateException, Exception {

		courseCategory.setCD(new Date());
		courseCategory.setUD(new Date());
		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		courseCategory.setUB(customUser.getId());
		courseCategory.setCB(customUser.getId());

		dataServices.addEntity(courseCategory);
		Long categoryId = courseCategory.getId();
		if (categoryId == null)
			throw new SomethingWentWrongException();

		return new Status(categoryId, HttpStatus.ACCEPTED.toString(),
				env.getProperty("category.add"));

	}

	/*
	 * Edit Category
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody CourseCategory getCourseCategories(
			@PathVariable("id") long id) throws HibernateException, Exception {
		CourseCategory category = null;
		CourseCategory entity = null;

		category = (CourseCategory) dataServices.getEntity(id,
				CourseCategory.class);

		if (category == null) 
			throw new EntityNotFoundException();

		entity = new CourseCategory(category.getCategoryName(),
				category.getCategoryImageLink(),
				category.getCategoryDescription(), category.getSubjectAreaId(),
				category.getCategoryCode(), category.isActiveFlag(),
				category.getCourseCount(), null);
		entity.setId(category.getId());
		entity.setCB(category.getCB());
		entity.setCD(category.getCD());

		return entity;
	}

	/*
	 * Update Category
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status updateCourses(
			@RequestBody CourseCategory courseCategory)
			throws HibernateException, Exception {
		try {
			courseCategory.setUD(new Date());
			CustomUser customUser = (CustomUser) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			courseCategory.setUB(customUser.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		dataServices.updateEntity(courseCategory);

		return new Status(HttpStatus.OK.toString(),
				env.getProperty("category.update"));
	}

	/*
	 * Delete Category
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Status delete(@PathVariable long id) throws HibernateException,
			Exception {
		if (id == 0)
			throw new EntityNotFoundException();

		dataServices.deleteEntity(id, CourseCategory.class);

		return new Status(HttpStatus.ACCEPTED.toString(),
				env.getProperty("category.delete"));

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
}
