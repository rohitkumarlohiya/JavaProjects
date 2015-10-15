package org.firstonlineuniversity.domains.custom;

import java.util.Set;

public class CustomAccounts {
	private String accountEmail;
	private String accountPhone;
	private String password;
	private String firstName;
	private String lastName;
	private String accessLevel;
	private boolean isActivated;
	private Set<String> userRoles;
	
	public CustomAccounts() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomAccounts(String accountEmail, String accountPhone,
			String password, String firstName, String lastName,
			String accessLevel, boolean isActivated, Set<String> userRoles) {
		super();
		this.accountEmail = accountEmail;
		this.accountPhone = accountPhone;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.accessLevel = accessLevel;
		this.isActivated = isActivated;
		this.userRoles = userRoles;
	}


	public Set<String> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<String> userRoles) {
		this.userRoles = userRoles;
	}

	public String getAccountEmail() {
		return accountEmail;
	}

	public void setAccountEmail(String accountEmail) {
		this.accountEmail = accountEmail;
	}

	public String getAccountPhone() {
		return accountPhone;
	}

	public void setAccountPhone(String accountPhone) {
		this.accountPhone = accountPhone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(String accessLevel) {
		this.accessLevel = accessLevel;
	}

	public boolean isActivated() {
		return isActivated;
	}

	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}
}
