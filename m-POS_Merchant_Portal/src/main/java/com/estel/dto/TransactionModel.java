package com.estel.dto;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created with IntelliJ IDEA.
 * User: ashutosh
 * Date: 2/3/14
 * Time: 7:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class TransactionModel {

    private Date transactionsResponseDate;
    private String transactionsTransTypeCode;
    private BigDecimal transactionsValue;
    private BigDecimal transactionscount;
    private String transactionsMobileNumber;


    public String getTransactionsMobileNumber() {
        return transactionsMobileNumber;
    }

    public void setTransactionsMobileNumber(String transactionsMobileNumber) {
        this.transactionsMobileNumber = transactionsMobileNumber;
    }

    public Date getTransactionsResponseDate() {
        return transactionsResponseDate;
    }

    public void setTransactionsResponseDate(Date transactionsResponseDate) {
        this.transactionsResponseDate = transactionsResponseDate;
    }

    public String getTransactionsTransTypeCode() {
        return transactionsTransTypeCode;
    }

    public void setTransactionsTransTypeCode(String transactionsTransTypeCode) {
        this.transactionsTransTypeCode = transactionsTransTypeCode;
    }

    public BigDecimal getTransactionsValue() {
        return transactionsValue;
    }

    public void setTransactionsValue(BigDecimal transactionsValue) {
        this.transactionsValue = transactionsValue;
    }

    public BigDecimal getTransactionscount() {
        return transactionscount;
    }

    public void setTransactionscount(BigDecimal transactionscount) {
        this.transactionscount = transactionscount;
    }
}
