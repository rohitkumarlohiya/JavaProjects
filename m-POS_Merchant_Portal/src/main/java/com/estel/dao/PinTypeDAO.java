package com.estel.dao;

import com.estel.entity.PinType;

import java.util.List;

public interface PinTypeDAO extends GenericDAO<PinType, Long> {

	public PinType addPinType(PinType PinType);
	public PinType getPinTypeById(Long Id);
	public List<PinType> listPinTypes();
	public void updatePinType(PinType PinType);
}
