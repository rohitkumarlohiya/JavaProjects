<%--
  Created by IntelliJ IDEA.
  User: rohit
  Date: 27/1/14
  Time: 2:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script type="text/javascript">
        function reportPost(agentName,agentIdStr,urlReport){
            window.open();

            //alert(agentName+","+agentIdStr+","+urlReport);
            document.getElementById("userId").value = "3";
            document.getElementById("userName").value = agentName;
            document.getElementById("agentId1").value = agentIdStr;
            document.getElementById("reportsName").value = "Transaction Detail, Transaction Summary, Batch History";
            document.getElementById("report").action = urlReport;
            document.getElementById("report").method = "post";
            document.getElementById("report").submit();
        }
    </script>
</head>
<body onload="reportPost('<%=session.getAttribute("userName")%>','<%=session.getAttribute("agentId1")%>','<%=session.getAttribute("urlReport")%>')">

<form name="report" id="report" autocomplete="off" onCopy="return false" onPaste="return false">
    <input type="hidden" id="userId" name="userId" value="">
    <input type="hidden" id="userName" name="userName" value="">
    <input type="hidden" id="reportsName" name="reportsName" value="">
    <input type="hidden" id="agentId1" name="agentId1" value="">
</form>

</body>
</html>