package org.firstonlineuniversity.services;

import java.util.List;
import java.util.Map;

public interface EntitySummaryServices {
	public List<Map<String, Object>> coursesSummaryList(long id) throws Exception ;
	public List<Map<String, Object>> coursesSectionSummaryList(long courseId) throws Exception ;
	public List<Map<String, Object>> coursesLectureSummaryList(long courseId)
			throws Exception;
	public List<Map<String, Object>> coursesContentsSummaryList(long courseId)
			throws Exception;
	public List<Map<String, Object>> coursesResourcesSummaryList(long courseId)
			throws Exception;
	public List<Map<String, Object>> coursesQuizSummaryList(long courseId)
			throws Exception;
	public List<Map<String, Object>> coursesQuizQuestionsSummaryList(
			long quizId) throws Exception;
	public Map<String, Object> courseHeirarchy(long courseId)
			throws Exception;
	public Map<String, Object> courseHeirarchyFull(long courseId)
			throws Exception;
}
