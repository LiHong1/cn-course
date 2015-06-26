<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>RSA</title>
    <script type="text/javascript" src="<s:url value="script/jquery-2.1.1.min.js" />"></script>
    <script type="text/javascript" src="<s:url value="js/safe/cm.cm.cm.security.js" />"></script>
    <script type="text/javascript">
        $(function () {
            $("#btn").click(function () {
                $.getJSON('<s:url  action="users_keyPair" />', function (data) {
                    var modulus = data[0].json.modulus, exponent = data[0].json.exponent;
                    //alert(modulus);
                    //alert(exponent);
                    var epwd = $('#password').val();
                    if (epwd.length != 256) {
                        var publicKey = RSAUtils.getKeyPair(exponent, '', modulus);
                        $('#password').val(RSAUtils.encryptedString(publicKey, epwd));
                        alert($('#password').val());
                    }
                    alert($('#password').val());
                    $("#login").submit();
                });
            });
        });
    </script>
</head>

<body>
<form id="login" name="login" action="<s:url action="users_login" />" method="post">
    　密码：<input type="password" id="password" name="password"/>
    <input id="btn" type="button" value="提 交"/>xcv
</form>
</body>
</html>
