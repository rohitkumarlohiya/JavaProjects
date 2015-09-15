package com.estel.dao;

import com.estel.entity.DeviceType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class DeviceTypeDAOImpl extends GenericDAOImpl<DeviceType, Long> implements DeviceTypeDAO {
	

	@Transactional
	public DeviceType addDeviceType(DeviceType DeviceType) {
		return (DeviceType) this.read(this.create(DeviceType));
	}


	@Transactional
	public DeviceType getDeviceTypeById(Long Id) {
		return (DeviceType) this.read(Id);
	}


	@SuppressWarnings("unchecked")
	@Transactional
	public List<DeviceType> listDeviceTypes() {
		return getSession().createQuery("from DeviceType").list();
	}


	@Transactional
	public void updateDeviceType(DeviceType DeviceType) {
		getSession().update(DeviceType);
		
	}

}
