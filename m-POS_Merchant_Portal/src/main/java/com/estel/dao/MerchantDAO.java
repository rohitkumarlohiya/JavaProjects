package com.estel.dao;

import com.estel.entity.Merchant;

import java.util.List;

public interface MerchantDAO extends GenericDAO<Merchant, Long> {

    public Merchant addMerchant(Merchant merchant);

    public Merchant getMerchantById(Long Id);

    public List<Merchant> listMerchants();

    public void updateMerchant(Merchant merchant);

    public Merchant getMerchantByAbbr(String abbr);

    public Merchant addMerchantByAbbr(String name, String abbr, Long statusId, String activationCode,String mobileNumber);
}
