function doAdd()
        {       
                document.forms[0].action= "serBankAdd.do";
                 document.forms[0].method = "post";
                 document.forms[0].submit();

        }


function submitFormserviceBank_Add()
         {
            if(validateservicebank_Add())
            {
            serviceBank.action = "serBankAdd.do";
            serviceBank.method = "POST";
			serviceBank.submit();
            }
         }
          function doBack()
         {
            serviceBank.action = "agentBankList.do";
            serviceBank.method = "POST";
			serviceBank.submit();
         }