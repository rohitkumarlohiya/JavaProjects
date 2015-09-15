<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<title></title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/style.default.css" type="text/css"/>
<!--[if IE 9]>
<link rel="stylesheet" media="screen" href="<%=request.getContextPath() %>/resources/css/style.ie9.css"/>
<![endif]-->
<!--[if IE 8]>
<link rel="stylesheet" media="screen" href="<%=request.getContextPath() %>/resources/css/style.ie8.css"/>

<script src="<%=request.getContextPath() %>/resources/css/css3-mediaqueries.js"></script>
<![endif]-->
<div class="clear"></div>
<div class="bodywrapper">
    <div class="footer1">
        <div class="block-1">
            <div><img src="<%=request.getContextPath() %>/resources/images/logo.png" alt="Estel Logo" width="80" height="50"/></div>
            <p><spring:message code="footer.copyright"/></p>
        </div>

        <div class="block-2">
            <p><br/><spring:message code="footer.licence"/><spring:message code="client.name"/>
                <br/><spring:message code="footer.release"/><spring:message code="release.number"/>
            </p>
        </div>
                                                                     
        <div class="block-3">
         <%--   <div><img src="images/logo.png" alt="Estel Logo" width="80" height="50"/></div>--%>
            <p></p>
        </div>

        <div class="clear"></div>
    </div>
</div>


