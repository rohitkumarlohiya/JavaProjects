package org.firstonlineuniversity.domains.auth.custom;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.firstonlineuniversity.models.enrollements.CoursesEnrollements;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUser extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	private long cb;
	private Date cd;
	private Set<CoursesEnrollements> coursesEnrollements = new HashSet<CoursesEnrollements>(
			0);

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Set<CoursesEnrollements> getCoursesEnrollements() {
		return coursesEnrollements;
	}

	public void setCoursesEnrollements(
			Set<CoursesEnrollements> coursesEnrollements) {
		this.coursesEnrollements = coursesEnrollements;
	}

	public long getCb() {
		return cb;
	}

	public void setCb(long cb) {
		this.cb = cb;
	}

	public Date getCd() {
		return cd;
	}

	public void setCd(Date cd) {
		this.cd = cd;
	}

	public CustomUser(long id, String username, String password,
			boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities,
			Set<CoursesEnrollements> coursesEnrollements) {
		super(username, password, enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked, authorities);
		this.id = id;
		this.coursesEnrollements = coursesEnrollements;
	}

	public CustomUser(long id, String username, String password,
			boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities,
			Set<CoursesEnrollements> coursesEnrollements, long cb, Date cd) {
		super(username, password, enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked, authorities);
		this.id = id;
		this.coursesEnrollements = coursesEnrollements;
		this.cb = cb;
		this.cd = cd;
	}

}
