package com.estel.service;

import com.estel.dao.DeviceTypeDAO;
import com.estel.dao.GenericDAOImpl;
import com.estel.entity.DeviceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceTypeServiceImpl extends GenericDAOImpl<DeviceType, Long> implements
        DeviceTypeService {

	@Autowired
	private DeviceTypeDAO DeviceTypeDAO;


	public DeviceType addDeviceType(DeviceType DeviceType) {
		return DeviceTypeDAO.addDeviceType(DeviceType);
	}


	public DeviceType getDeviceTypeById(Long Id) {
		return DeviceTypeDAO.getDeviceTypeById(Id);
	}


	public List<DeviceType> listDeviceTypes() {
		return DeviceTypeDAO.listDeviceTypes();
	}


	public void updateDeviceType(DeviceType DeviceType) {
		DeviceTypeDAO.updateDeviceType(DeviceType);
		
	}


}
