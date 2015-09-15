<%@ page import="com.estel.controller.BreadcrumbController" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>

<html>
<head><title></title>
</head>
<body>
<ul class="breadcrumbs">

    <%
       //WebApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());

        WebApplicationContext webAppContext2 = (WebApplicationContext)session.getAttribute("webAppContext");
        String mainPanelByController = "";
        String displayParentNameByController = "";
        String displayChildNameByController = "";
        if(webAppContext2 != null)
        {
       BreadcrumbController breadcrumbController = webAppContext2.getBean("breadcrumbController",BreadcrumbController.class);
       breadcrumbController.headerBreadCrums(request, response);
        mainPanelByController = breadcrumbController.getMainPanel();
        displayParentNameByController = breadcrumbController.getDisplayParentName();
        displayChildNameByController = breadcrumbController.getDisplayChildName();
        }
       if (mainPanelByController != null && mainPanelByController != "") {
   %>

   <li>&nbsp;&nbsp;&nbsp;<b><%=mainPanelByController%>
   </b>&nbsp;&nbsp;&nbsp;&nbsp;</li>
   <%
       }
       if (displayParentNameByController != null && displayParentNameByController != "") {

   %>
   <li>&nbsp;&nbsp;&nbsp;<b><%=displayParentNameByController%>
   </b>&nbsp;&nbsp;&nbsp;&nbsp;</li>
   <%
       }
       if (displayChildNameByController != null && displayChildNameByController != "") {

   %>
   <li>&nbsp;&nbsp;&nbsp;<b><%=displayChildNameByController%>
   </b>&nbsp;&nbsp;&nbsp;&nbsp;</li>

   <%} %>
</ul>
</body>
</html>