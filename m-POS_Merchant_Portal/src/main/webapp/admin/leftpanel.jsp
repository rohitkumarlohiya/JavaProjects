<%@ page import="com.estel.controller.ReportController" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="javax.mail.Message" %>
<%@ page import="com.sun.mail.imap.protocol.MessageSet" %>
<%@ page import="org.springframework.context.MessageSource" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<%-- js include for report section--%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/style.default.css">
<!-- Left Panel Starts Here -->
<script type="text/javascript">
   /* function doReport()
    {
        window.location.href = "Report.do";
    }*/
</script>
<%--Left pannel start here --%>
<div class="vernav2 iconmenu">

    <ul>
        <li>
            <a href="#mpos" class="gallery"><spring:message code="left.MPOS"/>
            </a>
            <span class="arrow"></span>
            <ul id="mpos">
                <li><a href="merchantBusinessInfoAction.do"><spring:message code="left.BusinessInformation"/>
                </a></li>

                <li><a href="merchantProfileAction.do"><spring:message code="left.MerchantProfile"/>
                </a></li>

                <li><a href="bankAccountList.do"><spring:message code="left.BankAccount"/>
                </a></li>
                <li><a href="deviceList.do"><spring:message code="left.RegisterDevice"/>
                </a></li>
                <li><a href="orderDevice.do"><spring:message code="left.order.card.reader"/>
                </a></li>
                <li><a href="searchReceipt.do"><spring:message code="left.view.receipt"/>
                </a></li>
            </ul>
        </li>

        <li>
            <a href="#myaccount" class="gallery"><spring:message code="left.MyAccount"/>
            </a>
            <span class="arrow"></span>
            <ul id="myaccount">
                <li><a href="changePassword.do"><spring:message code="left.changePassword"/>
                </a></li>
                <li><a href="changeMpin.do"><spring:message code="left.ChangeMPIN"/>
                </a></li>
                <li><a href="forgotMpin.do"><spring:message code="left.ForgotMPin"/>
                </a></li>
                <%--<li><a href="#"><%=prop.getProperty("left.Notification")%></a></li>
               <li><a href="#"><%=prop.getProperty("left.Settings")%></a></li>--%>
            </ul>
        </li>

          <%--  <%
                // WebApplicationContext context1 = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
                WebApplicationContext webAppContext1 = (WebApplicationContext) session.getAttribute("webAppContext");
                String str = "";
                String agentName = "";
                String agentIdStr ="";
                if (webAppContext1 != null) {

                    ReportController reportController = webAppContext1.getBean("reportController", ReportController.class);
                    agentName = reportController.getAgentName(request);
                    agentIdStr = String.valueOf(reportController.getAgentId(request));
                    MessageSource messageSource = webAppContext1.getBean("messageSource", MessageSource.class);
                    str = messageSource.getMessage("report.url", new Object[]{request.getServerName(),String.valueOf(request.getServerPort()),agentName, agentIdStr}, "default-report.url", null);
                    System.out.println("report.url = " + str);
                }
            %>--%>
       <%-- <li onclick="reportPost('<%=agentName%>','<%=agentIdStr%>','<%=str%>')">--%>
        <%--<li onclick="doReport()">--%>


        <%--<li onclick="window.open('<%=request.getContextPath()%>/Report.do')">
            <a href="#" class="navtext_link1"><spring:message code="left.Report"/></a>
            &lt;%&ndash;<a href="<%=str%>" onclick="window.open(this.href); return false;"
               onkeypress="window.open(this.href); return false;"
               class="gallery"><spring:message code="left.Report"/></a>&ndash;%&gt;
        </li>--%>

        <li>
            <a href="#report" class="navtext_link1"><spring:message code="left.Report"/>
            </a>
            <span class="arrow"></span>
            <ul id="report">
                <li><a href="batchHistory.do"><spring:message code="batch.history"/>
                </a></li>
                <li><a href="transDetails.do"><spring:message code="transaction.detail"/>
                </a></li>
                <li><a href="transSummary.do"><spring:message code="transaction.summary"/>
                </a></li>
            </ul>
        </li>


        <li><a href="logout.do" class="gallery"><spring:message code="left.Logout"/>
        </a></li>

    </ul>

    <a class="togglemenu"></a>
    <br/><br/>
</div>