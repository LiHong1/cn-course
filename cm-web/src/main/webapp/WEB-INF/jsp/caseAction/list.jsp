<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
    <script language="javascript" type="text/javascript"
            src="${pageContext.request.contextPath}/script/jquery.changeColor.js"></script>
</head>
<script type="text/javascript">
    $(document).ready(function () {
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
    <jsp:include page="../teacherAction/top.jsp"></jsp:include>
</div>
<div id="left">
    <jsp:include page="../teacherAction/menu.jsp"></jsp:include>
</div>
<div id="right">
    <div id="content">
        <div id="title">案例列表</div>
        <div style="float:right;margin-bottom: 20px"><s:a action="case_addUI.action">新增案例</s:a></div>
        <table>
            <thead>
            <tr>
                <td style="width: 60px">序号</td>
                <td style="width: 500px">案例</td>
                <td style="width: 130px">操作</td>
            </tr>
            <thead>
            <tbody>
            <s:iterator value="#caseList" status="status">
                <tr>
                    <td>${status.count}</td>
                    <td>${title}</td>

                    <td><s:a action="case_updateUI?id=%{id}">编辑</s:a>
                        <s:a action="case_delete?id=%{id}">删除</s:a></td>

                </tr>
            </s:iterator>
            </tbody>
        </table>
    </div>
</div>
<div id="bottom">
    <jsp:include page="../public/bottom.jsp"></jsp:include>
</div>
</body>
</html>
