<%@ page language="java" pageEncoding="UTF-8" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

    <title>登录</title>

    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
    <script language="javascript" src="${pageContext.request.contextPath}/script/jquery.validate.js"></script>
    <link type="text/css" rel="stylesheet" href="style/css/login.css">
</head>
<script type="text/javascript">


    $(function () {

        /*   $(this).InitLogin({usernameId:'number',
         passwordId:'password',
         buttonId:'login',
         formId:'loginForm',
         url:'user_prepare.action'
         }); */
        var u1_input = $("#u1_input");
        var u2_input = $("#u2_input");
        $("#reset").click(function () {

            $("#loginForm")[0].reset();
        });
        $("#login").click(function () {
            $("#loginForm").submit();
        });
        $("#number").blur(function () {
            u1_input[0].options.length = 1;
            u2_input[0].options.length = 1;
            $.getJSON("user_loginGetCourse.action", {number: $("#number").val()}, function (data) {
                for (var temp in data) {
                    alert(data[temp].id + "");
                    u1_input[0].options.add(new Option(data[temp].courseName, data[temp].id));
                }

            });
        });
        u1_input.change(function () {
            u2_input[0].options.length = 1;
            $.getJSON("user_loginGetClass.action", {
                number: $("#number").val(),
                courseId: u1_input.val()
            }, function (data) {
                for (var temp in data) {
                    u2_input[0].options.add(new Option(data[temp].className, data[temp].id));
                }

            });
        });
        window.onload = function () {
            var success = '${success}';
            if (success != "") {
                window.open("${pageContext.request.contextPath}/other.jsp", 'newwindow', 'height=350,width=300,top=300,left=500,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
            }
        };
        $("#loginForm").validate({
            rules: {
                number: "required",
                password: "required"
            },
            messages: {
                number: "请输入学号或教工号",
                password: "请输入密码"
            }
        });
    });


</script>
<body>
<div id="container">

    <s:form action="user_login" id="loginForm">
        <table>
            <tr>
                <td colspan="2" height="50px"><span id="title">上机系统登录</span></td>
            </tr>
            <tr>
                <td colspan="2" height="20px"><font color="red"><s:fielderror
                        cssStyle="list-sytle:none"><s:param>login</s:param></s:fielderror></font></td>

            </tr>
            <tr>
                <td style="width:240px">
                    <div class="p1"><span>学号(教号)</span></div>
                </td>
                <td style="width:310px">
                    <div class="p2"><s:textfield type="text" name="number" id="number" cssStyle="width:153px"/>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="p1"><span> &nbsp密　　码  </span></div>
                    　
                </td>
                <td>
                    <div class="p2"><s:password type="password" id="password" name="password" cssStyle="width:153px"/>
                    </div>
                </td>

            </tr>

            <tr>
                <td>
                    <div class="p1"><span> &nbsp课　　程 </span></div>
                </td>
                <td>
                    <div class="p2">
                        <select id="u1_input" style="width:153px" name="courseId"">
                        <option selected value="0">请选择课程</option>
                        </select>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <div class="p1"><span> &nbsp班　　级  </span></div>
                </td>
                <td>
                    <div class="p2">
                        <select id="u2_input" style="width:153px" name="classId">
                            <option selected value="0">请选择班级</option>
                        </select>
                    </div>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <div style="float:left;margin-left:140px;">
                        <div class="button">
                            <!-- button (形状) -->
                            <div class="u" id="login">
                                <img class="u_img" src="style/images/button_u15.png"/>
                                <!-- Unnamed () -->
                                <div class="u1">
                                    <p><span>登录</span></p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div style="float:left;margin-left:50px;">
                        <div class="button">
                            <!-- button (形状) -->
                            <div class="u" id="reset">
                                <img class="u_img" src="style/images/button_u15.png"/>
                                <!-- Unnamed () -->
                                <div class="u1">
                                    <p><span>重置</span></p>
                                </div>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <s:a action="student_addUI.action">注册</s:a>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <s:a action="application_applyUI.action">申请加入班级</s:a>
                </td>

            </tr>
        </table>
    </s:form>
</div>

</body>
</html>
