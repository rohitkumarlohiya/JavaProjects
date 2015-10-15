package org.firstonlineuniversity.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.firstonlineuniversity.dao.CustomDao;
import org.firstonlineuniversity.domains.custom.CourseWrapper;
import org.firstonlineuniversity.exceptions.customexceptions.SomethingWentWrongException;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.courses.CourseCategory;
import org.firstonlineuniversity.models.courses.CoursePrices;
import org.firstonlineuniversity.models.courses.CourseResources;
import org.firstonlineuniversity.models.courses.CoursesTags;
import org.firstonlineuniversity.models.courses.Providers;
import org.firstonlineuniversity.models.courses.QuizAttemptDetails;
import org.firstonlineuniversity.models.courses.QuizAttempts;
import org.firstonlineuniversity.models.courses.QuizHistory;
import org.firstonlineuniversity.models.courses.QuizQuestions;
import org.firstonlineuniversity.models.courses.QuizQuestionsOptions;
import org.firstonlineuniversity.models.courses.Tags;
import org.firstonlineuniversity.models.courses.UserContent;
import org.firstonlineuniversity.models.courses.UserPrograms;
import org.firstonlineuniversity.models.enrollements.CoursesEnrollements;
import org.firstonlineuniversity.models.login.UserRole;
import org.firstonlineuniversity.models.payment.PaymentTransactions;
import org.firstonlineuniversity.models.views.CourseCatalogView;
import org.firstonlineuniversity.models.views.ProviderSummaryView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomServicesImpl implements CustomServices {

	@Autowired
	CustomDao customDao;

	static final Logger logger = Logger.getLogger(CustomServicesImpl.class);

	@Override
	public CourseResources getCourseResourses(long id) {
		return customDao.getCourseResourses(id);
	}

	@Override
	public AbstractEntity getEdutableData(String entityName, long id) {
		return customDao.getEdutableData(entityName, id);
	}

	@Override
	public List<UserPrograms> getEdutabdleData(Long userId, Long programId) {
		return customDao.getEdutabdleData(userId, programId);
	}

	@Override
	public List<UserContent> getUserDataByUserIdAndContentId(Long userId,
			Long contentId) {
		return customDao.getUserDataByUserIdAndContentId(userId, contentId);
	}

	@Override
	public Map<String, Object> getUserContentByUserIdAndCourseId(Long userId,
			Long courseId) {
		Map<String, Object> totalValues = new HashMap<String, Object>();
		int count = customDao.getCourseContentCountByCourseId(courseId);

		if (count == 0)
			throw new SomethingWentWrongException();

		int totalCompletionStatus = 0;
		int totalTimeSpent = 0;
		List<UserContent> userContents = customDao
				.getUserContentByUserIdAndCourseId(userId, courseId);
		for (UserContent content : userContents) {
			totalCompletionStatus += content.getCompletionStatus();
			totalTimeSpent += content.getTimeSpent();
		}
		totalCompletionStatus = (totalCompletionStatus / count);
		totalValues.put("contentsStatus", userContents);
		totalValues.put("totalCourseTimeSpent", totalTimeSpent);
		totalValues.put("totalCourseCompletionStatus", totalCompletionStatus);

		return totalValues;
	}

	@Override
	public Providers getProviderByUserId(long userId) {
		return customDao.getProviderByUserId(userId);
	}

	@Override
	public Map<String, Object> getFullCourseSingle(long courseId) {
		return customDao.getFullCourseSingle(courseId);
	}

	@Override
	public List<CoursesEnrollements> getEnrolledCoursesByUserId(long userId) {
		return customDao.getEnrolledCoursesByUserId(userId);
	}

	@Override
	public List<CourseCategory> getCategoryList() {
		return customDao.getCategoryList();
	}

	@Override
	public CourseCatalogView getCourseCatalogView(Long courseId)
			throws Exception {
		return customDao.getCourseCatalogView(courseId);
	}

	@Override
	public List<Tags> getListByLike(String entityName, String[] like,
			String columnName) throws Exception {
		return customDao.getListByLike(entityName, like, columnName);
	}

	@Override
	public List<CourseWrapper> getCourseListByProvider(long accountId) {
		return customDao.getCourseListByProvider(accountId);
	}

	@Override
	public List<Tags> tagsByCourseId(long courseId) {
		return customDao.tagsByCourseId(courseId);
	}

	@Override
	public CoursesTags courseTagsByCourseAndTagId(long courseId, long tagId) {
		return customDao.courseTagsByCourseAndTagId(courseId, tagId);
	}

	@Override
	public List<QuizQuestionsOptions> optionsListByQuestionId(Long questionList) {
		return customDao.optionsListByQuestionId(questionList);
	}

	@Override
	public List<QuizQuestions> questionsListByQuizId(Long quizId) {
		return customDao.questionsListByQuizId(quizId);
	}

	@Override
	public boolean deleteQuiz(Long quizId) {
		return customDao.deleteQuiz(quizId);
	}

	@Override
	public boolean existsCourseKey(String courseKey) {
		return customDao.existsCourseKey(courseKey);
	}

	@Override
	public List<AbstractEntity> courseStatuses(Long courseId) {
		return customDao.courseStatuses(courseId);
	}

	@Override
	public List<QuizAttempts> quizAttemptList(Long accountId, Long quizId) {
		return customDao.quizAttemptList(accountId, quizId);
	}

	@Override
	public List<QuizHistory> quizHistoryList(Long accountId, Long courseId,
			Long quizId) {
		return customDao.quizHistoryList(accountId, courseId, quizId);
	}

	@Override
	public List<Providers> providerListByuAccountId(Long accountId) {
		return customDao.providerListByuAccountId(accountId);
	}

	@Override
	public List<ProviderSummaryView> providersView() {
		return customDao.providersView();
	}

	@Override
	public List<CoursePrices> coursePrices(Long courseId) {
		return customDao.coursePrices(courseId);
	}

	@Override
	public boolean updateRoles(String role, int flag, Long accountId) {
		return customDao.updateRoles(role, flag, accountId);
	}

	@Override
	public List<UserRole> getUserRole(Long accountId) {
		return customDao.getUserRole(accountId);
	}

	@Override
	public Set<String> allQuickCodesType() {
		return customDao.allQuickCodesType();
	}

	@Override
	public List<Providers> allProviders() {
		return customDao.allProviders();
	}

	@Override
	public List<QuizHistory> quizHistoryByAccountAndQuizId(Long quizId,
			Long accountId) {
		return customDao.quizHistoryByAccountAndQuizId(quizId, accountId);
	}

	@Override
	public List<QuizAttempts> quizAttemptByQuizId(Long quizId, Long accountId) {
		return customDao.quizAttemptByQuizId(quizId, accountId);
	}

	@Override
	public List<QuizAttemptDetails> quizAttemptDetailsByQuizId(Long attemptId) {
		return customDao.quizAttemptDetailsByQuizId(attemptId);
	}

	@Override
	public PaymentTransactions getTransaction(Long courseId, Long accountId) {
		return customDao.getTransaction(courseId,accountId);
	}

	@Override
	public List<Providers> providerListByOrgId(Long orgId) {
		return customDao.providerListByOrgId(orgId);
	}
}
