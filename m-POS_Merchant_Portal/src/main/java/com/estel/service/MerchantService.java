package com.estel.service;

import com.estel.entity.Merchant;

import java.util.List;


public interface MerchantService {

	public Merchant addMerchant(Merchant Merchant);
	public Merchant getMerchantById(Long Id);
	public List<Merchant> listMerchants();
	public void updateMerchant(Merchant mbAgent);

    public Merchant getMerchantByAbbr(String abbr);
    public Merchant addMerchantByAbbr(String name,String abbr,Long statusId,String activationCode,String mobileNumber);
}
