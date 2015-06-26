<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
</head>
<script type="text/javascript">
    $(document).ready(function () {
        window.UEDITOR_HOME_URL = "${pageContext.request.contextPath }/ueditor1_2_6_1-utf8-jsp/";
        window.UEDITOR_CONFIG.initialFrameHeight = 200;
        window.UEDITOR_CONFIG.initialFrameWidth = 700;
        window.UEDITOR_CONFIG.autoHeightEnabled = false;
        UE.getEditor('problem');
        UE.getEditor('answer');
        $("#save").click(function () {
            $("#form").submit();
        });
    });
</script>
<body>
<div id="top">
    <jsp:include page="../teacherAction/top.jsp"></jsp:include>
</div>
<div style="height:900px">
    <div id="left">
        <jsp:include page="../teacherAction/menu.jsp"></jsp:include>
    </div>
    <div id="right">
        <div id="content">
            <div id="title">新增试题</div>
            <form action="paper_add.action" id="form" method="post">
                <div id="area">
                    <div>试题名称<input type="text" name="name"/></div>
                    <div><textarea name="problem" id="problem"></textarea></div>
                    <div style="margin:15px">参考答案</div>
                    <div><textarea name="answer" id="answer"></textarea></div>
                </div>
            </form>
            <div style="position:relative">
                <div class="u" id="save">
                    <img class="u_img" src="style/images/button_u15.png"/>

                    <div class="u1" class="text">
                        <p><span>新增</span></p>
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
