package com.estel.controller;

import com.estel.entity.Chargeslip;
import com.estel.entity.User;
import com.estel.service.ChargeslipService;
import com.estel.service.EmailService;
import com.estel.service.UserService;
import com.estel.utility.Pagination;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

//import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

/**
 * Created with IntelliJ IDEA.
 * User: estel
 * Date: 26/12/13
 * Time: 2:23 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class ReceiptController {

    @Autowired
    ChargeslipService chargeslipService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    UserService userService;

    @Autowired
    EmailService emailService;

    @RequestMapping("/searchReceipt")
    public String provideSearchReceiptForm(HttpServletRequest request, Map<String, Object> map) {

        HttpSession session = request.getSession();
        Long agentId = (Long) session.getAttribute("agentId");

        if(agentId == null)
            return "redirect:/Mpos.do";

        return "merchant_receipt";
    }

    @RequestMapping(value = "/searchReceiptByTransactionIdorDate", method = {RequestMethod.GET,RequestMethod.POST})
    public String searchReceiptByTransactionIdorDate(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {


        HttpSession session = request.getSession();
        Long agentId = (Long) session.getAttribute("agentId");

        if(agentId == null)
            return "redirect:/Mpos.do";

        if(request.getParameter("fromDate") != null && !request.getParameter("fromDate").equalsIgnoreCase(""))
        {
            session.setAttribute("fromDate",request.getParameter("fromDate"));
            session.setAttribute("toDate",request.getParameter("toDate"));
            session.setAttribute("txnId",request.getParameter("txnId"));
        }

        //String txnId = request.getParameter("txnId");
        String txnId = (String) session.getAttribute("txnId");
        String fromDate = (String) session.getAttribute("fromDate");
        String toDate = (String) session.getAttribute("toDate");


        SimpleDateFormat formatter1 = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = formatter1.parse(fromDate +" 00:00:00");
            date2 = formatter1.parse(toDate +" 23:59:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (txnId != null)      //&& !txnId.trim().equalsIgnoreCase("")
        {

            List<Chargeslip> chargeslipList = chargeslipService.listChargeslipsFromDateToDate(txnId, date1, date2,agentId);
            if (chargeslipList == null) {
                request.getSession().setAttribute("receipt_info_msg", messageSource.getMessage("txn.id.not.exist", null, "default-txn.id.not.exist", null));
            } else {
                if (chargeslipList.size() < 1)
                    request.getSession().setAttribute("receipt_info_msg", messageSource.getMessage("txn.id.not.exist", null, "default-txn.id.not.exist", null));
            }




            List<Chargeslip> chargeslipListTemp = null;

            if (chargeslipList != null) {
                try {
                    chargeslipListTemp = Pagination.getSubList(chargeslipList, request);
                } catch (Exception e) {
                    e.printStackTrace();
                }
               /* deviceOrderModel.setDeviceOrderList(deviceOrderListTemp);
                deviceOrderModel.setDeviceTypeList(deviceTypeList);
                deviceOrderModel.setStateList(stateList);*/

                map.put("chargeslipList", chargeslipListTemp);

            } else {
               /* deviceOrderModel.setDeviceOrderList(deviceOrderList);
                deviceOrderModel.setDeviceTypeList(deviceTypeList);
                deviceOrderModel.setStateList(stateList);*/

                map.put("chargeslipList", chargeslipList);
            }

        } else {
            request.getSession().setAttribute("receipt_info_msg", messageSource.getMessage("txn.id.not.exist", null, "default-txn.id.not.exist", null));
        }
        return "merchant_receipt";

    }


    @RequestMapping(value = "/receiptView", method = RequestMethod.GET)
    public String receiptView(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {

        HttpSession session = request.getSession();
        Long agentId = (Long) session.getAttribute("agentId");

        if(agentId == null)
            return "redirect:/Mpos.do";


        String receiptId = request.getParameter("receiptId");
        if (receiptId != null) {

            Chargeslip chargeslip = chargeslipService.getChargeslipById(Long.parseLong(receiptId));
            byte[] imgBytes = chargeslip.getChargeslipByte();

            try {
                //store image
                String attachmentFileName = "sale_receipt.png";
                String relativeWebPath = "/resources/images";
                ServletContext context = request.getSession().getServletContext();
                String absoluteDiskPath = context.getRealPath(relativeWebPath);

                InputStream in = new ByteArrayInputStream(imgBytes);
                BufferedImage bImageFromConvert = ImageIO.read(in);
                ImageIO.write(bImageFromConvert, "png", new File(absoluteDiskPath + "/" + attachmentFileName));
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

            String encodedImage = Base64.encode(imgBytes);

            map.put("chargeslip", chargeslip);
            //map.put("imageData","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");
            map.put("imageData", encodedImage);

        }
        return "merchant_receipt_view";
    }


    @RequestMapping(value = "/sendEmailReceipt", method = RequestMethod.POST)
    public String sendEmailReceipt(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {

        HttpSession session = request.getSession();
        Long agentId = (Long)session.getAttribute("agentId");

        if(agentId == null)
            return "redirect:/Mpos.do";

        String emailtosend = request.getParameter("emailtosend");

        String attachmentFileName = "sale_receipt.png";
        String relativeWebPath = "/resources/images";
        ServletContext context = request.getSession().getServletContext();
        String absoluteDiskPath = context.getRealPath(relativeWebPath);
        String attachmentFilePath = absoluteDiskPath + "/" + attachmentFileName;

        User user = userService.getUserByAgentId(agentId);
        try {
            emailService.sendEmailWithAttachment(emailtosend, messageSource.getMessage("SALE-EMAIL", null, "default-SALE-EMAIL", null), "SALE-EMAIL", attachmentFilePath, attachmentFileName, new Object[]{user.getUserFirstName()});
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getSession().setAttribute("email_sent_msg", messageSource.getMessage("email.sent.msg", null, "default-email.sent.msg", null));
        return "merchant_receipt_view";
    }

}