<%@ page language="java" pageEncoding="utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
</head>

<body>
<div id="top">
    <jsp:include page="../studentAction/top.jsp"></jsp:include>
</div>
<div id="left">
    <jsp:include page="../studentAction/menu.jsp"></jsp:include>
</div>
<div id="right">
    <div id="content">
        <div id="title">课程信息</div>
        <div>${course.information}</div>
    </div>
</div>
<div id="bottom">
    <jsp:include page="../public/bottom.jsp"></jsp:include>
</div>
</body>
</html>
