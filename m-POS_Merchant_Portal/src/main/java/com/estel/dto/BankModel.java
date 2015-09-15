package com.estel.dto;

import com.estel.entity.Bank;
import com.estel.entity.BankAccountType;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Rohit
 * Date: 9/6/13
 * Time: 1:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class BankModel {

    private String strError = null;
    private Long bankId;
    private String account_name;
    private String account_type;
    private String bank_name;
    private String branch_name;
    private String branch_code;
    private Long routing_number;
    private String account_num1;
    private String account_num2;
    private List<Bank> bankList;
    private String pin;
    List<BankAccountType> accountTypeList;


    public String getStrError() {
        return strError;
    }

    public void setStrError(String strError) {
        this.strError = strError;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    public String getBranch_code() {
        return branch_code;
    }

    public void setBranch_code(String branch_code) {
        this.branch_code = branch_code;
    }

    public Long getRouting_number() {
        return routing_number;
    }

    public void setRouting_number(Long routing_number) {
        this.routing_number = routing_number;
    }

    public String getAccount_num1() {
        return account_num1;
    }

    public void setAccount_num1(String account_num1) {
        this.account_num1 = account_num1;
    }

    public String getAccount_num2() {
        return account_num2;
    }

    public void setAccount_num2(String account_num2) {
        this.account_num2 = account_num2;
    }

    public List<Bank> getBankList() {
        return bankList;
    }

    public void setBankList(List<Bank> bankList) {
        this.bankList = bankList;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public List<BankAccountType> getAccountTypeList() {
        return accountTypeList;
    }

    public void setAccountTypeList(List<BankAccountType> accountTypeList) {
        this.accountTypeList = accountTypeList;
    }
}
