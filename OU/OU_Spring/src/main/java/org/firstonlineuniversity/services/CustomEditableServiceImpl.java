package org.firstonlineuniversity.services;

import org.firstonlineuniversity.dao.CustomEditDao;
import org.firstonlineuniversity.models.courses.CourseContent;
import org.firstonlineuniversity.models.courses.CourseInformation;
import org.firstonlineuniversity.models.courses.CourseLectures;
import org.firstonlineuniversity.models.courses.CourseResources;
import org.firstonlineuniversity.models.courses.QuizInformation;
import org.firstonlineuniversity.models.courses.QuizQuestions;
import org.firstonlineuniversity.models.courses.QuizQuestionsOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("customEditableService")
public class CustomEditableServiceImpl implements CustomEditableService {

	@Autowired
	CustomEditDao dataDaoEditable;

	@Override
	public CourseContent getCourseFilesEditableDate(long id) throws Exception {
		return dataDaoEditable.getCourseFilesEditableDate(id);
	}

	@Override
	public CourseResources getCourseResourcesEditableDate(long id)
			throws Exception {
		return dataDaoEditable.getCourseResourcesEditableDate(id);
	}

	@Override
	public CourseLectures getCourseLecturesEditableData(long id)
			throws Exception {
		return dataDaoEditable.getCourseLecturesEditableData(id);
	}

	@Override
	public CourseInformation getCoursesEditableData(long id) throws Exception {
		return dataDaoEditable.getCoursesEditableData(id);
	}

	@Override
	public QuizInformation getQuizEditableData(long id) throws Exception {
		return dataDaoEditable.getQuizEditableData(id);
	}

	@Override
	public QuizQuestions getQuizQuestionsEditableData(long id) throws Exception {
		return dataDaoEditable.getQuizQuestionsEditableData(id);
	}

	@Override
	public QuizQuestionsOptions getQuizQuestionsOptionsEditableData(long id)
			throws Exception {
		return dataDaoEditable.getQuizQuestionsOptionsEditableData(id);
	}

}
