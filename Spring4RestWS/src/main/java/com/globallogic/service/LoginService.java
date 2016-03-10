package com.globallogic.service;

public interface LoginService {

	public abstract boolean authenticate(String username, String password);

}