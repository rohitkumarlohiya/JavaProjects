<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/plugins/jquery.uniform.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/plugins/jquery.validate.min.js"></script>
<script type="text/javascript"
        src="<%=request.getContextPath()%>/resources/js/plugins/jquery.tagsinput.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/plugins/charCount.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/plugins/ui.spinner.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/custom/general.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/custom/forms.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/resources/includes/js/validations_client.js"></script>

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


<script type="text/javascript">


    function clearall() {

        document.getElementById("deviceType_err").innerHTML = "";
        document.getElementById("quantity_err").innerHTML = "";
        document.getElementById("registeredOfficeAddress_error").innerHTML = "";
        document.getElementById("stateRegisterOfficeAddr_error").innerHTML = "";
        document.getElementById("cityRegisterOfficeAddr_error").innerHTML = "";
        document.getElementById("zipCodeRegisterOfficeAddrr_error").innerHTML = "";
        document.getElementById("registerOfficePhoneNumber_error").innerHTML = "";
    }


    function validateForm() {
        //alert("hi");
        document.getElementById('result_id').style.display = 'none';
        clearall();
        var flag = true;
        var quantity = document.getElementById("quantity").value.replace(/^\s+|\s+$/g, "");
        var registeredOfficeAddress = document.getElementById("registeredOfficeAddress").value.replace(/^\s+|\s+$/g, "");
        var zipCodeRegisterOfficeAddrr = document.getElementById("zipCodeRegisterOfficeAddrr").value.replace(/^\s+|\s+$/g, "");
        var registerOfficePhoneNumber = document.getElementById("registerOfficePhoneNumber").value.replace(/^\s+|\s+$/g, "")

        if (document.getElementById("deviceType").value < 1) {
            document.getElementById("deviceType_err").innerHTML = '<spring:message code="common.message.error.select.device.type"/>';
            return false;
        }

        if (quantity.length < 1) {
            document.getElementById("quantity_err").innerHTML = '<spring:message code="common.message.error.enter.quantity"/>';
            return false;
        }

        flag = isCorrect(quantity, 'numeric', 'quantity_err', '<spring:message code='common.message.error.numeric'/>');
        if (!flag)return false;


        if (!document.getElementById("sameAsRegisteredAddress").checked) {    ///    if unchecked
            if (registeredOfficeAddress.length < 1) {
                document.getElementById("registeredOfficeAddress_error").innerHTML = '<spring:message code="common.message.error.regAdd"/>';
                return false;
            }

            flag = isCorrect(registeredOfficeAddress, 'address', 'registeredOfficeAddress_error', '<spring:message code='common.message.error.valid.address'/>');
            if (!flag)return false;
            flag = isMinMax(registeredOfficeAddress, parseInt('<spring:message code="adress.line.length.min"/>'), parseInt('<spring:message code="adress.line.length.max"/>'), 'registeredOfficeAddress_error', '<spring:message code="common.address.minmax.error"/>');
            if (!flag)return false;

            if (document.getElementById("stateRegisterOfficeAddr").value < 1) {
                document.getElementById("stateRegisterOfficeAddr_error").innerHTML = '<spring:message code="common.message.error.state"/>';
                return false;
            }

            if (document.getElementById("cityRegisterOfficeAddr").value == "NONE") {
                document.getElementById("cityRegisterOfficeAddr_error").innerHTML = '<spring:message code="common.message.error.city"/>';
                return false;
            }

            if (zipCodeRegisterOfficeAddrr == "" || zipCodeRegisterOfficeAddrr == null) {
                document.getElementById("zipCodeRegisterOfficeAddrr_error").innerHTML = '<spring:message code="common.message.error.zip"/>';
                return false;
            }

            flag = isCorrect(zipCodeRegisterOfficeAddrr, 'numeric', 'zipCodeRegisterOfficeAddrr_error', '<spring:message code='common.message.error.numeric'/>');
            if (!flag)return false;

            flag = isMinMax(zipCodeRegisterOfficeAddrr, parseInt('<spring:message code="zipcode.length.min"/>'), parseInt('<spring:message code="zipcode.length.max"/>'), 'zipCodeRegisterOfficeAddrr_error', '<spring:message code="common.message.error.zip"/>');
            if (!flag)return false;


            if (registerOfficePhoneNumber == "" || registerOfficePhoneNumber == null) {
                document.getElementById("registerOfficePhoneNumber_error").innerHTML = '<spring:message code="common.message.error.validnum"/>';
                return false;
            }

            flag = isCorrect(registerOfficePhoneNumber, 'numeric', 'registerOfficePhoneNumber_error', '<spring:message code='common.message.error.numeric'/>');
            if (!flag)return false;

            flag = isMinMax(registerOfficePhoneNumber, parseInt('<spring:message code="mobile.number.length.min"/>'), parseInt('<spring:message code="mobile.number.length"/>'), 'registerOfficePhoneNumber_error', '<spring:message code="common.message.error.validnum"/>');
            if (!flag)return false;
        }

            //alert("inside submit");
            beforeSubmit("myForm");
            document.forms[0].action = "orderDeviceAdd.do";
            document.forms[0].method = "post";
            document.forms[0].submit();

    }


        function copy() {

            if (document.getElementById("sameAsRegisteredAddress").checked) {
                clearall();
                //alert("checked");

                document.getElementById("idtr1").style.display = 'none';
                document.getElementById("idtr2").style.display = 'none';
                document.getElementById("idtr3").style.display = 'none';
                document.getElementById("idtr4").style.display = 'none';
                document.getElementById("idtr5").style.display = 'none';
            }
            else {
                // alert("unchecked");
                document.getElementById("idtr1").style.display = 'table-row';
                document.getElementById("idtr2").style.display = 'table-row';
                document.getElementById("idtr3").style.display = 'table-row';
                document.getElementById("idtr4").style.display = 'table-row';
                document.getElementById("idtr5").style.display = 'table-row';
            }
        }

        function createRequestObject() {
            var tmpXmlHttpObject;
            if (window.XMLHttpRequest) {
                tmpXmlHttpObject = new XMLHttpRequest();
            } else if (window.ActiveXObject) {
                tmpXmlHttpObject = new ActiveXObject("Microsoft.XMLHTTP");
            }
            return tmpXmlHttpObject;
        }

        var http = createRequestObject();


        function getCityListByStateId_Permanent() {

            var stateId = document.getElementById('stateRegisterOfficeAddr').value;
            if (stateId != -1) {
                document.getElementById('loading1').style.display = 'table-cell';
                http.open('get', 'getStateList/' + stateId);
                http.onreadystatechange = processSrcResponseCity_Permanent;
                http.send(null);
            }
            else {
                var x = document.getElementById('cityRegisterOfficeAddr');
                for (i = x.options.length - 1; i >= 0; i--) {
                    x.remove(i);
                }
                addOption(x, "--- Select ---", "NONE");
                x.selectedIndex = 0;
            }
        }

        function processSrcResponseCity_Permanent() {
            var selectIndex = -1;
            if (http.readyState == 4) {
                if (http.status == 200) {
                    response1 = http.responseXML.getElementsByTagName("result")[0];
                    var result = response1.childNodes[0].nodeValue.split("####");
                    var x = document.getElementById('cityRegisterOfficeAddr');

                    for (i = x.options.length - 1; i > 0; i--) {
                        x.remove(i);
                    }

                    if (result.length != null && result.length != "") {

                        for (var i = 0; i < result.length - 1; i++) {

                            var value = result[i].split("|");

                            var resId = value[0];
                            var resName = value[1];

                            if (resId != 0) {
                                addOption(x, resName, resId);
                            }
                        }
                    }
                    x.selectedIndex = 0;
                    document.getElementById('loading1').style.display = 'none';
                }
            }
        }


</script>


</head>

<body class="withvernav" onload="setFocus(document.getElementById('deviceType'));">

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
    <h1 class="pagetitle"><spring:message code="order.card.reader"/>
    </h1>
    <%--<span class="pagedesc"></span>--%>

    <ul class="hornav">

    </ul>
</div>
<!--pageheader-->

<div id="contentwrapper" class="contentwrapper" style="min-height:400px;">

<div id="basicform" class="subcontent">


<form:form commandName="deviceOrderModel" name="myForm" id="myForm" autocomplete="off" onCopy="return false"
           onPaste="return false">

    <br>
    <table border="0" width="80%">

        <tr>
            <td colspan="3">
                    <span id="result_id" class="result_color" align="left">
                    <%
                        if (session.getAttribute("order_device_msg") != null) {
                    %>
                    <%=session.getAttribute("order_device_msg")%>
                    <%
                        }
                        session.removeAttribute("order_device_msg");
                    %>   </span>
            </td>
        </tr>

        <tr>
            <td colspan="3"><p></p></td>
        </tr>


        <tr>
            <td class="extra1"><spring:message code="device.type"/> : <sup style="color:#ff0000; ">*</sup></td>

            <td class="extra1">
                <form:select path="deviceType" cssClass="field" id="deviceType" tabindex="1" cssStyle="width:93%;">
                    <form:option
                            value="-1"><spring:message code="common.message.selrequired"/>
                    </form:option>

                    <c:forEach items="${deviceOrderModel.deviceTypeList}" var="deviceType">
                        <form:option
                                value="${deviceType.deviceTypeId}">${deviceType.deviceTypeDescription}</form:option>
                    </c:forEach>
                </form:select>
            </td>

            <td align="left"><span id="deviceType_err" class="error_color" align="left"></span></td>
        </tr>


        <tr>
            <td class="extra1"><spring:message code="order.quantity"/> : <sup style="color:#ff0000; ">*</sup></td>

            <td class="extra1">
                <form:input path="quantity" cssClass="smallinput" id='quantity' tabindex="2" maxlength="6"
                            onkeypress="return MyTrapEnter(this,event,'numeric');"/>
            </td>

            <td align="left"><span id="quantity_err" class="error_color" align="left"></span></td>
        </tr>


        <tr>
            <td class="extra1" colspan="3"><form:checkbox path="sameAsRegisteredAddress" id="sameAsRegisteredAddress"
                                                          tabindex="3"
                                                          onclick="copy();" onkeypress="copy();"/>
                <spring:message code="merchant.message.delivery.address.same.as.registered.address"/>
            </td>
        </tr>


            <%--  Added hidden fields--%>
        <tr id="idtr6" style="display: none; ">
            <td class="extra1"><spring:message code="merchant.message.delivery.address"/> : <sup
                    style="color:#ff0000; ">*</sup></td>

            <td class="extra1"><form:input path="regisAdd" cssClass="field" id="regisAdd"/></td>
            <td></td>
        </tr>


        <tr id="idtr7" style="display: none; ">
            <td class="extra1"><spring:message code="merchant.message.state"/> : <sup
                    style="color:#ff0000; ">*</sup>
            </td>
            <td class="extra1"><form:input path="regisState" cssClass="field" id="regisState"/></td>
            <td></td>
        </tr>

        <tr id="idtr8" style="display: none; ">
            <td class="extra1"><spring:message code="merchant.message.city"/> : <sup
                    style="color:#ff0000; ">*</sup>
            </td>
            <td class="extra1"><form:input path="regisCity" cssClass="field" id="regisCity"/></td>
            <td></td>
        </tr>

        <tr id="idtr9" style="display: none; ">
            <td class="extra1"><spring:message code="merchant.message.pincode"/> : <sup
                    style="color:#ff0000; ">*</sup></td>
            <td class="extra1"><form:input path="regisZipCode" cssClass="field" id="regisZipCode"/></td>
            <td></td>
        </tr>


        <tr id="idtr10" style="display: none; ">
            <td class="extra1"><spring:message code="common.message.phonenumber"/> : <sup
                    style="color:#ff0000; ">*</sup></td>
            <td class="extra1"><form:input path="regisPhoneNumber" cssClass="field" id="regisPhoneNumber"/></td>
            <td></td>
        </tr>

            <%--  Added hidden fields--%>


        <tr id="idtr1" style="display: table-row; ">
            <td class="extra1"><spring:message code="merchant.message.delivery.address"/> : <sup
                    style="color:#ff0000; ">*</sup>
            </td>
            <td class="extra1"><form:input path="registeredOfficeAddress" cssClass="field" id="registeredOfficeAddress"
                                           onkeypress="return MyTrapEnter(this,event,'address');" tabindex="4"
                                           maxlength='<%=webAppContext.getMessage("adress.line.length.max",null,"default-adress.line.length.max",null)%>'/>
            </td>
            <td><span class="error_color" id="registeredOfficeAddress_error"></span>
            </td>
        </tr>

        <tr id="idtr2" style="display: table-row; ">
            <td class="extra1"><spring:message code="merchant.message.state"/> : <sup
                    style="color:#ff0000; ">*</sup>
            </td>
            <td class="extra1"><form:select path="stateRegisterOfficeAddr" cssClass="field" id="stateRegisterOfficeAddr"
                                            tabindex="5"
                                            onchange="getCityListByStateId_Permanent()" cssStyle="width:93%;">
                <form:option value="-1" label="--- Select ---"/>
                <c:forEach items="${deviceOrderModel.stateList}" var="state">
                    <form:option value="${state.stateId}">${state.stateDescription}</form:option>
                </c:forEach>
            </form:select></td>
            <td><span class="error_color" id="stateRegisterOfficeAddr_error"></span>
            </td>
        </tr>

        <tr id="idtr3" style="display: table-row; ">
            <td class="extra1"><spring:message code="merchant.message.city"/> : <sup
                    style="color:#ff0000; ">*</sup>
            </td>
            <td class="extra1"><form:select path="cityRegisterOfficeAddr" cssClass="field" id="cityRegisterOfficeAddr"
                                            tabindex="6" cssStyle="width:93%;">
                <form:option value="NONE" label="--- Select ---"/>
            </form:select></td>
            <td>
                <div id="loading1" style="display: none;"><img border='0'
                                                               src='<%=request.getContextPath()%>/resources/images/common/loading.gif'
                                                               width='20' height='20'></div>
                <span class="error_color" id="cityRegisterOfficeAddr_error"></span>
            </td>
        </tr>

        <tr id="idtr4" style="display: table-row; ">
            <td class="extra1"><spring:message code="merchant.message.pincode"/> : <sup
                    style="color:#ff0000; ">*</sup>
            </td>
            <td class="extra1"><form:input path="zipCodeRegisterOfficeAddrr" cssClass="field"
                                           id="zipCodeRegisterOfficeAddrr"
                                           onkeypress="return MyTrapEnter(this,event,'numeric');" tabindex="7"
                                           maxlength='<%=webAppContext.getMessage("zipcode.length.max",null,"default-zipcode.length.max",null)%>'/>
            </td>
            <td><span class="error_color" id="zipCodeRegisterOfficeAddrr_error"></span>
            </td>
        </tr>

        <tr id="idtr5" style="display: table-row; ">
            <td class="extra1"><spring:message code="common.message.phonenumber"/> : <sup
                    style="color:#ff0000; ">*</sup>
            </td>
            <td class="extra1"><form:input path="registerOfficePhoneNumber" cssClass="field"
                                           id="registerOfficePhoneNumber"
                                           onkeypress="return MyTrapEnter(this,event,'numeric');" tabindex="8"
                                           maxlength='<%=webAppContext.getMessage("mobile.number.length",null,"default-mobile.number.length",null)%>'/>
            </td>
            <td><span class="error_color" id="registerOfficePhoneNumber_error"></span>
            </td>
        </tr>

        <tr>
            <td colspan="2"><p></p></td>
        </tr>

        <tr align="center">
            <td width="30%">&nbsp;</td>
            <td align="left" width="20%">


                <div class="buttonwrapper">
                    <a class="btn btn_rss" href="#" onClick="validateForm();" tabindex="9"
                       onkeypress="return MyTrapEnter(this,event,'');">
                        <span><spring:message code="order.device"/></span></a>
                </div>


            </td>
            <td width="50%">&nbsp;</td>
        </tr>

    </table>


</form:form>
<%--<br>--%>

<form:form name="deviceOrderFormView" id="deviceOrderFormView" commandName="deviceOrderModel">

    <input type="hidden" name="straction" value="orderDevice.do"/>
    <c:if test="${!empty deviceOrderModel.deviceOrderList}">
        <%@include file="pagination.jsp" %>
    </c:if>

    <c:if test="${!empty deviceOrderModel.deviceOrderList}">
        <table cellpadding="0" cellspacing="0" border="0" class="stdtable">
            <colgroup>
                <col class="con0"/>
                <col class="con1"/>
                <col class="con0"/>
                <col class="con1"/>
                    <%--<col class="con0"/>--%>
            </colgroup>
            <thead>
            <tr>
                <th class="head0"><spring:message code="order.number"/></th>
                <th class="head1"><spring:message code="quantity"/></th>
                <th class="head0"><spring:message code="device.type"/></th>
                <th class="head1"><spring:message code="order.status"/></th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="deviceOrder" items="${deviceOrderModel.deviceOrderList}">
                <tr>
                    <td width="25%"><c:out value="${deviceOrder.deviceOrderOrderNo}"/></td>
                    <td width="25%"><c:out value="${deviceOrder.deviceOrderQuantity}"/></td>
                    <td width="25%">
                        <c:forEach items="${deviceOrderModel.deviceTypeList}" var="deviceType">
                            <c:if test="${deviceType.deviceTypeId == deviceOrder.deviceOrderDtId}">
                                ${deviceType.deviceTypeDescription}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td width="25%">
                        <c:forEach items="${deviceOrderModel.statusList}" var="status">
                        <c:if test="${status.statusId == deviceOrder.deviceOrderStatus}">
                            ${status.statusDescription}
                        </c:if>
                        </c:forEach>
                            <%--<c:out value="${deviceOrder.deviceOrderStatus}"/></td>--%>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

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
