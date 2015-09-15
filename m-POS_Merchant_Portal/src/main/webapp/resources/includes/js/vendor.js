/*
 this method is used for opening vendor view
 */

function doView(id)
{

    document.forms[0].vendorId.value = id;
    document.forms[0].action = "vendorView.do";
    document.forms[0].method = "post";
    document.forms[0].submit();

}
/*
 this methid is used for opening vendor edit jsp
 */
function doEditnew(id)
{
    document.forms[0].vendorId.value = id;
    document.forms[0].action = "vendorEdit.do";
    document.forms[0].method = "post";
    document.forms[0].submit();

}



function doEdit(id)
{

//    document.forms[0].vendorId.value = id;
    document.forms[0].action = "vendorEdit.do";
    document.forms[0].method = "post";
    document.forms[0].submit();
}

/*
 this method is used for opening vendor list jsp
 */
function doBack()
{
    window.location.href = "vendorList.do";
}

/*
 Thius function is called when a new vendor is created
 */
function doAdd()
{
    var valid = validateVendor_Add();
    if (valid) {
        document.forms[0].action = "vendorAdd.do";
        document.forms[0].method = "post";
        document.forms[0].submit();

        if(document.getElementById("addVendorDiv2")!=null && document.getElementById("addVendorDiv2")!=undefined){
        document.getElementById("addVendorDiv2").style.display = "";
        }
        if(document.getElementById("addVendorDiv1")!=null && document.getElementById("addVendorDiv1")!=undefined){
        document.getElementById("addVendorDiv1").style.display = "none";
        }
    }

}

function enableDisable() {
    try {
        if(document.getElementById("addVendorDiv2")!=null && document.getElementById("addVendorDiv2")!=undefined){
        document.getElementById("addVendorDiv2").style.display = "none";
        }
        if(document.getElementById("addVendorDiv1")!=null && document.getElementById("addVendorDiv1")!=undefined){
        document.getElementById("addVendorDiv1").style.display = "";
        }
     } catch(err) {
    }
}
/*
 This method os used for trapping add vendor key event
 */
function trapAdd(evt) {

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
        var validSubmit = doAdd();
        if (validSubmit)
            return true;
        else
            return false;
    } else
        return true;

}

/*
 This method is used for updating vendor
 */

function doSave()
{

    var valid = validateVendor_Edit();
    if (valid) {
        document.forms[0].pageAction.value = "Edit";
        document.forms[0].action = "vendorEdit.do";
        document.forms[0].method = "post";
        document.forms[0].submit();

    }

    return valid;
}

/*
 This method os used for trapping edit vendor key event
 */
function trapEdit(evt)
{
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
        var validSubmit = doSave();
        if (validSubmit)
            return true;
        else
            return false;
    } else
        return true;
}

/*
 This method is used for approving vendor
 */

function doApprove(id)
{
    document.forms[0].vendorId.value = id;
    document.forms[0].action = "vendorApprove.do";
    document.forms[0].method = "post";
    document.forms[0].submit();
}

/*
 This method is used for rejecting vendor
 */

function doReject(id)
{
    document.forms[0].vendorId.value = id;
    document.forms[0].action = "vendorReject.do";
    document.forms[0].method = "post";
    document.forms[0].submit();
}