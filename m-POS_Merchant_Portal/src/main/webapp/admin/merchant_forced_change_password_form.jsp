<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title><spring:message code="common.message.title"/></title>
    <meta http-equiv="Content-Language" content="en-us">
    <meta name="GENERATOR" content="Microsoft FrontPage 5.0">
    <meta name="ProgId" content="FrontPage.Editor.Document">
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
    <style type="text/css">
    </style>

    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/stylesheet/style.css">
    <script src="<%=request.getContextPath()%>/resources/js/leftpanel.js" type="text/javascript"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/stylesheet/style.default.css" type="text/css" />

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
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/mpos.js"></script>

    <%--Setting for tooltip--%>

    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/tooltipster-master/css/tooltipster.css" />
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/tooltipster-master/js/jquery.tooltipster.min.js"></script>

    <%--Setting for tooltip--%>

    <!--[if lte IE 8]><script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/resources/js/plugins/excanvas.min.js"></script><![endif]-->
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
        $(document).ready(function() {
         /*   $('.tooltip').tooltipster();*/
            $('#idTooltip').tooltipster({
                offsetX:0,
                offsetY:25,
                trigger:'click'
            });
        });
    </script>

    <script type="text/javascript">


        function clearall() {
            document.getElementById("oldPassword_error").innerHTML = "";
            document.getElementById("password_error").innerHTML = "";
            document.getElementById("confirmPassword_error").innerHTML = "";
        }

        function validateForm() {
            clearall();
            var flag = true;
            var oldPassword = document.getElementById("oldPassword").value.replace(/^\s+|\s+$/g, "");
            var pass = document.getElementById("password").value.replace(/^\s+|\s+$/g, "");
            var confirmpass = document.getElementById("confirmPassword").value.replace(/^\s+|\s+$/g, "");
            document.getElementById('result_id').style.display = 'none';

            if (oldPassword == "" || oldPassword == null) {
                document.getElementById("oldPassword_error").innerHTML = '<spring:message code="pass.info.oldpassword"/>';
                return false;
            }


            /*flag = isCorrect(oldPassword, 'password', 'oldPassword_error', '<spring:message code="pass.info.oldpassword"/>');
            if (!flag)return false;*/



            if (pass == "" || pass == null) {
                document.getElementById("password_error").innerHTML = '<spring:message code="password.helppage.passwordatleast"/>';
                return false;
            }

            flag = isCorrect(pass, 'password', 'password_error', '<spring:message code="password.helppage.passwordatleast"/>');
            if (!flag)return false;


            if (confirmpass != pass) {
                document.getElementById("confirmPassword_error").innerHTML = '<spring:message code="password.script.error.newconfirmpasssame"/>';
                return false;
            }

            document.forms[0].action = "forcedChangePasswordAction.do";
            document.forms[0].pageAction.value = "forced";
            document.forms[0].method = "post";
            document.forms[0].submit();

        }


    </script>

</head>

<body class="withvernav" onLoad="setFocus(document.getElementById('oldPassword'));document.getElementById('oldPassword').value='';">

<div class="bodywrapper">

    <!-- header start-->
    <%@include file="header3.jsp" %>
    <!-- header end -->


    <%-- leftmenu start--%>
    <%--<%@include file="leftpanel.jsp" %>--%>
    <!--leftmenu-->

    <%-- leftmenu end --%>


    <div class="centercontent1" align="center">

        <%--<%@include file="breadcrums.jsp" %>--%>
        <div class="pageheader" align="center">
            <h1 class="pagetitle"><spring:message code="pass.changepass"/>
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

                <div  class="contentwrapper" style="min-height:400px;" >

                    <%--  body satrt here --%>


                    <form:form method="POST" commandName="userModel" name="myform" id="myform" autocomplete="off">
                        <input type="hidden" name="pageAction" value=""/>
                        <%--<table border="0" width="50%" align="center">--%>
                        <table border="0" style="margin-left:16%" width="60%">

                            <tr>
                                <td><p></p></td>
                            </tr>

                            <tr>
                                <td colspan="3" align="left">
                                         <span id="result_id" class="result_color" align="left">
                                    <%
                                        if (session.getAttribute("change_pass_msg") != null) {
                                    %>
                                    <%=session.getAttribute("change_pass_msg")%>
                                    <%}
                                        session.removeAttribute("change_pass_msg");

                                    %>   </span>
                                </td>
                            </tr>


                            <tr>
                                <td class="extra1" width="20%"><spring:message code="pass.oldpass"/> : <sup
                                            style="color:#ff0000; ">*</sup></td>
                                <td width="20%"><form:password path="oldPassword" cssClass="password" id="oldPassword" maxlength='12'  onkeypress="return MyTrapEnter(this,event,'');" tabindex="1" onCopy="return true" onPaste="return true"/>
                                </td>
                                <td width="20%" align="left"><span id="oldPassword_error" class="error_color"></span>
                                </td>
                            </tr>

                            <tr>
                                <td class="extra1"><spring:message code="pass.newpass"/> : <sup
                                            style="color:#ff0000; ">*</sup></td>
                                <td><form:password path="password" cssClass="password" id="password" maxlength='12' onkeypress="return MyTrapEnter(this,event,'');" tabindex="2" onCopy="return false" onPaste="return false"/>
                                </td>
                                <td align="left"><img src="<%=request.getContextPath()%>/resources/images/default/help_img_4.png" class="tooltip" alt="help_img_4.png" title="The first step in protecting your online privacy is creating a safe password - i.e. one that a computer program or persistent individual won't easily be able to guess in a short period of time. Following are the key points which you need to know before creating a secure password:

Password is case sensitive.

Password length should be minimum 8 characters and maximum 12.

We advise you not to use your first name or last name as your password.

Use of atleast one of each alphabets, numerals &amp; special characters{Use only these @ . _ # , : ( ) characters} in your password is mandatory as it increases password strength. (Example: Rd1@g2#)

Tips for keeping your password secure:

1.Never tell your password to anyone.
2.Never write your password down.
3.Never send your password by email.
4.Periodically test your current password and change it to a new one."/>
                                    <span id="password_error" class="error_color"></span>
                                </td>
                            </tr>

                            <tr>
                                <td class="extra1"><spring:message code="pass.confpass"/> : <sup
                                            style="color:#ff0000; ">*</sup></td>
                                <td><form:password path="confirmPassword" cssClass="password" id="confirmPassword" maxlength='12' onkeypress="return MyTrapEnter(this,event,'');" tabindex="3" onCopy="return false" onPaste="return false"/>
                                </td>
                                <td align="left"><span id="confirmPassword_error" class="error_color"></span>
                                </td>
                            </tr>

                            <tr>
                                <td><p></p></td>
                            </tr>

                            <tr align="center">
                                <td width="30%">&nbsp;</td>
                                <td align="left" width="20%">


                                    <div class="buttonwrapper">
                                        <a class="btn btn_bulb" href="#"
                                           onClick="validateForm();" tabindex="4" ><span><spring:message code="pass.changepass"/></span></a>
                                    </div>


                                </td>
                                <td width="50%">&nbsp;</td>
                            </tr>
                        </table>
                    </form:form>




                    <%--  body end here --%>
                    <br />



                </div>

            </div><!--contentwrapper-->



        </div>

        <!-- centercontent -->

    </div>
    <div class="clear"></div>
    <%-- footer start--%>
    <div class="footer1">

        <%@include file="footer.jsp" %>

    </div>

    <%-- footer end --%>
</div><!--bodywrapper-->

</body>

</html>


