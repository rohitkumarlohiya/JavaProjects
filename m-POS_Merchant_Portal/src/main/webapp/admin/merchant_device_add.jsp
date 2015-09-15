<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <title><spring:message code="common.message.title"/>
    </title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.default.css" type="text/css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/stylesheet/style.css" type="text/css"/>
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
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/includes/js/vendor.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/mpos.js"></script>

    <%--Setting for tooltip--%>

    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/tooltipster-master/css/tooltipster.css"/>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/tooltipster-master/js/jquery.tooltipster.min.js"></script>

    <%--Setting for tooltip--%>

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

        $(document).ready(
                function () {

                    $('#deviceNumber').tooltipster({
                        offsetX:0,
                        offsetY:25,
                        trigger:'click'
                    });
                },

                function () {

                    $('#IMEINumber').tooltipster({
                        offsetX:0,
                        offsetY:25,
                        trigger:'click'
                    });
                }

        );

    </script>

    <SCRIPT type="text/javascript">
        window.history.forward();
        function noBack() {
            window.history.forward();
        }
    </SCRIPT>
    <script type="text/javascript">


        function doBack() {
            window.location.href = "deviceList.do";
        }

        function clearall() {
            document.getElementById("deviceNumber_err").innerHTML = '';
            document.getElementById("IMEINumber_err").innerHTML = '';
            document.getElementById("cardReaderSerialNo_err").innerHTML = '';
        }

        function validateForm() {

            clearall();
            var flag = true;
            var tempFlag = 0;
            var devicenum = document.getElementById("deviceNumber").value;
            var IMEINumber = document.getElementById("IMEINumber").value;
            var cardSerialNumber = document.getElementById("cardReaderSerialNo").value;

            if (devicenum == "" || devicenum == null) {
                document.getElementById("deviceNumber_err").innerHTML = '<spring:message code="common.message.error.validnum"/>';
                return false;
            }
            flag = isCorrect(devicenum, 'numeric', 'deviceNumber_err', '<spring:message code='common.message.error.numeric'/>');
            if(!flag)return false;
            flag = isMinMax(devicenum, parseInt('<spring:message code="mobile.number.length.min"/>'), parseInt('<spring:message code="mobile.number.length"/>'), 'deviceNumber_err', '<spring:message code="common.message.error.validnum"/>');
            if(!flag)return false;

            if (IMEINumber == "" || IMEINumber == null) {
                document.getElementById("IMEINumber_err").innerHTML = '<spring:message code="common.message.error.imei"/>';
                return false;
            }
            flag = isCorrect(IMEINumber, 'numeric', 'IMEINumber_err', '<spring:message code='common.message.error.numeric'/>');
            if(!flag)return false;
            flag = isMinMax(IMEINumber, parseInt('<spring:message code="imei.length.min"/>'), parseInt('<spring:message code="imei.number.length"/>'), 'IMEINumber_err', '<spring:message code="common.message.error.imei"/>');
            if(!flag)return false;

            if (cardSerialNumber == "" || cardSerialNumber.length < parseInt('<spring:message code="card.reader.serial.length.min"/>')) {
                document.getElementById("cardReaderSerialNo_err").innerHTML = '<spring:message code="common.message.error.card.reader.serial"/>';
                return false;
            }
            flag = isCorrect(cardSerialNumber, 'Alphanumeric', 'cardReaderSerialNo_err', '<spring:message code='common.message.error.alphanumeric'/>');
            if(!flag)return false;
            flag = isMinMax(cardSerialNumber, parseInt('<spring:message code="card.reader.serial.length.min"/>'), parseInt('<spring:message code="card.reader.serial.length.max"/>'), 'cardReaderSerialNo_err', '<spring:message code="common.message.error.card.reader.serial"/>');
            if(!flag)return false;

                beforeSubmit('myform');
                document.forms[0].action = "deviceAdd.do";
                document.forms[0].method = "post";
                document.forms[0].submit();

        }



    </script>
</head>


<body onload="noBack();setFocus(document.getElementById('deviceNumber'));" onpageshow="if (event.persisted) noBack();"
      onunload="" class="withvernav">

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
        <h1 class="pagetitle"><spring:message code="merchant.message.device.add"/>
        </h1>
       <%-- <span class="pagedesc"></span>--%>

        <ul class="hornav">

        </ul>
    </div>
    <!--pageheader-->

    <div id="contentwrapper" class="contentwrapper" style="min-height:400px;">

        <div id="basicform" class="subcontent">


            <form:form commandName="deviceModel" id="myform" name="myform" autocomplete="off" onCopy="return false" onPaste="return false">

                <br>

                <table>
                    <tr>
                        <td colspan="3">
                                         <span class="result_color" align="left">
                                    <%
                                        if (session.getAttribute("sequence_info_msg") != null) {
                                    %>
                                             <script>
                                                 alert('<%=session.getAttribute("sequence_info_msg")%>');
                                             </script>
                                    <%
                                        }
                                        session.removeAttribute("sequence_info_msg");

                                    %>   </span>
                        </td>
                    </tr>
                </table>

                <table>
                    <tr>
                        <td colspan="3">
                                         <span class="result_color" align="left">
                                    <%
                                        if (session.getAttribute("device_msg") != null) {
                                    %>
                                    <%=session.getAttribute("device_msg")%>
                                    <%
                                        }
                                        session.removeAttribute("device_msg");

                                    %>   </span>
                        </td>
                    </tr>
                    <tr>
                        <td><p></p></td>
                    </tr>
                </table>


                <c:if test="${!empty deviceModel.deviceList}">
                    <table>
                        <tr>
                            <td>
                                <ul class="buttonlist">
                                    <li>
                                        <a href="#" onclick="doBack();"
                                           class="btn-1 btn_back"><span><spring:message
                                                code="common.message.back"/></span></a>
                                    </li>
                                </ul>
                            </td>
                        </tr>
                    </table>
                </c:if>


                <!--table 1 start here -->
                <table width="100%" height="40%" cellpadding=0 cellspacing=0>
                    <tr>
                        <td>
                            <!--table 2 start here -->
                            <table border="0" width="100%">

                                <tr>
                                    <td align="left"
                                        width="30%"><spring:message code="merchant.message.device.number"/> : <sup
                                            style="color:#ff0000; ">*</sup>
                                    </td>

                                    <td align="left" class="extra" width="20%">
                                        <form:input path="deviceNumber" cssClass="smallinput" id='deviceNumber'
                                                    cssStyle=""
                                                    maxlength='<%=webAppContext.getMessage("mobile.number.length",null,"default-mobile.number.length",null)%>'
                                                    title='<%=webAppContext.getMessage("device.number.help",null,"default-device.number.help",null)%>'
                                                    onkeypress="return MyTrapEnter(this,event,'numeric');" tabindex="1"/>
                                    </td>

                                    <td align="left" width="50%"><span class="error_color" id="deviceNumber_err"
                                                                       align="left"></span></td>
                                </tr>


                                <tr>
                                    <td align="left"
                                        width="30%"><spring:message code="merchant.message.imei.number"/> : <sup
                                            style="color:#ff0000; ">*</sup>
                                    </td>

                                    <td align="left" class="extra" width="20%">
                                        <form:input path="IMEINumber" cssClass="smallinput" id='IMEINumber'
                                                    cssStyle="" maxlength='<%=webAppContext.getMessage("imei.number.length",null,"default-imei.number.length",null)%>'
                                                    title='<%=webAppContext.getMessage("imei.number.help",null,"default-imei.number.help",null)%>'
                                                    onkeypress="return MyTrapEnter(this,event,'numeric');" tabindex="1"/>
                                    </td>

                                    <td align="left" width="50%"><span class="error_color" id="IMEINumber_err"
                                                                       align="left"></span></td>
                                </tr>

                                <tr style="display: none;">
                                    <td align="left" width="30%">
                                            <spring:message code="common.message.status"/>
                                        :<sup style="color:#ff0000; ">*</sup>
                                    <td width="20%">
                                        <form:select path="deviceStatus" cssClass="field" id="deviceStatus"
                                                     tabindex="2"  >
                                            <%--                                        <form:option value="${bankModel.account_type}">${bankModel.account_type}
                                            </form:option>--%>
                                            <c:forEach items="${deviceModel.deviceStatusList}" var="deviceStatus">
                                                <form:option
                                                        value="${deviceStatus.statusId}">${deviceStatus.statusDescription}</form:option>
                                            </c:forEach>
                                        </form:select>
                                    </td>
                                    <td width="50%" align="left"><span id="account_type_err" align="left"></span>
                                    </td>
                                </tr>

                                <tr>
                                    <td align="left"
                                        width="30%"><spring:message code="card.reader.serial"/> : <sup
                                            style="color:#ff0000; ">*</sup>
                                    </td>

                                    <td align="left" class="extra" width="20%">
                                        <form:input path="cardReaderSerialNo" cssClass="smallinput" id='cardReaderSerialNo'
                                                    cssStyle=""
                                                    maxlength='<%=webAppContext.getMessage("card.reader.serial.length.max",null,"default-card.reader.serial.length.max",null)%>'
                                                    onkeypress="return MyTrapEnter(this,event,'Alphanumeric');" tabindex="3"/>          <%--title='<%=webAppContext.getMessage("device.number.help",null,"default-device.number.help",null)%>'--%>
                                    </td>

                                    <td align="left" width="50%"><span class="error_color" id="cardReaderSerialNo_err"
                                                                       align="left"></span></td>
                                </tr>

                                <tr>
                                    <td colspan="2"><p></p></td>

                                </tr>

                                <tr align="center">
                                    <td width="30%">&nbsp;</td>
                                    <td align="left" width="20%">


                                        <div class="buttonwrapper">
                                            <a class="btn btn_rss" href="#" onClick="validateForm();"
                                               tabindex="4">
                                                <span><spring:message code="common.message.create"/></span></a>
                                        </div>


                                    </td>
                                    <td width="50%">&nbsp;</td>
                                </tr>


                            </table>

                        </td>

                    </tr>
                    <!--table 2 end  here -->

                </table>
            </form:form>


        </div>


    </div>

</div>
<div class="clear"></div>
<div class="footer1">

    <%@include file="footer.jsp" %>

</div>

<%-- footer end --%>
</div>
<!--bodywrapper-->

</body>

</html>
