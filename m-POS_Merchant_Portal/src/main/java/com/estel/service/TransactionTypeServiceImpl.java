package com.estel.service;

import com.estel.dao.GenericDAOImpl;
import com.estel.dao.TransactionTypeDAO;
import com.estel.entity.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionTypeServiceImpl extends GenericDAOImpl<TransactionType, Long> implements
        TransactionTypeService {

    @Autowired
    private TransactionTypeDAO TransactionTypeDAO;


    public TransactionType addTransactionType(TransactionType TransactionType) {
        return TransactionTypeDAO.addTransactionType(TransactionType);
    }


    public TransactionType getTransactionTypeById(Long Id) {
        return TransactionTypeDAO.getTransactionTypeById(Id);
    }


    public List<TransactionType> listTransactionTypes() {
        return TransactionTypeDAO.listTransactionTypes();
    }


    public void updateTransactionType(TransactionType TransactionType) {
        TransactionTypeDAO.updateTransactionType(TransactionType);

    }


}
