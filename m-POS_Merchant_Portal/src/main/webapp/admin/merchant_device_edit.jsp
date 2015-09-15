<%@ page import="com.estel.mbanking.webcommon.util.filereader.PropertyFileReader" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<%
    String filepath = (String) session.getAttribute("filepath");
    PropertyFileReader prop = new PropertyFileReader(filepath);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <title><%=prop.getProperty("common.message.title")%>
    </title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" href="css/style.default.css" type="text/css"/>
    <link rel="stylesheet" href="stylesheet/style.css" type="text/css"/>
    <script type="text/javascript" src="js/plugins/jquery-1.7.min.js"></script>
    <script type="text/javascript" src="js/plugins/jquery-ui-1.8.16.custom.min.js"></script>
    <script type="text/javascript" src="js/plugins/jquery.cookie.js"></script>
    <script type="text/javascript" src="js/plugins/jquery.uniform.min.js"></script>
    <script type="text/javascript" src="js/plugins/jquery.validate.min.js"></script>
    <script type="text/javascript" src="js/plugins/jquery.tagsinput.min.js"></script>
    <script type="text/javascript" src="js/plugins/charCount.js"></script>
    <script type="text/javascript" src="js/plugins/ui.spinner.min.js"></script>
    <script type="text/javascript" src="js/custom/general.js"></script>
    <script type="text/javascript" src="js/custom/forms.js"></script>
    <script type="text/javascript" src="includes/js/validations.js"></script>
    <%--<script type="text/javascript" src="includes/js/validations_client.js"></script>--%>
    <%--<script type="text/javascript" src="includes/js/vendor.js"></script>--%>


    <!--[if IE 9]>
    <link rel="stylesheet" media="screen" href="css/style.ie9.css"/>
    <![endif]-->
    <!--[if IE 8]>
    <link rel="stylesheet" media="screen" href="css/style.ie8.css"/>
    <![endif]-->
    <!--[if lt IE 9]>
    <script src="css/css3-mediaqueries.js"></script>
    <![endif]-->

    <script type="text/javascript">

        function doUpdate()
        {
            document.forms[0].pageAction.value = "update";
            // document.forms[0].bankId.value = id ;
            document.forms[0].action = "deviceEdit.do";
            document.forms[0].method = "post";
            document.forms[0].submit();
        }

        function doBack()
        {
            window.location.href = "deviceList.do";
        }

        function validateform()
        {
            var flag = true
            var devicenum = document.getElementById("deviceNumber").value;
            if(devicenum == "" || isNaN(devicenum) || devicenum.length < 10){
                //alert("devicenum.length    "+devicenum.length);
                document.getElementById("deviceNumber_err").innerHTML = '<%=prop.getProperty("common.message.error.validnum")%>';
                flag = false;
            }
            if(flag)
            {      alert("flag is true");
                doUpdate();
            }
        }
       function trapEnter(This, evt) {



    var keycode;
    alert("evt  ==>" + evt);
    var next = This.tabIndex;
    //alert("next  ==>" + next);
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
        //alert("enter pressed");
       validateform();
        return false;
    }
    if (keycode == 9) {
        //alert("tab pressed");
        if (next < document.getElementById("myform").length)
        {
           // document.getElementById("myform").elements[next].focus();
        }
    }
    else
        return true;
}


function isNumber(This,evt) {
    trapEnter(This,evt);
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}

    </script>
</head>

<body  class="withvernav" onload="setFocus(document.getElementById('deviceNumber'))">

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
    <h1 class="pagetitle"><%=prop.getProperty("merchant.message.device.edit")%>
    </h1>
    <span class="pagedesc"></span>

    <ul class="hornav">

    </ul>
</div>
<!--pageheader-->

<div id="contentwrapper" class="contentwrapper" style="min-height:400px;">

<div id="basicform" class="subcontent">

<form:form commandName="deviceModel" autocomplete="off" onCopy="return false" onPaste="return false">
    <input type="hidden" name="pageAction" value=""/>
    <input type="hidden" name="deviceId" value="${deviceModel.deviceId}"/>

    <br>
    <table>
        <tr>
            <td colspan="3">
                                         <span class="result_color" align="left">
                                    <%
                                        if (session.getAttribute("edit_device_err_msg") != null) {
                                    %>
                                    <%=session.getAttribute("edit_device_err_msg")%>
                                    <%}
                                        session.removeAttribute("edit_device_err_msg");

                                    %>   </span>
            </td>
        </tr>
    </table>

    <table>
        <tr>
            <td>
                <ul class="buttonlist">
                    <li>
                        <a href="#" onclick="doBack();"
                           class="btn-1 btn_back"><span><%=prop.getProperty("common.message.back")%></span></a>
                    </li>
                </ul>
            </td>
        </tr>
    </table>


    <!--table 1 start here -->
    <table width="100%" height="40%" cellpadding=0 cellspacing=0>
        <tr>
            <td>
                <!--table 2 start here -->
                <table border="0" width="70%">

                    <tr>
                        <td align="left" width="30%" class="extra1"><%=prop.getProperty("merchant.message.device.number")%> : <sup
                                style="color:#ff0000; ">*</sup>
                        </td>

                        <td align="left" class="extra" width="20%">
                            <form:input path="deviceNumber" cssClass="smallinput" id='deviceNumber' tabindex="1"
                                        cssStyle="" maxlength="50"
                                        onkeypress="return isNumber(this,event);"/>
                        </td>
                        <td align="left" width="50%"><span class="error_color" id="deviceNumber_err" align="left"></span></td>
                    <%--<tr>
                        <td align="left" width="30%"><%=prop.getProperty("common.message.status")%>
                            :<sup style="color:#ff0000; ">*</sup>
                        <td width="20%">
                            <form:select path="deviceStatus" cssClass="field" id="deviceStatus" tabindex="3" onkeypress="return trapAdd(event);">
                                &lt;%&ndash;                                        <form:option value="${bankModel.account_type}">${bankModel.account_type}
                                </form:option>&ndash;%&gt;
                                <c:forEach items="${deviceModel.deviceStatusList}" var="deviceStatus">
                                    <form:option value="${deviceStatus.id}">${deviceStatus.description}</form:option>
                                </c:forEach>
                            </form:select>
                        </td>
                        <td width="50%" align="left">&lt;%&ndash;<span id="account_type_err" align="left"></span>&ndash;%&gt;
                        </td>
                    </tr>--%>


                    <tr>
                        <td align="left" width="30%" class="extra1"><%=prop.getProperty("merchant.message.enterpin")%> : <sup
                                style="color:#ff0000; ">*</sup>
                        </td>

                        <td align="left" class="extra" width="20%">
                            <form:password path="pin" cssClass="password" id='pin' tabindex="2"
                                           cssStyle="" maxlength="4"
                                           onkeypress="return isNumber(this,event);"/>
                        </td>

                        <td align="left" width="50%"><span id="pin_err" class="error_color" align="left"></span></td>
                    </tr>

                    <tr>
                        <td colspan="2"><p></p></td>

                    </tr>
                     <%--status =   ${deviceModel.deviceStatus}--%>

                     <c:if test="${deviceModel.deviceStatus == 2}">

                         <tr align="center">
                             <td width="30%">&nbsp;</td>
                             <td align="left" width="20%">


                                 <div class="buttonwrapper">
                                     <a class="btn btn_bulb" href="#" onkeypress="return trapEnter(this,event);"
                                        onClick="validateform();" tabindex="3"><span><%=prop.getProperty("common.message.activate")%></span></a>
                                 </div>


                             </td>
                             <td width="50%">&nbsp;</td>
                         </tr>

                      </c:if>


                    <c:if test="${deviceModel.deviceStatus == 1}">
                        <tr align="center">
                            <td width="30%">&nbsp;</td>
                            <td align="left" width="20%">


                                <div class="buttonwrapper">
                                    <a class="btn btn_bulb" href="#"  onkeypress="return trapEnter(this,event);"
                                       onClick="validateform();" tabindex="3"><span><%=prop.getProperty("common.message.deactivate")%></span></a>
                                </div>


                            </td>
                            <td width="50%">&nbsp;</td>
                        </tr>
                    </c:if>





                    <%--<tr align="center">
                        <td width="30%">&nbsp;</td>
                        <td align="left" width="20%">


                            <div class="buttonwrapper">
                                <a class="btn btn_bulb" href="#"
                                   onClick="validateform();" tabindex="3"><span><%=prop.getProperty("common.message.update")%></span></a>
                            </div>


                        </td>
                        <td width="50%">&nbsp;</td>
                    </tr>
--%>


                </table>

            </td>

        </tr>
        <!--table 2 end  here -->

    </table>
</form:form>


<%--<form:form commandName="bankModel">

    <br>
    <table>
        <tr>
            <td>
                <ul class="buttonlist">
                    <li>
                        <a href="#" onclick="doBack();"
                           class="btn-1 btn_back"><span><%=prop.getProperty("common.message.back")%></span></a>
                    </li>
                </ul>
            </td>
        </tr>
    </table>


    <!--table 1 start here -->
    <table width="100%" height="40%" cellpadding=0 cellspacing=0>
        <tr>
            <td>
                <!--table 2 start here -->
                <table border="0" width="100%">

                    <tr>
                        <td align="left" width="30%"><%=prop.getProperty("merchant.message.accountname")%> : <sup
                                style="color:#ff0000; ">*</sup>
                        </td>

                        <td align="left" class="extra" width="20%">
                            <form:input path="account_name" cssClass="smallinput" id='account_name' tabindex="1"
                                        cssStyle="" maxlength="50"
                                        onkeypress="javascript:if(checkforchar(event,'name_err')){return trapAdd(event)}else {return false;}"/>
                        </td>

                        <td align="left" width="50%"><span id="account_name_err" align="left"></span></td>
                    </tr>



                    <tr>
                        <td align="left" width="30%"><%=prop.getProperty("merchant.message.accounttype")%>
                            :<sup style="color:#ff0000; ">*</sup>
                        <td width="20%">
                            <form:select path="account_type" cssClass="field" id="account_type" tabindex="3" onkeypress="return trapAdd(event);">
                                <form:option value="-1"><%=prop.getProperty("common.message.selrequired")%>
                                </form:option>
                                <c:forEach items="${accountTypeList}" var="accountTypeList">
                                    <form:option value="${accountTypeList.id}">${accountTypeList.description}</form:option>
                                </c:forEach>
                            </form:select>
                        </td>
                        <td width="50%" align="left"><span id="account_type_err" align="left"></span>
                        </td>
                    </tr>


                    <tr>
                        <td align="left" width="30%"><%=prop.getProperty("merchant.message.bankname")%> : <sup
                                style="color:#ff0000; ">*</sup>
                        </td>

                        <td align="left" class="extra" width="20%">
                            <form:input path="bank_name" cssClass="smallinput" id='bank_name' tabindex="1"
                                        cssStyle="" maxlength="50"
                                        onkeypress="javascript:if(checkforchar(event,'name_err')){return trapAdd(event)}else {return false;}"/>
                        </td>

                        <td align="left" width="50%"><span id="bank_name_err" align="left"></span></td>
                    </tr>

                    <tr>
                        <td align="left" width="30%"><%=prop.getProperty("merchant.message.branchname")%> : <sup
                                style="color:#ff0000; ">*</sup>
                        </td>

                        <td align="left" class="extra" width="20%">
                            <form:input path="branch_name" cssClass="smallinput" id='branch_name' tabindex="1"
                                        cssStyle="" maxlength="50"
                                        onkeypress="javascript:if(checkforchar(event,'name_err')){return trapAdd(event)}else {return false;}"/>
                        </td>

                        <td align="left" width="50%"><span id="branch_name_err" align="left"></span></td>
                    </tr>

                    <tr>
                        <td align="left" width="30%"><%=prop.getProperty("merchant.message.branchcode")%> : <sup
                                style="color:#ff0000; ">*</sup>
                        </td>

                        <td align="left" class="extra" width="20%">
                            <form:input path="branch_code" cssClass="smallinput" id='branch_code' tabindex="1"
                                        cssStyle="" maxlength="50"
                                        onkeypress="javascript:if(checkforchar(event,'name_err')){return trapAdd(event)}else {return false;}"/>
                        </td>

                        <td align="left" width="50%"><span id="branch_code_err" align="left"></span></td>
                    </tr>

                    <tr>
                        <td align="left" width="30%"><%=prop.getProperty("merchant.message.routingnumber")%> : <sup
                                style="color:#ff0000; ">*</sup>
                        </td>

                        <td align="left" class="extra" width="20%">
                            <form:input path="routing_number" cssClass="smallinput" id='routing_number' tabindex="1"
                                        cssStyle="" maxlength="50"
                                        onkeypress="javascript:if(checkforchar(event,'name_err')){return trapAdd(event)}else {return false;}"/>
                        </td>

                        <td align="left" width="50%"><span id="routing_number_err" align="left"></span></td>
                    </tr>

                    <tr>
                        <td align="left" width="30%"><%=prop.getProperty("merchant.message.accountnumber")%> : <sup
                                style="color:#ff0000; ">*</sup>
                        </td>

                        <td align="left" class="extra" width="20%">
                            <form:input path="account_num1" cssClass="smallinput" id='account_num1' tabindex="1"
                                        cssStyle="" maxlength="50"
                                        onkeypress="javascript:if(checkforchar(event,'name_err')){return trapAdd(event)}else {return false;}"/>
                        </td>

                        <td align="left" width="50%"><span id="account_num1_err" align="left"></span></td>
                    </tr>

                    <tr>
                        <td align="left" width="30%"><%=prop.getProperty("merchant.message.confirmaccountnumber")%> : <sup
                                style="color:#ff0000; ">*</sup>
                        </td>

                        <td align="left" class="extra" width="20%">
                            <form:input path="account_num2" cssClass="smallinput" id='account_num2' tabindex="1"
                                        cssStyle="" maxlength="50"
                                        onkeypress="javascript:if(checkforchar(event,'name_err')){return trapAdd(event)}else {return false;}"/>
                        </td>

                        <td align="left" width="50%"><span id="account_num2_err" align="left"></span></td>
                    </tr>

                    <tr>
                        <td colspan="2"><p></p></td>

                    </tr>

                    <tr align="center">
                        <td width="30%">&nbsp;</td>
                        <td align="left" width="20%">


                            <div class="buttonwrapper">
                                <a class="btn btn_bulb" href="#"
                                   onClick="doSave();" tabindex="3"><span><%=prop.getProperty("common.message.update")%></span></a>
                            </div>


                        </td>
                        <td width="50%">&nbsp;</td>
                    </tr>



                </table>

            </td>

        </tr>
        <!--table 2 end  here -->

    </table>
</form:form>--%>


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
