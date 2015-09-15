<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.estel.mbanking.webcommon.util.filereader.PropertyFileReader" %>
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
    <meta http-equiv="Content-Language" content="<%=session.getAttribute("locale2")%>">
    <meta name="GENERATOR" content="Microsoft FrontPage 5.0">
    <meta name="ProgId" content="FrontPage.Editor.Document">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <style type="text/css">
    </style>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="stylesheet" href="css/style.default.css" type="text/css"/>
    <%--<link rel="stylesheet" type="text/css" href="stylesheet/style.css">--%>
    <script src="js/leftpanel.js" type="text/javascript"></script>
    <link href="includes/generic.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="includes/js/vendor.js"></script>
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
        function doBack() {
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
            <h1 class="pagetitle"><%=prop.getProperty("merchant.message.device.view")%>
            </h1>
            <span class="pagedesc"></span>

            <ul class="hornav">

            </ul>
        </div>
        <!--pageheader-->

        <div id="contentwrapper" class="contentwrapper" style="min-height:400px;">

            <div id="basicform" class="subcontent">


                <form autocomplete="off" onCopy="return false" onPaste="return false">
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

                    <b>
                        <!--table 2 start here -->
                        <table border="0" width="50%">

                            <tr>
                                <td><%=prop.getProperty("merchant.message.device.number")%> :
                                </td>

                                <td>
                                    ${deviceModel.deviceNumber}
                                </td>
                            </tr>

                            <tr>
                                <td><%=prop.getProperty("common.message.status")%> :
                                </td>

                                <td>
                                    <c:forEach items="${deviceModel.deviceStatusList}" var="deviceStatusList">
                                        <c:if test="${deviceStatusList.id == deviceModel.deviceStatus}">
                                        ${deviceStatusList.description}
                                        </c:if>
                                    </c:forEach>

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