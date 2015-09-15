<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title><spring:message code="common.message.title"/></title>

    <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/style.default.css" type="text/css"/>

    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/plugins/jquery-1.7.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/plugins/jquery-ui-1.8.16.custom.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/plugins/jquery.cookie.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/plugins/jquery.uniform.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/plugins/jquery.slimscroll.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/custom/general.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/custom/dashboard.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/mpos.js"></script>

    <!--[if lte IE 8]>
    <script language="javascript" type="text/javascript" src="<%=request.getContextPath() %>/resources/js/plugins/excanvas.min.js"></script><![endif]-->
    <!--[if IE 9]>
    <link rel="stylesheet" media="screen" href="<%=request.getContextPath() %>/resources/css/style.ie9.css"/>
    <![endif]-->
    <!--[if IE 8]>
    <link rel="stylesheet" media="screen" href="<%=request.getContextPath() %>/resources/css/style.ie8.css"/>
    <![endif]-->
    <!--[if lt IE 9]>
    <script src="<%=request.getContextPath() %>/resources/css/css3-mediaqueries.js"></script>
    <![endif]-->

</head>
<body>
<div class="bodywrapper">

<!--  header  start-->
<%@include file="header1.jsp" %>
<!--  header  end -->

<div>
<%--  leftmenu start--%>
<%@include file="leftpanel.jsp" %>
<!--leftmenu-->

<%--  leftmenu end --%>

<div class="centercontent">

<%@include file="breadcrums.jsp" %>

<!--pageheader satrts here-->
<div class="pageheader">

    <h1 class="pagetitle">
        <spring:message code="common.message.agentLevel.dashboard"/>
    </h1>

    <ul class="hornav"></ul>
</div>
<!--pageheader ends here-->

<div id="contentwrapper" class="contentwrapper">

<div id="updates" class="subcontent">

<div class="notibar announcement">
<a class="close"></a>

<h3>
    <spring:message code="common.message.announcement"/>
</h3>

<p>
    <b> <spring:message
            code="common.message.announcement.welcome"/> <br/><br/></b>
</p>


<%-- add for registration--%>

<%--<div class="one_third last dashboard_right" style="margin-left: 10%">--%>

<div>
    <%-- <div class="contenttitle2_1 nomargintop">--%>
    <%--<c:if test="${registration_status != 'incomplete'}">--%>

    <c:if test="${registration_status == 'incomplete'}">
        <div align="left" style="color: #FB9337;">
            <h3>
                <spring:message
                        code="common.message.top.pending.registration"/>
            </h3>
        </div>

        <!--contenttitle-->


        <c:set var="tempStepNo" value='<%=Integer.parseInt(session.getAttribute("stepNo").toString())%>'/>

        <table style="margin-left: 5%" width="25%">

            <c:if test="${tempStepNo == 1}">
                <tr>
                    <td width="25%"><a href="merchantBusinessInfoAction.do"><spring:message
                            code="left.BusinessInformation"/></a></td>
                    <td width="25%" style="color: #ff0000;"><spring:message
                            code="status.pending"/></td>
                </tr>
                <tr>
                    <td><a href="merchantProfileAction.do"><spring:message
                            code="left.MerchantProfile"/></a></td>
                    <td style="color: #ff0000;"><spring:message
                            code="status.pending"/></td>
                </tr>
                <tr>
                    <td><a href="bankAccountList.do"><spring:message
                            code="left.BankAccount"/></a></td>
                    <td style="color: #ff0000;"><spring:message
                            code="status.pending"/></td>
                </tr>
                <tr>
                    <td><a href="deviceList.do"><spring:message
                            code="left.RegisterDevice"/></a></td>
                    <td style="color: #ff0000;"><spring:message
                            code="status.pending"/></td>
                </tr>
            </c:if>

            <c:if test="${tempStepNo == 2}">
                <tr>
                    <td style="color: green; font-weight: bold;"><spring:message
                            code="left.BusinessInformation"/></td>
                    <td style="color: green; font-weight: bold;"><spring:message
                            code="status.completed"/></td>
                </tr>
                <tr>
                    <td><a href="merchantProfileAction.do"><spring:message
                            code="left.MerchantProfile"/></a></td>
                    <td style="color: #ff0000;"><spring:message
                            code="status.pending"/></td>
                </tr>
                <tr>
                    <td><a href="bankAccountList.do"><spring:message
                            code="left.BankAccount"/></a></td>
                    <td style="color: #ff0000;"><spring:message
                            code="status.pending"/></td>
                </tr>
                <tr>
                    <td><a href="deviceList.do"><spring:message
                            code="left.RegisterDevice"/></a></td>
                    <td style="color: #ff0000;"><spring:message
                            code="status.pending"/></td>
                </tr>

            </c:if>

            <c:if test="${tempStepNo == 3}">
                <tr>
                    <td style="color: green; font-weight: bold;"><spring:message
                            code="left.BusinessInformation"/></td>
                    <td style="color: green; font-weight: bold;"><spring:message
                            code="status.completed"/></td>
                </tr>
                <tr>
                    <td style="color: green; font-weight: bold;"><spring:message
                            code="left.MerchantProfile"/></td>
                    <td style="color: green; font-weight: bold;"><spring:message
                            code="status.completed"/></td>
                </tr>
                <tr>
                    <td><a href="bankAccountList.do"><spring:message
                            code="left.BankAccount"/></a></td>
                    <td style="color: #ff0000;"><spring:message
                            code="status.pending"/></td>
                </tr>
                <tr>
                    <td><a href="deviceList.do"><spring:message
                            code="left.RegisterDevice"/></a></td>
                    <td style="color: #ff0000;"><spring:message
                            code="status.pending"/></td>
                </tr>

            </c:if>

            <c:if test="${tempStepNo == 4}">
                <tr>
                    <td style="color: green; font-weight: bold;"><spring:message
                            code="left.BusinessInformation"/></td>
                    <td style="color: green; font-weight: bold;"><spring:message
                            code="status.completed"/></td>
                </tr>
                <tr>
                    <td style="color: green; font-weight: bold;"><spring:message
                            code="left.MerchantProfile"/></td>
                    <td style="color: green; font-weight: bold;"><spring:message
                            code="status.completed"/></td>
                </tr>
                <tr>
                    <td style="color: green; font-weight: bold;"><spring:message
                            code="left.BankAccount"/></td>
                    <td style="color: green; font-weight: bold;"><spring:message
                            code="status.completed"/></td>
                </tr>
                <tr>
                    <td><a href="deviceList.do"><spring:message
                            code="left.RegisterDevice"/></a></td>
                    <td style="color: #ff0000;"><spring:message
                            code="status.pending"/></td>
                </tr>

            </c:if>

            <c:if test="${tempStepNo == 5}">
                <tr>
                    <td style="color: green; font-weight: bold;"><spring:message
                            code="left.BusinessInformation"/></td>
                    <td style="color: green; font-weight: bold;"><spring:message
                            code="status.completed"/></td>
                </tr>
                <tr>
                    <td style="color: green; font-weight: bold;"><spring:message
                            code="left.MerchantProfile"/></td>
                    <td style="color: green; font-weight: bold;"><spring:message
                            code="status.completed"/></td>
                </tr>
                <tr>
                    <td style="color: green; font-weight: bold;"><spring:message
                            code="left.BankAccount"/></td>
                    <td style="color: green; font-weight: bold;"><spring:message
                            code="status.completed"/></td>
                </tr>
                <tr>
                    <td style="color: green; font-weight: bold;"><spring:message
                            code="left.RegisterDevice"/></td>
                    <td style="color: green; font-weight: bold;"><spring:message
                            code="status.completed"/></td>
                </tr>

            </c:if>
        </table>
    </c:if>


        <c:if test="${!empty deviceOrderList}">

           <%-- <table style="margin-left: 5%" width="25%" border="1">--%>
            <table style="margin-left: 5%" width="30%" border="0">

               <%-- <tr align="left">
                    <td colspan="2"  style="color: #FB9337;"><h3><spring:message code="common.message.top.pending.order"/></h3></td>
                </tr>--%>

                <%--<div align="left" style="color: #FB9337;">
                    <h3><spring:message code="common.message.top.pending.order"/></h3>
                </div>--%>

                <%--
                <tr align="left">
                    <th width="25%"><spring:message code="order.number"/></th>
                    <th width="25%"><spring:message code="order.status"/></th>
                </tr>
                --%>

                   <thead>
                   <tr>
                       <th colspan="2"  style="color: #FB9337;"><h3 align="left" style="margin-left: 0px;"><spring:message
                               code="common.message.top.pending.order"/></h3></th>

                   </tr>
                   <tr>
                       <th align="left"><spring:message code="order.number"/></th>
                       <th align="left"><spring:message code="order.status"/></th>
                   </tr>
                   </thead>

                <c:forEach items="${deviceOrderList}" var="deviceOrder">
                    <tr>
                        <td>${deviceOrder.deviceOrderOrderNo}</td>
                        <td>
                            <c:forEach items="${statusList}" var="status">
                                <c:if test="${status.statusId == deviceOrder.deviceOrderStatus}">
                                    ${status.statusDescription}
                                </c:if>
                            </c:forEach>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:if>



</div>

<%-- add for registration--%>

</div>
<!-- notification announcement -->
<p>
    <font style="font: xx-small"> <b> <spring:message
            code="common.message.announcement.message"/></b></font>
</p>

<div class="two_third dashboard_left">


    <br clear="all"/>

    <div id="chartplace" style="height: 1px;"></div>

    <br clear="all"/>


</div>
<!--two_third dashboard_left -->

<%--                    <div class="one_third last dashboard_right">


    <div class="contenttitle2 nomargintop">
        <h3><spring:message code="common.message.top.pending.registration")%>
        </h3>
    </div>
    <!--contenttitle-->


    <ul class="toplist">

        <li>
            <div>
                <span class="three_fourth">
                    <span class="left">
                        <span class="title">
                           &lt;%&ndash; registration_status = ${registration_status}&ndash;%&gt;
                              <c:if test="${registration_status != 'incomplete'}">
                            <a href="#"><spring:message code="common.message.registration.complete")%></a>
                              </c:if>
                                   <c:if test="${registration_status == 'incomplete'}">
                                       <% session.setAttribute("fromdashboard","fromdashboard"); %>
                            <a href="deviceList.do"><spring:message code="common.message.registration.incomplete")%></a>
                            </c:if>
                        </span>
                        <span class="desc">
                            <c:if test="${registration_status == 'incomplete'}">
                                <spring:message code="common.message.completion.link")%>
                                </c:if>
                        </span>
                    </span><!--left-->
                </span><!--three_fourth-->
                &lt;%&ndash;<span class="one_fourth last">
                    <span class="right">
                        <span class="h3">25%&lt;%&ndash;${dashBoardModel.topupPendingListSize}&ndash;%&gt;</span>
                    </span><!--right-->
                </span><!--one_fourth-->&ndash;%&gt;
                <br clear="all"/>
            </div>
        </li>

    </ul>


</div>--%>
<!--one_third last-->


</div>
<!-- #updates -->

<div id="activities" class="subcontent" style="display: none;">
    &nbsp;</div>
<!-- #activities -->

</div>
<!--contentwrapper-->

<br clear="all"/>
<!--subcontent-->


</div>
<!--contentwrapper-->


</div>

<!-- centercontent -->

</div>

<%-- footer start--%>
<div class="footer1">

    <%@include file="footer.jsp" %>

</div>

<%-- footer end --%>
<!--bodywrapper-->

</body>

</html>
