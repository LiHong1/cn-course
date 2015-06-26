<%@ page language="java" import="java.util.*,cn.commons.util.AESCoder" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <meta name="content-type" content="text/html; charset=UTF-8">
 <script language="JavaScript" type="text/javascript" src="./js/safe/jsbn.js"></script>
      
         <script language="JavaScript" type="text/javascript" src="./js/rollups/aes.js"></script>
         <script language="JavaScript" type="text/javascript" src="./js/rollups/rabbit.js"></script>
    
   
        <script>
        var salt = CryptoJS.lib.WordArray.random(16);
        var salt_hex = CryptoJS.enc.Hex.stringify(salt);

        var iv = CryptoJS.lib.WordArray.random(256/32);
        var iv_hex = CryptoJS.enc.Hex.stringify(iv);
        iv='AAAAAAAAAAAAAAAA';
       
        
        var plaintext="test text 123";
        var key="0123456789abcdef";
        var encrypted = CryptoJS.AES.encrypt(plaintext, key, { iv: iv });    

       
        alert(encrypted);
        alert(decrypted.toString(CryptoJS.enc.Utf8));
        </script>

    </head>
    
    <body style="font-family: monospace; white-space:pre;">
    </body>
    
</html>


































