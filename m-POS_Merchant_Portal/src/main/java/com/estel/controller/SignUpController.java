package com.estel.controller;

import com.estel.dto.LoginModel;
import com.estel.dto.UserModel;
import com.estel.entity.Country;
import com.estel.entity.Device;
import com.estel.entity.Merchant;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: estel
 * Date: 19/12/13
 * Time: 12:45 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class SignUpController {

    @Autowired
    CountryService countryService;

    @Autowired
    StateService stateService;

    @Autowired
    CityService cityService;

    @Autowired
    StatusService statusService;

    @Autowired
    UserService userService;

    @Autowired
    DeviceService deviceService;

    @Autowired
    ContactdetailService contactdetailService;

    @Autowired
    PinService pinService;

    @Autowired
    MerchantService merchantService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    EmailService emailService;

    @Autowired
    SmsService smsService;

    @Autowired
    AudittrailService audittrailService;

    @RequestMapping("/signUp")
    public String provideSignUpForm(Map<String, Object> map) {

        UserModel userModel = new UserModel();
        userModel.setCountryList(countryService.listCountrys());
        map.put("userModel", userModel);
        return "merchant_sign_up_form";
    }

    @RequestMapping(value = "/signUpAction", method = RequestMethod.POST)
    public String signUpAction(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map,
                               @ModelAttribute("userModel") UserModel userModel, BindingResult result) {

        List<Country> countryList = countryService.listCountrys();
        userModel.setCountryList(countryList);

        boolean alreadyRegistered = false;

        User user = userService.getUserByEmailId(userModel.getEmail());
        if (user != null) {
            alreadyRegistered = true;
        }

        Merchant merchant = merchantService.getMerchantByAbbr(userModel.getEmail());
        if (merchant != null) {
            alreadyRegistered = true;
        }

        if (alreadyRegistered == true && user != null && merchant != null) {
            String getCode = "";
            getCode = user.getStatus().getStatusCode();
            map.put("userState", getCode);



            if (getCode.equalsIgnoreCase("U")) {
                //String serverUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + "/Mpos" + "/admin/verifyUser.do";
                String serverUrl = messageSource.getMessage("application.running.on",null,"http",null)+"://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() +"/MposVerifyUser.do";
                String url = serverUrl + "?refId=" + merchant.getMerchantProofValue() + "&email=" + user.getUserMailId();

                //Send Email
                //User mbUserLogin = userService.getUserByAgentId(agentId);
                try {
                    emailService.sendEmail(user.getUserMailId(),messageSource.getMessage("REGISTRATION",null,"default-REGISTRATION",null),"REGISTRATION", new Object[]{user.getUserFirstName(), url});
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //Send Sms
                try {
                    smsService.sendSms("MERCHANT_REGISTRATION", contactdetailService.getContactdetailByAgentIdAndBdApplicable(merchant.getMerchantId(), "1").getContactdetailsMobileNumber(), new Object[]{user.getUserFirstName()});
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return "merchant_already_registered_success";

        } else {
            String refId = RandomStringUtils.randomAlphanumeric(5);

            Merchant merchant1 = merchantService.addMerchantByAbbr((userModel.getFirstName().substring(0, 1).toUpperCase()) + userModel.getFirstName().substring(1, userModel.getFirstName().length()) + "-" + (userModel.getSurName().substring(0, 1).toUpperCase()) + userModel.getSurName().substring(1, userModel.getSurName().length()),
                    userModel.getEmail(),
                    statusService.getStatusByCode("Y").getStatusId(),
                    refId,userModel.getMobileNumber());

            EncryptionAlgorithm encryptionAlgorithm = new EncryptionAlgorithmImpl(Constant.SHA2);

            User user1 = userService.addUserByEmailId(
                    userModel.getEmail(),
                    //MD5.MD5Convertor(merchant1.getMerchantCode() + userModel.getPassword()).toUpperCase(),
                    encryptionAlgorithm.encrypt(merchant1.getMerchantCode() + userModel.getPassword()),
                    (userModel.getFirstName().substring(0, 1).toUpperCase()) + userModel.getFirstName().substring(1, userModel.getFirstName().length()),
                    (userModel.getSurName().substring(0, 1).toUpperCase()) + userModel.getSurName().substring(1, userModel.getSurName().length()),
                    1L,
                    userModel.getEmail(),
                    statusService.getStatusByCode("U").getStatusId(),
                    merchant1.getMerchantId()
            );

            contactdetailService.addContactdetailByAgentIdAndBdApplicable(null, null, null, null, null, null, "no",
                    userModel.getMobileNumber(),
                    userModel.getEmail(),
                    null,
                    merchant1.getMerchantId(),
                    "1",userModel.getResidentialCountry());

            contactdetailService.addContactdetailByAgentIdAndBdApplicable(null, null, null, null, null, null, "no",
                    userModel.getMobileNumber(),
                    userModel.getEmail(),
                    null,
                    merchant1.getMerchantId(),
                    "2",userModel.getResidentialCountry());

            //pinService.addPinByPinTypeAndAgentId(MD5.MD5Convertor(merchant1.getMerchantCode() + userModel.getPin()).toUpperCase(), null, Constant.IPIN, merchant1.getMerchantId());
            pinService.addPinByPinTypeAndAgentId(encryptionAlgorithm.encrypt(merchant1.getMerchantCode() + userModel.getPin()), null, Constant.IPIN, merchant1.getMerchantId());

            //String serverUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + "/Mpos" + "/admin/verifyUser.do";
            String serverUrl = messageSource.getMessage("application.running.on",null,"http",null)+"://" + request.getServerName() + ":" + request.getServerPort()+ request.getContextPath() +"/MposVerifyUser.do";
            String url = serverUrl + "?refId=" + merchant1.getMerchantProofValue() + "&email=" + user1.getUserMailId();
            System.out.println("url = " + url);

            //Send Email
            //User mbUserLogin = userService.getUserByAgentId(agentId);
            try {
                emailService.sendEmail(user1.getUserMailId(),messageSource.getMessage("REGISTRATION",null,"default-REGISTRATION",null),"REGISTRATION", new Object[]{user1.getUserFirstName(), url});
            } catch (Exception e) {
                e.printStackTrace();
            }

            //Send Sms
            try {
                smsService.sendSms("MERCHANT_REGISTRATION", contactdetailService.getContactdetailByAgentIdAndBdApplicable(merchant1.getMerchantId(), "1").getContactdetailsMobileNumber(), new Object[]{user1.getUserFirstName()});
            } catch (Exception e) {
                e.printStackTrace();
            }

            //Long userId, String lpanelTabName,  String actionName, String ip, String description, String old_value, String new_value

            String ipAddress = (request).getHeader("X-FORWARDED-FOR");
            if (ipAddress == null) {
                ipAddress = (request).getRemoteAddr();
            }

            audittrailService.addAudittrailByUserId(-1L,"New Sign Up","Add",ipAddress,"New Sign Up",null,null);

            map.put("userModel", userModel);
            return "merchant_sign_up_success";
        }
    }


    //@RequestMapping(value = "/Mpos/admin/verifyUser", method = RequestMethod.GET)
    @RequestMapping(value = "/MposVerifyUser", method = RequestMethod.GET)
    public String signUpVerify(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map,
                               @ModelAttribute("userModel") UserModel userModel,@ModelAttribute("loginModel") LoginModel loginModel, BindingResult result) {

        String activationCode = "";
        String refId = request.getParameter("refId");
        String email = request.getParameter("email");

        User user = userService.getUserByEmailId(email);
        if (user != null)
        {
            activationCode = user.getMerchant().getMerchantProofValue();
            if (activationCode.equals(refId))
            {
                //check link expiry
                boolean linkExpireStatus = false;
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE , -(Integer.parseInt(messageSource.getMessage("expiry.date.for.link",null,"default-expiry.date.for.link",null))));
                Date nowDate = new java.sql.Date(calendar.getTimeInMillis());

                if (nowDate.after(user.getMerchant().getMerchantCreationDate())) {
                    linkExpireStatus = true;
                    System.out.println("linkExpireStatus===>" + linkExpireStatus);

                    System.out.println("regenrate-link==>"+messageSource.getMessage("link.expired",new Object[]{request.getContextPath()},"default-link.expired",null));

                    request.getSession().setAttribute("loginerrormessage", messageSource.getMessage("link.expired", new Object[]{request.getContextPath()}, "default-link.expired", null));
                    request.getSession().setAttribute("activateuseremailid",user.getUserMailId());
                    return "redirect:/Mpos.do";
                }

                //check if already active then show login screen with msg

                if ((user.getStatus().getStatusCode().equalsIgnoreCase("Y")) || (user.getStatus().getStatusCode().equalsIgnoreCase("A"))) {

                    List<Device> deviceList = deviceService.listDeviceByAgentId(user.getMerchant().getMerchantId());
                    if (deviceList == null || deviceList.isEmpty()) {

                        request.getSession().setAttribute("loginerrormessage", messageSource.getMessage("account.already.active.registration.pending.index",null,"default-account.already.active.registration.pending.index",null));

                    } else {
                        request.getSession().setAttribute("loginerrormessage", messageSource.getMessage("account.already.active.index",null,"default-account.already.active.index",null));

                    }

                } else {
                    user.setStatus(statusService.getStatusByCode("A"));
                    userService.updateUser(user);

                }

                loginModel.setUsername(email);
                map.put("loginModel",loginModel);
                request.getSession().setAttribute("userEmailId", email);
                //return "redirect:/Mpos.do";
                return "login";
            }
            else {

                request.getSession().setAttribute("loginerrormessage", messageSource.getMessage("link.not.verified",null,"default-link.not.verified",null));
                return "redirect:/Mpos.do";
            }
        }

        request.getSession().setAttribute("loginerrormessage", messageSource.getMessage("link.not.verified",null,"default-link.not.verified",null));
        return "redirect:/Mpos.do";
    }

    @RequestMapping(value = "/regenerateLink", method = RequestMethod.GET)
    public String regenerateLink(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map,
                               @ModelAttribute("userModel") UserModel userModel,@ModelAttribute("loginModel") LoginModel loginModel, BindingResult result) {

        String emailId = (String)request.getSession().getAttribute("activateuseremailid");

        Merchant merchant = merchantService.getMerchantByAbbr(emailId);

        String refId = RandomStringUtils.randomAlphanumeric(5);
        merchant.setMerchantProofValue(refId);
        merchant.setMerchantCreationDate(new java.sql.Date(System.currentTimeMillis()));
        merchantService.updateMerchant(merchant);

        User user = userService.getUserByAgentId(merchant.getMerchantId());

        String serverUrl = messageSource.getMessage("application.running.on",null,"http",null)+"://" + request.getServerName() + ":" + request.getServerPort()+ request.getContextPath() +"/MposVerifyUser.do";
        String url = serverUrl + "?refId=" + merchant.getMerchantProofValue() + "&email=" + user.getUserMailId();
        System.out.println("url = " + url);

        //Send Email
        try {
            emailService.sendEmail(user.getUserMailId(),messageSource.getMessage("REGISTRATION",null,"default-REGISTRATION",null),"REGISTRATION", new Object[]{user.getUserFirstName(), url});
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Send Sms
        try {
            smsService.sendSms("MERCHANT_REGISTRATION", contactdetailService.getContactdetailByAgentIdAndBdApplicable(merchant.getMerchantId(), "1").getContactdetailsMobileNumber(), new Object[]{user.getUserFirstName()});
        } catch (Exception e) {
            e.printStackTrace();
        }

        map.put("userModel", userModel);
        return "merchant_sign_up_success";
    }
}
