function doEdit()
{
document.forms[0].action = "voucherTemplateEdit.do";
document.forms[0].method = "post";
document.forms[0].submit();
}

function doAddVoucherDesign()
{
document.forms[0].action = "voucherDesignAdd.do";
document.forms[0].method = "post";
document.forms[0].submit();
}

function doAddAgentPromo()
{
    document.forms[0].action = "agentPromoList.do";
    document.forms[0].method = "post";
    document.forms[0].submit();
}
/*
 this method is used for opening vvoucherTemplateList jsp
 */
function doBack()
{
    window.location.href = "voucherTemplateList.do";
}

function getProduct(id){
    document.forms[0].vendorTemp.value = id;
    document.forms[0].action = "voucherTemplateAdd.do";
    document.forms[0].method = "post";
    document.forms[0].submit();
}
/*
 Thius function is called when a new vendor is created
 */
function doAdd()
{
    var valid = voucherTemplate_Add();
    if (valid) {

        document.forms[0].action = "voucherTemplateAdd.do";
        document.forms[0].method = "post";
        document.forms[0].submit();
        document.getElementById("addVoucherDiv2").style.display = "";
        document.getElementById("addVoucherDiv1").style.display = "none";
    }
}

function enableDisable() {
try {
document.getElementById("addVoucherDiv2").style.display = "none";
document.getElementById("addVoucherDiv1").style.display = "";
} catch(err) {
}
}

function doView(id)
{

    document.forms[0].voucherTemplateId.value = id;
   // document.forms[0].voucherDesignId.value = vdid;
    document.forms[0].action = "voucherTemplateView.do";
    document.forms[0].method = "post";
    document.forms[0].submit();

}

function doViewDesign(id)     {

   // alert("=== call doViewVoucherDesign ===="+id);
    document.forms[0].voucherDesignId.value = id;
    document.forms[0].action = "voucherDesignView.do";
    document.forms[0].method = "post";
    document.forms[0].submit();

}

function doViewDesignAll(id)
{
    document.forms[0].voucherTemplateId.value = id;
    document.forms[0].action = "voucherDesignAllView.do";
    document.forms[0].target = "_blank";
    document.forms[0].method = "post";
    document.forms[0].submit();
    document.forms[0].target = "";

}

function doSyncAgentClient(id)
{
    var status = document.getElementById("syncVTCheck").checked;
    if(status){
        document.forms[0].voucherDesignId.value = id;
        document.forms[0].action = "voucherTemplateSyncUpdate.do";
        document.forms[0].method = "post";
        document.forms[0].submit();
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

    var valid = validateVoucher_Edit();
    if (valid) {
        document.forms[0].pageAction.value = "Edit";
        document.forms[0].action = "voucherTemplateEdit.do";
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
