package com.estel.service;

import com.estel.dao.CityDAO;
import com.estel.dao.GenericDAOImpl;
import com.estel.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl extends GenericDAOImpl<City, Long> implements
        CityService {

    @Autowired
    private CityDAO CityDAO;


    public City addCity(City City) {
        return CityDAO.addCity(City);
    }


    public City getCityById(Long Id) {
        return CityDAO.getCityById(Id);
    }

    public List<City> listCitys() {
        return CityDAO.listCitys();
    }


    public void updateCity(City City) {
        CityDAO.updateCity(City);

    }


    public List<City> listAllActiveCityByStateId(Long stateId) {

        return CityDAO.listAllActiveCityByStateId(stateId);
    }

    public List<City> listAllActiveCityByStateDescription(String stateDesc)
    {
        return CityDAO.listAllActiveCityByStateDescription(stateDesc);
    }


    public City getCityByName(String cityName) {
        return CityDAO.getCityByName(cityName);
    }

}
