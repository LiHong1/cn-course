<%@ page language="java" pageEncoding="utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
</head>
<style>
    body {
        width: 700px;
        margin: 100px;
        margin-top: 40px;
    }

    p {
        font-size: 20px;
        font-weight: bold;
    }
</style>

<body onload="setHeight();changeTitle('已经答卷');">
<div id="title">我的考试</div>
<div><p>考试试题：</p>

    <div style="height:130px">${paper.problem}</div>
</div>
<div><p>我的答卷：</p>

    <div>${answer}</div>
</div>
</body>
</html>
