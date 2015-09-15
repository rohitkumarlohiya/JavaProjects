package com.estel.dao;

import com.estel.entity.Merchant;
import com.estel.entity.User;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

@Repository
public class UserDAOImpl extends GenericDAOImpl<User, Long> implements UserDAO {
	
    @Autowired
    User user;

    @Autowired
    CountryDAO countryDAO;

    @Autowired
    StatusDAO statusDAO;

    @Autowired
    MerchantDAO merchantDAO;

    @Autowired
    RoleDAO roleDAO;

    @Autowired
    MessageSource messageSource;

	@Transactional
	public User addUser(User User) {
		return (User) this.read(this.create(User));
	}


	@Transactional
	public User getUserById(Long Id) {
		return (User) this.read(Id);
	}


	@SuppressWarnings("unchecked")
	@Transactional
	public List<User> listUsers() {
		return getSession().createQuery("from User").list();
	}


	@Transactional
	public void updateUser(User User) {
		getSession().update(User);
		
	}

    @SuppressWarnings("unchecked")
    @Transactional
    public User getUserByAgentId(Long agentId) {

        Merchant merchant = merchantDAO.getMerchantById(agentId);

        String sql = "from User where merchant=:merchant";
        Query query = getSession().createQuery(sql);
        query.setEntity("merchant", merchant);
        List<User> userList = query.list();
        if (userList.size() <= 0) {
            return null;
        }
        return userList.get(0);
    }

    @Transactional
    public User getUserByEmailId(String emailId)
    {
        String sql = "from User where userMailId=:emailId";
        Query query = getSession().createQuery(sql);
        query.setString("emailId", emailId);
        List<User> userList = query.list();
        if (userList.size() <= 0) {
            return null;
        }
        return userList.get(0);
    }

	@SuppressWarnings("unchecked")
	@Transactional
	public User verifyUserLogin(String username, String password) {

		String sql = "from User where userMailId=:username and userPassword=:password";
		Query query = getSession().createQuery(sql);
		query.setString("username", username);
		query.setString("password", password);
		List<User> userList = query.list();
		if (userList.size() <= 0) {
			return null;
		}
		return userList.get(0);

	}

    @Transactional
    public User addUserByEmailId(String loginName,String password,String firstName,String surName,Long countryId,String emailId,Long statusId,Long agentId)
    {
        //madatory field set
        /*user_login_name
        user_password
        user_password_expiry
        user_merchant_id
        user_role_id
        user_status_id
        user_ts*/

        String passwordExpiryDays = messageSource.getMessage("expiry.date.for.password",null,"default-expiry.date.for.password",null);
        if(Integer.parseInt(passwordExpiryDays) >= 90)
        {
            passwordExpiryDays = String.valueOf(90);
        }

        user.setUserLoginName(loginName);
        user.setUserPassword(password);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE , Integer.parseInt(passwordExpiryDays));
        user.setUserPasswordExpiry(new java.sql.Date(calendar.getTimeInMillis()));
        user.setMerchant(merchantDAO.getMerchantById(agentId));
        user.setRole(roleDAO.getRoleById(1L));
        user.setStatus(statusDAO.getStatusById(statusId));
        user.setUserTs(new java.sql.Date(System.currentTimeMillis()));
        //madatory field set

        user.setUserWrongAttemptCtr(0L);

        user.setUserFirstName(firstName);
        user.setUserLastName(surName);
        //user.setMbCountry(countryDAO.getMbCountryById(countryId));
        user.setUserMailId(emailId);

        return (User) this.read(this.create(user));


    }

}
