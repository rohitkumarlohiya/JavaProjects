function doCodeSearch()
{
    var valid = validateSearch();
    if (valid) {
        document.forms[0].action = "transList.do";
        document.forms[0].method = "post";
        document.forms[0].submit();
    }
}

 function doBulkRefund() {

    var selObj = document.getElementById("transType");
    var selIndex = selObj.selectedIndex;
    var transTypeId = selObj.options[selIndex].value;
    var transTypeCode = selObj.options[selIndex].text;

    if (transTypeCode == "PURCHASE") {
        document.getElementById("purchaseSearchId").style.display = "";
        document.getElementById("pinTrfSearchId").style.display = "none";
        return;
    } else {
        document.getElementById("purchaseSearchId").style.display = "none";
    }

    if (transTypeCode == "PINTRF") {
        document.getElementById("pinTrfSearchId").style.display = "";
       // document.getElementById("agentId").disabled = true;
       // document.getElementById("vendorId").disabled = true;
       // document.getElementById("srcMob").disabled = true;
        document.getElementById("desMob").disabled = true;
       // document.getElementById("pinNo").disabled = true;
        document.getElementById("pcode").disabled = true;

        document.getElementById("purchaseSearchId").style.display = "none";
        return;
    } else {
        //document.getElementById("agentId").disabled = false;
       // document.getElementById("vendorId").disabled = false;
       // document.getElementById("srcMob").disabled = false;
        document.getElementById("desMob").disabled = false;
       // document.getElementById("pinNo").disabled = false;
        document.getElementById("pcode").disabled = false;
        document.getElementById("pinTrfSearchId").style.display = "none";
        return;
    }
}

function trapSearch(evt) {
    var keycode;
    //  var charCode = (evt.which) ? evt.which : event.keyCode
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
        doCodeSearch();
    }
    else
        return true;
}
function doView(id)
{
    document.forms[0].id.value = id;
    document.forms[0].action = "transView.do";
    document.forms[0].method = "post";
    document.forms[0].submit();
}


function doRefund(id)
{
    var valid = validateRefund();
    if (valid)
    {
        document.forms[0].isSetCheck.value = document.getElementById("issettled").checked;
        document.forms[0].id.value = id;
        document.forms[0].action = "transRefund.do";
        document.forms[0].method = "post";
        document.forms[0].submit();
    }
}

function doPurchaseSearch() {
    /* var valid = validateSearch();
     if (valid) {
     transaction.action = "transaction_search.jsp?&&pageAction=search" + "&hide=y";
     transaction.codepurchase.value="purchase";
     transaction.method = "POST";
     transaction.submit();
     */
    var valid = validateSearch();
    if (valid) {
        document.forms[0].codepurchase.value = "purchase";
        document.forms[0].action = "transList.do";
        document.forms[0].method = "post";
        document.forms[0].submit();

    }
}
function doPinTrfSearch() {
    var valid = validateSearch();
  if (valid)
    {       document.forms[0].pintrf.value = "pintrf";
            document.forms[0].action = "transListPinTrf.do";
            document.forms[0].method = "post";
            document.forms[0].submit();
     }
}


function doDisplay(id)
{

    document.forms[0].pageAction.value = "Display";
    doView(id);

}

function SelectAll() {
    var n = document.getElementById("sub_List_size").value;
    for (var i = 0; i < n; i++) {
        if (document.getElementById("all").checked == true) {
            document.getElementById(i).checked = true;
        } else {
            document.getElementById(i).checked = false;
        }
    }
}

function SelectManual() {
    var n = document.getElementById("sub_List_size").value;
    var val = "";
    for (var i = 0; i < n; i++) {
        if (document.getElementById(i).checked == true) {
            document.getElementById(i).checked = true;
        } else {
            document.getElementById(i).checked = false;
        }
    }
}

function hideshow() {
    document.getElementById("refund_tab").style.display = '';
    document.getElementById("issettled").checked = true;
}
function hide() {
    document.getElementById("refund_tab").style.display = 'none';
}

function doRefundMultiple()
{
    var valid = validateRefundPurchase();
    if (valid)
    {
        document.forms[0].isSetCheck.value = document.getElementById("issettled").checked;
        document.forms[0].pageAction.value = "Refund";
        document.forms[0].action = "transRefundPurchase.do";
        document.forms[0].method = "post";
        document.forms[0].submit();
    }
}
function doPinTrfRefundMultiple()
{
    var valid = validateRefundPurchase();
    if (valid)
    {
        document.forms[0].isSetCheck.value = document.getElementById("issettled").checked;
        document.forms[0].pageAction.value = "Refund";
        document.forms[0].action = "transRefundPinTrf.do";
        document.forms[0].method = "post";
        document.forms[0].submit();
    }
}

function ismaxlength(obj) {
    var mlength = obj.getAttribute ? parseInt(obj.getAttribute("maxlength")) : ""
    if (obj.getAttribute && obj.value.length > mlength)

        obj.value = obj.value.substring(0, mlength)
}
function isAlphanum3(objId, helperMsg, spanid)
{
    var elem = objId;
    var alphaExp = /^[a-z A-Z 0-9.,]+$/;
    var txt = "<span class='error_txt'>" + helperMsg + " <br/></span>";
    if ((elem.value.match(alphaExp)) || elem.value == "")
    {

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