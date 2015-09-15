package com.estel.service;

import com.estel.entity.Contactdetail;

import java.util.List;


public interface ContactdetailService {

	public Contactdetail addContactdetail(Contactdetail Contactdetail);
	public Contactdetail getContactdetailById(Long Id);
	public List<Contactdetail> listContactdetails();
	public void updateContactdetail(Contactdetail Contactdetail);

    public Contactdetail getContactdetailByAgentIdAndBdApplicable(Long agentId,String bdApplicable);

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
                                                          Long agentId,String bdApplicable,String country);


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
                                            String bdApplicable);


}
