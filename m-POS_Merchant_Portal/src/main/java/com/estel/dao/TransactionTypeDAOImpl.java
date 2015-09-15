package com.estel.dao;

import com.estel.entity.TransactionType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class TransactionTypeDAOImpl extends GenericDAOImpl<TransactionType, Long> implements TransactionTypeDAO {


    @Transactional
    public TransactionType addTransactionType(TransactionType TransactionType) {
        return (TransactionType) this.read(this.create(TransactionType));
    }


    @Transactional
    public TransactionType getTransactionTypeById(Long Id) {
        return (TransactionType) this.read(Id);
    }


    @SuppressWarnings("unchecked")
    @Transactional
    public List<TransactionType> listTransactionTypes() {
        return getSession().createQuery("from TransactionType").list();
    }


    @Transactional
    public void updateTransactionType(TransactionType TransactionType) {
        getSession().update(TransactionType);

    }

}
