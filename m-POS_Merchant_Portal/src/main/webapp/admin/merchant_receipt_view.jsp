<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
    <title><spring:message code="common.message.title"/></title>
    <meta http-equiv="Content-Language" content="en-us">
    <meta name="GENERATOR" content="Microsoft FrontPage 5.0">
    <meta name="ProgId" content="FrontPage.Editor.Document">
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">

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

    <script>

        function doEmail() {
            document.getElementById("idEmailBox").style.display = 'table-row';
            setFocus(document.getElementById("emailtosend"));
        }

        function doPrint() {
            document.getElementById("idEmailBox").style.display = 'none';
            window.print();
        }

        function validateForm() {

            var emailtosend = document.getElementById("emailtosend").value.replace(/^\s+|\s+$/g, "");
            var flag;
            if (emailtosend == null || emailtosend == "") {
                document.getElementById("email_err").innerHTML = '<spring:message code="common.message.error.email"/>';
                return false;
            }
            flag = isCorrect(emailtosend, 'email', 'email_err', '<spring:message code="common.message.error.email"/>');
            if (!flag)return false;

            document.forms[0].action = "sendEmailReceipt.do";
            document.forms[0].method = "post";
            document.forms[0].submit();

            return false;
        }

    </script>
</head>
<body>


<table border="0" <%--width="50%" --%>align="center">
    <%if (session.getAttribute("email_sent_msg") == null) {%>
    <tr>
        <td colspan="2" align="left">

            <img src="data:image/png;charset=utf-8;base64,${imageData}" alt="IMG DESC">

        </td>
    </tr>
    <tr>
        <td>
            <form autocomplete="off" onCopy="return false" onPaste="return false">
                <table border="0" align="center">
                    <tr>
                        <td><a href="#" onclick="doEmail()">Email</a></td>
                        <td><a href="#" onclick="doPrint()">Print</a></td>
                    </tr>
                    <tr id="idEmailBox" style="display:none;">
                        <td><input type="text" name="emailtosend" id="emailtosend" placeholder="Enter email"
                                   onkeypress="return MyTrapEnter(this,event,'email')"></td>
                        <td><input type="button" onclick="validateForm()" value="Done" ></td>
                    </tr>
                    <tr>
                        <td colspan="2"><span id="email_err" style="color: #ff0000;"></span></td>
                    </tr>
                </table>
            </form>
        </td>
    </tr>

    <%}%>
    <tr>
        <td><p></p></td>
    </tr>

    <tr>
        <td colspan="2">
                                         <span class="result_color" align="left">
                                    <%
                                        if (session.getAttribute("email_sent_msg") != null) {
                                    %>
                                    <%=session.getAttribute("email_sent_msg")%>
                                    <%
                                        }
                                        session.removeAttribute("email_sent_msg");

                                    %>   </span>
        </td>
    </tr>

</table>

</body>


</html>
