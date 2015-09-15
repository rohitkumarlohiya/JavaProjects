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

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/tooltipster-master/css/tooltipster.css" />
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/tooltipster-master/js/jquery.tooltipster.min.js"></script>

<%--Setting for tooltip--%>


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

<script>

    $(document).ready(
            function () {

                $('#marketingName').tooltipster({
                    offsetX:0,
                    offsetY:25,
                    trigger:'click'
                });
            },
            function () {

                $('#legalBusinessName').tooltipster({
                    offsetX:0,
                    offsetY:25,
                    trigger:'click'
                });
            },
            function () {

                $('#businessPhoneNumber').tooltipster({
                    offsetX:0,
                    offsetY:25,
                    trigger:'click'
                });
            }
    );

</script>
<script type="text/javascript">



    function clearall() {
        ////alert("clearall()");
        document.getElementById("legalBusinessName_error").innerHTML = "";
        document.getElementById("marketingName_error").innerHTML = "";
        document.getElementById("ownershipType_error").innerHTML = "";
        document.getElementById("businessCategory_error").innerHTML = "";
        document.getElementById("businessType_error").innerHTML = "";
        document.getElementById("businessAddress_error").innerHTML = "";
        document.getElementById("stateBusinessAddr_error").innerHTML = "";
        document.getElementById("cityBusinessAddr_error").innerHTML = "";
        document.getElementById("zipCodeBusinessAddr_error").innerHTML = "";
        document.getElementById("businessPhoneNumber_error").innerHTML = "";
        document.getElementById("registeredOfficeAddress_error").innerHTML = "";
        document.getElementById("stateRegisterOfficeAddr_error").innerHTML = "";
        document.getElementById("cityRegisterOfficeAddr_error").innerHTML = "";
        document.getElementById("zipCodeRegisterOfficeAddrr_error").innerHTML = "";
        document.getElementById("registerOfficePhoneNumber_error").innerHTML = "";
        document.getElementById("emailBusiness_error").innerHTML = "";
        document.getElementById("panNumberBusiness_error").innerHTML = "";

    }

    function validateForm() {
        clearall();

        var flag = true;
        var legalBusinessName =  document.getElementById("legalBusinessName").value.replace(/^\s+|\s+$/g,"");
        var marketingName =  document.getElementById("marketingName").value.replace(/^\s+|\s+$/g,"");
        var businessAddress = document.getElementById("businessAddress").value.replace(/^\s+|\s+$/g,"");
        var registeredOfficeAddress = document.getElementById("registeredOfficeAddress").value.replace(/^\s+|\s+$/g,"");
        var panNumberBusiness = document.getElementById("panNumberBusiness").value.replace(/^\s+|\s+$/g,"");
        var zipCodeBusinessAddr = document.getElementById("zipCodeBusinessAddr").value.replace(/^\s+|\s+$/g,"");
        var zipCodeRegisterOfficeAddrr = document.getElementById("zipCodeRegisterOfficeAddrr").value.replace(/^\s+|\s+$/g,"");
        var businessPhoneNumber = document.getElementById("businessPhoneNumber").value.replace(/^\s+|\s+$/g,"");
        var registerOfficePhoneNumber = document.getElementById("registerOfficePhoneNumber").value.replace(/^\s+|\s+$/g,"");
        var emailBusiness = document.getElementById("emailBusiness").value.replace(/^\s+|\s+$/g,"");

        if ( legalBusinessName == "" || legalBusinessName  == null ) {
            document.getElementById("legalBusinessName_error").innerHTML = '<spring:message code="common.message.error.name"/>';
            return false;
        }

        flag = isCorrect(legalBusinessName, 'alpha', 'legalBusinessName_error', '<spring:message code='common.message.error.alphabets'/>');
        if(!flag)return false;
        flag = isMinMax(legalBusinessName, parseInt('<spring:message code="name.length.min"/>'), parseInt('<spring:message code="name.length.max"/>'), 'legalBusinessName_error', '<spring:message code="common.name.minmax.error"/>');
        if (!flag)return false;

        if ( marketingName == "" || marketingName == null) {
            document.getElementById("marketingName_error").innerHTML = '<spring:message code="common.message.error.name"/>';
            return false;
        }


        flag = isCorrect(marketingName, 'alpha', 'marketingName_error', '<spring:message code='common.message.error.alphabets'/>');
        if(!flag)return false;
        flag = isMinMax(marketingName, parseInt('<spring:message code="name.length.min"/>'), parseInt('<spring:message code="name.length.max"/>'), 'marketingName_error', '<spring:message code="common.name.minmax.error"/>');
        if (!flag)return false;

        if (document.getElementById("ownershipType").value < 1) {
            document.getElementById("ownershipType_error").innerHTML = '<spring:message code="common.message.error.ownership"/>';
            return false;
        }


        if (document.getElementById("businessCategory").value < 1) {
            document.getElementById("businessCategory_error").innerHTML = '<spring:message code="common.message.error.bussCategory"/>';
            return false;
        }

        if (document.getElementById("businessType").value < 1) {
            document.getElementById("businessType_error").innerHTML = '<spring:message code="common.message.error.bussType"/>';
            return false;
        }
        if (businessAddress == null || businessAddress == "") {
            document.getElementById("businessAddress_error").innerHTML = '<spring:message code="common.message.error.bussAdd"/>';
            return false;
        }

        flag = isCorrect(businessAddress, 'address', 'businessAddress_error', '<spring:message code='common.message.error.valid.address'/>');
        if(!flag)return false;
        flag = isMinMax(businessAddress, parseInt('<spring:message code="adress.line.length.min"/>'), parseInt('<spring:message code="adress.line.length.max"/>'), 'businessAddress_error', '<spring:message code="common.address.minmax.error"/>');
        if (!flag)return false;

        if (document.getElementById("stateBusinessAddr").value < 1) {
            document.getElementById("stateBusinessAddr_error").innerHTML = '<spring:message code="common.message.error.state"/>';
            return false;
        }


        if (document.getElementById("cityBusinessAddr").value == "NONE") {
            document.getElementById("cityBusinessAddr_error").innerHTML = '<spring:message code="common.message.error.city"/>';
            return false;
        }

        if (zipCodeBusinessAddr == "" || zipCodeBusinessAddr == null) {
            document.getElementById("zipCodeBusinessAddr_error").innerHTML = '<spring:message code="common.message.error.zip"/>';
            return false;
        }

            flag = isCorrect(zipCodeBusinessAddr, 'numeric', 'zipCodeBusinessAddr_error', '<spring:message code='common.message.error.numeric'/>');
        if(!flag)return false;
                flag = isMinMax(zipCodeBusinessAddr, parseInt('<spring:message code="zipcode.length.min"/>'), parseInt('<spring:message code="zipcode.length.max"/>'), 'zipCodeBusinessAddr_error', '<spring:message code="common.message.error.zip"/>');
        if(!flag)return false;



        if (businessPhoneNumber == "" || businessPhoneNumber == null) {
            document.getElementById("businessPhoneNumber_error").innerHTML = '<spring:message code="business.phone.help"/>';
            return false;
        }

            flag = isCorrect(businessPhoneNumber, 'numeric', 'businessPhoneNumber_error', '<spring:message code='common.message.error.numeric'/>');
        if(!flag)return false;
                flag = isMinMax(businessPhoneNumber, parseInt('<spring:message code="mobile.number.length.min"/>'), parseInt('<spring:message code="mobile.number.length"/>'), 'businessPhoneNumber_error', '<spring:message code="common.message.error.validnum"/>');
        if(!flag)return false;


        if (!document.getElementById("sameBusinessAddress").checked) {
            if (registeredOfficeAddress == "" || registeredOfficeAddress == null) {
                document.getElementById("registeredOfficeAddress_error").innerHTML = '<spring:message code="common.message.error.regAdd"/>';
                return false;
            }

            flag = isCorrect(registeredOfficeAddress, 'address', 'registeredOfficeAddress_error', '<spring:message code='common.message.error.valid.address'/>');
            if(!flag)return false;
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
            if(!flag)return false;
                    flag = isMinMax(zipCodeRegisterOfficeAddrr, parseInt('<spring:message code="zipcode.length.min"/>'), parseInt('<spring:message code="zipcode.length.max"/>'), 'zipCodeRegisterOfficeAddrr_error', '<spring:message code="common.message.error.zip"/>');
            if(!flag)return false;

            if (registerOfficePhoneNumber == "" || registerOfficePhoneNumber == null) {
                document.getElementById("registerOfficePhoneNumber_error").innerHTML = '<spring:message code="business.phone.help"/>';
                return false;
            }

                flag = isCorrect(registerOfficePhoneNumber, 'numeric', 'registerOfficePhoneNumber_error', '<spring:message code='common.message.error.numeric'/>');
            if(!flag)return false;
                    flag = isMinMax(registerOfficePhoneNumber, parseInt('<spring:message code="mobile.number.length.min"/>'), parseInt('<spring:message code="mobile.number.length"/>'), 'registerOfficePhoneNumber_error', '<spring:message code="common.message.error.validnum"/>');
            if(!flag)return false;
        }

        //alert("dsgasdg");

        if (emailBusiness == null || emailBusiness == "") {
            document.getElementById("emailBusiness_error").innerHTML = '<spring:message code="common.message.error.email"/>';
            return false;
        }

            flag = isCorrect(emailBusiness, 'email', 'emailBusiness_error', '<spring:message code="common.message.error.email"/>');
        if(!flag)return false;


        if (panNumberBusiness == "" || panNumberBusiness == null) {

            document.getElementById("panNumberBusiness_error").innerHTML = '<spring:message code="common.message.error.panNum"/>';
            return false;
        }


            flag = isCorrect(panNumberBusiness, 'Alphanumeric', 'panNumberBusiness_error', '<spring:message code='common.message.error.alphanumeric'/>');
        if(!flag)return false;
                flag = isMinMax(panNumberBusiness, parseInt('<spring:message code="pancard.length.min"/>'), parseInt('<spring:message code="pancard.length.max"/>'), 'panNumberBusiness_error', '<spring:message code="common.message.error.panNum"/>');
        if(!flag)return false;


            beforeSubmit('myform');
            document.forms[0].action = "merchantBusinessInfoAdd.do";
            document.forms[0].method = "post";
            //alert("submitting")
            document.forms[0].submit();

    }

    function updateFields(This) {
        var next = This.tabIndex;
        var legalBusinessName =  document.getElementById("legalBusinessName").value.replace(/^\s+|\s+$/g,"");
        var marketingName =  document.getElementById("marketingName").value.replace(/^\s+|\s+$/g,"");
        var businessAddress = document.getElementById("businessAddress").value.replace(/^\s+|\s+$/g,"");
        var registeredOfficeAddress = document.getElementById("registeredOfficeAddress").value.replace(/^\s+|\s+$/g,"");
        var panNumberBusiness = document.getElementById("panNumberBusiness").value.replace(/^\s+|\s+$/g,"");
        var zipCodeBusinessAddr = document.getElementById("zipCodeBusinessAddr").value.replace(/^\s+|\s+$/g,"");
        var zipCodeRegisterOfficeAddrr = document.getElementById("zipCodeRegisterOfficeAddrr").value.replace(/^\s+|\s+$/g,"");
        var businessPhoneNumber = document.getElementById("businessPhoneNumber").value.replace(/^\s+|\s+$/g,"");
        var registerOfficePhoneNumber = document.getElementById("registerOfficePhoneNumber").value.replace(/^\s+|\s+$/g,"");
        var emailBusiness = document.getElementById("emailBusiness").value.replace(/^\s+|\s+$/g,"");

        if (next == 6 && document.getElementById("sameBusinessAddress").checked) {

            document.getElementById("regisAdd").value = businessAddress;
        }
        else if (next == 9 && document.getElementById("sameBusinessAddress").checked) {

            document.getElementById("regisZipCode").value = zipCodeBusinessAddr;
        }

        else if (next == 10 && document.getElementById("sameBusinessAddress").checked) {

            document.getElementById("regisPhoneNumber").value = businessPhoneNumber;
        }
        else if (next == 7 && document.getElementById("sameBusinessAddress").checked) {

            document.getElementById("regisState").value = document.getElementById("stateBusinessAddr").options[document.getElementById("stateBusinessAddr").selectedIndex].text;
            document.getElementById("regisCity").value = document.getElementById("cityBusinessAddr").options[0].text;
        }
        else if (next == 8 && document.getElementById("sameBusinessAddress").checked) {
            document.getElementById("regisCity").value = document.getElementById("cityBusinessAddr").options[document.getElementById("cityBusinessAddr").selectedIndex].text;
        }
        else {
            return true;
        }

        return true;
    }


    function copy() {

        if (document.getElementById("sameBusinessAddress").checked) {
            // alert("checked");


            var stateBusinessAddr = document.getElementById("stateBusinessAddr");
            var cityBusinessAddr = document.getElementById("cityBusinessAddr");
            var legalBusinessName =  document.getElementById("legalBusinessName").value.replace(/^\s+|\s+$/g,"");
            var marketingName =  document.getElementById("marketingName").value.replace(/^\s+|\s+$/g,"");
            var businessAddress = document.getElementById("businessAddress").value.replace(/^\s+|\s+$/g,"");
            var registeredOfficeAddress = document.getElementById("registeredOfficeAddress").value.replace(/^\s+|\s+$/g,"");
            var panNumberBusiness = document.getElementById("panNumberBusiness").value.replace(/^\s+|\s+$/g,"");
            var zipCodeBusinessAddr = document.getElementById("zipCodeBusinessAddr").value.replace(/^\s+|\s+$/g,"");
            var zipCodeRegisterOfficeAddrr = document.getElementById("zipCodeRegisterOfficeAddrr").value.replace(/^\s+|\s+$/g,"");
            var businessPhoneNumber = document.getElementById("businessPhoneNumber").value.replace(/^\s+|\s+$/g,"");
            var registerOfficePhoneNumber = document.getElementById("registerOfficePhoneNumber").value.replace(/^\s+|\s+$/g,"");
            var emailBusiness = document.getElementById("emailBusiness").value.replace(/^\s+|\s+$/g,"");


            if (businessAddress != null && businessAddress.length > 0 && stateBusinessAddr.value > -1 && cityBusinessAddr.value != "NONE" && zipCodeBusinessAddr.length >= parseInt('<spring:message code="zipcode.length.min"/>') && zipCodeBusinessAddr != null && businessPhoneNumber.length >= parseInt('<spring:message code="mobile.number.length.min"/>') && businessPhoneNumber != null) {
                clearall();
                document.getElementById("regisAdd").value = businessAddress;
                document.getElementById("regisAdd").readOnly = true;

                document.getElementById("regisState").value = stateBusinessAddr.options[stateBusinessAddr.selectedIndex].text;
                document.getElementById("regisState").readOnly = true;

                document.getElementById("regisCity").value = cityBusinessAddr.options[cityBusinessAddr.selectedIndex].text;
                document.getElementById("regisCity").readOnly = true;

                document.getElementById("regisZipCode").value = zipCodeBusinessAddr;
                document.getElementById("regisZipCode").readOnly = true;

                document.getElementById("regisPhoneNumber").value = businessPhoneNumber;
                document.getElementById("regisPhoneNumber").readOnly = true;

                document.getElementById("idtr1").style.display = 'none';
                document.getElementById("idtr2").style.display = 'none';
                document.getElementById("idtr3").style.display = 'none';
                document.getElementById("idtr4").style.display = 'none';
                document.getElementById("idtr5").style.display = 'none';


                document.getElementById("idtr6").style.display = 'table-row';
                document.getElementById("idtr7").style.display = 'table-row';
                document.getElementById("idtr8").style.display = 'table-row';
                document.getElementById("idtr9").style.display = 'table-row';
                document.getElementById("idtr10").style.display = 'table-row';

            }
            else {
                clearall();

                if (businessAddress == null || businessAddress == "") {
                    document.getElementById("businessAddress_error").innerHTML = '<spring:message code="common.message.error.bussAdd"/>';
                }

                isCorrect(businessAddress, 'address', 'businessAddress_error', '<spring:message code='common.message.error.valid.address'/>');
                isMinMax(businessAddress, parseInt('<spring:message code="adress.line.length.min"/>'), parseInt('<spring:message code="adress.line.length.max"/>'), 'businessAddress_error', '<spring:message code="common.address.minmax.error"/>');


                if (document.getElementById("stateBusinessAddr").value < 1) {
                    document.getElementById("stateBusinessAddr_error").innerHTML = '<spring:message code="common.message.error.state"/>';
                }


                if (document.getElementById("cityBusinessAddr").value == "NONE") {
                    document.getElementById("cityBusinessAddr_error").innerHTML = '<spring:message code="common.message.error.city"/>';
                }

                if (zipCodeBusinessAddr == "" || zipCodeBusinessAddr == null) {
                    document.getElementById("zipCodeBusinessAddr_error").innerHTML = '<spring:message code="common.message.error.zip"/>';
                }
                isCorrect(zipCodeBusinessAddr, 'numeric', 'zipCodeBusinessAddr_error', '<spring:message code='common.message.error.numeric'/>');
                isMinMax(zipCodeBusinessAddr, parseInt('<spring:message code="zipcode.length.min"/>'), parseInt('<spring:message code="zipcode.length.max"/>'), 'zipCodeBusinessAddr_error', '<spring:message code="common.message.error.zip"/>');

                if (businessPhoneNumber == "" || businessPhoneNumber == null) {
                    document.getElementById("businessPhoneNumber_error").innerHTML = '<spring:message code="business.phone.help"/>';
                }
              isCorrect(businessPhoneNumber, 'numeric', 'businessPhoneNumber_error', '<spring:message code='common.message.error.numeric'/>');
              isMinMax(businessPhoneNumber, parseInt('<spring:message code="mobile.number.length.min"/>'), parseInt('<spring:message code="mobile.number.length"/>'), 'businessPhoneNumber_error', '<spring:message code="common.message.error.validnum"/>');

                document.getElementById("sameBusinessAddress").checked = false;
            }
        }
        else  //if unchecked
        {
            //alert ("unchecked");


            document.getElementById("idtr6").style.display = 'none';
            document.getElementById("idtr7").style.display = 'none';
            document.getElementById("idtr8").style.display = 'none';
            document.getElementById("idtr9").style.display = 'none';
            document.getElementById("idtr10").style.display = 'none';

            document.getElementById("regisAdd").readOnly = false;
            document.getElementById("regisState").readOnly = false;
            document.getElementById("regisCity").readOnly = false;
            document.getElementById("regisZipCode").readOnly = false;
            document.getElementById("regisPhoneNumber").readOnly = false;

            document.getElementById("idtr1").style.display = 'table-row';
            document.getElementById("idtr2").style.display = 'table-row';
            document.getElementById("idtr3").style.display = 'table-row';
            document.getElementById("idtr4").style.display = 'table-row';
            document.getElementById("idtr5").style.display = 'table-row';
            clearall();
        }
    }



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

    var stateId = document.getElementById('stateBusinessAddr').value;
    if (stateId != -1) {
        document.getElementById('loading1').style.display = 'table-cell';
        http.open('get', 'getStateList/' + stateId);
        http.onreadystatechange = processSrcResponseCity;
        http.send(null);
    }
    else
    {
        var x = document.getElementById('cityBusinessAddr');
        for (i = x.options.length - 1; i >= 0; i--)
        {
            x.remove(i);
        }
        addOption(x,"--- Select ---","NONE");
        x.selectedIndex = 0;
    }
}

function processSrcResponseCity() {
    var selectIndex = -1;
    if (http.readyState == 4) {
        if (http.status == 200) {


            response1 = http.responseXML.getElementsByTagName("result")[0];
            var result = response1.childNodes[0].nodeValue.split("####");
            var x = document.getElementById('cityBusinessAddr');


            // alert("x.selectedIndex before = " + x.selectedIndex);

            for (i = x.options.length - 1; i > 0; i--) {
                x.remove(i);
            }

            //alert("x.selectedIndex after = " + x.selectedIndex);

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

            //alert("x.selectedIndex = " + x.selectedIndex);

            x.selectedIndex = 0;

            document.getElementById('loading1').style.display = 'none';

        }
    }
}

function getCityListByStateId_Permanent() {

    var stateId = document.getElementById('stateRegisterOfficeAddr').value;
    if (stateId != -1) {
        document.getElementById('loading2').style.display = 'table-cell';
        http.open('get', 'getStateList/' + stateId);
        http.onreadystatechange = processSrcResponseCity_Permanent;
        http.send(null);
    }
    else
    {
        var x = document.getElementById('cityRegisterOfficeAddr');
        for (i = x.options.length - 1; i >= 0; i--)
        {
            x.remove(i);
        }
        addOption(x,"--- Select ---","NONE");
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
            document.getElementById('loading2').style.display = 'none';
        }
    }
}


</script>
</head>

<body class="withvernav" onload="setFocus(document.getElementById('legalBusinessName'));">

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
    <h1 class="pagetitle"><spring:message code="merchant.message.businessinfo.add"/>
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


<form:form method="POST" commandName="businessInfoModel" id="myform"
           name="myform" autocomplete="off" onCopy="return false" onPaste="return false">
<table>
    <tr>
        <td colspan="3">
                                         <span class="result_color" align="left">
                                    <%
                                        if (session.getAttribute("sequence_info_msg") != null) {
                                    %>
                                    <%--<%=session.getAttribute("sequence_info_msg")%>--%>
                                             <script>
                                                 alert('<%=session.getAttribute("sequence_info_msg")%>');
                                                 setFocus(document.getElementById('legalBusinessName'));
                                             </script>

                                    <%
                                        }
                                        session.removeAttribute("sequence_info_msg");

                                    %>

                                         </span>
        </td>
    </tr>
</table>

<table border="0" width="85%">
<tr>
    <td><p></p></td>
</tr>
<tr>
    <td class="extra1" width="30%"><spring:message code="merchant.message.legalbusinessname"/> : <sup
            style="color:#ff0000; ">*</sup>
    </td>
    <td class="extra1" width="20%"><form:input path="legalBusinessName" cssClass="field" id="legalBusinessName"
                                               onkeypress="return MyTrapEnter(this,event,'alpha');" tabindex="1"
                                               maxlength='<%=webAppContext.getMessage("name.length.max",null,"default-name.length.max",null)%>'
                                               title='<%=webAppContext.getMessage("legal.business.help",null,"default-legal.business.help",null)%>'/>
    </td>
    <td width="50%"><span class="error_color" id="legalBusinessName_error"></span>


    </td>
</tr>

<tr>
    <td class="extra1"><spring:message code="merchant.message.marketingname"/> : <sup
            style="color:#ff0000; ">*</sup>
    </td>
    <td class="extra1"><form:input path="marketingName" cssClass="field" id="marketingName"
                                   title='<%=webAppContext.getMessage("merchant.message.nameonreciept",null,"default-merchant.message.nameonreciept",null)%>'
                                   onkeypress="return MyTrapEnter(this,event,'alpha');" tabindex="2"
                                   maxlength='<%=webAppContext.getMessage("name.length.max",null,"default-name.length.max",null)%>'/>
    </td>
    <td><span class="error_color" id="marketingName_error"></span>
    </td>
</tr>


<tr>
    <td class="extra1"><spring:message code="merchant.message.ownershiptype"/> : <sup
            style="color:#ff0000; ">*</sup>
    </td>

    <td class="extra1">
        <form:select path="ownershipType" size="1" cssClass="field" id="ownershipType" tabindex="3"
                     cssStyle="width:93%;">
            <form:option value="-1" label="--- Select ---"/>
            <c:forEach items="${businessInfoModel.businessOwnerTypeList}" var="businessOwnerType">
                <form:option value="${businessOwnerType.businessOwnerTypeId}">${businessOwnerType.businessOwnerTypeDescription}</form:option>
            </c:forEach>
        </form:select>
    </td>
    <td><span class="error_color" id="ownershipType_error"></span>
    </td>
</tr>

<tr>
    <td class="extra1"><spring:message code="merchant.message.businesscategory"/> : <sup
            style="color:#ff0000; ">*</sup>
    </td>
    <td class="extra1">
        <form:select path="businessCategory" size="1" cssClass="field" id="businessCategory" tabindex="4"
                     cssStyle="width:93%;">
            <form:option value="-1" label="--- Select ---"/>
        <c:forEach items="${businessInfoModel.businessCategoryList}" var="businessCategory">
            <form:option value="${businessCategory.businessCategoryId}">${businessCategory.businessCategoryDescription}</form:option>
        </c:forEach>
            <%--<form:options items="${businessInfoModel.businessCategoryList}" />--%>
        </form:select>
    <td><span class="error_color" id="businessCategory_error"></span>
    </td>
</tr>


<tr>
    <td class="extra1"><spring:message code="merchant.message.businesstype"/> : <sup
            style="color:#ff0000; ">*</sup>
    </td>
    <td class="extra1"><form:select path="businessType" cssClass="field" id="businessType" tabindex="5"
                                    cssStyle="width:93%;">
        <form:option value="-1" label="--- Select ---"/>
        <c:forEach items="${businessInfoModel.businessTypeList}" var="businessType">
            <form:option value="${businessType.businessTypeId}">${businessType.businessTypeDescription}</form:option>
        </c:forEach>
        <%-- <form:options items="${businessInfoModel.businessTypeList}" />--%>
    </form:select></td>
    <td><span class="error_color" id="businessType_error"></span>
    </td>
</tr>

<tr>
    <td class="extra1"><spring:message code="merchant.message.business.address"/> : <sup
            style="color:#ff0000; ">*</sup>
    </td>
    <td class="extra1"><form:input path="businessAddress" cssClass="field" id="businessAddress"
                                   onkeypress="return MyTrapEnter(this,event,'address');" tabindex="6"
                                   maxlength='<%=webAppContext.getMessage("adress.line.length.max",null,"default-adress.line.length.max",null)%>'
                                   onkeyup="updateFields(this);"/>
    </td>
    <td><span class="error_color" id="businessAddress_error"></span>
    </td>
</tr>

<tr>
    <td class="extra1"><spring:message code="merchant.message.state"/> : <sup
            style="color:#ff0000; ">*</sup>
    </td>
    <td class="extra1"><form:select path="stateBusinessAddr" cssClass="field" id="stateBusinessAddr" tabindex="7"
                                    onchange="getCityListByStateId();updateFields(this);" cssStyle="width:93%;">
        <form:option value="-1" label="--- Select ---"/>
        <c:forEach items="${businessInfoModel.stateList}" var="state">
            <form:option value="${state.stateId}">${state.stateDescription}</form:option>
        </c:forEach>
        <%--<form:options items="${businessInfoModel.stateList}" />--%>
    </form:select></td>
    <td><span class="error_color" id="stateBusinessAddr_error"></span>
    </td>
</tr>

<tr>
    <td class="extra1"><spring:message code="merchant.message.city"/> : <sup
            style="color:#ff0000; ">*</sup>
    </td>
    <td class="extra1"><form:select path="cityBusinessAddr" cssClass="field" id="cityBusinessAddr" tabindex="8"
                                    onchange="updateFields(this);" cssStyle="width:93%;">
        <form:option value="NONE" label="--- Select ---"/>
        <c:forEach items="${businessInfoModel.cityList}" var="city">
            <form:option value="${city.cityDescription}">${city.cityDescription}</form:option>
        </c:forEach>
        <%--<form:options items="${businessInfoModel.cityList}" />--%>
    </form:select>

    </td>
    <td>
        <div id="loading1" style="display: none;"><img border='0' src='<%=request.getContextPath()%>/resources/images/common/loading.gif' width='20'
                                                       height='20'></div>
        <span class="error_color" id="cityBusinessAddr_error"></span>
    </td>
</tr>

<tr>
    <td class="extra1"><spring:message code="merchant.message.pincode"/> : <sup
            style="color:#ff0000; ">*</sup>
    </td>
    <td class="extra1"><form:input path="zipCodeBusinessAddr" cssClass="field" id="zipCodeBusinessAddr"
                                   onkeypress="return MyTrapEnter(this,event,'numeric');" tabindex="9" onkeyup="updateFields(this);"
                                   maxlength='<%=webAppContext.getMessage("zipcode.length.max",null,"default-zipcode.length.max",null)%>'/>
    </td>
    <td><span class="error_color" id="zipCodeBusinessAddr_error"></span>
    </td>
</tr>

<tr>
    <td class="extra1"><spring:message code="merchant.message.businessphonenumber"/> : <sup
            style="color:#ff0000; ">*</sup>
    </td>
    <td class="extra1"><form:input path="businessPhoneNumber" cssClass="field" id="businessPhoneNumber"
                                   title='<%=webAppContext.getMessage("business.contact.help",null,"default-business.contact.help",null)%>'
                                   onkeypress="return MyTrapEnter(this,event,'numeric');" tabindex="10" onkeyup="updateFields(this);"
                                   maxlength='<%=webAppContext.getMessage("mobile.number.length",null,"default-mobile.number.length",null)%>'/>
    </td>
    <td><span class="error_color" id="businessPhoneNumber_error"></span>
    </td>
</tr>

<tr>
    <td colspan="2" class="extra1"><form:checkbox path="sameBusinessAddress" id="sameBusinessAddress"
                                                  onclick="copy();"/>
        <spring:message code="merchant.message.businessaddresssameasregistered"/>
    </td>

</tr>


    <%--  Added hidden fields--%>
<tr id="idtr6" style="display: none; ">
    <td class="extra1"><spring:message code="merchant.message.rigisteredofficeaddress"/> : <sup
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
    <td class="extra1"><spring:message code="merchant.message.registeredphonenumber"/> : <sup
            style="color:#ff0000; ">*</sup></td>
    <td class="extra1"><form:input path="regisPhoneNumber" cssClass="field" id="regisPhoneNumber"/></td>
    <td></td>
</tr>

    <%--  Added hidden fields--%>


<tr id="idtr1" style="display: table-row; ">
    <td class="extra1"><spring:message code="merchant.message.rigisteredofficeaddress"/> : <sup
            style="color:#ff0000; ">*</sup>
    </td>
    <td class="extra1"><form:input path="registeredOfficeAddress" cssClass="field" id="registeredOfficeAddress"
                                   onkeypress="return MyTrapEnter(this,event,'address');" tabindex="11"
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
                                    tabindex="12" onchange="getCityListByStateId_Permanent()" cssStyle="width:93%;">
        <form:option value="-1" label="--- Select ---"/>
        <c:forEach items="${businessInfoModel.stateList}" var="state">
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
                                    tabindex="13" cssStyle="width:93%;">
        <form:option value="NONE" label="--- Select ---"/>
        <c:forEach items="${businessInfoModel.cityList}" var="city">
            <form:option value="${city.cityDescription}">${city.cityDescription}</form:option>
        </c:forEach>
    </form:select></td>
    <td>
        <div id="loading2" style="display: none;"><img border='0' src='<%=request.getContextPath()%>/resources/images/common/loading.gif' width='20'
                                                       height='20'></div>
        <span class="error_color" id="cityRegisterOfficeAddr_error"></span>
    </td>
</tr>

<tr id="idtr4" style="display: table-row; ">
    <td class="extra1"><spring:message code="merchant.message.pincode"/> : <sup
            style="color:#ff0000; ">*</sup>
    </td>
    <td class="extra1"><form:input path="zipCodeRegisterOfficeAddrr" cssClass="field" id="zipCodeRegisterOfficeAddrr"
                                   onkeypress="return MyTrapEnter(this,event,'numeric');" tabindex="14"
                                   maxlength='<%=webAppContext.getMessage("zipcode.length.max",null,"default-zipcode.length.max",null)%>'/>
    </td>
    <td><span class="error_color" id="zipCodeRegisterOfficeAddrr_error"></span>
    </td>
</tr>

<tr id="idtr5" style="display: table-row; ">
    <td class="extra1"><spring:message code="merchant.message.registeredphonenumber"/> : <sup
            style="color:#ff0000; ">*</sup>
    </td>
    <td class="extra1"><form:input path="registerOfficePhoneNumber" cssClass="field" id="registerOfficePhoneNumber"
                                   onkeypress="return MyTrapEnter(this,event,'numeric');" tabindex="15"
                                   maxlength='<%=webAppContext.getMessage("mobile.number.length",null,"default-mobile.number.length",null)%>'/>
    </td>
    <td><span class="error_color" id="registerOfficePhoneNumber_error"></span>
    </td>
</tr>


<tr>
    <td class="extra1"><spring:message code="merchant.message.emailaddress"/> : <sup
            style="color:#ff0000; ">*</sup>
    </td>
    <td class="extra1"><form:input path="emailBusiness" cssClass="field" id="emailBusiness" readonly="true"
                                   onkeypress="return MyTrapEnter(this,event,'email');" tabindex="16"
                                   maxlength='<%=webAppContext.getMessage("email.length",null,"default-email.length",null)%>'/>
    </td>
    <td><span class="error_color" id="emailBusiness_error"></span>
    </td>
</tr>

<tr>
    <td class="extra1"><spring:message code="merchant.message.pannumber"/> : <sup
            style="color:#ff0000; ">*</sup>
    </td>
    <td class="extra1"><form:input path="panNumberBusiness" cssClass="field" id="panNumberBusiness"
                                   onkeypress="return MyTrapEnter(this,event,'Alphanumeric');" tabindex="17"
                                   maxlength='<%=webAppContext.getMessage("pancard.length.max",null,"default-pancard.length.max",null)%>'/>
    </td>
    <td><span class="error_color" id="panNumberBusiness_error"></span>
    </td>
</tr>


<tr>
    <td><p></p></td>
</tr>

<tr align="center">
    <td width="30%">&nbsp;</td>
    <td align="left" width="20%">


        <div class="buttonwrapper"><a class="btn btn_rss" href="#" <%--onClick="doAdd();"--%> onclick="validateForm();"
                                      onkeypress="return MyTrapEnter(this,event,'');" tabindex="18">
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


