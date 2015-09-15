package com.estel.dao;

import com.estel.entity.Contactdetail;
import com.estel.entity.Merchant;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class ContactdetailDAOImpl extends GenericDAOImpl<Contactdetail, Long> implements ContactdetailDAO {

    @Autowired
    private MerchantDAO merchantDAO;

    @Autowired
    private Contactdetail contactdetail;

    @Autowired
    private CityDAO cityDAO;

    @Autowired
    private StateDAO stateDAO;

    @Autowired
    private CountryDAO countryDAO;


	@Transactional
	public Contactdetail addContactdetail(Contactdetail Contactdetail) {
		return (Contactdetail) this.read(this.create(Contactdetail));
	}


	@Transactional
	public Contactdetail getContactdetailById(Long Id) {
		return (Contactdetail) this.read(Id);
	}


	@SuppressWarnings("unchecked")
	@Transactional
	public List<Contactdetail> listContactdetails() {
		return getSession().createQuery("from Contactdetail").list();
	}


	@Transactional
	public void updateContactdetail(Contactdetail Contactdetail) {
		getSession().update(Contactdetail);
		
	}

    @SuppressWarnings("unchecked")
    @Transactional
    public Contactdetail getContactdetailByAgentIdAndBdApplicable(Long agentId,String bdApplicable)
    {
        Merchant merchant = merchantDAO.getMerchantById(agentId);

        String sql = "from Contactdetail where merchant=:merchant and contactdetailsBdApplicable=:bdApplicable";
        Query query = getSession().createQuery(sql);
        query.setEntity("merchant", merchant);
        query.setString("bdApplicable",bdApplicable);
        List<Contactdetail> contactdetailList = query.list();
        if (contactdetailList.size() <= 0) {
            return null;
        }
        return contactdetailList.get(0);
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public Contactdetail addContactdetailByAgentIdAndBdApplicable(String contactName,
                                                            String addr1,
                                                            String city,
                                                            String zipCode,
                                                            String state,
                                                            String phone,
                                                            String fax,
                                                            String mobile,
                                                            String email,
                                                            String panNumber,
                                                            Long agentId,String bdApplicable,String country)
    {
        //set mandatory fields
        /*contactdetails_merchant_id
                contactdetails_ts*/
        contactdetail.setMerchant(merchantDAO.getMerchantById(agentId));
        contactdetail.setContactdetailsTs(new java.sql.Date(System.currentTimeMillis()));
        //set mandatory fields

        contactdetail.setContactdetailsContactName(contactName);
        contactdetail.setContactdetailsAddLine1(addr1);
        contactdetail.setContactdetailsCity(city);
        contactdetail.setContactdetailsPostcode(zipCode);
        contactdetail.setContactdetailsState(state);
        contactdetail.setContactdetailsPhoneNumber(phone);
        contactdetail.setContactdetailsFaxNumber(fax);
        contactdetail.setContactdetailsMobileNumber(mobile);
        contactdetail.setContactdetailsEmail(email);
        contactdetail.setContactdetailsPan(panNumber);
        contactdetail.setContactdetailsBdApplicable(bdApplicable);
        if(country != null)
        contactdetail.setContactdetailsCountry(country);

        return (Contactdetail) this.read(this.create(contactdetail));
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public void updateContactdetailByAgentIdAndBdApplicable(Long id,
                                                        String contactName,
                                                        String gender,
                                                        String addr1,
                                                        String addr2,
                                                        String city,
                                                        String zipCode,
                                                        String state,
                                                        String country,
                                                        String phone,
                                                        String fax,
                                                        String mobile,
                                                        String email,
                                                        String panNumber,
                                                        Long agentId,String bdApplicable)
    {
        contactdetail =  this.read(id);
        contactdetail.setContactdetailsContactName(contactName);
        contactdetail.setContactdetailsGender(gender);
        contactdetail.setContactdetailsAddLine1(addr1);
        contactdetail.setContactdetailsAddLine2(addr2);
        contactdetail.setContactdetailsCity(city);
        contactdetail.setContactdetailsPostcode(zipCode);
        contactdetail.setContactdetailsState(state);
        contactdetail.setContactdetailsCountry(country);
        contactdetail.setContactdetailsPhoneNumber(phone);
        contactdetail.setContactdetailsFaxNumber(fax);
        contactdetail.setContactdetailsMobileNumber(mobile);
        contactdetail.setContactdetailsEmail(email);
        contactdetail.setContactdetailsPan(panNumber);
        contactdetail.setContactdetailsBdApplicable(bdApplicable);
        contactdetail.setMerchant(merchantDAO.getMerchantById(agentId));

        getSession().update(contactdetail);
    }
}
