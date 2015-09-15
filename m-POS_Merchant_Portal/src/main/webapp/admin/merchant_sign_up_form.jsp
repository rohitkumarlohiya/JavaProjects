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


<%--<script type="text/javascript" src="<%=request.getContextPath() %>/resources/includes/js/validations.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath() %>/resources/includes/js/validations_client.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/includes/js/vendor.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/common.js"></script>--%>


<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/mpos.js"></script>


<%--Setting for tooltip--%>

<link rel="stylesheet" type="text/css"
      href="<%=request.getContextPath() %>/resources/tooltipster-master/css/tooltipster.css"/>
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.0.min.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath() %>/resources/tooltipster-master/js/jquery.tooltipster.min.js"></script>

<%--Setting for tooltip--%>


<!--[if lte IE 8]>
<script language="javascript" type="text/javascript"
        src="<%=request.getContextPath() %>/resources/js/plugins/excanvas.min.js"></script><![endif]-->
<!--[if IE 9]>
<link rel="stylesheet" media="screen" href="<%=request.getContextPath() %>/resources/css/style.ie9.css"/>
<![endif]-->
<!--[if IE 8]>
<link rel="stylesheet" media="screen" href="<%=request.getContextPath() %>/resources/css/style.ie8.css"/>
<![endif]-->
<!--[if lt IE 9]>
<script src="<%=request.getContextPath() %>/resources/css/css3-mediaqueries.js"></script>
<![endif]-->

<script>

    $(document).ready(

            function () {

                $('#email').tooltipster({
                    offsetX:0,
                    offsetY:25,
                    trigger:'click'
                });
            },
            function () {

                $('#mobileNumber').tooltipster({
                    offsetX:0,
                    offsetY:25,
                    trigger:'click'
                });
            }
    );

</script>

<script type="text/javascript">

    function clearall() {
        document.getElementById("firstname_error").innerHTML = "";
        document.getElementById("surname_error").innerHTML = "";
        document.getElementById("country_error").innerHTML = "";
        document.getElementById("mobile_error").innerHTML = "";
        document.getElementById("email_error").innerHTML = "";
        document.getElementById("password_error").innerHTML = "";
        document.getElementById("confirmPassword_error").innerHTML = "";
        document.getElementById("pin_error").innerHTML = "";
        document.getElementById("confirmPin_error").innerHTML = "";
    }

    function validateForm() {

        clearall();

        var flag = true;

        var mobileNumber = document.getElementById("mobileNumber").value.replace(/^\s+|\s+$/g, "");
        var email = document.getElementById("email").value.replace(/^\s+|\s+$/g, "");
        var firstName = document.getElementById("firstName").value.replace(/^\s+|\s+$/g, "");
        var surname = document.getElementById("surname").value.replace(/^\s+|\s+$/g, "");
        var residentialCountry = document.getElementById("residentialCountry").value;
        var password = document.getElementById("password").value.replace(/^\s+|\s+$/g, "");
        var confirmPassword = document.getElementById("confirmPassword").value;
        var validPassword = false;
        var pin = document.getElementById("pin").value.replace(/^\s+|\s+$/g, "");
        var confirmPin = document.getElementById("confirmPin").value;
        var validPin = false;


        if (email == null || email == "") {
            document.getElementById("email_error").innerHTML = '<spring:message code="common.message.error.email"/>';
            return false;
        }

        flag = isCorrect(email, 'email', 'email_error', '<spring:message code="common.message.error.email"/>');
        if (!flag)return false;

        if (firstName == null || firstName == "") {
            document.getElementById("firstname_error").innerHTML = '<spring:message code="common.message.error.firstname"/>';
            return false;
        }

        flag = isCorrect(firstName, 'alpha', 'firstname_error', '<spring:message code='common.message.error.alphabets'/>');
        if (!flag)return false;
        flag = isMinMax(firstName, parseInt('<spring:message code="name.length.min"/>'), parseInt('<spring:message code="name.length.max"/>'), 'firstname_error', '<spring:message code="common.name.minmax.error"/>');
        if (!flag)return false;


        if (surname == null || surname == "") {
            document.getElementById("surname_error").innerHTML = '<spring:message code="common.message.error.surname"/>';
            return false;
        }

        flag = isCorrect(surname, 'alpha', 'surname_error', '<spring:message code='common.message.error.alphabets'/>');
        if (!flag)return false;
        flag = isMinMax(surname, parseInt('<spring:message code="name.length.min"/>'), parseInt('<spring:message code="name.length.max"/>'), 'surname_error', '<spring:message code="common.name.minmax.error"/>');
        if (!flag)return false;


        if (residentialCountry < 1) {
            document.getElementById("country_error").innerHTML = '<spring:message code="common.message.error.country"/>';
            return false;
        }

        if (mobileNumber == "" || mobileNumber == null) {
            document.getElementById("mobile_error").innerHTML = '<spring:message code="common.message.error.validnum"/>';
            return false;
        }

        flag = isCorrect(mobileNumber, 'numeric', 'mobile_error', '<spring:message code='common.message.error.numeric'/>');
        if (!flag)return false;

        flag = isMinMax(mobileNumber, parseInt('<spring:message code="mobile.number.length.min"/>'), parseInt('<spring:message code="mobile.number.length"/>'), 'mobile_error', '<spring:message code="common.message.error.validnum"/>');
        if (!flag)return false;


        if (password == null || password == "") {
            document.getElementById("password_error").innerHTML = '<spring:message code="password.helppage.passwordatleast"/>';
            return false;
        }

        flag = isCorrect(password, 'password', 'password_error', '<spring:message code="password.helppage.passwordatleast"/>');
        if (!flag)return false;


        if (password != confirmPassword) {
            document.getElementById("confirmPassword_error").innerHTML = '<spring:message code="password.script.error.newconfirmpasssame"/>';
            return false;
        }

        if (pin == null || pin == "") {
            document.getElementById("pin_error").innerHTML = '<spring:message code="merchant.message.enterpin"/>';
            return false;
        }

        flag = isCorrect(pin, 'numeric', 'pin_error', '<spring:message code='common.message.error.numeric'/>');
        if (!flag)return false;


        flag = isMinMax(pin, parseInt('<spring:message code="pin.length"/>'), parseInt('<spring:message code="pin.length"/>'), 'pin_error', '<spring:message code="merchant.message.enterpin"/>');
        if (!flag)return false;

        if (pin != confirmPin) {
            document.getElementById("confirmPin_error").innerHTML = '<spring:message code="common.message.error.confirmPin"/>';
            return false;
        }
        beforeSubmit("myform");
        document.forms[0].action = "signUpAction.do";
        document.forms[0].method = "post";
        //alert("submitting");
        document.forms[0].submit();

    }

</script>

</head>

<body class="withvernav" onload="setFocus(document.getElementById('email'))">

<div class="bodywrapper">

<!-- header start-->
<%@include file="header2.jsp" %>
<!-- header end -->

<%-- leftmenu start--%>
<%--<%@include file="leftpanel.jsp" %>--%>
<%-- leftmenu end --%>

<div class="centercontent1">

<%--<%@include file="breadcrums.jsp" %>--%>
<div class="pageheader" align="center">
    <h1 class="pagetitle"><spring:message code="merchant.message.register"/>
    </h1>
    <%--<span class="pagedesc"></span>
--%>
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

        <form:form method="POST" commandName="userModel" id="myform" name="myform" autocomplete="off"
                   onCopy="return false" onPaste="return false">
            <table border="0" style="margin-left:30%">

                <tr>
                    <td colspan="3"><p></p></td>
                </tr>

                <tr>
                    <td width="25%" class="extra1"><spring:message code="merchant.message.emailaddress"/> : <sup
                            style="color:#ff0000; ">*</sup>
                    </td>
                    <td width="20%"><form:input path="email"
                                                title='<%=webAppContext.getMessage("email.help",null,"default-email.help",null)%>'
                                                cssClass="field" id="email"
                                                maxlength='<%=webAppContext.getMessage("email.length",null,"default-email.length",null)%>'
                                                tabindex="1"
                                                onkeypress="return MyTrapEnter(this,event,'email');"/>
                    </td>
                    <td width="55%"><span class="error_color" id="email_error"></span></td>
                </tr>

                <tr>
                    <td class="extra1"><spring:message code="merchant.message.firstname"/> : <sup
                            style="color:#ff0000; ">*</sup>
                    </td>
                    <td><form:input path="firstName" cssClass="field" id="firstName"
                                    maxlength='<%=webAppContext.getMessage("name.length.max",null,"default-name.length.max",null)%>'
                                    onkeypress="return MyTrapEnter(this,event,'alpha');" tabindex="2"/>
                    </td>
                    <td><span class="error_color" id="firstname_error"></span></td>
                </tr>

                <tr>
                    <td class="extra1"><spring:message code="merchant.message.lastname"/> : <sup
                            style="color:#ff0000; ">*</sup>
                    </td>
                    <td><form:input path="surName" cssClass="field" id="surname"
                                    maxlength='<%=webAppContext.getMessage("name.length.max",null,"default-name.length.max",null)%>'
                                    tabindex="3"
                                    onkeypress="return MyTrapEnter(this,event,'alpha');"/>
                    </td>
                    <td><span class="error_color" id="surname_error"></span></td>
                </tr>

                <tr>
                    <td class="extra1"><spring:message code="merchant.message.countryofresidence"/> : <sup
                            style="color:#ff0000; ">*</sup>
                    </td>
                    <td>
                        <form:select path="residentialCountry" id="residentialCountry" size="1"
                                     cssClass="field" tabindex="4" onkeypress="return MyTrapEnter(this,event,'');">
                            <form:option value="-1" label="--- Select ---"/>
                            <c:forEach items="${userModel.countryList}" var="country">
                                <form:option
                                        value="${country.countryId}">${country.countryDescription}</form:option>
                            </c:forEach>
                        </form:select>
                    </td>
                    <td><span class="error_color" id="country_error"></span></td>
                </tr>

                <tr>
                    <td class="extra1"><spring:message code="merchant.message.mobilephonenumber"/> : <sup
                            style="color:#ff0000; ">*</sup>
                    </td>
                    <td><form:input path="mobileNumber" cssClass="field" id="mobileNumber"
                                    maxlength='<%=webAppContext.getMessage("mobile.number.length",null,"default-mobile.number.length",null)%>'
                                    title='<%=webAppContext.getMessage("mobile.number.help",null,"default-mobile.number.help",null)%>'
                                    onkeypress="return MyTrapEnter(this,event,'numeric');" tabindex="5"/>
                    </td>
                    <td><span class="error_color" id="mobile_error"></span>
                    </td>
                </tr>

                <tr>
                    <td class="extra1"><spring:message code="merchant.message.password"/> : <sup
                            style="color:#ff0000; ">*</sup>
                    </td>
                    <td><form:password path="password" cssClass="password" id="password"
                                       maxlength='12' tabindex="6"
                                       onkeypress="return MyTrapEnter(this,event,'');"/>
                    </td>
                    <td>
                        <img id="idTooltip"
                             src="<%=request.getContextPath() %>/resources/images/default/help_img_4.png"
                             class="tooltip" alt="help_img_4.png"
                             title="The first step in protecting your online privacy is creating a safe password - i.e. one that a computer program or persistent individual won't easily be able to guess in a short period of time. Following are the key points which you need to know before creating a secure password:

Password is case sensitive.

Password length should be minimum 8 characters and maximum 12.

We advise you not to use your first name or last name as your password.

Use of atleast one of each alphabets, numerals &amp; special characters{Use only these @ . _ # , : ( ) characters} in your password is mandatory as it increases password strength. (Example: Rd1@g2#)

Tips for keeping your password secure:

1.Never tell your password to anyone.
2.Never write your password down.
3.Never send your password by email.
4.Periodically test your current password and change it to a new one."/>
                        <span class="error_color" id="password_error"></span>
                    </td>
                </tr>

                <tr>
                    <td class="extra1"><spring:message code="merchant.message.confirmpassword"/> : <sup
                            style="color:#ff0000; ">*</sup>
                    </td>
                    <td><form:password path="confirmPassword" cssClass="password" id="confirmPassword"
                                       maxlength='12' tabindex="7"
                                       onkeypress="return MyTrapEnter(this,event,'');"/>
                    </td>
                    <td>
                        <span class="error_color" id="confirmPassword_error"></span>
                    </td>
                </tr>


                <tr>
                    <td class="extra1"><spring:message code="merchant.message.pin"/> : <sup
                            style="color:#ff0000; ">*</sup>
                    </td>
                    <td><form:password path="pin" cssClass="password" id="pin"
                                       maxlength='<%=webAppContext.getMessage("pin.length",null,"default-pin.length",null)%>'
                                       tabindex="8"
                                       onkeypress="return MyTrapEnter(this,event,'numeric');"/>
                    </td>
                    <td><img src="<%=request.getContextPath() %>/resources/images/default/help_img_4.png"
                             class="tooltip" alt="help_img_4.png"
                             title='<%=webAppContext.getMessage("secret.key.help",null,"default-secret.key.help",null)%>'/>
                        <span class="error_color" id="pin_error"></span>
                    </td>
                </tr>

                <tr>
                    <td class="extra1"><spring:message code="merchant.message.confirmpin"/> : <sup
                            style="color:#ff0000; ">*</sup>
                    </td>
                    <td><form:password path="confirmPin" cssClass="password" id="confirmPin"
                                       maxlength='<%=webAppContext.getMessage("pin.length",null,"default-pin.length",null)%>'
                                       tabindex="9"
                                       onkeypress="return MyTrapEnter(this,event,'numeric');"/>
                    </td>
                    <td>
                        <span class="error_color" id="confirmPin_error"></span>
                    </td>
                </tr>


                <tr>
                    <td><p></p></td>
                </tr>

                <tr align="center">
                    <td width="25%">&nbsp;</td>
                    <td align="left">
                        <div class="buttonwrapper"><a class="btn btn_rss" href="#" tabindex="10"
                                                      onkeypress="return MyTrapEnter(this,event,'');"
                                                      onclick="validateForm();">
                            <span><spring:message code="merchant.message.done"/></span></a></div>
                    </td>
                </tr>

                <tr>
                    <td><p></p></td>
                </tr>

                <tr>
                    <td colspan="3">
                        <a href="Mpos.do" style="color: blue;" tabindex="11"><spring:message
                                code="merchant.message.alreadyregistered"/>
                        </a>
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

