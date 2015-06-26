<%@ page language="java" import="cn.commons.util.AESCoder" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
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

    <script language="JavaScript" type="text/javascript" src="./js/components/core-min.js"></script>
    <script language="JavaScript" type="text/javascript" src="./js/rollups/aes.js"></script>
    <script language="JavaScript" type="text/javascript" src="./js/safe/jsbn.js"></script>
    <script language="JavaScript" type="text/javascript" src="./js/safe/random.js"></script>
    <script language="JavaScript" type="text/javascript" src="./js/safe/hash.js"></script>
    <script language="JavaScript" type="text/javascript" src="./js/safe/rsa.js"></script>
    <script language="JavaScript" type="text/javascript" src="./js/safe/aes.js"></script>
    <script language="JavaScript" type="text/javascript" src="./js/safe/api.js"></script>


    <script>

        var pwd = "1234567812345678";
        var lihong = '黎红';
        var lihong = lihong.toString(CryptoJS.enc.Utf8);
        var mi = CryptoJS.AES.encrypt("黎红", pwd);
        alert("你好，欢迎来到开源中国在线工具，这是一个AES加密测试----密文:" + mi);

        var result = CryptoJS.AES.decrypt(mi, pwd).toString(CryptoJS.enc.Utf8);

        alert("解密结果：" + result);
        function print(string) {
            document.write(string + "\n\n");
        }


        print("<h1>Unsigned:</h1>");

        var PassPhrase = "The Moon is a Harsh Mistress.";
        var Bits = 512;

        print("Matt's passphrase: " + PassPhrase);
        print("Bit length: " + Bits);

        var MattsRSAkey = cryptico.generateRSAKey(PassPhrase, Bits);
        var MattsPublicKeyString = cryptico.publicKeyString(MattsRSAkey);

        print("Matt's public key string:");
        print(MattsPublicKeyString);

        var PlainText = "Matt, I need you to help me with my Starcraft strategy撒的伤口哦死的.";

        print("Sam's message: " + PlainText);

        var EncryptionResult = cryptico.encrypt(PlainText, MattsPublicKeyString);

        print("The encrypted message:");
        print(EncryptionResult.cipher);

        var DecryptionResult = cryptico.decrypt(EncryptionResult.cipher, MattsRSAkey);

        print("The decrypted message:");
        print(DecryptionResult.plaintext);
        print("DecryptionResult.signature: " + DecryptionResult.signature);

        print("<h1>Signed, good signature:</h1>");

        var PassPhrase = "There Ain't No Such Thing As A Free Lunch.";
        var Bits = 512;
        var SamsRSAkey = cryptico.generateRSAKey(PassPhrase, Bits);
        var PlainText = "Matt, I need you to help me with my Starcraft strategy.";
        var EncryptionResult = cryptico.encrypt(PlainText, MattsPublicKeyString, SamsRSAkey);

        print("Sam's public key ID: " + cryptico.publicKeyID(cryptico.publicKeyString(SamsRSAkey)));

        print("The encrypted message:");
        print(EncryptionResult.cipher);

        var DecryptionResult = cryptico.decrypt(EncryptionResult.cipher, MattsRSAkey);

        print("The decrypted message:");
        print(DecryptionResult.plaintext);

        print("DecryptionResult.signature: " + DecryptionResult.signature);

        print("The signature public key string:");
        print(DecryptionResult.publicKeyString);

        print("The signature public key ID:");
        print(cryptico.publicKeyID(DecryptionResult.publicKeyString));


        print("<h1>Signed, forged signature:</h1>");

        EncryptionResult.cipher = "FrD9P9pbSuCpaMExcHI/6WHbrOgLlIWWegHrWRLN027+DekkaVzumh8QbCS7\n\
6BZJpfQ0H0b/pEvPCnE9RNqFFQ==?h7W8J7KrqDd7TCDlOolSUPwRNxoJYoQ\n\
o7h62SDsfLTfKcdzi6DUTfEq7DgsIKIZd8nYYrDmn3F1utFlgVja2mXSD7FY\n\
RRNvYbmpmu3WBozG77hyFup3IlEQeOkKLBk9G1uEYGcrXiIktJiYBvn8ltVP\n\
Qdo6cViIkwYjEdNoCIanYsSO+YB20EyuKfDj0p62QW9sAVx8jeQmY+f7cvWj\n\
/3evPfZ2D3gaXXT+QY2mu+0ap8P89rPFmrlMgMVFRye4FEWHSkSiKtrddt1y\n\
DZoMxwxFytKA2QciN7MHgZRZ16kcO1KjpPlb9jSXDbzllCWDhigN+kvBog4L\n\
GvhTe0CEn5HKGpWx1+TGbC7pim6/KOFo34DScLOrclUNGl0VY8W+/+EBXhin\n\
dthvRRcjy+0BRn4tDpC4QJjdJoXCqDmT3NRU="

        print("The encrypted message:");
        print(EncryptionResult.cipher);

        var DecryptionResult = cryptico.decrypt(EncryptionResult.cipher, MattsRSAkey);

        print("The decrypted message:");
        print(DecryptionResult.plaintext);

        print("DecryptionResult.signature: " + DecryptionResult.signature);

        print("The signature public key string:");
        print(DecryptionResult.publicKeyString);

        print("The signature public key ID:");
        print(cryptico.publicKeyID(DecryptionResult.publicKeyString));

    </script>

</head>

<body style="font-family: monospace; white-space:pre;">
</body>

</html>


































