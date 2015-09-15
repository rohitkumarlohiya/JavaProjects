
function doBackAdd()
   {

    document.forms[0].action = "billerList.do";
    document.forms[0].method = "post";
    document.forms[0].submit();

   }



 
   function trapEnterEdit(evt, id)
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
          var validSubmit = doSave(id);
          if (validSubmit)
              return true;
          else
              return false;
      }
      else
          return true;
  }
  function doBackedit()
   {

    document.forms[0].action = "billerList.do";
    document.forms[0].method = "post";
    document.forms[0].submit();

   }
 function doEdit(id)
  {
      // alert( "ruchi------"+document.forms[0].id.value);
        document.forms[0].id.value = id;
        document.forms[0].action = "billerEdit.do";
        document.forms[0].method = "post";
        document.forms[0].submit();

   }
