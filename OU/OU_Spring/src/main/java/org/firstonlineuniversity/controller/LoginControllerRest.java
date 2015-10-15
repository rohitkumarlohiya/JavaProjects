package org.firstonlineuniversity.controller;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.firstonlineuniversity.email.api.EmailAPI;
import org.firstonlineuniversity.email.api.MailBodyTempletes;
import org.firstonlineuniversity.exceptions.customexceptions.EmptyFormElementsException;
import org.firstonlineuniversity.exceptions.customexceptions.EntityNotFoundException;
import org.firstonlineuniversity.exceptions.customexceptions.InvalidConfirmationCode;
import org.firstonlineuniversity.exceptions.customexceptions.SomethingWentWrongException;
import org.firstonlineuniversity.exceptions.customexceptions.UserAlreadyExists;
import org.firstonlineuniversity.formvalidation.rest.Errors;
import org.firstonlineuniversity.formvalidation.rest.SignupFormValidation;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.login.Accounts;
import org.firstonlineuniversity.models.login.UserRole;
import org.firstonlineuniversity.models.status.Status;
import org.firstonlineuniversity.service.login.LoginServices;
import org.firstonlineuniversity.services.CustomServices;
import org.firstonlineuniversity.services.DataServices;
import org.firstonlineuniversity.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.hibernate.exception.ConstraintViolationException;

@Controller
@RequestMapping("api/login")
@PropertySource("classpath:message.properties")
public class LoginControllerRest {

	@Autowired
	Utils utils;

	@Autowired
	Environment env;

	@Autowired
	DataServices<AbstractEntity> dataServices;

	@Autowired
	CustomServices customServices;

	@Autowired
	SignupFormValidation signupFormValidation;

	@Autowired
	@Qualifier("authenticationManager")
	AuthenticationManager authenticationManager;

	@Autowired
	@Qualifier("mailService")
	EmailAPI mailService;

	@Autowired
	LoginServices loginService;

	@Autowired
	MailBodyTempletes mailBodyTempletes;

	static final Logger logger = Logger.getLogger(LoginControllerRest.class);

	/*
	 * need to handle email uniqueness properly and more optimization
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status addEmployee(@RequestBody Accounts accounts) {

		if (accounts == null) {
			throw new EmptyFormElementsException();
		}

		try {
			Date date = new Date();
			logger.info("Created Date: " + date);
			accounts.setCD(date);
		} catch (Exception e) {
			logger.info("Not able to get date: " + e);
		}

		// need a little optimization
		List<Errors> errorsList = signupFormValidation.validateForm(accounts);
		if (!signupFormValidation.validateForm(accounts).isEmpty()) {
			return new Status(HttpStatus.NOT_ACCEPTABLE.toString(),
					env.getProperty("exception.validationfailed"), errorsList);

		}

		accounts.setPassword(utils.getHashPassword(accounts.getPassword()));
		accounts.setActivated(false);

		String conformString = utils.confirmationString();
		accounts.setConfirmation(conformString);
		try {
			dataServices.addUser(accounts, new UserRole(accounts, "ROLE_USER"));
		} catch (ConstraintViolationException e) {
			e.printStackTrace();
			throw new UserAlreadyExists();
		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException();
		}

		// confirmation mail
		String[] to = { accounts.getAccountEmail() };
		String bodyContent = mailBodyTempletes
				.getEmailTemplete("email_confirmation")
				.replace("USER_NAME", accounts.getAccountEmail())
				.replace(
						"VERIFICATION_LINK",
						"http://localhost:8080/OU_Spring/api/login/emailconfirm/"
								+ accounts.getAccountEmail() + "/"
								+ conformString);

		mailService.sendMail(env.getProperty("admin.email"), to,
				env.getProperty("register.subject"), bodyContent);

		return new Status(HttpStatus.ACCEPTED.toString(),
				env.getProperty("register.success"));

	}

	@RequestMapping("emailconfirm/{email}/{code}")
	@ResponseBody
	public Status verifyEmail(@PathVariable String email,
			@PathVariable String code) {

		Accounts accounts = null;
		try {
			accounts = dataServices.findByUserName(email);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException();
		}

		if (accounts == null) {
			throw new EntityNotFoundException();
		}

		try {
			Date date = new Date();
			logger.info("Created Date: " + date);
			accounts.setUD(date);
		} catch (Exception e) {
			logger.info("Not able to get date: " + e);
		}

		if (!accounts.getConfirmation().equalsIgnoreCase(code))
			throw new InvalidConfirmationCode();

		accounts.setActivated(true);
		try {
			dataServices.activateUser(accounts);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException();
		}

		return new Status(HttpStatus.ACCEPTED.toString(),
				env.getProperty("account.activated"));

	}

	@RequestMapping("forgetpassword")
	@ResponseBody
	public Status forgetPassword(@RequestParam String email) {

		if (email == null)
			throw new SomethingWentWrongException();

		email = email.trim();

		/*
		 * UserDetails userDetails =
		 * userDetailsService.loadUserByUsername(email); userDetails.
		 * 
		 * CustomUser customUser = (CustomUser)
		 * SecurityContextHolder.getContext()
		 * .getAuthentication().getPrincipal();
		 * 
		 * Accounts accounts = null; try { accounts = (Accounts)
		 * dataServices.getEntity(customUser.getId(), Accounts.class); } catch
		 * (Exception e1) { e1.printStackTrace(); }
		 */
		String conformString = utils.confirmationString();

		try {
			dataServices.updateConfirmation(email, conformString);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException();
		}

		// sending password change mail
		String[] to = { email };
		String bodyContent = mailBodyTempletes
				.getEmailTemplete("forget_password")
				.replace("USER_NAME", email)
				.replace(
						"VERIFICATION_LINK",
						"http://localhost:8080/OU_Spring/api/login/newpassword/"
								+ email + "/" + conformString);

		mailService.sendMail(env.getProperty("admin.email"), to,
				env.getProperty("passwordchange.subject"), bodyContent);

		return new Status(HttpStatus.ACCEPTED.toString(),
				env.getProperty("passwordchange.success"));
	}

	@RequestMapping("newpassword/{email}/{code}/{newPassword}")
	@ResponseBody
	public Status newPassword(@PathVariable String email,
			@PathVariable String code, @PathVariable String newPassword) {
		
		

		Accounts accounts = null;
		try {
			accounts = (Accounts) dataServices.findByUserName(email);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException();
		}

		if (accounts == null) {
			throw new EntityNotFoundException();
		}

		String hashedPassword = utils.getHashPassword(newPassword);

		try {
			Date date = new Date();
			logger.info("Created Date: " + date);
			accounts.setUD(date);
		} catch (Exception e) {
			logger.info("Not able to get date: " + e);
		}

		if (!accounts.getConfirmation().equalsIgnoreCase(code))
			throw new InvalidConfirmationCode();

		accounts.setPassword(hashedPassword);
		try {
			dataServices.updateUserEntity(accounts);
		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException();
		}

		return new Status(HttpStatus.ACCEPTED.toString(),
				env.getProperty("password.changed"));
	}
}
