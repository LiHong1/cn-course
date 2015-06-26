<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
    <link rel="stylesheet" type="text/css" href="style/css/switch.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/switch.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/Calendar5.js"></script>
    <script type="text/javascript">
        $(function () {
            var loginAble =${loginAble};
            var replaceMachineAble =${replaceMachineAble};
            init(replaceMachineAble, loginAble);
            init(replaceMachineAble, loginAble);
            $("#switch1").click(function () {
                if (loginAble == true)
                    loginAble = false;
                else loginAble = true;
                switchChange($("#switch1_on"), $("#switch1_state1"), $("#switch1_off"), $("#switch1_state2"));
            });
            $("#switch2").click(function () {
                if (replaceMachineAble == true)
                    replaceMachineAble = false;
                else replaceMachineAble = true;
                switchChange($("#switch2_on"), $("#switch2_state1"), $("#switch2_off"), $("#switch2_state2"));
            });

            $("#save").click(function () {
                $("#form").attr("action", "system_save.action?loginAble=" + loginAble + "&replaceMachineAble=" + replaceMachineAble);
                $("#form").submit();
            });
        });
        var c = new Calendar("c");
        document.write(c);
    </script>
</head>
<style>
    span {
        margin-top: 5px;
        display: block;
    }

    #u2 {
        position: absolute;
        font-family: 'Helvetica Regular', 'Helvetica';
        font-weight: 400;
        font-style: normal;
        font-size: 14px;
        color: #AE5300;
        cursor: pointer;
    }

    #u2_img {
        position: absolute;
        left: 0px;
        top: 0px;
        width: 101px;
        height: 63px;
    }

    #u3 {
        position: absolute;
        left: 2px;
        top: 4px;
        width: 101px;
        height: 63px;
        word-wrap: break-word;
    }

    #u3 p {
        margin-top: 10px;
        padding: 0px;
    }

    #u3 span {
        text-align: center;
        display: block;
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
        <div id="title">系统设置</div>
        <div class="h">
            <div style="float: left;margin-right: 60px">1.系统登录</div>
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
            <div style="float: left;margin-right: 10px">2.允许学生换机器&nbsp</div>
            <div style="float: left;">
                <div id="switch2" class="switch">
                    <div id="switch2_on" class="switch_on"><span>ON</span></div>
                    <div id="switch2_state1" class="switch_state1"></div>

                    <div id="switch2_off" class="switch_off"><span>OFF</span></div>
                    <div id="switch2_state2" class="switch_state2"></div>
                </div>
            </div>
        </div>
        <s:form id="form">
            <div class="h">
                <div>3.学生作业区大小 &nbsp<s:textfield value="%{size}" name="size" id="jobArea_size"
                                                 cssStyle="width:80px"></s:textfield>MB
                </div>
            </div>
            <div style="margin-left: 50px;margin-bottom:20px; height: 30px">
                <div style="float: left;margin-right:30px ">4.学期开始日期</div>
                <div style="float: left;">
                    Date:<br>
                    <s:textfield id="startTermTime" name="startTermTime" cssStyle="width: 100px"
                                 onclick="c.showMoreDay = false;c.show(this);" value="%{startTermTime}"></s:textfield>
                </div>
            </div>
        </s:form>
        <div class="h" id="save">
            <div id="u2">
                <img id="u2_img" src="style/images/manage/button_u41.png"/>

                <div id="u3" class="text">
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