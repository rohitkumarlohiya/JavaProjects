<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<!-- header start -->
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<c:if test="${sessionScope.agentId == null}">
    <%--<script>
       window.location.href = <%=request.getContextPath()%>+"/Mpos.do";
    </script>--%>
    <c:redirect url='Mpos.do'/>
</c:if>

<%
    WebApplicationContext webAppContext = (WebApplicationContext)session.getAttribute("webAppContext");
    if(webAppContext == null)
    {
%>
<%--<script>
    window.location.href = <%=request.getContextPath()%>+"/Mpos.do";
</script>--%>
<c:redirect url='Mpos.do'/>
<%
    }
%>
<%
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "must-revalidate");
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setDateHeader("Expires", 0);
%>

<!-- header start -->

<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
<title></title>

<div class="bodywrapper">
    <div class="topheader">
        <div class="left">
            <h1 class="logo"><img src="<%=request.getContextPath() %>/resources/images/logo.png" alt="Estel Logo" width="131" height="80"/></h1>
            <span class="slogan"><spring:message code="header.admin"/></span>

            <br clear="all"/>

        </div>
        <!--left-->

       <%-- <div class="middle"><img src="images/default/m-pos.png" width="68" height="86" alt="m-pos.png"/>
        </div>--%>
        <div class="middle1">
            <img src="<%=request.getContextPath()%>/resources/images/default/m-pos.png" width="130" height="50" alt="m-pos.png"/>
        </div>

        <div class="right">

            <div class="userinfo">
                <img src="<%=request.getContextPath()%>/resources/images/thumbs/avatar.png" alt=""/>
                <span>Welcome <%=session.getAttribute("UserName")%></span>
            </div>
            <!--userinfo-->

            <div class="userinfodrop">

                <div class="userdata">
                    <h4>Welcome <%=session.getAttribute("UserName")%></h4>
                    <ul>
                        <%-- <li><a href="accountsettings.html">Account Settings</a></li>--%>
                        <%--<li><a href="changePasswd.do"><spring:message code="left.ChangePassword"/></a></li>--%>
                        <%-- <li><a href="help.html">Help</a></li>--%>
                        <li><a href="logout.do"><spring:message code="left.Logout"/></a></li>
                    </ul>
                </div>
                <!--userdata-->
            </div>
            <!--userinfodrop-->
        </div>
        <!--right-->
    </div>
    <!--topheader-->


    <div class="header1">
        <%-- <ul class="headermenu">
                    <li><a href="showDashboard.do"><spring:message code="common.message.agentLevel.dashboard"/></a></li>
                </ul>
        --%>
        <!--headerwidget-->


    </div>
    <!--header-->
</div>


<!-- header end -->

<script>
    <c:if test="${!empty errorMessage}">
    alert('${errorMessage}');
    </c:if>
</script>