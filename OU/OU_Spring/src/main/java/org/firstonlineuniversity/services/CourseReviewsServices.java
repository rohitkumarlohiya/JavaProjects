package org.firstonlineuniversity.services;

import java.util.List;
import java.util.UUID;

import org.firstonlineuniversity.models.courses.CourseReviews;

public interface CourseReviewsServices {
	public void addCourseReviews(CourseReviews courseReviews);

	public List<CourseReviews> getCourseReviews(Long accountId, Long courseId);

	public CourseReviews getCourseReviewsById(Long courseId, UUID reviewId);

	public void updateCourseReviews(CourseReviews courseReviews);

	public void deleteCourseReviews(Long courseId, UUID reviewId);
}
