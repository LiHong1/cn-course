<%@ page language="java" pageEncoding="utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
    <script language="javascript" type="text/javascript"
            src="${pageContext.request.contextPath}/script/jquery.changeColor.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("tbody tr:odd").changeColor({oddColor: "#ccc"});
        });
    </script>
    <style type="text/css">
        table {
            text-align: center;
            border-spacing: 1px;
            border-spadding: 1px;
            border: 0px;
            background-color: #999;

        }

        tr {
            background-color: white;
            height: 30px;
        }

        a {
            text-decoration: none;
            color: #333333;
            font-size: 13px;
        }

        table thead tr {
            background-color: #711;
            color: white;
        }
    </style>
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
        <div id="title">工程案例</div>
        <table>
            <thead>
            <tr>
                <td width="100px">序号</td>
                <td width="500px">案例</td>

            </tr>
            </thead>
            <tbody>
            <s:iterator value="#caseList" status="status">
                <tr>
                    <td>${status.count}</td>
                    <td><s:a action="student_showCase?id=%{id}">${title}</s:a></td>
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
