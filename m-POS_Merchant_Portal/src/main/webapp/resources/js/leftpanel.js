var place = 160;     // change the # 80 to adjust the placement of the menu from the top of the page. The greater

                    // number the lower will the menu be placed.
var leftSide = 16;   // change the # 0 to adjust the placement of the menu from the left-margin of the page
(document.getElementById) ? dom = true : dom = false;
function typeStart() {
 if (dom) { document.write('<div id="menuBox" style="position:absolute; left:' + leftSide + 'px; width:130px; visibility:visible">') } }

function typeEnd() { if (dom) { document.write('</div>') } }

function placeIt() {
  if (dom && !document.all) {document.getElementById("menuBox").style.top = window.pageYOffset + place + "px";}
  if (document.all) {document.all["menuBox"].style.top = document.documentElement.scrollTop + place + "px"}
  //window.setTimeout("placeIt()", 12000);
}
if (dom) {
 document.writeln('<style type="text/css">')
 document.writeln('.main {text-decoration:none; color:blue; cursor:hand; cursor:pointer}')
 document.writeln('span:hover.mainLink {text-decoration:underline; color:red}')
 document.writeln('.sublinks1 {display:none; padding-left:9px}')
 document.writeln('.link2 {text-decoration:none; color:blue}')
 document.writeln('a:hover.link2 {text-decoration:underline; color:red}')
 document.writeln('</style>') }

var mainNum = new Array("a1", "a2","a3", "a4","a5");  // at the left you should add a1, a2 etc. for each main link you wish to include
// so if you want 3 main links you should add a1, a2, a3 in the format shown
// enclosed in double quotes
var subNum1 = new Array("b1","b2");  // at the left you should add b1, b2 etc. for each sub link you wish to include
// under one main link, here the first main link. so if you want 4 sub links you
// should add b1, b2, b3, b4 in the format shown enclosed in double quotes

/*function openClose(menuArray,theID) {
 for(var i=0; i < menuArray.length; i++) {
  if (menuArray[i] == theID) {

   if (document.getElementById(theID).style.display == "block") {
          document.getElementById(theID).style.display = "none";
          document.getElementById("tick_"+menuArray[i]).innerHTML = " ";
   }else{
       document.getElementById(theID).style.display = "block";
       document.getElementById("tick_"+menuArray[i]).innerHTML = " ";
   }
  }else{
      document.getElementById(menuArray[i]).style.display = "none";
      document.getElementById("tick_"+menuArray[i]).innerHTML = " ";
    }
 }
}*/
function openClose(menuArray,theID,task) {
for(var i=0; i < menuArray.length; i++) {
 if (menuArray[i] == theID) {
        if (document.getElementById(theID).style.display == "block") {
          if(task=='undefined' || task !='open') {
         document.getElementById(theID).style.display = "none";
          }
         document.getElementById("tick_"+menuArray[i]).innerHTML = " "; }
  else { document.getElementById(theID).style.display = "block";
         document.getElementById("tick_"+menuArray[i]).innerHTML = " ";
  }
 }
 else { document.getElementById(menuArray[i]).style.display = "none";
        document.getElementById("tick_"+menuArray[i]).innerHTML = " "; }
}
}