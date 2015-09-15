function MyTrapEnter(This, evt, type) {
    var keycode;
    var next = This.tabIndex;
    var charCode = (evt.which) ? evt.which : event.keyCode
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
        return validateForm();
    }
    else {
        if (type == "numeric") {
            return isNumericMPOS(charCode);
        }
        else if (type == "Alphanumeric") {
            return isAlphaNumericMPOS(charCode);
        }
        else if (type == "alpha") {
            return isAlphabatMPOS(charCode);
        }
        else if (type == "email") {
            return isEmailMPOS(charCode);
        }
        else if (type == "address") {
            return isAddressMPOS(charCode);
        }
        else {
            return true;
        }
    }
}

function isAlphaNumericMPOS(charCode) {
    if ((charCode > 47 && charCode < 58) || (charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) || charCode == 32 || charCode == 8)
        return true;
    else
        return false;
}

function isNumericMPOS(charCode) {
    if (charCode > 31 && (charCode < 48 || charCode > 57))
        return false;
    else
        return true;
}

function isAlphabatMPOS(charCode) {
    if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) || charCode == 32 || charCode == 8)
        return true;
    else
        return false;
}

function isEmailMPOS(charCode) {
    if ((charCode > 47 && charCode < 58) || (charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) || charCode == 64 || charCode == 45 || charCode == 95 || charCode == 46 || charCode == 8)//support for @/./-/_ numbers and alphabats
        return true;
    else
        return false;
}

function isAddressMPOS(charCode) {
    if ((charCode > 47 && charCode < 58) || (charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123) || charCode == 64 || charCode == 95 || charCode == 46 || charCode == 8
        || charCode == 35 || charCode == 44 || charCode == 58 || charCode == 40 || charCode == 41 || charCode == 47)//support for @ . _ numbers and alphabats # , : ' ( ) /
        return true;
    else
        return false;
}

function isCorrect(input, type, errorId, errorMsg) {
    var alpha = /^[A-Za-z\s]+$/;
    var numeric = /^[0-9]+$/;
    var alphaNumeric = /^[A-Za-z0-9\s]+$/;
    //To check a password between 8 to 12 characters which contain at least one numeric digit and a special character
    var password = /^(?=.*[0-9])(?=.*[@._#,:()])[a-zA-Z0-9@._#,:()]{8,12}$/;

   /*
    (			# Start of group
        (?=.*\d)		#   must contains one digit from 0-9
        (?=.*[a-z])		#   must contains one lowercase characters
        (?=.*[A-Z])		#   must contains one uppercase characters
        (?=.*[@#$%])		#   must contains one special symbols in the list "@#$%"
        .		#     match anything with previous condition checking
    {6,20}	#        length at least 6 characters and maximum of 20
    )			# End of group
*/

    var email = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /*if(input == null || input == "")
     {
     document.getElementById(errorId).innerHTML="<spring:message code='common.message.error.field'/>";
     return false;
     }*/


    if (type == "numeric") {


        if (input.match(numeric))
            return true;
        else {

            document.getElementById(errorId).innerHTML = errorMsg;
            return false;
        }

    }
    else if (type == "Alphanumeric") {


        if (input.match(alphaNumeric))
            return true;
        else {
            document.getElementById(errorId).innerHTML = errorMsg;
            return false;
        }
    }
    else if (type == "alpha") {

        if (input.match(alpha)) {

            return true;
        }
        else {

            document.getElementById(errorId).innerHTML = errorMsg;
            return false;
        }
    }
    else if (type == "email") {

        /*var atpos = input.indexOf("@");
         var dotpos = input.lastIndexOf(".");
         if (!(atpos < 1 || dotpos < atpos + 2 || dotpos + 2 >= input.length)) {
         return true;
         }
         else {
         document.getElementById(inputId).innerHTML = errorMsg;
         return false;
         }*/

        if (input.match(email)) {
            return true;
        }
        else {
            document.getElementById(errorId).innerHTML = errorMsg;
            return false;
        }
    }

    else if (type == "address") {

        for (var i = 0; i < input.length; i++) {
            var flag = true;
            var charCode1 = input.charCodeAt(i);

            if (!((charCode1 > 47 && charCode1 < 58) || (charCode1 > 64 && charCode1 < 91) || (charCode1 > 96 && charCode1 < 123) || charCode1 == 64 || charCode1 == 95 || charCode1 == 46 || charCode1 == 8
                || charCode1 == 35 || charCode1 == 44 || charCode1 == 58 || charCode1 == 40 || charCode1 == 41 || charCode1 == 47))//support for @ . _ numbers and alphabats # , : ( ) /
            {
                flag = false;
                break;
            }
        }
        if (!flag) {
            document.getElementById(errorId).innerHTML = errorMsg;
        }
        return flag;
    }

    else if (type == "password") {

       //  alert("inside password"+input);
        if (input.match(password)) {
            return true;
        }
        else {
            document.getElementById(errorId).innerHTML = errorMsg;
            return false;
        }
    }
    else if (type == "date") {

        var today = new Date();
        var datePart = input.split("/");
        var year = datePart[2];
        var month = datePart[0];
        var day = datePart[1];
        var myDate= new Date(year,month-1,day);

        if(myDate>today){
            document.getElementById(errorId).innerHTML = errorMsg;
            return false;
        }
        else
            return true;


     /*   var today = new Date();
        var dd = today.getDate();
        var mm = today.getMonth() + 1; // January is 0!
        var yyyy = today.getFullYear();

        var datePart = input.split("/");
        var year = datePart[2];
        var month = datePart[0];
        var day = datePart[1];

        if (year >= yyyy) {
            if (month >= mm) {
                if (day > dd) {
                    document.getElementById(errorId).innerHTML = errorMsg;
                    return false;
                }
                else
                    return true;
            }
            else
                return true;
        }
        else
            return true;      */

    }
    else
        return true;
}

function isMinMax(input, minVal, maxVal, errorId, errorMsg) {
    if ((input.length < minVal) || (input.length > maxVal)) {
        document.getElementById(errorId).innerHTML = errorMsg;
        return false;
    }
    else {
        return true;
    }
}

function setFocus(value) {
    value.focus();
}


function beforeSubmit(formID)   //To trim all form fields data before form submission.
{
    var array = document.getElementById(formID);
    for (var i = 0; i < array.length; i++) {
        array.elements[i].value = array.elements[i].value.replace(/^\s+|\s+$/g, "");
    }
}


/*
 this will check for right click*/

var message = "This function is not allowed here.";
function clickIE4() {

    if (event.button == 2) {
        alert(message);
        return false;
    }
}

function clickNS4(e) {
    if (document.layers || document.getElementById && !document.all) {
        if (e.which == 2 || e.which == 3) {
            alert(message);
            return false;
        }
    }
}

if (document.layers) {
    document.captureEvents(Event.MOUSEDOWN);
    document.onmousedown = clickNS4;
}

else if (document.all && !document.getElementById) {
    document.onmousedown = clickIE4;
}

document.oncontextmenu = new Function("alert(message);return false;")


/*
 this will check for right click*/


function after(sdate, sdateErrorID, edate, edateErrorID) {

    var sdatePart = sdate.split("/");
    var edatePart = edate.split("/");

    var startyear = sdatePart[2];
    var startmonth = sdatePart[0];
    var startday = sdatePart[1];

    var endyear = edatePart[2];
    var endmonth = edatePart[0];
    var endday = edatePart[1];

    /*var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth() + 1; // January is 0!
    var yyyy = today.getFullYear();
    if (startyear >= yyyy) {
        if (startmonth >= mm) {
            if (startday > dd) {
                // alert("Date is greater than current date");
                document.getElementById(sdateErrorID).innerHTML = "Future date selected";
                return false;
            }
        }

    }
    if (endyear >= yyyy) {
        if (endmonth >= mm) {
            if (endday > dd) {
                // alert("Date is greater than current date");
                document.getElementById(edateErrorID).innerHTML = "Future date selected";
                return false;
            }
        }

    }
*/
    /* if ((startyear == endyear) && (startmonth == endmonth) && (startday == endday)) {

     document.getElementById(sdateErrorID).innerHTML = "Start Date and End Date are identical";


     } else {*/
    if (endyear > startyear) {
        return true;
    } else if (endyear == startyear) {
        if (endmonth > startmonth) {
            return true;
        } else if (endmonth == startmonth) {
            if (endday >= startday) {
                return true;
            } else {
                document.getElementById(edateErrorID).innerHTML = "End date should be same or greater than Start date";
                return false;
            }

        } else {
            document.getElementById(edateErrorID).innerHTML = "End date should be same or greater than Start date";
            return false;
        }
    } else {
        document.getElementById(edateErrorID).innerHTML = "End date should be same or greater than Start date";
        return false;
    }
    // }
}

function fnSetDateFormat(oDateFormat)
{
    oDateFormat['FullYear'];		//Example = 2007
    oDateFormat['Year'];			//Example = 07
    oDateFormat['FullMonthName'];	//Example = January
    oDateFormat['MonthName'];		//Example = Jan
    oDateFormat['Month'];			//Example = 01
    oDateFormat['Date'];			//Example = 01
    oDateFormat['FullDay'];			//Example = Sunday
    oDateFormat['Day'];				//Example = Sun
    oDateFormat['Hours'];			//Example = 01
    oDateFormat['Minutes'];			//Example = 01
    oDateFormat['Seconds'];			//Example = 01

    var sDateString;
    //Example = 01/01/00  mm/dd/yy
    sDateString = oDateFormat['Month'] +"/"+ oDateFormat['Date'] +"/"+ oDateFormat['FullYear'];
    return sDateString;
}