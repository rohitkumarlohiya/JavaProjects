package com.estel.dao;

import com.estel.dto.TransactionDetailModel;
import com.estel.dto.TransactionModel;
import com.estel.entity.Transaction;

import java.util.List;

public interface TransactionDAO extends GenericDAO<Transaction, Long> {

    public Transaction addTransaction(Transaction Transaction);

    public Transaction getTransactionById(Long Id);

    public List<Transaction> listTransactions();

    public void updateTransaction(Transaction Transaction);

    public List<Transaction> getTransList(String transType, String transId, String agentCode);

    public List<Transaction> getBatchHistory(String startDate, String endDate, String deviceNum, String batchNum, String agentId);

    public List<TransactionModel> getTransSummary(String startDate, String endDate, String agentId);

    public List<TransactionDetailModel> getTransDetail(String startDate, String endDate, String deviceNum, String last4Digit, String AuthNum, String rrn, String transType, String agentId);

}
