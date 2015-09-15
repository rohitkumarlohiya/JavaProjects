package com.estel.dto;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * User: ashutosh
 * Date: 2/4/14
 * Time: 11:09 AM
 * To change this template use File | Settings | File Templates.
 */
public class TransactionDetailModel {

    private BigDecimal transactionsValue;
    private Long transactionsTransactionId;
    private String transactionsMerchantMobileNumber;
    private String transactionsTransTypeCode;
    private String transactionsMerchantCode;
    private String transactionsCcLast;
    private String transactionsSiResultcode;
    private String transactionsRrnNumber;
    private String transactionsAuthNumber;
    private String transactionsBatchNumber;
    private String transactionsResultcode;
    private String transactionsResultDescription;
    private Timestamp transactionsRequestTs;
    private Timestamp transactionsResponseTs;


    public BigDecimal getTransactionsValue() {
        return transactionsValue;
    }

    public void setTransactionsValue(BigDecimal transactionsValue) {
        this.transactionsValue = transactionsValue;
    }

    public Long getTransactionsTransactionId() {
        return transactionsTransactionId;
    }

    public void setTransactionsTransactionId(Long transactionsTransactionId) {
        this.transactionsTransactionId = transactionsTransactionId;
    }

    public String getTransactionsMerchantMobileNumber() {
        return transactionsMerchantMobileNumber;
    }

    public void setTransactionsMerchantMobileNumber(String transactionsMerchantMobileNumber) {
        this.transactionsMerchantMobileNumber = transactionsMerchantMobileNumber;
    }

    public String getTransactionsTransTypeCode() {
        return transactionsTransTypeCode;
    }

    public void setTransactionsTransTypeCode(String transactionsTransTypeCode) {
        this.transactionsTransTypeCode = transactionsTransTypeCode;
    }

    public String getTransactionsMerchantCode() {
        return transactionsMerchantCode;
    }

    public void setTransactionsMerchantCode(String transactionsMerchantCode) {
        this.transactionsMerchantCode = transactionsMerchantCode;
    }

    public String getTransactionsCcLast() {
        return transactionsCcLast;
    }

    public void setTransactionsCcLast(String transactionsCcLast) {
        this.transactionsCcLast = transactionsCcLast;
    }

    public String getTransactionsSiResultcode() {
        return transactionsSiResultcode;
    }

    public void setTransactionsSiResultcode(String transactionsSiResultcode) {
        this.transactionsSiResultcode = transactionsSiResultcode;
    }

    public String getTransactionsRrnNumber() {
        return transactionsRrnNumber;
    }

    public void setTransactionsRrnNumber(String transactionsRrnNumber) {
        this.transactionsRrnNumber = transactionsRrnNumber;
    }

    public String getTransactionsAuthNumber() {
        return transactionsAuthNumber;
    }

    public void setTransactionsAuthNumber(String transactionsAuthNumber) {
        this.transactionsAuthNumber = transactionsAuthNumber;
    }

    public String getTransactionsBatchNumber() {
        return transactionsBatchNumber;
    }

    public void setTransactionsBatchNumber(String transactionsBatchNumber) {
        this.transactionsBatchNumber = transactionsBatchNumber;
    }

    public String getTransactionsResultcode() {
        return transactionsResultcode;
    }

    public void setTransactionsResultcode(String transactionsResultcode) {
        this.transactionsResultcode = transactionsResultcode;
    }

    public String getTransactionsResultDescription() {
        return transactionsResultDescription;
    }

    public void setTransactionsResultDescription(String transactionsResultDescription) {
        this.transactionsResultDescription = transactionsResultDescription;
    }

    public Timestamp getTransactionsRequestTs() {
        return transactionsRequestTs;
    }

    public void setTransactionsRequestTs(Timestamp transactionsRequestTs) {
        this.transactionsRequestTs = transactionsRequestTs;
    }

    public Timestamp getTransactionsResponseTs() {
        return transactionsResponseTs;
    }

    public void setTransactionsResponseTs(Timestamp transactionsResponseTs) {
        this.transactionsResponseTs = transactionsResponseTs;
    }
}
