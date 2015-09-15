package com.estel.controller;

import com.estel.dto.LoginModel;
import com.estel.dto.UserModel;
import com.estel.entity.PasswordRecord;
import com.estel.entity.User;
import com.estel.service.*;
import com.estel.utility.Constant;
import com.estel.utility.EncryptionAlgorithm;
import com.estel.utility.EncryptionAlgorithmImpl;
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
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: estel
 * Date: 17/12/13
 * Time: 10:19 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class PasswordController {

    @Autowired
    MessageSource messageSource;

    @Autowired
    PasswordRecordService passwordRecordService;

    @Autowired
    UserService userService;

    @Autowired
    ContactdetailService contactdetailService;

    @Autowired
    EmailService emailService;

    @Autowired
    SmsService smsService;

    @Autowired
    StatusService statusService;

    @Autowired
    MerchantService merchantService;

    @Autowired
    AudittrailService audittrailService;

    @RequestMapping("/changePassword")
    public String provideChangePasswordForm(HttpServletRequest request,Map<String, Object> map) {

        HttpSession session = request.getSession();
        Long agentId = (Long) session.getAttribute("agentId");

        if(agentId == null)
            return "redirect:/Mpos.do";

        map.put("userModel", new UserModel());
        return "merchant_change_password_form";
    }

    @RequestMapping(value = {"/changePasswordAction", "/forcedChangePasswordAction"}, method = RequestMethod.POST)
    public String changePasswordAction(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map,
                                       @ModelAttribute("userModel") UserModel userModel, BindingResult result) {

        HttpSession session = request.getSession();
        Long agentId = (Long) session.getAttribute("agentId");

        if(agentId == null)
            return "redirect:/Mpos.do";

        //server side password check
        boolean notAllowPassword = false;
        String passwd = userModel.getPassword();

        //String pattern = "((?=.*[0-9])(?=.*[!@#$^*])[a-zA-Z0-9!@#$%^&*]{8,12})";
        String pattern = "((?=.*[0-9])(?=.*[@._#,:()])[a-zA-Z0-9@._#,:()]{8,12})";
        notAllowPassword = !passwd.matches(pattern);

        if(notAllowPassword)
        {
            if (request.getParameter("pageAction") != null && request.getParameter("pageAction").toString().trim().equalsIgnoreCase("forced")) {

                session.setAttribute("change_pass_msg", messageSource.getMessage("not.allowed.password", null, "default-not.allowed.password", null));
                map.put("userModel", new UserModel());
                return "merchant_forced_change_password_form";
            } else {

                session.setAttribute("change_pass_msg", messageSource.getMessage("not.allowed.password", null, "default-not.allowed.password", null));
                map.put("userModel", new UserModel());
                return "merchant_change_password_form";
            }
        }

        //server side password check

        EncryptionAlgorithm encryptionAlgorithm = new EncryptionAlgorithmImpl(Constant.SHA2);

        if (userModel.getOldPassword().equals(userModel.getPassword())) {
            if (request.getParameter("pageAction") != null && request.getParameter("pageAction").toString().trim().equalsIgnoreCase("forced")) {

                session.setAttribute("change_pass_msg", messageSource.getMessage("user.infomsg.password.cannot.same", null, "default-user.infomsg.password.cannot.same", null));
                map.put("userModel", new UserModel());
                return "merchant_forced_change_password_form";
            } else {

                session.setAttribute("change_pass_msg", messageSource.getMessage("user.infomsg.password.cannot.same", null, "default-user.infomsg.password.cannot.same", null));
                map.put("userModel", new UserModel());
                return "merchant_change_password_form";
            }
        } else    //Added for Last n password
        {
            List<PasswordRecord> passwordRecordList = passwordRecordService.listPasswordRecordByAgentId(agentId);
            if (passwordRecordList != null) {

                String lastPasswordsUsed = messageSource.getMessage("last.passwords", null, "default-last.passwords", null);

                if(Integer.parseInt(lastPasswordsUsed) >= 10)
                {
                   lastPasswordsUsed = String.valueOf(10);
                }

                if (passwordRecordList.size() <= Integer.parseInt(lastPasswordsUsed)) {
                    if (passwordRecordList.size() > 0) {
                        //check with all password if match display msg
                        //List<PasswordRecord> passwordRecordList1 = passwordRecordService.listPasswordRecordByAgentIdAndPassword(agentId, (MD5.MD5Convertor(merchantService.getMerchantById(agentId).getMerchantCode() + userModel.getPassword())).toUpperCase());
                        List<PasswordRecord> passwordRecordList1 = passwordRecordService.listPasswordRecordByAgentIdAndPassword(agentId, (encryptionAlgorithm.encrypt(merchantService.getMerchantById(agentId).getMerchantCode() + userModel.getPassword())));
                        if (passwordRecordList1 != null) {
                            //return because password matched
                            if (request.getParameter("pageAction") != null && request.getParameter("pageAction").toString().trim().equalsIgnoreCase("forced")) {

                                session.setAttribute("change_pass_msg", messageSource.getMessage("password.last.used", null, "default-password.last.used", null));
                                map.put("userModel", new UserModel());
                                return "merchant_forced_change_password_form";
                            } else {

                                session.setAttribute("change_pass_msg", messageSource.getMessage("password.last.used", null, "default-password.last.used", null));
                                map.put("userModel", new UserModel());
                                return "merchant_change_password_form";
                            }
                        } else {
                            if (passwordRecordList.size() < Integer.parseInt(lastPasswordsUsed)) {
                                //passwordRecordService.addPasswordRecordByAgentId(Constant.IPIN, (MD5.MD5Convertor(merchantService.getMerchantById(agentId).getMerchantCode() + userModel.getPassword())).toUpperCase(), agentId);
                                passwordRecordService.addPasswordRecordByAgentId(Constant.IPIN, (encryptionAlgorithm.encrypt(merchantService.getMerchantById(agentId).getMerchantCode() + userModel.getPassword())), agentId);
                            } else {

                                PasswordRecord passwordRecord = passwordRecordService.listAllByAgentIdWithLowestCTS(agentId);
                                //passwordRecord.setPasswordRecordPassword((MD5.MD5Convertor(merchantService.getMerchantById(agentId).getMerchantCode() + userModel.getPassword())).toUpperCase());
                                passwordRecord.setPasswordRecordPassword((encryptionAlgorithm.encrypt(merchantService.getMerchantById(agentId).getMerchantCode() + userModel.getPassword())));

                                passwordRecordService.updatePasswordRecord(passwordRecord);
                            }
                        }
                    } else {
                        //passwordRecordService.addPasswordRecordByAgentId(Constant.IPIN, (MD5.MD5Convertor(merchantService.getMerchantById(agentId).getMerchantCode() + userModel.getPassword())).toUpperCase(), agentId);
                        passwordRecordService.addPasswordRecordByAgentId(Constant.IPIN, (encryptionAlgorithm.encrypt(merchantService.getMerchantById(agentId).getMerchantCode() + userModel.getPassword())), agentId);
                    }
                }
            } else {
                //passwordRecordService.addPasswordRecordByAgentId(Constant.IPIN, (MD5.MD5Convertor(merchantService.getMerchantById(agentId).getMerchantCode() + userModel.getPassword())).toUpperCase(), agentId);
                passwordRecordService.addPasswordRecordByAgentId(Constant.IPIN, (encryptionAlgorithm.encrypt(merchantService.getMerchantById(agentId).getMerchantCode() + userModel.getPassword())), agentId);
            }
        }


        User user1 = userService.getUserByAgentId(agentId);

        //User user = userService.verifyUserLogin(user1.getUserMailId(), (MD5.MD5Convertor(merchantService.getMerchantById(agentId).getMerchantCode() + userModel.getOldPassword())).toUpperCase());
        User user = userService.verifyUserLogin(user1.getUserMailId(), (encryptionAlgorithm.encrypt(merchantService.getMerchantById(agentId).getMerchantCode() + userModel.getOldPassword())));

        if (user != null) {

            String passwordExpiryDays = messageSource.getMessage("expiry.date.for.password",null,"default-expiry.date.for.password",null);
            if(Integer.parseInt(passwordExpiryDays) >= 90)
            {
                passwordExpiryDays = String.valueOf(90);
            }

            //user.setUserPassword((MD5.MD5Convertor(user.getUserMailId() + userModel.getPassword())).toUpperCase());
            user.setUserPassword((encryptionAlgorithm.encrypt(user.getUserMailId() + userModel.getPassword())));
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE , Integer.parseInt(passwordExpiryDays));
            user.setUserPasswordExpiry(new java.sql.Date(calendar.getTimeInMillis()));
            user.setStatus(statusService.getStatusByCode("Y"));
            userService.updateUser(user);

            //Send Email
            // User mbUserLogin = userService.getUserByAgentId(agentId);
            try {
                emailService.sendEmail(user.getUserMailId(),messageSource.getMessage("CHANGE-PASSWORD",null,"default-CHANGE-PASSWORD",null),"CHANGE-PASSWORD",new Object[]{user.getUserFirstName(), userModel.getPassword()});
            } catch (Exception e) {
                e.printStackTrace();
            }

            //Send Sms
            try {
                smsService.sendSms("CHANGE_PASSWORD", contactdetailService.getContactdetailByAgentIdAndBdApplicable(agentId, "1").getContactdetailsMobileNumber(), new Object[]{user.getUserFirstName()});
            } catch (Exception e) {
                e.printStackTrace();
            }

            String ipAddress = (request).getHeader("X-FORWARDED-FOR");
            if (ipAddress == null) {
                ipAddress = (request).getRemoteAddr();
            }
            String old_val = "old password = "+(encryptionAlgorithm.encrypt(merchantService.getMerchantById(agentId).getMerchantCode() + userModel.getOldPassword()));
            String new_val = "new password = "+(encryptionAlgorithm.encrypt(user.getUserMailId() + userModel.getPassword()));
            audittrailService.addAudittrailByUserId(user.getUserId(), "Change Password", "Update", ipAddress, "Password Updated", old_val, new_val);


        } else {
            if (request.getParameter("pageAction") != null && request.getParameter("pageAction").toString().trim().equalsIgnoreCase("forced")) {

                session.setAttribute("change_pass_msg", messageSource.getMessage("user.infomsg.password", null, "default-user.infomsg.password", null));
                map.put("userModel", new UserModel());
                return "merchant_forced_change_password_form";

            } else {

                session.setAttribute("change_pass_msg", messageSource.getMessage("user.infomsg.password", null, "default-user.infomsg.password", null));
                map.put("userModel", new UserModel());
                return "merchant_change_password_form";
            }
        }

        if (request.getParameter("pageAction") != null && request.getParameter("pageAction").toString().trim().equalsIgnoreCase("forced")) {
            //return "dashboard";
            return "redirect:/showDashboard.do";
        } else {
            session.setAttribute("change_pass_msg", messageSource.getMessage("merchant.message.password.change.sucess", null, "default-merchant.message.password.change.sucess", null));
            map.put("userModel", new UserModel());
            return "merchant_change_password_form";
        }
    }

    @RequestMapping("/forgotPassword")
    public String provideForgotPasswordForm(Map<String, Object> map) {

        map.put("loginModel", new LoginModel());
        return "merchant_forgot_password_form";
    }

    @RequestMapping(value = "/forgotPasswordAction", method = RequestMethod.POST)
    public String forgotPasswordAction(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map,
                                       @ModelAttribute("loginModel") LoginModel loginModel, BindingResult result) {

        HttpSession session = request.getSession();

        String defaultPassword = "MPOS" + RandomStringUtils.randomAlphanumeric(4);

        String email = loginModel.getUsername();
        boolean alreadyRegistered = false;

        User user = userService.getUserByEmailId(email);
        if (user != null) {
            alreadyRegistered = true;
        }

        if (alreadyRegistered == true) {

            if(user.getStatus().getStatusCode().equalsIgnoreCase("N"))
            {
                session.setAttribute("forgot_pass_msg", messageSource.getMessage("merchant.inactive", null, "default-merchant.inactive", null));
                map.put("loginModel", new LoginModel());
                return "merchant_forgot_password_form";
            }

            user.setStatus(statusService.getStatusByCode("A"));
            //user.setUserPassword((MD5.MD5Convertor(user.getUserMailId() + defaultPassword).toUpperCase()));
            EncryptionAlgorithm encryptionAlgorithm = new EncryptionAlgorithmImpl(Constant.SHA2);
            user.setUserPassword((encryptionAlgorithm.encrypt(user.getUserMailId() + defaultPassword)));
            userService.updateUser(user);

            //Send Email
            //User mbUserLogin = userService.getUserByAgentId(agentId);
            try {
                emailService.sendEmail(user.getUserMailId(),messageSource.getMessage("FORGOT-PASSWORD",null,"default-FORGOT-PASSWORD",null),"FORGOT-PASSWORD", new Object[]{user.getUserFirstName(),defaultPassword});
            } catch (Exception e) {
                e.printStackTrace();
            }

            //Send Sms
            try {
                smsService.sendSms("FORGOT_PASSWORD", contactdetailService.getContactdetailByAgentIdAndBdApplicable(user.getMerchant().getMerchantId(), "1").getContactdetailsMobileNumber(), new Object[]{user.getUserFirstName()});
            } catch (Exception e) {
                e.printStackTrace();
            }

            String ipAddress = (request).getHeader("X-FORWARDED-FOR");
            if (ipAddress == null) {
                ipAddress = (request).getRemoteAddr();
            }
            String old_val = null;
            String new_val = "new password = "+(encryptionAlgorithm.encrypt(user.getUserMailId() + defaultPassword));
            audittrailService.addAudittrailByUserId(-1L, "Forgot Password", "Update", ipAddress, "Password Updated", old_val, new_val);


            return "merchant_forgot_password_success";

        } else {
            session.setAttribute("forgot_pass_msg", messageSource.getMessage("forgot.password.error", null, "default-forgot.password.error", null));
            map.put("loginModel", new LoginModel());
            return "merchant_forgot_password_form";
        }
    }


    @RequestMapping("/forcedChangePassword")
    public String provideForcedChangePasswordForm(HttpServletRequest request,Map<String, Object> map) {

        HttpSession session = request.getSession();
        Long agentId = (Long) session.getAttribute("agentId");

        if(agentId == null)
            return "redirect:/Mpos.do";

        map.put("userModel", new UserModel());
        return "merchant_forced_change_password_form";
    }
}
