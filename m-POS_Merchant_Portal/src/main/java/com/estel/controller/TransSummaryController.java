package com.estel.controller;

import com.estel.dto.TransactionDetailModel;
import com.estel.dto.TransactionModel;
import com.estel.entity.Transaction;
import com.estel.service.TransactionService;
import com.estel.service.TransactionTypeService;
import com.itextpdf.text.*;
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
 * Date: 1/29/14
 * Time: 5:43 PM
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class TransSummaryController {


    @Autowired
    TransactionService transactionService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    TransactionTypeService typeService;

    private String FILE = "";//"/opt/estel/mpos/";

    private String pdfFileName = "";
    private String csvFileName = "";

    @RequestMapping("/transSummary")
    public String transSummary(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {

        HttpSession session = request.getSession();
        Long agentId = (Long) session.getAttribute("agentId");

        if(agentId == null)
            return "redirect:/Mpos.do";

        return "admin_trans_summary";
    }

    @RequestMapping("/transSummaryView")
    public String transSummaryView(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {

        HttpSession session = request.getSession();
        Long agentId = (Long) session.getAttribute("agentId");

        if(agentId == null)
            return "redirect:/Mpos.do";

        List<TransactionModel> transSumList = null;
        if (!request.getParameter("str_date").isEmpty() && !request.getParameter("end_date").isEmpty()) {
            transSumList = transactionService.getTransSummary(
                    request.getParameter("str_date").trim() + " 00:00:00",
                    request.getParameter("end_date").trim() + " 23:59:59",
                    request.getSession().getAttribute("agentId").toString());
                    //"250");
            System.out.println("size of list is...... " + transSumList.size());
            if (request.getParameter("report_type").equals("2") || request.getParameter("report_type").equals("3")) {
                if (transSumList.size() > 0) {
                    if (request.getParameter("report_type").equals("2")) { //pdf
                        generateTransSummaryPDF(transSumList);
                        return "redirect:/DownloadServlet?param1=" + pdfFileName + "";
                    }
                    if (request.getParameter("report_type").equals("3")) {
                        //csv
                        generateTransSummaryCSV(transSumList);
                        return "redirect:/DownloadServlet?param1=" + csvFileName + "";
                    }
                } else {
                    request.getSession().setAttribute("batch_list", messageSource.getMessage("txn.id.not.exist", null, "No Records Found", null));
                    return "admin_trans_summary";
                }
            }
            map.put("transSumList", transSumList);
            return "admin_trans_summary_view";
        } else {
            request.getSession().setAttribute("batch_list", messageSource.getMessage("txn.id.not.exist", null, "No Records Found", null));
            return "admin_trans_summary";
        }

    }

    private void generateTransSummaryCSV(List<TransactionModel> transSumList){
        FileWriter writer;
        String line = "";
        StringBuffer buffer = new StringBuffer("");
        String seperator = ",";
        FILE = messageSource.getMessage("upload.path",null,"default-upload.path",null);
        csvFileName = FILE+"Transaction_summary_" + RandomStringUtils.randomNumeric(6)+".csv";
        try{
            writer=new FileWriter(csvFileName);
            line= "Transaction Summary Report";
            writer.append(line);
            writer.append('\n');
            line = "Date"+seperator+"Device No."+seperator+"Transaction Type"+seperator+"Amount"+seperator+"No. of Transactions";
            writer.append(line);
            writer.append("\n");
            for (TransactionModel model : transSumList) {

                buffer= new StringBuffer("");
                if (model.getTransactionsResponseDate()== null){
                    buffer.append(seperator);
                }
                else {
                    buffer.append(model.getTransactionsResponseDate().toString()).append(seperator);
                }

                if (model.getTransactionsMobileNumber()== null){
                    buffer.append(seperator);
                }
                else {
                    buffer.append(model.getTransactionsMobileNumber()).append(seperator);
                }
                buffer.append(model.getTransactionsTransTypeCode()).append(seperator);
                buffer.append(model.getTransactionsValue()).append(seperator);
                buffer.append(model.getTransactionscount().toString()).append(seperator);

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

    private void generateTransSummaryPDF(List<TransactionModel> transSumList) {

        Document document = new Document(PageSize.A4);
        OutputStream file = null;
        FILE = messageSource.getMessage("upload.path",null,"default-upload.path",null);
        pdfFileName = FILE+"transaction_Summary_" +RandomStringUtils.randomNumeric(6)+".pdf";
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
                    "        <td align=\"center\" style=\"background-color: #B9C510;font-weight: 10;font-size: xx-large;\">Transaction summary Report</td>\n" +
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
                    "        <td  align=\"center\" style=\"background-color: #B9C510;font-weight: 10;\">Amount</td>\n" +
                    "        <td align=\"center\" style=\"background-color: #B9C510;font-weight: 10;\">No. of Transactions</td>\n" +
                    "    </tr>";

            String str1 = null;
            StringBuffer buf = new StringBuffer();


            for (TransactionModel model : transSumList) {
                if (model.getTransactionsResponseDate()== null){
                    str1 = "<tr>\n" +
                            "        <td align=\"center\" height=\"10%\"></td>\n" +
                            "        <td align=\"center\"  height=\"10%\">" + model.getTransactionsMobileNumber() + "</td>\n" +
                            "        <td align=\"center\" height=\"10%\">" + model.getTransactionsTransTypeCode() + "</td>\n" +
                            "        <td align=\"right\" height=\"10%\">" + model.getTransactionsValue() + "</td>\n" +
                            "        <td align=\"right\" height=\"10%\">" + model.getTransactionscount() + "</td>\n" +
                            "    </tr>";
                }
                else {
                    str1 = "<tr>\n" +
                            "        <td align=\"center\" height=\"10%\">" + model.getTransactionsResponseDate() + "</td>\n" +
                            "        <td align=\"center\"  height=\"10%\">" + model.getTransactionsMobileNumber() + "</td>\n" +
                            "        <td align=\"center\" height=\"10%\">" + model.getTransactionsTransTypeCode() + "</td>\n" +
                            "        <td align=\"right\" height=\"10%\">" + model.getTransactionsValue() + "</td>\n" +
                            "        <td align=\"right\" height=\"10%\">" + model.getTransactionscount() + "</td>\n" +
                            "    </tr>";
                }
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