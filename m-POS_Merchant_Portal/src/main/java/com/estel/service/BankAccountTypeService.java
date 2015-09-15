package com.estel.service;

import java.util.List;
import com.estel.entity.BankAccountType;



public interface BankAccountTypeService {

    public BankAccountType addBankAccountType(BankAccountType BankAccountType);
    public BankAccountType getBankAccountTypeById(Long Id);
    public List<BankAccountType> listBankAccountTypes();
    public void updateBankAccountType(BankAccountType BankAccountType);


    public BankAccountType getBankAccountTypeByAccountTypeName(String accountTypeName);
}
