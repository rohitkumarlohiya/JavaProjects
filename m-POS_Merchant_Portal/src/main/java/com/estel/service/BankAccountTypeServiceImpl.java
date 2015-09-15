package com.estel.service;

import com.estel.dao.BankAccountTypeDAO;
import com.estel.dao.GenericDAOImpl;
import com.estel.entity.BankAccountType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BankAccountTypeServiceImpl extends GenericDAOImpl<BankAccountType, Long> implements
        BankAccountTypeService {

    @Autowired
    private BankAccountTypeDAO BankAccountTypeDAO;


    public BankAccountType addBankAccountType(BankAccountType BankAccountType) {
        return BankAccountTypeDAO.addBankAccountType(BankAccountType);
    }


    public BankAccountType getBankAccountTypeById(Long Id) {
        return BankAccountTypeDAO.getBankAccountTypeById(Id);
    }


    public List<BankAccountType> listBankAccountTypes() {
        return BankAccountTypeDAO.listBankAccountTypes();
    }


    public void updateBankAccountType(BankAccountType BankAccountType) {
        BankAccountTypeDAO.updateBankAccountType(BankAccountType);

    }


    @Transactional
    public BankAccountType getBankAccountTypeByAccountTypeName(String accountTypeName)
    {
       return BankAccountTypeDAO.getBankAccountTypeByAccountTypeName(accountTypeName);
    }


}
