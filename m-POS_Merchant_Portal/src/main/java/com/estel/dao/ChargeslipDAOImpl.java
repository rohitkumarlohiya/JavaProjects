package com.estel.dao;

import com.estel.entity.Chargeslip;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public class ChargeslipDAOImpl extends GenericDAOImpl<Chargeslip, Long> implements ChargeslipDAO {


    @Transactional
    public Chargeslip addChargeslip(Chargeslip Chargeslip) {
        return (Chargeslip) this.read(this.create(Chargeslip));
    }


    @Transactional
    public Chargeslip getChargeslipById(Long Id) {
        return (Chargeslip) this.read(Id);
    }


    @SuppressWarnings("unchecked")
    @Transactional
    public List<Chargeslip> listChargeslips() {
        return getSession().createQuery("from Chargeslip").list();
    }


    @Transactional
    public void updateChargeslip(Chargeslip Chargeslip) {
        getSession().update(Chargeslip);

    }

    @Transactional
    public List<Chargeslip> listChargeslipsFromDateToDate(String txnId, Date dateFrom, Date dateTo,Long merchantId)
    {
        //Long merchantId=2250L;
        String sql = "from Chargeslip where chargeslipMerchantId=:merchantId and (chargeslipTxnId=:txnId or chargeslipTs between :startDate and :endDate)";

        Query query = getSession().createQuery(sql);
        query.setString("txnId", txnId);
        query.setLong("merchantId", merchantId);
        query.setDate("startDate",dateFrom);
        query.setDate("endDate",dateTo);
        List<Chargeslip> chargeslipList = query.list();
        if (chargeslipList.size() <= 0) {
            return null;
        }
        return chargeslipList;
    }

}
