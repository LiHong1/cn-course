<%@ page language="java" pageEncoding="utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>工程案例</title>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
</head>
<body>
<div id="title">${Case.title}</div>
案例内容：
<div style="margin-bottom: 100px;">${Case.content}</div>
<div>
    附件：
    <div>
        <s:iterator value="#Case.materials" status="status">

            <div style="height: 30px">
                <a href="material_show.action?id=${id}">${name}</a> &nbsp &nbsp

                <a href="fileDownload_materialFile.action?id=${id}">下载</a>
            </div>
        </s:iterator>
    </div>
</div>
</body>
</html>
