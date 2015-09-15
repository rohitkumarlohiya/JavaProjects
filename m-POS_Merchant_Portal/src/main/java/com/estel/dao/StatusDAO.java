package com.estel.dao;

import com.estel.entity.Status;

import java.util.List;

public interface StatusDAO extends GenericDAO<Status, Long> {

	public Status addStatus(Status Status);
	public Status getStatusById(Long Id);
	public List<Status> listStatuss();
	public void updateStatus(Status Status);

    public Status getStatusByCode(String statusCode);
    public List<Status> listStatussByCode(String[] statusCodes);
}
