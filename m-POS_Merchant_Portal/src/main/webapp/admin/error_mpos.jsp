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

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/stylesheet/style.css">
<script src="<%=request.getContextPath() %>/resources/js/leftpanel.js" type="text/javascript"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/stylesheet/style.default.css" type="text/css"/>

<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/plugins/jquery-1.7.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/plugins/jquery-ui-1.8.16.custom.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/plugins/jquery.cookie.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/plugins/jquery.uniform.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/plugins/jquery.validate.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/plugins/jquery.tagsinput.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/plugins/charCount.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/plugins/ui.spinner.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/custom/general.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/custom/forms.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/includes/js/validations.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/includes/js/validations_client.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/includes/js/vendor.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/common.js"></script>


<%--Setting for tooltip--%>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/resources/tooltipster-master/css/tooltipster.css" />
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/tooltipster-master/js/jquery.tooltipster.min.js"></script>

<%--Setting for tooltip--%>


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

<script>


</script>

</head>

<body class="withvernav">

<div class="bodywrapper">

<!-- header start-->
<%@include file="header2.jsp" %>
<!-- header end -->

<%-- leftmenu start--%>
<%--<%@include file="leftpanel.jsp" %>--%>
<%-- leftmenu end --%>

<div class="centercontent1">

<%--<%@include file="breadcrums.jsp" %>--%>
<div class="pageheader" align="center">
    <h1 class="pagetitle"><spring:message code="error.occured"/>
    </h1>
    <span class="pagedesc"></span>

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

            <br><br>
            <%--<h1 align="center" style="color: blue;"><a href="<%=gotoURL%>">Click here</a> to login again.</h1>--%>
                <center>
                    <spring:message code="error.occured"/>
                    <br>
                    <%--<a href="merchantLogin.do">Click Here to Login</a>--%>
                    <a href="<c:redirect url='Mpos.do'/>" style="color: blue;">Click Here to Login</a>
                </center>

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


