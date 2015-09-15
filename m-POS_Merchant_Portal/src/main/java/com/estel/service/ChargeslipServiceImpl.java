package com.estel.service;

import com.estel.dao.ChargeslipDAO;
import com.estel.dao.GenericDAOImpl;
import com.estel.entity.Chargeslip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ChargeslipServiceImpl extends GenericDAOImpl<Chargeslip, Long> implements
        ChargeslipService {

    @Autowired
    private ChargeslipDAO ChargeslipDAO;


    public Chargeslip addChargeslip(Chargeslip Chargeslip) {
        return ChargeslipDAO.addChargeslip(Chargeslip);
    }


    public Chargeslip getChargeslipById(Long Id) {
        return ChargeslipDAO.getChargeslipById(Id);
    }


    public List<Chargeslip> listChargeslips() {
        return ChargeslipDAO.listChargeslips();
    }


    public void updateChargeslip(Chargeslip Chargeslip) {
        ChargeslipDAO.updateChargeslip(Chargeslip);

    }

    public List<Chargeslip> listChargeslipsFromDateToDate(String txnId, Date dateFrom, Date dateTo,Long merchantId)
    {
       return ChargeslipDAO.listChargeslipsFromDateToDate(txnId, dateFrom, dateTo,merchantId);
    }


}
