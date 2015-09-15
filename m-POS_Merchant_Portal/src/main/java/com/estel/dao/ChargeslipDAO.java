package com.estel.dao;

import com.estel.entity.Chargeslip;

import java.util.Date;
import java.util.List;

public interface ChargeslipDAO extends GenericDAO<Chargeslip, Long> {

    public Chargeslip addChargeslip(Chargeslip Chargeslip);
    public Chargeslip getChargeslipById(Long Id);
    public List<Chargeslip> listChargeslips();
    public void updateChargeslip(Chargeslip Chargeslip);

    public List<Chargeslip> listChargeslipsFromDateToDate(String txnId, Date dateFrom, Date dateTo,Long merchantId);
}
