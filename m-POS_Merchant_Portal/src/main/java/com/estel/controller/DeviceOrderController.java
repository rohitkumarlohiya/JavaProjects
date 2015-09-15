package com.estel.controller;

import com.estel.dto.DeviceOrderModel;
import com.estel.entity.*;
import com.estel.service.*;
import com.estel.utility.Pagination;
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
public class DeviceOrderController {

    @Autowired
    DeviceOrderService deviceOrderService;

    @Autowired
    DeviceTypeService deviceTypeService;

    @Autowired
    StateService stateService;

    @Autowired
    ContactdetailService contactdetailService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    StatusService statusService;

    @Autowired
    UserService userService;

    @Autowired
    EmailService emailService;

    @Autowired
    SmsService smsService;


    @RequestMapping("/orderDevice")
    public String orderDevice(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {

        HttpSession session = request.getSession();
        Long agentId = (Long)session.getAttribute("agentId");

        if(agentId == null)
            return "redirect:/Mpos.do";

        DeviceOrderModel deviceOrderModel = new DeviceOrderModel();

        List<DeviceOrder> deviceOrderList = null;
        List<DeviceType> deviceTypeList = null;
        List<State> stateList = null;
        List<Status> statusList = statusService.listStatuss();
        try {
            deviceOrderList = deviceOrderService.listDeviceOrderByAgentId(agentId);
            deviceTypeList = deviceTypeService.listDeviceTypes();
            stateList = stateService.listStates();
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


        List<DeviceOrder> deviceOrderListTemp = null;

        if (deviceOrderList != null) {
            try {
                deviceOrderListTemp = Pagination.getSubList(deviceOrderList, request);
            } catch (Exception e) {
                e.printStackTrace();
            }
            deviceOrderModel.setDeviceOrderList(deviceOrderListTemp);
            deviceOrderModel.setDeviceTypeList(deviceTypeList);
            deviceOrderModel.setStateList(stateList);

        } else {
            deviceOrderModel.setDeviceOrderList(deviceOrderList);
            deviceOrderModel.setDeviceTypeList(deviceTypeList);
            deviceOrderModel.setStateList(stateList);
        }

        deviceOrderModel.setStatusList(statusList);
        map.put("deviceOrderModel", deviceOrderModel);
        return "merchant_order_device";
    }


    @RequestMapping(value = "/orderDeviceAdd", method = RequestMethod.POST)
    public String orderDeviceAdd(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map,
                                 @ModelAttribute("deviceOrderModel") DeviceOrderModel deviceOrderModel, BindingResult result) {

        HttpSession session = request.getSession();
        Long agentId = (Long) session.getAttribute("agentId");

        if(agentId == null)
            return "redirect:/Mpos.do";

        String orderNumber = RandomStringUtils.randomNumeric(6);

        Contactdetail mbAddress1 = null;

        if (deviceOrderModel.isSameAsRegisteredAddress()) {
            Contactdetail mbAddress = contactdetailService.getContactdetailByAgentIdAndBdApplicable(agentId, "4");
            if (mbAddress != null) {
                mbAddress.setContactdetailsBdApplicable("5");
                mbAddress1 = contactdetailService.addContactdetail(mbAddress);
            }
        } else {
            mbAddress1 = contactdetailService.addContactdetailByAgentIdAndBdApplicable("",
                    deviceOrderModel.getRegisteredOfficeAddress(),
                    deviceOrderModel.getCityRegisterOfficeAddr(),
                    deviceOrderModel.getZipCodeRegisterOfficeAddrr(),
                    deviceOrderModel.getStateRegisterOfficeAddr(),
                    deviceOrderModel.getRegisterOfficePhoneNumber(),
                    "", "", "", "", agentId, "5", null);
        }


        deviceOrderService.addDeviceOrderByAgentId(deviceOrderModel.getDeviceType(), statusService.getStatusByCode("I").getStatusId(), orderNumber, agentId, deviceOrderModel.getQuantity(), mbAddress1.getContactdetailsId());
        session.setAttribute("order_device_msg", messageSource.getMessage("order.device.success1", null, "default-order.device.success1", null) + " " + orderNumber + messageSource.getMessage("order.device.success2", null, "default-order.device.success2", null));

        deviceOrderModel = new DeviceOrderModel();
        List<DeviceOrder> deviceOrderList = deviceOrderService.listDeviceOrderByAgentId(agentId);
        List<DeviceType> deviceTypeList = deviceTypeService.listDeviceTypes();
        List<State> stateList = stateService.listStates();
        List<Status> statusList = statusService.listStatuss();

        List<DeviceOrder> deviceOrderListTemp = null;

        if (deviceOrderList != null) {
            try {
                deviceOrderListTemp = Pagination.getSubList(deviceOrderList, request);
            } catch (Exception e) {
                e.printStackTrace();
            }
            deviceOrderModel.setDeviceOrderList(deviceOrderListTemp);

        } else {
            deviceOrderModel.setDeviceOrderList(deviceOrderList);
        }

        deviceOrderModel.setDeviceTypeList(deviceTypeList);
        deviceOrderModel.setStateList(stateList);
        deviceOrderModel.setStatusList(statusList);

        //Send Email
        User user = userService.getUserByAgentId(agentId);
        try {
            emailService.sendEmail(user.getUserMailId(), messageSource.getMessage("DEVICE-ORDER", null, "default-DEVICE-ORDER", null), "DEVICE-ORDER", new Object[]{user.getUserFirstName(), orderNumber});
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Send Sms
        try {
            smsService.sendSms("DEVICE_ORDER", contactdetailService.getContactdetailByAgentIdAndBdApplicable(agentId, "1").getContactdetailsMobileNumber(), new Object[]{user.getUserFirstName()});
        } catch (Exception e) {
            e.printStackTrace();
        }


        map.put("deviceOrderModel", deviceOrderModel);
        return "merchant_order_device";
    }
}
