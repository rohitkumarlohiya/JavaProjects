package org.firstonlineuniversity.exceptions.customexceptions;

public class InvalidPaymentException extends RuntimeException {

	private String errorString;

	/**
	 * @author Nagesh.Chauhan
	 */
	private static final long serialVersionUID = 1L;

	public InvalidPaymentException() {
		super();
	}

	public InvalidPaymentException(String errorString) {
		this.errorString = errorString;
	}

	@Override
	public String toString() {
		return "Exception : " + errorString;
	}

}
