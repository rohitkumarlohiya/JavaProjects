package org.firstonlineuniversity.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.firstonlineuniversity.domains.auth.custom.CustomUser;
import org.firstonlineuniversity.domains.custom.CustomAccounts;
import org.firstonlineuniversity.domains.custom.CustomProfile;
import org.firstonlineuniversity.email.api.EmailAPI;
import org.firstonlineuniversity.email.api.MailBodyTempletes;
import org.firstonlineuniversity.exceptions.customexceptions.EmptyFormElementsException;
import org.firstonlineuniversity.exceptions.customexceptions.EntityNotFoundException;
import org.firstonlineuniversity.exceptions.customexceptions.InvalidConfirmationCode;
import org.firstonlineuniversity.exceptions.customexceptions.SomethingWentWrongException;
import org.firstonlineuniversity.formvalidation.rest.EditProfileFormValidation;
import org.firstonlineuniversity.formvalidation.rest.Errors;
import org.firstonlineuniversity.formvalidation.rest.SignupFormValidation;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.enrollements.CoursesEnrollements;
import org.firstonlineuniversity.models.login.Accounts;
import org.firstonlineuniversity.models.login.UserRole;
import org.firstonlineuniversity.models.profiles.Profiles;
import org.firstonlineuniversity.models.status.Status;
import org.firstonlineuniversity.service.login.LoginServices;
import org.firstonlineuniversity.services.CustomServices;
import org.firstonlineuniversity.services.DataServices;
import org.firstonlineuniversity.utils.Utils;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("api/users")
@PropertySource("classpath:message.properties")
public class UserControllerRest {

	@Autowired
	Utils utils;

	@Autowired
	Environment env;

	@Autowired
	@Qualifier("mailService")
	EmailAPI mailService;

	@Autowired
	DataServices<AbstractEntity> dataServices;

	@Autowired
	CustomServices customService;

	@Autowired
	MailBodyTempletes mailBodyTempletes;

	@Autowired
	SignupFormValidation signupFormValidation;

	@Autowired
	EditProfileFormValidation editProfileFormValidation;

	@Autowired
	LoginServices loginServices;

	static final Logger logger = Logger.getLogger(UserControllerRest.class);

	/*
	 * Get User Login Details
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody CustomAccounts getUser() throws HibernateException,
			Exception {
		Accounts accounts = null;
		CustomAccounts customAccounts = null;

		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();

		String username = userDetails.getUsername();

		logger.info("Username: " + username);
		accounts = (Accounts) dataServices.findByUserName(username);

		if (accounts == null) {
			throw new EntityNotFoundException();
		}

		Set<String> roles = new HashSet<String>();
		for (UserRole role : accounts.getUserRole()) {
			roles.add(role.getRole());
		}

		customAccounts = new CustomAccounts(accounts.getAccountEmail(),
				accounts.getAccountPhone(), accounts.getPassword(),
				accounts.getFirstName(), accounts.getLastName(),
				accounts.getAccessLevel(), accounts.isActivated(), roles);

		return customAccounts;
	}

	/*
	 * Update User
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status editUser(@RequestBody Accounts accounts)
			throws HibernateException, Exception {
		if (accounts == null) {
			throw new EmptyFormElementsException();
		}

		// need a little optimization
		List<Errors> errorsList = signupFormValidation.validateForm(accounts);
		if (!errorsList.isEmpty()) {
			return new Status(HttpStatus.NOT_ACCEPTABLE.toString(),
					env.getProperty("exception.validationfailed"), errorsList);

		}
		dataServices.updateUserEntity(accounts);

		return new Status(HttpStatus.OK.toString(),
				env.getProperty("status.successupdate"));
	}

	/*
	 * Get User Profile
	 */
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public @ResponseBody CustomProfile getUserProfile()
			throws HibernateException, Exception {
		Accounts accounts = null;
		Profiles profiles = null;
		CustomProfile customProfile = null;

		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();
		accounts = (Accounts) dataServices.findByUserName(username);
		if (accounts == null)
			throw new EntityNotFoundException();

		logger.info("accounts id: " + accounts.getId());
		profiles = dataServices.getProfileByAccountId(accounts);

		try {

			CustomUser customUser = (CustomUser) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();

			System.out.println("ID: ....> " + customUser.getId());

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (profiles == null)
			throw new EntityNotFoundException();

		customProfile = new CustomProfile(profiles.getFirstName(),
				profiles.getLastName(), profiles.getMiddleInitial(),
				profiles.getSex(), profiles.getBdMonth(), profiles.getBdDay(),
				profiles.getBdYear(), profiles.getEmail(),
				profiles.getAlternateEmail(), profiles.getPhoneCell(),
				profiles.getPhoneCell(), profiles.getPhoneOffice(),
				profiles.getBiography(), profiles.getPhotoLink(),
				profiles.getLinkTwitter(), profiles.getLinkFacebok(),
				profiles.getLinkGooglePlus(), profiles.getLinkLinkedin(),
				profiles.getLinkWebsite(), profiles.getLanguage(),
				profiles.getTimeZone(), profiles.getDateFormat(),
				profiles.getVideoPlayers(), profiles.getProfileAccess(),
				profiles.getHighestDegree(), profiles.getHobbies());

		return customProfile;
	}

	/*
	 * Update User Profile
	 */
	@RequestMapping(value = "/profile", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status addEmployee(@RequestBody Profiles profiles)
			throws HibernateException, Exception {
		Accounts accounts = null;
		Profiles existingProfile = null;
		if (profiles == null) {
			throw new EmptyFormElementsException();
		}

		// need a little optimization
		List<Errors> errorsList = editProfileFormValidation
				.validateForm(profiles);
		if (!editProfileFormValidation.validateForm(profiles).isEmpty()) {
			return new Status(HttpStatus.NOT_ACCEPTABLE.toString(),
					env.getProperty("exception.validationfailed"), errorsList);

		}

		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();

		String username = userDetails.getUsername();

		accounts = (Accounts) dataServices.findByUserName(username);

		profiles.setEmail(accounts.getAccountEmail());
		profiles.setAccounts(accounts);

		existingProfile = dataServices.getProfileByAccountId(accounts);

		if (existingProfile == null) {
			throw new SomethingWentWrongException();
		}

		profiles.setId(existingProfile.getId());

		dataServices.updateOrg(profiles);

		return new Status(HttpStatus.ACCEPTED.toString(),
				env.getProperty("profile.updated"));

	}

	/*
	 * Update User Profile
	 */
	@RequestMapping(value = "/my-courses", method = RequestMethod.GET)
	public @ResponseBody List<CoursesEnrollements> myEnrollerCourses()
			throws HibernateException, Exception {
		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		List<CoursesEnrollements> coursesEnrollements = customService
				.getEnrolledCoursesByUserId(customUser.getId());
		if (coursesEnrollements == null || coursesEnrollements.size() == 0)
			throw new EntityNotFoundException();

		List<CoursesEnrollements> coursesEnrollementsNew = new ArrayList<CoursesEnrollements>();
		for (CoursesEnrollements enrollementsDB : coursesEnrollements) {
			CoursesEnrollements enrollements = new CoursesEnrollements(
					enrollementsDB.getId(), enrollementsDB.getCourseId(),
					enrollementsDB.getSessionId(), enrollementsDB.getGroupId(),
					enrollementsDB.getProgramId(), enrollementsDB.isPaid(),
					enrollementsDB.getCost(), enrollementsDB.getCurrency(),
					enrollementsDB.isEthicPolicyAccepted(),
					enrollementsDB.isEthicPolicyAccepted());
			coursesEnrollementsNew.add(enrollements);
		}

		return coursesEnrollementsNew;

	}

	@RequestMapping("/roles")
	@ResponseBody
	public Set<String> getRoles() {

		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		Set<String> rolesSet = new HashSet<String>();
		List<UserRole> rolesList = customService
				.getUserRole(customUser.getId());
		for (UserRole role : rolesList) {
			rolesSet.add(role.getRole());
		}
		return rolesSet;
	}

	@RequestMapping("/reset-password/{newPassword}")
	@ResponseBody
	public Status resetpassowrd(@PathVariable String newPassword) {

		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		Accounts accounts = null;
		try {
			accounts = (Accounts) dataServices.findByUserName(customUser
					.getUsername());
			/*
			 * if (!accounts.getPassword()
			 * .equals(utils.getHashPassword(oldPassword))) return new
			 * Status(HttpStatus.ACCEPTED.toString(),
			 * "Old password not correct");
			 */

			accounts.setPassword(utils.getHashPassword(newPassword));
		} catch (Exception e) {
			e.printStackTrace();
			throw new SomethingWentWrongException();
		}

		try {
			Date date = new Date();
			logger.info("Created Date: " + date);
			accounts.setUD(date);
		} catch (Exception e) {
			logger.info("Not able to get date: " + e);
		}
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
