package org.firstonlineuniversity.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.firstonlineuniversity.domains.auth.custom.CustomUser;
import org.firstonlineuniversity.exceptions.customexceptions.EmptyFormElementsException;
import org.firstonlineuniversity.exceptions.customexceptions.EntityNotFoundException;
import org.firstonlineuniversity.formvalidation.rest.AddCourseFormValidation;
import org.firstonlineuniversity.formvalidation.rest.EditCourseFormValidation;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.courses.CourseInformation;
import org.firstonlineuniversity.models.courses.CoursePrices;
import org.firstonlineuniversity.models.status.Status;
import org.firstonlineuniversity.services.CustomEditableService;
import org.firstonlineuniversity.services.CustomServices;
import org.firstonlineuniversity.services.DataServices;
import org.firstonlineuniversity.services.EntitySummaryServices;
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
@RequestMapping("/api/coursesadmin/courses/price")
@PropertySource("classpath:message.properties")
public class CoursePricesController {
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

	static final Logger logger = Logger.getLogger(CoursePricesController.class);

	/*
	 * Add Courses
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Status add(@RequestBody CoursePrices coursePrices)
			throws HibernateException, Exception {
		if (coursePrices == null)
			throw new EntityNotFoundException();
		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		coursePrices.setCB(customUser.getId());
		coursePrices.setUB(customUser.getId());
		coursePrices.setCD(new Date());
		coursePrices.setUD(new Date());
		coursePrices.setEnabled(true);

		if (coursePrices.getId() == null) {
			dataServices.addEntity(coursePrices);
			return new Status(HttpStatus.ACCEPTED.toString(),
					env.getProperty("courses.prices.add"));
		} else {
			dataServices.updateEntity(coursePrices);
			return new Status(HttpStatus.ACCEPTED.toString(),
					env.getProperty("courses.prices.update"));
		}
	}

	/*
	 * Edit Courses
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public CoursePrices edit(@PathVariable long id) throws HibernateException,
			Exception {

		CoursePrices coursePricesDB = (CoursePrices) dataServices.getEntity(id,
				CoursePrices.class);

		CoursePrices coursePrices = new CoursePrices(
				coursePricesDB.getPriceType(), coursePricesDB.getCurrency(),
				coursePricesDB.getPrice(), coursePricesDB.getDiscountType(),
				coursePricesDB.getDiscount(),
				coursePricesDB.getDiscountPercent(),
				coursePricesDB.getStartDate(), coursePricesDB.getEndDate(),
				null, null, new CourseInformation(coursePricesDB.getCourses()
						.getCourseName(), coursePricesDB.getCourses().getId()));
		coursePrices.setCD(coursePricesDB.getCD());
		coursePrices.setId(id);
		coursePrices.setCB(coursePricesDB.getCB());
		coursePrices.setEnabled(coursePricesDB.isEnabled());
		return coursePrices;

	}

	/*
	 * List
	 */
	@RequestMapping(value = "/list/{courseId}", method = RequestMethod.GET)
	@ResponseBody
	public List<CoursePrices> list(@PathVariable long courseId)
			throws HibernateException, Exception {
		List<CoursePrices> coursePrices = new ArrayList<CoursePrices>();
		List<CoursePrices> coursePricesDb = customServices
				.coursePrices(courseId);

		for (CoursePrices coursePricesDB : coursePricesDb) {
			if (!coursePricesDB.isEnabled())
				continue;

			CoursePrices coursePrice = new CoursePrices(
					coursePricesDB.getPriceType(),
					coursePricesDB.getCurrency(), coursePricesDB.getPrice(),
					coursePricesDB.getDiscountType(),
					coursePricesDB.getDiscount(),
					coursePricesDB.getDiscountPercent(),
					coursePricesDB.getStartDate(), coursePricesDB.getEndDate(),
					null, null, new CourseInformation(coursePricesDB
							.getCourses().getCourseName(), coursePricesDB
							.getCourses().getId()));
			coursePrice.setCD(coursePricesDB.getCD());
			coursePrice.setId(courseId);
			coursePrice.setCB(coursePricesDB.getCB());
			coursePrice.setEnabled(coursePricesDB.isEnabled());
			coursePrices.add(coursePrice);
			coursePrice.setId(coursePricesDB.getId());
		}
		return coursePrices;
	}

	/*
	 * Update Courses
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status updateCourses(
			@RequestBody CoursePrices coursePrices) throws HibernateException,
			Exception {
		if (coursePrices == null) {
			throw new EmptyFormElementsException();
		}

		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		coursePrices.setUB(customUser.getId());
		coursePrices.setUD(new Date());

		dataServices.updateEntity(coursePrices);
		return new Status(HttpStatus.OK.toString(),
				env.getProperty("courses.prices.update"));
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

		dataServices.deleteEntity(id, CoursePrices.class);

		return new Status(HttpStatus.ACCEPTED.toString(),
				env.getProperty("courses.price.delete"));

	}
}
