<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <title><spring:message code="common.message.title"/>
    </title>
    <meta name="GENERATOR" content="Microsoft FrontPage 5.0">
    <meta name="ProgId" content="FrontPage.Editor.Document">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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

        function doEdit() {

            document.forms[0].action = "merchantProfileEdit.do";
            document.forms[0].method = "post";
            document.forms[0].submit();
        }

        function doNext()
        {
            window.location.href = "bankAccountList.do";
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
            <h1 class="pagetitle"><spring:message code="merchant.message.basicprofileinfo.view"/>
            </h1>
            <%--<span class="pagedesc"></span>--%>

            <ul class="hornav">

            </ul>
        </div>
        <!--pageheader-->

        <div id="contentwrapper" class="contentwrapper" style="min-height:400px;">

            <div id="basicform" class="subcontent">


                <form autocomplete="off" onCopy="return false" onPaste="return false">
                    <br>
                    <table border="0" width="50%">

                        <tr>
                            <td>
                                         <span class="result_color" align="left">
                                    <%
                                        if (session.getAttribute("change_profile_msg") != null) {
                                    %>
                                    <%=session.getAttribute("change_profile_msg")%>
                                    <%}
                                        session.removeAttribute("change_profile_msg");

                                    %>   </span>
                            </td>
                        </tr>
                        </table>

                    <table width="100%">
                        <tr>
                            <td width="25%"  align="left">
                                <ul class="buttonlist">
                                    <%--<li>
                                        <a href="#" onclick="doBack();"
                                           class="btn-1 btn_back"><span><spring:message code="common.message.back"/></span></a>
                                    </li>--%>

                                    <li>
                                        <a href="#" onclick="doEdit();"
                                           class="btn-1 btn_back"><span><spring:message code="common.message.edit"/></span></a>
                                    </li>
                                </ul>
                            </td>


                            <td width="75%" align="left">
                                <ul class="buttonlist">
                                    <%--<li>
                                        <a href="#" onclick="doBack();"
                                           class="btn-1 btn_back"><span><spring:message code="common.message.back"/></span></a>
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
                        <table border="0" width="100%">

                            <tr>
                                <td width="25%"><spring:message code="merchant.message.merchantname"/>
                                </td>

                                <td width="75%">
                                    ${userModel.contactName}
                                </td>
                            </tr>

                            <tr>
                                <td><spring:message code="merchant.message.residentialaddress"/></td>
                                <td>    ${userModel.residentialAddress}
                                </td>

                            </tr>

                            <tr>
                                <td><spring:message code="merchant.message.state"/>
                                </td>
                                <td>
                                    <c:forEach items="${userModel.stateList}" var="state">
                                        <c:if test="${state.stateId == userModel.residentialState}">
                                            ${state.stateDescription}
                                        </c:if>
                                    </c:forEach>
                                </td>

                            </tr>


                            <tr>
                                <td><spring:message code="merchant.message.city"/>
                                </td>
                                <td>
                                    ${userModel.residentialCity}
                                </td>

                            </tr>


                            <tr>
                                <td><spring:message code="merchant.message.pincode"/></td>
                                <td>    ${userModel.residentialZipCode}
                                </td>

                            </tr>

                            <tr>
                                <td><spring:message code="merchant.message.mobilenumber"/></td>
                                <td>     ${userModel.mobileNumber}
                                </td>

                            </tr>

                            <tr>
                                <td><spring:message code="merchant.message.alternatenumber"/></td>
                                <td>    ${userModel.alternateMobileNumber}
                                </td>

                            </tr>

                            <tr>
                                <td><spring:message code="merchant.message.permanentaddress"/></td>
                                <td>        ${userModel.permanentAddress}
                                </td>

                            </tr>


                            <tr>
                                <td><spring:message code="merchant.message.state"/>
                                </td>   <td>
                                <c:forEach items="${userModel.stateList}" var="state">
                                    <c:if test="${state.stateId == userModel.permanentState}">
                                        ${state.stateDescription}
                                    </c:if>
                                </c:forEach>
                            </td>

                            </tr>


                            <tr>
                                <td><spring:message code="merchant.message.city"/>
                                </td>
                                <td>
                                    ${userModel.permanentCity}
                                </td>

                            </tr>

                            <tr>
                                <td><spring:message code="merchant.message.pincode"/></td>
                                <td>    ${userModel.permanentZipCode}
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