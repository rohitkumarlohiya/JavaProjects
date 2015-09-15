function doView(id)
{
    document.forms[0].action = "transLimitView.do";
    document.forms[0].id.value = id;
    document.forms[0].method = "POST";
   document.forms[0].submit();
}


function doAdd()
{
    servicePlan.action = "transLimitAdd.do";
    servicePlan.method = "POST";
    servicePlan.submit();
}

function submitFormservicePlan_Add()
{
    var valid = validateserviceplan_Add();
    if (valid)
    {
        transLimit.action = "transLimitAdd.do";
        transLimit.method = "POST";
        transLimit.submit();
    }
}

function addEnter(evt)
{
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
    if (keycode == 13)
    {
        var validSubmit = submitFormservicePlan_Add();
        if (validSubmit)
            return true;
        else
            return false;
    }
    else
        return true;
}


function createRequestObject()
{
    var tmpXmlHttpObject;
    if (window.XMLHttpRequest)
    {
        tmpXmlHttpObject = new XMLHttpRequest();
    } else if (window.ActiveXObject)
    {
        tmpXmlHttpObject = new ActiveXObject("Microsoft.XMLHTTP");
    }
    return tmpXmlHttpObject;
}
var http = createRequestObject();
function getPackValue()
{
    var x = document.getElementById("servicePlanName");
    for (i = x.options.length - 1; i > 0; i--)
    {
        x.remove(i);
    }
    var packId = document.getElementById("servicePackName").value;

    if (packId > 0)
    {
        http.open('get', 'transLimitAjax.jsp?id=' + packId);
        http.onreadystatechange = processResponse;
        http.send(null);
    }
}
function processResponse()
{
    if (http.readyState == 4)
    {
        if (http.status == 200)
        {
            var response1 = http.responseXML.getElementsByTagName("result")[0];
            var result = response1.childNodes[0].nodeValue.split("###");
            var x = document.getElementById("servicePlanName");
            for (var i = 0; i < result.length - 1; i++) {
                var idvalue = result[i].split("|");
                var id = idvalue[0];
                var value = idvalue[1];
                addOption(x, value, id);
            }
        }
    }
}
function addOption(selectbox, text, value)
{
    var optn = document.createElement("OPTION");
    optn.text = text;
    optn.value = value;
    selectbox.options.add(optn);
}

function doEdit()
{
    transLimit.action = "transLimitEdit.do";

    transLimit.method = "POST";
    transLimit.submit();

}


function doSave()
{
     var valid = validateTransLimit_Add();
     if (valid)
    {
    transLimit.action = "transLimitEdit.do";

    transLimit.method = "POST";
    transLimit.submit();
      }
}

function trapEdit(evt)
        {
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
            if (keycode == 13)
            {
                var validSubmit =validateTransLimit_Add();
                if (validSubmit)
                    return true;
                else
                    return false;
            }
            else
                return true;
        }

