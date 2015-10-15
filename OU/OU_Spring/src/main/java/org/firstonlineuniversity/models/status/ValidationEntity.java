package org.firstonlineuniversity.models.status;

public class ValidationEntity {

	private String inputString;
	private boolean alreadyExists;
	
	public ValidationEntity() {
		super();
	}

	public ValidationEntity(String inputString, boolean alreadyExists) {
		super();
		this.inputString = inputString;
		this.alreadyExists = alreadyExists;
	}

	public String getInputString() {
		return inputString;
	}

	public void setInputString(String inputString) {
		this.inputString = inputString;
	}

	public boolean isAlreadyExists() {
		return alreadyExists;
	}

	public void setAlreadyExists(boolean alreadyExists) {
		this.alreadyExists = alreadyExists;
	}
}
