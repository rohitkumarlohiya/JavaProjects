var sessionStatus;
var reportkey;
function IsNumeric(sText)


{


    var ValidChars = "0123456789";


    var IsNumber = true;


    var Char;


    for (i = 0; i < sText.length && IsNumber;

         i++)

    {

        Char = sText.charAt(i);

        if (ValidChars.indexOf(Char) == -1)
        {


            IsNumber = false;


        }


    }


    return IsNumber;


}


//////////////////////// Fun to check a Alfanumeric value.


function AlfaNumeric(sText)


{


    var ValidChars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";


    var IsNumber = true;


    var Char;


    for (i = 0; i < sText.length && IsNumber;

         i++)


    {


        Char = sText.charAt(i);


        if (ValidChars.indexOf(Char) == -1)


        {


            IsNumber = false;


        }


    }


    return IsNumber;


}


//reporting new entry 03-12-2009


var xmlHttpRequest;

//var status=false;

var str;

var reportStatus = true;


function validateEmail(elementValue) {

    var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;

    return emailPattern.test(elementValue);

}


function isEmailId(email) {

    var status = false;

    var validChars = "._@0123456789abcdedghijklmnopqrstuvwxyz";

    var length = 0;

    var count = 0;

    try {

        if (email == null || email.charAt(0) == '.' || email.charAt(0) == '@' || email.charAt(0) == '_' || email.search("\\.\\.") != -1 || email.search("@@") != -1 || email.search("__") != -1 || email.search("\\._") != -1 || email.search("\\.@") != -1 || email.search("_@") != -1 || email.search("@_") != -1 || email.search("@\\.") != -1 || email.length > 45 || email.length < 1) {

            return(status);

        }


        length = email.length;

        if (email.charAt(length - 1) == '.' || email.charAt(length - 1) == '@' || email.charAt(length - 1) == '_') {// checking at last position

            return(status);

        }

        status = true;


        for (i = 0; i < length; i++) { // checking valid characters.

            if (validChars.indexOf(email.charAt(i)) == -1) {

                status = false;

                break;

            }

        }

        if (status) {

            for (i = 0; i < length; i++) { // checking for presence of double occurance of @ at different position.

                if (email.charAt(i) == '@') {

                    count++;

                }

                if (count > 1) {

                    status = false;

                    break;

                }

            }

        }


        if (status) {// checking at least one occurance of . and @ in the maill last, so that these will be at legal position

            if (email.search("\\.") != -1 && email.search("@") != -1 && email.lastIndexOf('.') > email.indexOf('@')) {

                status = true;

            } else {

                status = false;

            }

        }


    } catch(ex) {


    }


    return(status);

}


//validate Email


function isNumberKey(evt)

{

    var charCode = (evt.which) ? evt.which : event.keyCode

    if (charCode > 31 && (charCode < 48 || charCode > 57))

        return false;

    else

        return true;

}


function IsNumeric(sText)
{


    var ValidChars = "0123456789";


    var IsNumber = true;


    var Char;


    for (i = 0; i < sText.length && IsNumber;

         i++)


    {


        Char = sText.charAt(i);


        if (ValidChars.indexOf(Char) == -1)


        {


            IsNumber = false;


        }


    }


    return IsNumber;


}


function AlfaNumeric(sText)


{


    var ValidChars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";


    var IsNumber = true;


    var Char;


    for (i = 0; i < sText.length && IsNumber;

         i++)


    {


        Char = sText.charAt(i);


        if (ValidChars.indexOf(Char) == -1)


        {


            IsNumber = false;


        }


    }


    return IsNumber;


}


//reporting new entry 03-12-2009


if (reportStatus) {

    function ajaxCallForSearchCriteria(url)

    {

        //Check if Browser is Internet explorer.

        //alert("came in ajax...hi common...");

        if (window.ActiveXObject)

        {

            try {

                xmlHttpRequest = new ActiveXObject("Msxml2.XMLHTTP");

            } catch (e) {

                try {

                    xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");

                } catch (E) {

                    xmlHttpRequest = false;

                }

            }

        }

        else if (window.XMLHttpRequest) //Check if Browser is not Internet explorer.

        {

            if (!xmlHttpRequest && typeof XMLHttpRequest != 'undefined') {

                try {

                    xmlHttpRequest = new XMLHttpRequest();

                } catch (e) {

                    xmlHttpRequest = false;

                }

            }

        }

        if (!xmlHttpRequest && window.createRequest) {

            try {

                xmlHttpRequest = window.createRequest();

            } catch (e) {

                xmlHttpRequest = false;

            }

        }

        reportStatus = false;

        // alert("url" + "===" +url);

        var idx = url.indexOf("=");

        str = url.substring(idx + 1, url.length);

        xmlHttpRequest.open("POST", url, true);

        xmlHttpRequest.onreadystatechange = populateAjaxSearchCriteria;

        xmlHttpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

        xmlHttpRequest.send(null);


    }

}


function populateAjaxSearchCriteria()

{

    var htmldata;

    document.getElementById("divWelcome").innerHTML = "";

    var divReportCriteriaV = document.getElementById("divReportCriteria");

    divReportCriteriaV.innerHTML = "<p>" + "Loading ...." + "</p>";


    if (xmlHttpRequest != null)

    {

        if (xmlHttpRequest.readyState == 4)

        {
            // alert();
            if (xmlHttpRequest.status == 200)

            {

                htmldata = xmlHttpRequest.responseText;

                // alert(htmldata);

                var out = trimString(htmldata);
                if (out == "SESSION_OUT") {
                    window.location.href = "../index.jsp";
                } else if (divReportCriteriaV != null)
                {

                    // alert(str);
                    if (str == "Wallet Summary Report" || str == "Wallet Topup Report") {
                        htmldata = htmldata.replace("<option value=\"all\">All</option>", "<option value=\"-1\">--Select--</option>");
                    }

                    document.getElementById("divWelcome").innerHTML = "";

                    document.getElementById("gt").innerHTML = "<b>&gt;</b>&nbsp;";

                    document.getElementById("test").innerHTML = strHead;

                    divReportCriteriaV.innerHTML = htmldata;

                }

                else

                {//alert("Div ReportCriteria not populated..");

                    divReportCriteriaV.innerHTML = "";

                    document.getElementById("divWelcome").innerHTML = "<font color='red'>" + "Report Criteria not populated." + "</font>"


                }


            }

            else if (xmlHttpRequest.status == 404)

            {

                //alert("URL doesn't exist!")

                divReportCriteriaV.innerHTML = "";

                document.getElementById("divWelcome").innerHTML = "<font color='red'>" + "URL doesn't exist!." + "</font>"


            }

            else

            {

                //alert("Status is "+xmlHttpRequest.status)

                divReportCriteriaV.innerHTML = "";

                document.getElementById("divWelcome").innerHTML = "<font color='red'>" + "Internal server error." + "</font>"


            }

        }

    }

    else

    {

        //alert('some problem in Ajax call');

        divReportCriteriaV.innerHTML = "";

        document.getElementById("divWelcome").innerHTML = "<font color='red'>" + "Internal server error." + "</font>"


    }

    reportStatus = true;

}


function validateDate(sdate) {

    //var regexp=/^((\d{2}(([02468][048])|([13579][26]))[\-]?((((0?[13578])|(1[02]))[\-]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\-]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\-]?((0?[1-9])|([1-2][0-9])))))|(\d{2}(([02468][1235679])|([13579][01345789]))[\-]?((((0?[13578])|(1[02]))[\-]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\-]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\-]?((0?[1-9])|(1[0-9])|(2[0-8]))))))?$/;

    //var regexp = /^([1-3][0-9][0-9][0-9]-((0[1-9])|(1[0-2]))-((0[1-9])|([1-2][0-9])|([3][0-1])))?$/;

    var regexp = /^((0[1-9])|([1-2][0-9])|([3][0-1]))[/]((0[1-9])|(1[0-2]))[/][1-3][0-9][0-9][0-9] ((0[0-9])|(1[0-9])|(2[0-3])):[0-5][0-9]:[0-5][0-9]?$/;

    if ((sdate.match(regexp)) && (sdate != "")) {

        return true;

    } else {

        return false;

    }

}


var stime;

var etime;


function after(sdate, edate) {

    //alert("Kul");


    var sdatePart = sdate.split("/");

    var edatePart = edate.split("/");


    var startyear = sdatePart[2];

    var startmonth = sdatePart[1];

    var startday = sdatePart[0];


    var endyear = edatePart[2];

    var endmonth = edatePart[1];

    var endday = edatePart[0];


    if ((startyear == endyear) && (startmonth == endmonth) && (startday == endday)) {

        //alert("bhushan");

        var stimePart = stime.split(":");

        var etimePart = etime.split(":");


        var stimehh = stimePart[0];

        var stimemm = stimePart[1];

        var stimess = stimePart[2];


        var etimehh = etimePart[0];

        var etimemm = etimePart[1];

        var etimess = etimePart[2];


        if (stimehh == etimehh && stimemm == etimemm && stimess == etimess) {

            document.getElementById("startdate1").innerHTML = "<font color='red'>" + "Start Date and End Date are identical." + "</font>"

            //alert("Start time and End time is equal.");

            return false;

        } else {

            if (stimehh <= etimehh) {

                if (stimemm <= etimemm) {

                    if (stimess <= etimess) {

                        return true;

                    } else {

                        document.getElementById("startdate1").innerHTML = "<font color='red'>" + "Time is not correct." + "</font>"

                        //alert("Start time is not correct.");

                        return false;

                    }

                } else {

                    document.getElementById("startdate1").innerHTML = "<font color='red'>" + "Time is not correct." + "</font>"

                    //alert("Start time is not correct.");

                    return false;

                }

            } else {

                document.getElementById("startdate1").innerHTML = "<font color='red'>" + "Time is not correct." + "</font>"

                //alert("Start time is not correct.");

                return false;

            }

        }

    } else {

        //alert("srivastava");

        if (endyear > startyear) {

            return true;

        } else if (endyear == startyear) {

            if (endmonth > startmonth) {

                return true;

            } else if (endmonth == startmonth) {

                if (endday > startday) {

                    return true;

                } else {

                    document.getElementById("enddate1").innerHTML = "<font color='red'>" + "End date should be greater than Start date." + "</font>"

                    //alert("End date should be greater than start date.");

                    return false;

                }


            } else {

                document.getElementById("enddate1").innerHTML = "<font color='red'>" + "End date should be greater than Start date." + "</font>"

                //alert("End date should be greater than start date.");

                return false;

            }

        } else {

            document.getElementById("enddate1").innerHTML = "<font color='red'>" + "End date should be greater than Start date." + "</font>"

            //alert("End date should be greater than start date.");

            return false;

        }

    }

}

var dailystocksummarystatus = true;

function searchData(reportname)

{
    // alert("came......................"+reportname);
    var dateStr = new Array(2);

    var dateSplit0 = null;

    var dateSplit1 = null;

    var slashSplit0 = null;

    var slashSplit1 = null;

    var sdate = "";

    var edate = "";

    var parameters = "";

    var val = "";

    var newFormat = "";

    var stat = true;

    var status = false;

    var status0 = false;

    var status1 = false;

    var param_array = new Array();

    var cnt = 0;
    var mobilenumber;

    var elementsForms = document.getElementsByTagName("form");

    if (document.getElementById("tfeild") != null)

        document.getElementById("tfeild").innerHTML = "";

    for (var intCounter = 0; intCounter < elementsForms.length; intCounter++)

    {

        currentForm = elementsForms[intCounter];


        elementsInputs = currentForm.getElementsByTagName("input");


        for (var inputs = 0; inputs < elementsInputs.length; inputs++)

        {


            var input = elementsInputs[inputs];


            if (input.type == 'text')

            {

                val = input.value;

                if (val == "") {

                    if (input.name == "mobileno") {
                        param_array[cnt] = "all";
                        stat = true;
                        cnt++;
                    } else if (reportname == "pintransactiondetail") {
                        param_array[cnt] = "all";
                        stat = true;
                        cnt++;
                    } else {
                        parameters += input.name + "=" + val + "&";
                        param_array[cnt] = "all";
                        //mobilenumber
                        stat = true;
                        cnt++;
                    }

                } else {

                    parameters += input.name + "=" + val + "&";

                    if (input.name == "startdate") {
                        document.getElementById("startdate1").innerHTML = "";

                        dateStr[0] = input.value;
                        // alert(reportname);
                        if (reportname == "agentbalancebydate" || reportname == "walletstatusreport" || reportname == "agentwisesummary" || reportname == "denominationwisesumm" || reportname == "marginonthetopupcard")
                            newFormat = changeFormat(val);
                        else if (reportname == "topupreport")
                            newFormat = changeFormatDate(val);
                        else
                            newFormat = val;

                        param_array[cnt] = newFormat;

                        cnt++;

                        status0 = validateDate(dateStr[0]);

                        if (status0 == true) {

                            dateSplit0 = dateStr[0].split(" ");

                            //slashSplit0 = dateSplit0[0].split("/");

                        } else {

                            document.getElementById("startdate1").innerHTML = "<font color='red'>" + "Start Date is not correct." + "</font>";

                            return false;

                        }

                        sdate = dateSplit0[0];

                        stime = dateSplit0[1];

                        //alert(sdate);

                    } else if (input.name == "enddate") {
                        document.getElementById("enddate1").innerHTML = "";
                        //alert(input.value)
                        dateStr[1] = input.value;
                        if (reportname == "agentbalancebydate" || reportname == "walletstatusreport" || reportname == "agentwisesummary" || reportname == "denominationwisesumm" || reportname == "marginonthetopupcard")
                            newFormat = changeFormat(val);
                        else if (reportname == "topupreport")
                            newFormat = changeFormatDate(val);
                        else
                            newFormat = val;
                        param_array[cnt] = newFormat;
                        cnt++;

                        status1 = validateDate(dateStr[1]);

                        if (status1 == true) {

                            dateSplit1 = dateStr[1].split(" ");

                            //slashSplit1 = dateSplit1[0].split("/");

                        } else {

                            document.getElementById("enddate1").innerHTML = "<font color='red'>" + "End Date is not correct." + "</font>";

                            return false;

                        }

                        edate = dateSplit1[0];

                        etime = dateSplit1[1];

                        //alert(edate);

                    } else if (input.name != "mobileno" || input.name != "pgname" || input.name != "enddate" || input.name != "startdate")
                    {
                        param_array[cnt] = input.value;
                        stat = true;
                        cnt++;
                    }
                    if (input.name == "mobileno") {
                        //mobilenumber=input.value;
                        // if (reportname == "pintransactiondetail") {
                        param_array[cnt] = input.value;
                        stat = true;
                        cnt++;
                        //  }

                    }
                    if (input.name == "pgname") {
                        var mobilenumber1 = input.value;
                        param_array[cnt] = mobilenumber1;
                        cnt++;
                    }

                }

            }

            ///*

            /*if (input.type == 'checkbox') {

             if (input.checked) {

             input.value = "Y";

             parameters += input.name + "=" + input.value + "&";

             param_array[cnt] = input.value;

             //alert(input.value);

             }

             else {

             input.value = "N";

             parameters += input.name + "=" + input.value + "&";

             param_array[cnt] = input.value;

             //alert(input.value);

             }

             }*/

            if (input.type == 'checkbox') {
                if (input.checked) {


                    if (input.name == 'downLoadcsvonly')
                    {
                        // input.value="Y";
                        downloadvalue = "Y";
                    }

                }
            }

            if (input.type == 'hidden') {//get default branch code if branchcode is not available

                if (input.name == "prefix") {

                    var code = input.value;

                    if (code == "" || code == "null")

                        code = "all";

                    param_array[cnt] = code;

                    cnt++;

                }

            }

            //*/


        }


        if (status0 == true && status1 == true && stat == true) {



            //alert("1");

            status = after(sdate, edate);

        }

        var selectCount = 0;

        elementsInputs = currentForm.getElementsByTagName("select");


        for (var inputs = 0; inputs < elementsInputs.length; inputs++)

        {

            var input = elementsInputs[inputs];

            if (reportname == "walletsummaryreport" || reportname == "walletsummary" || reportname == "wallettopupreport") {
                document.getElementById("combo1").innerHTML = "";
            }

            if (input.value != "") {
                if (reportname == "walletsummaryreport" || reportname == "wallettopupreport") {
                    if (input.value == "-1") {
                        document.getElementById("combo1").innerHTML = "<p class='error_color'>" + "Agent Code is Missing." + "</p>";
                        status = false;
                    }
                } else if (reportname == "pinstocksummary") {
                    // alert("alert");
                    if (input.value == "-11") {
                        document.getElementById("stocktype").innerHTML = "<p class='error_color'>" + "Agent Code is Missing." + "</p>";
                        dailystocksummarystatus = false;
                    } else
                        dailystocksummarystatus = true;
                }
                param_array[cnt] = input.value;
                cnt++;
            }
            selectCount++;
        }
    }

    // alert("params : "+param_array);
    //setting status for wallet report
    if (reportname == "currentwalletbalance" || reportname == "statementoffranchiseedcommission" || reportname == "statementofcommissionperfranchisee"
            || reportname == "statementofprovisionaccounts" || reportname == "statementofpendingfiles" || reportname == "frbranchreport"
            || reportname == "frcommissionsummary" || reportname == "frcommissiondetail" || reportname == "sendmoneyrollback" || reportname == "agentbalance"
            || reportname == "unusedagent" || reportname == "pendingtransfer" || reportname == "agentprofile" || reportname == "walletbalance" || reportname == "wallettopuppinmargin") {
        status = true;
    } else if (reportname == "pinstocksummary" && dailystocksummarystatus || reportname == "statementofunusedaccount") {
        status = true;
        param_array[cnt] = getCurrentDate1();
    } else if (reportname == "agentbalancebydate") {
        var combo1 = document.getElementById("type");
        var type = combo1.options[combo1.selectedIndex].value;
        // alert(type);

        if (param_array.length > 3) {
            if (type == "2") {
                removeByIndex(param_array, 3);
                removeByIndex(param_array, 2);
            } else if (type == "3") {
                removeByIndex(param_array, 2);
                removeByIndex(param_array, 3);
            } else if (type == "-1" || !agentBalComboFlag) {
                document.getElementById("type1").innerHTML = "<p class='error_color'>" + "Type is Missing." + "</p>";
                status = false;
            }
        }
    }

    // alert("params2 : "+param_array);


    if (status) {
        window.open("../reportServlet?report=" + reportname + "&parameters=" + param_array, '', 'resizable=yes, menubar=no,scrollbars=yes,toolbar=no, height=1000, width=1000 ');//,fullscreen=yes
    }

}

function changeFormat(val) {

    var myArr = new Array();

    var myArr1 = new Array();

    myArr = val.split(' ');

    var myArr1 = myArr[0].split('/');

    var newDt = myArr1[2] + "-" + myArr1[1] + "-" + myArr1[0];

    return newDt;

}


function changeFormatDate(val) {

    var myArr = new Array();

    var myArr1 = new Array();

    myArr = val.split(' ');

    var myArr1 = myArr[0].split('/');

    var newDt = myArr1[2] + "-" + myArr1[1] + "-" + myArr1[0] + " " + myArr[1]

    return newDt;

}

function trimString(str) {
    var st = str.replace(/^\s+/g, '').replace(/\s+$/g, '');
    return st;
}

//######################olrreport.jsp######################################


function generateExcel() {

    document.report.action = "CsvSevlet";

    document.report.method = "post";

    document.report.submit();

}

function callDiv() {

    var divReport = document.getElementById("divReport");

    //divReport.innerHTML="Records are above 500 (please download the CSV to see all).";

    divReport.innerHTML = "<p class='max_rows'>" + "Records are above 500 (please download the CSV to see all)." + "</p>";

}

function callDiv1() {

    var noRec = document.getElementById("noRec");

    document.getElementById("headerId").style.display = "none";

    document.getElementById("recCount").style.display = "none";

    document.getElementById("a1").style.display = "none";

    document.getElementById("a2").style.display = "none";

    noRec.innerHTML = "<p class='max_rows'>" + "No records Found" + "</p>";

}

function serverError() {
    var noRec = document.getElementById("noRec");

    document.getElementById("headerId").style.display = "none";

    document.getElementById("recCount").style.display = "none";

    document.getElementById("a1").style.display = "none";

    document.getElementById("a2").style.display = "none";

    noRec.innerHTML = "<p class='max_rows'>" + "Internal Server Error" + "</p>";

}


//######################leftpanel.jsp######################################

//function submitForm(reportkey) {
//
//  var url="../SearchCriteriaServlet?report="+reportkey;

//  ajaxCallForSearchCriteria(url);

//  }

//  function reportHome(){

//		 window.location.href="index.jsp";

// }



//#######################index.jsp######################################


function onKeyDown() {



    // current pressed key


    var pressedKey = String.fromCharCode(event.keyCode).toLowerCase();

    if (event.ctrlKey && (pressedKey == "c" ||


                          pressedKey == "v")) {



        // disable key press porcessing


        event.returnValue = false;


    }

}

//####################################check session######################################
var sreq;

function checkSession1(reportname)
{
    //Check if Browser is Internet explorer.
    //alert("came in ajax...hi common...");
    reportkey = reportname;
    if (window.ActiveXObject)
    {
        try {
            sreq = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (e) {
            try {
                sreq = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (E) {
                sreq = false;
            }
        }
    }
    else if (window.XMLHttpRequest) //Check if Browser is not Internet explorer.
    {
        if (!sreq && typeof XMLHttpRequest != 'undefined') {
            try {
                sreq = new XMLHttpRequest();
            } catch (e) {
                sreq = false;
            }
        }
    }
    if (!sreq && window.createRequest) {
        try {
            sreq = window.createRequest();
        } catch (e) {
            sreq = false;
        }
    }

    var url = "CheckSession";
    sreq.open("POST", url, true);
    sreq.onreadystatechange = sessionCheckResponse;
    sreq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    sreq.send(null);

}


dailystocksummarystatus = true;
var olrtranstatus = true;
function checkSession(reportname, sitename)
{
    //alert("2222222")
    //document.getElementById("combo1").innerHTML="";
    //checkSession();
    var dateStr = new Array(2);
    var dateSplit0 = null;
    var dateSplit1 = null;
    var slashSplit0 = null;
    var slashSplit1 = null;
    var sdate = "";
    var edate = "";
    var parameters = "";
    var val = "";
    var newFormat = "";
    var stat = true;
    var status = false;
    var status0 = false;
    var status1 = false;
    var param_array = new Array();
    var criteria_array = new Array();
    var cnt = 0;
    var vend = true;
    var agen = true;
    var mobilenumber = "";
    var bulkvalue = "";
    var checkval = "";
    var status_fordate = false;
    var elementsForms = document.getElementsByTagName("form");
    //alert("came");
    var downloadvalue = "N";
    var subscriber = "nothidden";
    var rewardsreedsubs = true;
    var rewardsreedvend = true;
    var hiddenvendorval = "";
    var hiddenvendorflag = false;
    var businessday = "N";
    var flagremitestatus = false;
    var flagremitetranstp = false;
    var status = false;
    var pinstatus = false;
    var salesmanrptflag = false;
    var Salesreport = 'N';
    if (sitename == "OLR")
    {
        if (document.getElementById("tfeild") != null)
            document.getElementById("tfeild").innerHTML = "";
        for (var intCounter = 0; intCounter < elementsForms.length; intCounter++)
        {
            currentForm = elementsForms[intCounter];
            elementsInputs = currentForm.getElementsByTagName("input");
            for (var inputs = 0; inputs < elementsInputs.length; inputs++)
            {

                var input = elementsInputs[inputs];
                //document.getElementById("startdate1").innerHTML="";
                //document.getElementById("enddate1").innerHTML="";

                if (input.type == 'text')
                {
                    val = input.value;
                    //alert(input.name)
                    if (val == "") {
                        if (input.name == "mobileno" && reportname != "transactiondetail") {
                            //alert("mobile no :.....")
                            mobilenumber = "all";
                            stat = true;
                        } else if (reportname == "transactiondetail") {
                            param_array[cnt] = "all";
                            stat = true;
                            cnt++;
                        }


                        else {
                            parameters += input.name + "=" + val + "&";
                            param_array[cnt] = "all";
                            //mobilenumber
                            stat = true;
                            cnt++;
                        }
                    } else {
                        parameters += input.name + "=" + val + "&";
                        if (input.name == "startdate") {


                            document.getElementById("startdate1").innerHTML = "";
                            dateStr[0] = input.value;
                            if (reportname == "agentbalancebydate" || reportname == "walletstatusreport" || reportname == "agentsalessummary" || reportname == "denominationsummary" || reportname == "wallettopuppinmargin")
                            {

                                newFormat = changeFormat(val);
                            }
                            else if (reportname == "topupreport" || reportname == "transactionreport" || reportname == "transactionsummary")
                            {

                                newFormat = changeFormatDate(val);
                            }
                            else
                            {

                                newFormat = val;
                                //alert("start date in else part.....1111:"+val);
                            }
                            param_array[cnt] = newFormat;
                            cnt++;
                            status0 = validateDate(dateStr[0]);
                            if (status0 == true) {
                                dateSplit0 = dateStr[0].split(" ");
                                //slashSplit0 = dateSplit0[0].split("/");
                            } else {
                                document.getElementById("startdate1").innerHTML = "<font color='red'>" + "Start Date is not correct." + "</font>";
                                return false;
                            }
                            sdate = dateSplit0[0];
                            stime = dateSplit0[1];
                            //alert(sdate);
                        } else if (input.name == "enddate") {
                            document.getElementById("enddate1").innerHTML = "";
                            dateStr[1] = input.value;
                            if (reportname == "agentbalancebydate" || reportname == "walletstatusreport" || reportname == "agentsalessummary" || reportname == "denominationsummary" || reportname == "wallettopuppinmargin")
                                newFormat = changeFormat(val);
                            else if (reportname == "topupreport" || reportname == "transactionreport" || reportname == "transactionsummary")
                            {
                                newFormat = changeFormatDate(val);
                            }
                            else {

                                newFormat = val;
                                //alert("end date in else part.....2222:"+val);
                            }
                            param_array[cnt] = newFormat;
                            cnt++;
                            status1 = validateDate(dateStr[1]);
                            if (status1 == true) {
                                dateSplit1 = dateStr[1].split(" ");
                                //slashSplit1 = dateSplit1[0].split("/");
                            } else {
                                document.getElementById("enddate1").innerHTML = "<font color='red'>" + "End Date is not correct." + "</font>";
                                return false;
                            }
                            edate = dateSplit1[0];
                            etime = dateSplit1[1];
                            //alert(edate);
                        } else if (input.name == "mobileno") {
                            if (reportname == "transactiondetail") {

                                param_array[cnt] = input.value;
                                stat = true;
                                cnt++;
                            } else {
                                mobilenumber = input.value;
                            }

                        } else if (input.name == "pgname") {
                            var mobilenumber1 = input.value;
                            param_array[cnt] = mobilenumber1;
                            cnt++;
                        } else {
                            param_array[cnt] = input.value;
                            cnt++;
                        }
                    }
                }
                /*if (input.type == 'checkbox') {
                 if (input.checked) {
                 input.value = "Y";
                 parameters += input.name + "=" + input.value + "&";
                 param_array[cnt] = input.value;
                 }
                 else {
                 input.value = "N";
                 parameters += input.name + "=" + input.value + "&";
                 param_array[cnt] = input.value;
                 //alert(input.value);
                 }
                 }*/

                if (input.type == 'checkbox') {
                    if (input.checked) {


                        if (input.name == 'downloadcsvonly')
                        {
                            // input.value="Y";
                            downloadvalue = "Y";
                        }

                    }
                }
                if (input.type == 'hidden') {//get default branch code if branchcode is not available
                    if (input.name == "prefix") {
                        var code = input.value;
                        if (code == "" || code == "null")
                            code = "all";
                        param_array[cnt] = code;
                        cnt++;
                    }
                }
            }

            if (status0 == true && status1 == true && stat == true) {
                status = after(sdate, edate);
            }
            var selectCount = 0;
            elementsInputs = currentForm.getElementsByTagName("select");

            for (var inputs = 0; inputs < elementsInputs.length; inputs++)
            {

                var input = elementsInputs[inputs];
                //parameters+=input.name+"="+input.options[input.selectedIndex].value+"&";
                if (selectCount == 1 && reportname == "transactionreport") {
                    if (mobilenumber != "")
                    {
                        param_array[cnt] = mobilenumber;
                        cnt++;
                    }
                }
                if (input.value != "" && input.name != "type") {
                    //if (reportname == "walletsummaryreport" || reportname == "walletsummary") {
                    if ((reportname == "walletsummaryreport" || reportname == "walletsummary") && input.name == "agentcode") {
                        if (input.value == "-1") {
                            document.getElementById("combo1").innerHTML = "<font color='red'>" + "Agent Code is Missing." + "</font>";
                            status = false;
                            //	olrtranstatus = false;
                        }
                        else
                        {
                            param_array[cnt] = input.value;
                            cnt++;
                        }
                    }

                    else if (reportname == "pinstocksummary") {
                        // alert("came");
                        if (input.value == "-11") {
                            document.getElementById("stocktype").innerHTML = "<font color='red'>" + "Agent Code is Missing." + "</font>";
                            dailystocksummarystatus = false;
                        } else
                        {
                            dailystocksummarystatus = true;
                            param_array[cnt] = input.value;
                            cnt++;
                        }
                    }

                    //-----------------modified for olrtransactionreport----------
                    else if (reportname == "olrtransactionreport" || reportname == "pintransactionreport" || reportname == "tprtransactionreport" || reportname == "rechargesummaryreport" || reportname == "walletstatusreport" || reportname == "tprtransactionbsnlidea") {

                        if (input.value == "-1") {
                            document.getElementById("type1").innerHTML = "<font color='red'>" + "Please Select" + "</font>";
                            //  olrtranstatus = false;
                            // alert("olrtranstatusssss_false"+olrtranstatus)
                            status = false;
                        } else {
                            //olrtranstatus = true;
                            // alert("olrtranstatusssss_true"+olrtranstatus)
                            status = true;
                        }

                        //-----------------modified to send parameter dynamically of superagent/subagent/retailer--------------------------
                        if (input.name == "retailer")
                        {
                            if (document.getElementById("retailer").value != 'all')
                            {
                                cnt--;
                                param_array[cnt] = null;
                                cnt--;
                                param_array[cnt] = null;

                                param_array[cnt] = document.getElementById("retailer").value;
                                cnt++;
                            }
                            else
                            {
                                cnt--;
                                param_array[cnt] = null;
                                cnt--;
                                param_array[cnt] = null;

                                param_array[cnt] = document.getElementById("subagent").value;
                                cnt++;

                            }

                            if (reportname == "olrtransactionreport" || reportname == "pintransactionreport") {

                                removeByIndex(param_array, 6);
                            }
                            else if (reportname == "tprtransactionreport" || reportname == "rechargesummaryreport" || reportname == "walletstatusreport" || reportname == "tprtransactionbsnlidea" || reportname == "walletsummaryreport") {
                                removeByIndex(param_array, 3);
                            }
                            //alert("param_array_retailer   :"+param_array)
                        }

                        else if (input.name == "subagent" && document.getElementById("retailer") == null)
                        {
                            if (document.getElementById("subagent").value != 'all')
                            {
                                cnt--;
                                param_array[cnt] = null;
                                param_array[cnt] = document.getElementById("subagent").value;
                                cnt++;
                            }
                            else
                            {
                                cnt--;
                                param_array[cnt] = null;
                                param_array[cnt] = document.getElementById("superagentcode").value;
                                cnt++;

                            }
                        }

                        else
                        {
                            param_array[cnt] = input.value;
                            cnt++;
                        }

                        //--------------end-----------------------------

                    }
                    //------------------------------------------------------------
                    else
                    {
                        param_array[cnt] = input.value;
                        cnt++;
                    }
                }
                selectCount++;
            }


        }
        //  alert("==========param_array===:" + param_array);
        //var url="reportServlet?report="+reportname+"&"+parameters;

        //setting status for wallet report
        //alert(" key : "+reportname);
        if (reportname == "walletbalance" || reportname == "frcommissionsummary" || reportname == "frcommissiondetail" || reportname == "agentbalance" || reportname == "agentprofile" || reportname == "pendingtransfer" || reportname == "frbranchreport" || reportname == "sendmoneyrollback") {
            status = true;
        } else if (reportname == "pinstocksummary" && dailystocksummarystatus || reportname == "unusedagent") {
            status = true;
            param_array[cnt] = getCurrentDate1();
        } else if (reportname == "pinstatusreport") {
            status = true;
        }

        else if (reportname == "agentbalancebydate") {
            var combo1 = document.getElementById("type");
            var type = combo1.options[combo1.selectedIndex].value;
            if (param_array.length > 3) {
                if (type == "2") {
                    removeByIndex(param_array, 2);
                } else if (type == "3") {
                    removeByIndex(param_array, 3);
                } else if (type == "-1" || !agentBalComboFlag) {
                    document.getElementById("type1").innerHTML = "<font color='red'>" + "Type is Missing." + "</font>";
                    status = false;
                }
            }
        }

        //------------modified for olrtransactionreport

        //----------------
        //var dt=getCurrentDate1();
        if (reportname == "registrationreport")
            status = true;
        if (status) {
            //alert("status: "+status);
            window.open("../reportServlet?report=" + reportname + "&parameters=" + param_array + "&downloadvalue=" + downloadvalue, '', 'resizable=yes, menubar=no,scrollbars=yes,toolbar=no, height=1000, width=1000 ');//,fullscreen=yes
            //alert("came1 :: ");
            document.report.method = "post";
            //document.report.submit();
        }
    }


    else {


        if (document.getElementById("tfeild") != null)
        {
            //pinstatus=true;
            document.getElementById("tfeild").innerHTML = "";
        }
        for (var intCounter = 0; intCounter < elementsForms.length; intCounter++)
        {

            currentForm = elementsForms[intCounter];

            elementsInputs = currentForm.getElementsByTagName("input");

            for (var inputs = 0; inputs < elementsInputs.length; inputs++)
            {

                var input = elementsInputs[inputs];
                //document.getElementById("startdate1").innerHTML="";
                //document.getElementById("enddate1").innerHTML="";

                if (input.type == 'text')
                {


                    val = input.value;
                    //alert(input.name)
                    if (val == "") {


                        if (input.name == "mobileno" && reportname != "pintransactiondetail") {

                            mobilenumber = "all";
                            stat = true;

                        }
                        else if (reportname == "pintransactiondetail") {
                            param_array[cnt] = "all";
                            criteria_array[cnt] = "novalue";
                            stat = true;
                            cnt++;
                        }

                        else if ((reportname == "subscriberbankreport" || reportname == "walletsummary") && sitename == "EU") {

                            document.getElementById("tfeild").innerHTML = "<font color='red'>" + "Please Enter Agent" + "</font>";
                            stat = false;
                            //cnt++;
                        }
                        else if ((reportname == "orderdetails") && sitename == "DIGICO") {

                            document.getElementById("tfeild").innerHTML = "<font color='red'>" + "Please Enter Order Number" + "</font>";
                            stat = false;
                            //cnt++;
                        }

                        else if ((( reportname == "pinstockmovement") || ( reportname == "merchantorder") || ( reportname == "pinsales") || ( reportname == "terminalstock") || ( reportname == "activeterminals") || ( reportname == "terminallastsync") ) && sitename == "DIGICO") {

                            document.getElementById("tfeild").innerHTML = "<font color='red'>" + "Please Enter Merchant" + "</font>";
                            //pinstatus = false;
                            stat = false;
                            status = false;
                            //cnt++;
                        }
                        /*else if (( reportname == "merchantorder" ) && sitename == "DIGICO") {

                         document.getElementById("tfeild").innerHTML = "<font color='red'>" + "Please Enter Merchant" + "</font>";
                         stat = false;
                         //cnt++;
                         }*/
                        else {
                            parameters += input.name + "=" + val + "&";
                            param_array[cnt] = "all";
                            criteria_array[cnt] = "novalue";
                            //mobilenumber
                            stat = true;
                            cnt++;
                        }
                    } else {

                        parameters += input.name + "=" + val + "&";
                        if (input.name == "startdate") {
                            document.getElementById("startdate1").innerHTML = "";
                            dateStr[0] = input.value;
                            if (reportname == "agentbalancebydate" || reportname == "walletstatusreport" || reportname == "agentwisesummary" || reportname == "denominationwisesumm" || reportname == "wallettopuppinmargin" || reportname == "denominationwisereport")
                                newFormat = changeFormat(val);
                            else if (reportname == "topupreport")
                                newFormat = changeFormatDate(val);
                            //else if (reportname == "orderdetails" && sitename == "DIGICO")
                            // newFormat = changeFormatbackslash(val);
                            else
                                newFormat = val;

                            param_array[cnt] = newFormat;
                            criteria_array[cnt] = newFormat;
                            cnt++;
                            status0 = validateDate(dateStr[0]);

                            if (status0 == true) {
                                dateSplit0 = dateStr[0].split(" ");
                                //slashSplit0 = dateSplit0[0].split("/");
                            } else {
                                document.getElementById("startdate1").innerHTML = "<font color='red'>" + "Start Date is not correct." + "</font>";
                                return false;
                            }
                            sdate = dateSplit0[0];
                            stime = dateSplit0[1];
                            //alert(sdate);
                        } else if (input.name == "enddate") {
                            document.getElementById("enddate1").innerHTML = "";
                            dateStr[1] = input.value;
                            if (reportname == "agentbalancebydate" || reportname == "walletstatusreport" || reportname == "agentwisesummary" || reportname == "denominationwisesumm" || reportname == "wallettopuppinmargin" || reportname == "denominationwisereport")
                                newFormat = changeFormat(val);
                            else if (reportname == "topupreport")
                                newFormat = changeFormatDate(val);
                            //	else if (reportname == "orderdetails" && sitename == "DIGICO")
                            //    newFormat = changeFormatbackslash(val);
                            else
                                newFormat = val;
                            param_array[cnt] = newFormat;
                            criteria_array[cnt] = newFormat;
                            cnt++;
                            status1 = validateDate(dateStr[1]);
                            if (status1 == true) {
                                dateSplit1 = dateStr[1].split(" ");
                                //slashSplit1 = dateSplit1[0].split("/");
                            } else {
                                document.getElementById("enddate1").innerHTML = "<font color='red'>" + "End Date is not correct." + "</font>";
                                return false;
                            }
                            edate = dateSplit1[0];
                            etime = dateSplit1[1];
                            //alert(edate);
                        }


                        else if (input.name == "expirydate") {
                            //alert("expirydate"+input.value);
                            dateStr[1] = input.value;
                            document.getElementById("expirydate1").innerHTML = "";
                            criteria_array[cnt] = input.value;
                            param_array[cnt] = input.value;
                            cnt++;
                            status1 = validateDate(dateStr[1]);
                            if (status1 == true) {
                                dateSplit1 = dateStr[1].split(" ");
                                //slashSplit1 = dateSplit1[0].split("/");
                            } else {
                                document.getElementById("expirydate1").innerHTML = "<font color='red'>" + "Expiry Date is not correct." + "</font>";
                                return false;
                            }
                            edate = dateSplit1[0];
                            etime = dateSplit1[1];
                            //alert(edate);
                        }


                        else if (input.name == "mobileno") {

                            if (reportname == "pintransactiondetail") {
                                param_array[cnt] = input.value;
                                criteria_array[cnt] = input.value;
                                stat = true;
                                cnt++;
                            } else {
                                mobilenumber = input.value;
                            }

                        } else if (input.name == "pgname") {
                            var mobilenumber1 = input.value;
                            param_array[cnt] = mobilenumber1;
                            criteria_array[cnt] = mobilenumber1;
                            cnt++;
                        }


                        else if (reportname == "orderdetails" && sitename == "DIGICO") {
                            param_array[cnt] = input.value;
                            criteria_array[cnt] = input.value;
                            status = true;
                            cnt++;
                        }

                        else if (reportname == "pinstockmovement" && sitename == "DIGICO") {
                            param_array[cnt] = input.value;
                            criteria_array[cnt] = input.value;
                            status = true;
                            cnt++;
                        }
                        else if (reportname == "terminallastsync" && sitename == "DIGICO") {
                            param_array[cnt] = input.value;
                            criteria_array[cnt] = input.value;
                            status = true;
                            cnt++;
                        }
                        else if (reportname == "terminalstock" && sitename == "DIGICO") {
                            param_array[cnt] = input.value;
                            criteria_array[cnt] = input.value;
                            status = true;
                            cnt++;
                        }
                        else if (reportname == "activeterminals" && sitename == "DIGICO") {
                            param_array[cnt] = input.value;
                            criteria_array[cnt] = input.value;
                            status = true;
                            cnt++;
                        }

                        else {
                            param_array[cnt] = input.value;
                            criteria_array[cnt] = input.value;
                            cnt++;
                        }
                    }
                }
                ///*
                /* if (input.type == 'checkbox') {
                 if (input.checked) {
                 input.value = "Y";
                 bulkvalue=input.value;
                 checkval="bulk";
                 parameters += input.name + "=" + input.value + "&";
                 // param_array[cnt] = input.value;
                 //alert(input.value);
                 }
                 else {
                 input.value = "N";
                 bulkvalue=input.value;  //edited
                 checkval="nonbulk";
                 parameters += input.name + "=" + input.value + "&";
                 //param_array[cnt] = input.value;
                 //alert(input.value);
                 }
                 }*/

                if (input.type == 'checkbox') {

                    if (input.checked) {

                        if (input.name == 'bulk')
                        {
                            // input.value="Y";
                            bulkvalue = "Y";
                            checkval = "bulk";
                        }
                        else if (input.name == 'downloadcsvonly')
                        {
                            // input.value="Y";
                            downloadvalue = "Y";
                        }
                        else if (input.name = 'businessday')
                        {
                            businessday = 'Y';

                        }
                        if (input.name = 'salesman')
                        {
                            //alert("44444")
                            //alert("check_yes")
                            Salesreport = 'Y';
                        }
                        //edited

                        //edited
                        parameters += input.name + "=" + input.value + "&";
                        //param_array[cnt]=input.value;//edited
                        //alert(input.value);
                    }
                    else {
                        if (input.name == 'bulk')
                        {
                            // input.value="Y";
                            bulkvalue = "N";
                            checkval = "nonbulk";
                        }
                        else if (input.name == 'downloadcsvonly')
                        {
                            // input.value="Y";
                            downloadvalue = "N";
                        }
                        else if (input.name = 'businessday')
                        {
                            businessday = 'N';

                        }

                        if (input.name = 'salesman')
                        {
                            //alert("44444")
                            //alert("check_yes")
                            Salesreport = 'N';
                        }
                        // edited
                        parameters += input.name + "=" + input.value + "&";
                        //param_array[cnt]=input.value;// edited
                        //alert(input.value);
                    }
                }

                if (input.type == 'hidden') {//get default branch code if branchcode is not available

                    if (input.name == "prefix") {

                        var code = input.value;
                        if (code == "" || code == "null")
                            code = "all";
                        param_array[cnt] = code;
                        criteria_array[cnt] = code;
                        cnt++;
                    }

                    /*else if(input.name=="subscriber" && reportname == "pointsearnedreport")
                     {
                     //   alert("hidden2222222")
                     agen=false;
                     subscriber="hidden";
                     param_array[cnt]=input.value;
                     criteria_array[cnt]=input.value;
                     cnt++;
                     }*/

                    else if (input.name == "subscriber" && (reportname == "rewardsredeemedreport" || reportname == "rewardsunclaimedreport" || reportname == "walletadjustmentreport" || reportname == "pointsearnedreport"))
                    {
                        // alert("hidden_subs"+input.value);
                        // alert("hidden33333333")
                        param_array[cnt] = input.value;
                        criteria_array[cnt] = input.value;
                        cnt++;
                    }
                    else if (input.name == "vendor" && (reportname == "rewardsredeemedreport" || reportname == "rewardsunclaimedreport" || reportname == "walletadjustmentreport" || reportname == "pointsearnedreport"))
                    {
                        // alert("hidden44444444")
                        hiddenvendorval = input.value;
                        hiddenvendorflag = true;
                        //  param_array[cnt]=input.value;
                        //     criteria_array[cnt]=input.value;
                        //cnt++;
                    }

                }
                //*/

            }

            if (status0 == true && status1 == true && stat == true) {
                status = after(sdate, edate);
                status_fordate = status;
            }
            var selectCount = 0;
            elementsInputs = currentForm.getElementsByTagName("select");
            for (var inputs = 0; inputs < elementsInputs.length; inputs++)
            {
                // alert("select"+param_array)
                var input = elementsInputs[inputs];
                //parameters+=input.name+"="+input.options[input.selectedIndex].value+"&";
                if (selectCount == 1 && reportname == "transactionreport") {
                    if (mobilenumber != "")
                    {
                        param_array[cnt] = mobilenumber;
                        criteria_array[cnt] = mobilenumber;
                        cnt++;
                    }
                }
                //alert(input.name)
                if (input.value != "") {
                    if (reportname == "walletsummaryreport" || reportname == "walletsummary") {
                        if (input.value == "-1") {
                            document.getElementById("combo1").innerHTML = "<font color='red'>" + "Agent Code is Missing." + "</font>";
                            status = false;
                        }
                    } else if (reportname == "pinstocksummary" && sitename != "DIGICO") {
                        // alert("came");
                        if (input.value == "-11") {
                            document.getElementById("stocktype").innerHTML = "<font color='red'>" + "Agent Code is Missing." + "</font>";
                            dailystocksummarystatus = false;
                        } else
                            dailystocksummarystatus = true;
                    }
                    else if (reportname == "agentstatusreport" && sitename == "AXIOM") {
                        // alert("came");
                        status = true;
                    }


                    else  if (reportname == "aggregatedwalletbalancereport" && sitename == "AXIOM")
                    {
                        if (input.value == "-1")
                        {
                            document.getElementById("type1").innerHTML = "<font color='red'>" + "Please Select Agent Code" + "</font>";
                            status = false;
                        }
                        else
                        {
                            //document.getElementById("type1").innerHTML="";
                            status = true;
                        }


                    }

                    else  if ((reportname == "businessdaytransactions" || reportname == "businessdayshiftreport") && sitename == "AXIOM")
                    {
                        //  alert(input.name);
                        if (input.name == "agentcode")
                        {
                            if (input.value == "-1")
                            {
                                document.getElementById("type1").innerHTML = "<font color='red'>" + "Please Select" + "</font>";
                                status = false;
                            }
                            else
                            {
                                //document.getElementById("type1").innerHTML="";
                                status = true;
                            }
                        }

                    }

                    else  if (reportname == "agentbalancebydate" && sitename == "DIGICO")
                    {

                        if (input.value == "-1")
                        {

                            document.getElementById("type1").innerHTML = "<font color='red'>" + "Please Select" + "</font>";
                            status = false;
                        }
                        else
                        {

                            status = true;
                        }


                    }
                    else  if (reportname == "remittancestatus" && sitename == "ZEMEN")
                    {

                        if (input.value == "-1" && input.name == "transactiontype")
                        {

                            flagremitestatus = false;
                            document.getElementById("typet").innerHTML = "<font color='red'>" + "Please Select" + "</font>";
                            document.getElementById("types").innerHTML = "";
                            status = false;
                        }
                        else if (input.value != "-1" && input.name == "transactiontype")
                        {

                            flagremitestatus = true;
                        }


                        if (flagremitestatus)
                        {

                            if (input.value == "-1" && input.name == "status")
                            {

                                document.getElementById("types").innerHTML = "<font color='red'>" + "Please Select" + "</font>";
                                flagremitetranstp = false;
                                status = false;
                            }
                            else if (input.value != "-1" && input.name == "status")
                            {
                                flagremitetranstp = true;

                            }

                        }

                    }


                    else if ((reportname == "rewardsredeemedreport" || reportname == "walletadjustmentreport" || reportname == "pointsearnedreport") && input.value == "-1")
                    {
                        //  alert("input"+input.name);
                        if (input.name == "vendor")
                        {
                            rewardsreedvend = false;
                        }
                        else if (input.name == "subscriber")
                        {
                            rewardsreedsubs = false;
                        }


                    }
                    else if (reportname == "rewardsunclaimedreport" || reportname == "walletadjustmentreport" || reportname == "pointsearnedreport")
                    {
                        //  alert("input"+input.name);
                        if (input.name == "vendor")
                        {
                            status = true;
                        }
                        else if (input.name == "subscriber")
                        {
                            status = true;
                        }


                    }

                    else if (reportname == "agentstatusreport" && sitename == "KNET")
                    {

                        if (input.value == "-1") {
                            document.getElementById("combo2status").innerHTML = "<font color='red'>" + "Please Select" + "</font>";
                            status = false;
                        }
                        else
                        {
                            document.getElementById("combo2status").innerHTML = "";
                            status = true;
                        }
                    }



                    /* else if (reportname == "salesmansalesreport" ) {

                     if (input.value == "-1") {
                     document.getElementById("type1").innerHTML = "<font color='red'>" + "Please Select" + "</font>";

                     status = false;
                     // alert("olrtranstatusssss_false"+olrtranstatus)
                     } else if (input.value != "-1") {

                     status = true;
                     // alert("olrtranstatusssss_true"+olrtranstatus)
                     }

                     }*/
                    /*else if (reportname == "pinstockstatus" && sitename == "DIGICO") {
                     // alert("pinstocksummary")
                     if (input.value == "-1") {
                     document.getElementById("typem").innerHTML = "<font color='red'>" + "Please Select" + "</font>";
                     // alert("fasle")
                     status = false;
                     } else if (input.value != "-1") {

                     status = true;
                     }
                     }*/

                    else if (( reportname == "evdata" ) && sitename == "DIGICO") {

                        if (input.value == "-1") {
                            document.getElementById("type1").innerHTML = "<font color='red'>" + "Please Select" + "</font>";

                            status = false;
                            salesmanrptflag = false;
                            // alert("olrtranstatusssss_false"+olrtranstatus)
                        } else if (input.value != "-1") {

                            // status = true;
                            salesmanrptflag = true;
                            // alert("olrtranstatusssss_true"+olrtranstatus)
                        }
                        if (salesmanrptflag)
                        {
                            // alert("inside_salesmanrptflag")
                            if (input.name == "agentcode")
                            {
                                // alert("inside_salesmanrptflag_agentcode")
                                status = true;
                            }
                            else
                            {
                                //  alert("inside_salesmanrptflag_agentcode_not")
                                status = false;
                            }
                        }

                    }


                    else if (reportname == "registration" && sitename == "DIGICO") {

                        //  alert("zzzzzzz")
                        if (input.value == "-1") {
                            document.getElementById("type1").innerHTML = "<font color='red'>" + "Please Select" + "</font>";

                            status = false;
                            // salesmanrptflag=false;
                            // alert("olrtranstatusssss_false"+olrtranstatus)
                        } else if (input.value != "-1") {

                            status = true;
                            //salesmanrptflag=true;
                            // alert("olrtranstatusssss_true"+olrtranstatus)
                        }
                    }

                    else if (reportname == "salesman" || reportname == "regionsales" || reportname == "branchsales") {
                        //alert("start salsman")

                        if (document.getElementById("level").value == '-1') {
                            document.getElementById("type1").innerHTML = "<font color='red'>" + "Please Select Level" + "</font>";
                            // alert("not level")
                            status = false;
                            salesmanrptflag = false;
                            // alert("olrtranstatusssss_false"+olrtranstatus)
                        }
                        else if (document.getElementById("agentcode").value == '-1') {
                            document.getElementById("type2").innerHTML = "<font color='red'>" + "Please Select Agent" + "</font>";
                            //alert("not agent")
                            status = false;
                            salesmanrptflag = false;
                            // alert("olrtranstatusssss_false"+olrtranstatus)
                        }

                        else if (document.getElementById("agentcode").value != '-1') {

                            status = true;
                            salesmanrptflag = true;
                            // alert("olrtranstatusssss_true")
                        }
                        else if (document.getElementById("level").value != '-1') {

                            status = true;
                            salesmanrptflag = true;
                            // alert("olrtranstatusssss_true")
                        }

                    }


                    /*else if(reportname == "pointsearnedreport" && input.value == "-1" ){

                     if(input.name=="vendor")
                     {
                     vend=false;
                     }
                     else if(input.name=="subscriber")
                     {
                     agen=false;
                     }
                     }*/
                    /*param_array[cnt] = input.value;
                     criteria_array[cnt]=input.options[input.selectedIndex].text;*/
                    if (reportname == "transactiondetailreport" && sitename == "AXIOM")
                    {
                        var val = input.value.split("$$$");
                        param_array[cnt] = val[0];
                        criteria_array[cnt] = input.options[input.selectedIndex].text;
                    }
                    else
                    {
                        param_array[cnt] = input.value;
                        criteria_array[cnt] = input.options[input.selectedIndex].text;
                    }
                    cnt++;
                }
                selectCount++;
            }


        }


        /* edited for checkbox value*/

        if (reportname == "transactiondetailreport" && sitename == "AXIOM")
        {
            if (bulkvalue == "Y")
            {

                /*cnt++;*/
                param_array[cnt] = checkval;
                criteria_array[cnt] = checkval;
                status = true;
            }
            else if (bulkvalue == "N")
            {

                //cnt++;
                param_array[cnt] = checkval;
                criteria_array[cnt] = checkval;
                status = true;
            }
            else
            {

                param_array[cnt] = "nonbulk";
                criteria_array[cnt] = "nonbulk"
                status = status_fordate;
            }
        }


        if (flagremitetranstp == true && flagremitetranstp == true)
        {
            status = true;
        }


        // end of check box value


        //var url="reportServlet?report="+reportname+"&"+parameters;

        //setting status for wallet report
        //alert(" key : "+reportname);
        if (reportname == "walletbalance" || reportname == "frcommissionsummary" || reportname == "frcommissiondetail" || reportname == "agentbalance" || reportname == "agentprofile" || reportname == "pendingtransfer" || reportname == "frbranchreport" || reportname == "sendmoneyrollback") {
            status = true;
        } else if (sitename != "DIGICO" && reportname == "pinstocksummary" && dailystocksummarystatus || reportname == "unusedagent") {
            status = true;
            param_array[cnt] = getCurrentDate1();
            criteria_array[cnt] = getCurrentDate1();
        } else if (reportname == "pinstatusreport") {
            status = true;
        }
        else if (reportname == "agentbalancebydate" && sitename != "DIGICO") {
            var combo1 = document.getElementById("type");
            var type = combo1.options[combo1.selectedIndex].value;
            //alert(type);
            if (param_array.length > 3) {
                if (type == "2") {
                    removeByIndex(param_array, 2);
                    removeByIndex(criteria_array, 2);
                } else if (type == "3") {
                    removeByIndex(param_array, 3);
                    removeByIndex(criteria_array, 3);
                } else if (type == "-1" || !agentBalComboFlag) {
                    document.getElementById("type1").innerHTML = "<font color='red'>" + "Type is Missing." + "</font>";
                    status = false;
                }
            }
        }


        if (reportname == "rewardsredeemedreport" || reportname == "walletadjustmentreport" || reportname == "pointsearnedreport")
        {
            //alert("inside")
            if (input.name == "vendor")
            {
                //alert("vendorrrr")
                if ((rewardsreedvend == true))
                {   // alert("vendor1111")
                    status = true;
                }
            }
            else  if (input.name == "subscriber")
            {   //alert("subscriber")
                if ((rewardsreedsubs == true))
                {   // alert("subscriber1111")
                    status = true;
                }
            }
            if ((rewardsreedvend == false) || (rewardsreedsubs == false))
            {
                //alert("11111");
                document.getElementById("typev").innerHTML = "<font color='red'>" + "Please Select " + "</font>";
                status = false;

            }

            if (status_fordate == false)
            {
                status = status_fordate;
            }

        }
        if (reportname == "registrationreport")
            status = true;
        /*  if(reportname == "pointsearnedreport" )
         {
         //alert(subscriber);
         if((agen==true) || (vend==true))
         {
         status=true;
         }
         else if (( vend==false) &&  (subscriber=="hidden") )
         {
         // alert("11111");
         document.getElementById("typev").innerHTML="<font color='red'>"+"Please Select "+"</font>";
         status=false;

         }
         else if (((vend==false) || (vend==false)) &&  (subscriber=="nothidden"))
         {
         //alert("22222");
         document.getElementById("typev").innerHTML="<font color='red'>"+"Please Select Atleast One."+"</font>";
         status=false;
         }

         }*/

        if (hiddenvendorflag == true)
        {
            param_array[cnt] = hiddenvendorval;
            criteria_array[cnt] = hiddenvendorval;
            cnt++;

        }
        if ((  reportname == "pintotalstock" ) && sitename == "DIGICO")
        {

            status = true;
        }

        if (reportname == "salesman")
        {
            //  alert("ddddd"+Salesreport)
            param_array[cnt] = Salesreport;
            // criteria_array[cnt] = hiddenvendorval;
            cnt++;

        }

        /* if(pinstatus && reportname == "pinstockstatus"  && sitename == "DIGICO")
         {
         status=true;
         }
         else if(!pinstatus && reportname == "pinstockstatus"  && sitename == "DIGICO")
         {
         status=false;
         }*/
        //  alert(pinstatus);
        //alert("params1 : "+param_array.length);
        //var dt=getCurrentDate1();

        if (status) {
            //alert("status: "+status);


            //if(sitename=="I360")
            if (sitename == "DIGICO" || sitename == "I360")
            {

                window.open("../reportServlet?report=" + reportname + "&parameters=" + param_array + "&downloadvalue=" + downloadvalue, '', 'resizable=yes, menubar=no,scrollbars=yes,toolbar=no, height=1000, width=1000 ');//,fullscreen=yes


            }
            else
            {

                window.open("../reportServlet?report=" + reportname + "&parameters=" + param_array + "&downloadvalue=" + downloadvalue + "&businessdayvalue=" + businessday, '', 'resizable=yes, menubar=no,scrollbars=yes,toolbar=no, height=1000, width=1000 ');//,fullscreen=yes
            }

            document.report.method = "post";

            //document.report.submit();
        }
    }
}


function sessionCheckResponse()
{
    var htmldata2;
    document.getElementById("divWelcome").innerHTML = "";
    if (sreq != null)
    {
        if (sreq.readyState == 4)
        {
            if (sreq.status == 200)
            {
                htmldata2 = sreq.responseText;
                if (htmldata2 != null)
                {
                    //alert(reportkey+"came "+htmldata2)
                    var sessionFlag = htmldata2.replace(/\s*/g, "");

                    if (sessionFlag == "true") {
                        searchData(reportkey)
                    } else {
                        window.location.href = "../index.jsp";
                    }

                    //document.getElementById("sessionFlag").value=htmldata2;
                }
            }
            else if (sreq.status == 404)
            {

                document.getElementById("divWelcome").innerHTML = "<font color='red'>" + "Internal server error." + "</font>"
            }
            else
            {

                document.getElementById("divWelcome").innerHTML = "<font color='red'>" + "Internal server error." + "</font>"

            }
        }
    }
    else
    {

        document.getElementById("divWelcome").innerHTML = "<font color='red'>" + "Internal server error." + "</font>"
    }

}


//-----------------------------------------------disableCombo-----------------------------------------------------------

function disableCombo()
{
    document.getElementById("type1").innerHTML = "";
    var combo1 = document.getElementById("type");
    var id1 = combo1.options[combo1.selectedIndex].value;
    //var id1=id;
    if (id1 == 2) {
        var x = document.getElementById("tpr");
        x.disabled = true;
        //document.getElementById('tpr').length = 0;

        var y = document.getElementById("operator");
        y.disabled = false;
        y.selectedIndex = 0;
    } else if (id1 == 3) {
        var y = document.getElementById("operator");
        y.disabled = true;
        //document.getElementById('operator').length = 0;
        var x = document.getElementById("tpr");
        x.selectedIndex = 0;
        x.disabled = false;
    } else {
        var x = document.getElementById("tpr");
        x.disabled = false;
        var y = document.getElementById("operator");
        y.disabled = false;
    }
}

function checkCombo() {
    var combo1 = document.getElementById("type");
    var id = combo1.options[combo1.selectedIndex].value;

    if (id == -1) {
        agentBalComboFlag = false;
    } else {
        agentBalComboFlag = true;
    }
}
/*function clearComboMsg(key) {

 if (key == "walletsummaryreport") {
 document.getElementById("combo1").innerHTML = "";
 //alert(key)

 }
 }*/
function clearRemittance(key, fname)
{

    if (key == "remittancestatus" && fname == "transactiontype") {
        document.getElementById("typet").innerHTML = "";
    }
    else if (key == "remittancestatus" && fname == "status") {
        document.getElementById("types").innerHTML = "";
    }
}

function removeByIndex(arrayName, arrayIndex) {
    //alert("came : "+arrayName);
    arrayName.splice(arrayIndex, 1);
}

function clearLabel(key) {


    if (key == "salesman" || key == "regionsales" || key == "branchsales" || key == "evdata" || key == "activeterminals" || key == "registration") {

        // alert("clear select")
        document.getElementById("type1").innerHTML = "";
    }
    else if (key == "pinstockmovement") {
        document.getElementById("typem").innerHTML = "";

    }
}

function clearsalesman() {
    document.getElementById("type2").innerHTML = "";
}

function clearComboMsg(key) {
    //alert("starttttt"+key);
    // alert(key == "businessdaytransactions");
    if (key == "walletsummaryreport") {
        document.getElementById("combo1").innerHTML = "";
    }
    else if (key == "businessdaytransactions" || key == "businessdayshiftreport" || key == "agentbalancebydate") {
        //alert("checkkkkk")
        document.getElementById("type1").innerHTML = "";
    }


    else if (key == "pointsearnedreport" || key == "rewardsredeemedreport" || key == "walletadjustmentreport") {
        document.getElementById("typev").innerHTML = "";
    }

    else if (key == "aggregatedwalletbalancereport") {

        document.getElementById("type1").innerHTML = "";
    }

    else if (key == "pinstocksummary") {
        //alert();
        // document.getElementById("stocktype").innerHTML="";
    }
    /*else if(key == "olrtransactionreport" || key == "pintransactionreport" || key == "tprtransactionreport" || key == "rechargesummaryreport" || key == "walletstatusreport" || key == "tprtransactionbsnlidea"){
     alert("inside_olrtransactionreport")
     document.getElementById("type1").innerHTML="";
     }*/

    else if (key == "agentstatusreport")
    {
        document.getElementById("combo2status").innerHTML = "";
    }

}

function clearSuperMsg(key) {


    if (key == "olrtransactionreport" || key == "pintransactionreport" || key == "tprtransactionreport" || key == "rechargesummaryreport" || key == "walletstatusreport" || key == "tprtransactionbsnlidea" || key == "walletsummaryreport") {

        document.getElementById("type1").innerHTML = "";
    }

}

function changeFormatbackslash(val) {
    //alert(val);
    var myArr = new Array();
    var myArr1 = new Array();
    myArr = val.split(' ');
    var myArr1 = myArr[0].split('/');
    var newDt = myArr1[0] + "/" + myArr1[1] + "/" + myArr1[2];
    return newDt;
}
function getCurrentDate1() {
    var currentDate = new Date()
    var day = currentDate.getDate();
    var s = day.toString();
    if (s.length == 1)
        day = "0" + day;
    var month = currentDate.getMonth() + 1;
    s = month.toString();
    if (s.length == 1)
        month = "0" + month;
    var year = currentDate.getFullYear();
    var dat = year + "-" + month + "-" + day;
    //alert(dat);
    return dat;
}








