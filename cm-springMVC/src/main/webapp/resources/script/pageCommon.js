/*
//判断IE7\8 兼容性检测
var isIE=!!window.ActiveXObject;
var isIE6=isIE&&!window.XMLHttpRequest;
var isIE8=isIE&&!!document.documentMode;
var isIE7=isIE&&!isIE6&&!isIE8;
//获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
var curWwwPath=window.document.location.href;
//获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
var pathName=window.document.location.pathname;
var pos=curWwwPath.indexOf(pathName);
//获取主机地址，如： http://localhost:8083
var localhostPaht=curWwwPath.substring(0,pos);
//获取带"/"的项目名，如：/uimcardprj
var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
var projectRoot=localhostPaht+projectName;
if(isIE8 || isIE7||isIE6){
	document.write("<script type='text/javascript' src='"+projectRoot+"/resources/script/jquery-1.9.1.min.js'></script>");
}else{
	document.write("<script type='text/javascript' src='"+projectRoot+"/resources/script/jquery-2.1.1.min.js'></script>");
}
*/


