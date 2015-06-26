<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
    <link rel="stylesheet" type="text/css" href="style/css/tab.css">
    <link type="text/css" rel="stylesheet" href="style/css/forum.css"/>
    <link rel="stylesheet" type="text/css" href="style/css/switch.css">
    <script type="text/javascript" src="js/tab.js"></script>
    <script type="text/javascript" src="js/switch.js"></script>
    <script language="javascript" type="text/javascript"
            src="${pageContext.request.contextPath}/script/jquery.changeColor.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            var joinAble =${teacher.cuState.joinAble};
            var loginAble =${teacher.cuState.loginAble};
            init(joinAble, loginAble);
            $("#switch1").click(function () {
                if (loginAble == true)
                    loginAble = false;
                else loginAble = true;
                switchChange($("#switch1_on"), $("#switch1_state1"), $("#switch1_off"), $("#switch1_state2"));
            });
            $("#switch2").click(function () {

                if (joinAble == true)
                    joinAble = false;
                else joinAble = true;
                switchChange($("#switch2_on"), $("#switch2_state1"), $("#switch2_off"), $("#switch2_state2"));
            });


            $("#save").click(function () {

                window.location.href = "teacher_classSet.action?&loginAble=" + loginAble + "&joinAble=" + joinAble;
            });

            $("#download").click(function () {
                window.location.href = "fileDownload_jobsFile.action";
            });

            $("#type").change(function () {
                window.location.href = "teacher_classManageUI.action?type=" + document.getElementById("type").value + "&option=1";
            });
            if (${option!=null}) {
                if (${type==null}) {
                    $("#type option").eq(0).attr("selected", true);
                } else {
                    $("#type option").eq(${type+1}).attr("selected", true);
                }
                $("#tabfirst li").eq(0).removeClass("tabclick");
                $("#tabfirst li").eq(1).addClass("tabclick");
                $("#area > div").eq(0).removeClass("contentin");
                $("#area > div").eq(1).addClass("contentin");
            }
            $("tbody tr:odd").changeColor({oddColor: "#ccc"});
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

    thead tr {
        background-color: #711;
        color: white;
    }

    #download span {
        margin-top: 8px;
    }

    .h1 {
        height: 60px;
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
        <div id="title">班级管理</div>

        <ul id="tabfirst">
            <li class="tabclick"><span>班级设置 </span></li>
            <li><span>申请加入</span></li>
        </ul>

        <div id="area">

            <s:hidden value="%{#teacher.cuState.id}" name="id"></s:hidden>
            <div class="contentin contentfirst">
                <div class="h1">
                    <div style="float: left;margin-right: 45px">1.允许学生登录</div>
                    <div style="float: left;">
                        <div class="switch" id="switch1">
                            <div id="switch1_on" class="switch_on"><span>ON</span></div>
                            <div id="switch1_state1" class="switch_state1"></div>

                            <div id="switch1_off" class="switch_off"><span>OFF</span></div>
                            <div id="switch1_state2" class="switch_state2"></div>
                        </div>
                    </div>
                </div>
                <div class="h1">
                    <div style="float: left;margin-right: 10px">2.允许申请加入班级&nbsp</div>
                    <div style="float: left;">
                        <div id="switch2" class="switch">
                            <div id="switch2_on" class="switch_on"><span>ON</span></div>
                            <div id="switch2_state1" class="switch_state1"></div>

                            <div id="switch2_off" class="switch_off"><span>OFF</span></div>
                            <div id="switch2_state2" class="switch_state2"></div>
                        </div>
                    </div>
                </div>


                <div style="float: left;width: 101px;height: 26px;position:relative">
                    <!-- button (形状) -->
                    <div class="u" id="save" style="width: 101px;height: 26px">
                        <img class="u_img" src="style/images/button_u15.png"/>

                        <div class="u1" class="text">
                            <p><span>保存</span></p>
                        </div>
                    </div>
                </div>
                <div style="float: left;width: 131px;height: 41px;position:relative;margin-left:30px">
                    <div class="u" id="download">
                        <img class="u_img" src="style/images/teacher/button_u93.png"/>

                        <div class="u1" class="text" style="float: left;width: 131px;height: 41px">
                            <p><span>下载所以学生作业</span></p>
                        </div>
                    </div>
                </div>


            </div>
            <div class="contentfirst">

                <select id="type" style="width:200px" name="type">
                    <option selected value="-1">所有</option>
                    <option value="0">等待审核</option>
                    <option value="1">已通过</option>
                    <option value="2">已拒绝</option>
                </select>
                <table id="table">
                    <thead>
                    <tr>
                        <td width="40px">序号</td>
                        <td width="150px">学号</td>
                        <td width="150">姓名</td>
                        <td width="140px">申请时间</td>
                        <td width="110px">状态</td>
                        <td width="80px"></td>
                    </tr>
                    </thead>
                    <tbody>
                    <s:iterator value="recordList" status="status">
                        <tr>
                            <td>${status.count}</td>
                            <td>${student.number}</td>
                            <td>${student.name}</td>
                            <td><fmt:formatDate value="${applicationDate}" type="both"/></td>
                            <td><c:if test="${type==0}">等待审核</c:if><c:if test="${type==1}"> 已通过</c:if> <c:if
                                    test="${type==2}">已拒绝</c:if></td>
                            <td><c:if test="${type==0}"><s:a action="application_getThrough?id=%{id}">通过</s:a><s:a
                                    action="application_refuse?id=%{id}">拒绝</s:a></c:if></td>

                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
                <!--分页信息-->
                <%@ include file="/WEB-INF/jsp/public/pageView.jspf" %>
                <form action="teacher_classManageUI.action" id="form">
                    <s:hidden value="%{#teacher.cuState.id}" name="id"></s:hidden>
                    <s:hidden value="-1" name="type"></s:hidden>
                    <s:hidden value="1" name="option"></s:hidden>
                </form>
            </div>
        </div>
    </div>
</div>
<div id="bottom">
    <jsp:include page="../public/bottom.jsp"></jsp:include>
</div>
</body>
</html>
