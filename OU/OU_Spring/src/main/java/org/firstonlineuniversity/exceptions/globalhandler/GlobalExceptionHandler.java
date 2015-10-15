package org.firstonlineuniversity.exceptions.globalhandler;

import org.firstonlineuniversity.exceptions.customexceptions.NotRefundableException;
import org.firstonlineuniversity.exceptions.customexceptions.AlreadyEnrolled;
import org.firstonlineuniversity.exceptions.customexceptions.BadRequestException;
import org.firstonlineuniversity.exceptions.customexceptions.EmptyFormElementsException;
import org.firstonlineuniversity.exceptions.customexceptions.EntityNotFoundException;
import org.firstonlineuniversity.exceptions.customexceptions.InvalidConfirmationCode;
import org.firstonlineuniversity.exceptions.customexceptions.ListNotFoundException;
import org.firstonlineuniversity.exceptions.customexceptions.NotAProvider;
import org.firstonlineuniversity.exceptions.customexceptions.NotAuthorisedException;
import org.firstonlineuniversity.exceptions.customexceptions.SomethingWentWrongException;
import org.firstonlineuniversity.exceptions.customexceptions.TokenRemovedException;
import org.firstonlineuniversity.exceptions.customexceptions.UserAlreadyExists;
import org.firstonlineuniversity.models.status.Status;
import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;

import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;

@ControllerAdvice
@Component
@PropertySource("classpath:message.properties")
public class GlobalExceptionHandler {
	@Autowired
	Environment env;

	@ResponseStatus(HttpStatus.BAD_GATEWAY)
	@ExceptionHandler(NotRefundableException.class)
	@ResponseBody
	public Status notRefundable() {
		return new Status(HttpStatus.BAD_GATEWAY.toString(),
				env.getProperty("refund.refused"));
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(ListNotFoundException.class)
	@ResponseBody
	public Status listNotFound() {
		return new Status(HttpStatus.NOT_FOUND.toString(),
				env.getProperty("exception.listnotfound"));
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(org.firstonlineuniversity.exceptions.customexceptions.InvalidPaymentException.class)
	@ResponseBody
	public Status paymentException() {
		return new Status(HttpStatus.NOT_FOUND.toString(),
				env.getProperty("exception.listnotfound"));
	}

	@ResponseStatus(HttpStatus.ACCEPTED)
	@ExceptionHandler(TokenRemovedException.class)
	@ResponseBody
	public Status tokenRemoved() {
		return new Status(HttpStatus.ACCEPTED.toString(),
				env.getProperty("exception.tokenremoved"));
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseBody
	public Status entityNotFound() {
		return new Status(HttpStatus.NOT_FOUND.toString(),
				env.getProperty("exception.entitynotfound"));
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(EmptyFormElementsException.class)
	@ResponseBody
	public Status formValuesEmpty() {
		return new Status(HttpStatus.BAD_REQUEST.toString(),
				env.getProperty("exception.badrequest"));
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(SomethingWentWrongException.class)
	@ResponseBody
	public Status somethingWentWrong() {
		return new Status(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
				env.getProperty("exception.internalserver"));
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(UserAlreadyExists.class)
	@ResponseBody
	public Status userAlreadyExists() {
		return new Status(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
				env.getProperty("exception.alreadyexists"));
	}

	@ResponseStatus(HttpStatus.BAD_GATEWAY)
	@ExceptionHandler(AlreadyEnrolled.class)
	@ResponseBody
	public Status alreadyEnrolled(AlreadyEnrolled e) {
		return new Status(HttpStatus.BAD_GATEWAY.toString(),
				env.getProperty("exception.alreadyenrolled"));
	}

	@ResponseStatus(HttpStatus.BAD_GATEWAY)
	@ExceptionHandler(BadRequestException.class)
	@ResponseBody
	public Status badRequest(BadRequestException e) {
		return new Status(HttpStatus.BAD_REQUEST.toString(), e.toString());
	}

	@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
	@ExceptionHandler(HibernateException.class)
	@ResponseBody
	public Status hibernateException(HibernateException e) {
		e.printStackTrace();
		return new Status(HttpStatus.EXPECTATION_FAILED.toString(),
				e.toString());
	}

	@ResponseStatus(HttpStatus.SEE_OTHER)
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Status exception(Exception e) {
		e.printStackTrace();
		return new Status(HttpStatus.SEE_OTHER.toString(), e.toString());
	}

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(NotAuthorisedException.class)
	@ResponseBody
	public Status exception(NotAuthorisedException e) {
		e.printStackTrace();
		return new Status(HttpStatus.UNAUTHORIZED.toString(), e.toString());
	}

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(InvalidConfirmationCode.class)
	@ResponseBody
	public Status exception(InvalidConfirmationCode e) {
		e.printStackTrace();
		return new Status(HttpStatus.UNAUTHORIZED.toString(), e.toString());
	}

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(InternalAuthenticationServiceException.class)
	@ResponseBody
	public Status exception(InternalAuthenticationServiceException e) {
		e.printStackTrace();
		return new Status(HttpStatus.UNAUTHORIZED.toString(),
				env.getProperty("username.incorrect"));
	}

	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(org.firstonlineuniversity.exceptions.customexceptions.ConstraintViolationExceptionCustom.class)
	@ResponseBody
	public Status exception(
			org.firstonlineuniversity.exceptions.customexceptions.ConstraintViolationExceptionCustom e) {
		e.printStackTrace();
		return new Status(HttpStatus.CONFLICT.toString(),
				env.getProperty("constraint.duplicate.violation"));
	}

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseBody
	public Status exception(ConstraintViolationException e) {

		e.printStackTrace();

		if (e.getErrorCode() == 1062)
			return new Status(HttpStatus.UNAUTHORIZED.toString(),
					env.getProperty("constraint.duplicate.violation"));
		return new Status(HttpStatus.UNAUTHORIZED.toString(),
				env.getProperty("constraint.violation"));
	}

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(NotAProvider.class)
	@ResponseBody
	public Status notAProviderException(NotAProvider e) {
		e.printStackTrace();
		return new Status(HttpStatus.UNAUTHORIZED.toString(),
				env.getProperty("provider.unautorised"));
	}

	// stripe exception

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(AuthenticationException.class)
	@ResponseBody
	public Status authenticationException(AuthenticationException e) {
		e.printStackTrace();
		return new Status(HttpStatus.NOT_FOUND.toString(),
				env.getProperty("exception.stripe.authentication"));
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InvalidRequestException.class)
	@ResponseBody
	public Status invalidRequestException(InvalidRequestException e) {
		e.printStackTrace();
		return new Status(HttpStatus.NOT_FOUND.toString(),
				env.getProperty("exception.stripe.invalidrequest"));
	}

	@ResponseStatus(HttpStatus.BAD_GATEWAY)
	@ExceptionHandler(APIConnectionException.class)
	@ResponseBody
	public Status aPIConnectionException(APIConnectionException e) {
		e.printStackTrace();
		return new Status(HttpStatus.NOT_FOUND.toString(),
				env.getProperty("exception.stripe.apiconnectionexception"));
	}

	@ResponseStatus(HttpStatus.CHECKPOINT)
	@ExceptionHandler(CardException.class)
	@ResponseBody
	public Status cardException(CardException e) {
		e.printStackTrace();
		return new Status(HttpStatus.NOT_FOUND.toString(),
				env.getProperty("exception.stripe.cardexception"));
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(APIException.class)
	@ResponseBody
	public Status aPIException(APIException e) {
		e.printStackTrace();
		return new Status(HttpStatus.NOT_FOUND.toString(),
				env.getProperty("exception.stripe.apiexception"));
	}

}
