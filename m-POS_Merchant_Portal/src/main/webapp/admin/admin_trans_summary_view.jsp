<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="html" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
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
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/plugins/jquery.uniform.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/plugins/jquery.validate.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/js/plugins/jquery.tagsinput.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/plugins/charCount.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/plugins/ui.spinner.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/custom/general.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/custom/forms.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/includes/js/validations.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/includes/js/validations_client.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/includes/js/vendor.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/mpos.js"></script>

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



</head>
<body class="withvernav" >
<table width="100%" border="0">
    <tr>

        <td align="center" style="background-color: #B9C510;color: white;font-weight: bold;font-size: xx-large;"><spring:message code="transaction.summary.report"/></td>

    </tr>

</table>
<br>
<br>

<table width="100%" border="1">
    <tr>
        <td width="20%"align="center" style="background-color: #B9C510;color: white;font-weight: bold;"><spring:message code="common.message.date"/></td>
        <td width="20%"align="center" style="background-color: #B9C510;color: white;font-weight: bold;"><spring:message code="common.message.device.num"/></td>
        <td width="20%"align="center" style="background-color: #B9C510;color: white;font-weight: bold;"><spring:message code="common.message.transtype"/></td>
        <td width="20%"align="center" style="background-color: #B9C510;color: white;font-weight: bold;"><spring:message code="common.message.amount"/></td>
        <td width="20%"align="center" style="background-color: #B9C510;color: white;font-weight: bold;"><spring:message code="common.message.trns.num"/></td>

    </tr>
    <c:if test="${!empty transSumList}">
        <c:forEach var="summary" items="${transSumList}">
            <tr style="border-bottom: #000000">
                <td align="center" height="10%">${summary.transactionsResponseDate}</td>
                <td align="center" height="10%">${summary.transactionsMobileNumber}</td>
                <td  align="center" height="10%">${summary.transactionsTransTypeCode}</td>
                <td align="right"  height="10%">${summary.transactionsValue}</td>
                <td align="center" height="10%">${summary.transactionscount}</td>

            </tr>

        </c:forEach>
        </c:if>

        <c:if test="${empty transSumList}">
    <tr>
        <td colspan="5" align="center" style="background-color: #B9C510;color: white;font-weight: bold;">No Records Found</td>

    </tr>
            </c:if>


    <tr>
        <td colspan="5" align="center" style="background-color: #B9C510;color: white;font-weight: bold;"><spring:message code="common.message.end.of.report"/></td>
    </tr>
</table>


</body>

</html>
