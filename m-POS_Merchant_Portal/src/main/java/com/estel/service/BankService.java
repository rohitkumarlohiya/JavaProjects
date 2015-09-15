package com.estel.service;

import com.estel.entity.Bank;

import java.util.List;


public interface BankService {

	public Bank addBank(Bank Bank);
	public Bank getBankById(Long Id);
	public List<Bank> listBanks();
	public void updateBank(Bank Bank);

    public List<Bank> listBankByAgentId(Long agentId);

    public Bank addBankByAgentId(String accountNumber,
                                 String accountType,
                                 Long agentId,
                                 String statusCode,
                                 String vendorAbbr,
                                 String branchCode,
                                 String accountName,
                                 String bankName,
                                 String branchName,
                                 Long routingNumber);


}
