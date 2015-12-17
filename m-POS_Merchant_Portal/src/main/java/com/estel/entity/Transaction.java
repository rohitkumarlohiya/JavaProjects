package com.estel.entity;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;


/**
 * The persistent class for the transactions database table.
 *
 */
@Component
@Entity
@Table(name="transactions")
public class Transaction implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
//    @SequenceGenerator(name="TRANSACTIONS_TRANSACTIONSID_GENERATOR" )
//    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TRANSACTIONS_TRANSACTIONSID_GENERATOR")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="transactions_id")
    private Long transactionsId;

    @Column(name="transactions_auth_number")
    private String transactionsAuthNumber;

    @Column(name="transactions_batch_number")
    private String transactionsBatchNumber;

    @Column(name="transactions_cc_first")
    private String transactionsCcFirst;

    @Column(name="transactions_cc_last")
    private String transactionsCcLast;

    @Column(name="transactions_cc_name")
    private String transactionsCcName;

    @Column(name="transactions_comments")
    private String transactionsComments;

    @Column(name="transactions_fee")
    private BigDecimal transactionsFee;

    @Column(name="transactions_invoice_number")
    private String transactionsInvoiceNumber;

    @Column(name="transactions_is_chargeslip")
    private String transactionsIsChargeslip;

    @Column(name="transactions_is_refunded")
    private String transactionsIsRefunded;

    @Column(name="transactions_is_reversed")
    private String transactionsIsReversed;

    @Column(name="transactions_is_settled")
    private String transactionsIsSettled;

    @Column(name="transactions_is_signature_capture")
    private String transactionsIsSignatureCapture;

    @Column(name="transactions_merchant_code")
    private String transactionsMerchantCode;

    @Column(name="transactions_merchant_device_id")
    private Long transactionsMerchantDeviceId;

    @Column(name="transactions_merchant_device_number")
    private String transactionsMerchantDeviceNumber;

    @Column(name="transactions_merchant_id")
    private Long transactionsMerchantId;

    @Column(name="transactions_merchant_level_id")
    private Long transactionsMerchantLevelId;

    @Column(name="transactions_merchant_mobile_number")
    private String transactionsMerchantMobileNumber;

    @Column(name="transactions_merchant_parent_id")
    private Long transactionsMerchantParentId;

    @Column(name="transactions_refund_ts")
    private Date transactionsRefundTs;

    @Column(name="transactions_request_ts")
    private Date transactionsRequestTs;

    @Column(name="transactions_response_ts")
    private Date transactionsResponseTs;

    @Column(name="transactions_result_description")
    private String transactionsResultDescription;

    @Column(name="transactions_resultcode")
    private String transactionsResultcode;

    @Column(name="transactions_reverse_ts")
    private Date transactionsReverseTs;

    @Column(name="transactions_rrn_number")
    private String transactionsRrnNumber;

    @Column(name="transactions_service_tax")
    private BigDecimal transactionsServiceTax;

    @Column(name="transactions_si_id")
    private Long transactionsSiId;

    @Column(name="transactions_si_result_description")
    private String transactionsSiResultDescription;

    @Column(name="transactions_si_resultcode")
    private String transactionsSiResultcode;

    @Column(name="transactions_si_trans_id")
    private String transactionsSiTransId;

    @Column(name="transactions_trans_ref_id")
    private Long transactionsTransRefId;

    @Column(name="transactions_trans_type_code")
    private String transactionsTransTypeCode;

    @Column(name="transactions_trans_type_id")
    private Long transactionsTransTypeId;

    @Column(name="transactions_transaction_id")
    private Long transactionsTransactionId;

    @Column(name="transactions_ts")
    private Date transactionsTs;

    @Column(name="transactions_value")
    private BigDecimal transactionsValue;

    @Column(name="transactions_vat")
    private BigDecimal transactionsVat;


    private BigDecimal transactionscount;

    public Transaction() {
    }

    public BigDecimal getTransactionscount() {
        return transactionscount;
    }

    public void setTransactionscount(BigDecimal transactionscount) {
        this.transactionscount = transactionscount;
    }


    public Long getTransactionsId() {
        return this.transactionsId;
    }

    public void setTransactionsId(Long transactionsId) {
        this.transactionsId = transactionsId;
    }

    public String getTransactionsAuthNumber() {
        return this.transactionsAuthNumber;
    }

    public void setTransactionsAuthNumber(String transactionsAuthNumber) {
        this.transactionsAuthNumber = transactionsAuthNumber;
    }

    public String getTransactionsBatchNumber() {
        return this.transactionsBatchNumber;
    }

    public void setTransactionsBatchNumber(String transactionsBatchNumber) {
        this.transactionsBatchNumber = transactionsBatchNumber;
    }

    public String getTransactionsCcFirst() {
        return this.transactionsCcFirst;
    }

    public void setTransactionsCcFirst(String transactionsCcFirst) {
        this.transactionsCcFirst = transactionsCcFirst;
    }

    public String getTransactionsCcLast() {
        return this.transactionsCcLast;
    }

    public void setTransactionsCcLast(String transactionsCcLast) {
        this.transactionsCcLast = transactionsCcLast;
    }

    public String getTransactionsCcName() {
        return this.transactionsCcName;
    }

    public void setTransactionsCcName(String transactionsCcName) {
        this.transactionsCcName = transactionsCcName;
    }

    public String getTransactionsComments() {
        return this.transactionsComments;
    }

    public void setTransactionsComments(String transactionsComments) {
        this.transactionsComments = transactionsComments;
    }

    public BigDecimal getTransactionsFee() {
        return this.transactionsFee;
    }

    public void setTransactionsFee(BigDecimal transactionsFee) {
        this.transactionsFee = transactionsFee;
    }

    public String getTransactionsInvoiceNumber() {
        return this.transactionsInvoiceNumber;
    }

    public void setTransactionsInvoiceNumber(String transactionsInvoiceNumber) {
        this.transactionsInvoiceNumber = transactionsInvoiceNumber;
    }

    public String getTransactionsIsChargeslip() {
        return this.transactionsIsChargeslip;
    }

    public void setTransactionsIsChargeslip(String transactionsIsChargeslip) {
        this.transactionsIsChargeslip = transactionsIsChargeslip;
    }

    public String getTransactionsIsRefunded() {
        return this.transactionsIsRefunded;
    }

    public void setTransactionsIsRefunded(String transactionsIsRefunded) {
        this.transactionsIsRefunded = transactionsIsRefunded;
    }

    public String getTransactionsIsReversed() {
        return this.transactionsIsReversed;
    }

    public void setTransactionsIsReversed(String transactionsIsReversed) {
        this.transactionsIsReversed = transactionsIsReversed;
    }

    public String getTransactionsIsSettled() {
        return this.transactionsIsSettled;
    }

    public void setTransactionsIsSettled(String transactionsIsSettled) {
        this.transactionsIsSettled = transactionsIsSettled;
    }

    public String getTransactionsIsSignatureCapture() {
        return this.transactionsIsSignatureCapture;
    }

    public void setTransactionsIsSignatureCapture(String transactionsIsSignatureCapture) {
        this.transactionsIsSignatureCapture = transactionsIsSignatureCapture;
    }

    public String getTransactionsMerchantCode() {
        return this.transactionsMerchantCode;
    }

    public void setTransactionsMerchantCode(String transactionsMerchantCode) {
        this.transactionsMerchantCode = transactionsMerchantCode;
    }

    public Long getTransactionsMerchantDeviceId() {
        return this.transactionsMerchantDeviceId;
    }

    public void setTransactionsMerchantDeviceId(Long transactionsMerchantDeviceId) {
        this.transactionsMerchantDeviceId = transactionsMerchantDeviceId;
    }

    public String getTransactionsMerchantDeviceNumber() {
        return this.transactionsMerchantDeviceNumber;
    }

    public void setTransactionsMerchantDeviceNumber(String transactionsMerchantDeviceNumber) {
        this.transactionsMerchantDeviceNumber = transactionsMerchantDeviceNumber;
    }

    public Long getTransactionsMerchantId() {
        return this.transactionsMerchantId;
    }

    public void setTransactionsMerchantId(Long transactionsMerchantId) {
        this.transactionsMerchantId = transactionsMerchantId;
    }

    public Long getTransactionsMerchantLevelId() {
        return this.transactionsMerchantLevelId;
    }

    public void setTransactionsMerchantLevelId(Long transactionsMerchantLevelId) {
        this.transactionsMerchantLevelId = transactionsMerchantLevelId;
    }

    public String getTransactionsMerchantMobileNumber() {
        return this.transactionsMerchantMobileNumber;
    }

    public void setTransactionsMerchantMobileNumber(String transactionsMerchantMobileNumber) {
        this.transactionsMerchantMobileNumber = transactionsMerchantMobileNumber;
    }

    public Long getTransactionsMerchantParentId() {
        return this.transactionsMerchantParentId;
    }

    public void setTransactionsMerchantParentId(Long transactionsMerchantParentId) {
        this.transactionsMerchantParentId = transactionsMerchantParentId;
    }

    public Date getTransactionsRefundTs() {
        return this.transactionsRefundTs;
    }

    public void setTransactionsRefundTs(Date transactionsRefundTs) {
        this.transactionsRefundTs = transactionsRefundTs;
    }

    public Date getTransactionsRequestTs() {
        return this.transactionsRequestTs;
    }

    public void setTransactionsRequestTs(Date transactionsRequestTs) {
        this.transactionsRequestTs = transactionsRequestTs;
    }

    public Date getTransactionsResponseTs() {
        return this.transactionsResponseTs;
    }

    public void setTransactionsResponseTs(Date transactionsResponseTs) {
        this.transactionsResponseTs = transactionsResponseTs;
    }

    public String getTransactionsResultDescription() {
        return this.transactionsResultDescription;
    }

    public void setTransactionsResultDescription(String transactionsResultDescription) {
        this.transactionsResultDescription = transactionsResultDescription;
    }

    public String getTransactionsResultcode() {
        return this.transactionsResultcode;
    }

    public void setTransactionsResultcode(String transactionsResultcode) {
        this.transactionsResultcode = transactionsResultcode;
    }

    public Date getTransactionsReverseTs() {
        return this.transactionsReverseTs;
    }

    public void setTransactionsReverseTs(Date transactionsReverseTs) {
        this.transactionsReverseTs = transactionsReverseTs;
    }

    public String getTransactionsRrnNumber() {
        return this.transactionsRrnNumber;
    }

    public void setTransactionsRrnNumber(String transactionsRrnNumber) {
        this.transactionsRrnNumber = transactionsRrnNumber;
    }

    public BigDecimal getTransactionsServiceTax() {
        return this.transactionsServiceTax;
    }

    public void setTransactionsServiceTax(BigDecimal transactionsServiceTax) {
        this.transactionsServiceTax = transactionsServiceTax;
    }

    public Long getTransactionsSiId() {
        return this.transactionsSiId;
    }

    public void setTransactionsSiId(Long transactionsSiId) {
        this.transactionsSiId = transactionsSiId;
    }

    public String getTransactionsSiResultDescription() {
        return this.transactionsSiResultDescription;
    }

    public void setTransactionsSiResultDescription(String transactionsSiResultDescription) {
        this.transactionsSiResultDescription = transactionsSiResultDescription;
    }

    public String getTransactionsSiResultcode() {
        return this.transactionsSiResultcode;
    }

    public void setTransactionsSiResultcode(String transactionsSiResultcode) {
        this.transactionsSiResultcode = transactionsSiResultcode;
    }

    public String getTransactionsSiTransId() {
        return this.transactionsSiTransId;
    }

    public void setTransactionsSiTransId(String transactionsSiTransId) {
        this.transactionsSiTransId = transactionsSiTransId;
    }

    public Long getTransactionsTransRefId() {
        return this.transactionsTransRefId;
    }

    public void setTransactionsTransRefId(Long transactionsTransRefId) {
        this.transactionsTransRefId = transactionsTransRefId;
    }

    public String getTransactionsTransTypeCode() {
        return this.transactionsTransTypeCode;
    }

    public void setTransactionsTransTypeCode(String transactionsTransTypeCode) {
        this.transactionsTransTypeCode = transactionsTransTypeCode;
    }

    public Long getTransactionsTransTypeId() {
        return this.transactionsTransTypeId;
    }

    public void setTransactionsTransTypeId(Long transactionsTransTypeId) {
        this.transactionsTransTypeId = transactionsTransTypeId;
    }

    public Long getTransactionsTransactionId() {
        return this.transactionsTransactionId;
    }

    public void setTransactionsTransactionId(Long transactionsTransactionId) {
        this.transactionsTransactionId = transactionsTransactionId;
    }

    public Date getTransactionsTs() {
        return this.transactionsTs;
    }

    public void setTransactionsTs(Date transactionsTs) {
        this.transactionsTs = transactionsTs;
    }

    public BigDecimal getTransactionsValue() {
        return this.transactionsValue;
    }

    public void setTransactionsValue(BigDecimal transactionsValue) {
        this.transactionsValue = transactionsValue;
    }

    public BigDecimal getTransactionsVat() {
        return this.transactionsVat;
    }

    public void setTransactionsVat(BigDecimal transactionsVat) {
        this.transactionsVat = transactionsVat;
    }

}