package com.estel.dao;

import com.estel.entity.Country;

import java.util.List;

public interface CountryDAO extends GenericDAO<Country, Long> {

	public Country addCountry(Country Country);
	public Country getCountryById(Long Id);
	public List<Country> listCountrys();
	public void updateCountry(Country Country);
}
