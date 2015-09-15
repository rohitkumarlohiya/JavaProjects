package com.estel.dao;

import com.estel.entity.BankAccountType;

import java.util.List;

public interface BankAccountTypeDAO extends GenericDAO<BankAccountType, Long> {

    public BankAccountType addBankAccountType(BankAccountType BankAccountType);
    public BankAccountType getBankAccountTypeById(Long Id);
    public List<BankAccountType> listBankAccountTypes();
    public void updateBankAccountType(BankAccountType BankAccountType);


    public BankAccountType getBankAccountTypeByAccountTypeName(String accountTypeName);
}
