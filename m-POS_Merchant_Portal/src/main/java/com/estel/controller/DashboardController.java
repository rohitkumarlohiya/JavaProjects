package com.estel.controller;

import com.estel.entity.*;
import com.estel.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class DashboardController {

    @Autowired
    MessageSource messageSource;

    @Autowired
    BankService bankService;

    @Autowired
    DeviceService deviceService;

    @Autowired
    BusinessService businessService;

    @Autowired
    ContactdetailService contactdetailService;

    @Autowired
    DeviceOrderService deviceOrderService;

    @Autowired
    StatusService statusService;


    @RequestMapping("/showDashboard")
    public String showDashboard(HttpServletRequest request, Map<String, Object> map) {

        ///////Added for sequence registration
        HttpSession session = request.getSession();
        Long agentId = (Long) session.getAttribute("agentId");

        if(agentId == null)
            return "redirect:/Mpos.do";

        if (agentId != null) {

            List<Device> deviceList = deviceService.listDeviceByAgentId(agentId);
            if (deviceList == null) {
                map.put("registration_status", "incomplete");
                session.setAttribute("stepNo", "4");
            }

            List<Bank> bankList = bankService.listBankByAgentId(agentId);
            if (bankList == null) {
                session.setAttribute("stepNo", "3");
            }

            Contactdetail mbAddress = contactdetailService.getContactdetailByAgentIdAndBdApplicable(agentId, "1");
            if (mbAddress != null && mbAddress.getContactdetailsContactName() == null) {
                session.setAttribute("stepNo", "2");
            }

            Business mbBusiness = businessService.getBusinessByAgentId(agentId);
            if (mbBusiness == null) {
                session.setAttribute("stepNo", "1");
            }

            if (session.getAttribute("stepNo") == null) {
                session.setAttribute("stepNo", "5");
            }
            //////
        }

        List<DeviceOrder> deviceOrderList = deviceOrderService.listDeviceOrderByAgentId(agentId);
        List<Status> statusList = statusService.listStatuss();
        map.put("deviceOrderList",deviceOrderList);
        map.put("statusList",statusList);

        return "dashboard";
    }
}
