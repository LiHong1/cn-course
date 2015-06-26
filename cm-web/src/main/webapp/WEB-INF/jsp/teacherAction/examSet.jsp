<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath }/ueditor1_2_6_1-utf8-jsp/themes/default/css/ueditor.css"/>
    <link rel="stylesheet" type="text/css" href="style/css/switch.css">
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/ueditor1_2_6_1-utf8-jsp/ueditor.config.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/ueditor1_2_6_1-utf8-jsp/ueditor.all.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/ueditor1_2_6_1-utf8-jsp/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript" src="js/switch.js"></script>

    <script type="text/javascript">

        $(document).ready(function () {
            window.UEDITOR_HOME_URL = "${pageContext.request.contextPath }/ueditor1_2_6_1-utf8-jsp/";
            window.UEDITOR_CONFIG.initialFrameHeight = 250;
            window.UEDITOR_CONFIG.initialFrameWidth = 700;
            window.UEDITOR_CONFIG.autoHeightEnabled = false;
            UE.getEditor('examAttention');

            var examAble =${state.examAble};
            var reExamAble =${state.reExamAble};
            init(reExamAble, examAble);
            $("#switch1").click(function () {
                if (examAble == true)
                    examAble = false;
                else examAble = true;
                switchChange($("#switch1_on"), $("#switch1_state1"), $("#switch1_off"), $("#switch1_state2"));
            });
            $("#switch2").click(function () {
                if (reExamAble == true)
                    reExamAble = false;
                else reExamAble = true;
                switchChange($("#switch2_on"), $("#switch2_state1"), $("#switch2_off"), $("#switch2_state2"));
            });


            $("#save").click(function () {
                $("#form").attr("action", "state_examSet.action?reExamAble=" + reExamAble + "&examAble=" + examAble);
                $("#form").submit();
            });
            $("#reExam").click(function () {
                if (confirm("确定全部从新考试？")) {
                    window.location.href = "${pageContext.request.contextPath}/state_reExam.action";
                }
            });
        });

    </script>
</head>
<style>
    #area {
        margin-top: 30px;
        margin-bottom: 40px;
    }

    ul li span {
        margin-top: -5px;
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

    .h {
        margin-left: 0px;
    }

    #download span {
        margin-top: 8px;
    }
</style>
<body>
<div id="top">
    <jsp:include page="top.jsp"></jsp:include>
</div>
<div id="left">
    <jsp:include page="menu.jsp"></jsp:include>
</div>
<div id="right">
    <div id="content">
        <div id="title">考试管理</div>
        <div id="area">
            <s:form id="form" method="post">
                <s:hidden value="%{#state.id}" name="id"></s:hidden>
                <div class="contentin contentfirst">
                    <div class="h">
                        <div style="float: left;margin-right: 45px">1.考试开启</div>
                        <div style="float: left;">
                            <div class="switch" id="switch1">
                                <div id="switch1_on" class="switch_on"><span>ON</span></div>
                                <div id="switch1_state1" class="switch_state1"></div>

                                <div id="switch1_off" class="switch_off"><span>OFF</span></div>
                                <div id="switch1_state2" class="switch_state2"></div>
                            </div>
                        </div>
                    </div>
                    <div class="h">
                        <div style="float: left;margin-right: 10px">2.允许重考　　&nbsp</div>
                        <div style="float: left;">
                            <div id="switch2" class="switch">
                                <div id="switch2_on" class="switch_on"><span>ON</span></div>
                                <div id="switch2_state1" class="switch_state1"></div>

                                <div id="switch2_off" class="switch_off"><span>OFF</span></div>
                                <div id="switch2_state2" class="switch_state2"></div>
                            </div>
                        </div>
                    </div>
                    <div>考试注意事项：<s:textarea value="%{#state.examAttention}" name="examAttention"
                                            id="examAttention"/></div>
                    <div style="float: left;width: 101;height: 26;position: relative;">
                        <!-- button (形状) -->
                        <div class="u" id="save" style="width: 101;height: 26">
                            <img class="u_img" src="style/images/button_u15.png"/>

                            <div class="u1" class="text">
                                <p><span>保存</span></p>
                            </div>
                        </div>
                    </div>
                    <div style="float: left;width: 131px;height: 41px;margin-left: 80px;position: relative;">
                        <div class="u" id="download">
                            <img class="u_img" src="style/images/teacher/button_u93.png"/>

                            <div class="u1" class="text" style="float: left;width: 131px;height: 41px">
                                <p><span>重新导入试题库</span></p>
                            </div>
                        </div>
                    </div>
                    <div style="float: left;width: 131px;height: 41px;margin-left: 80px;position: relative;">
                        <div class="u" id="reExam">
                            <img class="u_img" src="style/images/teacher/button_u93.png"/>

                            <div class="u1" class="text" style="float: left;width: 131px;height: 41px">
                                <p><span>全部重新考试</span></p>
                            </div>
                        </div>
                    </div>
                </div>

            </s:form>
        </div>
    </div>
</div>
<div id="bottom">
    <jsp:include page="../public/bottom.jsp"></jsp:include>
</div>
</body>
</html>
