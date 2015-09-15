<!-- header start -->
<%@ page import="java.net.*" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%
    WebApplicationContext webAppContext = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());
    session.setAttribute("webAppContext",webAppContext);
%>

<div class="fleft top-lg"><img src="<%=request.getContextPath() %>/resources/images/logo.png" width="131" height="80" alt="Estel Logo" /></div>
<div class="fleft top-lg-text"><spring:message code="common.message.title"/></div>

<div align="right" class="fright1">
    <img src="<%=request.getContextPath()%>/resources/images/default/m-pos.png" width="130" height="100" alt="m-pos.png" style="margin-top: 33px;"/>
</div>
<div class="clear"></div>
<div class="top-lg-btm"></div>
<script>
    <c:if test="${!empty errorMessage}">
    alert('${errorMessage}');
    </c:if>
</script>

