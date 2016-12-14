package com.hascode.tutorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private AutoUserRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AutoUser autoUser = repo.findByUsername(username);
		 if(autoUser == null){
			 throw new UsernameNotFoundException(username);
		 }
		 
		 return autoUser;
		
//		System.out.println("tewsting");
//		return new User("rohit", "lohiya", AuthorityUtils.createAuthorityList("ROLE_USER"));
	}
}
