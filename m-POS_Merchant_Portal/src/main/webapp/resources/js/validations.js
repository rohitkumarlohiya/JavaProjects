function notEmpty(objId) {

    var elem = objId;

    if (elem.value.length == 0) {

        alert("Please provide an input");

        elem.focus(); // set the focus to this input

        return false;

    }

    return true;

}


function isNumeric(objId) {

    var elem = objId;

    var numericExpression = /^[0-9]+$/;

    if (elem.match(numericExpression)) {

        return true;

    } else {

        // alert("Please enter Numeric values");

        elem.focus();

        return false;

    }

}


function isAlphabet(objId) {

    //var elem = document.getElementById(objId);

    var elem = objId;

    var alphaExp = /^[a-zA-Z]+$/;

    if (elem.value.match(alphaExp)) {

        return true;

    } else {

        // alert("Please enter Alphabet values only");

        elem.focus();

        return false;

    }

}


function isValidName(objId) {

    //var elem = document.getElementById(objId);

    var elem = objId;

    var alphaExp = /^[a-zA-Z]+$/;

    var illegalChars = /\W/; // allow letters, numbers, and underscores


    if (objId.value == "" || objId.value.length == 0) {

        //objId.style.background = 'Yellow';

        alert("You didn't enter the name");

        return false;

    } else if ((objId.value.length < 3) || (objId.value.length > 15)) {

        //objId.style.background = 'Yellow';

        alert("The name is of wrong length");

        return false;

    } else if (illegalChars.test(objId.value)) {

        //objId.style.background = 'Yellow';

        alert("The name contains illegal characters");

        return false;

    } else {

        //objId.style.background = 'White';

        return true;

    }

}


function isAlphanumeric(objId) {

    var elem = objId;

    var alphaExp = /^[0-9a-zA-Z]+$/;


    if (elem.value.match(alphaExp)) {

        return true;

    } else {

        // alert("Please enter Alphanumeric values");

        elem.focus();

        return false;

    }

}


function isAlpha(objId) {

    var elem = objId;

    var alphaExp = /^[a-z A-Z]+$/;


    if (elem.value.match(alphaExp)) {

        return true;

    } else {

        // alert("Please enter Alphanumeric values");

        elem.focus();

        return false;

    }

}


function lengthRestriction(objId, min, max) {

    var elem = document.getElementById(objId);

    var uInput = elem.value;

    if (uInput.length >= min && uInput.length <= max) {

        return true;

    } else {

        if (max == min)

            alert("Please enter " + max + " characters only")

        else

            alert("Please enter between " + min + " and " + max + " characters");

        elem.focus();

        return false;

    }

}


function isDate(objId) {

    try

    {

        var elem = document.getElementById(objId);

        var dob = new Date(elem.value);

    } catch(e)

    {

        return false;

    }


    if (dob == "Invalid Date")

    {

        alert("Please enter correct date in format DD/MM/YYYY")

        elem.focus();

        return false;

    }


    return true;

}


function validEmail(objId) {


    var elem = document.getElementById(objId);

    var email = elem.value;

    var AtPos = email.indexOf("@");

    var StopPos = email.lastIndexOf(".");


    if ((email == "") || (AtPos == -1 || StopPos == -1) || (StopPos < AtPos) || (StopPos - AtPos == 1) || AtPos < 1) {

        alert("Please enter a valid email address");

        elem.focus();

        return false;

    }

    else

    {

        return true;

    }


}


function isBoolean(objId) {

    var elem = objId;

    var booleanExp = /^[nNyY]+$/;

    if (elem.value.match(booleanExp)) {

        return true;

    } else {

        alert("Only 'Y' or 'N' values are allowed");

        elem.focus();

        return false;

    }

}


function isFloat(objId)

{

    //alert("=== test ==");

    var s = objId.value;

    var elem = objId;

    if (isWhitespace(s)) {

        alert("Please enter Numeric values");

        elem.focus();

        return false;

    }


    var seenDecimalPoint = false;

    if (s == '.') {

        alert("Please enter Numeric values");

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

            alert("Please enter Numeric values");

            elem.focus();

            return false;

        }

    }


    // All characters are numbers.

    return true;

}


function isWhitespace(s) {

    var whitespace = " \t\n\r";


    if (s.length == 0) {

        // empty field!

        return true;

    } else {

        // check for whitespace now!

        for (var z = 0; z < s.length; z++) {

            // Check that current character isn't whitespace.

            var c = s.charAt(z);

            if (whitespace.indexOf(c) == -1) return false;

        }

        return true;

    }

}


function isDigit(c)

{

    return ((c >= "0") && (c <= "9"));

}


function isMinGrMax(minValue1, maxValue1) {

    //var elem = document.getElementById(objId);

    //alert("=== Call function minimum");

    var minValue;

    minValue = minValue1;

    //alert("minValue"+minValue);

    var maxValue;

    maxValue = maxValue1;

    //alert("maxValue"+maxValue);

    if (minValue > maxValue)

    {

        alert("Minimum Value can not be greater than Maximum Value");

        //minValue.focus();

        return false;

    } else {

        return true;

    }

}


/*

 function validateName(objId) {



 var fld = document.getElementById(objId);

 var error = "";

 //alert("Inside validateName");

 var illegalChars = /\W/; // allow letters, numbers, and underscores



 if (fld.value == "") {

 fld.style.background = 'Yellow';

 alert("You didn't enter a username. ");

 } else if ((fld.value.length < 5) || (fld.value.length > 15)) {

 fld.style.background = 'Yellow';
 alert("The name is of wrong length.");

 } else if (illegalChars.test(fld.value)) {

 fld.style.background = 'Yellow';

 alert("The name contains illegal characters");

 } else {

 fld.style.background = 'White';

 return true;

 }

 return false;

 }

 */


function checkOptionValue(objId) {

    var elem = objId;


    if (elem.value != -1) {

        return true;

    } else {

        alert("Please select valid option");

        elem.focus();

        return false;

    }

}


////



// Additional function added by Atul


function my_notEmpty(objId, spnid) {

    var elem = objId;

    if (elem.value.length == 0)

    {

        document.getElementById(spnid).innerHTML = "&nbsp;&nbsp;Please provide an input";

        objId.style.background = '#FFFFCC';

        elem.focus(); // set the focus to this input

        return false;

    }

    else

    {

        document.getElementById(spnid).innerHTML = "";

        objId.style.background = 'white';

    }

    return true;

}


function my_isAlpha(objId, spnid) {

    var elem = objId;

    var alphaExp = /^[a-z A-Z]+$/;


    if (elem.value.match(alphaExp))

    {

        document.getElementById(spnid).innerHTML = "";

        objId.style.background = 'white';

        return true;

    }

    else {

        document.getElementById(spnid).innerHTML = "&nbsp;&nbsp;Please enter Character values";

        objId.style.background = '#FFFFCC';

        elem.focus();

        return false;

    }

}


function my_isFloat(objId, spnid)

{

    var s = objId.value;

    var elem = objId;

    if (isWhitespace(s))

    {

        document.getElementById(spnid).innerHTML = "&nbsp;&nbsp;Please enter Numeric values";

        objId.style.background = '#FFFFCC';

        elem.focus();

        return false;

    }

    else

    {

        document.getElementById(spnid).innerHTML = "";

        objId.style.background = 'white';

    }


    var seenDecimalPoint = false;

    if (s == '.') {

        document.getElementById(spnid).innerHTML = "&nbsp;&nbsp;Please enter Numeric values";

        // alert("Please enter Numeric values");

        objId.style.background = '#FFFFCC';

        elem.focus();

        return false;

    }

    else

    {

        document.getElementById(spnid).innerHTML = "";

        objId.style.background = 'white';

    }


    // Search through string's characters one by one until we find a non-numeric character.

    // When we do, return false; if we don't, return true.

    for (var i = 0; i < s.length; i++) {

        // Check that current character is number.

        var c = s.charAt(i);

        if ((c == '.') && !seenDecimalPoint) {

            seenDecimalPoint = true;

        } else if (!isDigit(c)) {

            document.getElementById(spnid).innerHTML = "&nbsp;&nbsp;Please enter Numeric values";

            objId.style.background = '#FFFFCC';

            elem.focus();

            return false;

        }

        else {
            document.getElementById(spnid).innerHTML = "";

            objId.style.background = 'white';
        }

    }

    // All characters are numbers.

    return true;

}


// Additional function added by Me


function checkEmail(elem, helperMsg, spanid) {
    var txt = "<span class='error_color'>" + helperMsg + "</span>";
    var knownDomsPat = new Array("com", "co.in", "net", "org", "edu", "int", "mil", "gov");
    var validEmail = false;
    var emailExp = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    var emailStr = elem.value;
    if (emailStr.match(emailExp))///search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) != -1)
    {


        var afterAt = emailStr.slice(emailStr.indexOf("@") + 1, emailStr.length);

        var domain = afterAt.split(".");

        if (domain.length == 2) {

            for (var i = 0; i < knownDomsPat.length; i++) {

                if (domain[1] == knownDomsPat[i]) {

                    validEmail = true;
                    document.getElementById(spanid).innerHTML = "";

                    elem.style.backgroundColor = bgColor;
                }
            }


        }
        else if (domain.length == 3) {

            var domainToCheck = domain[1] + "." + domain[2];


            for (var i = 0; i < knownDomsPat.length; i++) {

                if (domainToCheck == knownDomsPat[i]) {
                    validEmail = true;
                    document.getElementById(spanid).innerHTML = "";

                    elem.style.backgroundColor = bgColor;
                }
            }


        }
    } else {
        document.getElementById(spanid).innerHTML = txt;
        objId.style.backgroundColor = errorColor;
        elem.focus();


        validEmail = false;

    }

    return validEmail;


}