//////

function Generic_rule(s, common_chk) {

    // var alphanumeric_chk = common_chk;
    var flag = true;
    if (s.length == 0) {
        // empty field!
        return false;
    } else {

        for (var z = 0; z < s.length; z++) {

            var c = s.charAt(z);

            if (common_chk.indexOf(c) == -1) return false;


        }


        return true;

    }

}


function GSDRule(objId, spanid)
{
    var helperMsg = "Please enter at least one alphabetic, one numeric and one special character as New Password.";
    var txt = "<span class='error_color'>" + helperMsg + " <br/></span>";
    var s = objId.value;
    var elem = objId;
    var alpha_chk = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    var number_chk = "0123456789";
    var SpecialChar = "!@#$%^&*()-_+=[{}}\|;:,?/<,>.'" + '"';

    var alphanumeric_chk = alpha_chk + number_chk;
    var alphanSpecial_chk = alpha_chk + SpecialChar;
    var numericSpecial_chk = number_chk + SpecialChar;

    if (isAlphaChk(objId, spanid)) {
        var helperMsg1 = "Initial two character should be alphabet";
        objId.style.backgroundColor = errorColor;
        document.getElementById(spanid).innerHTML = helperMsg1;
        //alert(helperMsg1);
        //validrule=false;

        return false;


    }


    if (passwd_length(objId, spanid)) {

        var helperMsg1 = "Password should be between 8 to 12 characters.";
        objId.style.backgroundColor = errorColor;
        document.getElementById(spanid).innerHTML = helperMsg1;
        // alert(helperMsg1);

        return false;
    }


    if (Generic_rule(s, alpha_chk)) {
        //alert("isAlphanumeric_chk");
        objId.style.backgroundColor = errorColor;
        document.getElementById(spanid).innerHTML = helperMsg;
        //alert(helperMsg);

        return false;
    }

    if (Generic_rule(s, number_chk)) {
        //alert("isAlphanumeric_chk");
        objId.style.backgroundColor = errorColor;
        document.getElementById(spanid).innerHTML = helperMsg;
        //alert(helperMsg);
        //validrule=false;

        return false;
    }
    if (Generic_rule(s, SpecialChar)) {
        //alert("isAlphanumeric_chk");
        objId.style.backgroundColor = errorColor;
        document.getElementById(spanid).innerHTML = helperMsg;
        //alert(helperMsg);
        //validrule=false;

        return false;
    }
    if (Generic_rule(s, alphanumeric_chk)) {
        //alert("isAlphanumeric_chk");
        objId.style.backgroundColor = errorColor;
        document.getElementById(spanid).innerHTML = helperMsg;
        //alert(helperMsg);
        //validrule=false;

        return false;
    }

    if (Generic_rule(s, alphanSpecial_chk)) {
        //alert("isAlphanumeric_chk");
        objId.style.backgroundColor = errorColor;
        document.getElementById(spanid).innerHTML = helperMsg;
        //alert(helperMsg);
        //validrule=false;

        return false;
    }

    if (Generic_rule(s, numericSpecial_chk)) {
        //alert("isAlphanumeric_chk");
        objId.style.backgroundColor = errorColor;
        document.getElementById(spanid).innerHTML = helperMsg;
        //alert(helperMsg);
        //validrule=false;

        return false;
    }


    if (isSpace_msg(objId, helperMsg, spanid)) {
        var helperMsg1 = "Space is not allowed";
        objId.style.backgroundColor = errorColor;
        document.getElementById(spanid).innerHTML = helperMsg1;
        //alert(helperMsg1);
        //validrule=false;

        return false;
    }

    /* if(isInitChkAlpha(objId,helperMsg, spanid)){
     var helperMsg1="Initial two character should be alphabet";
     objId.style.backgroundColor = errorColor;
     // document.getElementById(spanid).innerHTML = txt;
     alert(helperMsg1);
     //validrule=false;

     return false;

     }*/
    /*if (isAlphaChk(objId,spanid)){
     var helperMsg1="Initial two character should be alphabet";
     objId.style.backgroundColor = errorColor;
     document.getElementById(spanid).innerHTML = helperMsg1;
     //alert(helperMsg1);
     //validrule=false;

     return false;


     }*/

    objId.style.backgroundColor = bgColor;
    document.getElementById(spanid).innerHTML = "";
    return true;


}

function passwd_length(objId, spanid)
{
    var passwd_length = objId.value.length;
    minLength = '8';
    maxLength = '12';
    if (passwd_length < minLength || passwd_length > maxLength) {
        objId.style.backgroundColor = errorColor;

        return true;
    } else
    {
        objId.style.backgroundColor = bgColor;
        // document.getElementById(spanid).innerHTML = "";
        return false;
    }


}


function isSpace_msg(objId, helperMsg, spanid)
{
    // var helperMsg1="Space is not allowed";
    // var txt = "<span class='error_txt'>" + helperMsg1 + " <br/></span>";
    var s = objId.value;
    //alert(s);
    //	var elem = objId;
    ///NOTE ::  define here which is not allowed ie space
    var seenSpace = false;
    if (s == ' ') {
        return false;
    }

    for (var i = 0; i < s.length; i++) {
        var c = s.charAt(i);
        if ((c == ' ') && !seenSpace) {
            seenSpace = true;
        }
    }
    if (seenSpace == true) {
        objId.style.backgroundColor = errorColor;
        return true;
    }

    // All characters are numbers.
    //document.getElementById(spanid).innerHTML = "";
    objId.style.backgroundColor = bgColor;
    return false;
}


function isAlphaChk(objId, spanid) {

    var alphaExp = /^[a-zA-Z]+$/;

    var s = objId.value;

    var c1 = s.charAt(0) + s.charAt(1);


    if (c1.match(alphaExp)) {
        //alert(" Alphanumeric values ");
        // document.getElementById(spanid).innerHTML = "";

        objId.style.backgroundColor = bgColor;
        return false;
    } else {
        //alert("Please enter Alphanumeric values");
        // document.getElementById(spanid).innerHTML = txt;
        objId.style.backgroundColor = errorColor;

        //elem.focus();
        return true;
    }
}
function GSDRule1(objId, spanid, helperMsg, helperMsg1, helperMsg2, helperMsg3)
{
    //var helperMsg= ;
    var txt = "<span class='error_color'>" + helperMsg + " <br/></span>";
    var s = objId.value;
    var elem = objId;
    var alpha_chk = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    var number_chk = "0123456789";
    var SpecialChar = "!@#$%^&*()-_+=[{}}\|;:,?/<,>.'" + '"';

    var alphanumeric_chk = alpha_chk + number_chk;
    var alphanSpecial_chk = alpha_chk + SpecialChar;
    var numericSpecial_chk = number_chk + SpecialChar;

    if (isAlphaChk(objId, spanid)) {
        // var helperMsg1="Initial two character should be alphabet";
        objId.style.backgroundColor = errorColor;
        document.getElementById(spanid).innerHTML = helperMsg1;
        //alert(helperMsg1);
        //validrule=false;

        return false;


    }


    if (passwd_length(objId, spanid)) {

        //var helperMsg2="Password should be between 8 to 12 characters.";
        objId.style.backgroundColor = errorColor;
        document.getElementById(spanid).innerHTML = helperMsg2;
        // alert(helperMsg1);

        return false;
    }


    if (Generic_rule(s, alpha_chk)) {
        //alert("isAlphanumeric_chk");
        objId.style.backgroundColor = errorColor;
        document.getElementById(spanid).innerHTML = helperMsg;
        //alert(helperMsg);

        return false;
    }

    if (Generic_rule(s, number_chk)) {
        //alert("isAlphanumeric_chk");
        objId.style.backgroundColor = errorColor;
        document.getElementById(spanid).innerHTML = helperMsg;
        //alert(helperMsg);
        //validrule=false;

        return false;
    }
    if (Generic_rule(s, SpecialChar)) {
        //alert("isAlphanumeric_chk");
        objId.style.backgroundColor = errorColor;
        document.getElementById(spanid).innerHTML = helperMsg;
        //alert(helperMsg);
        //validrule=false;

        return false;
    }
    if (Generic_rule(s, alphanumeric_chk)) {
        //alert("isAlphanumeric_chk");
        objId.style.backgroundColor = errorColor;
        document.getElementById(spanid).innerHTML = helperMsg;
        //alert(helperMsg);
        //validrule=false;

        return false;
    }

    if (Generic_rule(s, alphanSpecial_chk)) {
        //alert("isAlphanumeric_chk");
        objId.style.backgroundColor = errorColor;
        document.getElementById(spanid).innerHTML = helperMsg;
        //alert(helperMsg);
        //validrule=false;

        return false;
    }

    if (Generic_rule(s, numericSpecial_chk)) {
        //alert("isAlphanumeric_chk");
        objId.style.backgroundColor = errorColor;
        document.getElementById(spanid).innerHTML = helperMsg;
        //alert(helperMsg);
        //validrule=false;

        return false;
    }


    if (isSpace_msg(objId, helperMsg, spanid)) {
        //var helperMsg3="Space is not allowed";
        objId.style.backgroundColor = errorColor;
        document.getElementById(spanid).innerHTML = helperMsg3;
        //alert(helperMsg1);
        //validrule=false;

        return false;
    }

    /* if(isInitChkAlpha(objId,helperMsg, spanid)){
     var helperMsg1="Initial two character should be alphabet";
     objId.style.backgroundColor = errorColor;
     // document.getElementById(spanid).innerHTML = txt;
     alert(helperMsg1);
     //validrule=false;

     return false;

     }*/
    /*if (isAlphaChk(objId,spanid)){
     var helperMsg1="Initial two character should be alphabet";
     objId.style.backgroundColor = errorColor;
     document.getElementById(spanid).innerHTML = helperMsg1;
     //alert(helperMsg1);
     //validrule=false;

     return false;


     }*/

    objId.style.backgroundColor = bgColor;
    document.getElementById(spanid).innerHTML = "";
    return true;


}


// NOTE ::  The function which is define GSD Rule return true for error and after message it convert to false.


/////