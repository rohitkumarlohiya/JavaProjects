package org.firstonlineuniversity.service.login;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.firstonlineuniversity.dao.DataDao;
import org.firstonlineuniversity.domains.auth.custom.CustomUser;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.login.UserRole;
import org.firstonlineuniversity.models.login.Accounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("loginService")
public class LoginServiceImpl implements UserDetailsService, LoginServices {

	@Autowired
	DataDao<AbstractEntity> dataDao;

	static final Logger logger = Logger.getLogger(LoginServiceImpl.class);

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		Accounts accounts = null;
		try {
			accounts = dataDao.findByUserName(username);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (accounts.getUserRole() != null) {
			logger.info(accounts.getUserRole());
		} else {
			logger.info("info null");
		}

		List<GrantedAuthority> authorities = buildUserAuthority(accounts
				.getUserRole());

		return buildUserForAuthentication(accounts, authorities);
	}

	private CustomUser buildUserForAuthentication(Accounts accounts,
			List<GrantedAuthority> authorities) {

		logger.info("Username: " + accounts.getAccountEmail());
		logger.info("Password: " + accounts.getPassword());
		logger.info("Is Activated: " + accounts.isActivated());
		logger.info("Role: " + authorities);
		return new CustomUser(accounts.getId(), accounts.getAccountEmail(),
				accounts.getPassword(), accounts.isActivated(), true, true,
				true, authorities,accounts.getCoursesEnrollements());
	}

	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (UserRole userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(
				setAuths);

		return Result;
	}

	@Override
	public boolean addEntity(Accounts accounts) throws Exception {
		return dataDao.addEntity(accounts);
	}

}
