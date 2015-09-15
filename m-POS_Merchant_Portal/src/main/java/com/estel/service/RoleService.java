package com.estel.service;

import com.estel.entity.Role;

import java.util.List;


public interface RoleService {

	public Role addRole(Role Role);
	public Role getRoleById(Long Id);
	public List<Role> listRoles();
	public void updateRole(Role Role);
}
