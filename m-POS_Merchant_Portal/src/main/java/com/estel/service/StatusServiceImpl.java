package com.estel.service;

import com.estel.dao.GenericDAOImpl;
import com.estel.dao.StatusDAO;
import com.estel.entity.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusServiceImpl extends GenericDAOImpl<Status, Long> implements
        StatusService {

	@Autowired
	private StatusDAO StatusDAO;


	public Status addStatus(Status Status) {
		return StatusDAO.addStatus(Status);
	}


	public Status getStatusById(Long Id) {
		return StatusDAO.getStatusById(Id);
	}


	public List<Status> listStatuss() {
		return StatusDAO.listStatuss();
	}


	public void updateStatus(Status Status) {
		StatusDAO.updateStatus(Status);
		
	}


    public Status getStatusByCode(String statusCode)
    {
        return StatusDAO.getStatusByCode(statusCode);
    }

    public List<Status> listStatussByCode(String[] statusCodes)
    {
         return  StatusDAO.listStatussByCode(statusCodes);
    }

	
}
