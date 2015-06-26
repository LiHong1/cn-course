<%@ page language="java" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

    <title></title>
    <script language="JavaScript" type="text/javascript" src="./js/rollups/md5.js"></script>
    <script language="JavaScript" type="text/javascript" src="./js/rollups/aes.js"></script>
    <script language="JavaScript" type="text/javascript" src="./js/jquery.safe.js"></script>
    <script language="JavaScript" type="text/javascript" src="./js/safe/random.js"></script>
    <script language="JavaScript" type="text/javascript" src="./js/safe/cm.security.js"></script>
    <script language="JavaScript" type="text/javascript" src="./js/safe/base64.js"></script>

</head>
<script type="text/javascript">

    $(document).ready(function () {
        $(document).InitRegister({
            usernameId: 'username',
            passwordId: 'password',
            buttonId: 'button',
            formId: 'register',
            url: 'users_prepare.action'
        });
    });
</script>
<body>
<form id="register" action="<s:url action="users_register" />" method="post">
    用户名：<input type="text" name="username" id="username"/>
    　密码：<input type="password" id="password" name="password"/>
    <input id="button" type="button" value="注册"/>
</form>

</body>
</html>

