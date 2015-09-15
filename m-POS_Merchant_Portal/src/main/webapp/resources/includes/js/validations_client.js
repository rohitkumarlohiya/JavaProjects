var errorColor = "#FFFFCC";

var bgColor = "white";


// Functions Added By Me on 12 June STARTS ///////////////
function validateDateRange(objId1, objId2, helperMsg, spanid) {

    var txt = "<span class='error_color'>" + helperMsg + " <br/></span>";


    var stdate = objId1.value;
    var endDate = objId2.value

    var start_date = new Date(stdate).getTime();
    var end_date = new Date(endDate).getTime();

    if (eval(end_date - start_date) < 0)
    {
        objId2.style.backgroundColor = errorColor;
        document.getElementById(spanid).innerHTML = txt;
        return false;
    }

    return true;
}

function validateSameField(objId1, objId2, helperMsg, spanid) {

    var txt = "<span class='error_color'>" + helperMsg + " <br/></span>";
    var field1 = objId1.value;
    var field2 = objId2.value

    if (field1 == field2)
    {
        objId2.style.backgroundColor = errorColor;
        document.getElementById(spanid).innerHTML = txt;
        return false;
    }
    return true;

}


function validateDateInterval(objId1, objId2, helperMsg, spanid)
{
    var returnFlag = true;
    var dateFrom = objId1.value;
    var dateTo = objId2.value;
    var myDate = new Date(dateFrom);
    var currentDate = new Date(dateTo);
    var txt = "<span class='error_color'>" + helperMsg + " <br/></span>";

    if (myDate.getFullYear() > currentDate.getFullYear()) {
        objId2.style.backgroundColor = "#FFFFCC";
        document.getElementById(spanid).innerHTML = txt;
        returnFlag = false;

    } else if (myDate.getFullYear() == currentDate.getFullYear() && myDate.getMonth() > currentDate.getMonth()) {
        objId2.style.backgroundColor = "#FFFFCC";
        document.getElementById(spanid).innerHTML = txt;
        returnFlag = false;
    } else if (myDate.getFullYear() == currentDate.getFullYear() && myDate.getMonth() == currentDate.getMonth() && myDate.getDate() > currentDate.getDate()) {
        objId2.style.backgroundColor = "#FFFFCC";
        document.getElementById(spanid).innerHTML = txt;
        returnFlag = false;
    }
    return returnFlag;
}

function validateAmount(objId, helperMsg, spanid)

{

    var amt = objId.value;

    var startindex = amt.indexOf(".")

    var lastindex = amt.lastIndexOf(".")


    var txt = "<span class='error_color'>" + helperMsg + " <br/></span>";

    if (startindex > 8) {

        objId.style.backgroundColor = errorColor;
        document.getElementById(spanid).innerHTML = txt;

        return false;
    }
    if (startindex != lastindex)
    {
        objId.style.backgroundColor = errorColor;

        document.getElementById(spanid).innerHTML = txt;

        return false;

    }

    if (startindex > -1)

    {

        if (amt.length == lastindex)

        {

            objId.style.backgroundColor = bgColor;

            document.getElementById(spanid).innerHTML = "";

            return true;

        }

        if ((amt.length - 1) == lastindex)

        {

            objId.style.backgroundColor = bgColor;

            document.getElementById(spanid).innerHTML = "";

            return true;

        }


        else if ((amt.length - 2) == lastindex)

        {

            objId.style.backgroundColor = bgColor;

            document.getElementById(spanid).innerHTML = "";

            return true;

        }


        else if ((amt.length - 3) != lastindex)

        {

            objId.style.backgroundColor = errorColor;

            document.getElementById(spanid).innerHTML = txt;

            return false;

        }

    } else if (startindex == -1) {

        if (amt.length > 8) {
            objId.style.backgroundColor = errorColor;
            document.getElementById(spanid).innerHTML = txt;
            return false;
        }

    }

    objId.style.backgroundColor = bgColor;

    document.getElementById(spanid).innerHTML = "";
      
    return true;

}

function validateAmount5(objId, helperMsg, spanid)

{

    var amt = objId.value;

    var startindex = amt.indexOf(".")

    var lastindex = amt.lastIndexOf(".")


    var txt = "<span class='error_color'>" + helperMsg + " <br/></span>";

    if (startindex > 5) {

        objId.style.backgroundColor = errorColor;
        document.getElementById(spanid).innerHTML = txt;

        return false;
    }
    if (startindex != lastindex)
    {
        objId.style.backgroundColor = errorColor;

        document.getElementById(spanid).innerHTML = txt;

        return false;

    }

    if (startindex > -1)

    {

        if (amt.length == lastindex)

        {

            objId.style.backgroundColor = bgColor;

            document.getElementById(spanid).innerHTML = "";

            return true;

        }

        if ((amt.length - 1) == lastindex)

        {

            objId.style.backgroundColor = bgColor;

            document.getElementById(spanid).innerHTML = "";

            return true;

        }


        else if ((amt.length - 2) == lastindex)

        {

            objId.style.backgroundColor = bgColor;

            document.getElementById(spanid).innerHTML = "";

            return true;

        }


        else if ((amt.length - 3) != lastindex)

        {

            objId.style.backgroundColor = errorColor;

            document.getElementById(spanid).innerHTML = txt;

            return false;

        }

    } else if (startindex == -1) {

        if (amt.length > 5) {
            objId.style.backgroundColor = errorColor;
            document.getElementById(spanid).innerHTML = txt;
            return false;
        }

    }

    objId.style.backgroundColor = bgColor;

    document.getElementById(spanid).innerHTML = "";

    return true;

}



function validateAmount14(objId, helperMsg, spanid)

{

    var amt = objId.value;

    var startindex = amt.indexOf(".")

    var lastindex = amt.lastIndexOf(".")


    var txt = "<span class='error_color'>" + helperMsg + " <br/></span>";

    if (startindex > 14) {

        objId.style.backgroundColor = errorColor;
        document.getElementById(spanid).innerHTML = txt;
        return false;
    }
    if (startindex != lastindex)

    {

        objId.style.backgroundColor = errorColor;

        document.getElementById(spanid).innerHTML = txt;

        return false;

    }

    if (startindex > -1)

    {

        if (amt.length == lastindex)

        {

            objId.style.backgroundColor = bgColor;

            document.getElementById(spanid).innerHTML = "";

            return true;

        }

        if ((amt.length - 1) == lastindex)

        {

            objId.style.backgroundColor = bgColor;

            document.getElementById(spanid).innerHTML = "";

            return true;

        }


        else if ((amt.length - 2) == lastindex)

        {

            objId.style.backgroundColor = bgColor;

            document.getElementById(spanid).innerHTML = "";

            return true;

        }


        else if ((amt.length - 3) != lastindex)

        {

            objId.style.backgroundColor = errorColor;

            document.getElementById(spanid).innerHTML = txt;

            return false;

        }

    } else if (startindex == -1) {

        if (amt.length > 14) {
            objId.style.backgroundColor = errorColor;
            document.getElementById(spanid).innerHTML = txt;
            return false;
        }

    }

    objId.style.backgroundColor = bgColor;

    document.getElementById(spanid).innerHTML = "";

    return true;

}



function validateAmount13(objId, helperMsg, spanid)

{

    var amt = objId.value;

    var startindex = amt.indexOf(".")

    var lastindex = amt.lastIndexOf(".")


    var txt = "<span class='error_color'>" + helperMsg + " <br/></span>";

    if (startindex > 10) {

        objId.style.backgroundColor = errorColor;
        document.getElementById(spanid).innerHTML = txt;
        return false;
    }
    if (startindex != lastindex)

    {

        objId.style.backgroundColor = errorColor;

        document.getElementById(spanid).innerHTML = txt;

        return false;

    }

    if (startindex > -1)

    {

        if (amt.length == lastindex)

        {

            objId.style.backgroundColor = bgColor;

            document.getElementById(spanid).innerHTML = "";

            return true;

        }

        if ((amt.length - 1) == lastindex)

        {

            objId.style.backgroundColor = bgColor;

            document.getElementById(spanid).innerHTML = "";

            return true;

        }


        else if ((amt.length - 2) == lastindex)

        {

            objId.style.backgroundColor = bgColor;

            document.getElementById(spanid).innerHTML = "";

            return true;

        }


        else if ((amt.length - 3) != lastindex)

        {

            objId.style.backgroundColor = errorColor;

            document.getElementById(spanid).innerHTML = txt;

            return false;

        }

    } else if (startindex == -1) {

        if (amt.length > 10) {
            objId.style.backgroundColor = errorColor;
            document.getElementById(spanid).innerHTML = txt;
            return false;
        }

    }

    objId.style.backgroundColor = bgColor;

    document.getElementById(spanid).innerHTML = "";

    return true;

}


function validateAmount12(objId, helperMsg, spanid)

{

    var amt = objId.value;

    var startindex = amt.indexOf(".")

    var lastindex = amt.lastIndexOf(".")


    var txt = "<span class='error_color'>" + helperMsg + " <br/></span>";

    if (startindex > 15) {

        objId.style.backgroundColor = errorColor;
        document.getElementById(spanid).innerHTML = txt;
        return false;
    }
    if (startindex != lastindex)

    {

        objId.style.backgroundColor = errorColor;

        document.getElementById(spanid).innerHTML = txt;

        return false;

    }

    if (startindex > -1)

    {

        if (amt.length == lastindex)

        {

            objId.style.backgroundColor = bgColor;

            document.getElementById(spanid).innerHTML = "";

            return true;

        }

        if ((amt.length - 1) == lastindex)

        {

            objId.style.backgroundColor = bgColor;

            document.getElementById(spanid).innerHTML = "";

            return true;

        }


        else if ((amt.length - 2) == lastindex)

        {

            objId.style.backgroundColor = bgColor;

            document.getElementById(spanid).innerHTML = "";

            return true;

        }


        else if ((amt.length - 3) != lastindex)

        {

            objId.style.backgroundColor = errorColor;

            document.getElementById(spanid).innerHTML = txt;

            return false;

        }

    } else if (startindex == -1) {

        if (amt.length > 12) {
            objId.style.backgroundColor = errorColor;
            document.getElementById(spanid).innerHTML = txt;
            return false;
        }

    }

    objId.style.backgroundColor = bgColor;

    document.getElementById(spanid).innerHTML = "";

    return true;

}

function validateminusAmount12(objId, helperMsg, spanid)
{

    var amt = objId.value;

    var startindex = amt.indexOf(".");

    var lastindex = amt.lastIndexOf(".");

    var startminus = amt.indexOf("-");

    var length = amt.length;


    var txt = "<span class='error_color'>" + helperMsg + " <br/></span>";

    if (startminus == 0)
    {
        if (startindex > 0 && lastindex > 0)
        {
            startindex = startindex - 1;
            lastindex = lastindex - 1;
        }

        length = length - 1;
    }

    if (startindex > 12) {


        objId.style.backgroundColor = errorColor;
        document.getElementById(spanid).innerHTML = txt;
        return false;
    }
    if (startindex != lastindex)

    {

        objId.style.backgroundColor = errorColor;

        document.getElementById(spanid).innerHTML = txt;

        return false;

    }

    if (startindex > 0)

    {

        if (length == lastindex)

        {

            objId.style.backgroundColor = bgColor;

            document.getElementById(spanid).innerHTML = "";

            return true;

        }

        if ((length - 1) == lastindex)

        {

            objId.style.backgroundColor = bgColor;

            document.getElementById(spanid).innerHTML = "";

            return true;

        }


        else if ((length - 2) == lastindex)

        {

            objId.style.backgroundColor = bgColor;

            document.getElementById(spanid).innerHTML = "";

            return true;

        }


        else if ((length - 3) != lastindex)

        {

            objId.style.backgroundColor = errorColor;

            document.getElementById(spanid).innerHTML = txt;

            return false;

        }

    } else if (startindex == -1) {

        if (length > 12) {
            objId.style.backgroundColor = errorColor;
            document.getElementById(spanid).innerHTML = txt;
            return false;
        }

    }

    objId.style.backgroundColor = bgColor;

    document.getElementById(spanid).innerHTML = "";

    return true;

}


function validateAmount10(objId, helperMsg, spanid)

{

    var amt = objId.value;

    var startindex = amt.indexOf(".")

    var lastindex = amt.lastIndexOf(".")

    var txt = "<span class='error_color'>" + helperMsg + " <br/></span>";

    if (startindex > 10) {

        objId.style.backgroundColor = errorColor;
        document.getElementById(spanid).innerHTML = txt;
        return false;
    }
    if (startindex != lastindex)

    {

        objId.style.backgroundColor = errorColor;

        document.getElementById(spanid).innerHTML = txt;

        return false;

    }

    if (startindex > -1)

    {

        if (amt.length == lastindex)

        {

            objId.style.backgroundColor = bgColor;

            document.getElementById(spanid).innerHTML = "";

            return true;

        }

        if ((amt.length - 1) == lastindex)

        {

            objId.style.backgroundColor = bgColor;

            document.getElementById(spanid).innerHTML = "";

            return true;

        }


        else if ((amt.length - 2) == lastindex)

        {

            objId.style.backgroundColor = bgColor;

            document.getElementById(spanid).innerHTML = "";

            return true;

        }


        else if ((amt.length - 3) != lastindex)

        {

            objId.style.backgroundColor = errorColor;

            document.getElementById(spanid).innerHTML = txt;

            return false;

        }

    } else if (startindex == -1) {

        if (amt.length > 10) {
            objId.style.backgroundColor = errorColor;
            document.getElementById(spanid).innerHTML = txt;
            return false;
        }

    }

    objId.style.backgroundColor = bgColor;

    document.getElementById(spanid).innerHTML = "";

    return true;

}


function validateValue(objId, value, helperMsg, spanid)

{

    var amt = value;

    var startindex = amt.indexOf(".")

    var lastindex = amt.lastIndexOf(".")


    var txt = "<span class='error_color'>" + helperMsg + " <br/></span>";

    if (startindex > 8) {

        objId.style.backgroundColor = errorColor;
        document.getElementById(spanid).innerHTML = txt;
        return false;
    }
    if (startindex != lastindex)

    {

        objId.style.backgroundColor = errorColor;

        document.getElementById(spanid).innerHTML = txt;

        return false;

    }

    if (startindex > 0)

    {

        if (amt.length == lastindex)

        {

            objId.style.backgroundColor = bgColor;

            document.getElementById(spanid).innerHTML = "";

            return true;

        }

        if ((amt.length - 1) == lastindex)

        {

            objId.style.backgroundColor = bgColor;

            document.getElementById(spanid).innerHTML = "";

            return true;

        }


        else if ((amt.length - 2) == lastindex)

        {

            objId.style.backgroundColor = bgColor;

            document.getElementById(spanid).innerHTML = "";

            return true;

        }


        else if ((amt.length - 3) != lastindex)

        {

            objId.style.backgroundColor = errorColor;

            document.getElementById(spanid).innerHTML = txt;

            return false;

        }

    } else if (startindex == -1) {

        if (amt.length > 8) {
            objId.style.backgroundColor = errorColor;
            document.getElementById(spanid).innerHTML = txt;
            return false;
        }

    }

    objId.style.backgroundColor = bgColor;

    document.getElementById(spanid).innerHTML = "";

    return true;

}


/*function isvalidateComment(objId, helperMsg, spanid) {

    var elem = objId;

    var alphaExp = /^[a-z A-Z 0-9.,]+$/;

    var txt = "<span class='error_txt'>" + helperMsg + " <br/></span>";

    if (elem.value.match(alphaExp)) {

        //alert(" Alphanumeric values ");

        document.getElementById(spanid).innerHTML = "";

        objId.style.backgroundColor = bgColor;

        return true;

    } else {

        //alert("Please enter Alphanumeric values");

        document.getElementById(spanid).innerHTML = txt;

        objId.style.backgroundColor = errorColor;

        //elem.focus();

        return false;

    }

}*/


function isvalidateComment(objId, helperMsg, spanid) {

        //alert("isvalidateCommentnew")
    var char;
    var elemVale = objId.value;

    //var alphaExp = /^[a-z A-Z0-9!@#$%^*()"\-_+=;:,?/.']+$/;
    var alphaExp = /^[<>&|#]+$/;


    var txt = "<span class='error_txt'>" + helperMsg + " <br/></span>";


    for (var i = 0; i < elemVale.length; i++) {
        char = elemVale.charAt(i);
        if (char.match(alphaExp)) {

            document.getElementById(spanid).innerHTML = txt;

            objId.style.backgroundColor = errorColor;
            return false;
        }

    }

    document.getElementById(spanid).innerHTML = "";

    objId.style.backgroundColor = bgColor;


    return true;


}



function checkforDot(evt)

{

    var charCode = (evt.which) ? evt.which : evt.keyCode

    var txt = "<span class='error_color'>Only Enter Numeric value</span>";

    if ((charCode > 47 && charCode < 58) || charCode == 46 || charCode == 8)

    {

        return true;

    }

    return false;

}


function checkforlogin(evt, spanid)

{

    //var txt="<p class='bodytext'>Enter Alphanumeric"

    var txt = "<span class='error_color'>Enter Alphanumeric</span>";

    var charCode = (evt.which) ? evt.which : evt.keyCode

    if ((charCode > 47 && charCode < 58) || (charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) || charCode == 13 || charCode == 8)

    {

        return true;

    }

    return false;

}


function checkforMSDN(evt)

{

    var charCode = (evt.which) ? evt.which : evt.keyCode


    //	alert(charCode);


    if ((charCode > 47 && charCode < 58) || (charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) || (charCode == 32 || charCode == 8))

    {

        return true;

    }

    return false;

}


function checkforIdCode(evt)

{

    var charCode = (evt.which) ? evt.which : evt.keyCode

    if ((charCode > 47 && charCode < 58) || (charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) || (charCode == 32 || charCode == 8 || charCode == 44 || charCode == 47 || charCode == 46 || charCode == 64 || charCode == 38 || charCode == 45 || charCode == 95))

    {

        return true;

    }

    return false;

}


function checkforAddress(evt)

{

    var charCode = (evt.which) ? evt.which : evt.keyCode

    if ((charCode > 47 && charCode < 58) || (charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) || (charCode == 32 || charCode == 8 || charCode == 44 || charCode == 47 || charCode == 46 || charCode == 64 || charCode == 38 || charCode == 45 || charCode == 95 || charCode == 35))

    {

        return true;

    }

    return false;

}


function isDecimalOnly(objId, helperMsg, spanid) {

    var elem = objId;

    var txt = "<span class='error_color'>" + helperMsg + "</span>";

    var numericExpression = /^[0-9.]+$/;

    if (elem.value.match(numericExpression)) {

        document.getElementById(spanid).innerHTML = "";


        objId.style.backgroundColor = bgColor;

        return true;

    } else {

        // alert("Please enter Numeric values");

        objId.style.backgroundColor = errorColor;


        document.getElementById(spanid).innerHTML = txt;

        //  elem.focus();

        return false;

    }

}


function isNumeric1Only(objId, helperMsg, spanid) {

    var elem = objId;

    var txt = "<span class='error_color'>" + helperMsg + "</span>";

    var numericExpression = /^[0-9]+$/;

    if (elem.value.match(numericExpression)) {

        document.getElementById(spanid).innerHTML = "";


        objId.style.backgroundColor = bgColor;

        return true;

    } else {

        // alert("Please enter Numeric values");

        objId.style.backgroundColor = errorColor;


        document.getElementById(spanid).innerHTML = txt;

        //  elem.focus();

        return false;

    }

}

function isNumericComma(objId, helperMsg, spanid) {
    var elem = objId;
    var txt = "<span class='error_color'>" + helperMsg + "</span>";
    var numericExpression = /^[0-9,]+$/;
    if (elem.value.match(numericExpression)) {
        document.getElementById(spanid).innerHTML = "";
        objId.style.backgroundColor = bgColor;
        return true;
    } else {
        // alert("Please enter Numeric values");
        objId.style.backgroundColor = errorColor;
        document.getElementById(spanid).innerHTML = txt;
        //  elem.focus();
        return false;

    }

}

function isNumericHiphan(objId, helperMsg, spanid) {
    var elem = objId;
    var txt = "<span class='error_color'>" + helperMsg + "</span>";
    var numericExpression = /^[0-9_]+$/;
    if (elem.value.match(numericExpression)) {
        document.getElementById(spanid).innerHTML = "";
        objId.style.backgroundColor = bgColor;
        return true;
    } else {
        // alert("Please enter Numeric values");
        objId.style.backgroundColor = errorColor;
        document.getElementById(spanid).innerHTML = txt;
        //  elem.focus();
        return false;

    }

}



function errorRemove(objId, spanid) {
    document.getElementById(spanid).innerHTML = "";
    objId.style.backgroundColor = bgColor;
    return true;
}

function errorDisplay(spanid,msg) {   
    document.getElementById(spanid).innerHTML = msg;
    return true;
}

function isNumeric2(objId, helperMsg, spanid) {

    var elem = objId;

    var txt = "<span class='error_color'>" + helperMsg + "</span>";

    var numericExpression = /^[0-9]+$/;

    if (elem.value.match(numericExpression)) {

        document.getElementById(spanid).innerHTML = "";


        objId.style.backgroundColor = bgColor;

        return true;

    } else if (elem.value == "") {

        document.getElementById(spanid).innerHTML = "";


        objId.style.backgroundColor = bgColor;

        return true;

    }

    else {

        // alert("Please enter Numeric values");

        objId.style.backgroundColor = errorColor;


        document.getElementById(spanid).innerHTML = txt;

        //  elem.focus();

        return false;

    }

}


function isIdCodeOnly(objId, helperMsg, spanid) {

    var elem = objId;

    var alphaExp = /^[a-z A-Z 0-9,/@ & _ -]+$ /;

    var txt = "<span class='error_txt'>" + helperMsg + " <br/></span>";

    if (elem.value.match(alphaExp)) {

        //alert(" Alphanumeric values ");

        document.getElementById(spanid).innerHTML = "";

        objId.style.backgroundColor = bgColor;

        return true;

    } else {

        //alert("Please enter Alphanumeric values");

        document.getElementById(spanid).innerHTML = txt;

        objId.style.backgroundColor = errorColor;

        //elem.focus();

        return false;

    }

}


function DisableCtrlKey(e)

{

    var keycode;

    if (window.event)

    {

        keycode = window.event.keyCode;

        if ((keycode == 17) || (keycode == 86) || (keycode == 67) || (keycode == 88))

        {

            return false;

        }

    }

    else

    {

        //     alert("TRUE");

    }

}


function isIdAddOnly(objId, helperMsg, spanid) {

    var elem = objId;

    var alphaExp = /^[a-z A-Z 0-9., /@#&_-]+$/;

    var txt = "<span class='error_txt'>" + helperMsg + " <br/></span>";

    if (elem.value.match(alphaExp)) {

        //alert(" Alphanumeric values ");

        document.getElementById(spanid).innerHTML = "";

        objId.style.backgroundColor = bgColor;

        return true;

    } else {

        //alert("Please enter Alphanumeric values");

        document.getElementById(spanid).innerHTML = txt;

        objId.style.backgroundColor = errorColor;

        //elem.focus();

        return false;

    }

}


function isIdAdd2(objId, helperMsg, spanid) {

    var elem = objId;

    var alphaExp = /^[a-z A-Z 0-9.,/@ & _ -]+$ /;

    var txt = "<span class='error_txt'>" + helperMsg + " <br/></span>";

    if (elem.value.match(alphaExp)) {

        //alert(" Alphanumeric values ");

        document.getElementById(spanid).innerHTML = "";

        objId.style.backgroundColor = bgColor;

        return true;

    } else if (elem.value == "") {

        //alert(" Alphanumeric values ");

        document.getElementById(spanid).innerHTML = "";

        objId.style.backgroundColor = bgColor;

        return true;

    }

    else {

        //alert("Please enter Alphanumeric values");

        document.getElementById(spanid).innerHTML = txt;

        objId.style.backgroundColor = errorColor;

        //elem.focus();

        return false;

    }

}


function isIdLoginOnly(objId, helperMsg, spanid) {

    var elem = objId;

    var alphaExp = /^[a-z A-Z 0-9]+$/;

    var txt = "<span class='error_txt'>" + helperMsg + " <br/></span>";

    if (elem.value.match(alphaExp)) {

        //alert(" Alphanumeric values ");

        document.getElementById(spanid).innerHTML = "";

        objId.style.backgroundColor = bgColor;

        return true;

    }

    else {

        //alert("Please enter Alphanumeric values");

        document.getElementById(spanid).innerHTML = txt;

        objId.style.backgroundColor = errorColor;

        //elem.focus();

        return false;

    }

}


function isIdLogin2(objId, helperMsg, spanid) {

    var elem = objId;

    var alphaExp = /^[a-z A-Z 0-9]+$/;

    var txt = "<span class='error_txt'>" + helperMsg + " <br/></span>";

    if (elem.value.match(alphaExp)) {

        //alert(" Alphanumeric values ");

        document.getElementById(spanid).innerHTML = "";

        objId.style.backgroundColor = bgColor;

        return true;

    } else if (elem.value == "") {

        document.getElementById(spanid).innerHTML = "";

        objId.style.backgroundColor = bgColor;

        return true;

    }

    else {

        //alert("Please enter Alphanumeric values");

        document.getElementById(spanid).innerHTML = txt;

        objId.style.backgroundColor = errorColor;

        //elem.focus();

        return false;

    }

}


function emailValidator1(elem, helperMsg, spanid) {

    var txt = "<span class='error_txt'>" + helperMsg + " <br/></span>";

    var emailExp = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.(aero$|asia$|biz$|cat$|com$|coop$|edu$|gov$|info$|jobs$|mil$|mobi$|museum$|name$|net$|org$|travel$|int$|co.in$)/;


    if (elem.value.match(emailExp))

    {

        elem.style.backgroundColor = bgColor;

        document.getElementById(spanid).innerHTML = "";

        return true;

    }

    else

    {

        elem.style.backgroundColor = errorColor;

        document.getElementById(spanid).innerHTML = txt;

        elem.focus();

        return false;

    }

}


function emailValidator2(elem, helperMsg, spanid) {

    var txt = "<span class='error_txt'>" + helperMsg + " <br/></span>";

    //	var emailExp = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.(aero$|asia$|biz$|cat$|com$|coop$|edu$|gov$|info$|jobs$|mil$|mobi$|museum$|name$|net$|org$|travel$|int$|co.in$)/;

    var emailExp = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

    if (elem.value.match(emailExp))

    {

        elem.style.backgroundColor = bgColor;

        document.getElementById(spanid).innerHTML = "";

        return true;

    }

    else if (elem.value == "")

    {

        elem.style.backgroundColor = bgColor;

        document.getElementById(spanid).innerHTML = "";

        return true;

    }

    else

    {

        elem.style.backgroundColor = errorColor;

        document.getElementById(spanid).innerHTML = txt;

        elem.focus();

        return false;

    }

}


function validEmail_new(elem, helperMsg, spanid) {

    //alert("== test validEmail ==");
    var txt = "<span class='error_txt'>" + helperMsg + " <br/></span>";

    var StopPos1 = elem.value.split("@");
    //alert(StopPos1);

    var StopPos = StopPos1[1].split(".");
    //alert(StopPos);

    //alert(StopPos.length);
    if (StopPos.length > 3) {
        elem.style.backgroundColor = errorColor;
        document.getElementById(spanid).innerHTML = txt;
        //elem.focus();
        //redstar(span_ast);
        return false;
    } else {
        elem.style.backgroundColor = bgColor;
        document.getElementById(spanid).innerHTML = "";
        //blackstar(span_ast);
        return true;

    }
}

// Functions Added By Me on 12 June ENDS ///////////////


function emailValidator(elem) {

    //    var txt = "<span class='error_txt'>" + helperMsg + " <br/></span>";

    var emailExp = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.(aero$|asia$|biz$|cat$|com$|coop$|edu$|gov$|info$|jobs$|mil$|mobi$|museum$|name$|net$|org$|travel$|int$|co.in$)/;


    var bgColor = "red";

    var errorColor = "yellow";


    if (elem.value.match(emailExp))

    {

        elem.style.backgroundColor = bgColor;

        document.getElementById("errorContent").innerHTML = "";

        return true;

    }

    else

    {

        elem.style.backgroundColor = errorColor;

        document.getElementById("errorContent").innerHTML = txt;

        elem.focus();

        return false;

    }

}


function checkforInt(evt, spanid)

{

    var charCode = (evt.which) ? evt.which : evt.keyCode

    //var txt="Only Enter Numeric value";

    var txt = "<span class='error_color'>Only Enter Numeric value</span>";

    //alert(charCode);

    if ((charCode > 47 && charCode < 58) || charCode == 32 || charCode == 13 || charCode == 8)

    {

        /*if (charCode == 13)

        {

            return trapEnter(evt);

        }*/
        //document.getElementById(spanid).innerHTML = "";

        return true;

    }
    else
    {

    //document.getElementById(spanid).innerHTML = txt;

    return false;
    }

}





function isNumber(objId, helperMsg, spanid) {

    var elem = objId;

    var txt = "<span class='error_color'>" + helperMsg + "</span>";

    var numericExpression = /^[0-9]+$/;

    if (elem.value.match(numericExpression)) {

        document.getElementById(spanid).innerHTML = "";


        objId.style.backgroundColor = bgColor;

        return true;

    } else {

        // alert("Please enter Numeric values");

        objId.style.backgroundColor = errorColor;


        document.getElementById(spanid).innerHTML = txt;

        //  elem.focus();

        return false;

    }

}


function checkforchar(evt, spanid)

{

    //var txt="Enter Character Only"

    var txt = "<span class='error_color'>Enter Character Only</span>";

    var charCode = (evt.which) ? evt.which : evt.keyCode

    //alert("====charCode ===="+charCode);



    if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) || charCode == 32 || charCode == 13 || charCode == 8)

    {


        return true;

    }

    return false;

}


function checkforalphanumeric(evt, spanid)

{

    //var txt="<p class='bodytext'>Enter Alphanumeric"

    var txt = "<span class='error_color'>Enter Alphanumeric</span>";

    var charCode = (evt.which) ? evt.which : evt.keyCode


    if ((charCode > 47 && charCode < 58) || (charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) || charCode == 32 || charCode == 13 || charCode == 8)

    {

        return true;

    }

    return false;

}


function checkforFloat(evt, spanid)
{

    var charCode = (evt.which) ? evt.which : evt.keyCode
    // var txt="<p class='bodytext'>Only Enter Numeric value";
    var txt = "<span class='error_color'>Only Enter Numeric value</span>";
    if ((charCode > 47 && charCode < 58) || charCode == 46 || charCode == 32 || charCode == 13 || charCode == 8)
    {
        if (charCode == 13)
        {
            return trapEnter(evt);
        }
        return true;
    }
    return false;

}


function checkforFloat1(evt, spanid)

{

    var charCode = (evt.which) ? evt.which : evt.keyCode

    // var txt="Only Enter Numeric value";

    var txt = "<span class='error_color'>Only Enter Numeric value</span>";


 

    if ((charCode > 47 && charCode < 58) || charCode == 46 || charCode == 32 || charCode == 13 || charCode == 8)

    {
           
        if(charCode==13)

         {

         return trapEnter(evt);

         }

        //document.getElementById(spanid).innerHTML = "";

        return true;

    }

    //document.getElementById(spanid).innerHTML = txt;

    return false;

}


/*function checkforchar(evt,spanid)

 {

 var txt="Enter Character Only"

 var charCode = (evt.which) ? evt.which : event.keyCode

 if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) || charCode==32||charCode==13)

 {

 if(charCode==13)

 {

 return trapEnter(evt);

 }



 // document.getElementById(spanid).innerHTML = "";

 return true;

 }

 // document.getElementById(spanid).innerHTML = txt;

 return false;

 }*/



/*function trapEnter(evt) {

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



 submitFormVendor_Add();

 } else

 return true;

 }*/


function echeck(str) {

    var at = "@"

    var dot = "."

    var lat = str.indexOf(at)

    var lstr = str.length

    var ldot = str.indexOf(dot)

    if (str.indexOf(at) == -1) {

        alert("Invalid E-mail ID")

        return false

    }

    if (str.indexOf(at) == -1 || str.indexOf(at) == 0 || str.indexOf(at) == lstr) {

        alert("Invalid E-mail ID")

        return false

    }

    if (str.indexOf(dot) == -1 || str.indexOf(dot) == 0 || str.indexOf(dot) == lstr) {

        alert("Invalid E-mail ID")

        return false

    }

    if (str.indexOf(at, (lat + 1)) != -1) {

        alert("Invalid E-mail ID")

        return false

    }

    if (str.substring(lat - 1, lat) == dot || str.substring(lat + 1, lat + 2) == dot) {

        alert("Invalid E-mail ID")

        return false

    }

    if (str.indexOf(dot, (lat + 2)) == -1) {

        alert("Invalid E-mail ID")

        return false

    }

    if (str.indexOf(" ") != -1) {

        alert("Invalid E-mail ID")

        return false

    }

    return true

}

function ValidateEmail() {

    var emailID = document.formLogin.email

    if ((emailID.value == null) || (emailID.value == "")) {



        //	document.getElementById("em").innerHTML = "Please Enter your Email ID";

        alert("Please Enter your Email ID")

        emailID.focus()

        return false

    }

    if (echeck(emailID.value) == false) {

        emailID.value = ""

        emailID.focus()

        return false

    }

    return true

}


function DisableCtrlKey(e)

{

    //alert(e);

    var keycode;


    if (window.event)

    {

        keycode = window.event.keyCode;

        //			   document.getElementById("errorContent").innerHTML = keycode;

        if ((keycode == 17) || (keycode == 86) || (keycode == 67) || (keycode == 88))

        {

            //                    document.getElementById("errorContent").innerHTML = keycode;

            //					 alert("use of ctrl key not allowed");

            return false;

        }

    }


    else

    {

        //     alert("TRUE");

    }

}


function clickk()

{

    if ((event.button == 2) || (event.button == 3))

    {

        alert("right click disabled for security reason!!!!!!");

        return false;

    }

    else

        return true;

}

function checkzeroAmountValue(obj, helperMsg, spanid)
{
   
   obj.value = trim(obj.value);
  var txt = "<span class='error_color'>" + helperMsg + " <br/></span>";
     if (obj.value == 0 || obj.value == 0.00 || obj.value == 0.0)
     {

                obj.style.backgroundColor = errorColor;
                document.getElementById(spanid).innerHTML = txt;
                return false;
     }
   
}




function hasValue(obj, obj_type, helperMsg, spanid) {
    //alert("obj============>"+obj.value+"\nobj_type============>"+obj_type+"\n"+"helperMsg==========>"+helperMsg+"\n"+"spanid==========>"+spanid);
    obj.value = trim(obj.value);
    var txt = "<span class='error_color'>" + helperMsg + " <br/></span>";
    if (obj_type == "TEXT" || obj_type == "PASSWORD" || obj_type == "TEXTAREA" || obj_type == "FILE") {
        var count = 0;
        if (spanid == "desc_err_spack") {

            var iChars = "#!@$%^&*()+=-[]\\\';,./{}|\":<>?~_";

            data = obj.value;

            for (var i = 0; i < data.length; i++) {

                if (iChars.indexOf(data.charAt(i)) != -1) {
                    count++;
                }
            }

        }
        if (obj.value.length == 0) {

            if (spanid == "desc_err_spack") {
                return true;
            } else {
                obj.style.backgroundColor = errorColor;
                document.getElementById(spanid).innerHTML = txt;
                return false;
            }
        }
          
        else if (count > 0) {

            obj.style.backgroundColor = errorColor;
            document.getElementById(spanid).innerHTML = "<span class='error_color'>Spacial characters not allowed.<br/></span>";
            return false;
        } else if (document.all && document.all["_" + obj.name + "_editor"] && (obj.value == '<P>&nbsp;</P>')) {
            obj.style.backgroundColor = errorColor;
            document.getElementById(spanid).innerHTML = txt;
            return false;
        } else {
            document.getElementById(spanid).innerHTML = "";
            obj.style.backgroundColor = bgColor;
            return true;
        }
    } else if (obj_type == "SELECT") {
        if (obj.value == -1) {
            obj.style.backgroundColor = errorColor;
            document.getElementById(spanid).innerHTML = txt;
            return false;
        } else {
            document.getElementById(spanid).innerHTML = "";
            obj.style.backgroundColor = bgColor;
            return true;
        }
    } else if (obj_type == "SELECT") {
        if (obj.value == 1) {
            obj.style.backgroundColor = errorColor;
            document.getElementById(spanid).innerHTML = txt;
            //obj.focus();
            return false;
        } else {
            document.getElementById(spanid).innerHTML = "";
            obj.style.backgroundColor = bgColor;
            return true;
        }
    } else if (obj_type == "RADIO") {
        var status = false;
        for (i = 0; i < obj.length; i++) {
            if (obj[i].checked) {
                document.getElementById(spanid).innerHTML = "";
                //obj.style.backgroundColor = bgColor;
                status = true;
            }
        }

        if (!status) {
            //obj.style.backgroundColor = errorColor;
            document.getElementById(spanid).innerHTML = txt;
        }
        return status;
    } else if (obj_type == "CHECKBOX") {
            if (obj.checked) {
                document.getElementById(spanid).innerHTML = "";
                obj.style.backgroundColor = bgColor;
                return true;
            }
            else {
                obj.style.backgroundColor = errorColor;
                document.getElementById(spanid).innerHTML = txt;
                //obj.focus();
                return false;
            }
        }
        else if (obj_type == "button" || obj_type == "BUTTON") {
            document.getElementById(spanid).innerHTML = txt;
            document.getElementById(spanid).style.backgroundColor = errorColor;
        }
    return false;
}


function trim(str) {

    if (str != null) {
        var i;
        for (i = 0; i < str.length; i++) {
            if (str.charAt(i) != " ") {
                str = str.substring(i, str.length);
                break;
            }
        }
        for (i = str.length - 1; i >= 0; i--) {
            if (str.charAt(i) != " ") {
                str = str.substring(0, i + 1);
                break;
            }
        }

        if (str.charAt(0) == " ") {
            return "";

        } else {
            return str;

        }

    }

}


function isAlphaOnly(obj, helperMsg, spanid) {

    var elem = obj;
    var alphaExp = /^[a-zA-Z]+$/;
    var txt = "<span class='error_color'>" + helperMsg + " <br/></span>";

    //alert("=== Value ====="+elem.value.charAt(0));
    if (elem.value.match(alphaExp)) {
        document.getElementById(spanid).innerHTML = "";
        obj.style.backgroundColor = bgColor;
        return true;
    } else {
        obj.style.backgroundColor = errorColor;
        document.getElementById(spanid).innerHTML = txt;
        return false;

    }
}

function isAlphaDotOnly(obj, helperMsg, spanid) {

    var elem = obj;
    var alphaExp = /^[a-z.A-Z0-9]+$/;
    var txt = "<span class='error_color'>" + helperMsg + " <br/></span>";

    //alert("=== Value ====="+elem.value.charAt(0));
    if (elem.value.match(alphaExp)) {
        document.getElementById(spanid).innerHTML = "";
        obj.style.backgroundColor = bgColor;
        return true;
    } else {
        obj.style.backgroundColor = errorColor;
        document.getElementById(spanid).innerHTML = txt;
        return false;

    }
}


function isAlphaNumericSpecialCharSpace(obj, helperMsg, spanid) {

    var elem = obj;
    var alphaExp = /^[a-z A-Z0-9_#!@#$%^*()-_+=;:,?/.']+$/;
    var txt = "<span class='error_color'>" + helperMsg + " <br/></span>";
    if (elem.value.match(alphaExp)) {
        document.getElementById(spanid).innerHTML = "";
        obj.style.backgroundColor = bgColor;
        return true;
    } else {
        obj.style.backgroundColor = errorColor;
        document.getElementById(spanid).innerHTML = txt;
        return false;
    }
}


function isAlphaNumericSpecialChar(obj, helperMsg, spanid) {

    var elem = obj;
    var alphaExp = /^[a-zA-Z0-9_#!@#$%^&*()-_+=;:,?/<,>.']+$/;
    var txt = "<span class='error_color'>" + helperMsg + " <br/></span>";
    if (elem.value.match(alphaExp)) {
        document.getElementById(spanid).innerHTML = "";
        obj.style.backgroundColor = bgColor;
        return true;
    } else {
        obj.style.backgroundColor = errorColor;
        document.getElementById(spanid).innerHTML = txt;
        return false;
    }
}


function isAlphaNumericNospace(obj, helperMsg, spanid) {


    var elem = obj;
    var alphaExp = /^[a-zA-Z0-9_#-]+$/;
    var txt = "<span class='error_color'>" + helperMsg + " <br/></span>";

    //alert("=== Value ====="+elem.value.charAt(0));
    if (elem.value.match(alphaExp)) {
        document.getElementById(spanid).innerHTML = "";
        obj.style.backgroundColor = bgColor;
        return true;
    } else {
        obj.style.backgroundColor = errorColor;
        document.getElementById(spanid).innerHTML = txt;
        return false;

    }
}

function isAlphaNumericspace(obj, helperMsg, spanid) {


    var elem = obj;
    var alphaExp = /^[a-z A-Z0-9_#-]+$/;
    var txt = "<span class='error_color'>" + helperMsg + " <br/></span>";

    //alert("=== Value ====="+elem.value.charAt(0));
    if (elem.value.match(alphaExp)) {
        document.getElementById(spanid).innerHTML = "";
        obj.style.backgroundColor = bgColor;
        return true;
    } else {
        obj.style.backgroundColor = errorColor;
        document.getElementById(spanid).innerHTML = txt;
        return false;

    }
}

function isAlphaNumericspaceOutletName(obj, helperMsg, spanid) {

    var elem = obj;
    var alphaExp = /^[a-z A-Z0-9#$@_-]+$/;
    var txt = "<span class='error_color'>" + helperMsg + " <br/></span>";

    //alert("=== Value ====="+elem.value.charAt(0));
    if (elem.value.match(alphaExp)) {
        document.getElementById(spanid).innerHTML = "";
        obj.style.backgroundColor = bgColor;
        return true;
    } else {
        obj.style.backgroundColor = errorColor;
        document.getElementById(spanid).innerHTML = txt;
        return false;

    }

}

function isAlphaNumericspaceOutletCode(obj, helperMsg, spanid) {

    var elem = obj;
    var alphaExp = /^[a-z A-Z0-9_-]+$/;
    var txt = "<span class='error_color'>" + helperMsg + " <br/></span>";

    //alert("=== Value ====="+elem.value.charAt(0));
    if (elem.value.match(alphaExp)) {
        document.getElementById(spanid).innerHTML = "";
        obj.style.backgroundColor = bgColor;
        return true;
    } else {
        obj.style.backgroundColor = errorColor;
        document.getElementById(spanid).innerHTML = txt;
        return false;

    }

}


function checkposition_firstChar(obj) {
    var elem = obj;
    var alphaExp = /^[a-zA-Z]+$/;

    // alert("=== Value ====="+elem.value.charAt(0));

    var pasitionFirst = elem.value.charAt(0);
    if (pasitionFirst.match(alphaExp)) {

        return 1;
    }
}
function checkposition_firstNumber(obj) {
    var elem = obj;
    var alphaExp = /^[0-9]+$/;
    // alert("=== Value ====="+elem.value.charAt(0));

    var pasitionFirst = elem.value.charAt(0);
    if (pasitionFirst.match(alphaExp)) {

        return 2;
    }
}


function isNumericOnly(objId, helperMsg, spanid) {

    var elem = objId;

    var txt = "<span class='error_color'>" + helperMsg + "</span>";

    var numericExpression = /^[0-9.]+$/;

    if (elem.value.match(numericExpression)) {

        document.getElementById(spanid).innerHTML = "";


        objId.style.backgroundColor = bgColor;

        return true;

    } else {

        // alert("Please enter Numeric values");

        objId.style.backgroundColor = errorColor;


        document.getElementById(spanid).innerHTML = txt;

        //  elem.focus();

        return false;

    }

}

function isNumericOnly1(objId, helperMsg, spanid) {

    var elem = objId;

    var txt = "<span class='error_color'>" + helperMsg + "</span>";

    var numericExpression = /^[0-9.]+$/;

    if (elem.value.match(numericExpression)) {

        document.getElementById(spanid).innerHTML = "";


        objId.style.backgroundColor = bgColor;

        return true;

    } else {

        // alert("Please enter Numeric values");

        objId.style.backgroundColor = errorColor;


        document.getElementById(spanid).innerHTML = txt;

        //  elem.focus();

        return false;

    }

}

function checkEqualOrGreaterLength(objId, helperMsg, spanid) {

    var elem = objId;


    var txt = "<span class='error_color'>" + helperMsg + "</span>";

    if (elem.value >= 0) {

        document.getElementById(spanid).innerHTML = "";

        objId.style.backgroundColor = bgColor;

        return true;

    } else {

        objId.style.backgroundColor = errorColor;

        document.getElementById(spanid).innerHTML = txt;

        return false;

    }

}



function checkGreaterThanZero(objId, helperMsg, spanid) {

    var elem = objId;


    var txt = "<span class='error_color'>" + helperMsg + "</span>";

    if (elem.value > 0) {

        document.getElementById(spanid).innerHTML = "";

        objId.style.backgroundColor = bgColor;

        return true;

    } else {

        objId.style.backgroundColor = errorColor;

        document.getElementById(spanid).innerHTML = txt;

        return false;

    }

}

function checkGreaterAndEqualZero(objId, helperMsg, spanid) {

    var elem = objId;


    var txt = "<span class='error_color'>" + helperMsg + "</span>";

    if (elem.value >= 0) {

        document.getElementById(spanid).innerHTML = "";

        objId.style.backgroundColor = bgColor;

        return true;

    } else {

        objId.style.backgroundColor = errorColor;

        document.getElementById(spanid).innerHTML = txt;

        return false;

    }

}

function greaterThanZero(objId, value, helperMsg, spanid) {

    var elem = value;


    var txt = "<span class='error_color'>" + helperMsg + "</span>";

    if (elem > 0) {

        document.getElementById(spanid).innerHTML = "";

        objId.style.backgroundColor = bgColor;

        return true;

    } else {

        objId.style.backgroundColor = errorColor;

        document.getElementById(spanid).innerHTML = txt;

        return false;

    }

}


function checkMinMaxLength(objId, min, max, helperMsg, spanid) {

    var elem = objId;

    var txt = "<span class='error_color'>" + helperMsg + "</span>";

  // alert(elem.value.length+"="+min+"-"+max);

    if (elem.value.length > min && elem.value.length < max) {
      
        document.getElementById(spanid).innerHTML = "";

        objId.style.backgroundColor = bgColor;

        return true;

    } else {

        objId.style.backgroundColor = errorColor;

        document.getElementById(spanid).innerHTML = txt;

        return false;

    }


}

function checkfixedLength(objId, min, max, helperMsg, spanid) {

    var elem = objId;

    var txt = "<span class='error_color'>" + helperMsg + "</span>";



    if (elem.value.length == min && elem.value.length == max) {

        document.getElementById(spanid).innerHTML = "";

        objId.style.backgroundColor = bgColor;

        return true;

    } else {

        objId.style.backgroundColor = errorColor;

        document.getElementById(spanid).innerHTML = txt;

        return false;

    }


}


function checkMinMaxLength_mobile(objId, min, max, helperMsg, spanid) {

    var elem = objId;

    var txt = "<span class='error_color'>" + helperMsg + "</span>";

    //alert(elem.value.length+"="+min+"-"+max);

    if (elem.value.length >= min && elem.value.length < max) {

        document.getElementById(spanid).innerHTML = "";

        objId.style.backgroundColor = bgColor;

        return true;

    } else {

        objId.style.backgroundColor = errorColor;

        document.getElementById(spanid).innerHTML = txt;

        return false;

    }


}


function checkMax(objId, value, helperMsg, spanid) {

    var elem = objId;

    var txt = "<span class='error_color'>" + helperMsg + "</span>";
    if (elem.value >= value) {

        document.getElementById(spanid).innerHTML = "";

        objId.style.backgroundColor = bgColor;

        return true;

    } else {

        objId.style.backgroundColor = errorColor;

        document.getElementById(spanid).innerHTML = txt;

        return false;

    }

}

function checkMaxValue(objId, value, helperMsg, spanid) {

    var elem = objId;

    var txt = "<span class='error_color'>" + helperMsg + "</span>";
    if (parseFloat(trim(elem.value)) >= parseFloat(trim(value))) {
        document.getElementById(spanid).innerHTML = "";
        objId.style.backgroundColor = bgColor;
        return true;
    } else {
        objId.style.backgroundColor = errorColor;
        document.getElementById(spanid).innerHTML = txt;
        return false;

    }

}


function maxValue(objId, min, max, helperMsg, spanid) {

    var elem = max;

    var txt = "<span class='error_color'>" + helperMsg + "</span>";

    if (parseInt(trim(elem)) >= parseInt(trim(min))) {

        document.getElementById(spanid).innerHTML = "";

        objId.style.backgroundColor = bgColor;

        return true;

    } else {

        objId.style.backgroundColor = errorColor;

        document.getElementById(spanid).innerHTML = txt;

        return false;

    }

}


function checkMin(objId, value, helperMsg, spanid) {

    var elem = objId;

    var txt = "<span class='error_color'>" + helperMsg + "</span>";
    if (elem.value >= 0 && elem.value <= value) {
        document.getElementById(spanid).innerHTML = "";

        objId.style.backgroundColor = bgColor;

        return true;

    } else {
        objId.style.backgroundColor = errorColor;

        document.getElementById(spanid).innerHTML = txt;

        return false;

    }

}

function checkRange(objId, objId1, helperMsg, spanid) {

    var elem = objId;
    var elem1 = objId1;

    var txt = "<span class='error_color'>" + helperMsg + "</span>";

    if (parseFloat(elem.value) >= parseFloat(elem1.value)) {

        document.getElementById(spanid).innerHTML = "";

        objId.style.backgroundColor = bgColor;

        return true;

    } else {

        objId.style.backgroundColor = errorColor;

        document.getElementById(spanid).innerHTML = "Max range should be greater then or equal to min range";

        return false;

    }

}


function checkLength(objId, value, helperMsg, spanid) {

    var elem = objId;

    var txt = "<span class='error_color'>" + helperMsg + "</span>";


    if (elem.value.length == value) {

        document.getElementById(spanid).innerHTML = "";

        objId.style.backgroundColor = bgColor;

        return true;

    } else {

        objId.style.backgroundColor = errorColor;

        document.getElementById(spanid).innerHTML = txt;

        return false;

    }

}


function isAlpha1(objId, helperMsg, spanid) {

    var elem = objId;

    var alphaExp = /^[a-z A-Z]+$/;

    // var txt="Enter the Character"

    var txt = "<span class='error_color'>Enter the Character</span>";


    if (elem.value.match(alphaExp)) {

        //alert(" Alphanumeric values ");

        document.getElementById(spanid).innerHTML = "";


        objId.style.backgroundColor = bgColor;

        return true;

    } else {

        //alert("Please enter Alphanumeric values");

        document.getElementById(spanid).innerHTML = txt;

        objId.style.backgroundColor = errorColor;


        //elem.focus();

        return false;

    }

}


function isAlpha2(objId, helperMsg, spanid) {

    var elem = objId;

    var alphaExp = /^[a-z A-Z]+$/;

    // var txt="Enter the Character"

    var txt = "<span class='error_color'>Enter the Character</span>";


    if (elem.value.match(alphaExp)) {

        //alert(" Alphanumeric values ");

        document.getElementById(spanid).innerHTML = "";


        objId.style.backgroundColor = bgColor;

        return true;

    } else if (elem.value == "") {

        document.getElementById(spanid).innerHTML = "";


        objId.style.backgroundColor = bgColor;

        return true;

    } else {

        //alert("Please enter Alphanumeric values");

        document.getElementById(spanid).innerHTML = txt;

        objId.style.backgroundColor = errorColor;


        //elem.focus();

        return false;

    }

}


function isAlphanumeric_msg(objId, helperMsg, spanid) {


    var elem = objId;

    var txt = "<span class='error_color' >" + helperMsg + "</span>";

    var alphaExp = /^[0-9a-z A-Z]+$/;


    if (elem.value.match(alphaExp)) {

        document.getElementById(spanid).innerHTML = "";


        objId.style.backgroundColor = bgColor;


        return true;


    } else {



        // alert("Please enter Alphanumeric values");

        document.getElementById(spanid).innerHTML = txt;

        objId.style.backgroundColor = errorColor;


        elem.focus();


        return false;


    }


}


function isFloat_msg(objId, helperMsg, spanid)

{

    //alert("=== test ==");

    var txt = "<span class='error_color'>" + helperMsg + " <br/></span>";


    var s = objId.value;

    //alert(s);

    var elem = objId;

    if (isWhitespace(s)) {

        //alert("Please enter Numeric values");

        objId.style.backgroundColor = errorColor;


        document.getElementById(spanid).innerHTML = txt;

        elem.focus();

        return false;

    }


    var seenDecimalPoint = false;

    if (s == '.') {

        // alert("Please enter Numeric values");

        objId.style.backgroundColor = errorColor;


        document.getElementById(spanid).innerHTML = txt;

        elem.focus();

        return false;

    }

    // Search through string's characters one by one

    // until we find a non-numeric character.

    // When we do, return false; if we don't, return true.

    for (var i = 0; i < s.length; i++) {

        // Check that current character is number.

        var c = s.charAt(i);

        if ((c == '.') && !seenDecimalPoint) {

            seenDecimalPoint = true;

        } else if (!isDigit(c)) {

            objId.style.backgroundColor = errorColor;


            document.getElementById(spanid).innerHTML = txt;

            elem.focus();

            return false;

        }

    }


    // All characters are numbers.

    document.getElementById(spanid).innerHTML = "";

    objId.style.backgroundColor = bgColor;

    return true;

}

function hasValueZero(obj, helperMsg, spanid) {

    obj.value = trim(obj.value);
    var txt = "<span class='error_color'>" + helperMsg + " <br/></span>";
    if (obj.value == 0) {


        obj.style.backgroundColor = errorColor;


        document.getElementById(spanid).innerHTML = txt;


        //obj.focus();


        return false;


    } else {


        document.getElementById(spanid).innerHTML = "";


        obj.style.backgroundColor = bgColor;


        return true;


    }

}


function isAlphanumeric_msg1(objId, helperMsg, spanid) {


    var elem = objId;

    var txt = "<span class='error_color'>" + helperMsg + "</span>";

    var alphaExp = /^[0-9a-zA-Z]+$/;


    if (elem.value.match(alphaExp)) {

        document.getElementById(spanid).innerHTML = "";


        objId.style.backgroundColor = bgColor;


        return true;


    } else {



        // alert("Please enter Alphanumeric values");

        document.getElementById(spanid).innerHTML = txt;

        objId.style.backgroundColor = errorColor;

        elem.focus();


        return false;

    }

}
function daysInMonth(month, year) {
    var ds = String(monthNum + 1) + '/0/' + String(yearNum);
    var dd = new Date(ds);
    return dd.getDate();
}

function selectoptiondisplay(m, obj, obj1, obj2, y, sel)
{


    var j = 0;
    var sel1;
    if (sel == 0)
    {
        sel1 = 0;
    }
    else
    {
        sel1 = sel;
    }
    removeOptions(obj);
    removeOptions(obj1);
    obj.disabled = false;
    obj1.disabled = false;
    addOption(obj, "Selection Required", -1);
    addOption(obj1, "Selection Required", -1);
    addOption(obj2, "Selection Required", -1);

    if (m != -1)
    {

        if (m == 00 || m == 02 || m == 04 || m == 06 || m == 07 || m == 9 || m == 11)
        {
            var dmax = 31;

            for (i = 1; i <= dmax; i++)
            {
                if (sel1 > 0 && sel1 == i)
                {
                    addOption(obj, i, i, sel1);
                }
                else
                {
                    addOption(obj, i, i, -1);
                }
            }


        }
        else if (m == 03 || m == 05 || m == 08 || m == 10)
        {

            var dmax = 30;

            for (i = 1; i <= dmax; i++)
            {
                if (sel1 > 0 && sel1 == i)
                {
                    addOption(obj, i, i, sel1);
                }
                else
                {
                    addOption(obj, i, i, -1);
                }
            }

        }
        else
        {

            if ((y % 400 == 0) || (y % 400 == 0 && y % 100 != 0))
            {

                dmax = 29;
                for (i = 1; i <= dmax; i++)
                {
                    if (sel1 > 0 && sel1 == i)
                    {
                        addOption(obj, i, i, sel1);
                    }
                    else
                    {
                        addOption(obj, i, i, -1);
                    }
                }

            }
            else
            {

                dmax = 28;
                for (i = 1; i <= dmax; i++)
                {
                    if (sel1 > 0 && sel1 == i)
                    {
                        addOption(obj, i, i, sel1);
                    }
                    else
                    {
                        addOption(obj, i, i, -1);
                    }
                }
            }


        }
        var n = m;
        var mon = new Array();
        mon[0] = "January";
        mon[1] = "February";
        mon[2] = "March";
        mon[3] = "April";
        mon[4] = "May";
        mon[5] = "June";
        mon[6] = "July";
        mon[7] = "August";
        mon[8] = "September";
        mon[9] = "October";
        mon[10] = "November";
        mon[11] = "December";

        --m;
        ++m;
        for (i = m + 1; i < 12; i++)
        {
            if (document.getElementById("deMonth").value != null && document.getElementById("deMonth").value > 0)
            {
                addOption(obj1, mon[i], i, 1)
            }
            else
            {
                addOption(obj1, mon[i], i, -1);

            }


        }
    }
    else
    {
        removeOptions(obj);
        removeOptions(obj1);
        removeOptions(obj2);
        addOption(obj, "Selection Required", -1);
        addOption(obj1, "Selection Required", -1);
        addOption(obj2, "Selection Required", -1);

    }
}
function selectoptiondisplay1(m, obj, y, sel)
{


    var j = 0;
    removeOptions(obj);

    obj.disabled = false;

    addOption(obj, "Selection Required", -1);
    ++m;
    var sel1;


    if (sel == 0)
    {
        sel1 = 0;
    }
    else
    {
        sel1 = sel;
    }
    if (m != (0))
    {

        if (m == 01 || m == 03 || m == 05 || m == 07 || m == 08 || m == 10 || m == 12)
        {
            var dmax = 31;

            for (i = 1; i <= dmax; i++)
            {
                if (sel1 > 0 && sel1 == i)
                {
                    addOption(obj, i, i, sel1);
                }
                else
                {
                    addOption(obj, i, i, -1);
                }

            }


        }
        else if (m == 04 || m == 06 || m == 09 || m == 11)
        {

            var dmax = 30;

            for (i = 1; i <= dmax; i++)
            {

                if (sel1 > 0 && sel1 == i)
                {
                    addOption(obj, i, i, sel1);
                }
                else
                {
                    addOption(obj, i, i, -1);
                }
            }

        }
        else
        {

            if ((y % 400 == 0) || (y % 400 == 0 && y % 100 != 0))
            {

                dmax = 29;
                for (i = 1; i <= dmax; i++)
                {
                    if (sel1 > 0 && sel1 == i)
                    {
                        addOption(obj, i, i, sel1);
                    }
                    else
                    {
                        addOption(obj, i, i, -1);
                    }
                }

            }
            else
            {

                dmax = 28;
                for (i = 1; i <= dmax; i++)
                {
                    if (sel1 > 0 && sel1 == i)
                    {
                        addOption(obj, i, i, sel1);
                    }
                    else
                    {
                        addOption(obj, i, i, -1);
                    }
                }
            }


        }
    }
    else
    {
        removeOptions(obj);
        addOption(obj, "Selection Required", -1);
    }

}
function removeOptions(selectbox)
{
    var i;
    for (i = selectbox.options.length - 1; i >= 0; i--)
    {

        selectbox.remove(i);
    }
}
function addOption(selectbox, text, value)
{
    var optn = document.createElement("OPTION");
    optn.text = text;
    optn.value = value;
    selectbox.options.add(optn);
}
function addOption(selectbox, text, value, selvalue)
{
    if (selvalue == -1)
    {
        var optn = document.createElement("OPTION");
        optn.text = text;
        optn.value = value;
        selectbox.options.add(optn);
    }
    else
    {
        var optn = document.createElement("OPTION");
        optn.text = text;
        optn.value = value;
        optn.selected = true;
        selectbox.options.add(optn);
    }

}
function checkforMonth(obj)
{
    var month = obj.value

    if (month > 0 && month < 13)
    {
        return true;
    }
    return false;
}

function makedisable()
{
    var min = document.getElementById("minrange").value;

    var max = document.getElementById("maxrange").value;


    if ((parseFloat(max) > parseFloat(min) ))
    {

        document.getElementById("invalue").value = "";
        document.getElementById("smsvalue").value = "";
        document.getElementById("invalue").readOnly = true;
        document.getElementById("smsvalue").readOnly = true;


    }
    else if (parseFloat(max) == 0)
    {

        document.getElementById("invalue").value = "";
        document.getElementById("smsvalue").value = "";
        document.getElementById("invalue").readOnly = true;
        document.getElementById("smsvalue").readOnly = true;

    }
    else if (parseFloat(max) < parseFloat(min))
    {

        document.getElementById("invalue").value = "";
        document.getElementById("smsvalue").value = "";
        document.getElementById("invalue").readOnly = true;
        document.getElementById("smsvalue").readOnly = true;

    }
    else if (parseFloat(max) == parseFloat(min))
    {

        document.getElementById("invalue").value = "";
        document.getElementById("smsvalue").value = "";
        document.getElementById("invalue").readOnly = false;
        document.getElementById("smsvalue").readOnly = false;

    }
}
function displaysel(obj, dem, obj1, ded, y)
{

    if (dem != null && ded != null)
    {

        removeOptions(obj);
        removeOptions(obj1);

        addOption(obj, "Selection Required", -1, 0);
        addOption(obj1, "Selection Required", -1, 0);


        var mon = new Array();
        mon[0] = "January";
        mon[1] = "February";
        mon[2] = "March";
        mon[3] = "April";
        mon[4] = "May";
        mon[5] = "June";
        mon[6] = "July";
        mon[7] = "August";
        mon[8] = "September";
        mon[9] = "October";
        mon[10] = "November";
        mon[11] = "December";

        for (i = 0; i < 12; i++)
        {

            if (dem >= 0 && dem == i)
            {

                addOption(obj, mon[i], i, dem)
            }
            else
            {
                addOption(obj, mon[i], i, -1);
            }
        }
        var m = dem;
        if (m == 01 || m == 03 || m == 05 || m == 07 || m == 08 || m == 10 || m == 12)
        {
            var dmax = 31;

            for (i = 1; i <= dmax; i++)
            {
                if (ded == i)
                {
                    addOption(obj1, i, i, ded);
                }
                else
                {
                    addOption(obj1, i, i, -1);
                }
            }
        }
        else if (m == 04 || m == 06 || m == 09 || m == 11)
        {
            var dmax = 30;
            for (i = 1; i <= dmax; i++)
            {
                if (ded == i)
                {
                    addOption(obj1, i, i, ded);
                }
                else
                {
                    addOption(obj1, i, i, -1);
                }
            }
        }
        else
        {
            if ((y % 400 == 0) || (y % 400 == 0 && y % 100 != 0))
            {
                dmax = 29;
                for (i = 1; i <= dmax; i++)
                {
                    if (ded == i)
                    {
                        addOption(obj1, i, i, ded);
                    }
                    else
                    {
                        addOption(obj1, i, i, -1);
                    }
                }
            }
            else
            {
                dmax = 28;
                for (i = 1; i <= dmax; i++)
                {
                    if (ded == i)
                    {
                        addOption(obj1, i, i, ded);
                    }
                    else
                    {
                        addOption(obj1, i, i, -1);
                    }
                }
            }
        }
    }
}

function displaymonth(m)
{
    var month = "hello";
    var mon = new Array();
    mon[0] = "January";
    mon[1] = "February";
    mon[2] = "March";
    mon[3] = "April";
    mon[4] = "May";
    mon[5] = "June";
    mon[6] = "July";
    mon[7] = "August";
    mon[8] = "September";
    mon[9] = "October";
    mon[10] = "November";
    mon[11] = "December";
    for (i = 0; i <= m; i++)
    {
        if (i == m)
        {
            month = mon[i];
        }

    }
    return month;

}

function selectoptiondisplayadd(m, obj, obj1, obj2, y, sel)
{


    var j = 0;
    var sel1;
    if (sel == 0)
    {
        sel1 = 0;
    }
    else
    {
        sel1 = sel;
    }
    removeOptions(obj);
    removeOptions(obj1);
    obj.disabled = false;
    obj1.disabled = false;
    addOption(obj, "Selection Required", -1);
    addOption(obj1, "Selection Required", -1);
    addOption(obj2, "Selection Required", -1);

    if (m != -1)
    {

        if (m == 01 || m == 03 || m == 05 || m == 07 || m == 08 || m == 10 || m == 12)
        {
            var dmax = 31;

            for (i = 1; i <= dmax; i++)
            {
                if (sel1 > 0 && sel1 == i)
                {
                    addOption(obj, i, i, sel1);
                }
                else
                {
                    addOption(obj, i, i, -1);
                }
            }


        }
        else if (m == 04 || m == 06 || m == 09 || m == 11)
        {

            var dmax = 30;

            for (i = 1; i <= dmax; i++)
            {
                if (sel1 > 0 && sel1 == i)
                {
                    addOption(obj, i, i, sel1);
                }
                else
                {
                    addOption(obj, i, i, -1);
                }
            }

        }
        else
        {

            if ((y % 400 == 0) || (y % 400 == 0 && y % 100 != 0))
            {

                dmax = 29;
                for (i = 1; i <= dmax; i++)
                {
                    if (sel1 > 0 && sel1 == i)
                    {
                        addOption(obj, i, i, sel1);
                    }
                    else
                    {
                        addOption(obj, i, i, -1);
                    }
                }

            }
            else
            {

                dmax = 28;
                for (i = 1; i <= dmax; i++)
                {
                    if (sel1 > 0 && sel1 == i)
                    {
                        addOption(obj, i, i, sel1);
                    }
                    else
                    {
                        addOption(obj, i, i, -1);
                    }
                }
            }


        }
        var n = m;
        var mon = new Array();
        mon[0] = "January";
        mon[1] = "February";
        mon[2] = "March";
        mon[3] = "April";
        mon[4] = "May";
        mon[5] = "June";
        mon[6] = "July";
        mon[7] = "August";
        mon[8] = "September";
        mon[9] = "October";
        mon[10] = "November";
        mon[11] = "December";

        --m;
        ++m;
        for (i = m; i < 12; i++)
        {
            if (document.getElementById("deMonth").value != null && document.getElementById("deMonth").value > 0)
            {
                addOption(obj1, mon[i], i, 1)
            }
            else
            {
                addOption(obj1, mon[i], i, -1);

            }


        }
    }
    else
    {
        removeOptions(obj);
        removeOptions(obj1);
        removeOptions(obj2);
        addOption(obj, "Selection Required", -1);
        addOption(obj1, "Selection Required", -1);
        addOption(obj2, "Selection Required", -1);

    }
}

function checkforfixper(obj, obj1)
{

    if (obj.value == 'P')
    {
        if (obj1.value > 100)
        {
            obj1.value = '';
        }

    }

}
function checkforchardesc(evt, spanid)
{
    var txt = "<span class='error_color'>Enter Character with - & _ Only</span>";

    var charCode = (evt.which) ? evt.which : evt.keyCode

    // alert("====charCode ===="+charCode);

    if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) || charCode == 32 || charCode == 13 || charCode == 8 || charCode == 45 || charCode == 95)

    {

        document.getElementById(spanid).innerHTML = "";
        return true;

    }
    document.getElementById(spanid).innerHTML = txt;
    return false;

}
function isDecimalminusOnly(objId, helperMsg, spanid) {

    var elem = objId;
    var length = elem.value.length;
    var minusindex = elem.value.indexOf("-");
    var txt = "<span class='error_color'>" + helperMsg + "</span>";

    //var numericExpression = /^[-]?\d*\.?\d*$/;
    var numericExpression = /^[-]?([1-9]{1}[0-9]{0,}(\.[0-9]{0,2})?|0(\.[0-9]{0,2})?|\.[0-9]{1,2})$/;

    if (elem.value.match(numericExpression)) {

        document.getElementById(spanid).innerHTML = "";


        objId.style.backgroundColor = bgColor;

        return true;

    } else {

        // alert("Please enter Numeric values");

        objId.style.backgroundColor = errorColor;


        document.getElementById(spanid).innerHTML = txt;

        //  elem.focus();

        return false;

    }


}
function checkforFloatminus(evt, spanid)

{


    var charCode = (evt.which) ? evt.which : evt.keyCode

    // var txt="<p class='bodytext'>Only Enter Numeric value";

    var txt = "<span class='error_color'>Only Enter Numeric value</span>";
    //alert("charcode:->"+charCode);
    if ((charCode > 47 && charCode < 58) || charCode == 46 || charCode == 45 || charCode == 32 || charCode == 13 || charCode == 8 || charCode == 37 || charCode == 39 || charCode == 35 || charCode == 36)

    {

        if (charCode == 13)

        {

            return trapEnter(evt);

        }

        //document.getElementById(spanid).innerHTML = "";

        return true;

    }

    //document.getElementById(spanid).innerHTML = txt;

    return false;

}

function isAlphaSpaceOnly(obj, helperMsg, spanid) {

    var elem = obj;
    var alphaExp = /^[a-zA-Z, ]+$/;
    var txt = "<span class='error_color'>" + helperMsg + " <br/></span>";

    //alert("=== Value ====="+elem.value.charAt(0));
    if (elem.value.match(alphaExp)) {
        document.getElementById(spanid).innerHTML = "";
        obj.style.backgroundColor = bgColor;
        return true;
    } else {
        obj.style.backgroundColor = errorColor;
        document.getElementById(spanid).innerHTML = txt;
        return false;

    }
}
function checkallowchar(evt, spanid)

{

    //var txt="Enter Character Only"

    var txt = "<span class='error_color'>Enter Character Only</span>";

    var charCode = (evt.which) ? evt.which : event.keyCode

    // alert("====charCode ===="+charCode);

    if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) || charCode == 32 || charCode == 36 || charCode == 13 || charCode == 8)

    {


        return true;

    }

    return false;

}


function validateminusAmount(objId, helperMsg, spanid)
{

    var amt = objId.value;

    var startindex = amt.indexOf(".");

    var lastindex = amt.lastIndexOf(".");

    var startminus = amt.indexOf("-");

    var length = amt.length;


    var txt = "<span class='error_color'>" + helperMsg + " <br/></span>";

    if (startminus == 0)
    {
        if (startindex > 0 && lastindex > 0)
        {
            startindex = startindex - 1;
            lastindex = lastindex - 1;
        }

        length = length - 1;
    }

    if (startindex > 8) {


        objId.style.backgroundColor = errorColor;
        document.getElementById(spanid).innerHTML = txt;
        return false;
    }
    if (startindex != lastindex)

    {

        objId.style.backgroundColor = errorColor;

        document.getElementById(spanid).innerHTML = txt;

        return false;

    }

    if (startindex > 0)

    {

        if (length == lastindex)

        {

            objId.style.backgroundColor = bgColor;

            document.getElementById(spanid).innerHTML = "";

            return true;

        }

        if ((length - 1) == lastindex)

        {

            objId.style.backgroundColor = bgColor;

            document.getElementById(spanid).innerHTML = "";

            return true;

        }


        else if ((length - 2) == lastindex)

        {

            objId.style.backgroundColor = bgColor;

            document.getElementById(spanid).innerHTML = "";

            return true;

        }


        else if ((length - 3) != lastindex)

        {

            objId.style.backgroundColor = errorColor;

            document.getElementById(spanid).innerHTML = txt;

            return false;

        }

    } else if (startindex == -1) {

        if (length > 8) {
            objId.style.backgroundColor = errorColor;
            document.getElementById(spanid).innerHTML = txt;
            return false;
        }

    }

    objId.style.backgroundColor = bgColor;

    document.getElementById(spanid).innerHTML = "";

    return true;

}
function validateminusAmount14(objId, helperMsg, spanid)
{

    var amt = objId.value;

    var startindex = amt.indexOf(".");

    var lastindex = amt.lastIndexOf(".");

    var startminus = amt.indexOf("-");

    var length = amt.length;


    var txt = "<span class='error_color'>" + helperMsg + " <br/></span>";

    if (startminus == 0)
    {
        if (startindex > 0 && lastindex > 0)
        {
            startindex = startindex - 1;
            lastindex = lastindex - 1;
        }

        length = length - 1;
    }

    if (startindex > 14) {


        objId.style.backgroundColor = errorColor;
        document.getElementById(spanid).innerHTML = txt;
        return false;
    }
    if (startindex != lastindex)

    {

        objId.style.backgroundColor = errorColor;

        document.getElementById(spanid).innerHTML = txt;

        return false;

    }

    if (startindex > 0)

    {

        if (length == lastindex)

        {

            objId.style.backgroundColor = bgColor;

            document.getElementById(spanid).innerHTML = "";

            return true;

        }

        if ((length - 1) == lastindex)

        {

            objId.style.backgroundColor = bgColor;

            document.getElementById(spanid).innerHTML = "";

            return true;

        }


        else if ((length - 2) == lastindex)

        {

            objId.style.backgroundColor = bgColor;

            document.getElementById(spanid).innerHTML = "";

            return true;

        }


        else if ((length - 3) != lastindex)

        {

            objId.style.backgroundColor = errorColor;

            document.getElementById(spanid).innerHTML = txt;

            return false;

        }

    } else if (startindex == -1) {

        if (length > 14) {
            objId.style.backgroundColor = errorColor;
            document.getElementById(spanid).innerHTML = txt;
            return false;
        }

    }

    objId.style.backgroundColor = bgColor;

    document.getElementById(spanid).innerHTML = "";

    return true;

}

function greaterThanobject(objId, value, helperMsg, spanid) {

    var elem = parseFloat(value);
    var valueobj = parseFloat(objId.value);

    var txt = "<span class='error_color'>" + helperMsg + "</span>";

    if (valueobj >= elem) {

        document.getElementById(spanid).innerHTML = "";

        objId.style.backgroundColor = bgColor;

        return true;

    } else {

        objId.style.backgroundColor = errorColor;

        document.getElementById(spanid).innerHTML = txt;

        return false;

    }

}
function lessThanobject(objId, value, helperMsg, spanid) {

    var elem = parseFloat(value);
    var valueobj = parseFloat(objId.value);

    var txt = "<span class='error_color'>" + helperMsg + "</span>";

    if (valueobj <= elem) {

        document.getElementById(spanid).innerHTML = "";

        objId.style.backgroundColor = bgColor;

        return true;

    } else {

        objId.style.backgroundColor = errorColor;

        document.getElementById(spanid).innerHTML = txt;

        return false;

    }

}


function emailValidatornew(elem, helperMsg, spanid) {
    var txt = "<span class='error_color'>" + helperMsg + " <br/></span>";
    //var emailExp = /^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.(aero$|asia$|lb$|biz$|cat$|com$|coop$|edu$|gov$|info$|jobs$|mil$|mobi$|museum$|name$|net$|org$|travel$|int$|in$|us$|uk$|co.in$|co.uk$|co.us$|co.lb$)/;
    //var emailExp = /^(\".\"|[A-Za-z0-9]_\-\.\w*)@(\[\d{1,3}(\.\d{1,3}){3}]|[A-Za-z]\w*(\.[A-Za-z]\w)+)$/;
    var emailExp = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;


    if (elem.value.match(emailExp))
    {
        elem.style.backgroundColor = bgColor;
        document.getElementById(spanid).innerHTML = "";
        //blackstar(span_ast);


        return true;
    }
    else
    {
        elem.style.backgroundColor = errorColor;
        document.getElementById(spanid).innerHTML = txt;
        elem.focus();
        //redstar(span_ast);
        return false;
    }

}

function validEmailnew(elem, helperMsg, spanid) {
    var txt = "<span class='error_color'>" + helperMsg + " <br/></span>";

    var StopPos1 = elem.value.split("@");
    //alert(StopPos1);

    var StopPos = StopPos1[1].split(".");
    //alert(StopPos);

    //alert(StopPos.length);
    if (StopPos.length > 3) {
        elem.style.backgroundColor = errorColor;
        document.getElementById(spanid).innerHTML = txt;
        elem.focus();
        //redstar(span_ast);
        return false;
    } else {
        elem.style.backgroundColor = bgColor;
        document.getElementById(spanid).innerHTML = "";
        //blackstar(span_ast);
        return true;

    }
}
function checkforalphanumericnospace(evt, spanid)

{

    //var txt="<p class='bodytext'>Enter Alphanumeric"

    var txt = "<span class='error_color'>Enter Alphanumeric</span>";

    var charCode = (evt.which) ? evt.which : evt.keyCode
    //alert (charCode);
    if ((charCode > 47 && charCode < 58) || (charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) || charCode == 13 || charCode == 8)

    {

        return true;

    }

    return false;

}


 function validateIPBoth(obj, spanID, name) {


    errorString = "";
    theName = name + " address";
    returnval = true;
    var IPvalue = obj.value;
    var ipPatternV6 = /^([a-f0-9]{1,4})+\:([a-f0-9]{1,4})+\:([a-f0-9]{1,4})+\:([a-f0-9]{1,4})+\:([a-f0-9]{1,4})+\:([a-f0-9]{1,4})+\:([a-f0-9]{1,4})+\:([a-f0-9]{1,4})$/;
    var ipArrayV6 = IPvalue.match(ipPatternV6);


    var ipPatternV4 = /^(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})$/;
    var ipArrayV4 = IPvalue.match(ipPatternV4);


     if (ipArrayV4 || ipArrayV6 ) {

        /* for ipv4 validation*/
          if(ipArrayV4){
                            if (IPvalue == "0.0.0.0")
                            errorString = errorString + theName + ': ' + IPvalue + ' is a special ' + name + ' address and cannot be used here.';
                            else if (IPvalue == "255.255.255.255")
                            errorString = errorString + theName + ': ' + IPvalue + ' is a special ' + name + ' address and cannot be used here.';
                            if (ipArrayV4 == null)
                            errorString = errorString + theName + ': ' + IPvalue + ' is not a valid ' + name + ' address.';
                            else {
                            for (i = 0; i < 4; i++) {
                            thisSegment = ipArrayV4[i];
                            if (thisSegment > 255) {
                            errorString = errorString + theName + ': ' + IPvalue + ' is not a valid ' + name + ' address.';
                            i = 4;
                            }
                            if ((i == 0) && (thisSegment > 255)) {
                            errorString = errorString + theName + ': ' + IPvalue + ' is a special ' + name + ' address and cannot be used here.';
                            i = 4;
                            }
                            }
                            }
                            extensionLength = 3;
                            if (errorString == "") {
                            obj.style.backgroundColor =  bgColor;
                            document.getElementById(spanID).innerHTML = "";
                            returnval = true;

                            } else {

                            obj.style.backgroundColor = errorColor;
                            document.getElementById(spanID).innerHTML = "<span class='error_color'>" + errorString + " <br/></span>";
                            returnval = false;
                            }


          }

         /* for ipv6 validation*/
          if(ipArrayV6){
           obj.style.backgroundColor = bgColor;
           document.getElementById(spanID).innerHTML = "";
           return true;
          }


    } else {
        errorString = errorString + theName + ': ' + IPvalue + ' is not a valid ' + name + ' address.';
        obj.style.backgroundColor = errorColor;
        document.getElementById(spanID).innerHTML = "<span class='error_color'>" + errorString + " <br/></span>";
        returnval = false;
    }
    return returnval;
}

 function validateIP6(obj, spanID, name) {


    errorString = "";
    theName = name + " address";
    returnval = true;
    var IPvalue = obj.value;    
    var ipPattern = /^([a-f0-9]{1,4})+\:([a-f0-9]{1,4})+\:([a-f0-9]{1,4})+\:([a-f0-9]{1,4})+\:([a-f0-9]{1,4})+\:([a-f0-9]{1,4})+\:([a-f0-9]{1,4})+\:([a-f0-9]{1,4})$/;
    var ipArray = IPvalue.match(ipPattern);

     if (ipArray) {
           obj.style.backgroundColor = bgColor;
           document.getElementById(spanID).innerHTML = "";
           return true;
    } else {
        errorString = errorString + theName + ': ' + IPvalue + ' is not a valid ' + name + ' address.';
        obj.style.backgroundColor = errorColor;
        document.getElementById(spanID).innerHTML = "<span class='error_color'>" + errorString + " <br/></span>";
        returnval = false;
    }
    return returnval;
}

function validateIP(obj, spanID, name) {
    errorString = "";
    theName = name + " address";
    returnval = true;
    var IPvalue = obj.value;

    var ipPattern = /^(\d{1,3})\.(\d{1,3})\.(\d{1,3})\.(\d{1,3})$/;
    var ipArray = IPvalue.match(ipPattern);

    if (IPvalue == "0.0.0.0")
        errorString = errorString + theName + ': ' + IPvalue + ' is a special ' + name + ' address and cannot be used here.';
    else if (IPvalue == "255.255.255.255")
        errorString = errorString + theName + ': ' + IPvalue + ' is a special ' + name + ' address and cannot be used here.';
    if (ipArray == null)
        errorString = errorString + theName + ': ' + IPvalue + ' is not a valid ' + name + ' address.';
    else {
        for (i = 0; i < 4; i++) {
            thisSegment = ipArray[i];
            if (thisSegment > 255) {
                errorString = errorString + theName + ': ' + IPvalue + ' is not a valid ' + name + ' address.';
                i = 4;
            }
            if ((i == 0) && (thisSegment > 255)) {
                errorString = errorString + theName + ': ' + IPvalue + ' is a special ' + name + ' address and cannot be used here.';
                i = 4;
            }
        }
    }
    extensionLength = 3;
    if (errorString == "") {
        obj.style.backgroundColor =  bgColor;
        document.getElementById(spanID).innerHTML = "";
        returnval = true;

    } else {

        obj.style.backgroundColor = errorColor;
        document.getElementById(spanID).innerHTML = "<span class='error_color'>" + errorString + " <br/></span>";
        returnval = false;
    }
    return returnval;
}

function validateMac(obj, spanID) {
    errorString = "";
    theName = "MAC address";
    returnval = true;
    var Macvalue = obj.value;

    regex = /^([0-9a-f]{2}([:-]|$)){6}$|([0-9a-f]{4}([.]|$)){3}$/i;

    if (!regex.test(Macvalue) || Macvalue.length != 17) {
        errorString = 'MAC address is not valid';
    }
    else {
        errorString = "";
    }
    if (errorString == "") {
        obj.style.backgroundColor = bgColor;
        document.getElementById(spanID).innerHTML = "";
    } else {

        obj.style.backgroundColor = errorColor;
        document.getElementById(spanID).innerHTML = "<span class='error_color'>" + errorString + " <br/></span>";
        returnval = false;
    }
    return returnval;


}

function dateOrder()
{

    var debug = false;
    var returnOrderVal = true;
    var returnOrderFlag = true;
    var validOrder = true;

    var date_from = document.forms[0].bod.value;
    var date_to = document.forms[0].eod.value;

    df = date_from.split("/");
    dt = date_to.split("/");
    df_mon = df[0];
    df_day = df[1];
    df_rest = df[2];
    dff = df_rest.split(" ");
    df_year = dff[0];
    df_mode = dff[2];
    df_time = dff[1];

    dt_mon = dt[0];
    dt_day = dt[1];
    dt_rest = dt[2];

    dtt = dt_rest.split(" ");
    dt_year = dtt[0];
    dt_mode = dtt[2];
    dt_time = dtt[1];


    if (((df_day == dt_day) && (df_mon == dt_mon) && (df_year == dt_year)) && ((df_time == dt_time) && (df_mode == dt_mode)))

    {

        document.getElementById("eod_err").innerHTML = "<font color='red'>Start and End dates are same.</font>";
        // document.getElementById("eod_ast").innerHTML="<font size=2 class='red_color'>*</font>";
        document.getElementById("eod").style.backgroundColor = "#FFFFCC";
        document.getElementById("eod").focus();
        returnOrderVal = false;
        returnOrderFlag = false;
    }
    else
    {
        if (df_year > dt_year)
        {
            document.getElementById("eod_err").innerHTML = "<font color='red'>Start year should be of before of end year.</font>";
            //document.getElementById("eod_ast").innerHTML="<font size=2 class='red_color'>*</font>";
            document.getElementById("eod").style.backgroundColor = "#FFFFCC";
            document.getElementById("eod").focus();
            returnOrderVal = false;
            returnOrderFlag = false;
        }
        else if ((df_year == dt_year) && (df_mon > dt_mon))
        {

            document.getElementById("eod_err").innerHTML = "Start month should be of before of end month.";
            //  document.getElementById("eod_ast").innerHTML="<font size=2 class='red_color'>*</font>";
            document.getElementById("eod").style.backgroundColor = "#FFFFCC";
            document.getElementById("eod").focus();
            returnOrderVal = false;
            returnOrderFlag = false;
        }

        else if ((df_year == dt_year) && (df_mon == dt_mon) && (df_day > dt_day))

        {

            document.getElementById("eod_err").innerHTML = "<font color='red'>Start day should be of before of end day.</font>";
            //  document.getElementById("eod_ast").innerHTML="<font size=2 class='red_color'>*</font>";
            document.getElementById("eod").style.backgroundColor = "#FFFFCC";

            //document.getElementById("eod").focus();
            returnOrderVal = false;
            returnOrderFlag = false;

        }

        else if (((df_year == dt_year) && (df_mon == dt_mon) && (df_day == dt_day)) && ((df_mode == dt_mode) && (df_time > dt_time)))

        {

            document.getElementById("eod_err").innerHTML = "<font color='red'>Time should be in order.</font>";
            // document.getElementById("eod_ast").innerHTML="<font size=2 class='red_color'>*</font>";
            document.getElementById("eod").style.backgroundColor = "#FFFFCC";

            document.getElementById("eod").focus();

            returnOrderVal = false;

            returnOrderFlag = false;

        }

        else if (((df_year == dt_year) && (df_mon == dt_mon) && (df_day == dt_day)) && ((df_mode > dt_mode) && (df_time == dt_time)))

        {

            document.getElementById("eod_err").innerHTML = "<font color='red'>Time mode should be in order.</font>";
            // document.getElementById("eod_ast").innerHTML="<font size=2 class='red_color'>*</font>";
            document.getElementById("eod").style.backgroundColor = "#FFFFCC";
            document.getElementById("eod").focus();
            returnOrderVal = false;
            returnOrderFlag = false;
        }

        else if (((df_year == dt_year) && (df_mon == dt_mon) && (df_day == dt_day)) && (df_mode > dt_mode))

        {

            document.getElementById("eod_err").innerHTML = "<font color='red'>Time must be in order.</font>";
            // document.getElementById("eod_ast").innerHTML="<font size=2 class='red_color'>*</font>";
            document.getElementById("eod").style.backgroundColor = "#FFFFCC";
            document.getElementById("eod").focus();
            returnOrderVal = false;
            returnOrderFlag = false;

        }

        else

        {

            document.getElementById("eod_err").innerHTML = "";
            // document.getElementById("eod_ast").innerHTML="<font size=2 class='black_color'>*</font>";
            document.getElementById("eod").style.backgroundColor = "#FFFFff";

        }

    }

    validOrder = returnOrderFlag;

    return validOrder;

}


var _validFileExtensions = [".jpg", ".jpeg", ".bmp", ".gif", ".png"];

function checkLogoFileName(objId1, helperMsg, spanid) {
    var txt = "<span class='error_color'>" + helperMsg + " <br/></span>";

    var sFileName = objId1.value;
    if (sFileName.length > 0) {
        var blnValid = false;
        for (var j = 0; j < _validFileExtensions.length; j++) {
            var sCurExtension = _validFileExtensions[j];
            if (sFileName.substr(sFileName.length - sCurExtension.length, sCurExtension.length).toLowerCase() == sCurExtension.toLowerCase()) {
                blnValid = true;
                objId1.style.backgroundColor = bgColor;
                document.getElementById(spanid).innerHTML = "";
                return true;
                break;

            }
        }

        if (!blnValid) {
            // alert("Sorry, " + sFileName + " is invalid, allowed extensions are: " + _validFileExtensions.join(", "));
            objId1.style.backgroundColor = errorColor;
            document.getElementById(spanid).innerHTML = txt;
            return false;
        }

    }


}


function reset(objId1, spanid) {

    objId1.style.backgroundColor = bgColor;
    document.getElementById(spanid).innerHTML = "";
    return true;
}


function validateTag(elem, helperMsg, spanid) {
     var txt = "<span class='error_color'>" + helperMsg + " <br/></span>";  

    var alphaExp = /^[a-z A-Z 0-9<b></b>]+$/;
    var textValue = elem.value;
    var chValid = true;
    for (var i = 0; i < textValue.length; i++)
    {
       
        if (textValue.charAt(i) == '<')
        {

            if (textValue.charAt(++i) == 'b')
            {
                if (textValue.charAt(++i) == '>')
                { continue; }
                else
                   // return false;
                  chValid=false;

            }

            else   if (textValue.charAt(i) == '/')
            {
                if (textValue.charAt(++i) == 'b')
                {
                    if (textValue.charAt(++i) == '>')
                         chValid=true;
                    else
                         chValid=false;
                }
                 else
                     chValid=false;
            }
            else
                 chValid=false;
        }
        else
        {   
            continue;
        }

    }

    if(chValid){
        elem.style.backgroundColor = bgColor;
        document.getElementById(spanid).innerHTML = "";
        return true;
    } else{
         elem.style.backgroundColor = errorColor;
        document.getElementById(spanid).innerHTML = txt;
        elem.focus();
        return false;
    }





    if (elem.value.match(alphaExp))
    {
        elem.style.backgroundColor = bgColor;
        document.getElementById(spanid).innerHTML = "";
        return true;
    } else {

        elem.style.backgroundColor = errorColor;
        document.getElementById(spanid).innerHTML = txt;
        elem.focus();
        return false;

    }

}
