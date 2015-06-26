<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath }/ueditor1_2_6_1-utf8-jsp/themes/default/css/ueditor.css"/>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/ueditor1_2_6_1-utf8-jsp/ueditor.config.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/ueditor1_2_6_1-utf8-jsp/ueditor.all.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/ueditor1_2_6_1-utf8-jsp/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript">

        $(document).ready(function () {
            window.UEDITOR_HOME_URL = "${pageContext.request.contextPath }/ueditor1_2_6_1-utf8-jsp/";
            window.UEDITOR_CONFIG.autoHeightEnabled = false;
            window.UEDITOR_CONFIG.initialFrameHeight = 250;
            window.UEDITOR_CONFIG.initialFrameWidth = 700;
            UE.getEditor('problem');
            UE.getEditor('answer');
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

</style>
<body onload="setHeight();changTitle('编辑试题')">
<div id="content">
    <div id="title">编辑试题</div>
    <div>最后更新时间：<fmt:formatDate value="${paper.lastUpdateTime}" type="both"/></div>
    <form action="paper_edit.action" id="form" method="post">
        <div id="area">
            <div>试题名称<s:textfield name="name" value="%{#paper.name}"/></div>
            <s:hidden value="%{#paper.id}" name="id"></s:hidden>
            <div><s:textarea name="problem" id="problem" value="%{#paper.problem}"></s:textarea></div>
            <div style="height: 40px">参考答案</div>
            <div><s:textarea name="answer" id="answer" value="%{#paper.answer}"></s:textarea></div>

        </div>
    </form>
    <div style="position:relative">
        <!-- button (形状) -->
        <div class="u" id="save">
            <img class="u_img" src="style/images/button_u15.png"/>

            <div class="u1" class="text">
                <p><span>保存</span></p>
            </div>
        </div>
    </div>
</div>
</body>
</html>
