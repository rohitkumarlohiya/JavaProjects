<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=8"><%--setting compatibility mode--%>
    <title><spring:message code="common.message.title"/></title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1">

    <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/bootstrap.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/login.css">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/stylesheet/style.css">
    <script src="<%=request.getContextPath() %>/resources/js/leftpanel.js" type="text/javascript"></script>
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

    <SCRIPT type="text/javascript">

        function doSignUp() {
            window.location.href = "signUp.do";
        }

        function validateLogin() {

            var valid = true;
            var focas = 0;

            if (document.getElementById("username").value == "") {

                document.getElementById("log_err").innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp; <spring:message code='common.message.enuser'/>";
                document.getElementById("username").style.backgroundColor = "#FFFFCC";
                document.getElementById("username").focus();
                focas = 1;
                valid = false;
            }
            else {
                document.getElementById("username").style.backgroundColor = "white";

                if (document.getElementById("password").value == "") {

                    document.getElementById("log_err").innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp; <spring:message code='common.message.enpass'/>";
                    document.getElementById("password").style.background = "#FFFFCC";
                    if (focas == 0)
                        document.getElementById("password").focus();
                    valid = false;
                }
                else {

                    document.getElementById("password").style.backgroundColor = "white";
                }

            }

            if (valid == true) {

                document.forms[0].action = "login.do";
                document.forms[0].method = "post";
                document.forms[0].submit();
            }

            return valid;
        }

        function trapEnter(evt) {
            var keycode;
            //  var charCode = (evt.which) ? evt.which : event.keyCode
            if (evt)
                ;
            else if (window.event)
                evt = window.event;
            else if (event)
                evt = event;
            else
                return true;
            if (evt.charCode)
                keycode = evt.charCode;
            else if (evt.keyCode)
                keycode = evt.keyCode;
            else if (evt.which)
                keycode = evt.which;
            else
                keycode = 0;
            if (keycode == 13) {
                return validateLogin();
            } else
                return true;
        }

    </SCRIPT>

</head>
<body class='login_body' onload="setFocus(document.getElementById('username'));">

<%--<div class="fleft top-lg"><img src="images/logo.png" width="131" height="80" alt="Estel Logo" /></div>
<div class="fleft top-lg-text">Admin Portal</div>
   <div class="clear"></div>
<div class="top-lg-btm"></div>--%>
<%@include file="header_login.jsp" %>

<form:form commandName="loginModel" autocomplete="off" onCopy="return true" onPaste="return true">
    <div class="wrap">

        <div class="block-1" style="height: 206px">
                <%--class="logo img"--%>
            <center>
                <div style="margin-top: -30px;">
                    <img src="<%=request.getContextPath() %>/resources/images/default/m-pos_index.png" width="145" height="175"
                         alt="m-pos_index.png"/>
                </div>
            </center>
            <div class="clear"></div>
        </div>

        <div class="block-2">

            <span id="log_err" class="error_color">
                <c:if test="${sessionScope.loginerrormessage != null}">
                    &nbsp;&nbsp;&nbsp;&nbsp; <%=session.getAttribute("loginerrormessage")%>
                    <%session.removeAttribute("loginerrormessage");%>
                </c:if>
            </span>

            <div class="login">

                <div class="email">
                    <label for="username" style="width: 150px"><spring:message
                            code="merchant.message.loginname"/> :<sup
                            style="color: #ff0000;">*</sup></label>

                    <div class="email-input">
                        <div class="control-group">
                            <div class="input-prepend">
                                <span class="add-on"><i class="icon-user"></i></span>

                                <form:input path="username" id="username"
                                            tabindex="1" class="inputform200"
                                            maxlength='<%=webAppContext.getMessage("email.length",null,"default-email.length",null)%>' onkeypress="return trapEnter(event);" style="height:auto;"/>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="pw">
                    <label for="password"><spring:message
                            code="merchant.message.password"/> :<sup
                            style="color: #ff0000;">*</sup></label>

                    <div class="pw-input">
                        <div class="control-group">
                            <div class="input-prepend">
                                <span class="add-on"><i class="icon-key"></i></span>
                                <form:password path="password" id="password"
                                               tabindex="2" class="inputform200"
                                               maxlength="20" onkeypress="return trapEnter(event);"
                                               style="height:auto;"/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="remember">
                    <label class="checkbox"> <%--<input type="checkbox" value="1" name="remember"> Remember me--%>
                    </label>
                </div>

                <div class="submit">

                    <input type="reset" name="subbtn" id="clrbtn" class="btn-red5"
                           style="width: 86px;margin-right: 23%;"
                           value='<spring:message code="merchant.message.clear"/>'
                           Onclick=""  tabindex="4">


                    <input type="button"
                           name="subbtn" id="subbtn" class="btn-red5"
                           style="width: 86px;"
                           value='<spring:message code="merchant.message.login"/>'
                           onclick="validateLogin();"  tabindex="3">

                </div>

                <div>
                    <label for="log_err"><a href="forgotPassword.do" tabindex="5"><spring:message
                            code="merchant.message.forgotpassword"/></a>
                    </label>
                </div>

            </div>


            <c:if test="${sessionScope.userEmailId == null}">
                <br>
                <table border="0">
                    <tr>
                        <td nowrap align="right" width="30%"><spring:message
                                code="new.to.mpos"/>&nbsp;&nbsp;&nbsp;</td>
                        <td align="left" width="30%"><input type="button"
                                                            name="subbtn" id="signupbtn" class="btn-red-orange"
                                                            style="width: 86px;"
                                                            value='<spring:message code="merchant.message.signup"/>'
                                                            onclick="doSignUp();" tabindex="6"></td>
                    </tr>
                </table>
            </c:if>
            <c:if test="${sessionScope.userEmailId != null}">
                <%session.removeAttribute("userEmailId");%>
            </c:if>


        </div>
        <div class="clear"></div>

        <div class="shadow-bg1"></div>

    </div>
</form:form>
<div class="clear"></div>

<div class="bodywrapper">
    <div class="footer1">
        <div class="block-1">
            <div>
                <img src="<%=request.getContextPath() %>/resources/images/logo.png" alt="Estel Logo" width="80" height="50"/>
            </div>
            <p>
                <spring:message code="footer.copyright"/>
            </p>
        </div>

        <div class="block-2">
            <p>
                <br/>
                <spring:message code="footer.licence"/>
                <spring:message code="client.name"/>
                <br/>
                <spring:message code="footer.release"/>
                <spring:message code="release.number"/>
            </p>

        </div>

        <div class="block-3">
            <!-- <div><img src="admin/images/logo.png" alt="Estel Logo" width="80" height="50"/></div> -->
            <p></p>
        </div>

        <div class="clear"></div>
    </div>
</div>
</body>
</html>