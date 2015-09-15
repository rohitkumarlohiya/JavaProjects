<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
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

    <script type="text/javascript">
        function doBack() {
            window.location.href = "bankAccountList.do";
        }

        function doEdit() {
            document.forms[0].pageAction.value = "edit";
            //document.forms[0].bankId.value = id;
            document.forms[0].action = "bankAccountEdit.do";
            document.forms[0].method = "post";
            document.forms[0].submit();
        }

        function doNext()
        {
            window.location.href = "deviceList.do";
        }
    </script>

</head>
<body class="withvernav">
<%--<body onload="enableDisable();" class="withvernav">--%>

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
            <h1 class="pagetitle"><spring:message code="merchant.message.bank.view"/>
            </h1>
            <%--<span class="pagedesc"></span>--%>

            <ul class="hornav">

            </ul>
        </div>
        <!--pageheader-->

        <div id="contentwrapper" class="contentwrapper" style="min-height:400px;">

            <div id="basicform" class="subcontent">


                <form autocomplete="off" onCopy="return false" onPaste="return false">

                    <input type="hidden" name="pageAction" value=""/>
                    <%--<input type="hidden" name="bankId" value='${bankModel.bankId}'/>--%>
                    <br>
                    <%--<table>
                        <tr>
                            <td>
                                <ul class="buttonlist">
                                    <li>
                                        <a href="#" onclick="doBack();"
                                           class="btn-1 btn_back"><span><spring:message code="common.message.back"/></span></a>
                                    </li>
                                </ul>
                            </td>
                        </tr>
                    </table>--%>
                    <table>
                        <tr>
                            <td colspan="3">
                                         <span class="result_color" align="left">
                                    <%
                                        if (session.getAttribute("bank_msg") != null) {
                                    %>
                                    <%=session.getAttribute("bank_msg")%>
                                    <%
                                        }
                                        session.removeAttribute("bank_msg");

                                    %>   </span>
                            </td>
                        </tr>
                    </table>

                    <table  width="50%">
                        <tr >
                            <td width="25%">
                                <ul class="buttonlist">
                                    <li>
                                        <a href="#" onclick="doEdit();"
                                           class="btn-1 btn_back"><span><spring:message code="common.message.edit"/></span></a>
                                    </li>
                                </ul>
                            </td>


                        <td width="25%">
                            <ul class="buttonlist">
                                <%--<li>
                                    <a href="#" onclick="doBack();"
                                       class="btn-1 btn_back"><span><spring:message code="common.message.back"></span></a>
                                </li>--%>

                                <li>
                                    <a href="#" onclick="doNext();"
                                       class="btn-1 btn_back"><span><spring:message code="common.message.next"/></span></a>
                                </li>
                            </ul>
                        </td>

                        </tr>
                    </table>

                      <b>
                    <!--table 2 start here -->
                    <table border="0" width="50%">

                        <tr>
                            <td width="25%"><spring:message code="merchant.message.accountname"/> :
                            </td>

                            <td width="25%">
                                ${bankModel.account_name}
                            </td>
                        </tr>


                        <tr>
                            <td><spring:message code="merchant.message.accounttype"/> :
                            </td>
                            <td>
                                ${bankModel.account_type}
                            </td>


                        </tr>


                        <tr>
                            <td><spring:message code="merchant.message.bankname"/> :
                            </td>
                            <td>
                                ${bankModel.bank_name}
                            </td>

                        </tr>

                        <tr>
                            <td><spring:message code="merchant.message.branchname"/> :
                            </td>
                            <td>
                                ${bankModel.branch_name}
                            </td>

                        </tr>

                        <tr>
                            <td><spring:message code="merchant.message.branchcode"/> :
                            </td>
                            <td>
                                ${bankModel.branch_code}
                            </td>


                        </tr>

                        <tr>
                            <td><spring:message code="merchant.message.routingnumber"/> :
                            </td>
                            <td>
                                ${bankModel.routing_number}
                            </td>

                        </tr>

                        <tr>
                            <td><spring:message code="merchant.message.accountnumber"/> :
                            </td>
                            <td>
                                <c:set var="len" value="${fn:length(bankModel.account_num1)}"/>
                                <c:set var="string1" value="${bankModel.account_num1}"/>
                                <c:set var="string2" value="${fn:substring(string1,len-4, len)}"/>
                                xxxx xxxx xxxx ${string2}
                            </td>

                        </tr>


                    </table>

                      </b>
                </form>


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