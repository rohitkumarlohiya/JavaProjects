package com.estel.service;

import com.estel.dao.GenericDAOImpl;
import com.estel.dao.MerchantDAO;
import com.estel.dao.UserDAO;
import com.estel.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends GenericDAOImpl<User, Long> implements
        UserService {

	@Autowired
	private UserDAO UserDAO;

    @Autowired
    private MerchantDAO merchantDAO;


	public User addUser(User User) {
		return UserDAO.addUser(User);
	}


	public User getUserById(Long Id) {
		return UserDAO.getUserById(Id);
	}


	public List<User> listUsers() {
		return UserDAO.listUsers();
	}

	public void updateUser(User User) {
		UserDAO.updateUser(User);
		
	}

	public User verifyUserLogin(String username, String password) {
		return UserDAO.verifyUserLogin(username, password);
	}


    public User getUserByAgentId(Long agentId) {
        return UserDAO.getUserByAgentId(agentId);
    }


    public User getUserByEmailId(String emailId)
    {
        return UserDAO.getUserByEmailId(emailId);
    }


    public User addUserByEmailId(String loginName,String password,String firstName,String SurName,Long countryId,String emailId,Long statusId,Long agentId)
    {
        return  UserDAO.addUserByEmailId(loginName, password, firstName, SurName, countryId, emailId, statusId,agentId);
    }

	
}
