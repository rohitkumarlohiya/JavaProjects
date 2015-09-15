package com.estel.service;

import com.estel.entity.TransactionType;

import java.util.List;


public interface TransactionTypeService {

    public TransactionType addTransactionType(TransactionType TransactionType);

    public TransactionType getTransactionTypeById(Long Id);

    public List<TransactionType> listTransactionTypes();

    public void updateTransactionType(TransactionType TransactionType);
}
