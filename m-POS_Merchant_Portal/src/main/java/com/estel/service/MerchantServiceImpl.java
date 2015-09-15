package com.estel.service;

import com.estel.dao.GenericDAOImpl;
import com.estel.dao.MerchantDAO;
import com.estel.entity.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantServiceImpl extends GenericDAOImpl<Merchant, Long> implements
        MerchantService {

	@Autowired
	private MerchantDAO MerchantDAO;


	public Merchant addMerchant(Merchant Merchant) {
		return MerchantDAO.addMerchant(Merchant);
	}


	public Merchant getMerchantById(Long Id) {
		return MerchantDAO.getMerchantById(Id);
	}


	public List<Merchant> listMerchants() {
		return MerchantDAO.listMerchants();
	}


	public void updateMerchant(Merchant mbAgent) {
		MerchantDAO.updateMerchant(mbAgent);
		
	}


    public Merchant getMerchantByAbbr(String abbr)
    {
        return MerchantDAO.getMerchantByAbbr(abbr);
    }


    public Merchant addMerchantByAbbr(String name,String abbr,Long statusId,String activationCode,String mobileNumber)
    {
        return MerchantDAO.addMerchantByAbbr(name, abbr, statusId, activationCode,mobileNumber);
    }

	
}
