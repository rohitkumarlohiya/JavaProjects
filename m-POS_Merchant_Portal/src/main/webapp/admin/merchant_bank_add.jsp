<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

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

                $('#account_name').tooltipster({
                    offsetX:0,
                    offsetY:25,
                    trigger:'click'
                });
            }
    );

</script>


<script type="text/javascript">


    function doBack()
    {
        window.location.href = "bankAccountList.do";
    }

    function clearall()
    {
        document.getElementById("account_name_err").innerHTML = "";
        document.getElementById("account_type_err").innerHTML = "";
        document.getElementById("bank_name_err").innerHTML = "";
        document.getElementById("branch_name_err").innerHTML = "";
        document.getElementById("branch_code_err").innerHTML = "";
        document.getElementById("routing_number_err").innerHTML = "";
        document.getElementById("account_num1_err").innerHTML = "";
        document.getElementById("account_num2_err").innerHTML = "";
        document.getElementById("pin_err").innerHTML = "";
    }


    function validateForm()
    {
      clearall();
        var flag = true;

       var account_name = document.getElementById("account_name").value.replace(/^\s+|\s+$/g,"");
        var bank_name = document.getElementById("bank_name").value.replace(/^\s+|\s+$/g,"");
        var branch_name = document.getElementById("branch_name").value.replace(/^\s+|\s+$/g,"");
        var branch_code = document.getElementById("branch_code").value.replace(/^\s+|\s+$/g,"");
        var account_num1 = document.getElementById("account_num1").value.replace(/^\s+|\s+$/g,"");
        var account_num2 = document.getElementById("account_num2").value.replace(/^\s+|\s+$/g,"");
        var routing_number = document.getElementById("routing_number").value.replace(/^\s+|\s+$/g,"");
        var pin = document.getElementById("pin").value.replace(/^\s+|\s+$/g,"");


        if (account_name == "" || account_name == null)
        {
            document.getElementById("account_name_err").innerHTML = '<spring:message code="common.message.error.accountname"/>';
            return false;
        }
        flag = isCorrect(account_name, 'alpha', 'account_name_err', '<spring:message code='common.message.error.alphabets'/>');
        if (!flag)return false;
        flag = isMinMax(account_name, parseInt('<spring:message code="name.length.min"/>'), parseInt('<spring:message code="name.length.max"/>'), 'account_name_err', '<spring:message code="common.name.minmax.error"/>');
        if (!flag)return false;

        if (document.getElementById("account_type").value < 1)
        {
            document.getElementById("account_type_err").innerHTML = '<spring:message code="common.message.error.bankactype"/>';
            return false;
        }

        if (bank_name == "" || bank_name == null)
        {
            document.getElementById("bank_name_err").innerHTML = '<spring:message code="common.message.error.bankname"/>';
            return false;
        }
        flag = isCorrect(bank_name, 'alpha', 'bank_name_err', '<spring:message code='common.message.error.alphabets'/>');
        if (!flag)return false;
        flag = isMinMax(bank_name, parseInt('<spring:message code="name.length.min"/>'), parseInt('<spring:message code="name.length.max"/>'), 'bank_name_err', '<spring:message code="common.name.minmax.error"/>');
        if (!flag)return false;


        if (branch_name == "" || branch_name == null)
        {
            document.getElementById("branch_name_err").innerHTML = '<spring:message code="common.message.error.branchname"/>';
            return false;
        }
        flag = isCorrect(branch_name, 'alpha', 'branch_name_err', '<spring:message code='common.message.error.alphabets'/>');
        if (!flag)return false;
        flag = isMinMax(branch_name, parseInt('<spring:message code="name.length.min"/>'), parseInt('<spring:message code="name.length.max"/>'), 'branch_name_err', '<spring:message code="common.name.minmax.error"/>');
        if (!flag)return false;

        if (branch_code == "" || branch_code == null )
        {
            document.getElementById("branch_code_err").innerHTML = '<spring:message code="common.message.error.branchcode"/>';
            return false;
        }
        flag = isCorrect(branch_code, 'Alphanumeric', 'branch_code_err', '<spring:message code='common.message.error.alphanumeric'/>');
        if(!flag)return false;




        if (routing_number == "" || routing_number == null)
        {
            document.getElementById("routing_number_err").innerHTML = '<spring:message code="common.message.error.routingnum"/>';
            return false;
        }

        flag = isCorrect(routing_number, 'numeric', 'routing_number_err', '<spring:message code='common.message.error.numeric'/>');
        if(!flag)return false;
        flag = isMinMax(routing_number, parseInt('<spring:message code="routing.length.min"/>'), parseInt('<spring:message code="routing.length"/>'), 'routing_number_err', '<spring:message code="common.message.error.routingnum"/>');
        if(!flag)return false;



        if (account_num1 == "" || account_num1 == null || isNaN(account_num1) || account_num1.replace(/^\s+|\s+$/g,"").length < parseInt('<spring:message code="account.number.length.min"/>'))
        {
            document.getElementById("account_num1_err").innerHTML = '<spring:message code="common.message.error.acno"/>';
            return false;
        }
        flag = isCorrect(account_num1, 'numeric', 'account_num1_err', '<spring:message code='common.message.error.numeric'/>');
        if (!flag)return false;

        flag = isMinMax(account_num1, parseInt('<spring:message code="account.number.length.min"/>'), parseInt('<spring:message code="account.number.length"/>'), 'account_num1_err', '<spring:message code="common.message.error.acno"/>');
        if (!flag)return false;

        if (account_num2 != account_num1)
        {
            document.getElementById("account_num2_err").innerHTML = '<spring:message code="common.message.error.confirmacnum"/>';
            return false;
        }
        if (pin == "" || pin == null)
        {
            document.getElementById("pin_err").innerHTML = '<spring:message code="common.message.error.secret"/>';
            return false;
        }
        flag = isCorrect(pin, 'numeric', 'pin_err', '<spring:message code='common.message.error.numeric'/>');
        if (!flag)return false;

        flag = isMinMax(pin, parseInt('<spring:message code="pin.length"/>'), parseInt('<spring:message code="pin.length"/>'), 'pin_err', '<spring:message code="merchant.message.enterpin"/>');
        if (!flag)return false;
            //alert("flag is ===>"+flag);
            beforeSubmit('myform');
            document.forms[0].action = "bankAccountAdd.do";
            document.forms[0].method = "post";
            //alert("submitting");
            document.forms[0].submit();
    }

</script>
</head>

<body  class="withvernav" onload="setFocus(document.getElementById('account_name'));document.getElementById('routing_number').value='';document.getElementById('account_num1').value='';">

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
        <h1 class="pagetitle"><spring:message code="merchant.message.bank.add"/>
        </h1>
       <%-- <span class="pagedesc"></span>--%>

        <ul class="hornav">

        </ul>
    </div>
    <!--pageheader-->

    <div id="contentwrapper" class="contentwrapper" style="min-height:400px;">

        <div id="basicform" class="subcontent">


            <form:form id="myform" name="myform" commandName="bankModel" autocomplete="off" onCopy="return false" onPaste="return false">
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



                <!--table 1 start here -->
                <table width="100%" height="40%" cellpadding=0 cellspacing=0>

                <tr>
                    <td>
                                         <span class="result_color" align="left">
                                    <%
                                        if (session.getAttribute("add_bank_err_msg") != null) {
                                    %>
                                    <%=session.getAttribute("add_bank_err_msg")%>
                                    <%
                                        }
                                        session.removeAttribute("add_bank_err_msg");

                                    %>   </span>
                    </td>
                </tr>

                    <tr>
                        <td>
                            <!--table 2 start here -->
                            <table border="0" width="100%">

                                <tr>
                                    <td class="extra1" width="30%"><spring:message code="merchant.message.accountname"/> :
                                        <sup
                                                style="color:#ff0000; ">*</sup>
                                    </td>

                                    <td  class="extra1" width="20%">
                                        <form:input path="account_name" cssClass="smallinput" id='account_name' tabindex="1" title='<%=webAppContext.getMessage("account.name.help",null,"default-account.name.help",null)%>'
                                                    maxlength="50" onkeypress="return MyTrapEnter(this,event,'alpha');"     />

                                    </td>

                                    <td align="left" width="50%"><span id="account_name_err" class="error_color"
                                                                       align="left"></span></td>
                                </tr>


                                <tr>
                                    <td class="extra1" width="30%"><spring:message code="merchant.message.accounttype"/>
                                        : <sup style="color:#ff0000; ">*</sup>
                                    <td  width="20%">
                                        <form:select path="account_type" cssClass="field" id="account_type"
                                                     tabindex="2" cssStyle="width:93%;">
                                            <form:option value="-1" label="--- Select ---"/>
                                            <c:forEach items="${bankModel.accountTypeList}" var="accountType">
                                                <form:option value="${accountType.bankAccountTypeCode}">${accountType.bankAccountTypeName}</form:option>
                                            </c:forEach>
                                        </form:select>
                                    </td>
                                    <td width="50%" align="left"><span id="account_type_err" class="error_color"
                                                                       align="left"></span>
                                    </td>
                                </tr>


                                <tr>
                                    <td class="extra1" width="30%"><spring:message code="merchant.message.bankname"/> :
                                        <sup
                                                style="color:#ff0000; ">*</sup>
                                    </td>

                                    <td  class="extra1" width="20%">
                                        <form:input path="bank_name" cssClass="smallinput" id='bank_name' tabindex="3"
                                                      maxlength="50"
                                                      onkeypress="return MyTrapEnter(this,event,'alpha');" />
                                    </td>

                                    <td align="left" width="50%"><span id="bank_name_err" class="error_color"
                                                                       align="left"></span></td>
                                </tr>

                                <tr>
                                    <td class="extra1" width="30%"><spring:message code="merchant.message.branchname"/> :
                                        <sup
                                                style="color:#ff0000; ">*</sup>
                                    </td>

                                    <td  class="extra1" width="20%">
                                        <form:input path="branch_name" cssClass="smallinput" id='branch_name'
                                                    tabindex="4"
                                                      maxlength="50"
                                                      onkeypress="return MyTrapEnter(this,event,'alpha');"/>
                                    </td>

                                    <td align="left" width="50%"><span id="branch_name_err" class="error_color"
                                                                       align="left"></span></td>
                                </tr>

                                <tr>
                                    <td class="extra1" width="30%"><spring:message code="merchant.message.branchcode"/> :
                                        <sup
                                                style="color:#ff0000; ">*</sup>
                                    </td>

                                    <td class="extra1" width="20%">
                                        <form:input path="branch_code" cssClass="smallinput" id='branch_code'
                                                    tabindex="5"
                                                    maxlength='<%=webAppContext.getMessage("branch.code.length",null,"default-branch.code.length",null)%>'
                                                    onkeypress="return MyTrapEnter(this,event,'Alphanumeric');"/>
                                    </td>

                                    <td align="left" width="50%"><span id="branch_code_err" class="error_color"
                                                                       align="left"></span></td>
                                </tr>

                                <tr>
                                    <td class="extra1" width="30%"><spring:message code="merchant.message.routingnumber"/>
                                        : <sup
                                                style="color:#ff0000; ">*</sup>
                                    </td>

                                    <td class="extra1" width="20%">
                                        <form:input path="routing_number" cssClass="smallinput" id='routing_number'
                                                    tabindex="6"
                                                    onkeypress="return MyTrapEnter(this,event,'numeric');"  maxlength='<%=webAppContext.getMessage("routing.length",null,"default-routing.length",null)%>'/>
                                    </td>

                                    <td align="left" width="50%"><span id="routing_number_err" class="error_color"
                                                                       align="left"></span></td>
                                </tr>

                                <tr>
                                    <td class="extra1" width="30%"><spring:message code="merchant.message.accountnumber"/>
                                        : <sup
                                                style="color:#ff0000; ">*</sup>
                                    </td>

                                    <td class="extra1" width="20%">
                                        <form:password path="account_num1" cssClass="password" id='account_num1'
                                                       tabindex="7"
                                                       onkeypress="return MyTrapEnter(this,event,'numeric');"
                                                       maxlength='<%=webAppContext.getMessage("account.number.length",null,"default-account.number.length",null)%>'/>
                                    </td>

                                    <td align="left" width="50%"><span id="account_num1_err" class="error_color"
                                                                       align="left"></span></td>
                                </tr>

                                <tr>
                                    <td class="extra1"
                                        width="30%"><spring:message code="merchant.message.confirmaccountnumber"/> :
                                        <sup
                                                style="color:#ff0000; ">*</sup>
                                    </td>

                                    <td class="extra1" width="20%">
                                        <form:password path="account_num2" cssClass="password" id='account_num2'
                                                       tabindex="8"
                                                       onkeypress="return MyTrapEnter(this,event,'numeric');"
                                                       maxlength='<%=webAppContext.getMessage("account.number.length",null,"default-account.number.length",null)%>'/>
                                    </td>

                                    <td align="left" width="50%"><span id="account_num2_err" class="error_color"
                                                                       class="error_color" align="left"></span></td>
                                </tr>

                                <tr>
                                    <td class="extra1" width="30%"><spring:message code="merchant.message.enterpin"/> : <sup
                                            style="color:#ff0000; ">*</sup>
                                    </td>

                                    <td  class="extra1" width="20%">
                                        <form:password path="pin" cssClass="password" id='pin' tabindex="9"
                                                       cssStyle="" maxlength='<%=webAppContext.getMessage("pin.length",null,"default-pin.length",null)%>'
                                                       onkeypress="return MyTrapEnter(this,event,'numeric');"/>
                                    </td>

                                    <td align="left" width="50%"><span id="pin_err" class="error_color" align="left"></span></td>
                                </tr>




                                <tr>
                                    <td colspan="2"><p></p></td>

                                </tr>

                                <tr align="center">
                                    <td width="30%">&nbsp;</td>
                                    <td align="left" width="20%">


                                        <div class="buttonwrapper">
                                            <a class="btn btn_rss" href="#" onClick="validateForm();" tabindex="10" onkeypress="return MyTrapEnter(this,event,'');">
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
