package com.estel.controller;

import com.estel.dto.TransactionDetailModel;
import com.estel.service.TransactionService;
import com.estel.service.TransactionTypeService;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ashutosh
 * Date: 2/7/14
 * Time: 3:46 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class TransactionDetailController {
    @Autowired
    TransactionService transactionService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    TransactionTypeService typeService;

    private String FILE = "";//"/opt/estel/mpos/";

    private String pdfFileName="";
    private String csvFileName="";
    List<String> transactionTypeList;

    @RequestMapping("/transDetails")
    public String transDetails(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {

        HttpSession session = request.getSession();
        Long agentId = (Long) session.getAttribute("agentId");

        if(agentId == null)
            return "redirect:/Mpos.do";

        transactionTypeList = new ArrayList<String>();
        transactionTypeList.add("SALE");
        transactionTypeList.add("VOID");
        transactionTypeList.add("REFUND");
        map.put("transactionTypeList", transactionTypeList);
        return "admin_trans_details";
    }

    @RequestMapping("/transDetailsView")
    public String transDetailsView(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {

        HttpSession session = request.getSession();
        Long agentId = (Long) session.getAttribute("agentId");

        if(agentId == null)
            return "redirect:/Mpos.do";

        List<TransactionDetailModel> transDetailList = null;
        if (!request.getParameter("str_date").isEmpty() && !request.getParameter("end_date").isEmpty()) {

            transDetailList = transactionService.getTransDetail(
                    request.getParameter("str_date").trim() + " 00:00:00",
                    request.getParameter("end_date").trim() + " 23:59:59",
                    request.getParameter("device_num").trim(),
                    request.getParameter("last_four").trim(),
                    request.getParameter("auth_num").trim(),
                    request.getParameter("rrn_num").trim(),
                    request.getParameter("transType").trim(),
                    request.getSession().getAttribute("agentId").toString());
                    //"250");

            System.out.println("size of list is...... " + transDetailList.size());
            if (request.getParameter("report_type").equals("2") || request.getParameter("report_type").equals("3")) {
                if (transDetailList.size() > 0) {
                    if (request.getParameter("report_type").equals("2")) { //pdf
                        generateTransDetailsPDF(transDetailList);
                        return "redirect:/DownloadServlet?param1=" + pdfFileName + "";
                    }
                    if (request.getParameter("report_type").equals("3")) {
                        //csv
                        System.out.println("hi");
                        generateTransDetailsCSV(transDetailList);
                        return "redirect:/DownloadServlet?param1=" + csvFileName + "";
                    }
                } else {
                    request.getSession().setAttribute("batch_list", messageSource.getMessage("txn.id.not.exist", null, "No Records Found", null));
                    map.put("transactionTypeList", transactionTypeList);
                    return "admin_trans_details";
                }
            }
            map.put("transDetailList", transDetailList);
            return "admin_trans_details_view";
        } else {
            request.getSession().setAttribute("batch_list", messageSource.getMessage("txn.id.not.exist", null, "No Records Found", null));
            map.put("transactionTypeList", transactionTypeList);
            return "admin_trans_details";
        }
    }

    private void generateTransDetailsCSV(List<TransactionDetailModel>transDetailList){
        FileWriter writer= null;
        String line = "";
        StringBuffer buffer = new StringBuffer("");
        String seperator = ",";
        FILE = messageSource.getMessage("upload.path",null,"default-upload.path",null);
        csvFileName = FILE+"TransactionDetail_" + RandomStringUtils.randomNumeric(6)+".csv";
        try{
            writer=new FileWriter(csvFileName);
            line= "Transaction Detail Report";
            writer.append(line);
            writer.append('\n');
            line = "Date"+seperator+"Device No."+seperator+"Transaction Type"+seperator+"Amount"+seperator+"Last 4 Digits"+seperator+"Result Desc"+seperator+"Auth No."+seperator+"RREF No."+seperator+"Batch No.";
            writer.append(line);
            writer.append("\n");
            for (TransactionDetailModel aTransDetailList : transDetailList) {

                buffer= new StringBuffer("");
                buffer.append(aTransDetailList.getTransactionsResponseTs().toString()).append(seperator);
                buffer.append(aTransDetailList.getTransactionsMerchantMobileNumber()).append(seperator);
                buffer.append(aTransDetailList.getTransactionsTransTypeCode()).append(seperator);
                buffer.append(aTransDetailList.getTransactionsValue().toString()).append(seperator);
                buffer.append(aTransDetailList.getTransactionsCcLast()).append(seperator);
                buffer.append(aTransDetailList.getTransactionsResultDescription()).append(seperator);
                buffer.append(aTransDetailList.getTransactionsAuthNumber()).append(seperator);
                buffer.append(aTransDetailList.getTransactionsRrnNumber()).append(seperator);
                buffer.append(aTransDetailList.getTransactionsBatchNumber()).append(seperator);
                writer.append(buffer);
                writer.append("\n");

            }
            writer.flush();
            writer.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    private void generateTransDetailsPDF(List<TransactionDetailModel> transDetailList) {

        Document document = new Document(PageSize.A2);
        OutputStream file = null;
        FILE = messageSource.getMessage("upload.path",null,"default-upload.path",null);
        pdfFileName = FILE+"TransactionDetail_" +RandomStringUtils.randomNumeric(6)+".pdf";
        try {
            file = new FileOutputStream(new File(pdfFileName));
            PdfWriter.getInstance(document, file);
            document.open();

            HTMLWorker htmlWorker = new HTMLWorker(document);
            Paragraph paragraph1 = new Paragraph();

            paragraph1.setSpacingBefore(35);
            document.add(paragraph1);
            String str = "<html>\n" +
                    "<head>\n" +
                    "    <title></title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<table width=\"100%\" border=\"1\">\n" +
                    "    <tr>\n" +
                    "\n" +
                    "        <td align=\"center\" style=\"background-color: #B9C510;font-weight: 10;font-size: xx-large;\">Transaction Detail Report</td>\n" +
                    "\n" +
                    "    </tr>\n" +
                    "\n" +
                    "</table>\n" +
                    "<br>\n" +
                    "<br>\n" +
                    "\n" +
                    "<table width=\"100%\" border=\"1\">\n" +
                    "    <tr>\n" +
                    "        <td  align=\"center\" style=\"background-color: #B9C510;font-weight: 10;\">Date</td>\n" +
                    "        <td align=\"center\" style=\"background-color: #B9C510;font-weight: 10;\">Device No.</td>\n" +
                    "        <td  align=\"center\" style=\"background-color: #B9C510;font-weight: 10;\">Transaction Type</td>\n" +
                    "        <td align=\"center\" style=\"background-color: #B9C510;font-weight: 10;\">Amount</td>\n" +
                    "        <td align=\"center\" style=\"background-color: #B9C510;font-weight: 10;\">Last 4 Digits</td>\n" +
                    "        <td  align=\"center\" style=\"background-color: #B9C510;font-weight: 10;\">Result Desc</td>\n" +
                    "        <td  align=\"center\" style=\"background-color: #B9C510;font-weight: 10;\">Auth No.</td>\n" +
                    "        <td  align=\"center\" style=\"background-color: #B9C510;font-weight: 10;\">RREF No.</td>\n" +
                    "        <td  align=\"center\" style=\"background-color: #B9C510;font-weight: 10;\">Batch No.</td>\n" +
                    "    </tr>";

            String str1 = null;
            StringBuffer buf = new StringBuffer();


            for (TransactionDetailModel aTransDetailList : transDetailList) {
                str1 = "<tr>\n" +
                        "        <td align=\"center\" height=\"10%\">" + aTransDetailList.getTransactionsResponseTs() + "</td>\n" +
                        "        <td align=\"center\" height=\"10%\">" + aTransDetailList.getTransactionsMerchantMobileNumber() + "</td>\n" +
                        "        <td align=\"right\" height=\"10%\">" + aTransDetailList.getTransactionsTransTypeCode() + "</td>\n" +
                        "        <td align=\"right\" height=\"10%\">" + aTransDetailList.getTransactionsValue() + "</td>\n" +
                        "        <td align=\"center\"  height=\"10%\">" + aTransDetailList.getTransactionsCcLast() + "</td>\n" +
                        "        <td align=\"center\"  height=\"10%\">" + aTransDetailList.getTransactionsResultDescription() + "</td>\n" +
                        "        <td align=\"center\"  height=\"10%\">" + aTransDetailList.getTransactionsAuthNumber() + "</td>\n" +
                        "        <td align=\"center\"  height=\"10%\">" + aTransDetailList.getTransactionsRrnNumber() + "</td>\n" +
                        "        <td align=\"center\"  height=\"10%\">" + aTransDetailList.getTransactionsBatchNumber() + "</td>\n" +
                        "    </tr>";
                buf.append(str1);
            }

            String str2 = "<tr>\n" +
                    "        <td colspan=\"9\" align=\"center\" style=\"background-color: #B9C510;color: white;font-weight: 10;\"><spring:message code=\"common.message.end.of.report\"/></td>\n" +
                    "    </tr>\n" +
                    "</table>\n" +
                    "\n" +
                    "\n" +
                    "</body>\n" +
                    "\n" +
                    "</html>";
            str = str + buf + str2;

            htmlWorker.parse(new StringReader(str));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
            try {
                file.close();
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

    }
}
