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
    <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/stylesheet/style.default.css"
          type="text/css"/>

    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/plugins/jquery-1.7.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath() %>/resources/js/plugins/jquery-ui-1.8.16.custom.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/plugins/jquery.cookie.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath() %>/resources/js/plugins/jquery.uniform.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath() %>/resources/js/plugins/jquery.validate.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath() %>/resources/js/plugins/jquery.tagsinput.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/plugins/charCount.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/plugins/ui.spinner.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/custom/general.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/custom/forms.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/mpos.js"></script>


    <%--Setting for tooltip--%>

    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/resources/tooltipster-master/css/tooltipster.css"/>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.8.0.min.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/tooltipster-master/js/jquery.tooltipster.min.js"></script>

    <%--Setting for tooltip--%>
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
        $(document).ready(function () {
            /* $('.tooltip').tooltipster();*/
            $('#idTooltip').tooltipster({
                offsetX:0,
                offsetY:25,
                trigger:'click'
            });
        });
    </script>

    <script type="text/javascript">


        function clearall() {
            //alert("clearall()");
            document.getElementById("oldPin_error").innerHTML = "";
            document.getElementById("pin_error").innerHTML = "";
            document.getElementById("confirmPin_error").innerHTML = "";
        }

        function validateForm() {
            document.getElementById('result_id').style.display = 'none';
            clearall();
            var flag = true;
            var returnpin = true;
            var oldpin = document.getElementById("oldPin").value.replace(/^\s+|\s+$/g, "");
            var pin = document.getElementById("pin").value.replace(/^\s+|\s+$/g, "");
            var confirmpin = document.getElementById("confirmPin").value.replace(/^\s+|\s+$/g, "");

            if (oldpin == "" || oldpin == null) {
                document.getElementById("oldPin_error").innerHTML = '&nbsp;<spring:message code="common.message.error.oldpin"/>';
                return false;
            }

            flag = isCorrect(oldpin, 'numeric', 'oldPin_error', '<spring:message code='common.message.error.numeric'/>');
            if (!flag)return false;

            flag = isMinMax(oldpin, parseInt('<spring:message code="pin.length"/>'), parseInt('<spring:message code="pin.length"/>'), 'pin_error', '<spring:message code="merchant.message.enterpin"/>');
            if (!flag)return false;


            if (pin == "" || pin == null) /*|| isNaN(pin)*/
            {
                document.getElementById("pin_error").innerHTML = '&nbsp;<spring:message code="common.message.error.newpin"/>';
                return false;
            }


            flag = isCorrect(pin, 'numeric', 'pin_error', '<spring:message code='common.message.error.numeric'/>');
            if (!flag)return false;


            flag = isMinMax(pin, parseInt('<spring:message code="pin.length"/>'), parseInt('<spring:message code="pin.length"/>'), 'pin_error', '<spring:message code="merchant.message.enterpin"/>');
            if (!flag)return false;


            if (confirmpin != pin) {
                document.getElementById("confirmPin_error").innerHTML = '&nbsp;<spring:message code="common.message.error.confirmPin"/>';
                return false;
            }

            beforeSubmit('myForm')
            document.forms[0].action = "changeMpinAction.do";
            document.forms[0].method = "post";
            //alert("submitting");
            document.forms[0].submit();

        }
    </script>
</head>

<body class="withvernav" onload="setFocus(document.getElementById('oldPin'))">

<div class="bodywrapper">

    <!-- header start-->
    <%@include file="header1.jsp" %>
    <!-- header end -->


    <%-- leftmenu start--%>
    <%@include file="leftpanel.jsp" %>
    <!--leftmenu-->

    <%-- leftmenu end --%>


    <div class="centercontent">

        <%@include file="breadcrums.jsp" %>
        <div class="pageheader">
            <h1 class="pagetitle"><spring:message code="merchant.message.change.mpin"/>
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


                    <form:form method="POST" commandName="userModel" name="myForm" id="myForm" autocomplete="off"
                               onCopy="return false" onPaste="return false">

                        <table style="margin-left: 5%;margin-top: 5%" width="70%">

                            <tr>
                                <td colspan="3">
                                         <span id="result_id" class="result_color" align="left">
                                    <%
                                        if (session.getAttribute("change_pin_msg") != null) {
                                    %>
                                    <%=session.getAttribute("change_pin_msg")%>
                                    <%
                                        }
                                        session.removeAttribute("change_pin_msg");

                                    %>   </span>
                                </td>
                            </tr>


                            <tr>
                                <td width="15%" class="extra1"><spring:message code="merchant.message.old.mpin"/> : <sup
                                        style="color:#ff0000; ">*</sup>
                                </td>
                                <td width="20%"><form:password path="oldPin" cssClass="password" id="oldPin"
                                                               onkeypress="return MyTrapEnter(this,event,'numeric');"
                                                               tabindex="1"
                                                               maxlength='<%=webAppContext.getMessage("pin.length",null,"default-pin.length",null)%>'/>
                                </td>
                                <td width="20%"><span id="oldPin_error" class="error_color"></span>
                                </td>
                            </tr>

                            <tr>
                                <td class="extra1"><spring:message code="merchant.message.new.mpin"/> : <sup
                                        style="color:#ff0000; ">*</sup>
                                </td>
                                <td><form:password path="pin" cssClass="password" id="pin"
                                                   onkeypress="return MyTrapEnter(this,event,'numeric');" tabindex="2"
                                                   maxlength='<%=webAppContext.getMessage("pin.length",null,"default-pin.length",null)%>'/>
                                </td>
                                <td><img src="<%=request.getContextPath()%>/resources/images/default/help_img_4.png"
                                         class="tooltip" alt="help_img_4.png"
                                         title='<%=webAppContext.getMessage("secret.key.help",null,"default-secret.key.help",null)%>'/>
                                    <span id="pin_error" class="error_color"></span>
                                </td>
                            </tr>

                            <tr>
                                <td class="extra1"><spring:message code="merchant.message.confirm.mpin"/> : <sup
                                        style="color:#ff0000; ">*</sup>
                                </td>
                                <td><form:password path="confirmPin" cssClass="password" id="confirmPin"
                                                   onkeypress="return MyTrapEnter(this,event,'numeric');" tabindex="3"
                                                   maxlength='<%=webAppContext.getMessage("pin.length",null,"default-pin.length",null)%>'/>
                                </td>
                                <td><span id="confirmPin_error" class="error_color"></span>
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
                                           onClick="validateForm();"
                                           tabindex="4"><span><spring:message
                                                code="merchant.message.change.mpin"/></span></a>
                                    </div>


                                </td>
                                <td width="50%">&nbsp;</td>
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


