<%@ page language="java" pageEncoding="utf-8" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>正在答题</title>
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
    $(function () {
        $("#h2").click(function () {
            $("#h3").show();
            $(this).hide();
        });
        $("#submit").click(function () {
            if (confirm("一旦交卷就不能重新考试，你确定交卷！")) {
                $("#form").submit();
            }
            $("#form").submit();
        });
        window.UEDITOR_HOME_URL = "${pageContext.request.contextPath }/ueditor1_2_6_1-utf8-jsp/";
        window.UEDITOR_CONFIG.initialFrameHeight = 250;
        window.UEDITOR_CONFIG.initialFrameWidth = 700;
        window.UEDITOR_CONFIG.autoHeightEnabled = false;
        window.UEDITOR_CONFIG.toolbars = [
            ['fullscreen', 'source', '|', 'undo', 'redo', '|',
                'bold', 'italic', 'underline',]
        ];
        UE.getEditor('answer');
        $("#h3").hide();
    });

</script>
<style>
    p {
        font-size: 20px;
        font-weight: bold;
    }
</style>

<body>
<div id="top">
    <jsp:include page="../studentAction/top.jsp"></jsp:include>
</div>
<div id="left">
    <jsp:include page="../studentAction/menu.jsp"></jsp:include>
</div>
<div id="right">
    <div id="content">
        <div id="h1" style="margin-top:50px">
            <div style="font-size: 20px">考试试题：
                <div>${problem}</div>
            </div>
        </div>

        <div id="h2" style="width: 101px;height: 76px;margin:auto;position: relative;">
            <div class="u">
                <img class="u_img" src="style/images/student/button_u49.png"/>
                <!-- Unnamed () -->
                <div class="u1" style="margin-top:20px">
                    <p><span>我要答卷</span></p>
                </div>
            </div>
        </div>
        <s:form action="student_submitPaper.action" method="post" id="form">
            <s:hidden name="id" value="%{#id}"></s:hidden>
            <div id="h3">我的答卷：
                <textarea rows="18" cols="100%" name="answer" id="answer"></textarea>

                <div style="margin-top:5px;margin-bottom:20px">交卷后不得修改答案</div>
                <div>
                    <div style="float: left;width: 101px;height: 76px;margin:auto;position: relative;">
                        <div class="u" id="submit">
                            <img class="u_img" src="style/images/student/button_u49.png"/>
                            <!-- Unnamed () -->
                            <div class="u1" style="margin-top:20px">
                                <p><span>交卷</span></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </s:form>
    </div>
</div>
<div id="bottom">
    <jsp:include page="../public/bottom.jsp"></jsp:include>
</div>
</body>
</html>
