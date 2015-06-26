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
        <div id="title">学习材料</div>
        <s:iterator value="#materialList" status="status">
            <div style="height: 30px">
                    ${status.count}.<s:if test="%{type==1}"><a
                    href="material_show.action?id=${id}">${name}</a> &nbsp</s:if>
                <s:else>${name}</s:else>
                <s:if test="%{type==0}"><a href="material_player.action?id=${id}">播放</a> &nbsp</s:if>
                <a href="fileDownload_materialFile.action?id=${id}">下载</a>
            </div>
        </s:iterator>
    </div>
</div>
<div id="bottom">
    <jsp:include page="../public/bottom.jsp"></jsp:include>
</div>
</body>
</html>
