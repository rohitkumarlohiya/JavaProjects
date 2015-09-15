<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/stylesheet/style.css">
    <script src="<%=request.getContextPath() %>/resources/js/leftpanel.js" type="text/javascript"></script>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/stylesheet/style.default.css" type="text/css"/>

    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/plugins/jquery-1.7.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath() %>/resources/js/plugins/jquery-ui-1.8.16.custom.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/plugins/jquery.cookie.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/plugins/jquery.uniform.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath() %>/resources/js/plugins/jquery.validate.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath() %>/resources/js/plugins/jquery.tagsinput.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/plugins/charCount.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/plugins/ui.spinner.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/custom/general.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/custom/forms.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/mpos.js"></script>


    <%--<script type="text/javascript" src="<%=request.getContextPath() %>/resources/includes/js/validations.js"></script>
 <script type="text/javascript"
         src="<%=request.getContextPath() %>/resources/includes/js/validations_client.js"></script>
 <script type="text/javascript" src="<%=request.getContextPath() %>/resources/includes/js/vendor.js"></script>
 <script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/common.js"></script>--%>



    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/mpos.js"></script>

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

        var clickedOnce = 0;
        function validateForm() {

            document.getElementById('result_id').style.display = 'none';

            var flag = true;

            var username = document.getElementById("username").value;

            if (username == null || username == "") {
                document.getElementById("username_error").innerHTML = '<spring:message code="common.message.error.email"/>';
                return false;
            }
            flag = isCorrect(username, 'email', 'username_error', '<spring:message code="common.message.error.email"/>');
            if (!flag)return false;

                beforeSubmit('myForm');
                document.getElementById('idDoneBtn').disabled = true;
                document.forms[0].action = "forgotPasswordAction.do";
                document.forms[0].method = "post";
                document.forms[0].submit();

        }

        function doCancel() {
            window.location.href = "Mpos.do";
        }

    </script>
</head>

<body class="withvernav" onLoad="setFocus(document.getElementById('username'));">

<div class="bodywrapper">

    <!-- header start-->
    <%--<%@include file="header.jsp" %>--%>
    <%@include file="header2.jsp" %>
    <!-- header end -->


    <%-- leftmenu start--%>
    <%--<%@include file="leftpanel.jsp" %>--%>
    <!--leftmenu-->

    <%-- leftmenu end --%>


    <div class="centercontent1" align="center">

        <%--<%@include file="breadcrums.jsp" %>--%>
        <div class="pageheader">
            <h1 class="pagetitle"><spring:message code="merchant.message.passwordrecovery"/>
            </h1>
            <%--<span class="pagedesc"></span>--%>

            <ul class="hornav">
                <%-- <li class="current"><a href="#basicform">Basic</a></li>--%>
                <%-- <li><a href="#validation">Validation</a></li>--%>
            </ul>
        </div>
        <!--pageheader-->

        <div id="contentwrapper" class="contentwrapper">

            <div id="basicform" class="subcontent">

                <%-- <div class="contenttitle2">
                <h3>Form Style 1</h3>
                </div>
                <!--contenttitle-->--%>


                <div id="contentwrapper" class="contentwrapper" style="min-height:400px;">

                    <%--  body satrt here --%>


                    <form:form method="POST" commandName="loginModel" autocomplete="off" onCopy="return false" onPaste="return false" id="myForm" name="myForm">

                        <%--<table border="1" width="50%" align="center">--%>
                        <table border="0" style="margin-left:12%">
                            <tr>
                                <td><p></p></td>
                            </tr>

                            <tr>
                                <td colspan="3" align="left">
                                    <span id="result_id" class="result_color" align="left">
                                    <%
                                        if (session.getAttribute("forgot_pass_msg") != null) {
                                    %>
                                         <%=session.getAttribute("forgot_pass_msg")%>
                                    <%
                                        }
                                        session.removeAttribute("forgot_pass_msg");
                                    %>
                                    </span>
                                </td>
                            </tr>


                            <tr>
                                <td class="extra1"
                                    width="25%"><spring:message code="merchant.message.enteryouremailaddress"/> : <sup
                                        style="color:#ff0000; ">*</sup>
                                </td>
                                <td width="30%"><form:input path="username" id="username"
                                                            onkeypress="return MyTrapEnter(this,event,'email');" tabindex="1"/>
                                </td>
                                <td width="45%"><span id="username_error" class="error_color"></span>
                                </td>
                            </tr>

                            <tr>
                                <td><p></p></td>
                            </tr>

                            <tr align="center">
                                <td></td>
                                <td>
                                    <div class="buttonwrapper">
                                        <a id="idDoneBtn" class="btn btn_rss" href="#" onclick="validateForm();">
                                            <span><spring:message code="merchant.message.done"/></span>
                                        </a>


                                        &nbsp;&nbsp;&nbsp;

                                        <a class="btn btn_rss" href="#" onclick="doCancel();">
                                            <span><spring:message code="merchant.message.cancel"/></span>
                                        </a>
                                    </div>
                                </td>
                                <td>

                                </td>
                            </tr>


                        </table>
                    </form:form>


                    <%--  body end here --%>
                    <br/>


                </div>

            </div>
            <!--contentwrapper-->


        </div>

        <!-- centercontent -->

    </div>
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


