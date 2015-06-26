<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/core.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/main.js"></script>
</head>
<script type="text/javascript">

    $(document).ready(function () {
        $("#add").click(function () {
            window.location.href = "topic_addUI.action";
        });
    });

</script>
<style>
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

    thead tr {
        background-color: #33f;
        color: white;
    }

    a {
        text-decoration: none;
        color: black;
    }

</style>
<body>
<div id="top">
    <jsp:include page="../${role}Action/top.jsp"></jsp:include>
</div>
<div id="left">
    <jsp:include page="../${role}Action/menu.jsp"></jsp:include>
</div>
<div id="right">
    <div id="content">
        <div id="title">讨论区</div>
        <div>
            <table width="700">
                <thead>
                <tr>
                    <td width="80">序号</td>
                    <td width="400">标题</td>
                    <c:if test="${teacher}">
                        <td width="50"></td>
                    </c:if>
                </tr>
                </thead>
                <s:iterator value="#topics" status="status">
                    <tr>
                        <td>${status.count}</td>
                        <td><s:a action="topic_show?id=%{id}">${title}</s:a></td>
                        <c:if test="${teacher}">
                            <td><s:a action="topic_delete?id=%{id}" cssClass="delete">删除</s:a></td>
                        </c:if>
                    </tr>
                </s:iterator>
            </table>
        </div>
        <c:if test="${teacher}">
            <div style="position: relative;">
                <!-- button (形状) -->
                <div class="u" id="add">
                    <img class="u_img" src="style/images/button_u15.png"/>

                    <div class="u1" class="text">
                        <p><span>新增讨论题目</span></p>
                    </div>
                </div>
            </div>
        </c:if>
    </div>
</div>
<div id="bottom">
    <jsp:include page="../public/bottom.jsp"></jsp:include>
</div>
</body>
</html>
