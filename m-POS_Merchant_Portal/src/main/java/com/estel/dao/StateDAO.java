package com.estel.dao;

import com.estel.entity.State;

import java.util.List;

public interface StateDAO extends GenericDAO<State, Long> {

	public State addState(State State);
	public State getStateById(Long Id);
	public List<State> listStates();
	public void updateState(State State);


    public State getStateByDesc(String stateDesc);
    public List<State> listAllActiveCityByStateId(Long countryId);
}
