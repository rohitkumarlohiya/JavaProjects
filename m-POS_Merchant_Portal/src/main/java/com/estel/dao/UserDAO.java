package com.estel.dao;

import com.estel.entity.User;

import java.util.List;

public interface UserDAO extends GenericDAO<User, Long> {

	public User addUser(User User);
	public User getUserById(Long Id);
	public List<User> listUsers();
	public void updateUser(User User);


    public User getUserByAgentId(Long agentId);
    public User getUserByEmailId(String emailId);
	public User verifyUserLogin(String username, String password);
    public User addUserByEmailId(String loginName,String password,String firstName,String surName,Long countryId,String emailId,Long statusId,Long agentId);
}
