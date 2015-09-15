package com.estel.dao;

import com.estel.entity.Bank;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class BankDAOImpl extends GenericDAOImpl<Bank, Long> implements BankDAO {


    @Autowired
    MerchantDAO merchantDAO;

    @Autowired
    BankAccountTypeDAO bankAccountTypeDAO;

    @Autowired
    StatusDAO statusDAO;

    @Autowired
    Bank bank;

	@Transactional
	public Bank addBank(Bank Bank) {
		return (Bank) this.read(this.create(Bank));
	}


	@Transactional
	public Bank getBankById(Long Id) {
		return (Bank) this.read(Id);
	}


	@SuppressWarnings("unchecked")
	@Transactional
	public List<Bank> listBanks() {
		return getSession().createQuery("from Bank").list();
	}


	@Transactional
	public void updateBank(Bank Bank) {
		getSession().update(Bank);
		
	}

    @SuppressWarnings("unchecked")
    @Transactional
    public List<Bank> listBankByAgentId(Long agentId)
    {
        String sql = "from Bank where bankMerchantId=:agentId";
        Query query = getSession().createQuery(sql);
        query.setLong("agentId", agentId);
        List<Bank> bankList = query.list();
        if (bankList.size() <= 0) {
            return null;
        }
        return bankList;
    }

    @Transactional
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
        //set mandatory fields
        /*bank_account_type_id
        bank_account_number
        bank_merchant_id
        bank_status_id
        bank_ts*/
        bank.setBankAccountTypeId(bankAccountTypeDAO.getBankAccountTypeByAccountTypeName(accountType).getBankAccountTypeId());
        bank.setBankAccountNumber(accountNumber);
        bank.setBankMerchantId(agentId);
        bank.setStatus(statusDAO.getStatusByCode(statusCode));
        bank.setBankTs(new java.sql.Date(System.currentTimeMillis()));
        //set mandatory field

        bank.setBankBranchCode(branchCode);
        bank.setBankAccountName(accountName);
        bank.setBankName(bankName);
        bank.setBankBranchName(branchName);
        bank.setBankRoutingNo(routingNumber);

        return (Bank) this.read(this.create(bank));

    }


}
