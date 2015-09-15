package com.estel.controller;

import com.estel.dto.UserModel;
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
import java.io.IOException;
import java.util.List;
import java.util.Map;


@Controller
public class ProfileController {

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

    @RequestMapping("/merchantProfileAction")
    public String merchantProfileAction(HttpServletRequest request,HttpServletResponse response, Map<String, Object> map) {


        UserModel userModel  = new UserModel();

        HttpSession session = request.getSession();
        Long agentId = (Long)session.getAttribute("agentId");

        if(agentId == null)
            return "redirect:/Mpos.do";

        ////Added For sequence

        try {
            Business business = businessService.getBusinessByAgentId(agentId);
            if(business == null)
            {
                session.setAttribute("sequence_info_msg",messageSource.getMessage("sequence.business.info.msg", null, "default-sequence.business.info.msg", null));
                session.setAttribute("stepNo","1");
                response.sendRedirect("merchantBusinessInfoAction.do");
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        ////Added For sequence


        try {
            userModel.setStateList(stateService.listStates());

            Contactdetail residentialAddress = contactdetailService.getContactdetailByAgentIdAndBdApplicable(agentId, "1");

            Contactdetail permanentAddress = contactdetailService.getContactdetailByAgentIdAndBdApplicable(agentId, "2");

            if(residentialAddress != null && (residentialAddress.getContactdetailsContactName() == null || residentialAddress.getContactdetailsContactName().equalsIgnoreCase("")))
            {
                userModel.setMobileNumber(residentialAddress.getContactdetailsMobileNumber());

                String names[] = merchantService.getMerchantById(agentId).getMerchantName().split("-");
                userModel.setContactName(names[0] +" "+names[1]);

                map.put("userModel", userModel);
                return "merchant_profile_form";
            }
            else
            {
                //residentialAddress
                userModel.setContactName(residentialAddress.getContactdetailsContactName());
                userModel.setResidentialAddress(residentialAddress.getContactdetailsAddLine1());
                userModel.setResidentialState(residentialAddress.getContactdetailsState());
                //userModel.setResidentialCity(residentialAddress.getMbaddCity());
                userModel.setResidentialCity(residentialAddress.getContactdetailsCity());
                userModel.setResidentialZipCode(residentialAddress.getContactdetailsPostcode());
                userModel.setMobileNumber(residentialAddress.getContactdetailsMobileNumber());
                userModel.setAlternateMobileNumber(residentialAddress.getContactdetailsPhoneNumber());

                //permanentAddress
                userModel.setPermanentAddress(permanentAddress.getContactdetailsAddLine1());
                userModel.setPermanentState(permanentAddress.getContactdetailsState());
                //userModel.setPermanentCity(permanentAddress.getMbaddCity());
                userModel.setPermanentCity(permanentAddress.getContactdetailsCity());
                userModel.setPermanentZipCode(permanentAddress.getContactdetailsPostcode());

                map.put("userModel", userModel);
                return  "merchant_profile_view";
            }
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return "redirect:/Mpos.do";
    }

    @RequestMapping(value = "/merchantProfileAdd", method = RequestMethod.POST)
    public String merchantProfileAdd(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map,
                                          @ModelAttribute("userModel") UserModel userModel, BindingResult result) {

        HttpSession session = request.getSession();
        Long agentId = (Long) session.getAttribute("agentId");

        if(agentId == null)
            return "redirect:/Mpos.do";

        List<City> cityList = null;
        List<State> stateList = null;
        try {
            cityList = cityService.listCitys();
            stateList = stateService.listStates();
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        userModel.setCityList(cityList);
        userModel.setStateList(stateList);

        String sameAddress = "no";
        if (userModel.isSameAddressOffnHome())
        {
            sameAddress = "yes";

            userModel.setPermanentAddress(userModel.getResidentialAddress());
            userModel.setPermanentState(userModel.getResidentialState());
            userModel.setPermanentCity(userModel.getResidentialCity());
            userModel.setPermanentZipCode(userModel.getResidentialZipCode());

        }
        else
        {
            sameAddress = "no";
        }

        String old_val = "";

        Contactdetail residentialAddress = null;
        try {
            residentialAddress = contactdetailService.getContactdetailByAgentIdAndBdApplicable(agentId, "1");
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        if(residentialAddress != null)
        {
            old_val += "Contact Person Name ="+residentialAddress.getContactdetailsContactName()+
                    ",Residential Address ="+residentialAddress.getContactdetailsAddLine1()+
                    ",State ="+residentialAddress.getContactdetailsState()+
                    ",City ="+residentialAddress.getContactdetailsCity()+
                    ",PIN/ZIP Code ="+residentialAddress.getContactdetailsPostcode()+
                    ",Registered Mobile Number ="+residentialAddress.getContactdetailsMobileNumber()+
                    ",Alternate Contact Number ="+residentialAddress.getContactdetailsPhoneNumber()+
                    ",Permanent Address same as Residential Address ="+residentialAddress.getContactdetailsFaxNumber();

                    //readonly field will not modify
                    //userModel.setContactName(residentialAddress.getContactdetailsContactName());
                    //userModel.setMobileNumber(residentialAddress.getContactdetailsMobileNumber());

                    contactdetailService.updateContactdetailByAgentIdAndBdApplicable(residentialAddress.getContactdetailsId(), userModel.getContactName(), residentialAddress.getContactdetailsGender(), userModel.getResidentialAddress(), sameAddress, userModel.getResidentialCity(), userModel.getResidentialZipCode(), userModel.getResidentialState(), residentialAddress.getContactdetailsCountry(), userModel.getAlternateMobileNumber(), sameAddress, userModel.getMobileNumber(), userModel.getEmail(), null, agentId, "1");
        }

        Contactdetail permanentAddress = null;
        try {
            permanentAddress = contactdetailService.getContactdetailByAgentIdAndBdApplicable(agentId, "2");
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        if(permanentAddress != null)
        {

            old_val += ",Permanent Address ="+permanentAddress.getContactdetailsAddLine1()+
                    ",State ="+permanentAddress.getContactdetailsState()+
                    ",City ="+permanentAddress.getContactdetailsCity()+
                    ",PIN/ZIP Code ="+permanentAddress.getContactdetailsPostcode();

            contactdetailService.updateContactdetailByAgentIdAndBdApplicable(permanentAddress.getContactdetailsId(), userModel.getContactName(), permanentAddress.getContactdetailsGender(), userModel.getPermanentAddress(), sameAddress, userModel.getPermanentCity(), userModel.getPermanentZipCode(), userModel.getPermanentState(), permanentAddress.getContactdetailsCountry(), userModel.getAlternateMobileNumber(), sameAddress, userModel.getMobileNumber(), userModel.getEmail(), null, agentId, "2");
        }

        try {
            User user = userService.getUserByAgentId(agentId);

            user.setUserFirstName(userModel.getContactName());
            userService.updateUser(user);
            session.setAttribute("UserName",userModel.getContactName());
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        String ipAddress = (request).getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = (request).getRemoteAddr();
        }

        String new_val = "Contact Person Name ="+userModel.getContactName()+
                ",Residential Address ="+userModel.getResidentialAddress()+
                ",State ="+userModel.getResidentialState()+
                ",City ="+userModel.getResidentialCity()+
                ",PIN/ZIP Code ="+userModel.getResidentialZipCode()+
                ",Registered Mobile Number ="+userModel.getMobileNumber()+
                ",Alternate Contact Number ="+userModel.getAlternateMobileNumber()+
                ",Permanent Address same as Residential Address ="+sameAddress+
                ",Permanent Address ="+userModel.getPermanentAddress()+
                ",State ="+userModel.getPermanentState()+
                ",City ="+userModel.getPermanentCity()+
                ",PIN/ZIP Code ="+userModel.getPermanentZipCode();

        audittrailService.addAudittrailByUserId(userService.getUserByAgentId(agentId).getUserId(), "Update Profile Info", "Update", ipAddress, "Profile Info Updated", old_val, new_val);

        session.setAttribute("change_profile_msg",messageSource.getMessage("profile.update.success", null, "default-profile.update.success", null));
        map.put("userModel", userModel);
        return "merchant_profile_view";
    }


    @RequestMapping(value = "/merchantProfileEdit", method = RequestMethod.POST)
    public String merchantProfileEdit(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map,
                                           @ModelAttribute("userModel") UserModel userModel, BindingResult result) {

        HttpSession session = request.getSession();
        Long agentId = (Long) session.getAttribute("agentId");

        if(agentId == null)
            return "redirect:/Mpos.do";

        try {
            String residentialState = null;
            String permanentState = null;

            Contactdetail residentialAddress = contactdetailService.getContactdetailByAgentIdAndBdApplicable(agentId, "1");
            if(residentialAddress != null)
            {
                userModel.setContactName(residentialAddress.getContactdetailsContactName());
                userModel.setResidentialAddress(residentialAddress.getContactdetailsAddLine1());
                userModel.setResidentialState(residentialAddress.getContactdetailsState());
                //userModel.setResidentialCity(residentialAddress.getMbaddCity());
                userModel.setResidentialCity(residentialAddress.getContactdetailsCity());
                userModel.setResidentialZipCode(residentialAddress.getContactdetailsPostcode());
                userModel.setMobileNumber(residentialAddress.getContactdetailsMobileNumber());
                userModel.setAlternateMobileNumber(residentialAddress.getContactdetailsPhoneNumber());
                userModel.setSameAddressOffnHome(residentialAddress.getContactdetailsFaxNumber().equalsIgnoreCase("yes"));

                residentialState =  residentialAddress.getContactdetailsState();
            }

            Contactdetail permanentAddress = contactdetailService.getContactdetailByAgentIdAndBdApplicable(agentId, "2");
            if(permanentAddress != null)
            {
                userModel.setPermanentAddress(permanentAddress.getContactdetailsAddLine1());
                userModel.setPermanentState(permanentAddress.getContactdetailsState());
                //userModel.setPermanentCity(permanentAddress.getMbaddCity());
                userModel.setPermanentCity(permanentAddress.getContactdetailsCity());
                userModel.setPermanentZipCode(permanentAddress.getContactdetailsPostcode());

                permanentState =  permanentAddress.getContactdetailsState();
            }

            userModel.setCityList(cityService.listAllActiveCityByStateId(Long.parseLong(residentialState)));
            userModel.setCityList2(cityService.listAllActiveCityByStateId(Long.parseLong(permanentState)));
            userModel.setStateList(stateService.listStates());
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        map.put("userModel", userModel);
        return "merchant_profile_edit";
    }
}
