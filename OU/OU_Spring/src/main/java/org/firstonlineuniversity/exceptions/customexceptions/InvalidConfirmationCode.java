package org.firstonlineuniversity.exceptions.customexceptions;

public class InvalidConfirmationCode extends RuntimeException {

	private String errorString;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getErrorString() {
		return errorString;
	}

	public void setErrorString(String errorString) {
		this.errorString = errorString;
	}

	@Override
	public String toString() {
		return "Exception : " + errorString;
	}

}
