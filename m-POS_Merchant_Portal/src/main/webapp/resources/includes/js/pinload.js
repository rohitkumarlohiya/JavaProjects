function doView()
{

    document.forms[0].strstartIndex.value =0;
    document.forms[0].strPagesize.value = document.forms[0].maxid.value;
     document.forms[0].strPageNum.value = 1;
    document.forms[0].action = "pinUploadList.do";
    document.forms[0].method = "POST";
    document.forms[0].submit();
}

function doEdit(id)
{

    document.forms[0].action = "pinFailList.do";
    document.forms[0].failureid.value = id;
    document.forms[0].strstartIndex.value =0;
    document.forms[0].strPagesize.value =document.forms[0].maxid.value;
     document.forms[0].strPageNum.value = 1;
    document.forms[0].method = "POST";
     document.forms[0].submit();

}

