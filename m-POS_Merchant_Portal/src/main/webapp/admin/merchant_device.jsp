<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="html" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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

    <%--<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/stylesheet/style.css">
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
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/custom/forms.js"></script>--%>
    <%--<script type="text/javascript" src="<%=request.getContextPath()%>/resources/includes/js/validations.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/resources/includes/js/validations_client.js"></script>--%>
    <%--<script type="text/javascript" src="<%=request.getContextPath()%>/resources/includes/js/vendor.js"></script>--%>
    <%--<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/mpos1.js"></script>--%>

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

    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/includes/js/validations_client.js"></script>

    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/mpos1.js"></script>

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


    <SCRIPT type="text/javascript">

        window.history.forward();
        function noBack() {
            window.history.forward();
        }
    </SCRIPT>
    <script>

        function trapEnter1(This, evt, type, id) {
            var keycode;
            var next = This.tabIndex;
            var charCode = (evt.which) ? evt.which : event.keyCode
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
                //alert("trap Enter");
                   return doSubmit222(id);
                /*//return false;*/
            }
            else {
                if (type == "numeric") {
                    return isNumericMPOS(charCode);
                }
                else if (type == "Alphanumeric") {
                    return isAlphaNumericMPOS(charCode);
                }
                else if (type == "alpha") {
                    return isAlphabatMPOS(charCode);
                }
                else if (type == "email") {
                    return isEmailMPOS(charCode);
                }
                else if (type == "address") {
                    return isAddressMPOS(charCode);
                }
                else {
                    return  true;
                }
            }
        }

        var oldDeviceId = null;

        function doAddDevice() {
            window.location.href = "deviceAdd.do";
        }


        function doSubmit222(id) {

            document.getElementById('result_id').style.display = 'none';
            var flag = true;
            document.getElementById("idBlankIMEI_error").innerHTML = '';

            var imeiValue = document.getElementById("id_TEXT_IMEI" + id + "2").value.replace(/^\s+|\s+$/g, "");
            var cardSerialNo = document.getElementById("id_TEXT_SERIAL" + id + "2").value.replace(/^\s+|\s+$/g, "");
            var pinValue = document.getElementById("idPinBox" + id).value.replace(/^\s+|\s+$/g, "");

            if (imeiValue == "" || imeiValue == null) {
                document.getElementById("idBlankIMEI_error").innerHTML = '<spring:message code="common.message.error.imei"/>';
                setFocus(document.getElementById("id_TEXT_IMEI" + id + "2"));
                return false;
            }

            flag = isCorrect(imeiValue, 'numeric', 'idBlankIMEI_error', '<spring:message code='common.message.error.numeric'/>');
            if (!flag)
            {
                setFocus(document.getElementById("id_TEXT_IMEI" + id + "2"));
                return false;
            }

            flag = isMinMax(imeiValue, parseInt('<spring:message code="imei.length.min"/>'), parseInt('<spring:message code="imei.number.length"/>'), 'idBlankIMEI_error', '<spring:message code="common.message.error.imei"/>');
            if (!flag)
            {
                setFocus(document.getElementById("id_TEXT_IMEI" + id + "2"));
                return false;
            }

            if (cardSerialNo == "" || cardSerialNo == null || (cardSerialNo.length < parseInt('<spring:message code="card.reader.serial.length.min"/>'))) {
                //document.getElementById("idBlankIMEI_error").innerHTML = document.getElementById("idBlankIMEI_error").innerHTML + '<br>' + '<spring:message code="common.message.error.card.reader.serial"/>';
                document.getElementById("idBlankIMEI_error").innerHTML = '<spring:message code="common.message.error.card.reader.serial"/>';
                setFocus(document.getElementById("id_TEXT_SERIAL" + id + "2"));
                return false;
            }

            flag = isCorrect(cardSerialNo, 'Alphanumeric', 'idBlankIMEI_error', '<spring:message code='common.message.error.alphanumeric'/>');
            if (!flag)
            {
                setFocus(document.getElementById("id_TEXT_SERIAL" + id + "2"));
                return false;
            }

            flag = isMinMax(cardSerialNo, parseInt('<spring:message code="card.reader.serial.length.min"/>'), parseInt('<spring:message code="card.reader.serial.length.max"/>'), 'idBlankIMEI_error', '<spring:message code="common.message.error.card.reader.serial"/>');
            if (!flag)
            {
                setFocus(document.getElementById("id_TEXT_SERIAL" + id + "2"));
                return false;
            }

            if (pinValue == "" || pinValue == null) {
                //document.getElementById("idBlankIMEI_error").innerHTML = document.getElementById("idBlankIMEI_error").innerHTML + '<br>' + document.getElementById("idBlankIMEI_error").innerHTML + '<br>' + '<spring:message code="common.message.error.pin"/>';
                document.getElementById("idBlankIMEI_error").innerHTML = '<spring:message code="common.message.error.pin"/>';
                setFocus(document.getElementById("idPinBox" + id));
                return false;
            }

            flag = isCorrect(pinValue, 'numeric', 'idBlankIMEI_error', '<spring:message code='common.message.error.numeric'/>');
            if (!flag)
            {
                setFocus(document.getElementById("idPinBox" + id));
                return false;
            }

            flag = isMinMax(pinValue, parseInt('<spring:message code="pin.length"/>'), parseInt('<spring:message code="pin.length"/>'), 'idBlankIMEI_error', '<spring:message code="common.message.error.pin"/>');
            if (!flag)
            {
                setFocus(document.getElementById("idPinBox" + id));
                return false;
            }


            document.deviceEditForm.pageAction.value = "other";
            document.deviceEditForm.deviceId.value = id;
            document.deviceEditForm.action = "deviceEdit.do";
            document.deviceEditForm.method = "post";
            document.deviceEditForm.submit();

            return false;

        }


        function doActivate(id) {

            if (oldDeviceId != null) {
                document.getElementById("idPinBox" + oldDeviceId).style.visibility = "hidden";
                document.getElementById("idSubmitBtn" + oldDeviceId).style.visibility = "hidden";


                document.getElementById("id_IMEI" + oldDeviceId).style.display = "table-row";
                document.getElementById("id_SERIAL" + oldDeviceId).style.display = "table-row";
                document.getElementById("id_IMEI" + oldDeviceId + "2").style.display = "none";
                document.getElementById("id_SERIAL" + oldDeviceId + "2").style.display = "none";

            }

            document.getElementById("id_IMEI" + id).style.display = "none";
            document.getElementById("id_SERIAL" + id).style.display = "none";
            document.getElementById("id_IMEI" + id + "2").style.display = "table-row";
            document.getElementById("id_SERIAL" + id + "2").style.display = "table-row";

            document.getElementById("idPinBox" + id).style.visibility = "visible";
            setFocus(document.getElementById("idPinBox" + id));
            document.getElementById("idSubmitBtn" + id).style.visibility = "visible";
            oldDeviceId = id;

        }


    </script>
</head>
<body class="withvernav" onload="noBack();"
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
        <h1 class="pagetitle"><spring:message code="merchant.message.device.add"/>
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

            <span class="error_color" id="idBlankIMEI_error"></span><br>



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

                                    %>
                                 </span>
                        </td>

                        <td colspan="3">
                                         <span id="result_id" class="result_color" align="left">
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


                <table width="100%">
                    <tr>
                        <td>
                            <a href="deviceAdd.do"><input type="button" onclick="doAddDevice();" class="stdbtn"
                                                          value="<spring:message code="merchant.message.add.device"/>"></a>
                        </td>
                    </tr>
                </table>

            <form></form>

            <form name="deviceEditForm" id="deviceEditForm" autocomplete="off" onCopy="return false" onPaste="return false">

                <input type="hidden" name="pageAction" value=""/>
                <input type="hidden" name="deviceId" value=""/>

                <input type="hidden" name="straction" value="deviceList.do"/>
                <c:if test="${!empty deviceModel.deviceList}">
                    <%@include file="pagination.jsp" %>
                </c:if>

                <c:if test="${!empty deviceModel.deviceList}">
                    <table cellpadding="0" cellspacing="0" border="0" class="stdtable">
                        <colgroup>
                            <col class="con0"/>
                            <col class="con1"/>
                            <col class="con0"/>
                            <col class="con1"/>
                            <col class="con0"/>
                        </colgroup>
                        <thead>
                        <tr>
                            <th class="head0"><spring:message code="merchant.message.device.number"/></th>
                            <th class="head1"><spring:message code="merchant.message.imei.number"/></th>
                            <th class="head0"><spring:message code="card.reader.serial"/></th>
                            <th class="head1"><spring:message code="common.message.status"/></th>
                            <th class="head0"><spring:message code="common.message.actions"/></th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach var="device" items="${deviceModel.deviceList}">
                            <tr>
                                <td><c:out value="${device.deviceNumber}"/></td>
                                <td>
                                            <span id="id_IMEI${device.deviceId}" style="display: table-row;">
                                            <c:out value="${device.deviceTerminalId}"/>
                                            </span>
                                            <span id="id_IMEI${device.deviceId}2" style="display:none;">
                                                <input id="id_TEXT_IMEI${device.deviceId}2"
                                                       name="TEXT_IMEI${device.deviceId}2" type="text"
                                                       value="${device.deviceTerminalId}"
                                                       maxlength='<%=webAppContext.getMessage("imei.number.length",null,"default-imei.number.length",null)%>'
                                                       onkeypress="return trapEnter1(this,event,'numeric','${device.deviceId}');"
                                                       title='<%=webAppContext.getMessage("imei.number.help",null,"default-imei.number.help",null)%>'>
                                            </span>
                                </td>
                                <td>
                                            <span id="id_SERIAL${device.deviceId}" style="display: table-row;">
                                            <c:out value="${device.devicePhoneId}"/>
                                            </span>
                                            <span id="id_SERIAL${device.deviceId}2" style="display:none;">
                                                 <input id="id_TEXT_SERIAL${device.deviceId}2"
                                                        name="TEXT_SERIAL${device.deviceId}2" type="text"
                                                        value="${device.devicePhoneId}"
                                                        maxlength='<%=webAppContext.getMessage("card.reader.serial.length.max",null,"default-card.reader.serial.length.max",null)%>'
                                                        onkeypress="return trapEnter1(this,event,'Alphanumeric','${device.deviceId}');">
                                            </span>
                                </td>
                                <td width="20%"><c:out value="${device.status.statusDescription}"/></td>
                                <td>
                                    <c:if test="${device.status.statusId == 2}">
                                        <a href="#" onClick="doActivate('${device.deviceId}');"
                                           class="btn btn_cloud"><span><spring:message
                                                code="common.message.activate"/></span></a>
                                    </c:if>
                                    <c:if test="${device.status.statusId == 1}">
                                        <a href="#" onClick="doActivate('${device.deviceId}');"
                                           class="btn btn_cloud"><span><spring:message
                                                code="common.message.deactivate"/></span></a>
                                    </c:if>

                                        <span>

                                             <input id="idPinBox${device.deviceId}" name="PinBox${device.deviceId}"
                                                    type="password" value=""
                                                    maxlength='<%=webAppContext.getMessage("pin.length",null,"default-pin.length",null)%>'
                                                    style="width: 35%;height: 25px;visibility: hidden;"
                                                    placeholder='<spring:message code="merchant.message.enterpin"/>'
                                                    class="field"
                                                    onkeypress="return trapEnter1(this,event,'numeric','${device.deviceId}');"
                                                    >


                                           </span>


                                    <a id="idSubmitBtn${device.deviceId}" class="btn btn_rss" href="#"
                                       onclick="doSubmit222('${device.deviceId}');" onkeypress="return trapEnter1(this,event,'','${device.deviceId}');"
                                       style="visibility: hidden;"><span><spring:message
                                            code="common.message.submit"/></span></a>


                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>
          </form>

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
