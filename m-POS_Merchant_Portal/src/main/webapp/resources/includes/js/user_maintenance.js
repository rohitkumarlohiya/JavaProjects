	function doEdit(id)
		{

             document.forms[0].id.value = id;
             document.forms[0].pageAction.value = "view";
             document.forms[0].action = "userEdit.do";        /// user_view.jsp
             document.forms[0].method = "post";
             document.forms[0].submit();

		}
