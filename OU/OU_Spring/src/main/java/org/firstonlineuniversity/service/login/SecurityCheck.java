package org.firstonlineuniversity.service.login;

import java.util.Set;

import org.firstonlineuniversity.domains.auth.custom.CustomUser;
import org.firstonlineuniversity.models.enrollements.CoursesEnrollements;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityCheck {

	public static boolean hasRole(String role) {
		try {
			SecurityContext context = SecurityContextHolder.getContext();
			if (context == null)
				return false;

			Authentication authentication = context.getAuthentication();
			if (authentication == null)
				return false;

			for (GrantedAuthority auth : authentication.getAuthorities()) {
				if (role.equals(auth.getAuthority()))
					return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean enrolled(long courseId) {
		try {
			CustomUser user = (CustomUser) SecurityContextHolder.getContext()
					.getAuthentication().getPrincipal();
			Set<CoursesEnrollements> coursesEnrollementsList = user
					.getCoursesEnrollements();

			for (CoursesEnrollements coursesEnrollement : coursesEnrollementsList) {
				if (coursesEnrollement.getCourseId() == courseId)
					return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
