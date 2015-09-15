package com.estel.controller;

import com.estel.dto.LoginModel;
import com.estel.dto.UserModel;
import com.estel.entity.Merchant;
import com.estel.entity.Pin;
import com.estel.entity.User;
import com.estel.service.*;
import com.estel.utility.Constant;
import com.estel.utility.EncryptionAlgorithm;
import com.estel.utility.EncryptionAlgorithmImpl;
import com.estel.utility.MD5;
import org.apache.commons.lang.RandomStringUtils;
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
import java.util.Calendar;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: estel
 * Date: 17/12/13
 * Time: 10:19 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class PINController {

    @Autowired
    MessageSource messageSource;

    @Autowired
    UserService userService;

    @Autowired
    ContactdetailService contactdetailService;

    @Autowired
    EmailService emailService;

    @Autowired
    SmsService smsService;

    @Autowired
    PinService pinService;

    @Autowired
    MerchantService merchantService;

    @Autowired
    DeviceService deviceService;

    @Autowired
    AudittrailService audittrailService;

    @RequestMapping("/changeMpin")
    public String provideChangeMPinForm(HttpServletRequest request,Map<String, Object> map) {

        HttpSession session = request.getSession();
        Long agentId = (Long) session.getAttribute("agentId");

        if(agentId == null)
            return "redirect:/Mpos.do";

        map.put("userModel", new UserModel());
        return "merchant_change_mpin_form";
    }

    @RequestMapping(value = {"/changeMpinAction","/forcedchangeMpinAction"}, method = RequestMethod.POST)
    public String changeMpinAction(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map,
                                   @ModelAttribute("userModel") UserModel userModel, BindingResult result) {

        HttpSession session = request.getSession();
        Long agentId = (Long) session.getAttribute("agentId");

        if(agentId == null)
            return "redirect:/Mpos.do";

        if (userModel.getOldPin().equals(userModel.getPin())) {
            if (request.getParameter("pageAction") != null && request.getParameter("pageAction").toString().trim().equalsIgnoreCase("forcedMpin")) {

                session.setAttribute("change_pin_msg", messageSource.getMessage("user.infomsg.pin.cannot.same", null, "default-user.infomsg.pin.cannot.same", null));
                map.put("userModel", new UserModel());
                return "merchant_forced_change_mpin_form";
            } else {

                session.setAttribute("change_pin_msg", messageSource.getMessage("user.infomsg.pin.cannot.same", null, "default-user.infomsg.pin.cannot.same", null));
                map.put("userModel", new UserModel());
                return "merchant_change_mpin_form";
            }
        }


        Merchant merchant = merchantService.getMerchantById(agentId);

        //String md5Pin = MD5.MD5Convertor(merchant.getMerchantCode() + userModel.getOldPin()).toUpperCase();
        EncryptionAlgorithm encryptionAlgorithm = new EncryptionAlgorithmImpl(Constant.SHA2);
        String md5Pin = encryptionAlgorithm.encrypt(merchant.getMerchantCode() + userModel.getOldPin());
        Pin pin = pinService.verifyPinByPinTypeAndAgentId(md5Pin, Constant.IPIN, agentId);
        if (pin != null) {
            //pin.setPinValue(MD5.MD5Convertor(merchant.getMerchantCode() + userModel.getPin()).toUpperCase());
            pin.setPinValue(encryptionAlgorithm.encrypt(merchant.getMerchantCode() + userModel.getPin()));
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, Integer.parseInt(messageSource.getMessage("expiry.date.for.pin",null,"default-expiry.date.for.pin",null)));
            pin.setPinExpiryDate(new java.sql.Date(calendar.getTimeInMillis()));
            pinService.updatePin(pin);

            String ipAddress = (request).getHeader("X-FORWARDED-FOR");
            if (ipAddress == null) {
                ipAddress = (request).getRemoteAddr();
            }
            String old_val = "old secret Key = "+md5Pin;
            String new_val = "new secret Key = "+encryptionAlgorithm.encrypt(merchant.getMerchantCode() + userModel.getPin());
            audittrailService.addAudittrailByUserId(-1L, "Change Secret Key", "Update", ipAddress, "Secret Key Updated", old_val, new_val);

        } else {

            if (request.getParameter("pageAction") != null && request.getParameter("pageAction").toString().trim().equalsIgnoreCase("forcedMpin")) {

                session.setAttribute("change_pin_msg", messageSource.getMessage("user.infomsg.pin", null, "default-user.infomsg.pin", null));
                map.put("userModel", new UserModel());
                return "merchant_forced_change_mpin_form";
            } else {

                session.setAttribute("change_pin_msg", messageSource.getMessage("user.infomsg.pin", null, "default-user.infomsg.pin", null));
                map.put("userModel", new UserModel());
                return "merchant_change_mpin_form";
            }
        }

        if (request.getParameter("pageAction") != null && request.getParameter("pageAction").toString().trim().equalsIgnoreCase("forcedMpin")) {
            return "redirect:/showDashboard.do";
        }
        else
        {
            session.setAttribute("change_pin_msg", messageSource.getMessage("merchant.message.pin.change.sucess", null, "default-merchant.message.pin.change.sucess", null));
            map.put("userModel", new UserModel());
            return "merchant_change_mpin_form";
        }
    }

    @RequestMapping("/forgotMpin")
    public String provideForgotMPinForm(HttpServletRequest request,Map<String, Object> map) {

        HttpSession session = request.getSession();
        Long agentId = (Long) session.getAttribute("agentId");

        if(agentId == null)
            return "redirect:/Mpos.do";

        map.put("loginModel", new LoginModel());
        return "merchant_forgot_pin_form";
    }

    @RequestMapping("/forgotMpinAction")
    public String forgotMpinAction(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map,
                                   @ModelAttribute("loginModel") LoginModel loginModel, BindingResult result) {


        HttpSession session = request.getSession();
        Long agentId =(Long) session.getAttribute("agentId");

        if(agentId == null)
            return "redirect:/Mpos.do";

        String defaultMpin = RandomStringUtils.randomNumeric(Integer.parseInt(messageSource.getMessage("pin.length", null, "default-pin.length", null)));

        Merchant merchant = merchantService.getMerchantById(agentId);
        //pinService.updatePinByAgentId(MD5.MD5Convertor(merchant.getMerchantCode() + defaultMpin).toUpperCase(), Constant.IPIN, agentId);
        EncryptionAlgorithm encryptionAlgorithm = new EncryptionAlgorithmImpl(Constant.SHA2);
        pinService.updatePinByAgentId(encryptionAlgorithm.encrypt(merchant.getMerchantCode() + defaultMpin), Constant.IPIN, agentId);

        session.setAttribute("forgot_pin_msg", messageSource.getMessage("forgot.pin.msg", null, "default-forgot.pin.msg", null));

        //Send Email
        User user = userService.getUserByAgentId(agentId);
        try {
            emailService.sendEmail(user.getUserMailId(), messageSource.getMessage("FORGOT-PIN",null,"default-FORGOT-PIN",null),"FORGOT-PIN", new Object[]{user.getUserFirstName(), defaultMpin});
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Send Sms
        try {
            smsService.sendSms("FORGOT_SECRET_KEY", contactdetailService.getContactdetailByAgentIdAndBdApplicable(agentId, "1").getContactdetailsMobileNumber(), new Object[]{user.getUserFirstName()});
        } catch (Exception e) {
            e.printStackTrace();
        }

        String ipAddress = (request).getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = (request).getRemoteAddr();
        }
        String old_val = null;
        String new_val = "new secret Key = "+encryptionAlgorithm.encrypt(merchant.getMerchantCode() + defaultMpin);
        audittrailService.addAudittrailByUserId(-1L, "Forgot Secret Key", "Update", ipAddress, "Secret Key Updated", old_val, new_val);


        map.put("loginModel", new LoginModel());
        return "merchant_forgot_pin_form";
    }


    @RequestMapping("/forcedchangeMpin")
    public String provideForcedMPinForm(HttpServletRequest request,Map<String, Object> map) {

        HttpSession session = request.getSession();
        Long agentId = (Long) session.getAttribute("agentId");

        if(agentId == null)
            return "redirect:/Mpos.do";

        map.put("userModel", new UserModel());
        return "merchant_forced_change_mpin_form";
    }




}
