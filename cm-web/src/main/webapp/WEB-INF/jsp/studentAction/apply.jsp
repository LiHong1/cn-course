<%@ page language="java" pageEncoding="utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>申请加入班级</title>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
    <script language="javascript" type="text/javascript"
            src="${pageContext.request.contextPath}/script/jquery.validate.js"></script>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/register.css"></link>
</head>
<script type="text/javascript">


    $(document).ready(function () {
        $("#register").click(function () {
            $("#form").submit();
        });
        $("#reset").click(function () {
            document.getElementById("form").reset();
        });

        $("#course").change(function () {
            var class_input = $("#class");
            class_input[0].options.length = 1;
            $.getJSON("application_applicationGetClass.action", {
                number: $("#number").val(),
                courseId: $("#course").val()
            }, function (data) {
                for (var temp in data) {
                    class_input[0].options.add(new Option(data[temp].className, data[temp].id));
                }
            });
        });
        $("#form").validate({
            rules: {
                password: "required",
                number: "required"
            },
            messages: {
                password: "请输入口令",
                number: "请输入学号"
            }
        });
    });
</script>
<style>

    #message {
        width: 80%;
        height: 30px;
        margin: auto;
        margin-top: 20px;
        margin-bottom: 5px;
        color: red;
    }

</style>

<body onload="setHeight();changeTitle('申请加入班级')">
<div id="container">
    <div id="area">
        <form action="application_apply.action" id="form" method="post">

            <div id="title" style="margin-bottom: 5px">申请加入班级</div>
            <div id="message">${message}</div>
            <div class="input">学 &nbsp号：<input type="text" name="number" id="number" style="width:153px"/></div>
            <div class="input">口 &nbsp令：<input type="password" name="password" style="width:153px"/></div>
            <div class="input">课 &nbsp程：<s:select list="courseList" cssStyle="width:153px" name="courseId" id="course"
                                                  listKey="id" listValue="name" headerKey="0"
                                                  headerValue="请选择课程"></s:select>
            </div>
            <div class="input">班 &nbsp级：<select id="class" style="width:153px" name="classId">
                <option selected value="0">请选择班级</option>
            </select>
            </div>
            <div style="position: relative;">
                <div style="float: left;">
                    <!-- button (形状) -->
                    <div class="u" id="register">
                        <img class="u_img" src="style/images/button_u15.png"/>
                        <!-- Unnamed () -->
                        <div class="u1">
                            <p><span>申请</span></p>
                        </div>
                    </div>
                </div>

                <div style="float: left;margin-left:15px;position: relative;">
                    <div class="u" id="reset">
                        <img class="u_img" src="style/images/button_u15.png"/>
                        <!-- Unnamed () -->
                        <div class="u1">
                            <p><span>重置</span></p>
                        </div>
                    </div>
                </div>

                <div style="margin-left:93px;clear: left"><a href="index.jsp">登录</a></div>
            </div>
        </form>
    </div>
</div>


</body>
</html>
