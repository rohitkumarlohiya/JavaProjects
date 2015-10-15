package org.firstonlineuniversity.services;

import org.firstonlineuniversity.models.courses.CourseContent;
import org.firstonlineuniversity.models.courses.CourseInformation;
import org.firstonlineuniversity.models.courses.CourseLectures;
import org.firstonlineuniversity.models.courses.CourseResources;
import org.firstonlineuniversity.models.courses.QuizInformation;
import org.firstonlineuniversity.models.courses.QuizQuestions;
import org.firstonlineuniversity.models.courses.QuizQuestionsOptions;

public interface CustomEditableService {
	public CourseContent getCourseFilesEditableDate(long id) throws Exception;

	public CourseResources getCourseResourcesEditableDate(long id)
			throws Exception;

	public CourseLectures getCourseLecturesEditableData(long id)
			throws Exception;

	public CourseInformation getCoursesEditableData(long id) throws Exception;

	public QuizInformation getQuizEditableData(long id) throws Exception;

	public QuizQuestions getQuizQuestionsEditableData(long id) throws Exception;

	public QuizQuestionsOptions getQuizQuestionsOptionsEditableData(long id)
			throws Exception;
}
