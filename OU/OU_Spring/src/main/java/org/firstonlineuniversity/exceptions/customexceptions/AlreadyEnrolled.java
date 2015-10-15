package org.firstonlineuniversity.exceptions.customexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Already enrolled !")
public class AlreadyEnrolled extends RuntimeException {
	private static final long serialVersionUID = 1L;
}
