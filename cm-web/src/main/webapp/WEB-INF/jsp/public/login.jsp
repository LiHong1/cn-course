<%@ page language="java" pageEncoding="UTF-8" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
    <title>登录</title>
</head>
<script type="text/javascript">
    var xmlHttp;
    $(document).ready(function () {

        /*   $(this).InitLogin({usernameId:'number',
         passwordId:'password',
         buttonId:'login',
         formId:'loginForm',
         url:'user_prepare.action'
         }); */

        $("#reset").click(function () {

            document.getElementById("loginForm").reset();
        });
    });

    function loadXMLDoc() {

        //创建异步请求对象
        if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
            xmlHttp = new XMLHttpRequest();

        }
        else {// code for IE6, IE5
            xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
    }
    function login1() {
        loadXMLDoc();
        xmlHttp.onreadystatechange = result1;
        xmlHttp.open("GET", "${pageContext.request.contextPath}/user_loginGetCourse.action?number=" + document.getElementById("number").value, true);
        xmlHttp.send();

    }
    function login2() {

        loadXMLDoc();
        xmlHttp.onreadystatechange = result2;

        xmlHttp.open("GET", "${pageContext.request.contextPath}/user_loginGetClass.action?number=" + document.getElementById("number").value + "&&courseId=" + document.getElementById("u1_input").value, true);

        xmlHttp.send();

    }
    function result1() {

        if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {

            var text = xmlHttp.responseText;
            var u1_input = document.getElementById("u1_input");
            var u2_input = document.getElementById("u2_input");
            u1_input.options.length = 1;
            u2_input.options.length = 1;
            var textObj = JSON.parse(text);
            for (var temp in textObj) {
                if (textObj[temp].className != null && textObj[temp].className != undefined)
                    u2_input.options.add(new Option(textObj[temp].className, textObj[temp].id));
                else  u1_input.options.add(new Option(textObj[temp].courseName, textObj[temp].id));
            }

        }

    }
    function result2() {
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {

            var text = xmlHttp.responseText;
            var u2_input = document.getElementById("u2_input");
            u2_input.options.length = 1;
            var textObj = JSON.parse(text);
            for (var temp in textObj) {
                if (textObj[temp].className != null)
                    u2_input.options.add(new Option(textObj[temp].className, textObj[temp].id));
            }

        }

    }
    window.onload = function () {
        var success = '${success}';
        if (success != "") {
            window.open("${pageContext.request.contextPath}/other.jsp", 'newwindow', 'height=350,width=300,top=300,left=500,toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no');
        }
    }

</script>
<style>

    #loginForm {
        border: 1px solid #CCC;
        margin: auto;
        margin-top: 100px;
        position: relative;
        height: 450px;
    }

    #u17 {
        position: absolute;
        font-family: 'Helvetica Regular', 'Helvetica';
        font-weight: 400;
        font-style: normal;
        font-size: 14px;
        color: #AE5300;
        cursor: pointer;

    }

</style>
<body>
<div id="container">

    <s:form action="user_login" id="loginForm">
        <table style="margin:auto;color:black;border-spacing: 15px;text-align: center;width:80%;height:100%">
            <tr>
                <td colspan="2" height="50px">
                    <div style="font-size: 24px;font-family:'Arial Normal', 'Arial';">登录</div>
                </td>
            </tr>
            <tr>
                <td colspan="2" height="20px"><font color="red"><s:fielderror
                        cssStyle="list-sytle:none"><s:param>login</s:param></s:fielderror></font></td>

            </tr>
            <tr>
                <td height="30px">
                    <div style="float:right">学号(教号)</div>
                </td>
                <td><s:textfield type="text" name="number" id="number" onblur="login1()" cssStyle="width:153px"/>
                </td>
            </tr>
            <tr>
                <td height="30px">
                    <div style="float:right"> &nbsp密　　码</div>
                    　
                </td>
                <td>
                    <s:password type="password" id="password" name="password" cssStyle="width:153px"/>
                </td>

            </tr>

            <tr>
                <td height="30px">
                    <div style="float:right"> &nbsp课　　程</div>
                </td>
                <td>
                    <select id="u1_input" style="width:153px" name="courseId" onchange="login2()">
                        <option selected value="0">请选择课程</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td height="30px">
                    <div style="float:right"> &nbsp班　　级</div>
                </td>
                <td>
                    <select id="u2_input" style="width:153px" name="classId">
                        <option selected value="0">请选择班级</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td height="30px" colspan="2">
                    <div style="float:left;width: 140px;height: 30px;position: relative;">
                        <div style="margin-left:25px;position: relative;">
                            <!-- button (形状) -->
                            <div class="u" id="login" style="margin-left:25px;">
                                <img class="u_img" src="style/images/button_u15.png"/>
                                <!-- Unnamed () -->
                                <div class="u1">
                                    <p><span>登录</span></p>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div style="float:left;width: 140px;height: 30px;position: relative;">
                        <div style="margin-left:30px;position: relative;">
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
                <td colspan="2" height="30px">
                    <s:a action="student_addUI.action">注册</s:a>
                </td>
            </tr>
            <tr>
                <td colspan="2" height="30px">
                    <s:a action="application_applyUI.action">申请加入班级</s:a>
                </td>

            </tr>
        </table>
    </s:form>
</div>

</body>
</html>
