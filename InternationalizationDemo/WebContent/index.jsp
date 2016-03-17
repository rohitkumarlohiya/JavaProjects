<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.ResourceBundle"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Internationalization Demo</title>
</head>
<body>

<%-- <%-- <%
ResourceBundle resourceBundle = (ResourceBundle)session.getAttribute("resourceBundle");
out.println(resourceBundle.getString("USER_NAME"));
%> --%>



<%
ResourceBundle resourceBundle = ResourceBundle.getBundle("i18.DemoBundle", request.getLocale());
//out.println(resourceBundle.getString("USER_NAME"));
%>
<%=resourceBundle.getString("USER_NAME") %>
</body>
</html>