<%@ page language="java" pageEncoding="UTF-8" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath }/ueditor1_2_6_1-utf8-jsp/themes/default/css/ueditor.css"/>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/ueditor1_2_6_1-utf8-jsp/ueditor.config.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/ueditor1_2_6_1-utf8-jsp/ueditor.all.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/ueditor1_2_6_1-utf8-jsp/lang/zh-cn/zh-cn.js"></script>
</head>

<script type="text/javascript">
    window.UEDITOR_HOME_URL = "${pageContext.request.contextPath }/ueditor1_2_6_1-utf8-jsp/";
    window.UEDITOR_CONFIG.initialFrameHeight = 250;
    window.UEDITOR_CONFIG.initialFrameWidth = 700;
    window.UEDITOR_CONFIG.autoHeightEnabled = false;
    $(document).ready(function () {
        UE.getEditor('content');
        $("#add").click(function () {
            $("#form").submit();
        });
    });
</script>
<style>

    body {
        width: 700px;
        margin: 100px;
        margin-top: 40px;
    }


</style>
<body onload="setHeight();changeTitle('新增讨论题')">
<div id="title">新增讨论题</div>
<form action="topic_add.action" id="form">
    <div style="height:40px">题目：<input type="text" name="title" style="width:80%;"></div>
    <div style="margin-bottom:20px">内容
        <div style="margin-left:40px;"><textarea name="content" id="content"></textarea></div>
    </div>
</form>
<div style="margin-left:30px;position: relative;">
    <!-- button (形状) -->
    <div class="u" id="add" style="margin-left:40px">
        <img class="u_img" src="style/images/button_u15.png"/>

        <div class="u1" class="text">
            <p><span>新增讨论题目</span></p>
        </div>
    </div>
</div>
</body>
</html>
