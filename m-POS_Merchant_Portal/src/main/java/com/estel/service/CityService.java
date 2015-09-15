package com.estel.service;

import com.estel.entity.City;

import java.util.List;


public interface CityService {

	public City addCity(City City);
	public City getCityById(Long Id);
	public List<City> listCitys();
	public void updateCity(City City);

    public List<City> listAllActiveCityByStateId(Long stateId);
    public List<City> listAllActiveCityByStateDescription(String stateDesc);
    public City getCityByName(String cityName);
}
