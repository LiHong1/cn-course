<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title></title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/menu.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/menu.css">
</head>

<body>
<div id="menu">
    <div><s:a action="student_showCourse.action" target="_self">课程信息</s:a></div>
    <div><s:a action="material_list.action" target="_self">学习材料</s:a></div>
    <div><s:a action="student_caseList.action" target="_self">工程案例</s:a></div>
    <div><s:a action="student_jobList.action" target="_self">我的作业</s:a></div>
    <div><s:a action="student_myExam.action" target="_self">我的考试</s:a></div>
    <div><s:a action="topic_list.action" target="_self">讨论区</s:a></div>
    <div><s:a action="user_changePasswordUI.action" target="_self">更改口令</s:a></div>
    <div><s:a action="user_logout" target="_self">退出</s:a></div>
</div>
</body>
</html>
