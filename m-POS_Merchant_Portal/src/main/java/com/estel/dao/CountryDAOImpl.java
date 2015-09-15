package com.estel.dao;

import com.estel.entity.Country;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CountryDAOImpl extends GenericDAOImpl<Country, Long> implements CountryDAO {
	

	@Transactional
	public Country addCountry(Country Country) {
		return (Country) this.read(this.create(Country));
	}


	@Transactional
	public Country getCountryById(Long Id) {
		return (Country) this.read(Id);
	}


	@SuppressWarnings("unchecked")
	@Transactional
	public List<Country> listCountrys() {
		return getSession().createQuery("from Country ").list();
	}


	@Transactional
	public void updateCountry(Country Country) {
		getSession().update(Country);
		
	}

}
