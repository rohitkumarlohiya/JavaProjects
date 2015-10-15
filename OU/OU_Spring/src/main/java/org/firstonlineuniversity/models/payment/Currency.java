package org.firstonlineuniversity.models.payment;

public enum Currency {
	usd;

	public static Currency getCurrency(String currency) {
		if (currency.endsWith("usd"))
			return usd;
		return null;
	}
}
