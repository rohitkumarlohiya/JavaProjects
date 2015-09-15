function doEdit()
{
    document.forms[0].action = "servicePlanEdit.do";
    document.forms[0].method = "post";
    document.forms[0].submit();

}
function doBack()
{
    document.forms[0].action = "servicePlanList.do";
    document.forms[0].method = "post";
    document.forms[0].submit();

}
function doBackdisp()
{
    document.forms[0].action = "servicePlanList.do";
    document.forms[0].method = "post";
    document.forms[0].submit();

}

function doBackadd()
{
    document.forms[0].action = "servicePlanList.do";
    document.forms[0].method = "post";
    document.forms[0].submit();

}


function doBackedit(id) {

    document.forms[0].id.value = id;
    document.forms[0].action = "servicePlanView.do";
    document.forms[0].method = "post";
    document.forms[0].submit();
}

function doEditmain(id)
{


    document.forms[0].id.value = id;
    document.forms[0].action = "servicePlanView.do";
    document.forms[0].method = "post";
    document.forms[0].submit();

}