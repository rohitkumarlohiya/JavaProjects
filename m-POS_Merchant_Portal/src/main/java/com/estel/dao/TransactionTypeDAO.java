package com.estel.dao;

import com.estel.entity.TransactionType;

import java.util.List;

public interface TransactionTypeDAO extends GenericDAO<TransactionType, Long> {

    public TransactionType addTransactionType(TransactionType TransactionType);

    public TransactionType getTransactionTypeById(Long Id);

    public List<TransactionType> listTransactionTypes();

    public void updateTransactionType(TransactionType TransactionType);
}
