package com.estel.service;

import com.estel.entity.Right;

import java.util.List;


public interface RightService {

	public Right addRight(Right Right);
	public Right getRightById(Long Id);
	public List<Right> listRights();
	public void updateRight(Right Right);
}
