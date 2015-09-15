package com.estel.service;

import com.estel.dao.CountryDAO;
import com.estel.dao.GenericDAOImpl;
import com.estel.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl extends GenericDAOImpl<Country, Long> implements
        CountryService {

	@Autowired
	private CountryDAO CountryDAO;


	public Country addCountry(Country Country) {
		return CountryDAO.addCountry(Country);
	}


	public Country getCountryById(Long Id) {
		return CountryDAO.getCountryById(Id);
	}


	public List<Country> listCountrys() {
		return CountryDAO.listCountrys();
	}


	public void updateCountry(Country Country) {
		CountryDAO.updateCountry(Country);
		
	}

	
}
