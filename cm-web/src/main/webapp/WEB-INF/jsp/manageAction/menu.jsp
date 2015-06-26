<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/menu.css">
</head>

<body>
<div id="menu">
    <div><s:a action="course_list.action" target="_self">课程列表</s:a></div>
    <div><s:a action="teacher_list.action" target="_self">教师管理</s:a></div>
    <div><s:a action="class_list.action" target="_self">班级管理</s:a></div>
    <div><s:a action="student_list.action" target="_self">学生管理</s:a></div>
    <div><s:a action="manage_system.action" target="_self">系统设置</s:a></div>
    <div><s:a action="user_changePasswordUI.action" target="_self">更改口令</s:a></div>
    <div><s:a action="user_logout" target="_self">退出</s:a></div>
</div>
</body>
</html>
