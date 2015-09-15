package com.estel.service;

import com.estel.dao.GenericDAOImpl;
import com.estel.dao.TransactionDAO;
import com.estel.dto.TransactionDetailModel;
import com.estel.dto.TransactionModel;
import com.estel.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl extends GenericDAOImpl<Transaction, Long> implements
        TransactionService {

    @Autowired
    private TransactionDAO TransactionDAO;


    public Transaction addTransaction(Transaction Transaction) {
        return TransactionDAO.addTransaction(Transaction);
    }


    public Transaction getTransactionById(Long Id) {
        return TransactionDAO.getTransactionById(Id);
    }


    public List<Transaction> listTransactions() {
        return TransactionDAO.listTransactions();
    }


    public void updateTransaction(Transaction Transaction) {
        TransactionDAO.updateTransaction(Transaction);

    }

    public List<Transaction> getTransList(String transType,String transId,String agentCode){
        return TransactionDAO.getTransList(transType,transId,agentCode);
    }

    public List<Transaction> getBatchHistory(String startDate, String endDate, String deviceNum, String batchNum,String agentId){
        return TransactionDAO.getBatchHistory(startDate, endDate,deviceNum, batchNum,agentId);
    }

    public List<TransactionModel> getTransSummary(String startDate, String endDate,String agentId){
        return TransactionDAO.getTransSummary(startDate, endDate, agentId);
    }

    public List<TransactionDetailModel> getTransDetail(String startDate, String endDate,String deviceNum,String last4Digit,String AuthNum,String rrn,String transType,String agentId){
        return TransactionDAO.getTransDetail( startDate,  endDate, deviceNum, last4Digit, AuthNum, rrn, transType, agentId);
    }

}
