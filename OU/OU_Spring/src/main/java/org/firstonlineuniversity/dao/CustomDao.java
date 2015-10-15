package org.firstonlineuniversity.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.firstonlineuniversity.domains.custom.CourseWrapper;
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

public interface CustomDao {
	public CourseResources getCourseResourses(long id);

	public AbstractEntity getEdutableData(String entityName, long id);

	public List<UserPrograms> getEdutabdleData(Long userId, Long programId);

	public List<UserContent> getUserDataByUserIdAndContentId(Long userId,
			Long contentId);

	public List<UserContent> getUserContentByUserIdAndCourseId(Long userId,
			Long courseId);

	public int getCourseContentCountByCourseId(Long courseId);

	public Providers getProviderByUserId(long userId);

	public Map<String, Object> getFullCourseSingle(long courseId);

	public List<CoursesEnrollements> getEnrolledCoursesByUserId(long userId);

	public List<CourseCategory> getCategoryList();

	public CourseCatalogView getCourseCatalogView(Long courseId)
			throws Exception;

	public List<Tags> getListByLike(String entityName, String[] like,
			String columnName) throws Exception;

	public List<CourseWrapper> getCourseListByProvider(long accountId);

	public List<Tags> tagsByCourseId(long courseId);

	public CoursesTags courseTagsByCourseAndTagId(long courseId, long tagId);

	public List<QuizQuestions> questionsListByQuizId(Long quizId);

	public List<QuizQuestionsOptions> optionsListByQuestionId(Long questionList);

	public boolean deleteQuiz(Long quizId);

	public boolean existsCourseKey(String courseKey);

	public List<AbstractEntity> courseStatuses(Long courseId);

	public List<QuizAttempts> quizAttemptList(Long accountId, Long quizId);

	public List<QuizHistory> quizHistoryList(Long accountId, Long courseId,
			Long quizId);

	public List<Providers> providerListByuAccountId(Long accountId);
	
	public List<Providers> providerListByOrgId(Long orgId);

	public List<ProviderSummaryView> providersView();

	public List<CoursePrices> coursePrices(Long courseId);

	public boolean updateRoles(String role, int flag, Long accountId);

	public List<UserRole> getUserRole(Long accountId);

	public Set<String> allQuickCodesType();

	public List<Providers> allProviders();

	public List<QuizHistory> quizHistoryByAccountAndQuizId(Long quizId,
			Long accountId);

	public List<QuizAttempts> quizAttemptByQuizId(Long quizId, Long accountId);

	public List<QuizAttemptDetails> quizAttemptDetailsByQuizId(Long attemptId);

	public PaymentTransactions getTransaction(Long courseId, Long accountId);
}
