package com.estel.controller;

import com.estel.dto.DeviceModel;
import com.estel.entity.*;
import com.estel.service.*;
import com.estel.utility.*;
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
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: estel
 * Date: 12/12/13
 * Time: 11:35 AM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class DeviceRegisterController {

    @Autowired
    BankService bankService;

    @Autowired
    ContactdetailService contactdetailService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    BusinessService businessService;

    @Autowired
    DeviceService deviceService;

    @Autowired
    StatusService statusService;

    @Autowired
    PinService pinService;

    @Autowired
    UserService userService;

    @Autowired
    MerchantService merchantService;

    @Autowired
    EmailService emailService;

    @Autowired
    SmsService smsService;

    @Autowired
    AudittrailService audittrailService;


    @RequestMapping("/deviceList")
    public String deviceList(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {

        HttpSession session = request.getSession();
        Long agentId = (Long) session.getAttribute("agentId");

        if(agentId == null)
            return "redirect:/Mpos.do";

        ///////Added for sequence registration
        List<Bank> bankList = bankService.listBankByAgentId(agentId);
        if (bankList == null || bankList.isEmpty()) {

            Contactdetail residentialAddress = null;
            Contactdetail permanentAddress = null;
            try {
                residentialAddress = contactdetailService.getContactdetailByAgentIdAndBdApplicable(agentId, "1");
                permanentAddress = contactdetailService.getContactdetailByAgentIdAndBdApplicable(agentId, "2");
            } catch (Exception e) {
                e.printStackTrace();
            }

            if ((residentialAddress != null || permanentAddress != null) && (residentialAddress.getContactdetailsContactName() == null)) {
                session.setAttribute("sequence_info_msg", messageSource.getMessage("sequence.profile.msg", null, "default-sequence.profile.msg", null));
                session.setAttribute("stepNo", "2");
                return "redirect:/merchantProfileAction.do";
            }


            Business business = businessService.getBusinessByAgentId(agentId);
            if (business == null) {

                session.setAttribute("sequence_info_msg", messageSource.getMessage("sequence.business.info.msg", null, "default-sequence.business.info.msg", null));
                session.setAttribute("stepNo", "1");                
                return "redirect:/merchantBusinessInfoAction.do";
            }

            session.setAttribute("sequence_info_msg", messageSource.getMessage("sequence.bank.msg", null, "default-sequence.bank.msg", null));
            session.setAttribute("stepNo", "3");            
            return "redirect:/bankAccountList.do";
        }

        //////

        DeviceModel deviceModel = new DeviceModel();

        List<Device> deviceList = deviceService.listDeviceByAgentId(agentId);
        if (deviceList == null || deviceList.isEmpty()) {
            deviceModel.setDeviceList(deviceList);
            map.put("deviceModel", deviceModel);
            return "merchant_device_add";
        } else {

            List<Device> deviceListTemp = null;

            if (deviceList != null) {
                try {
                    deviceListTemp = Pagination.getSubList(deviceList, request);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                deviceModel.setDeviceList(deviceListTemp);

            } else {
                deviceModel.setDeviceList(deviceList);
            }

            /*deviceModel.setDeviceList(deviceList);*/
            map.put("deviceModel", deviceModel);
            return "merchant_device";
        }
    }


    @RequestMapping(value = "/deviceAdd",  method = { RequestMethod.GET, RequestMethod.POST })
    public String deviceAdd(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map,
                            @ModelAttribute("deviceModel") DeviceModel deviceModel, BindingResult result) {

        HttpSession session = request.getSession();
        Long agentId = (Long) session.getAttribute("agentId");

        if(agentId == null)
            return "redirect:/Mpos.do";

        if(deviceModel.getDeviceNumber() == null)
        {
            deviceModel.setDeviceStatusList(statusService.listStatussByCode(new String[]{"Y", "N"}));
            map.put("deviceModel",deviceModel);
            return "merchant_device_add";
        }


        //check for mobile number duplicacy
        if (deviceService.listDeviceByPhoneNumber(deviceModel.getDeviceNumber()) != null) {
            session.setAttribute("device_msg", messageSource.getMessage("device.already.add", null, "default-device.already.add", null));
            return "merchant_device_add";           
        }
        //check for mobile number duplicacy
        else {

            String chars = "0123456789";
            Integer string_length = 12;//As per discussion 12 digit  before it was 16
            String randomstring = "";
            for (int i = 0; i < string_length; i++) {
                Double rnum = Math.floor(Math.random() * chars.length());
                int rnum_int = rnum.intValue();
                randomstring += chars.substring(rnum_int, rnum_int + 1);
            }

            String activationcode = randomstring;

            Device device = deviceService.addDeviceByAgentId(agentId, 2L, deviceModel.getDeviceNumber(), statusService.getStatusByCode("N").getStatusId(), activationcode, deviceModel.getIMEINumber(),deviceModel.getCardReaderSerialNo());

            Pin pin = pinService.addPinByPinTypeAndAgentId(null, device.getDeviceId(), Constant.MPIN, agentId);

            //Send Email for Device Activation
            User user = userService.getUserByAgentId(agentId);
            try {
                emailService.sendEmail(user.getUserMailId(),messageSource.getMessage("DEVICE-ACTIVATION",null,"default-DEVICE-ACTIVATION",null),"DEVICE-ACTIVATION",new Object[]{user.getUserFirstName(), deviceModel.getDeviceNumber(), activationcode, pin.getPinValue()});
            } catch (Exception e) {
                e.printStackTrace();
            }


            //Send Sms
            try {
                smsService.sendSms("DEVICE_ACTIVATION", contactdetailService.getContactdetailByAgentIdAndBdApplicable(agentId, "1").getContactdetailsMobileNumber(), new Object[]{deviceModel.getDeviceNumber()});
            } catch (Exception e) {
                e.printStackTrace();
            }

            String ipAddress = (request).getHeader("X-FORWARDED-FOR");
            if (ipAddress == null) {
                ipAddress = (request).getRemoteAddr();
            }

            String old_val = null;

            String new_val = "Mobile Number=" + deviceModel.getDeviceNumber() +
                    ",IMEI Number=" + deviceModel.getIMEINumber() +
                    ",Card Reader Serial No.=" + deviceModel.getCardReaderSerialNo()+
                    ",Device Status ="+statusService.getStatusByCode("N").getStatusDescription();

            audittrailService.addAudittrailByUserId(userService.getUserByAgentId(agentId).getUserId(), "Add Device", "Add", ipAddress, "Device Added", old_val, new_val);

            session.setAttribute("device_msg", messageSource.getMessage("device.add.sucess", null, "default-device.add.sucess", null));

            List<Device> deviceList = deviceService.listDeviceByAgentId(agentId);
            List<Device> deviceListTemp = null;

            if (deviceList != null) {
                try {
                    deviceListTemp = Pagination.getSubList(deviceList, request);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                deviceModel.setDeviceList(deviceListTemp);

            } else {
                deviceModel.setDeviceList(deviceList);
            }
            return "merchant_device";
        }
    }


    @RequestMapping(value = "/deviceEdit", method = RequestMethod.POST)
    public String deviceEdit(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map,
                             @ModelAttribute("deviceModel") DeviceModel deviceModel, BindingResult result) {

        HttpSession session = request.getSession();
        Long agentId = (Long) session.getAttribute("agentId");

        if(agentId == null)
            return "redirect:/Mpos.do";

        if (request.getParameter("pageAction") != null && request.getParameter("pageAction").toString().trim().equalsIgnoreCase("view")) {
            if (request.getParameter("deviceId") != null && !request.getParameter("deviceId").trim().equalsIgnoreCase("")) {

                Device device = deviceService.getDeviceById(Long.parseLong(request.getParameter("deviceId")));

                deviceModel.setDeviceId(Long.parseLong(request.getParameter("deviceId")));
                deviceModel.setDeviceNumber(device.getDeviceNumber());
                deviceModel.setDeviceStatus(device.getStatus().getStatusId());
                deviceModel.setDeviceStatusList(statusService.listStatussByCode(new String[]{"Y", "N"}));

            }

            return "merchant_device_view";

        } else if (request.getParameter("pageAction") != null && request.getParameter("pageAction").toString().trim().equalsIgnoreCase("edit")) {


            if (request.getParameter("deviceId") != null && !request.getParameter("deviceId").trim().equalsIgnoreCase("")) {

                Device device = deviceService.getDeviceById(Long.parseLong(request.getParameter("deviceId")));
                deviceModel.setDeviceId(Long.parseLong(request.getParameter("deviceId")));
                deviceModel.setDeviceNumber(device.getDeviceNumber());
                deviceModel.setDeviceStatus(device.getStatus().getStatusId());
                deviceModel.setDeviceStatusList(statusService.listStatussByCode(new String[]{"Y", "N"}));
                deviceModel.setDeviceList(deviceService.listDeviceByAgentId(agentId));
            }

            return "merchant_device_edit";
        } else {
            //  this block will called for updation in db
            Device device = deviceService.getDeviceById(Long.parseLong(request.getParameter("deviceId")));

            String old_val = "Mobile Number=" + device.getDeviceNumber() +
                    ",IMEI Number=" + device.getDeviceTerminalId() +
                    ",Card Reader Serial No.=" + device.getDevicePhoneId()+
                    ",Device Status ="+statusService.getStatusByCode(device.getStatus().getStatusCode()).getStatusDescription();

            deviceModel.setDeviceNumber(device.getDeviceNumber());
            //deviceModel.setIMEINumber(device.getDeviceTerminalId());
            deviceModel.setIMEINumber(request.getParameter("TEXT_IMEI"+ request.getParameter("deviceId")+"2"));
            deviceModel.setCardReaderSerialNo(request.getParameter("TEXT_SERIAL" + request.getParameter("deviceId") + "2"));

            deviceModel.setPin(request.getParameter("PinBox" + request.getParameter("deviceId")));
            deviceModel.setDeviceList(deviceService.listDeviceByAgentId(agentId));

            //check if pin is valid or not..if not valid show error on merchant_device_edit jsp page
            //String md5Pin = MD5.MD5Convertor((merchantService.getMerchantById(agentId).getMerchantCode()) + deviceModel.getPin()).toUpperCase();
            EncryptionAlgorithm encryptionAlgorithm = new EncryptionAlgorithmImpl(Constant.SHA2);
            String md5Pin = encryptionAlgorithm.encrypt((merchantService.getMerchantById(agentId).getMerchantCode()) + deviceModel.getPin());
            Pin pin = pinService.verifyPinByPinTypeAndAgentId(md5Pin, Constant.IPIN, agentId);
            if (pin != null) {

                if (deviceService.getDeviceById(Long.parseLong(request.getParameter("deviceId"))).getStatus().getStatusId() == 1) {
                    //device deactivation
                    Long statusId = statusService.getStatusByCode("N").getStatusId();
                    deviceService.updateDevice(Long.parseLong(request.getParameter("deviceId")), deviceModel.getDeviceNumber(), statusId, deviceModel.getIMEINumber(),deviceModel.getCardReaderSerialNo());

                    session.setAttribute("device_msg", messageSource.getMessage("device.deactivate.sucess", null, "default-device.deactivate.sucess", null));
                    deviceModel.setDeviceStatus(statusId);

                    //Send Email
                    User user = userService.getUserByAgentId(agentId);
                    try {
                        emailService.sendEmail(user.getUserMailId(),messageSource.getMessage("DEVICE-DEACTIVATION",null,"default-DEVICE-DEACTIVATION",null),"DEVICE-DEACTIVATION",new Object[]{user.getUserFirstName(), deviceModel.getDeviceNumber()});
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    //Send Sms
                    try {
                        smsService.sendSms("DEVICE_DEACTIVATION", contactdetailService.getContactdetailByAgentIdAndBdApplicable(agentId, "1").getContactdetailsMobileNumber(), new Object[]{deviceModel.getDeviceNumber()});
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                } else {
                    String chars = "0123456789";
                    Integer string_length = 12;//As per discussion 12 digit  before it was 16
                    String randomstring = "";
                    for (int i = 0; i < string_length; i++) {
                        Double rnum = Math.floor(Math.random() * chars.length());
                        int rnum_int = rnum.intValue();
                        randomstring += chars.substring(rnum_int, rnum_int + 1);
                    }

                    String activationcode = randomstring;


                    Long statusId = statusService.getStatusByCode("N").getStatusId();
                    deviceService.updateDevice(Long.parseLong(request.getParameter("deviceId")), deviceModel.getDeviceNumber(), statusId, activationcode, deviceModel.getIMEINumber(),deviceModel.getCardReaderSerialNo());

                    session.setAttribute("device_msg", messageSource.getMessage("device.add.already.sucess", null, "default-device.add.already.sucess", null));
                    deviceModel.setDeviceStatus(statusId);

                    String generatedMPIN = RandomStringUtils.randomNumeric(4);
                    //pinService.updatePinByAgentIdAndClientId(MD5.MD5Convertor(deviceModel.getDeviceNumber() + generatedMPIN).toUpperCase(), device.getDeviceId(), Constant.MPIN, agentId);
                    pinService.updatePinByAgentIdAndClientId(encryptionAlgorithm.encrypt(deviceModel.getDeviceNumber() + generatedMPIN), device.getDeviceId(), Constant.MPIN, agentId);


                    //Send Email
                    User user = userService.getUserByAgentId(agentId);
                    try {
                        emailService.sendEmail(user.getUserMailId(),messageSource.getMessage("DEVICE-ACTIVATION",null,"default-DEVICE-ACTIVATION",null),"DEVICE-ACTIVATION",new Object[]{user.getUserFirstName(), deviceModel.getDeviceNumber(), activationcode, generatedMPIN});
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    //Send Sms
                    try {
                        smsService.sendSms("DEVICE_ACTIVATION", contactdetailService.getContactdetailByAgentIdAndBdApplicable(agentId, "1").getContactdetailsMobileNumber(), new Object[]{deviceModel.getDeviceNumber()});
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


                String ipAddress = (request).getHeader("X-FORWARDED-FOR");
                if (ipAddress == null) {
                    ipAddress = (request).getRemoteAddr();
                }

                String new_val = "Mobile Number=" + deviceModel.getDeviceNumber() +
                        ",IMEI Number=" + deviceModel.getIMEINumber() +
                        ",Card Reader Serial No.=" + deviceModel.getCardReaderSerialNo()+
                        ",Device Status ="+statusService.getStatusByCode("N").getStatusDescription();

                audittrailService.addAudittrailByUserId(userService.getUserByAgentId(agentId).getUserId(), "Update Device", "Update", ipAddress, "Device Updated", old_val, new_val);

                map.put("deviceModel", deviceModel);
                //return "merchant_device";
                return "redirect:/deviceList";


            } else {
                deviceModel.setDeviceStatus(device.getStatus().getStatusId());
                deviceModel.setDeviceStatusList(statusService.listStatussByCode(new String[]{"Y", "N"}));
                deviceModel.setPin("");
                session.setAttribute("device_msg", messageSource.getMessage("user.infomsg.pin", null, "default-user.infomsg.pin", null));
                map.put("deviceModel", deviceModel);
                //return "merchant_device";
                return "redirect:/deviceList";
            }

        }
    }
}