package com.estel.service;

import com.estel.dao.GenericDAOImpl;
import com.estel.dao.RoleDAO;
import com.estel.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl extends GenericDAOImpl<Role, Long> implements
        RoleService {

	@Autowired
	private RoleDAO RoleDAO;


	public Role addRole(Role Role) {
		return RoleDAO.addRole(Role);
	}


	public Role getRoleById(Long Id) {
		return RoleDAO.getRoleById(Id);
	}


	public List<Role> listRoles() {
		return RoleDAO.listRoles();
	}


	public void updateRole(Role Role) {
		RoleDAO.updateRole(Role);
		
	}

	
}
