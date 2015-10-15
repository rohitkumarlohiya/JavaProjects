package org.firstonlineuniversity.services;

import java.util.List;
import java.util.UUID;

import org.firstonlineuniversity.dao.CourseReviewsDao;
import org.firstonlineuniversity.models.courses.CourseReviews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseReviewsServicesImpl implements CourseReviewsServices {

	@Autowired
	CourseReviewsDao reviewsDao;

	@Override
	public void addCourseReviews(CourseReviews courseReviews) {
		reviewsDao.addCourseReviews(courseReviews);
	}

	@Override
	public List<CourseReviews> getCourseReviews(Long accountId, Long courseId) {
		return reviewsDao.getCourseReviews(accountId, courseId);
	}

	@Override
	public CourseReviews getCourseReviewsById(Long courseId, UUID reviewId) {
		return reviewsDao.getCourseReviewsById(courseId, reviewId);
	}

	@Override
	public void updateCourseReviews(CourseReviews courseReviews) {
		reviewsDao.updateCourseReviews(courseReviews);
	}

	@Override
	public void deleteCourseReviews(Long courseId, UUID reviewId) {
		reviewsDao.deleteCourseReviews(courseId, reviewId);
	}

}
