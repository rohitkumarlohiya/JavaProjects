package org.firstonlineuniversity.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.firstonlineuniversity.exceptions.customexceptions.EntityNotFoundException;
import org.firstonlineuniversity.services.EntitySummaryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/coursesadmin/entitysummary/courses")
@PropertySource("classpath:message.properties")
public class EntitySummaryController {

	@Autowired
	EntitySummaryServices entitySummaryServices;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody List<Map<String, Object>> getCoursesSummaryList(
			@RequestParam(required = false) Long categoryid, HttpServletRequest request) {

		if (categoryid == null)
			categoryid = 0l;

		List<Map<String, Object>> list = null;
		try {
			list = entitySummaryServices.coursesSummaryList(categoryid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (list == null)
			throw new EntityNotFoundException();

		return list;
	}

	@RequestMapping(value = "/sections/", method = RequestMethod.GET)
	public @ResponseBody List<Map<String, Object>> getCoursesSectionsSummaryList(
			@RequestParam Long courseid) {

		List<Map<String, Object>> list = null;
		try {
			list = entitySummaryServices.coursesSectionSummaryList(courseid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (list == null)
			throw new EntityNotFoundException();

		return list;
	}

	@RequestMapping(value = "/lectures/", method = RequestMethod.GET)
	public @ResponseBody List<Map<String, Object>> getCoursesLecturesSummaryList(
			@RequestParam Long courseid) {

		List<Map<String, Object>> list = null;
		try {
			list = entitySummaryServices.coursesLectureSummaryList(courseid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (list == null)
			throw new EntityNotFoundException();

		return list;
	}

	@RequestMapping(value = "/content/", method = RequestMethod.GET)
	public @ResponseBody List<Map<String, Object>> getCoursesContentSummaryList(
			@RequestParam Long courseid) {

		List<Map<String, Object>> list = null;
		try {
			list = entitySummaryServices.coursesContentsSummaryList(courseid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (list == null)
			throw new EntityNotFoundException();

		return list;
	}

	@RequestMapping(value = "/resources/", method = RequestMethod.GET)
	public @ResponseBody List<Map<String, Object>> getCoursesResourcesSummaryList(
			@RequestParam Long courseid) {

		List<Map<String, Object>> list = null;
		try {
			list = entitySummaryServices.coursesResourcesSummaryList(courseid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (list == null)
			throw new EntityNotFoundException();

		return list;
	}

	@RequestMapping(value = "/quiz/", method = RequestMethod.GET)
	public @ResponseBody List<Map<String, Object>> getCoursesQuizSummaryList(
			@RequestParam Long courseid) {

		List<Map<String, Object>> list = null;
		try {
			list = entitySummaryServices.coursesQuizSummaryList(courseid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (list == null)
			throw new EntityNotFoundException();

		return list;
	}

	@RequestMapping(value = "/quizques/", method = RequestMethod.GET)
	public @ResponseBody List<Map<String, Object>> getCoursesQuizQuesSummaryList(
			@RequestParam Long quizid) {

		List<Map<String, Object>> list = null;
		try {
			list = entitySummaryServices
					.coursesQuizQuestionsSummaryList(quizid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (list == null)
			throw new EntityNotFoundException();

		return list;
	}
}
