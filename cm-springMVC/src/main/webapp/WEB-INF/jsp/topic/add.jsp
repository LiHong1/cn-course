<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>增加讨论主题</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath }/resources/ueditor1_2_6_1-utf8-jsp/themes/default/css/ueditor.css"/>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/resources/ueditor1_2_6_1-utf8-jsp/ueditor.config.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/resources/ueditor1_2_6_1-utf8-jsp/ueditor.all.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/resources/ueditor1_2_6_1-utf8-jsp/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript">
        window.UEDITOR_HOME_URL = "${pageContext.request.contextPath }/resources/ueditor1_2_6_1-utf8-jsp/";
        window.UEDITOR_CONFIG.initialFrameHeight = 250;
        window.UEDITOR_CONFIG.initialFrameWidth = 700;
        window.UEDITOR_CONFIG.autoHeightEnabled = false;
        $(document).ready(function () {
            UE.getEditor('topic_content');
            $("#add").click(function () {
                $("#form").submit();
            });
        });
    </script>
</head>


<body>
        <div id="title">新增讨论题</div>
        <form id="form" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <div style="height:40px">题目：<input type="text" name="title" style="width:80%;"></div>
            <div style="margin-bottom:20px">内容
                <div style="margin-left:40px;"><textarea name="content" id="topic_content"></textarea></div>
            </div>
        </form>
        <div style="margin-left:30px;position: relative;">
            <!-- button (形状) -->
            <div class="u" id="add" style="margin-left:40px">
                <img class="u_img" src="${pageContext.request.contextPath }/resources/style/images/button_u15.png"/>

                <div class="u1" class="text">
                    <p><span>新增讨论题目</span></p>
                </div>
            </div>
        </div>
</body>
</html>
