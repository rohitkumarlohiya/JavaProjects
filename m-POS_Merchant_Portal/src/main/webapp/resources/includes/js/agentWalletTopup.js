function doView(id)
        {
           
            agentApprove.action = "walletTopupView.do";
            agentApprove.id.value = id;
            agentApprove.method = "POST";
            agentApprove.submit();
        }
        
        function doSearch()
        {

            document.forms[0].action = "walletTopupAppList.do";
            document.forms[0].method = "POST";
            document.forms[0].submit();
        }

        function doApprove()
        {
            document.getElementById("approveButtonDiv").style.display = "none";
            document.getElementById("rejectButtonDiv").style.display = "none";
            walletAdjView.action = "walletTopupApprove.do";
            walletAdjView.method = "POST";
            walletAdjView.submit();

        }

        function doReject()
        {
            document.getElementById("approveButtonDiv").style.display = "none";
            document.getElementById("rejectButtonDiv").style.display = "none";
            walletAdjView.action = "walletTopupReject.do";
            walletAdjView.method = "POST";
            walletAdjView.submit();

        }