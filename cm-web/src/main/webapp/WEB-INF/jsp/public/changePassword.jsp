<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
    <script type="text/javascript" src="${pageContext.request.contextPath}/script/jquery.validate.js"></script>
</head>
<script type="text/javascript">
    $(document).ready(function () {
        $("#change").click(function () {
            $("#form").submit();
        });
        $("#reset").click(function () {

            $("#form")[0].reset();
        });

        $("#form").validate({
            rules: {
                password: "required",
                newPassword1: "required",
                newPassword2: {
                    required: true,
                    equalTo: "#newPassword1"
                }
            },
            messages: {
                password: "请输入原始密码",
                newPassword1: "请输入新密码",
                newPassword2: {
                    required: "请再输入新密码",
                    equalTo: "两次输入密码不一致"
                }
            }
        });

    });
</script>
<style>
    #form label.error {
        color: red;
        font-size: 15px;
        margin-left: 5px;
        padding-left: 16px;
    }
</style>
<body>
<div id="top">
    <jsp:include page="../${role}Action/top.jsp"></jsp:include>
</div>
<div id="left">
    <jsp:include page="../${role}Action/menu.jsp"></jsp:include>
</div>
<div id="right">
    <div id="content">
        <div id="title">更改口令</div>
        <form action="user_changePassword.action" method="post" id="form">
            <div class="input">旧口令　　<input type="password" name="password" style="width: 250px"></div>
            <div class="input">新口令　　<input type="password" name="newPassword1" style="width:250px">
            </div>
            <div class="input">再输一次　<input type="password" name="newPassword2" style="width: 250px">
            </div>
        </form>
        <div>
            <div style="position:relative;float:left">
                <!-- button (形状) -->
                <div class="u" id="change">
                    <img class="u_img" src="style/images/button_u15.png"/>
                    <!-- Unnamed () -->
                    <div class="u1">
                        <p><span>更改</span></p>
                    </div>
                </div>
            </div>
            <div style="position:relative;float:left;margin-left:60px">
                <div class="u" id="reset">
                    <img class="u_img" src="style/images/button_u15.png"/>
                    <!-- Unnamed () -->
                    <div class="u1">
                        <p><span>重置</span></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="bottom">
    <jsp:include page="../public/bottom.jsp"></jsp:include>
</div>
</body>
</html>
