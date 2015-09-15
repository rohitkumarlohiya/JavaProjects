package com.estel.controller;

import com.estel.dto.LoginModel;
import com.estel.dto.UserModel;
import com.estel.entity.Merchant;
import com.estel.entity.Pin;
import com.estel.entity.User;
import com.estel.service.MerchantService;
import com.estel.service.PinService;
import com.estel.service.StatusService;
import com.estel.service.UserService;
import com.estel.utility.Constant;
import com.estel.utility.EncryptionAlgorithm;
import com.estel.utility.EncryptionAlgorithmImpl;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    PinService pinService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    MerchantService merchantService;

    @Autowired
    StatusService statusService;


    @RequestMapping(value = {"/", "/Mpos"})
    public String provideLoginForm(Map<String, Object> map) {

        map.put("loginModel", new LoginModel());
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String VerifyLogin(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map,
                              @ModelAttribute("loginModel") LoginModel loginModel, BindingResult result){

        HttpSession session = request.getSession(true);

        String loginAttempts = messageSource.getMessage("login.attempts",null,"default-login.attempts",null);
        if(Long.parseLong(loginAttempts) > 6)
        {
            loginAttempts = String.valueOf(6);
        }

        User user2 = userService.getUserByEmailId(loginModel.getUsername());
        if(user2 != null && !(user2.getStatus().getStatusCode().equalsIgnoreCase("N")))
        {
            Long loginAttempt = user2.getUserWrongAttemptCtr();
            if(loginAttempt == null)
            {
                loginAttempt = 0L;
            }

            if (loginAttempt >= (Long.parseLong(loginAttempts)-1)) {
                user2.setStatus(statusService.getStatusByCode("N"));
                userService.updateUser(user2);

                Merchant merchant = merchantService.getMerchantByAbbr(user2.getUserMailId());
                merchant.setStatus2(statusService.getStatusByCode("N"));
                merchantService.updateMerchant(merchant);


            } else {
                user2.setUserWrongAttemptCtr(loginAttempt+1);
                userService.updateUser(user2);
            }
        }
        else
        {
            if(user2 == null)
            {
                request.getSession().setAttribute("loginerrormessage", messageSource.getMessage("login.error.msg", null, "default-login.error.msg", null));
            }
            else
            {
                if(user2.getStatus().getStatusCode().equalsIgnoreCase("N"))
                {
                    request.getSession().setAttribute("loginerrormessage", messageSource.getMessage("merchant.inactive", null, "default-merchant.inactive", null));
                }
                else
                {
                    request.getSession().setAttribute("loginerrormessage", messageSource.getMessage("login.error.msg", null, "default-login.error.msg", null));
                }
            }

            map.put("loginModel", new LoginModel());
            //request.getSession().setAttribute("loginerrormessage", messageSource.getMessage("merchant.inactive", null, "default-login.error.msg", null));

            return "login";
        }

        EncryptionAlgorithm encryptionAlgorithm = new EncryptionAlgorithmImpl(Constant.SHA2);

        User user = null;
        try {
            //user = userService.verifyUserLogin(loginModel.getUsername(), MD5.MD5Convertor(loginModel.getUsername() + loginModel.getPassword()).toUpperCase());
            user = userService.verifyUserLogin(loginModel.getUsername(), encryptionAlgorithm.encrypt(loginModel.getUsername() + loginModel.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (user != null) {

            user2.setUserWrongAttemptCtr(0L);
            userService.updateUser(user2);

            //For signle sign on
            HashMap<String, HttpSession> hashMap = (HashMap<String, HttpSession>) (session.getServletContext().getAttribute("hashMap"));

            if (hashMap.containsKey(loginModel.getUsername())) {
                System.out.println("current session.getId()===>" + session.getId());
                String currentSessionId = session.getId();

                System.out.println("old session.getId()===>" + hashMap.get(loginModel.getUsername()).getId());
                String oldSessionId = hashMap.get(loginModel.getUsername()).getId();

                if (!currentSessionId.equalsIgnoreCase(oldSessionId)) {
                    try {
                        hashMap.get(loginModel.getUsername()).invalidate();
                    } catch (Exception e) {
                        System.out.println("session is already invalidated either by timeout or you have logged out");
                    }
                    hashMap.remove(loginModel.getUsername());
                    hashMap.put(loginModel.getUsername(), session);
                    System.out.println("hashMap.size()===>" + hashMap.size());
                }
            } else {
                hashMap.put(loginModel.getUsername(), session);
                System.out.println("hashMap.size()===>" + hashMap.size());
            }
            //For signle sign on

            session.setAttribute("agentId", user.getMerchant().getMerchantId());
            //session.setAttribute("UserName",user.getUserFirstName()+" "+user.getUserLastName());
            session.setAttribute("UserName", user.getUserFirstName());

            //check password expiry
            boolean passwordExpireStatus = false;
            Date nowDate = Calendar.getInstance().getTime();
            if (nowDate.after(user.getUserPasswordExpiry())) {
                passwordExpireStatus = true;
                System.out.println("passwordExpireStatus===>" + passwordExpireStatus);
            }

            //check mpin validity
            Pin mbPin = pinService.getPinByAgentId(user.getMerchant().getMerchantId());
            System.out.println("pin.getExpiryDate()=====>" + mbPin.getPinExpiryDate());

            boolean pinExpireStatus = false;
            //Date nowDate = Calendar.getInstance().getTime();
            /*if (nowDate.after(mbPin.getPinExpiryDate())) {
                pinExpireStatus = true;
                System.out.println("pinExpireStatus===>" + pinExpireStatus);
            }*/

            if (user.getStatus().getStatusCode().equalsIgnoreCase("U")) {
                UserModel userModel = new UserModel();
                userModel.setEmail(loginModel.getUsername());
                map.put("userModel", userModel);
                return "merchant_sign_up_success";
            } else if (user.getStatus().getStatusCode().equalsIgnoreCase("A")) {
                map.put("userModel", new UserModel());
                return "merchant_forced_change_password_form";
            }
            else if (passwordExpireStatus == true) {
                map.put("userModel", new UserModel());
                return "merchant_forced_change_password_form";
            }
            else if (pinExpireStatus == true) {
                map.put("userModel", new UserModel());
                return "merchant_forced_change_mpin_form";
            } else {
                return "redirect:/showDashboard.do";
            }
        } else {
            map.put("loginModel", new LoginModel());
            request.getSession().setAttribute("loginerrormessage", messageSource.getMessage("login.error.msg", null, "default-login.error.msg", null));
            return "login";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        try {
            request.getSession().invalidate();


            //System.out.println("dsfdsfdsaf=>"+request.getSession().getServletContext().getContextPath());


        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/Mpos.do";

    }


}
