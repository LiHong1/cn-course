<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>学生信息</title>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
    <link rel="stylesheet" type="text/css" href="style/css/tab.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/forum.css"/>
    <script type="text/javascript" src="js/tab.js"></script>

    <script type="text/javascript">

        $(document).ready(function () {
            var i = 0;
            $("#save").click(function () {
                $("#form").submit();
            });
            $("#h1").change(function () {

                for (i = 0; i < $("li").size(); i++) {
                    if ($("li").eq(i).hasClass("tabclick"))
                        break;
                }
                window.location.href = "${pageContext.request.contextPath}/student_show.action?id=" + document.getElementById("h1").value + "&option=" + i;
            });
            $("li").each(function (index) {

                $("li").eq(index).removeClass("tabclick");
                $("#area > div").eq(index).removeClass("contentin");
                if (${option}==index
                )
                {

                    $("ul li").eq(index).addClass("tabclick");
                    $("#area > div").eq(index).addClass("contentin");
                }
            });
            $("#nextStudent").click(function () {
                for (i = 0; i < $("li").size(); i++) {
                    if ($("li").eq(i).hasClass("tabclick"))
                        break;
                }
                var id = document.getElementById("h1").value;
                var size = $("#h1 > option").length;
                window.location.href = "${pageContext.request.contextPath}/student_show.action?nextStudent=true&id=" + id + "&option=" + i;

            });
            $("#delete").click(function () {
                message = "你确定删除？";
                return confirm(message);
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

    .b1 {
        margin-top: 10px;
        margin-bottom: 20px;
    }

    a {
        text-decoration: none;
        color: #000;
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
        <div>学号与姓名 <s:select id="h1" list="#studentList" listKey="id" listValue="name" headerKey="%{#student.id}"
                             headerValue="%{#student.name}"> </s:select>
            <span id="nextStudent" style="cursor: pointer">下一个</span></div>
        <ul id="tabfirst">
            <li class="tabclick"><span>学生信息</span></li>
            <li><span>作业</span></li>
            <li><span>登录历史</span></li>
        </ul>
        <div id="area">
            <div class="contentin contentfirst">
                <div class="b1">学号： <span id="number">${student.number}</span></div>
                <div class="b1">姓名：<span id="name">${student.name }</span></div>
                <div class="b1">上次登录时间：<span id="loginTime"><fmt:formatDate value="${application.latestLoginTime }"
                                                                            type="both"/>  </span></div>
                <div class="b1">加入班级时间：<span id="joinTime"><fmt:formatDate value="${application.joinClassTime }"
                                                                           type="both"/> </span></div>
                <!-- button (形状) -->
                <div style="position:relative">
                    <div class="u" id="save">
                        <img class="u_img" src="style/images/button_u15.png"/>

                        <div class="u1" class="text">
                            <p><span>重置登录口令</span></p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="contentfirst">
                <table>
                    <tr>
                        <td width="40px">序号</td>
                        <td width="450px">作业</td>
                        <td width="150px"></td>
                    </tr>
                    <s:iterator value="%{#jobs}" status="status">
                        <tr>
                            <td>${status.count}</td>
                            <td>${jobName}</td>
                            <td><s:a action="fileDownload_jobFile?id=%{id}">下载</s:a></td>
                        </tr>

                    </s:iterator>
                </table>

            </div>
            <div class="contentfirst">
                共计登录${application.timeCount}
                <table>
                    <tr>
                        <td width="40px">序号</td>
                        <td width="350px">登录时间</td>
                        <td width="250px">退出时间</td>
                    </tr>
                    <s:iterator value="recordList" status="status">
                        <tr>
                            <td>${status.count}</td>
                            <td><fmt:formatDate value="${loginTime}" type="both"/></td>
                            <td><fmt:formatDate value="${logoutTime}" type="both"/></td>
                        </tr>

                    </s:iterator>
                </table>
                <!--分页信息-->
                <%@ include file="/WEB-INF/jsp/public/pageView.jspf" %>
                <s:form action="student_show?id=%{id}" id="form">
                    <s:hidden value="2" name="option"></s:hidden>
                </s:form>
            </div>
        </div>
    </div>
</div>
<div id="bottom">
    <jsp:include page="../public/bottom.jsp"></jsp:include>
</div>
</body>
</html>
