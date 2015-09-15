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
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/includes/js/validations.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/includes/js/validations_client.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/includes/js/vendor.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/mpos.js"></script>


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

    $(document).ready(
            function () {

                $('#alternateMobileNumber').tooltipster({
                    offsetX:0,
                    offsetY:25,
                    trigger:'click'
                });
            }
    );

</script>

<script type="text/javascript">


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

function getCityListByStateId() {

    var stateId = document.getElementById('residentialState').value;
    if (stateId != -1) {
        document.getElementById('loading1').style.display = 'table-cell';
        http.open('get', 'getStateList/' + stateId);
        http.onreadystatechange = processSrcResponseCity;
        http.send(null);
    }
    else {
        var x = document.getElementById('residentialCity');
        for (i = x.options.length - 1; i >= 0; i--) {
            x.remove(i);
        }
        addOption(x, "--- Select ---", "NONE");
        x.selectedIndex = 0;
    }
}

function processSrcResponseCity() {
    var selectIndex = -1;
    if (http.readyState == 4) {
        if (http.status == 200) {
            response1 = http.responseXML.getElementsByTagName("result")[0];
            var result = response1.childNodes[0].nodeValue.split("####");
            var x = document.getElementById('residentialCity');

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

function getCityListByStateId_Permanent() {

    var stateId = document.getElementById('permanentState').value;
    if (stateId != -1) {
        document.getElementById('loading2').style.display = 'table-cell';
        http.open('get', 'getStateList/' + stateId);
        http.onreadystatechange = processSrcResponseCity_Permanent;
        http.send(null);
    }
    else {
        var x = document.getElementById('permanentCity');
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
            var x = document.getElementById('permanentCity');

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
            document.getElementById('loading2').style.display = 'none';
        }
    }
}


function updateFields(This) {
    //alert("hi");
    var next = This.tabIndex;

    if (next == 2 && document.getElementById("sameAddressOffnHome").checked) {

        document.getElementById("permAdd").value = document.getElementById("residentialAddress").value;
    }
    else if (next == 5 && document.getElementById("sameAddressOffnHome").checked) {

        document.getElementById("permZipCode").value = document.getElementById("residentialZipCode").value;
    }
    else if (next == 3 && document.getElementById("sameAddressOffnHome").checked) {
        document.getElementById("permState").value = document.getElementById("residentialState").options[document.getElementById("residentialState").selectedIndex].text;
        document.getElementById("permCity").value = document.getElementById("residentialCity").options[0].text;
    }
    else if (next == 4 && document.getElementById("sameAddressOffnHome").checked) {

        //document.getElementById("permCity").value = document.getElementById("residentialCity").value;
        document.getElementById("permCity").value = document.getElementById("residentialCity").options[document.getElementById("residentialCity").selectedIndex].text;
    }
    else {
        return true;
    }

    return true;
}


function clearall() {
    //alert("clearall");
    document.getElementById("contactName_error").innerHTML = "";
    document.getElementById("residentialAddress_error").innerHTML = "";
    document.getElementById("residentialState_error").innerHTML = "";
    document.getElementById("residentialCity_error").innerHTML = "";
    document.getElementById("residentialZipCode_error").innerHTML = "";
    document.getElementById("mobileNumber_error").innerHTML = "";
    document.getElementById("alternateMobileNumber_error").innerHTML = "";
    document.getElementById("permanentAddress_error").innerHTML = "";
    document.getElementById("permanentState_error").innerHTML = "";
    document.getElementById("permanentCity_error").innerHTML = "";
    document.getElementById("permanentZipCode_error").innerHTML = "";
}

function forCheckbox() {
    var flag = true;
    var contactName = document.getElementById("contactName").value.replace(/^\s+|\s+$/g, "");
    var residentialAddress = document.getElementById("residentialAddress").value.replace(/^\s+|\s+$/g, "");
    var residentialZipCode = document.getElementById("residentialZipCode").value.replace(/^\s+|\s+$/g, "");
    var mobileNumber = document.getElementById("mobileNumber").value.replace(/^\s+|\s+$/g, "");
    var alternateMobileNumber = document.getElementById("alternateMobileNumber").value.replace(/^\s+|\s+$/g, "");
    var permanentZipCode = document.getElementById("permanentZipCode").value.replace(/^\s+|\s+$/g, "");
    var permanentAddress = document.getElementById("permanentAddress").value.replace(/^\s+|\s+$/g, "");

    if (residentialAddress == "" || residentialAddress == null) {
        document.getElementById("residentialAddress_error").innerHTML = '<spring:message code="common.message.error.residenceaddress"/>';
        return false;
    }
    flag = isCorrect(residentialAddress, 'address', 'residentialAddress_error', '<spring:message code='common.message.error.valid.address'/>');
    if (!flag)return false;
    flag = isMinMax(residentialAddress, parseInt('<spring:message code="adress.line.length.min"/>'), parseInt('<spring:message code="adress.line.length.max"/>'), 'residentialAddress_error', '<spring:message code="common.address.minmax.error"/>');
    if (!flag)return false;

    if (document.getElementById("residentialState").value < 1) {
        document.getElementById("residentialState_error").innerHTML = '<spring:message code="common.message.error.state"/>';
        return false;
    }
    if (document.getElementById("residentialCity").value == "NONE") {
        document.getElementById("residentialCity_error").innerHTML = '<spring:message code="common.message.error.city"/>';
        return false;
    }
    if (residentialZipCode == "" || residentialZipCode == null) {
        document.getElementById("residentialZipCode_error").innerHTML = '<spring:message code="common.message.error.zip"/>';
        return false;
    }

    flag = isCorrect(residentialZipCode, 'numeric', 'residentialZipCode_error', '<spring:message code='common.message.error.numeric'/>');
    if (!flag) {
        return false;
    }
    flag = isMinMax(residentialZipCode, parseInt('<spring:message code="zipcode.length.min"/>'), parseInt('<spring:message code="zipcode.length.max"/>'), 'residentialZipCode_error', '<spring:message code="common.message.error.zip"/>');
    if (!flag) {
        return false;
    }
    return true;
   // alert("true");

}

function validateForm() {

    clearall();

    var flag = true;
    var contactName = document.getElementById("contactName").value.replace(/^\s+|\s+$/g, "");
    var residentialAddress = document.getElementById("residentialAddress").value.replace(/^\s+|\s+$/g, "");
    var residentialZipCode = document.getElementById("residentialZipCode").value.replace(/^\s+|\s+$/g, "");
    var mobileNumber = document.getElementById("mobileNumber").value.replace(/^\s+|\s+$/g, "");
    var alternateMobileNumber = document.getElementById("alternateMobileNumber").value.replace(/^\s+|\s+$/g, "");
    var permanentZipCode = document.getElementById("permanentZipCode").value.replace(/^\s+|\s+$/g, "");
    var permanentAddress = document.getElementById("permanentAddress").value.replace(/^\s+|\s+$/g, "");

    if (contactName == "" || contactName == null) {
        document.getElementById("contactName_error").innerHTML = '<spring:message code="merchants.message.error.subsname"/>';
        return false
    }
    flag = isCorrect(contactName, 'alpha', 'contactName_error', '<spring:message code='common.message.error.alphabets'/>');
    if (!flag)return false;
    /*flag = isMinMax(contactName, parseInt('<spring:message code="name.length.min"/>'), 100, 'contactName_error', '<spring:message code="common.name.minmax.error"/>');
    if (!flag)return false;*/

    if(!forCheckbox()){
        //alert("validate form check");
       return false;
    }

    var checkmob = true;
    var checkalt = true;
    if (mobileNumber == "" || mobileNumber == null) {
        checkmob = false;
        document.getElementById("mobileNumber_error").innerHTML = '<spring:message code="common.message.error.validnum"/>';
        return false;
    }
    flag = isCorrect(mobileNumber, 'numeric', 'mobileNumber_error', '<spring:message code='common.message.error.numeric'/>');
    if (!flag)return false;

    flag = isMinMax(mobileNumber, parseInt('<spring:message code="mobile.number.length.min"/>'), parseInt('<spring:message code="mobile.number.length"/>'), 'mobileNumber_error', '<spring:message code="common.message.error.validnum"/>');
    if (!flag)return false;

    if (alternateMobileNumber == "" || alternateMobileNumber == null) {
        document.getElementById("alternateMobileNumber_error").innerHTML = '<spring:message code="common.message.error.validnum"/>';
        return false;
    }
    flag = isCorrect(alternateMobileNumber, 'numeric', 'alternateMobileNumber_error', '<spring:message code='common.message.error.numeric'/>');
    if (!flag)return false;

    flag = isMinMax(alternateMobileNumber, parseInt('<spring:message code="mobile.number.length.min"/>'), parseInt('<spring:message code="mobile.number.length"/>'), 'alternateMobileNumber_error', '<spring:message code="common.message.error.validnum"/>');
    if (!flag)return false;


    if (mobileNumber == alternateMobileNumber) {
        document.getElementById("alternateMobileNumber_error").innerHTML = '<spring:message code="common.message.error.samenum"/>';
        return false;
    }


    if (!document.getElementById("sameAddressOffnHome").checked) {

        if (permanentAddress == "" || permanentAddress == null) {
            document.getElementById("permanentAddress_error").innerHTML = '<spring:message code="common.message.error.permanentaddress"/>';
            return false;
        }
        flag = isCorrect(permanentAddress, 'address', 'permanentAddress_error', '<spring:message code='common.message.error.valid.address'/>');
        if (!flag)return false;
        flag = isMinMax(permanentAddress, parseInt('<spring:message code="adress.line.length.min"/>'), parseInt('<spring:message code="adress.line.length.max"/>'), 'permanentAddress_error', '<spring:message code="common.address.minmax.error"/>');
        if (!flag)return false;

        if (document.getElementById("permanentState").value < 1) {
            document.getElementById("permanentState_error").innerHTML = "";
            document.getElementById("permanentState_error").innerHTML = '<spring:message code="common.message.error.state"/>';
            return false;
        }
        if (document.getElementById("permanentCity").value == "NONE") {
            document.getElementById("permanentCity_error").innerHTML = "";
            document.getElementById("permanentCity_error").innerHTML = '<spring:message code="common.message.error.city"/>';
            return false;
        }
        if (permanentZipCode == "" || permanentZipCode == null) {
            document.getElementById("permanentZipCode_error").innerHTML = '<spring:message code="common.message.error.zip"/>';
            return false;
        }

        flag = isCorrect(permanentZipCode, 'numeric', 'permanentZipCode_error', '<spring:message code='common.message.error.numeric'/>');
        if (!flag)return false;
        flag = isMinMax(permanentZipCode, parseInt('<spring:message code="zipcode.length.min"/>'), parseInt('<spring:message code="zipcode.length.max"/>'), 'permanentZipCode_error', '<spring:message code="common.message.error.zip"/>');
        if (!flag)return false;

    }
    beforeSubmit('myform');
    document.forms[0].action = "merchantProfileAdd.do";
    document.forms[0].method = "post";
    //alert("submitting");
    document.forms[0].submit();


}


function copy() {

    if (document.getElementById("sameAddressOffnHome").checked) {
        //  alert("checked");
        var flag = true;
        var residentialAddress = document.getElementById("residentialAddress").value.replace(/^\s+|\s+$/g, "");
        var residentialState = document.getElementById("residentialState");
        var residentialCity = document.getElementById("residentialCity");
        var residentialZipCode = document.getElementById("residentialZipCode").value.replace(/^\s+|\s+$/g, "");
        var check = forCheckbox();
       // alert("check is "+check)
        if (check) {

            clearall();
            document.getElementById("permAdd").value = residentialAddress;
            document.getElementById("permAdd").readOnly = true;

            document.getElementById("permState").value = residentialState.options[residentialState.selectedIndex].text;
            document.getElementById("permState").readOnly = true;

            document.getElementById("permCity").value = residentialCity.options[residentialCity.selectedIndex].text;
            document.getElementById("permCity").readOnly = true;

            document.getElementById("permZipCode").value = residentialZipCode;
            document.getElementById("permZipCode").readOnly = true;

            document.getElementById("idtr1").style.display = 'none';
            document.getElementById("idtr2").style.display = 'none';
            document.getElementById("idtr3").style.display = 'none';
            document.getElementById("idtr4").style.display = 'none';

            document.getElementById("idtr5").style.display = 'table-row';
            document.getElementById("idtr6").style.display = 'table-row';
            document.getElementById("idtr7").style.display = 'table-row';
            document.getElementById("idtr8").style.display = 'table-row';

        }
        else {
            clearall();

            if (residentialAddress == "" || residentialAddress == null) {
                document.getElementById("residentialAddress_error").innerHTML = '<spring:message code="common.message.error.residenceaddress"/>';

            }
            isCorrect(residentialAddress, 'address', 'residentialAddress_error', '<spring:message code='common.message.error.valid.address'/>');
            isMinMax(residentialAddress, parseInt('<spring:message code="adress.line.length.min"/>'), parseInt('<spring:message code="adress.line.length.max"/>'), 'residentialAddress_error', '<spring:message code="common.address.minmax.error"/>');
            if (document.getElementById("residentialState").value < 1) {
                document.getElementById("residentialState_error").innerHTML = '<spring:message code="common.message.error.state"/>';

            }
            if (document.getElementById("residentialCity").value == "NONE") {
                document.getElementById("residentialCity_error").innerHTML = '<spring:message code="common.message.error.city"/>';

            }
            if (residentialZipCode == "" || residentialZipCode == null) {
                document.getElementById("residentialZipCode_error").innerHTML = '<spring:message code="common.message.error.zip"/>';

            }
            isCorrect(residentialZipCode, 'numeric', 'residentialZipCode_error', '<spring:message code='common.message.error.numeric'/>');


            isMinMax(residentialZipCode, parseInt('<spring:message code="zipcode.length.min"/>'), parseInt('<spring:message code="zipcode.length.max"/>'), 'residentialZipCode_error', '<spring:message code="common.message.error.zip"/>');
            //alert("res") ;
            document.getElementById("sameAddressOffnHome").checked = false;
        }
    }
    else  //if unchecked
    {
        //alert ("unchecked");

        document.getElementById("idtr5").style.display = 'none';
        document.getElementById("idtr6").style.display = 'none';
        document.getElementById("idtr7").style.display = 'none';
        document.getElementById("idtr8").style.display = 'none';

        document.getElementById("permAdd").readOnly = false;
        document.getElementById("permState").readOnly = false;
        document.getElementById("permCity").readOnly = false;
        document.getElementById("permZipCode").readOnly = false;

        document.getElementById("idtr1").style.display = 'table-row';
        document.getElementById("idtr2").style.display = 'table-row';
        document.getElementById("idtr3").style.display = 'table-row';
        document.getElementById("idtr4").style.display = 'table-row';
        clearall();
    }
}


</script>
</head>

<body class="withvernav" onload="setFocus(document.getElementById('contactName'));copy();">

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
    <h1 class="pagetitle"><spring:message code="merchant.message.basicprofileinfo.add"/>
    </h1>
    <%--<span class="pagedesc"></span>--%>

    <ul class="hornav">

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


<form:form method="POST" commandName="userModel" id="myform"
           name="myform" autocomplete="off" onCopy="return false" onPaste="return false">


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

<table width="85%">

<tr>
    <td colspan="3" class="extra1"><p></p></td>
</tr>

<tr>
    <td width="30%" class="extra1"><spring:message code="merchant.message.merchantname"/> : <sup
            style="color:#ff0000; ">*</sup>
            <%--<br><spring:message code="contact.person.name"/>--%>
    </td>
    <td width="20%" class="extra1"><form:input path="contactName" cssClass="field" id="contactName"
                                               onkeypress="return MyTrapEnter(this,event,'alpha');" tabindex="1"
                                               maxlength="50" readonly="true"/>
    </td>
    <td width="50%"><span id="contactName_error" class="error_color"></span>
    </td>
</tr>

<tr>
    <td class="extra1"><spring:message code="merchant.message.residentialaddress"/> : <sup
            style="color:#ff0000; ">*</sup></td>
    <td class="extra1"><form:input path="residentialAddress" cssClass="field"
                                   onkeypress="return MyTrapEnter(this,event,'address');"
                                   tabindex="2"
                                   onkeyup="updateFields(this);"
                                   id="residentialAddress"
                                   maxlength='<%=webAppContext.getMessage("adress.line.length.max",null,"default-adress.line.length.max",null)%>'/></td>
    <td>
            <%--<form:errors path="residentialAddress" cssClass="error" />--%>
										<span class="error_color" id="residentialAddress_error">

									</span>
    </td>
</tr>

<tr>
    <td class="extra1"><spring:message code="merchant.message.state"/> : <sup
            style="color:#ff0000; ">*</sup>
    </td>
    <td class="extra1">
        <form:select path="residentialState" id="residentialState" size="1" cssClass="field" tabindex="3"
                     onchange="getCityListByStateId();updateFields(this);" cssStyle="width:93%;">
            <form:option value="-1"><spring:message code="common.message.selrequired"/>
            </form:option>
            <c:forEach items="${userModel.stateList}" var="state">
                <form:option value="${state.stateId}">${state.stateDescription}</form:option>
            </c:forEach>
        </form:select>
    </td>
    <td><span class="error_color" id="residentialState_error">

									</span>
</tr>


<tr>
    <td class="extra1"><spring:message code="merchant.message.city"/> : <sup
            style="color:#ff0000; ">*</sup>
    </td>
    <td class="extra1">
        <form:select path="residentialCity" id="residentialCity" size="1" cssClass="field" tabindex="4"
                     onchange="updateFields(this);" cssStyle="width:93%;">
            <form:option value="NONE"><spring:message code="common.message.selrequired"/>
            </form:option>
            <%--<c:forEach items="${userModel.cityList}" var="city">
                <form:option value="${city.description}">${city.description}</form:option>
            </c:forEach>--%>
        </form:select>
    </td>
    <td>
        <div id="loading1" style="display: none;"><img border='0'
                                                       src='<%=request.getContextPath()%>/resources/images/common/loading.gif'
                                                       width='20' height='20'></div><span class="error_color"
                                                                                          id="residentialCity_error">

									</span></td>
</tr>


<tr>
    <td class="extra1"><spring:message code="merchant.message.pincode"/> : <sup
            style="color:#ff0000; ">*</sup></td>
    <td class="extra1"><form:input path="residentialZipCode" cssClass="field"
                                   id="residentialZipCode" onkeypress="return MyTrapEnter(this,event,'numeric');"
                                   tabindex="5"
                                   onkeyup="updateFields(this);"
                                   maxlength='<%=webAppContext.getMessage("zipcode.length.max",null,"default-zipcode.length.max",null)%>'/></td>
    <td>
            <%--<form:errors path="residentialZipCode" cssClass="error" />--%>
										<span class="error_color" id="residentialZipCode_error">

									</span>
    </td>
</tr>

<tr>
    <td class="extra1"><spring:message code="merchant.message.mobilenumber"/> : <sup
            style="color:#ff0000; ">*</sup></td>
    <td class="extra1"><form:input path="mobileNumber" cssClass="field"
                                   id="mobileNumber" onkeypress="return MyTrapEnter(this,event,'numeric');" tabindex="6"

                                   maxlength='<%=webAppContext.getMessage("mobile.number.length",null,"default-mobile.number.length",null)%>'
                                   readonly="true"/></td>
        <%--  title='<spring:message code="mobile.number.help"/>'--%>
    <td>
            <%--<form:errors path="mobileNumber" cssClass="error" />--%> <span
            class="error_color" id="mobileNumber_error"> </span>
    </td>
</tr>

<tr>
    <td class="extra1"><spring:message code="merchant.message.alternatenumber"/> : <sup
            style="color:#ff0000; ">*</sup></td>
    <td class="extra1"><form:input path="alternateMobileNumber"
                                   cssClass="field" id="alternateMobileNumber"
                                   title='<%=webAppContext.getMessage("alternate.mobile.number.help",null,"default-alternate.mobile.number.help",null)%>'
                                   onkeypress="return MyTrapEnter(this,event,'numeric');" tabindex="7"
                                   maxlength='<%=webAppContext.getMessage("mobile.number.length",null,"default-mobile.number.length",null)%>'/></td>
    <td>

										<span class="error_color" id="alternateMobileNumber_error">

									</span>
    </td>
</tr>

<tr>
    <td colspan="2" class="extra1"><form:checkbox path="sameAddressOffnHome"
                                                  cssClass="field" id="sameAddressOffnHome" onclick="copy();"/>

        <spring:message code="merchant.message.sameaddress"/>
    </td>

        <%--<form:errors path="sameAddressOffnHome" cssClass="error" />--%>


</tr>


    <%--  Added hidden fields--%>
<tr id="idtr5" style="display: none; ">
    <td class="extra1"><spring:message code="merchant.message.permanentaddress"/> : <sup
            style="color:#ff0000; ">*</sup></td>

    <td class="extra1"><form:input path="permAdd" cssClass="field" id="permAdd"/></td>
    <td></td>
</tr>


<tr id="idtr6" style="display: none; ">
    <td class="extra1"><spring:message code="merchant.message.state"/> : <sup
            style="color:#ff0000; ">*</sup>
    </td>
    <td class="extra1"><form:input path="permState" cssClass="field" id="permState"/></td>
    <td></td>
</tr>

<tr id="idtr7" style="display: none; ">
    <td class="extra1"><spring:message code="merchant.message.city"/> : <sup
            style="color:#ff0000; ">*</sup>
    </td>
    <td class="extra1"><form:input path="permCity" cssClass="field" id="permCity"/></td>
    <td></td>
</tr>

<tr id="idtr8" style="display: none; ">
    <td class="extra1"><spring:message code="merchant.message.pincode"/> : <sup
            style="color:#ff0000; ">*</sup></td>
    <td class="extra1"><form:input path="permZipCode" cssClass="field" id="permZipCode"/></td>
    <td></td>
</tr>

    <%--  Added hidden fields--%>


<tr id="idtr1" style="display: table-row; ">

    <td class="extra1"><spring:message code="merchant.message.permanentaddress"/> : <sup
            style="color:#ff0000; ">*</sup></td>
    <td class="extra1"><form:input path="permanentAddress" cssClass="field"
                                   onkeypress="return MyTrapEnter(this,event,'address');" tabindex="8"

                                   id="permanentAddress"
                                   maxlength='<%=webAppContext.getMessage("adress.line.length.max",null,"default-adress.line.length.max",null)%>'/></td>
    <td>
            <%--<form:errors path="permanentAddress" cssClass="error" />--%>
										<span class="error_color" id="permanentAddress_error">

									</span>
    </td>
</tr>


<tr id="idtr2" style="display: table-row; ">
    <td class="extra1"><spring:message code="merchant.message.state"/> : <sup
            style="color:#ff0000; ">*</sup>
    </td>
    <td class="extra1">
        <form:select path="permanentState" id="permanentState" size="1" cssClass="field" tabindex="9"
                     onchange="getCityListByStateId_Permanent()" cssStyle="width:93%;">
            <form:option value="-1"><spring:message code="common.message.selrequired"/>
            </form:option>
            <c:forEach items="${userModel.stateList}" var="state">
                <form:option value="${state.stateId}">${state.stateDescription}</form:option>
            </c:forEach>
        </form:select>
    </td>
    <td><span id="permanentState_error" class="error_color"></span></td>
</tr>


<tr id="idtr3" style="display: table-row; ">
    <td class="extra1"><spring:message code="merchant.message.city"/> : <sup
            style="color:#ff0000; ">*</sup>
    </td>
    <td class="extra1">
        <form:select path="permanentCity" id="permanentCity" size="1" cssClass="field" tabindex="10"
                     cssStyle="width:93%;">
            <form:option value="NONE"><spring:message code="common.message.selrequired"/>
            </form:option>
            <%--<c:forEach items="${userModel.cityList}" var="city">
                <form:option value="${city.description}">${city.description}</form:option>
            </c:forEach>--%>
        </form:select>
    </td>
    <td>
        <div id="loading2" style="display: none;"><img border='0'
                                                       src='<%=request.getContextPath()%>/resources/images/common/loading.gif'
                                                       width='20' height='20'></div>
        <span id="permanentCity_error" class="error_color"></span></td>
</tr>


<tr id="idtr4" style="display: table-row; ">
    <td class="extra1"><spring:message code="merchant.message.pincode"/> : <sup
            style="color:#ff0000; ">*</sup></td>
    <td class="extra1"><form:input path="permanentZipCode" cssClass="field"
                                   id="permanentZipCode" onkeypress="return MyTrapEnter(this,event,'numeric');"
                                   tabindex="11"
                                   maxlength='<%=webAppContext.getMessage("zipcode.length.max",null,"default-zipcode.length.max",null)%>'/></td>
    <td>

										<span class="error_color" id="permanentZipCode_error">

									</span>
    </td>
</tr>

<tr>
    <td><p></p></td>
</tr>


<tr align="center">
    <td width="30%">&nbsp;</td>
    <td align="left" width="20%">


        <div class="buttonwrapper"><a class="btn btn_rss" href="#" <%--onClick="doAdd();"--%> onclick="validateForm();"
                                      onkeypress="return MyTrapEnter(this,event,'');" tabindex="12">
            <span><spring:message code="common.message.create"/></span></a></div>


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


