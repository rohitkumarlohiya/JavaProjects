package com.globallogic.service;

import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

	@Override
	public boolean authenticate(String username, String password) {
		return username.equals("rohit.lohiya@globallogic.com") && password.equals("lohiya@1988");
	}
	
	/*public static void main(String[] args) {
		System.out.println(new LoginService().authenticate("rohit.lohiya@globallogic.com", "lohiya@1988"));
	}*/

}
