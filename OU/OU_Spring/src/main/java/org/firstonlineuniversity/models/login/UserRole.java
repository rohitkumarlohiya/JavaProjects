package org.firstonlineuniversity.models.login;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.firstonlineuniversity.models.commons.AbstractEntity;

@Entity
@Table(name = "UN_USER_ROLES", catalog = "ED")
public class UserRole extends AbstractEntity implements Serializable {

	/**
	 * @author Nagesh.Chauhan
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ACCOUNT_ID", nullable = false)
	@JsonBackReference
	private Accounts accounts;

	@Column(name = "ROLE", nullable = false, length = 45)
	private String role;

	public UserRole() {
	}

	public UserRole(Accounts accounts, String role) {
		super();
		this.accounts = accounts;
		this.role = role;
	}
	

	public Accounts getAccounts() {
		return accounts;
	}

	public void setAccounts(Accounts accounts) {
		this.accounts = accounts;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
