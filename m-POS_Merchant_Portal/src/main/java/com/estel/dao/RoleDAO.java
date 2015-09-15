package com.estel.dao;

import com.estel.entity.Role;

import java.util.List;

public interface RoleDAO extends GenericDAO<Role, Long> {

	public Role addRole(Role Role);
	public Role getRoleById(Long Id);
	public List<Role> listRoles();
	public void updateRole(Role Role);
}
