package com.estel.controller;

import com.estel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: estel
 * Date: 18/12/13
 * Time: 4:26 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class ReportController {

    @Autowired
    UserService userService;

    @Autowired
    MessageSource messageSource;

    public String getAgentName(HttpServletRequest request) {

        HttpSession session = request.getSession();
        Long agentId = (Long) session.getAttribute("agentId");

        if (agentId == null)
            return "redirect:/Mpos.do";

        return userService.getUserByAgentId(agentId).getUserFirstName();
    }

    public Long getAgentId(HttpServletRequest request) {

        HttpSession session = request.getSession();
        Long agentId = (Long) session.getAttribute("agentId");

        return agentId;
    }

    @RequestMapping(value = "/Report.do", method = RequestMethod.GET)
    public String showReport(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {

        HttpSession session = request.getSession();
        String str = messageSource.getMessage("report.url", new Object[]{request.getServerName(), String.valueOf(request.getServerPort())}, "default-report.url", null);
        session.setAttribute("userName", this.getAgentName(request));
        session.setAttribute("agentId1", this.getAgentId(request));
        session.setAttribute("urlReport", str);
        return "merchant_report";
    }

}
