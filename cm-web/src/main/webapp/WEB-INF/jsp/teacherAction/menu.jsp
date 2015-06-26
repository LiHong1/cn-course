<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title></title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/menu.css">
</head>
<body>
<div id="menu">
    <div><s:a action="teacher_courseSetUI.action" target="_self">课程设置</s:a></div>
    <div><s:a action="case_list.action" target="_self">案例管理</s:a></div>
    <div><s:a action="teacher_classManageUI.action" target="_self">班级管理</s:a></div>
    <div><s:a action="teacher_studentList.action" target="_self">学生管理</s:a></div>
    <div><s:a action="paper_list.action" target="_self">考试试题</s:a></div>
    <div><s:a action="state_examSetUI.action" target="_self">考试管理</s:a></div>
    <div><s:a action="topic_list.action" target="_self">讨论区</s:a></div>
    <div><s:a action="user_changePasswordUI.action" target="_self">更改口令</s:a></div>
    <div><s:a action="user_logout" target="_self">退出</s:a></div>
</div>
</body>
</html>
