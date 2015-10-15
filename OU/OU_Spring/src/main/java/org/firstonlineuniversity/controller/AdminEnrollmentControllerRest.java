package org.firstonlineuniversity.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.firstonlineuniversity.domains.auth.custom.CustomUser;
import org.firstonlineuniversity.exceptions.customexceptions.AlreadyEnrolled;
import org.firstonlineuniversity.exceptions.customexceptions.EmptyFormElementsException;
import org.firstonlineuniversity.exceptions.customexceptions.EntityNotFoundException;
import org.firstonlineuniversity.exceptions.customexceptions.NotRefundableException;
import org.firstonlineuniversity.models.commons.AbstractEntity;
import org.firstonlineuniversity.models.enrollements.CoursesEnrollements;
import org.firstonlineuniversity.models.helper.CourseEnrollementsWrapper;
import org.firstonlineuniversity.models.login.Accounts;
import org.firstonlineuniversity.models.payment.Currency;
import org.firstonlineuniversity.models.payment.PaymentTransactions;
import org.firstonlineuniversity.models.payment.TransactionType;
import org.firstonlineuniversity.models.status.Status;
import org.firstonlineuniversity.models.views.CourseCatalogView;
import org.firstonlineuniversity.services.CustomServices;
import org.firstonlineuniversity.services.DataServices;
import org.firstonlineuniversity.utils.StripeUtils;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;
import com.stripe.model.Token;

@Controller
@RequestMapping("api/users/enrollment")
@PropertySource("classpath:message.properties")
public class AdminEnrollmentControllerRest {
	@Autowired
	Environment env;

	@Autowired
	CustomServices customServices;

	@Autowired
	DataServices<AbstractEntity> dataServices;

	@Autowired
	StripeUtils stripeUtils;

	/*
	 * Add Enrollments
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Status add(@RequestBody CoursesEnrollements coursesEnrollements)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException, Exception {
		if (coursesEnrollements == null)
			throw new EntityNotFoundException();

		Token token = null;

		token = stripeUtils.retrieveToken(coursesEnrollements
				.getAuthorization());

		Charge charge = stripeUtils.createCharge(coursesEnrollements.getCost(),
				coursesEnrollements.getCurrency(), token.getId(), "empty");

		stripeUtils.retriveCharge(charge.getId());

		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		Set<CoursesEnrollements> coursesEnrollementsSet = customUser
				.getCoursesEnrollements();

		for (CoursesEnrollements ce : coursesEnrollementsSet) {
			if (ce.getCourseId() == coursesEnrollements.getCourseId())
				throw new AlreadyEnrolled();
		}

		try {
			coursesEnrollements.setCD(new Date());
			coursesEnrollements.setUD(new Date());

			coursesEnrollements.setUB(customUser.getId());
			coursesEnrollements.setCB(customUser.getId());

			coursesEnrollements.setAccounts(new Accounts(customUser.getId()));
			coursesEnrollementsSet.add(coursesEnrollements);
		} catch (Exception e) {
			e.printStackTrace();
		}

		dataServices.addEntity(coursesEnrollements);
		PaymentTransactions paymentTransactions = new PaymentTransactions(
				TransactionType.PAYMENT, coursesEnrollements.getCourseId(),
				customUser.getId(), charge.getId(),
				coursesEnrollements.getCost(),
				Currency.getCurrency(coursesEnrollements.getCurrency()), "",
				coursesEnrollements.getId(), PaymentTransactions.Status.SUCCESS);
		paymentTransactions.setCD(new Date());
		paymentTransactions.setUD(new Date());

		paymentTransactions.setUB(customUser.getId());
		paymentTransactions.setCB(customUser.getId());
		paymentTransactions.setEnabled(true);
		dataServices.addEntity(paymentTransactions);
		return new Status(HttpStatus.ACCEPTED.toString(),
				env.getProperty("enrollement.add"));

	}

	/*
	 * Get Enrollment Courses
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public List<CourseEnrollementsWrapper> list() throws HibernateException,
			Exception {

		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		Set<CoursesEnrollements> CeDb = customUser.getCoursesEnrollements();
		List<CourseEnrollementsWrapper> c = new ArrayList<CourseEnrollementsWrapper>();

		if (CeDb == null || CeDb.size() == 0)
			return c;

		for (CoursesEnrollements ce : CeDb) {
			CoursesEnrollements enrollements = new CoursesEnrollements(
					ce.getId(), ce.getCourseId(), ce.getSessionId(),
					ce.getGroupId(), ce.getProgramId(), ce.isPaid(),
					ce.getCost(), ce.getCurrency(), ce.isEthicPolicyAccepted(),
					ce.isLegalTermsAccepted());
			CourseEnrollementsWrapper cer = new CourseEnrollementsWrapper();
			cer.setCoursesEnrollements(enrollements);

			try {
				CourseCatalogView courseCatalogView = customServices
						.getCourseCatalogView(ce.getCourseId());
				cer.setCourseCatalogView(courseCatalogView);
			} catch (Exception e) {
				e.printStackTrace();
			}
			c.add(cer);
		}
		return c;
	}

	/*
	 * Edit Enrollments
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getUser(@PathVariable("id") long id) {
		AbstractEntity abstractEntity = null;
		try {
			abstractEntity = (AbstractEntity) customServices.getEdutableData(
					"CoursesEnrollements", id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (abstractEntity == null)
			throw new EntityNotFoundException();
		CoursesEnrollements coursesEnrollementsDB = (CoursesEnrollements) abstractEntity;
		CoursesEnrollements coursesEnrollements = new CoursesEnrollements(
				coursesEnrollementsDB.getId(),
				coursesEnrollementsDB.getCourseId(),
				coursesEnrollementsDB.getSessionId(),
				coursesEnrollementsDB.getGroupId(),
				coursesEnrollementsDB.getProgramId(),
				coursesEnrollementsDB.isPaid(),
				coursesEnrollementsDB.getCost(),
				coursesEnrollementsDB.getCurrency(),
				coursesEnrollementsDB.isEthicPolicyAccepted(),
				coursesEnrollementsDB.isLegalTermsAccepted());

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("accounts",
				String.valueOf(coursesEnrollementsDB.getAccounts().getId()));
		map.put("coursesEnrollements", coursesEnrollements);

		return map;
	}

	/*
	 * Update Enrollments
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status updateCourses(
			@RequestBody CoursesEnrollements coursesEnrollements)
			throws HibernateException, Exception {
		if (coursesEnrollements == null) {
			throw new EmptyFormElementsException();
		}

		try {
			coursesEnrollements.setUD(new Date());
			CustomUser customUser = (CustomUser) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			coursesEnrollements.setUB(customUser.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		dataServices.updateEntity(coursesEnrollements);

		return new Status(HttpStatus.OK.toString(),
				env.getProperty("enrollement.successupdate"));
	}

	/*
	 * Delete Enrollments
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Status delete(@PathVariable long id) throws HibernateException,
			Exception {
		if (id == 0)
			throw new EntityNotFoundException();

		dataServices.deleteEntity(id, CoursesEnrollements.class);

		return new Status(HttpStatus.ACCEPTED.toString(),
				env.getProperty("enrollement.delete"));

	}

	/*
	 * Refund
	 */
	@RequestMapping(value = "/refund/{courseId}", method = RequestMethod.GET)
	@ResponseBody
	public Status refund(@PathVariable Long courseId)
			throws AuthenticationException, InvalidRequestException,
			APIConnectionException, CardException, APIException, Exception {

		CustomUser customUser = (CustomUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		Long accountId = customUser.getId();

		PaymentTransactions paymentTransactions = customServices
				.getTransaction(courseId, accountId);

		Set<CoursesEnrollements> coursesEnrollements = customUser
				.getCoursesEnrollements();
		CoursesEnrollements coursesEnrollementNow = null;
		boolean enrolled = false;
		for (CoursesEnrollements coursesEnrollement : coursesEnrollements) {
			if (coursesEnrollement.getAccounts().getId() == accountId
					&& coursesEnrollement.getCourseId() == courseId) {
				enrolled = true;
				coursesEnrollementNow = coursesEnrollement;
			}
		}

		if (paymentTransactions == null || !enrolled)
			throw new EntityNotFoundException();

		String chargeToken = paymentTransactions.getToken();
		int difference = (int) ((coursesEnrollementNow.getCD().getTime() - new Date()
				.getTime()) / (1000 * 60 * 60 * 24));
		if (difference < -7 || chargeToken == null)
			throw new NotRefundableException();

		if (chargeToken != null)
			stripeUtils.refund(chargeToken);

		return new Status("200", env.getProperty("refund.done"));
	}
}
