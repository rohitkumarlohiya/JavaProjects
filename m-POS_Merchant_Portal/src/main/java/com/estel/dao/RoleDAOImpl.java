package com.estel.dao;

import com.estel.entity.Role;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class RoleDAOImpl extends GenericDAOImpl<Role, Long> implements RoleDAO {
	

	@Transactional
	public Role addRole(Role Role) {
		return (Role) this.read(this.create(Role));
	}


	@Transactional
	public Role getRoleById(Long Id) {
		return (Role) this.read(Id);
	}


	@SuppressWarnings("unchecked")
	@Transactional
	public List<Role> listRoles() {
		return getSession().createQuery("from Role").list();
	}


	@Transactional
	public void updateRole(Role Role) {
		getSession().update(Role);
		
	}

}
