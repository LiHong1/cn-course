var js=document.scripts;
var jsPath;
for(var i=js.length;i>0;i--){
 if(js[i-1].src.indexOf("webAuth.js")>-1){
   jsPath=js[i-1].src.substring(0,js[i-1].src.lastIndexOf("/")+1);
 }
}     
document.write("<script type='text/javascript' src='"+jsPath+"aes.js'></script>");
document.write("<script type='text/javascript' src='"+jsPath+"base64.js'></script>");
document.write("<script type='text/javascript' src='"+jsPath+"jquery.safe.js'></script>");
document.write("<script type='text/javascript' src='"+jsPath+"md5.js'></script>");
document.write("<script type='text/javascript' src='"+jsPath+"random.js'></script>");
document.write("<script type='text/javascript' src='"+jsPath+"cm.security.js'></script>");
  