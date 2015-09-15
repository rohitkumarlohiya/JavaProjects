package com.estel.dao;

import com.estel.entity.City;
import com.estel.entity.State;
import com.estel.entity.Status;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CityDAOImpl extends GenericDAOImpl<City, Long> implements CityDAO {

    @Autowired
    private StatusDAO statusDAO;

    @Autowired
    private StateDAO stateDAO;

		@Transactional
	public City addCity(City City) {
		return (City) this.read(this.create(City));
	}


	@Transactional
	public City getCityById(Long Id) {
		return (City) this.read(Id);
	}


	@SuppressWarnings("unchecked")
	@Transactional
	public List<City> listCitys() {
		return getSession().createQuery("from City").list();
	}

	@Transactional
	public void updateCity(City City) {
		getSession().update(City);
		
	}



    @SuppressWarnings("unchecked")
    @Transactional
    public List<City> listAllActiveCityByStateId(Long stateId) {

        Status status = statusDAO.getStatusByCode("Y");

        String sql = "from City where status=:status and cityStateId=:stateId";
        Query query = getSession().createQuery(sql);
        query.setEntity("status", status);
        query.setLong("stateId", stateId);
        List<City> cityList = query.list();
        if (cityList.size() <= 0) {
            return null;
        }
        return cityList;

    }

    @SuppressWarnings("unchecked")
    @Transactional
    public List<City> listAllActiveCityByStateDescription(String stateDesc)
    {
        Status status = statusDAO.getStatusByCode("Y");
        State state = stateDAO.getStateByDesc(stateDesc);

        String sql = "from City where status=:status and cityStateId=:stateId";
        Query query = getSession().createQuery(sql);
        query.setEntity("status", status);
        query.setEntity("stateId", state.getStateId());
        List<City> cityList = query.list();
        if (cityList.size() <= 0) {
            return null;
        }
        return cityList;
    }

    @Transactional
    public City getCityByName(String cityName) {

        String sql = "from City where cityDescription=:cityName";
        Query query = getSession().createQuery(sql);
        query.setString("cityName", cityName);

        List<City> cityList = query.list();
        if (cityList.size() <= 0) {
            return null;
        }
        return cityList.get(0);
    }

}
