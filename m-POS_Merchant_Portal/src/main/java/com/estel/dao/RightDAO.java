package com.estel.dao;

import com.estel.entity.Right;

import java.util.List;

public interface RightDAO extends GenericDAO<Right, Long> {

	public Right addRight(Right Right);
	public Right getRightById(Long Id);
	public List<Right> listRights();
	public void updateRight(Right Right);
}
