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
    <script type="text/javascript">
        $(document).ready(function () {
            window.UEDITOR_HOME_URL = "${pageContext.request.contextPath }/ueditor1_2_6_1-utf8-jsp/"
            window.UEDITOR_CONFIG.autoHeightEnabled = false;
            window.UEDITOR_CONFIG.initialFrameHeight = 250;
            window.UEDITOR_CONFIG.initialFrameWidth = 700;
            //window.UEDITOR_CONFIG.imageUrl="servlet/myservlet"
            // ,imagePath:URL + "jsp/"
            UE.getEditor('case_content');

            $("#add").click(function () {
                $("#form").submit();
            });

        });

    </script>
</head>
<style>
    table {
        text-align: center;
        margin: auto;
        border-spacing: 1px;
        border-spadding: 1px;
        border: 0px;
        background-color: black;
        clear: right;
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
        <div id="title">新增案例</div>
        <s:form action="case_%{id == -1 ? 'add' : 'update'}" id="form" method="post">
            <s:hidden name="id"></s:hidden>
            <div>标题
                <div><s:textfield name="title" value="%{#Case.title}"></s:textfield></div>
            </div>
            <div>内容
                <div><s:textarea name="content" id="case_content" value="%{#Case.content}"></s:textarea></div>
            </div>
        </s:form>
        <div style="position:relative">
            <div class="u" id="add">
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
