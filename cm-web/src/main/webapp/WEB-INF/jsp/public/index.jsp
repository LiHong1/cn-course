<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
    <link rel="stylesheet" type="text/css" href="style/css/index.css">
    <script language="javascript" type='text/javascript'
            src='${pageContext.request.contextPath}/script/jquery-2.1.1.min.js'></script>
    <script language="javascript" type="text/javascript"
            src="${pageContext.request.contextPath}/script/jquery.changeColor.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/core.js"></script>
    <script language="javascript" type="text/javascript"
            src="${pageContext.request.contextPath}/script/jquery.validate.js"></script>
</head>

<body style="margin: 0px;padding: 0px">
<div id="top">
    <jsp:include page="../${role}Action/top.jsp"></jsp:include>
</div>
<div id="center">
    <div id="left">
        <jsp:include page="../${role}Action/menu.jsp"></jsp:include>
    </div>
    <div id="right"></div>
</div>
<div id="bottom" style="height:50px">
    <jsp:include page="bottom.jsp"></jsp:include>
</div>
</body>

</html>
