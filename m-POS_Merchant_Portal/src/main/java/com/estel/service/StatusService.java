package com.estel.service;

import com.estel.entity.Status;

import java.util.List;


public interface StatusService {

	public Status addStatus(Status Status);
	public Status getStatusById(Long Id);
	public List<Status> listStatuss();
	public void updateStatus(Status Status);

    public Status getStatusByCode(String statusCode);
    public List<Status> listStatussByCode(String[] statusCodes);

}
