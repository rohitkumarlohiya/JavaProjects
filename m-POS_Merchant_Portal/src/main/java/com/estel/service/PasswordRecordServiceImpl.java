package com.estel.service;

import com.estel.dao.GenericDAOImpl;
import com.estel.dao.PasswordRecordDAO;
import com.estel.entity.PasswordRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasswordRecordServiceImpl extends GenericDAOImpl<PasswordRecord, Long> implements
        PasswordRecordService {

	@Autowired
	private PasswordRecordDAO PasswordRecordDAO;


	public PasswordRecord addPasswordRecord(PasswordRecord PasswordRecord) {
		return PasswordRecordDAO.addPasswordRecord(PasswordRecord);
	}


	public PasswordRecord getPasswordRecordById(Long Id) {
		return PasswordRecordDAO.getPasswordRecordById(Id);
	}


	public List<PasswordRecord> listPasswordRecords() {
		return PasswordRecordDAO.listPasswordRecords();
	}


	public void updatePasswordRecord(PasswordRecord PasswordRecord) {
		PasswordRecordDAO.updatePasswordRecord(PasswordRecord);
		
	}


    public List<PasswordRecord> listPasswordRecordByAgentId(Long agentId)
    {
       return PasswordRecordDAO.listPasswordRecordByAgentId(agentId);
    }


    public List<PasswordRecord> listPasswordRecordByAgentIdAndPassword(Long agentId,String password)
    {
        return PasswordRecordDAO.listPasswordRecordByAgentIdAndPassword(agentId,password);
    }


    public PasswordRecord addPasswordRecordByAgentId(Long passwordType,String password,Long agentId)
    {
        return PasswordRecordDAO.addPasswordRecordByAgentId(passwordType, password, agentId);
    }


    public PasswordRecord listAllByAgentIdWithLowestCTS(Long agentId)
    {
        return PasswordRecordDAO.listAllByAgentIdWithLowestCTS(agentId);
    }
	
}
