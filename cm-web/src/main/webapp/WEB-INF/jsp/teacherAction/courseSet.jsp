<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath }/ueditor1_2_6_1-utf8-jsp/themes/default/css/ueditor.css"/>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/ueditor1_2_6_1-utf8-jsp/ueditor.config.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/ueditor1_2_6_1-utf8-jsp/ueditor.all.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/ueditor1_2_6_1-utf8-jsp/lang/zh-cn/zh-cn.js"></script>
    <link rel="stylesheet" type="text/css" href="style/css/tab.css">
    <script type="text/javascript" src="js/tab.js"></script>
    <script type="text/javascript">

        $(document).ready(function () {
            window.UEDITOR_HOME_URL = "${pageContext.request.contextPath }/ueditor1_2_6_1-utf8-jsp/";
            window.UEDITOR_CONFIG.initialFrameHeight = 250;
            window.UEDITOR_CONFIG.initialFrameWidth = 700;
            window.UEDITOR_CONFIG.autoHeightEnabled = false;
            UE.getEditor('information');
            UE.getEditor('schedule');
            UE.getEditor('material');
            $("#save").click(function () {
                $("#form").submit();
            });

        });
    </script>
</head>
<style>
    #area {
        margin-top: 30px;
        margin-bottom: 40px;
    }

    table {
        text-align: center;
        border-spacing: 1px;
        border: 0px;
        background-color: black;
    }

    tr {
        background-color: white;
        height: 30px;
    }
</style>
<body>
<div id="top">
    <jsp:include page="../teacherAction/top.jsp"></jsp:include>
</div>
<div id="left">
    <jsp:include page="../teacherAction/menu.jsp"></jsp:include>
</div>
<div id="right">
    <div id="content">
        <div id="title">课程设置</div>
        <ul id="tabfirst">
            <li class="tabclick"><span>课程介绍</span></li>
            <li><span>进度安排</span></li>
            <li><span>学习材料</span></li>
        </ul>
        <div style="float:right"><s:a action="material_addUI">上传材料</s:a></div>
        <form action="course_save.action" id="form" method="post">
            <div id="area">
                <s:hidden value="%{#teacher.cuState.course.id}" name="id"></s:hidden>
                <div class="contentin contentfirst"><s:textarea name="information" id="information"
                                                                value="%{#teacher.cuState.course.information}"></s:textarea></div>
                <div class="contentfirst"><s:textarea name="schedule" id="schedule"
                                                      value="%{#teacher.cuState.course.schedule}"></s:textarea></div>
                <div class="contentfirst"><s:textarea name="material" id="material"></s:textarea></div>
            </div>
        </form>
        <div style="position: relative;">
            <!-- button (形状) -->
            <div class="u" id="save">
                <img class="u_img" src="style/images/button_u15.png"/>

                <div class="u1" class="text">
                    <p><span>保存</span></p>
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
