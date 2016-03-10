package com.lohiya;

public class Account {
	private int accountNumber;
	private String holderName;
	//private String holderLastName;

	public Account(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	/*@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountNumber;
		result = prime * result
				+ ((holderName == null) ? 0 : holderName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountNumber != other.accountNumber)
			return false;
		if (holderName == null) {
			if (other.holderName != null)
				return false;
		} else if (!holderName.equals(other.holderName))
			return false;
		return true;
	}*/

	// Depends only on account number
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountNumber;
		return result;
	}

	// Compare only account numbers
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountNumber != other.accountNumber)
			return false;
		return true;
	}
	
	

}