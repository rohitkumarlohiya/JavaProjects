package com.estel.dao;

import com.estel.entity.PinType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class PinTypeDAOImpl extends GenericDAOImpl<PinType, Long> implements PinTypeDAO {
	

	@Transactional
	public PinType addPinType(PinType PinType) {
		return (PinType) this.read(this.create(PinType));
	}


	@Transactional
	public PinType getPinTypeById(Long Id) {
		return (PinType) this.read(Id);
	}


	@SuppressWarnings("unchecked")
	@Transactional
	public List<PinType> listPinTypes() {
		return getSession().createQuery("from PinType").list();
	}


	@Transactional
	public void updatePinType(PinType PinType) {
		getSession().update(PinType);
		
	}

}
