<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="html" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>

<meta charset="utf-8"/>
<%--<title>jQuery UI Datepicker - Default functionality</title>--%>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css"/>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<%--<link rel="stylesheet" href="/resources/demos/style.css"/>--%>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<title><spring:message code="common.message.title"/>
</title>
<meta http-equiv="Content-Language" content="en-us">
<meta name="GENERATOR" content="Microsoft FrontPage 5.0">
<meta name="ProgId" content="FrontPage.Editor.Document">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<style type="text/css">
</style>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/stylesheet/style.css">
<script src="<%=request.getContextPath()%>/resources/js/leftpanel.js" type="text/javascript"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/stylesheet/style.default.css" type="text/css"/>

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/plugins/jquery-1.7.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/plugins/jquery-ui-1.8.16.custom.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/plugins/jquery.cookie.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/plugins/jquery.uniform.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/plugins/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/plugins/jquery.tagsinput.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/plugins/charCount.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/plugins/ui.spinner.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/custom/general.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/custom/forms.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/includes/js/validations.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/includes/js/validations_client.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/mpos.js"></script>

    <%--for calander--%>
    <link rel='stylesheet' href='<%=request.getContextPath()%>/resources/js/calendar/calendar.css' title='calendar'>
    <script language="javascript" src="<%=request.getContextPath()%>/resources/js/calendar/calendar.js"></script>
    <%--for calander--%>


    <!--[if lte IE 8]>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/resources/js/plugins/excanvas.min.js"></script><![endif]-->
<!--[if IE 9]>
<link rel="stylesheet" media="screen" href="<%=request.getContextPath()%>/resources/css/style.ie9.css"/>
<![endif]-->
<!--[if IE 8]>
<link rel="stylesheet" media="screen" href="<%=request.getContextPath()%>/resources/css/style.ie8.css"/>
<![endif]-->
<!--[if lt IE 9]>
<script src="<%=request.getContextPath()%>/resources/css/css3-mediaqueries.js"></script>
<![endif]-->


<script type="text/javascript">

    function clearall() {
        document.getElementById("fromDate_error").innerHTML = "";
        document.getElementById("toDate_error").innerHTML = "";
        document.getElementById("txnId_error").innerHTML = "";
        document.getElementById('result_id').style.display = 'none';
    }



    function validateForm() {
        var fromdateformat = true;
        var todateformat = true;
        var flag = -1;

        clearall();
        var startdate= document.getElementById("fromDate").value;
        var endDate= document.getElementById("toDate").value;
        if (startdate == "" || startdate == null) {
            document.getElementById("fromDate_error").innerHTML = '<spring:message code="enter.date"/>';
            return false;
        }

        flag = isCorrect(startdate, 'date', 'fromDate_error', '<spring:message code='common.future.date.selected'/>');
        if(!flag)return false;



        if (endDate == "" || endDate == null) {
            document.getElementById("toDate_error").innerHTML = '<spring:message code="enter.date"/>';
            return false;
        }

        flag = isCorrect(endDate, 'date', 'toDate_error', '<spring:message code='common.future.date.selected'/>');
        if(!flag)return false;

        var valid = after(startdate,"fromDate_error", endDate,"toDate_error");
        if(!valid)return false;


        document.forms[1].action = "searchReceiptByTransactionIdorDate.do";
        document.forms[1].method = "post";
        document.forms[1].submit();
    }


    window.history.forward();
    function noBack() {
        window.history.forward();
    }


    /*$(function () {
        $("#fromDate").datepicker();
        $("#toDate").datepicker();
    });*/

</script>
</head>
<body class="withvernav" onload="setFocus(document.getElementById('fromDate'))">

<div class="bodywrapper">

    <!--  header  start-->
    <%@include file="header1.jsp" %>
    <!--  header  end -->

    <%--<div class="water-mark-bg">--%>
    <%--  leftmenu start--%>
    <%@include file="leftpanel.jsp" %>
    <!--leftmenu-->

    <%--  leftmenu end --%>

    <div class="centercontent">

        <%@include file="breadcrums.jsp" %>

        <div class="pageheader">
            <h1 class="pagetitle"><spring:message code="left.view.receipt"/>
            </h1>
            <%--<span class="pagedesc"></span>--%>

            <ul class="hornav">
                <%-- <li class="current"><a href="#basicform">Basic</a></li>--%>
                <%-- <li><a href="#validation">Validation</a></li>--%>
            </ul>
        </div>
        <!--pageheader-->

        <div id="contentwrapper" class="contentwrapper" style="min-height:400px;">

            <div id="basicform" class="subcontent">

                <br>
                   <form></form>
                <form autocomplete="off" onCopy="return false" onPaste="return false">
                    <table border="0">

                        <tr>
                            <td colspan="4">
                                         <span id="result_id" class="result_color" align="left">
                                    <%
                                        if (session.getAttribute("receipt_info_msg") != null) {
                                    %>
                                    <%=session.getAttribute("receipt_info_msg")%>
                                    <%
                                        }
                                        session.removeAttribute("receipt_info_msg");

                                    %>   </span>
                            </td>
                        </tr>
                        <tr>
                            <td><p></p></td>
                        </tr>


                        <tr>
                            <td align='left' valign='left' class="extra">
                                <spring:message code="from.date"/> : <sup
                                    style="color:#ff0000; ">*</sup>
                            </td>

                            <td width="261px" >

                                <input type="text" id="fromDate" name="fromDate" readonly="readonly" autocomplete="off" tabindex="1">
                            </td>
                            <td>
                                <img src='<%=request.getContextPath()%>/resources/js/calendar/cal.gif' alt="cal" align='absmiddle'
                                     onmouseover="fnInitCalendar(this, 'fromDate', 'style=calendar_blue.css,restrict=true,close=true')">
                            </td>
                            <td >
                                <span class="error_color" id="fromDate_error"></span>
                            </td>
                        </tr>

                        <tr>
                            <td align='left' valign='left' class="extra"><spring:message code="end.date"/> : <sup
                                    style="color:#ff0000; ">*</sup>
                            </td>
                            <td>

                                <input type="text" id="toDate" name="toDate" readonly="readonly" autocomplete="off" tabindex="2">
                            </td>
                            <td>
                                <img src='<%=request.getContextPath()%>/resources/js/calendar/cal.gif' alt="cal" align='absmiddle'
                                     onmouseover="fnInitCalendar(this, 'toDate', 'style=calendar_blue.css,restrict=true,close=true')">
                            </td>
                            <td >
                                <span class="error_color" id="toDate_error"></span></td>

                        </tr>
                        <tr>

                            <td align='left' valign='left' class="extra">
                                <spring:message code="transaction.id"/> :
                            </td>
                            <td><input type="text" name="txnId" id="txnId" maxlength='<%=webAppContext.getMessage("transaction.id.length",null,"default-transaction.id.length",null)%>'
                                       onkeypress="return MyTrapEnter(this,event,'numeric');" tabindex="3"/></td>

                            <td><span class="error_color" id="txnId_error"></span></td>
                        </tr>
                        <tr>
                            <td class="extra1" colspan="4"><p></p></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td>
                                <div class="buttonwrapper" valign="left"><a
                                        class="btn btn_orange btn_search radius50" href="#"
                                        onclick="validateForm();"
                                        tabindex="4"><span><spring:message
                                        code="common.message.search"/></span></a>
                                </div>
                            </td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td class="extra1" colspan="4"><p></p></td>
                        </tr>
                    </table>

                    <input type="hidden" name="straction" value="searchReceiptByTransactionIdorDate.do"/>
                    <c:if test="${!empty chargeslipList}">
                        <%@include file="pagination.jsp" %>
                    </c:if>

                    <c:if test="${!empty chargeslipList}">
                        <table cellpadding="0" cellspacing="0" border="0" class="stdtable">
                            <colgroup>
                                <col class="con0"/>
                                <col class="con0"/>
                                <col class="con1"/>
                                <col class="con0"/>
                            </colgroup>
                            <thead>
                            <tr>
                                <th class="head0"><spring:message code="receipt.generation.date"/></th>
                                <th class="head1"><spring:message code="receipt.last.four.digit"/></th>
                                <th class="head0"><spring:message code="receipt.amount"/></th>
                                <th class="head1"><spring:message code="receipt.charge.slip"/></th>
                            </tr>
                            </thead>
                            <tbody>

                            <c:forEach var="chargeslip" items="${chargeslipList}">
                                <tr>
                                    <td width="25%"><c:out value="${chargeslip.chargeslipResponseTs}"/></td>
                                    <td width="25%"><c:out value="${chargeslip.chargeslipLastFourDigit}"/></td>
                                    <td width="17%"><c:out value="${chargeslip.chargeslipAmount}"/></td>
                                    <td width="33%">
                                        <a href="receiptView.do?receiptId=<c:out value='${chargeslip.chargeslipId}'/>"
                                           target="_blank" class="btn btn_cloud"><span><spring:message
                                                code="common.message.view"/></span></a>

                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                </form>


                <br>

                <br/>


            </div>


        </div>

    </div>

    <!-- centercontent -->

    <%--</div>--%>
    <div class="clear"></div>

    <%-- footer start--%>
    <div class="footer1">

        <%@include file="footer.jsp" %>

    </div>

    <%-- footer end --%>
</div>
<!--bodywrapper-->

</body>

</html>
