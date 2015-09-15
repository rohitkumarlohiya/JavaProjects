package com.estel.dao;

import com.estel.dto.TransactionDetailModel;
import com.estel.dto.TransactionModel;
import com.estel.entity.Transaction;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import java.util.Date;

@Repository
public class TransactionDAOImpl extends GenericDAOImpl<Transaction, Long> implements TransactionDAO {

    @Autowired
    Transaction transaction;

    static List<Timestamp> timestampList;

    public static List<Timestamp> getTimestampList() {
        return timestampList;
    }

    public static void setTimestampList(List<Timestamp> timestampList) {
        TransactionDAOImpl.timestampList = timestampList;
    }

    @Transactional
    public Transaction addTransaction(Transaction Transaction) {
        return (Transaction) this.read(this.create(Transaction));
    }


    @Transactional
    public Transaction getTransactionById(Long Id) {
        return (Transaction) this.read(Id);
    }


    @SuppressWarnings("unchecked")
    @Transactional
    public List<Transaction> listTransactions() {
        return getSession().createQuery("from Transaction").list();
    }


    @Transactional
    public void updateTransaction(Transaction Transaction) {
        getSession().update(Transaction);

    }

    @Transactional
    public List<Transaction> getTransList(String transType, String transId, String agentCode) {
        int flag1 = 0;
        int flag2 = 0;
        StringBuilder hql = new StringBuilder("from Transaction where transactionsTransTypeId = :transType  ");
        if (transId != null && !transId.isEmpty()) {
            hql.append("and transactionsTransactionId =:transId ");
            flag1 = 1;
        }
        if (agentCode != null && !agentCode.isEmpty()) {
            hql.append("and transactionsMerchantDeviceNumber = :agentCode");
            flag2 = 2;
        }

        Query query = getSession().createQuery(hql.toString());
        query.setLong("transType", Long.parseLong(transType));
        if (flag1 == 1) {
            query.setLong("transId", Long.parseLong(transId));
        }
        if (flag2 == 2) {
            query.setString("agentCode", agentCode);
        }

        List<Transaction> transactionList = query.list();
        if (transactionList.size() <= 0) {
            return null;
        }
        return transactionList;
    }

    @Transactional
    public List<Transaction> getBatchHistory(String startDate, String endDate, String deviceNum, String batchNum, String agentId) {

        //call report_mpos_batchhistory('01/09/2012 00:00:00', '23/01/2014 23:59:59','all','all','250', 0)
        String[] fromDate = startDate.split("/");
        startDate = fromDate[1] + "/" + fromDate[0] + "/" + fromDate[2];
        String[] toDate = endDate.split("/");
        endDate = toDate[1] + "/" + toDate[0] + "/" + toDate[2];
        StringBuilder query = new StringBuilder("{call report_mpos_batchhistory(?,?,?,?,?,?)}");
        List<Transaction> batchList = null;
        try {
            Connection con = getSession().connection();
            CallableStatement statement = con.prepareCall(query.toString());

            statement.setString(1, startDate);
            statement.setString(2, endDate);
            if (!deviceNum.isEmpty()) {
                statement.setString(3, deviceNum);
            } else statement.setString(3, "all");

            if (!batchNum.isEmpty()) {
                statement.setString(4, batchNum);
            } else {
                statement.setString(4, "all");
            }
            statement.setString(5, agentId);
            statement.setInt(6, 1);
            ResultSet rs = statement.executeQuery();

            batchList = new ArrayList<Transaction>();
            Transaction trans = null;
            String srcmobile = "";
            String transtype = "";
            String batchno = "";
            BigDecimal amount = BigDecimal.valueOf(0.00);
            String temp = "";


            while (rs.next()) {

                trans = new Transaction();
                srcmobile = rs.getString("srcmobile");
                if (srcmobile == null) {
                    srcmobile = "";
                }

                transtype = rs.getString("transtype");
                if (transtype == null) {
                    transtype = "";
                }
                batchno = rs.getString("batchno");
                if (batchno == null) {
                    batchno = "";
                }

                temp = rs.getString("amount");
                if (temp == null) {
                    trans.setTransactionsValue(amount);
                } else {
                    trans.setTransactionsValue(rs.getBigDecimal("amount"));
                }

                trans.setTransactionsResponseTs(rs.getDate("responsects"));
                ///System.out.println("rs.getTimestamp(\"responsects\")" + rs.getTimestamp("responsects"));
                //System.out.println("rs.getDate(\"responsects\")"+rs.getDate("responsects"));
                trans.setTransactionsMerchantDeviceNumber(srcmobile);
                trans.setTransactionsTransTypeCode(transtype);
                trans.setTransactionsBatchNumber(batchno);
               // trans.setTransactionsValue(amount);
                batchList.add(trans);
            }

        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return batchList;
    }

    @Transactional
    public List<TransactionModel> getTransSummary(String startDate, String endDate, String agentId) {
        //call report_mpos_transactionsummary('01/09/2013 00:00:00', '14/02/2014 23:59:59' ,'250', 0)
        String[] fromDate = startDate.split("/");
        startDate = fromDate[1] + "/" + fromDate[0] + "/" + fromDate[2];
        String[] toDate = endDate.split("/");
        endDate = toDate[1] + "/" + toDate[0] + "/" + toDate[2];
        StringBuilder query = new StringBuilder("{call report_mpos_transactionsummary(?,?,?,?)}");
        List<TransactionModel> transSumList = null;
        try {
            Connection con = getSession().connection();
            CallableStatement statement = con.prepareCall(query.toString());

            statement.setString(1, startDate);
            statement.setString(2, endDate);
            statement.setString(3, agentId);
            statement.setInt(4, 1);
            ResultSet rs = statement.executeQuery();

            transSumList = new ArrayList<TransactionModel>();
            TransactionModel trans = null;
            Date date;
            BigDecimal amount = BigDecimal.valueOf(0.00);
            String temp = "";
            String transtype = "";
            String mobileno = "";

            while (rs.next()) {
                //date = new Date(rs.getDate("responsects"))
                trans = new TransactionModel();
                //System.out.println("rs.getString(\"mobileno\")   " + rs.getString("mobileno"));
                mobileno = rs.getString("mobileno");
                if (mobileno == null) {
                    mobileno = "";
                }
                transtype = rs.getString("transtype");
                if (transtype == null) {
                    transtype = "";
                }

                temp = rs.getString("amount");
                if (temp == null) {
                    trans.setTransactionsValue(amount);
                } else {
                    trans.setTransactionsValue(rs.getBigDecimal("amount"));
                }
                trans.setTransactionsResponseDate(rs.getDate("responsects"));
                trans.setTransactionsMobileNumber(mobileno);
                trans.setTransactionsTransTypeCode(transtype);
                //trans.setTransactionsValue(amount);
                trans.setTransactionscount(rs.getBigDecimal("count1"));
                transSumList.add(trans);
            }

        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return transSumList;
    }

    @Transactional
    public List<TransactionDetailModel> getTransDetail(String startDate, String endDate, String deviceNum, String last4Digit, String AuthNum, String rrn, String transType, String agentId) {

        // call report_mpos_transactiondetail('01/09/2012 00:00:00', '10/02/2014 23:59:59','all','all','all','all','all','250', 0)

        String[] fromDate = startDate.split("/");
        startDate = fromDate[1] + "/" + fromDate[0] + "/" + fromDate[2];
        String[] toDate = endDate.split("/");
        endDate = toDate[1] + "/" + toDate[0] + "/" + toDate[2];
        StringBuilder query = new StringBuilder("{call report_mpos_transactiondetail(?,?,?,?,?,?,?,?,?)}");
        List<TransactionDetailModel> transDetailList = null;
        try {
            Connection con = getSession().connection();
            CallableStatement statement = con.prepareCall(query.toString());

            statement.setString(1, startDate);
            statement.setString(2, endDate);
            if (!deviceNum.isEmpty()) {
                statement.setString(3, deviceNum);
            } else statement.setString(3, "all");

            if (!last4Digit.isEmpty()) {
                statement.setString(4, last4Digit);
            } else {
                statement.setString(4, "all");
            }
            if (!AuthNum.isEmpty()) {
                statement.setString(5, AuthNum);
            } else {
                statement.setString(5, "all");
            }
            if (!rrn.isEmpty()) {
                statement.setString(6, rrn);
            } else {
                statement.setString(6, "all");
            }
            if (!transType.isEmpty()) {
                statement.setString(7, transType);
            } else {
                statement.setString(7, "all");
            }

            statement.setString(8, agentId);
            statement.setInt(9, 1);
            ResultSet rs = statement.executeQuery();

            transDetailList = new ArrayList<TransactionDetailModel>();
            TransactionDetailModel trans = null;
            Date date;
            String srcmobile = "";
            String transtype = "";
            String agentcode = "";
            String last4digit = "";
            String vendorcode = "";
            String rrefno = "";
            String authno = "";
            String batchno = "";
            String resultcode = "";
            String resultdesc = "";
            BigDecimal amount = BigDecimal.valueOf(0.00);
            String temp = "";


            while (rs.next()) {
                //date = new Date(rs.getDate("responsects"))
                //System.out.println("rs.getString(\"srcmobile\")  "+rs.getString("srcmobile"));
                trans = new TransactionDetailModel();
                srcmobile = rs.getString("srcmobile");
                if (srcmobile == null) {
                    srcmobile = "";
                }
                transtype = rs.getString("transtype");
                if (transtype == null) {
                    transtype = "";
                }
                agentcode = rs.getString("agentcode");
                if (agentcode == null) {
                    agentcode = "";
                }
                last4digit = rs.getString("last4digit");
                if (last4digit == null) {
                    last4digit = "";
                }
                vendorcode = rs.getString("vendorcode");
                if (vendorcode == null) {
                    vendorcode = "";
                }
                rrefno = rs.getString("rrefno");
                if (rrefno == null) {
                    rrefno = "";
                }
                authno = rs.getString("authno");
                if (authno == null) {
                    authno = "";
                }
                batchno = rs.getString("batchno");
                if (batchno == null) {
                    batchno = "";
                }
                resultcode = rs.getString("resultcode");
                if (resultcode == null) {
                    resultcode = "";
                }
                resultdesc = rs.getString("resultdesc");
                if (resultdesc == null) {
                    resultdesc = "";
                }

                temp = rs.getString("amount");
                if (temp == null) {
                    trans.setTransactionsValue(amount);
                } else {
                    trans.setTransactionsValue(rs.getBigDecimal("amount"));
                }

                trans.setTransactionsTransactionId(rs.getLong("tid"));
                trans.setTransactionsMerchantMobileNumber(srcmobile);
                trans.setTransactionsTransTypeCode(transtype);
                trans.setTransactionsMerchantCode(agentcode);
                //trans.setTransactionsValue(amount);
                trans.setTransactionsCcLast(last4digit);
                trans.setTransactionsSiResultcode(vendorcode);
                trans.setTransactionsRrnNumber(rrefno);
                trans.setTransactionsAuthNumber(authno);
                trans.setTransactionsBatchNumber(batchno);
                trans.setTransactionsResultcode(resultcode);
                trans.setTransactionsResultDescription(resultdesc);
                trans.setTransactionsRequestTs(rs.getTimestamp("requestcts"));
                trans.setTransactionsResponseTs(rs.getTimestamp("responsects"));
                transDetailList.add(trans);
            }

        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return transDetailList;
    }

}
