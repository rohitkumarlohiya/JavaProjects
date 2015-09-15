package com.estel.service;

import com.estel.entity.PinType;

import java.util.List;


public interface PinTypeService {

	public PinType addPinType(PinType PinType);
	public PinType getPinTypeById(Long Id);
	public List<PinType> listPinTypes();
	public void updatePinType(PinType PinType);
}
