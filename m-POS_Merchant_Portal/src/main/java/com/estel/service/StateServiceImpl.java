package com.estel.service;

import com.estel.dao.GenericDAOImpl;
import com.estel.dao.StateDAO;
import com.estel.entity.State;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StateServiceImpl extends GenericDAOImpl<State, Long> implements
        StateService {

    @Autowired
    private StateDAO StateDAO;


    public State addState(State State) {
        return StateDAO.addState(State);
    }


    public State getStateById(Long Id) {
        return StateDAO.getStateById(Id);
    }


    public List<State> listStates() {
        return StateDAO.listStates();
    }


    public void updateState(State State) {
        StateDAO.updateState(State);

    }


    public State getStateByDesc(String stateDesc) {
        return StateDAO.getStateByDesc(stateDesc);
    }

    public List<State> listAllActiveCityByStateId(Long countryId) {
        return StateDAO.listAllActiveCityByStateId(countryId);
    }

}
