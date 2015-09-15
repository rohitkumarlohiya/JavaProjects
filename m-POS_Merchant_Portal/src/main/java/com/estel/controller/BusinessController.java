package com.estel.controller;

import com.estel.dto.BusinessInfoModel;
import com.estel.entity.*;
import com.estel.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


@Controller
public class BusinessController {

    @Autowired
    BusinessService businessService;

    @Autowired
    CityService cityService;

    @Autowired
    StateService stateService;

    @Autowired
    BusinessOwnerTypeService businessOwnerTypeService;

    @Autowired
    BusinessTypeService businessTypeService;

    @Autowired
    BusinessCategoryService businessCategoryService;

    @Autowired
    ContactdetailService contactdetailService;

    @Autowired
    UserService userService;

    @Autowired
    MerchantService merchantService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    AudittrailService audittrailService;


    @RequestMapping("/merchantBusinessInfoAction")
    public String merchantBusinessInfoAction(HttpServletRequest request, Map<String, Object> map) {

        HttpSession session = request.getSession();
        Long agentId = (Long) session.getAttribute("agentId");

        if(agentId == null)
            return "redirect:/Mpos.do";

        List<State> stateList = stateService.listStates();
        List<BusinessOwnerType> businessOwnerTypeList = businessOwnerTypeService.listBusinessOwnerTypes();
        List<BusinessType> businessTypeList = businessTypeService.listBusinessTypes();
        List<BusinessCategory> businessCategoryList = businessCategoryService.listBusinessCategorys();

        BusinessInfoModel businessInfoModel = new BusinessInfoModel();

        businessInfoModel.setBusinessOwnerTypeList(businessOwnerTypeList);
        businessInfoModel.setBusinessTypeList(businessTypeList);
        businessInfoModel.setBusinessCategoryList(businessCategoryList);
        businessInfoModel.setStateList(stateList);

        Business mbBusiness = businessService.getBusinessByAgentId(agentId);

        if (mbBusiness != null) {
            //System.out.println("success==> mbBusiness is not null ");

            businessInfoModel.setLegalBusinessName(mbBusiness.getBusinessName());
            businessInfoModel.setMarketingName(mbBusiness.getBusinessMarkName());
            businessInfoModel.setOwnershipType(mbBusiness.getBusinessOwnerType().getBusinessOwnerTypeId());
            businessInfoModel.setBusinessCategory(mbBusiness.getBusinessCategory().getBusinessCategoryId());
            businessInfoModel.setBusinessType(mbBusiness.getBusinessType().getBusinessTypeId());

            Contactdetail businessAddress = contactdetailService.getContactdetailByAgentIdAndBdApplicable(agentId, "3");

            if (businessAddress != null) {
                businessInfoModel.setBusinessAddress(businessAddress.getContactdetailsAddLine1());
                businessInfoModel.setCityBusinessAddr(businessAddress.getContactdetailsCity());
                businessInfoModel.setStateBusinessAddr(businessAddress.getContactdetailsState());
                businessInfoModel.setZipCodeBusinessAddr(businessAddress.getContactdetailsPostcode());
                businessInfoModel.setBusinessPhoneNumber(businessAddress.getContactdetailsPhoneNumber());
                businessInfoModel.setEmailBusiness(businessAddress.getContactdetailsEmail());
                businessInfoModel.setSameBusinessAddress(businessAddress.getContactdetailsFaxNumber().equalsIgnoreCase("yes"));
                businessInfoModel.setPanNumberBusiness(businessAddress.getContactdetailsPan());
            }

            Contactdetail registeredOffAddress = contactdetailService.getContactdetailByAgentIdAndBdApplicable(agentId, "4");

            if (registeredOffAddress != null) {
                businessInfoModel.setRegisteredOfficeAddress(registeredOffAddress.getContactdetailsAddLine1());
                businessInfoModel.setStateRegisterOfficeAddr(registeredOffAddress.getContactdetailsState());
                businessInfoModel.setCityRegisterOfficeAddr(registeredOffAddress.getContactdetailsCity());
                businessInfoModel.setZipCodeRegisterOfficeAddrr(registeredOffAddress.getContactdetailsPostcode());
                businessInfoModel.setRegisterOfficePhoneNumber(registeredOffAddress.getContactdetailsPhoneNumber());
            }

            map.put("businessInfoModel", businessInfoModel);

            return "merchant_business_info_view";

        } else {
            //System.out.println("success==> mbBusiness is null ");

            User mbUserLogin = userService.getUserByAgentId(agentId);
            businessInfoModel.setEmailBusiness(mbUserLogin.getUserMailId());

            map.put("businessInfoModel", businessInfoModel);

            return "merchant_business_info_form";
        }
    }

    @RequestMapping(value = "/merchantBusinessInfoEdit", method = RequestMethod.POST)
    public String merchantBusinessInfoEdit(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map,
                                           @ModelAttribute("businessInfoModel") BusinessInfoModel businessInfoModel, BindingResult result) {

        HttpSession session = request.getSession();
        Long agentId = (Long)session.getAttribute("agentId");

        if(agentId == null)
            return "redirect:/Mpos.do";

        List<State> stateList = null;
        List<BusinessOwnerType> businessOwnerTypeList = null;
        List<BusinessType> businessTypeList = null;
        List<BusinessCategory> businessCategoryList = null;

        try {
            stateList = stateService.listStates();
            businessOwnerTypeList = businessOwnerTypeService.listBusinessOwnerTypes();
            businessTypeList = businessTypeService.listBusinessTypes();
            businessCategoryList = businessCategoryService.listBusinessCategorys();
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        businessInfoModel.setBusinessOwnerTypeList(businessOwnerTypeList);
        businessInfoModel.setBusinessTypeList(businessTypeList);
        businessInfoModel.setBusinessCategoryList(businessCategoryList);
        businessInfoModel.setStateList(stateList);

        Business mbBusiness = null;
        try {
            mbBusiness = businessService.getBusinessByAgentId(agentId);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        if(mbBusiness != null)
        {
        businessInfoModel.setLegalBusinessName(mbBusiness.getBusinessName());
        businessInfoModel.setMarketingName(mbBusiness.getBusinessMarkName());
        businessInfoModel.setOwnershipType(mbBusiness.getBusinessOwnerType().getBusinessOwnerTypeId());
        businessInfoModel.setBusinessCategory(mbBusiness.getBusinessCategory().getBusinessCategoryId());
        businessInfoModel.setBusinessType(mbBusiness.getBusinessType().getBusinessTypeId());
        }
        Long businessStateId = null;
        Long residentialStateId = null;

        Contactdetail businessAddress = null;
        try {
            businessAddress = contactdetailService.getContactdetailByAgentIdAndBdApplicable(agentId, "3");
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        if (businessAddress != null) {
            businessInfoModel.setBusinessAddress(businessAddress.getContactdetailsAddLine1());
            businessInfoModel.setCityBusinessAddr(businessAddress.getContactdetailsCity());
            businessInfoModel.setStateBusinessAddr(businessAddress.getContactdetailsState());
            businessInfoModel.setZipCodeBusinessAddr(businessAddress.getContactdetailsPostcode());
            businessInfoModel.setBusinessPhoneNumber(businessAddress.getContactdetailsPhoneNumber());
            businessInfoModel.setEmailBusiness(businessAddress.getContactdetailsEmail());
            businessInfoModel.setSameBusinessAddress(businessAddress.getContactdetailsFaxNumber().equalsIgnoreCase("yes"));
            businessInfoModel.setPanNumberBusiness(businessAddress.getContactdetailsPan());

            businessStateId = Long.parseLong(businessAddress.getContactdetailsState());
        }

        Contactdetail registeredOffAddress = null;
        try {
            registeredOffAddress = contactdetailService.getContactdetailByAgentIdAndBdApplicable(agentId, "4");
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        if (registeredOffAddress != null) {
            businessInfoModel.setRegisteredOfficeAddress(registeredOffAddress.getContactdetailsAddLine1());
            businessInfoModel.setStateRegisterOfficeAddr(registeredOffAddress.getContactdetailsState());
            businessInfoModel.setCityRegisterOfficeAddr(registeredOffAddress.getContactdetailsCity());
            businessInfoModel.setZipCodeRegisterOfficeAddrr(registeredOffAddress.getContactdetailsPostcode());
            businessInfoModel.setRegisterOfficePhoneNumber(registeredOffAddress.getContactdetailsPhoneNumber());

            residentialStateId = Long.parseLong(registeredOffAddress.getContactdetailsState());
        }

        List<City> cityList = null;
        List<City> cityList2 = null;
        try {
            cityList = cityService.listAllActiveCityByStateId(businessStateId);
            cityList2 = cityService.listAllActiveCityByStateId(residentialStateId);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        businessInfoModel.setCityList(cityList);
        businessInfoModel.setCityList2(cityList2);
        businessInfoModel.setMerchantId(agentId);

        return "merchant_business_info_edit";
    }

    @RequestMapping(value = "/merchantBusinessInfoAdd", method = RequestMethod.POST)
    public String merchantBusinessInfoAdd(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map,
                                           @ModelAttribute("businessInfoModel") BusinessInfoModel businessInfoModel, BindingResult result) {

        HttpSession session = request.getSession();
        Long agentId = (Long) session.getAttribute("agentId");

        if(agentId == null)
            return "redirect:/Mpos.do";


        User user = userService.getUserByAgentId(agentId);
        businessInfoModel.setEmailBusiness(user.getUserMailId());


        List<State> stateList = null;
        List<BusinessOwnerType> businessOwnerTypeList = null;
        List<BusinessType> businessTypeList = null;
        List<BusinessCategory> businessCategoryList = null;

        try {
            stateList = stateService.listStates();
            businessOwnerTypeList = businessOwnerTypeService.listBusinessOwnerTypes();
            businessTypeList = businessTypeService.listBusinessTypes();
            businessCategoryList = businessCategoryService.listBusinessCategorys();
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        businessInfoModel.setBusinessOwnerTypeList(businessOwnerTypeList);
        businessInfoModel.setBusinessTypeList(businessTypeList);
        businessInfoModel.setBusinessCategoryList(businessCategoryList);
        businessInfoModel.setStateList(stateList);

        String sameAddress = "no";
        if (businessInfoModel.isSameBusinessAddress())
        {
            sameAddress = "yes";
            businessInfoModel.setRegisteredOfficeAddress(businessInfoModel.getBusinessAddress());
            businessInfoModel.setStateRegisterOfficeAddr(businessInfoModel.getStateBusinessAddr());
            businessInfoModel.setCityRegisterOfficeAddr(businessInfoModel.getCityBusinessAddr());
            businessInfoModel.setRegisterOfficePhoneNumber(businessInfoModel.getBusinessPhoneNumber());
            businessInfoModel.setZipCodeRegisterOfficeAddrr(businessInfoModel.getZipCodeBusinessAddr());
        }
        else
        {
            sameAddress = "no";
        }

        if (request.getParameter("pageAction") != null && request.getParameter("pageAction").toString().trim().equalsIgnoreCase("update")) {


            Business business = businessService.getBusinessByAgentId(agentId);
            String old_val = "Legal Business Name ="+business.getBusinessName()+
                    ",Marketing Name ="+business.getBusinessMarkName()+
                    ",Ownership Type ="+business.getBusinessOwnerType().getBusinessOwnerTypeDescription()+
                    ",Business Category ="+business.getBusinessCategory().getBusinessCategoryDescription()+
                    ",Business Type ="+business.getBusinessType().getBusinessTypeDescription();

            try {
                businessService.updateBusinessByAgentId(
                        businessInfoModel.getLegalBusinessName(),
                        businessInfoModel.getMarketingName(),
                        businessInfoModel.getOwnershipType(),
                        businessInfoModel.getBusinessType(),
                        businessInfoModel.getBusinessCategory(),
                        agentId
                );

                Contactdetail businessAddress = contactdetailService.getContactdetailByAgentIdAndBdApplicable(agentId, "3");

                if (businessAddress != null) {


                    old_val += ",Business Address ="+businessAddress.getContactdetailsAddLine1()+
                            ",State ="+businessAddress.getContactdetailsState()+
                            ",City ="+businessAddress.getContactdetailsCity()+
                            ",PIN/ZIP Code ="+businessAddress.getContactdetailsPostcode()+
                            ",Business Contact Number ="+businessAddress.getContactdetailsPhoneNumber()+
                            ",Business Address Same as Registered Office Address ="+businessAddress.getContactdetailsFaxNumber();


                    businessAddress.setContactdetailsContactName(businessInfoModel.getLegalBusinessName());
                    businessAddress.setContactdetailsAddLine1(businessInfoModel.getBusinessAddress());
                    businessAddress.setContactdetailsCity(businessInfoModel.getCityBusinessAddr());
                    businessAddress.setContactdetailsPostcode(businessInfoModel.getZipCodeBusinessAddr());
                    businessAddress.setContactdetailsState(businessInfoModel.getStateBusinessAddr());
                    businessAddress.setContactdetailsPhoneNumber(businessInfoModel.getBusinessPhoneNumber());
                    businessAddress.setContactdetailsFaxNumber(sameAddress);
                    businessAddress.setContactdetailsEmail(businessInfoModel.getEmailBusiness());
                    businessAddress.setContactdetailsPan(businessInfoModel.getPanNumberBusiness());
                    businessAddress.setMerchant(merchantService.getMerchantById(agentId));

                    contactdetailService.updateContactdetail(businessAddress);
                }

                Contactdetail registeredOffAddress = contactdetailService.getContactdetailByAgentIdAndBdApplicable(agentId, "4");

                if (registeredOffAddress != null) {


                    old_val += ",Registered Office Address ="+registeredOffAddress.getContactdetailsAddLine1()+
                            ",State ="+registeredOffAddress.getContactdetailsState()+
                            ",City ="+registeredOffAddress.getContactdetailsCity()+
                            ",PIN/ZIP Code ="+registeredOffAddress.getContactdetailsPostcode()+
                            ",Contact Number ="+ registeredOffAddress.getContactdetailsPhoneNumber()+
                            ",Email Address ="+businessAddress.getContactdetailsEmail()+
                            ",PAN No/Income Tax No ="+businessAddress.getContactdetailsPan();

                    registeredOffAddress.setContactdetailsContactName(businessInfoModel.getLegalBusinessName());
                    registeredOffAddress.setContactdetailsAddLine1(businessInfoModel.getRegisteredOfficeAddress());
                    registeredOffAddress.setContactdetailsCity(businessInfoModel.getCityRegisterOfficeAddr());
                    registeredOffAddress.setContactdetailsPostcode(businessInfoModel.getZipCodeRegisterOfficeAddrr());
                    registeredOffAddress.setContactdetailsState(businessInfoModel.getStateRegisterOfficeAddr());
                    registeredOffAddress.setContactdetailsPhoneNumber(businessInfoModel.getRegisterOfficePhoneNumber());
                    registeredOffAddress.setContactdetailsFaxNumber(sameAddress);
                    registeredOffAddress.setMerchant(merchantService.getMerchantById(agentId));

                    contactdetailService.updateContactdetail(registeredOffAddress);
                }
            } catch (Exception e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

            String ipAddress = (request).getHeader("X-FORWARDED-FOR");
            if (ipAddress == null) {
                ipAddress = (request).getRemoteAddr();
            }

            String new_val = "Legal Business Name ="+businessInfoModel.getLegalBusinessName()+
                    ",Marketing Name ="+businessInfoModel.getMarketingName()+
                    ",Ownership Type ="+businessOwnerTypeService.getBusinessOwnerTypeById(businessInfoModel.getOwnershipType()).getBusinessOwnerTypeDescription()+
                    ",Business Category ="+businessCategoryService.getBusinessCategoryById(businessInfoModel.getBusinessCategory()).getBusinessCategoryDescription()+
                    ",Business Type ="+businessTypeService.getBusinessTypeById(businessInfoModel.getBusinessType()).getBusinessTypeDescription()+
                    ",Business Address ="+businessInfoModel.getBusinessAddress()+
                    ",State ="+businessInfoModel.getStateBusinessAddr()+
                    ",City ="+businessInfoModel.getCityBusinessAddr()+
                    ",PIN/ZIP Code ="+businessInfoModel.getZipCodeBusinessAddr()+
                    ",Business Contact Number ="+businessInfoModel.getBusinessPhoneNumber()+
                    ",Business Address Same as Registered Office Address ="+sameAddress+
                    ",Registered Office Address ="+businessInfoModel.getRegisteredOfficeAddress()+
                    ",State ="+businessInfoModel.getStateRegisterOfficeAddr()+
                    ",City ="+businessInfoModel.getCityRegisterOfficeAddr()+
                    ",PIN/ZIP Code ="+businessInfoModel.getZipCodeRegisterOfficeAddrr()+
                    ",Contact Number ="+ businessInfoModel.getRegisterOfficePhoneNumber()+
                    ",Email Address ="+businessInfoModel.getEmailBusiness()+
                    ",PAN No/Income Tax No ="+businessInfoModel.getPanNumberBusiness();


            audittrailService.addAudittrailByUserId(userService.getUserByAgentId(agentId).getUserId(), "Update Business Info", "Update", ipAddress, "Business Info Updated", old_val, new_val);

        }
        else
        {
            try {
                businessService.addBusinessByAgentId(
                        businessInfoModel.getLegalBusinessName(),
                        businessInfoModel.getMarketingName(),
                        businessInfoModel.getOwnershipType(),
                        businessInfoModel.getBusinessType(),
                        businessInfoModel.getBusinessCategory(),
                        agentId
                );

                contactdetailService.addContactdetailByAgentIdAndBdApplicable(businessInfoModel.getLegalBusinessName(),
                        businessInfoModel.getBusinessAddress(),
                        businessInfoModel.getCityBusinessAddr(),
                        businessInfoModel.getZipCodeBusinessAddr(),
                        businessInfoModel.getStateBusinessAddr(),
                        businessInfoModel.getBusinessPhoneNumber(),
                        sameAddress,
                        null,
                        businessInfoModel.getEmailBusiness(),
                        businessInfoModel.getPanNumberBusiness(),
                        agentId, "3",null);

                contactdetailService.addContactdetailByAgentIdAndBdApplicable(businessInfoModel.getLegalBusinessName(),
                        businessInfoModel.getRegisteredOfficeAddress(),
                        businessInfoModel.getCityRegisterOfficeAddr(),
                        businessInfoModel.getZipCodeRegisterOfficeAddrr(),
                        businessInfoModel.getStateRegisterOfficeAddr(),
                        businessInfoModel.getRegisterOfficePhoneNumber(),
                        sameAddress,
                        null,
                        null,
                        null,
                        agentId, "4",null);
            } catch (Exception e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }


            String ipAddress = (request).getHeader("X-FORWARDED-FOR");
            if (ipAddress == null) {
                ipAddress = (request).getRemoteAddr();
            }

            String old_val = null;
            String new_val = "Legal Business Name ="+businessInfoModel.getLegalBusinessName()+
            ",Marketing Name ="+businessInfoModel.getMarketingName()+
            ",Ownership Type ="+businessOwnerTypeService.getBusinessOwnerTypeById(businessInfoModel.getOwnershipType()).getBusinessOwnerTypeDescription()+
            ",Business Category ="+businessCategoryService.getBusinessCategoryById(businessInfoModel.getBusinessCategory()).getBusinessCategoryDescription()+
            ",Business Type ="+businessTypeService.getBusinessTypeById(businessInfoModel.getBusinessType()).getBusinessTypeDescription()+
            ",Business Address ="+businessInfoModel.getBusinessAddress()+
            ",State ="+businessInfoModel.getStateBusinessAddr()+
            ",City ="+businessInfoModel.getCityBusinessAddr()+
            ",PIN/ZIP Code ="+businessInfoModel.getZipCodeBusinessAddr()+
            ",Business Contact Number ="+businessInfoModel.getBusinessPhoneNumber()+
            ",Business Address Same as Registered Office Address ="+sameAddress+
            ",Registered Office Address ="+businessInfoModel.getRegisteredOfficeAddress()+
            ",State ="+businessInfoModel.getStateRegisterOfficeAddr()+
            ",City ="+businessInfoModel.getCityRegisterOfficeAddr()+
            ",PIN/ZIP Code ="+businessInfoModel.getZipCodeRegisterOfficeAddrr()+
            ",Contact Number ="+ businessInfoModel.getRegisterOfficePhoneNumber()+
            ",Email Address ="+businessInfoModel.getEmailBusiness()+
            ",PAN No/Income Tax No ="+businessInfoModel.getPanNumberBusiness();

            audittrailService.addAudittrailByUserId(userService.getUserByAgentId(agentId).getUserId(), "Add Business Info", "Add", ipAddress, "Business Info Added", old_val, new_val);


        }

        session.setAttribute("change_business_info_msg",messageSource.getMessage("profile.update.success", null, "default-profile.update.success", null));

        map.put("businessInfoModel", businessInfoModel);
        return "merchant_business_info_view";
    }
}
