<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page import="org.hibernate.criterion.MatchMode" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<%
    int startIndex = 0;
    int pageSize = 0;
    int count = 0;
    int pageDisplay = 1;
    int totalPages = 0;

    if (request.getAttribute("strstartIndex") != null) {
        startIndex = Integer.parseInt(request.getAttribute("strstartIndex").toString());
    }
    if (request.getAttribute("strPagesize") != null) {
        pageSize = Integer.parseInt(request.getAttribute("strPagesize").toString());
    }
    if (request.getAttribute("count") != null) {
        count = Integer.parseInt(request.getAttribute("count").toString());
    }
    try {
        if (count % pageSize > 0)
            totalPages = (count / pageSize) + 1;
        else
            totalPages = count / pageSize;
    } catch (Exception e) {
        System.out.println("pagination.jsp:Inside NumberFormatException");
    }
%>

<script type="text/javascript">
    function doNext() {
        document.forms[1].strstartIndex.value = parseInt(document.forms[1].strstartIndex.value) + parseInt(document.forms[1].strPagesize.value);
        document.forms[1].strPagesize.value = document.forms[1].strPagesize.value;
        document.forms[1].strPageNum.value = parseInt(document.forms[1].strPageNum.value) + 1;
        document.forms[1].action = document.forms[1].straction.value;
        document.forms[1].paginationVal.value = "pagination";
        document.forms[1].method = "post";
        document.forms[1].submit();
    }

    function doPrev() {
        document.forms[1].strstartIndex.value = parseInt(document.forms[1].strstartIndex.value) - parseInt(document.forms[1].strPagesize.value);
        document.forms[1].strPagesize.value = document.forms[1].strPagesize.value;
        document.forms[1].strPageNum.value = parseInt(document.forms[1].strPageNum.value) - 1;
        document.forms[1].action = document.forms[1].straction.value;
        document.forms[1].paginationVal.value = "pagination";
        document.forms[1].method = "post";
        document.forms[1].submit();
    }

    function doLast() {
        document.forms[1].strstartIndex.value =<%= count-(count % pageSize) %>;
        document.forms[1].strPagesize.value = document.forms[1].strPagesize.value;
        document.forms[1].strPageNum.value = <%= totalPages %>;
        document.forms[1].action = document.forms[1].straction.value;
        document.forms[1].paginationVal.value = "pagination";
        document.forms[1].method = "post";
        document.forms[1].submit();
    }

    function doFirst() {
        document.forms[1].strstartIndex.value = 0;
        document.forms[1].strPagesize.value = document.forms[1].strPagesize.value;
        document.forms[1].strPageNum.value = 1;
        document.forms[1].action = document.forms[1].straction.value;
        document.forms[1].paginationVal.value = "pagination";
        document.forms[1].method = "post";
        document.forms[1].submit();
    }

    function doDisplay() {
        document.forms[1].strstartIndex.value =<%= count-(count % pageSize) %>;
        document.forms[1].strPagesize.value = document.forms[1].strPagesize.value;
        document.forms[1].strPageNum.value = <%= totalPages %>;
        document.forms[1].action = document.forms[1].straction.value;
        document.forms[1].paginationVal.value = "pagination";
        document.forms[1].method = "post";
        document.forms[1].submit();

    }
    function submit_Display() {
        var valid = validateDisplayVendor();
        if (valid) {
            document.forms[1].strstartIndex.value = 0;
            document.forms[1].strPagesize.value = document.forms[1].pagesize.value;
            document.forms[1].strPageNum.value = document.forms[1].pageNum.value;
            document.forms[1].action = document.forms[1].straction.value;
            document.forms[1].paginationVal.value = "pagination";
            document.forms[1].method = "post";
            document.forms[1].submit();
        }
    }

    function validateDisplayVendor() {

        var debug = false;
        var returnval = true;
        var returnFlag = true;

        returnval = hasValue(document.forms[1].pageNum, 'TEXT', '<spring:message code="pagination.valid.pagefield"/>', 'display_err');
        if (returnval == false) returnFlag = false;
        if (returnval == true) {
            returnval = hasValueZero(document.forms[1].pageNum, '<spring:message code="pagination.valid.zero"/>', 'display_err');
        }
        if (returnval == true) {

            returnval = isNumericOnly(document.forms[1].pageNum, '<spring:message code="pagination.valid.int"/>', 'display_err');
        }

        if (returnval == false) returnFlag = false;

        if (returnval == true) {
            returnval = hasValue(document.forms[1].totalPages, 'TEXT', '<spring:message code="pagination.valid.enter"/>', 'display_err');
            if (returnval == false) returnFlag = false;
            if (returnval == true) {

                returnval = isNumericOnly(document.forms[1].totalPages, '<spring:message code="pagination.valid.int"/>', 'display_err');
            }
        }
        if (returnval == false) returnFlag = false;

        if (returnval == true) {
            returnval = hasValue(document.forms[1].pagesize, 'TEXT', '<spring:message code="pagination.valid.show"/>', 'display_err');
            if (returnval == false) returnFlag = false;
            if (returnval == true) {
                returnval = isNumericOnly(document.forms[1].pagesize, '<spring:message code="pagination.valid.int"/>', 'display_err');
            }
            if (returnval == false) {
                document.forms[1].pagesize.focus();
                returnFlag = false;
            }
            if (returnval == true) {
                returnval = hasValueZero(document.forms[1].pagesize, '<spring:message code="pagination.valid.zero"/>', 'display_err');
            }
            if (returnval == false) {
                document.forms[1].pagesize.focus();
                returnFlag = false;
            }

        }
        if (returnval == false) returnFlag = false;


        returnval = returnFlag;
        return returnval;

    }

    function trapEnter(evt) {
        //alert("test");
        var keycode;
        if (evt)
            ;
        else if (window.event)
            evt = window.event;
        else if (event)
            evt = event;
        else
            return true;
        if (evt.charCode)
            keycode = evt.charCode;
        else if (evt.keyCode)
            keycode = evt.keyCode;
        else if (evt.which)
            keycode = evt.which;
        else
            keycode = 0;
        if (keycode == 13) {

            var valid = submit_Display();
            if (valid)
                return true;
            else
                return false;
        } else
            return true;
    }

</script>

<input type="hidden" name="strstartIndex" value=<%= request.getAttribute("strstartIndex")%>>
<input type="hidden" name="strPagesize" value=<%= request.getAttribute("strPagesize")%>>
<input type="hidden" name="strPageNum" value=<%= request.getAttribute("strPageNum")%>>
<input type="hidden" name="paginationVal" id="paginationVal">

<table class="style33" border=0 cellspacing=0 cellpadding=0 width="100%">

    <tr width="100%">
        <td colspan="5">
            <br>
            <table border="0" width="100%">
                <tr>
                    <td width="5%"><p class="navtext"><spring:message code="pagination.Page"/>
                    </td>
                    <td width="5%"><p class="note"><input maxlength="3" name="pageNum" id="pageNum"
                                                          value='<%= request.getAttribute("strPageNum")%>' size="3"
                                                          onkeypress="javascript:if(checkforInt(event,'display_err')){return trapEnter(event);}else{return false};"></td>
                    <td width="1%"></td>

                    <td width="3%"><p class="navtext"><spring:message code="pagination.Of"/>
                    </td>
                    <td width="5%"><p class="note"><input maxlength="3" name="totalPages" id="totalPages"
                                                          size="3" value='<%=totalPages%>' readonly
                                                          onkeypress="javascript:if(checkforInt(event,'display_err')){return trapEnter(event);}else{return false};"></td>
                    <td width="1%"></td>
                    <td width="7%"><p class="navtext"><spring:message code="pagination.ShowLines"/>
                    </td>
                    <td width="5%"><p class="note"><input maxlength="3" name="pagesize" id="pagesize"
                                                          value='<%= pageSize%>'
                                                          size="3"
                                                          onkeypress="javascript:if(checkforInt(event,'display_err')){return trapEnter(event);}else{return false};"></td>
                    <td width="1%">

                    </td>
                    <td width="8%">
                        <button class="submit radius2" onclick="submit_Display();" title='Back'>
                            <span><spring:message code="pagination.Display"/></span></button>

                    </td>
                    <%--<a class="boldbuttons" href="#" onclick="submit_Display()"><span><spring:message code="pagination.Display"/></span></a></td>--%>

                    <td width="12%"><p class="navtext"><spring:message code="pagination.totalrecords"/>:
                        &nbsp;<%=count%>
                    </td>

                    <td width="10%"><span id="display_err"></span></td>
                    <td>
                    </td>
                    <td align="right" width="9%">
                        <%
                            if (startIndex != 0) {
                        %>

                        <button class="submit radius2" onclick="doFirst();">
                            <span><spring:message code="pagination.First"/></span></button>
                        <%--<a class="boldbuttons"  href="#" onclick="doFirst();"><span><spring:message code="pagination.First"/></span></a>--%>

                        <%
                        } else {
                        %>

                        <button class="submit radius2" disabled><span><spring:message code="pagination.First"/></span>
                        </button>
                        <%--<a class="boldbuttons" disabled><span><spring:message code="pagination.First"/></span></a></div>--%>
                        <%}%>
                    </td>
                    <td width="3%">

                    </td>
                    <td width="9%">
                        <%
                            if (startIndex != 0 && !(startIndex < 0)) {
                        %>

                        <button class="submit radius2" onclick="doPrev();">
                            <span><spring:message code="pagination.Previous"/></span></button>
                        <%--<a class="boldbuttons" href="#" onclick="doPrev();"><span><spring:message code="pagination.Previous"/></span></a>--%>

                        <%
                        } else {
                        %>

                        <button class="submit radius2" disabled>
                            <span><spring:message code="pagination.Previous"/></span>
                        </button>
                        <%--<a class="boldbuttons" disabled><span><spring:message code="pagination.Previous"/></span></a></div>--%>
                        <%}%>
                    </td>
                    <td width="3%">

                    </td>
                    <td width="5%">

                        <%
                            if (startIndex + pageSize < count) {
                        %>

                        <button class="submit radius2" onclick="doNext();">
                            <span><spring:message code="pagination.Next"/></span></button>
                        <%--<a class="boldbuttons" href="#" onclick="doNext();"><span><spring:message code="pagination.Next"/></span></a>--%>

                        <%
                        } else {
                        %>

                        <button class="submit radius2" disabled><span><spring:message code="pagination.Next"/></span>
                        </button>
                        <%--<a class="boldbuttons" disabled><span><spring:message code="pagination.Next"/></span></a></div>--%>
                        <%}%>
                    </td>
                    <td width="3%">
                    </td>

                    <td width="5%">

                        <%
                            if (startIndex + pageSize < count) {
                        %>
                        <%
                            if (count % pageSize > 0) {
                        %>

                        <button class="submit radius2" onclick="doLast();">
                            <span><spring:message code="pagination.Last"/></span></button>
                        <%--<a class="boldbuttons" href="#" onclick="doLast()"><span><spring:message code="pagination.Last"/></span></a>--%>

                        <%
                        } else {
                        %>

                        <button class="submit radius2" onclick="doLast();">
                            <span><spring:message code="pagination.Last"/></span></button>
                        <%--<a class="boldbuttons" href="#" onclick="doLast();"><span><spring:message code="pagination.Last"/></span></a>--%>

                        <%}%>
                        <%
                        } else {
                        %>

                        <button class="submit radius2" disabled><span><spring:message code="pagination.Last"/></span>
                        </button>
                        <%--<a class="boldbuttons"  disabled><span><spring:message code="pagination.Last"/></span></a></div>--%>
                        <%}%>
                    </td>
                </tr>
            </table>
        </td>

    </tr>
</table>


