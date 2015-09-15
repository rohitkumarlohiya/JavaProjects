package com.estel.dao;

import com.estel.entity.PasswordRecord;

import java.util.List;

public interface PasswordRecordDAO extends GenericDAO<PasswordRecord, Long> {

	public PasswordRecord addPasswordRecord(PasswordRecord PasswordRecord);
	public PasswordRecord getPasswordRecordById(Long Id);
	public List<PasswordRecord> listPasswordRecords();
	public void updatePasswordRecord(PasswordRecord PasswordRecord);

    public List<PasswordRecord> listPasswordRecordByAgentId(Long agentId);


    public List<PasswordRecord> listPasswordRecordByAgentIdAndPassword(Long agentId,String password);

    public PasswordRecord addPasswordRecordByAgentId(Long passwordType,String password,Long agentId);

    public PasswordRecord listAllByAgentIdWithLowestCTS(Long agentId);
}
