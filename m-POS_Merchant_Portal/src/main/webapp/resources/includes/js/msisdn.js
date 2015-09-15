



function doEditview(id)
       {

           document.forms[0].action = "prefixEdit.do";
           document.forms[0].method = "post";
            document.forms[0].submit();
       }
function doBackview()
       {
            document.forms[0].action = "prefixList.do";
            document.forms[0].method = "post";
            document.forms[0].submit();
       }

   function doEditon(id)
      {

            document.forms[0].prefixId.value = id;
            document.forms[0].action = "prefixView.do";
            document.forms[0].method = "post";
            document.forms[0].submit();

       }

  function doEditPrefix(id)
      {

            document.forms[0].id.value = id;
          
            document.forms[0].action = "prefixEdit.do";
            document.forms[0].method = "post";
            document.forms[0].submit();

       }
   function doBacknew()
         {
              document.forms[0].action = "prefixList.do";
              document.forms[0].method = "post";
              document.forms[0].submit();
         }
   function doApprove(id)
   {

    document.forms[0].id.value = id;
    document.forms[0].action = "prefixApproval.do";
    document.forms[0].method = "post";
    document.forms[0].submit();

   }
   function doReject(id)
   {
   document.forms[0].id.value = id;
   document.forms[0].action = "prefixReject.do";
   document.forms[0].method = "post";
   document.forms[0].submit();

   }
