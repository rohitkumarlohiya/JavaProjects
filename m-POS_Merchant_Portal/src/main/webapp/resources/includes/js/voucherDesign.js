
function doEdit()
{

document.forms[0].action = "voucherTemplateEdit.do";
document.forms[0].method = "post";
document.forms[0].submit();
}
// for show edit page
function doEditVoucherDesign()
{

document.forms[0].action = "voucherDesignEdit.do";
document.forms[0].method = "post";
document.forms[0].submit();

}

function doDeleteVoucherDesign()
{

document.forms[0].action = "voucherDesignDelete.do";
document.forms[0].method = "post";
document.forms[0].submit();

}
 // for  edit value
function doEditVoucherDesignProc()
           {

           var valid = validateVoucher_Edit();

           if (valid) {
           document.forms[0].action = "voucherDesignEdit.do";
           document.forms[0].method = "post";
           document.forms[0].submit();
           }
           }



function doBack()
{
    window.location.href = "voucherTemplateList.do";
}
/*function doBackFromAddDesign()
{
    window.location.href = "voucherTemplateList.do";
}*/



/*function doBackViavoucherDesignView()
{
    //window.location.href = "voucherTemplateList.do";
            document.forms[0].voucherTemplateId.value = id;
            document.forms[0].action = "voucherTemplateView.do";
            document.forms[0].method = "post";
            document.forms[0].submit();

}*/


function doAdd()
{
//var valid = voucherDesign_Add();
   var valid = true; // no validation required
if (valid) {


document.forms[0].pageAction.value = "add";
document.forms[0].action = "voucherDesignAdd.do";
document.forms[0].method = "post";
document.forms[0].submit();

}
}

function enableDisable() {
try {
document.getElementById("addVoucherDiv2").style.display = "none";
document.getElementById("addVoucherDiv1").style.display = "";
document.getElementById("dataTable").style.display = "none";
document.getElementById("create").style.display = "none";
} catch(err) {
}
}


function doView(id)
{

    document.forms[0].voucherTemplateId.value = id;
    document.forms[0].action = "voucherTemplateView.do";
    document.forms[0].method = "post";
    document.forms[0].submit();

}

/*
 This method os used for trapping add
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



function doAllRowView()
{
document.forms[0].action = "voucherDesignAllViewRow.do";
document.forms[0].method = "post";
document.forms[0].target = "_blank";
document.forms[0].submit();
document.forms[0].target="";
}


function doAddRow(tableID, tableID1,maxLine)
{


     var valid = voucherDesign_Add();

    if (valid) {

        addRow(tableID, tableID1,maxLine);
    }
}


function addRow(tableID, tableID1,maxLine) {
    /// show row
    document.getElementById(tableID).style.display = "";
    document.getElementById(tableID1).style.display = "";

    ///////
    var heading = document.getElementById("heading").value;
    var picText = document.getElementById("picText").value;
    var tagVal = document.getElementById("tagVal").value;
    var align = document.getElementById("align").value;
    var format = document.getElementById("format").value;
    var newline = document.getElementById("newline").value;
    document.getElementById("maxLine").value=maxLine;

    if(heading==-1){
       heading="";
    }

    if(align==-1){
       align="";
    }

     if(format==-1){
       format="";
    }

    if(newline==-1){
       newline="";
    }

    var table = document.getElementById(tableID);

     var rowCount = table.rows.length;
    // rowCount = Number(rowCount)+Number(maxLine);
     //alert("== rowCount =="+rowCount);
     var row = table.insertRow(rowCount);

    var cell1 = row.insertCell(0);
    //cell1.innerHTML =  '<p class="bodytext">'+rowCount;
    var element1 = document.createElement("input");
    element1.type = "checkbox";
    cell1.appendChild(element1);

    var cell2 = row.insertCell(1);
    cell2.innerHTML = '<p class="bodytext">' + heading;


    var cell3 = row.insertCell(2);
    cell3.innerHTML = '<p class="bodytext">' + picText;


    var cell4 = row.insertCell(3);
    cell4.innerHTML = '<p class="bodytext">' + tagVal;


    var cell5 = row.insertCell(4);
    cell5.innerHTML = '<p class="bodytext">' + align;

    var cell6 = row.insertCell(5);
    cell6.innerHTML = '<p class="bodytext">' + format;

    var cell7 = row.insertCell(6);
    cell7.innerHTML = '<p class="bodytext">' + newline;

    ////// Hidden   ///////
    //############## picText  ##############
    var cell8 = row.insertCell(7);
    var element8 = document.createElement("input");
    element8.type = "hidden";
    element8.id = "picTextHide";
    element8.name = "picTextHide";
    element8.value = picText;
    cell8.appendChild(element8);

    //############## tagVal  ##############
    var cell9 = row.insertCell(8);
    var element9 = document.createElement("input");
    element9.type = "hidden";
    element9.id = "tagValHide";
    element9.name = "tagValHide";
    element9.value = tagVal;
    cell9.appendChild(element9);

    //############## align  ##############
    var cell10 = row.insertCell(9);
    var element10 = document.createElement("input");
    element10.type = "hidden";
    element10.id = "alignHide";
    element10.name = "alignHide";
    element10.value = align;
    cell10.appendChild(element10);

    //############## format  ##############
    var cell11 = row.insertCell(10);
    var element11 = document.createElement("input");
    element11.type = "hidden";
    element11.id = "formatHide";
    element11.name = "formatHide";
    element11.value = format;
    cell11.appendChild(element11);

    //############## newline  ##############
    var cell12 = row.insertCell(11);
    var element12 = document.createElement("input");
    element12.type = "hidden";
    element12.id = "newlineHide";
    element12.name = "newlineHide";
    element12.value = newline;
    cell12.appendChild(element12);


    //############## heading  ##############
    var cell13 = row.insertCell(12);
    var element13 = document.createElement("input");
    element13.type = "hidden";
    element13.id = "headingHide";
    element13.name = "headingHide";
    element13.value = heading;
    cell13.appendChild(element13);

}

function deleteRow(tableID) {
    try {
        var table = document.getElementById(tableID);
        var rowCount = table.rows.length;

        for (var i = 0; i < rowCount; i++) {
            var row = table.rows[i];
            var chkbox = row.cells[0].childNodes[0];
            if (null != chkbox && true == chkbox.checked) {
                table.deleteRow(i);
                rowCount--;
                i--;
            }

        }
    } catch(e) {
        alert(e);
    }
}
/*
 This method os used for trapping edit
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
//-----------------------------------------------
var NumericIncrement = {
	register : function(name, minValue, maxValue, stepSize){
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.stepSize = stepSize;
		var elements = getElementsByClassName(document, "*", name);
		for (var i=0; i<elements.length; i++){
			var textbox = elements[i].getElementsByTagName('input')[0];
			if (textbox){
				if (textbox.value == undefined || textbox.value == '' || isNaN(textbox.value))
					textbox.value = 1;
				textbox.onkeypress = function(e){
					if(window.event){
						keynum = e.keyCode; // IE
					} else if(e.which){
						keynum = e.which; // Netscape/Firefox/Opera
					}
					keychar = String.fromCharCode(keynum);
					numcheck = /[1-9\-]/;
					if (keynum==8)
						return true;
					else
						return numcheck.test(keychar);
				};
				textbox.onblur = function(){
					if (parseInt(this.value) < NumericIncrement.minValue)
						this.value = NumericIncrement.minValue;
					if (parseInt(this.value) >NumericIncrement. maxValue)
						this.value = NumericIncrement.maxValue;
				};
				var buttons = elements[i].getElementsByTagName('button');
				if (buttons[0]){
					this.addButtonEvent(buttons[0], textbox, this.stepUp);
				}
				if (buttons[1])
					this.addButtonEvent(buttons[1], textbox, this.stepDown);
			}
		}
	}
  ,addButtonEvent:function(o,textbox, func){
    o.textbox = textbox;
		// convert button type to button to prevent form submission onclick
		if (o.getAttribute("type")=="submit"){
			o.removeAttribute("type"); // IE fix
			o.setAttribute("type","button");
		}
    o.onclick = func;
	}
  ,stepUp:function(){
    NumericIncrement.incrementer(this.textbox, NumericIncrement.stepSize);
  }
  ,stepDown:function(){
    NumericIncrement.incrementer(this.textbox, -NumericIncrement.stepSize);
  }
	,incrementer:function(textbox, val){
    if (textbox == undefined)
      return false;
    if (val == undefined || isNaN(val))
      val = 1;
    if (textbox.value == undefined || textbox.value == '' || isNaN(textbox.value))
      textbox.value = 1;
    textbox.value = parseInt(textbox.value) + parseInt(val);
    if (parseInt(textbox.value) < NumericIncrement.minValue)
      textbox.value = NumericIncrement.minValue;
    if (parseInt(textbox.value) >NumericIncrement. maxValue)
      textbox.value = NumericIncrement.maxValue;
  }
}


function getElementsByClassName(oElm, strTagName, strClassName){
  var arrElements = (strTagName == "*" && oElm.all)? oElm.all : oElm.getElementsByTagName(strTagName);
  var arrReturnElements = new Array();
  strClassName = strClassName.replace(/-/g, "\-");
  var oRegExp = new RegExp("(^|\s)" + strClassName + "(\s|$)");
  var oElement;
  for(var i=1; i<arrElements.length; i++){
    oElement = arrElements[i];
    if(oRegExp.test(oElement.className)){
      arrReturnElements.push(oElement);
    }
  }
  return (arrReturnElements)
}

function initNumericIncrement(){
	var myNumericIncrement = NumericIncrement.register("numeric-increment", 1, 100, 1);
}
function addEvent(o, evt, f){
	var r = false;
  if (o.addEventListener){
    o.addEventListener(evt, f, false);
		r = true;
  }
  else if (o.attachEvent)
  	r = o.attachEvent("on"+evt, f);
  return r;
}
addEvent(window, "load", initNumericIncrement);