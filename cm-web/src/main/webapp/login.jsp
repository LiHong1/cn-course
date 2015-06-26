<%@ page language="java" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

    <title></title>
    <script language="javascript" src="${pageContext.request.contextPath}/script/jquery.validate.js"></script>
</head>
<script type="text/javascript">
    $(document).ready(function () {

        /**$(document).InitLogin({usernameId:'username',
           passwordId:'password',
           buttonId:'button',
           formId:'login',
           url:'users_prepare.action'
	   });**/
        $("#login").validate({
            rules: {
                username: "required",
                password: "required"
            },
            messages: {
                name: "请输入教师姓名",
                number: "请输入教师工号"
            },
        });
    });
</script>

<body>

<form id="login" name="login" action="users_login.action" method="post">
    <table>
        <tr>
            <td width="100"> 用户名：</td>
            <td width="200"><input type="text" name="username" id="username"/></td>
        </tr>
        <tr>
            <td>　密码：</td>
            <td><input type="password" id="password" name="password"/></td>
        </tr>
        <tr>
            <td>　邮箱：</td>
            <td><input type="text" name="email"/></td>
        </tr>
        <tr>
            <td>　图片验证：</td>
            <td><input type="text" id="checkCode" name="checkCode"/></td>
        </tr>
        <tr>
            <td></td>
            <td><img src="${pageContext.request.contextPath }/users_drawCheckCode.action"></td>
        </tr>
        <tr>
            <td colspan="2"><input id="button" type="button" value="提 交"/></td>

        </tr>

    </table>

</form>

</body>
</html>

