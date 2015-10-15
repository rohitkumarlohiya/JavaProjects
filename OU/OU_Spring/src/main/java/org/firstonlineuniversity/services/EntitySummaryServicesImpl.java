package org.firstonlineuniversity.services;

import java.util.List;
import java.util.Map;

import org.firstonlineuniversity.dao.EntitySummaryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntitySummaryServicesImpl implements EntitySummaryServices {

	@Autowired
	EntitySummaryDao entitySummaryDao;

	@Override
	public List<Map<String, Object>> coursesSummaryList(long id)
			throws Exception {
		return entitySummaryDao.coursesSummaryList(id);
	}

	@Override
	public List<Map<String, Object>> coursesSectionSummaryList(long courseId)
			throws Exception {
		return entitySummaryDao.coursesSectionSummaryList(courseId);
	}

	@Override
	public List<Map<String, Object>> coursesLectureSummaryList(long courseId)
			throws Exception {
		return entitySummaryDao.coursesLectureSummaryList(courseId);
	}

	@Override
	public List<Map<String, Object>> coursesContentsSummaryList(long courseId)
			throws Exception {
		return entitySummaryDao.coursesContentsSummaryList(courseId);
	}

	@Override
	public List<Map<String, Object>> coursesResourcesSummaryList(long courseId)
			throws Exception {
		return entitySummaryDao.coursesResourcesSummaryList(courseId);
	}

	@Override
	public List<Map<String, Object>> coursesQuizSummaryList(long courseId)
			throws Exception {
		return entitySummaryDao.coursesQuizSummaryList(courseId);
	}

	@Override
	public List<Map<String, Object>> coursesQuizQuestionsSummaryList(long quizId)
			throws Exception {
		return entitySummaryDao.coursesQuizQuestionsSummaryList(quizId);
	}

	@Override
	public Map<String, Object> courseHeirarchy(long courseId) throws Exception {
		return entitySummaryDao.courseHeirarchy(courseId);
	}

	@Override
	public Map<String, Object> courseHeirarchyFull(long courseId)
			throws Exception {
		return entitySummaryDao.courseHeirarchyFull(courseId);
	}

}
