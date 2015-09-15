package com.estel.service;

import com.estel.dao.GenericDAOImpl;
import com.estel.dao.PinTypeDAO;
import com.estel.entity.PinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PinTypeServiceImpl extends GenericDAOImpl<PinType, Long> implements
        PinTypeService {

	@Autowired
	private PinTypeDAO PinTypeDAO;


	public PinType addPinType(PinType PinType) {
		return PinTypeDAO.addPinType(PinType);
	}


	public PinType getPinTypeById(Long Id) {
		return PinTypeDAO.getPinTypeById(Id);
	}


	public List<PinType> listPinTypes() {
		return PinTypeDAO.listPinTypes();
	}


	public void updatePinType(PinType PinType) {
		PinTypeDAO.updatePinType(PinType);
		
	}

	
}
