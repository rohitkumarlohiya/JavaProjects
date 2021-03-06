<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="html" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
<script type="text/javascript"
        src="<%=request.getContextPath()%>/resources/js/plugins/jquery-ui-1.8.16.custom.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/plugins/jquery.cookie.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/plugins/jquery.uniform.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/plugins/jquery.validate.min.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/resources/js/plugins/jquery.tagsinput.min.js"></script>
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
<script language="javascript" type="text/javascript"
        src="<%=request.getContextPath()%>/resources/js/plugins/excanvas.min.js"></script><![endif]-->
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
        document.getElementById("str_date_err").innerHTML = "";
        document.getElementById("end_date_err").innerHTML = "";
        document.getElementById("device_num_err").innerHTML = "";
        document.getElementById("report_type_err").innerHTML = "";

    }


    function validateForm() {
        document.getElementById('result_id').style.display = 'none';
        clearall();
        var flag;
        var reqType = document.detail.pageAction.value = "report";
        var startdate = document.detail.str_date.value;
        var endDate = document.detail.end_date.value;
        var deviceNum = document.detail.device_num.value;
        var lastFour = document.detail.last_four.value;
        var authNum = document.detail.auth_num.value;
        var rrn = document.detail.rrn_num.value;
        var tType = document.detail.transType.value;
        var reportType = document.detail.report_type.value;

        if (startdate == "" || startdate == null) {
            document.getElementById("str_date_err").innerHTML = '<spring:message code="enter.date"/>';
            //alert("hi");
            return false;
        }

        flag = isCorrect(startdate, 'date', 'str_date_err', '<spring:message code='common.future.date.selected'/>');
        if(!flag)return false;

        if (endDate == "" || endDate == null) {
            document.getElementById("end_date_err").innerHTML = '<spring:message code="enter.date"/>';
            return false;
        }

        flag = isCorrect(endDate, 'date', 'end_date_err', '<spring:message code='common.future.date.selected'/>');
        if(!flag)return false;


        var valid = after(startdate, 'str_date_err', endDate, 'end_date_err');
        if (!valid)return false;

        if (!(deviceNum == "" || deviceNum == null)) {
            flag = isCorrect(deviceNum, 'numeric', 'device_num_err', '<spring:message code='common.message.error.numeric'/>');
            if(!flag)return false;
            flag = isMinMax(deviceNum, parseInt('<spring:message code="mobile.number.length.min"/>'), parseInt('<spring:message code="mobile.number.length"/>'), 'device_num_err', '<spring:message code="common.message.error.validnum"/>');
            if(!flag)return false;
        }

        if(!(lastFour == "" || lastFour == null))  {
            flag = isCorrect(lastFour, 'numeric', 'last_four_err', '<spring:message code='common.message.error.numeric'/>');
            if(!flag)return false;
        }

        if (!(authNum == "" || authNum == null)) {
            flag = isCorrect(authNum, 'numeric', 'auth_num_err', '<spring:message code='common.message.error.numeric'/>');
            if(!flag)return false;
        }

        if (!(rrn == "" || rrn == null)) {
            flag = isCorrect(rrn, 'numeric', 'rrn_num_err', '<spring:message code='common.message.error.numeric'/>');
            if(!flag)return false;
        }

        if (document.getElementById("report_type").value < 1) {
            document.getElementById("report_type_err").innerHTML = "please select report type";
            return false;
        }

        if (reportType == "1") {
            var myWindow = window.open("", "myWin", "scrollbars=1,width=2000,height=1000");
            myWindow.document.write("<form id='myform' name='myform' action='transDetailsView.do' method='post'>"
                    + "<input type='hidden' id='pageAction' name='pageAction' value='" + reqType + "'>"
                    + "<input type='hidden' id='str_date' name='str_date' value=' " + startdate + "'>"
                    + "<input type='hidden' id='end_date' name='end_date' value=' " + endDate + "'>"
                    + "<input type='hidden' id='device_num' name='device_num' value=' " + deviceNum + "'>"
                    + "<input type='hidden' id='last_four' name='last_four' value=' " + lastFour + "'>"
                    + "<input type='hidden' id='auth_num' name='auth_num' value=' " + authNum + "'>"
                    + "<input type='hidden' id='rrn_num' name='rrn_num' value=' " + rrn + "'>"
                    + "<input type='hidden' id='transType' name='transType' value=' " + tType + "'>"
                    + "<input type='hidden' id='report_type' name='report_type' value='" + reportType + "'>"
                    + "</form> <script>document.myform.submit();" + "</s" + "cript>");
            return false;
        }
        document.detail.pageAction.value = "report";
        document.detail.method = "post";
        document.detail.action = "transDetailsView.do";
        document.detail.submit();
    }

    window.history.forward();
    function noBack() {
        window.history.forward();
    }
   /* $(function () {
        $("#str_date").datepicker();
        $("#end_date").datepicker();
    });*/

</script>


</head>
<body class="withvernav" onload="noBack();setFocus(document.getElementById('str_date'));"
      onpageshow="if (event.persisted) noBack();" onunload="">

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
    <h1 class="pagetitle"><spring:message code="report.transaction.detail"/>
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

<form name="detail" id="detail" method="post" autocomplete="off" onCopy="return false" onPaste="return false">
    <input type="hidden" name="pageAction" id="pageAction" value="">
    <table border="0">

        <tr>
            <td colspan="3">
                                         <span id="result_id" class="result_color" align="left">
                                    <%
                                        if (session.getAttribute("batch_list") != null) {
                                    %>
                                    <%=session.getAttribute("batch_list")%>
                                    <%
                                        }
                                        session.removeAttribute("batch_list");

                                    %>   </span>
            </td>
        </tr>


    </table>

    <table border="0">

        <%--<tr>

            <td align='left' valign='left' class="extra"><spring:message code="common.message.startdate"/>

                :<font color=red>*</font></td>
            <td>

                <input type="text" id="str_date" name="str_date"
                       autocomplete="off" tabindex="1">
            </td>
            <td>
                <span id="str_date_err" class="error_color"></span>
            </td>
        </tr>

        <tr>
            <td align='left' valign='left' class="extra"><spring:message code="common.message.enddate"/>

                :<font color=red>*</font></td>
            <td>
                <input type="text" id="end_date" name="end_date"
                       autocomplete="off" tabindex="2">
            </td>

            <td>
                <span id="end_date_err" class="error_color"></span>
            </td>
        </tr>--%>

            <tr>

                <td align='left' valign='left' class="extra"><spring:message
                        code="common.message.startdate"/>

                    :<font color=red>*</font></td>
                <td>

                    <input type="text" id="str_date" name="str_date" readonly="readonly" autocomplete="off" tabindex="1">
                </td>
                <td>
                    <img src='<%=request.getContextPath()%>/resources/js/calendar/cal.gif' alt="cal" align='absmiddle'
                         onmouseover="fnInitCalendar(this, 'str_date', 'style=calendar_blue.css,restrict=true,close=true')">
                </td>
                <td>
                    <span id="str_date_err" class="error_color"></span>
                </td>
            </tr>

            <tr>
                <td align='left' valign='left' class="extra"><spring:message code="common.message.enddate"/>

                    :<font color=red>*</font></td>
                <td>
                    <input type="text" id="end_date" name="end_date" readonly="readonly" autocomplete="off" tabindex="2">
                </td>
                <td>
                    <img src='<%=request.getContextPath()%>/resources/js/calendar/cal.gif' alt="cal" align='absmiddle'
                         onmouseover="fnInitCalendar(this, 'end_date', 'style=calendar_blue.css,restrict=true,close=true')">
                </td>
                <td>
                    <span id="end_date_err" class="error_color"></span>
                </td>
            </tr>

        <tr>
            <td align='left' valign='left' class="extra"><spring:message code="common.message.device.num"/>

                :
            </td>

            <td>
                <input type="text" id="device_num" name="device_num" onkeypress="return MyTrapEnter(this,event,'numeric');"
                       autocomplete="off" tabindex="3" maxlength="15">
            </td>

            <td>
                <span class="error_color" id="device_num_err"></span>
            </td>

        </tr>
        <tr>
            <td align='left' valign='left' class="extra"><spring:message code="last.four.digits"/>

                :
            </td>

            <td>
                <input type="text" id="last_four" name="last_four" onkeypress="return MyTrapEnter(this,event,'numeric');"
                       autocomplete="off" tabindex="4" maxlength="4">
            </td>

            <td>
                <span class="error_color" id="last_four_err"></span>
            </td>

        </tr>
        <tr>
            <td align='left' valign='left' class="extra"><spring:message code="auth.no"/>

                :
            </td>

            <td>
                <input type="text" id="auth_num" name="auth_num" onkeypress="return MyTrapEnter(this,event,'numeric');"
                       autocomplete="off" tabindex="5" maxlength="10">
            </td>

            <td>
                <span class="error_color" id="auth_num_err"></span>
            </td>

        </tr>

        <tr>
            <td align='left' valign='left' class="extra"><spring:message code="rref.no"/>

                :
            </td>

            <td>
                <input type="text" id="rrn_num" name="rrn_num" onkeypress="return MyTrapEnter(this,event,'numeric');"
                       autocomplete="off" tabindex="6" maxlength="15">
            </td>

            <td>
                <span class="error_color" id="rrn_num_err"></span>
            </td>

        </tr>

        <tr>
            <td align='left' valign='left' class="extra">
                <spring:message
                        code="common.message.transactiontype"/> :

            <td><select id="transType" name="transType" onclick="" tabindex="7">
                <option value="all">--- Select ---</option>
                <c:if test="${!empty transactionTypeList}">
                    <c:forEach var="transType" items="${transactionTypeList}">
                        <option value="${transType}">${transType}</option>
                    </c:forEach>
                </c:if>
            </select>
            </td>
            <td><span id="transType_err" class="error_color"></span></td>
        </tr>


        <tr>
            <td align='left' valign='left' class="extra"><spring:message code="common.message.report.format"/>

                :<font color=red>*</font></td>
            </td>

            <td>
                <select id="report_type" name="report_type" tabindex="8">
                    <option value="-1"><spring:message code="select.select.report.type"/></option>
                    <option value="1"><spring:message code="htnl.type"/></option>
                    <option value="2"><spring:message code="pdf.type"/></option>
                    <option value="3"><spring:message code="csv.type"/></option>
                </select>
            </td>

            <td>
                <span class="error_color" id="report_type_err"></span>
            </td>

        </tr>
        <tr>
            <td>
                &nbsp;
            </td>
        </tr>
        <tr>
            <td align='left' valign='middle'>
            <td colspan="2" align="left">
                <table border="0">
                    <tr>
                        <td>
                            <div class="buttonwrapper" valign="left"><a
                                    class="btn btn_orange btn_search radius50" href="#"
                                    onclick="validateForm();"
                                    tabindex="9"><span><spring:message
                                    code="common.message.search"/></span></a>
                            </div>
                        </td>


                    </tr>
                    <tr height="2">
                        <td colspan="4"></td>
                    </tr>
                </table>
            </td>

            <td>&nbsp;</td>
        </tr>

    </table>


</form>


<br>

<br>


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
