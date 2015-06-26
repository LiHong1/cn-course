<%@ page language="java" pageEncoding="utf-8" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>已经答卷</title>
</head>
<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
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
    <div id="content"></div>
    <div id="title">我的考试</div>
    <div><p>考试试题：</p>

        <div style="height:130px">${answer.paper.problem}</div>
    </div>
    <div><p>我的答卷：</p>

        <div>${answer.answer}</div>
    </div>
</div>
<div id="bottom">
    <jsp:include page="../public/bottom.jsp"></jsp:include>
</div>
</body>
</html>
