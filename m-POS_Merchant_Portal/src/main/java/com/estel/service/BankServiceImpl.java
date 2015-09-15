package com.estel.service;

import com.estel.dao.BankDAO;
import com.estel.dao.GenericDAOImpl;
import com.estel.entity.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankServiceImpl extends GenericDAOImpl<Bank, Long> implements
        BankService {

	@Autowired
	private BankDAO BankDAO;


	public Bank addBank(Bank Bank) {
		return BankDAO.addBank(Bank);
	}


	public Bank getBankById(Long Id) {
		return BankDAO.getBankById(Id);
	}


	public List<Bank> listBanks() {
		return BankDAO.listBanks();
	}


	public void updateBank(Bank Bank) {
		BankDAO.updateBank(Bank);
		
	}

    public List<Bank> listBankByAgentId(Long agentId) {

        return  BankDAO.listBankByAgentId(agentId);
    }


    public Bank addBankByAgentId(String accountNumber,
                                 String accountType,
                                 Long agentId,
                                 String statusCode,
                                 String vendorAbbr,
                                 String branchCode,
                                 String accountName,
                                 String bankName,
                                 String branchName,
                                 Long routingNumber)
    {
           return BankDAO.addBankByAgentId( accountNumber, accountType, agentId, statusCode, vendorAbbr, branchCode, accountName, bankName, branchName, routingNumber);
    }

	
}
