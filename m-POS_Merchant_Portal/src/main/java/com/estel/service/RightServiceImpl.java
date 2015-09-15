package com.estel.service;

import com.estel.dao.GenericDAOImpl;
import com.estel.dao.RightDAO;
import com.estel.entity.Right;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RightServiceImpl extends GenericDAOImpl<Right, Long> implements
        RightService {

	@Autowired
	private RightDAO RightDAO;


	public Right addRight(Right Right) {
		return RightDAO.addRight(Right);
	}


	public Right getRightById(Long Id) {
		return RightDAO.getRightById(Id);
	}


	public List<Right> listRights() {
		return RightDAO.listRights();
	}


	public void updateRight(Right Right) {
		RightDAO.updateRight(Right);
		
	}

	
}
