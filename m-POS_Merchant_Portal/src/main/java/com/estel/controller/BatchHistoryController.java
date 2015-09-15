package com.estel.controller;

import com.estel.entity.Transaction;
import com.estel.service.TransactionService;
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
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: ashutosh
 * Date: 2/7/14
 * Time: 3:55 PM
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class BatchHistoryController {
    @Autowired
    TransactionService transactionService;

    @Autowired
    MessageSource messageSource;

    private String FILE = "";//"/opt/estel/mpos/";
    private String pdfFileName = "";
    private String csvFileName = "";

    @RequestMapping("/batchHistory")
    public String batchHistory(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {

        HttpSession session = request.getSession();
        Long agentId = (Long) session.getAttribute("agentId");

        if(agentId == null)
            return "redirect:/Mpos.do";

        return "admin_batch_history";
    }

    @RequestMapping("/batchHistoryView")
    public String batchHistoryView(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) {


        HttpSession session = request.getSession();
        Long agentId = (Long) session.getAttribute("agentId");

        if(agentId == null)
            return "redirect:/Mpos.do";

        List<Transaction> batchList = null;

        if (!request.getParameter("str_date").isEmpty() && !request.getParameter("end_date").isEmpty()) {
            batchList = transactionService.getBatchHistory(
                    request.getParameter("str_date").trim() + " 00:00:00",
                    request.getParameter("end_date").trim() + " 23:59:59",
                    request.getParameter("device_num").trim(),
                    request.getParameter("batch_num").trim(),
                    request.getSession().getAttribute("agentId").toString());
                    //"250");
            System.out.println("size of list is...... " + batchList.size());
            if (request.getParameter("report_type").equals("2") || request.getParameter("report_type").equals("3")) {
                if (batchList.size() > 0) {
                    if (request.getParameter("report_type").equals("2")) { //pdf
                        generateBatchHistoryPDF(batchList);
                        return "redirect:/DownloadServlet?param1=" + pdfFileName + "";
                    }
                    if (request.getParameter("report_type").equals("3")) {
                        //csv
                        System.out.println("hi");
                        generateBatchHistoryCSV(batchList);
                        return "redirect:/DownloadServlet?param1=" + csvFileName + "";
                    }
                } else {
                    request.getSession().setAttribute("batch_list", messageSource.getMessage("txn.id.not.exist", null, "No Records Found", null));
                    return "admin_batch_history";
                }
            }
            map.put("batchList", batchList);
            return "admin_batch_history_view";
        } else {
            request.getSession().setAttribute("batch_list", messageSource.getMessage("txn.id.not.exist", null, "No Records Found", null));
            return "admin_batch_history";
        }
    }

    private void generateBatchHistoryCSV(List<Transaction> batchList) {
        FileWriter writer = null;
        String line = "";
        StringBuffer buffer = new StringBuffer("");
        String seperator = ",";

        FILE = messageSource.getMessage("upload.path",null,"default-upload.path",null);

        csvFileName = FILE + "Batch_History_" + RandomStringUtils.randomNumeric(6) + ".csv";
        try {
            writer = new FileWriter(csvFileName);
            line = "Batch History Report";
            writer.append(line);
            writer.append('\n');
            line = "Date" + seperator + "Batch No." + seperator + "Device No." + seperator + "Transaction Type" + seperator + "Amount";
            writer.append(line);
            writer.append("\n");
            for (Transaction transaction : batchList) {

                buffer = new StringBuffer("");
                if (transaction.getTransactionsResponseTs() == null) {
                    buffer.append(seperator);
                } else {
                    buffer.append(transaction.getTransactionsResponseTs().toString()).append(seperator);
                }

                buffer.append(transaction.getTransactionsBatchNumber()).append(seperator);
                buffer.append(transaction.getTransactionsMerchantDeviceNumber()).append(seperator);
                buffer.append(transaction.getTransactionsTransTypeCode()).append(seperator);
                buffer.append(transaction.getTransactionsValue().toString()).append(seperator);

                writer.append(buffer);
                writer.append("\n");

            }
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void generateBatchHistoryPDF(List<Transaction> batchList) {

        Document document = new Document(PageSize.A4);
        OutputStream file = null;
        FILE = messageSource.getMessage("upload.path",null,"default-upload.path",null);
        pdfFileName = FILE + "Batch_History_" + RandomStringUtils.randomNumeric(6) + ".pdf";
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
                    "        <td align=\"center\" style=\"background-color: #B9C510;font-weight: 10;font-size: xx-large;\">Batch History Report</td>\n" +
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
                    "        <td  align=\"center\" style=\"background-color: #B9C510;font-weight: 10;\">Batch No.</td>\n" +
                    "        <td align=\"center\" style=\"background-color: #B9C510;font-weight: 10;\">Device No.</td>\n" +
                    "        <td  align=\"center\" style=\"background-color: #B9C510;font-weight: 10;\">Transaction Type</td>\n" +
                    "        <td align=\"center\" style=\"background-color: #B9C510;font-weight: 10;\">Amount</td>\n" +
                    "    </tr>";

            String str1 = null;
            StringBuffer buf = new StringBuffer();


            for (Transaction transaction : batchList) {
                if (transaction.getTransactionsResponseTs() == null) {
                    str1 = "<tr>\n" +
                            "        <td align=\"center\" height=\"10%\"></td>\n" +
                            "        <td align=\"center\"  height=\"10%\">" + transaction.getTransactionsBatchNumber() + "</td>\n" +
                            "        <td align=\"center\" height=\"10%\">" + transaction.getTransactionsMerchantDeviceNumber() + "</td>\n" +
                            "        <td align=\"right\" height=\"10%\">" + transaction.getTransactionsTransTypeCode() + "</td>\n" +
                            "        <td align=\"right\" height=\"10%\">" + transaction.getTransactionsValue() + "</td>\n" +
                            "    </tr>";
                } else {
                    str1 = "<tr>\n" +
                            "        <td align=\"center\" height=\"10%\">" + transaction.getTransactionsResponseTs() + "</td>\n" +
                            "        <td align=\"center\"  height=\"10%\">" + transaction.getTransactionsBatchNumber() + "</td>\n" +
                            "        <td align=\"center\" height=\"10%\">" + transaction.getTransactionsMerchantDeviceNumber() + "</td>\n" +
                            "        <td align=\"right\" height=\"10%\">" + transaction.getTransactionsTransTypeCode() + "</td>\n" +
                            "        <td align=\"right\" height=\"10%\">" + transaction.getTransactionsValue() + "</td>\n" +
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
