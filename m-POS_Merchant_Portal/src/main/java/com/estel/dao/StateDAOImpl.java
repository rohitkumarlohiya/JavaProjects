package com.estel.dao;

import com.estel.entity.State;
import com.estel.entity.Status;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
public class StateDAOImpl extends GenericDAOImpl<State, Long> implements StateDAO {

    @Autowired
    private StatusDAO statusDAO;

    @Autowired
    private  CountryDAO countryDAO;

	@Transactional
	public State addState(State State) {
		return (State) this.read(this.create(State));
	}


	@Transactional
	public State getStateById(Long Id) {
		return (State) this.read(Id);
	}


	@SuppressWarnings("unchecked")
	@Transactional
	public List<State> listStates() {
		return getSession().createQuery("from State").list();
	}


	@Transactional
	public void updateState(State State) {
		getSession().update(State);
		
	}

    @Transactional
    public State getStateByDesc(String stateDesc)
    {
        String sql = "from State where stateDescription=:stateDesc";
        Query query = getSession().createQuery(sql);
        query.setString("stateDesc", stateDesc);
        List<State> stateList = query.list();
        if (stateList.size() <= 0) {
            return null;
        }
        return stateList.get(0);
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public List<State> listAllActiveCityByStateId(Long countryId) {

        Status status = statusDAO.getStatusByCode("Y");

        String sql = "from State where status=:status and stateCountryId=:countryId";
        Query query = getSession().createQuery(sql);
        query.setEntity("status", status);
        query.setLong("countryId", countryId);
        List<State> stateList = query.list();
        if (stateList.size() <= 0) {
            return null;
        }
        return stateList;

    }


}
