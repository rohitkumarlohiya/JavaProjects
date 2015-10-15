package org.firstonlineuniversity.exceptions.customexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_GATEWAY, reason = "Not refundable")
@Component
public class NotRefundableException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
