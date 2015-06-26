<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
    <script language="javascript" type="text/javascript"
            src="${pageContext.request.contextPath}/script/jquery.changeColor.js"></script>
    <link type="text/css" rel="stylesheet" href="style/css/forum.css"/>
</head>
<script type="text/javascript">

    $(document).ready(function () {
        $("#delete").click(function () {
            message = "你确定删除？";
            return confirm(message);
        });
        $("#resetPassword").click(function () {
            message = "你确定重置口令？";
            return confirm(message);
        });
        $("tbody tr:odd").changeColor({oddColor: "#ccc"});
    });
</script>
<style>
    table {
        text-align: center;
        border-spacing: 1px;
        border-spadding: 1px;
        border: 0px;
        background-color: #ccc;
        clear: right;
    }

    tr {
        background-color: white;
        height: 30px;
    }

    table thead tr {
        background-color: #711;
        color: white;
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
        <div id="title">学生列表</div>
        <table>
            <thead>
            <tr>
                <td width="40px">序号</td>
                <td width="150px">学号</td>
                <td width="150">姓名</td>
                <td width="250px">登录时间</td>
                <td width="450px">操作</td>
            </tr>
            </thead>
            <tbody>
            <s:iterator value="recordList" status="status">
                <tr>
                    <td>${status.count}</td>
                    <td>${student.number}</td>
                    <td>${student.name}</td>
                    <td><fmt:formatDate value="${latestLoginDate}" type="both"/></td>
                    <td><s:a action="teacher_showStudentPaper?id=%{student.id}">查看答卷</s:a>
                        <s:a action="student_resetPassword?id=%{student.id}" id="resetPassword">重置口令</s:a>
                        <s:a action="student_show?id=%{student.id}">详情</s:a>
                        <s:a action="teacher_reExam?id=%{student.id}">重考</s:a>
                        <s:a action="teacher_deleStudent?id=%{student.id}" cssClass="delete" id="delete">删除</s:a></td>
                </tr>
            </s:iterator>
            </tbody>
        </table>
        <!--分页信息-->
        <%@ include file="/WEB-INF/jsp/public/pageView.jspf" %>
        <form action="teacher_studentList.action" id="form"></form>
    </div>
</div>
<div id="bottom">
    <jsp:include page="../public/bottom.jsp"></jsp:include>
</div>
</body>
</html>
