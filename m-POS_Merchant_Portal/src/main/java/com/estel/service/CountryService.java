package com.estel.service;

import com.estel.entity.Country;

import java.util.List;


public interface CountryService {

	public Country addCountry(Country Country);
	public Country getCountryById(Long Id);
	public List<Country> listCountrys();
	public void updateCountry(Country Country);
}
