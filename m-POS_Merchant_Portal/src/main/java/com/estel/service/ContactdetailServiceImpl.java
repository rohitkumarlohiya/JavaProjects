package com.estel.service;

import com.estel.dao.ContactdetailDAO;
import com.estel.dao.GenericDAOImpl;
import com.estel.entity.Contactdetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactdetailServiceImpl extends GenericDAOImpl<Contactdetail, Long> implements
        ContactdetailService {

	@Autowired
	private ContactdetailDAO ContactdetailDAO;

	public Contactdetail addContactdetail(Contactdetail Contactdetail) {
		return ContactdetailDAO.addContactdetail(Contactdetail);
	}

	public Contactdetail getContactdetailById(Long Id) {
		return ContactdetailDAO.getContactdetailById(Id);
	}

	public List<Contactdetail> listContactdetails() {
		return ContactdetailDAO.listContactdetails();
	}

	public void updateContactdetail(Contactdetail Contactdetail) {
		ContactdetailDAO.updateContactdetail(Contactdetail);
		
	}


    public Contactdetail getContactdetailByAgentIdAndBdApplicable(Long agentId,String bdApplicable) {

      return   ContactdetailDAO.getContactdetailByAgentIdAndBdApplicable(agentId,bdApplicable);

    }


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
        return  ContactdetailDAO.addContactdetailByAgentIdAndBdApplicable(contactName, addr1, city, zipCode, state, phone, fax, mobile, email, panNumber, agentId, bdApplicable,country);
    }


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
                                                        Long agentId,
                                                        String bdApplicable)
    {
        ContactdetailDAO.updateContactdetailByAgentIdAndBdApplicable(id, contactName, gender, addr1, addr2, city, zipCode, state, country, phone, fax, mobile, email, panNumber, agentId,bdApplicable);
    }
	
}
