<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/forum.css"/>
</head>
<script type="text/javascript">

    $(function () {

        $("#delete").click(function () {
            $("#deleteForm").submit();
        });
        $(".delete").click(function (e) {
            message = "你确定删除?";
            return confirm(message);
        });

    });
</script>
<style>
    table {
        text-align: center;
        border-spacing: 1px;
        border-spadding: 1px;
        border: 0px;
        background-color: black;
    }

    tr {
        background-color: white;
        height: 30px;
    }
</style>
<body>
<div id="top">
    <jsp:include page="top.jsp"></jsp:include>
</div>
<div id="left">
    <jsp:include page="menu.jsp"></jsp:include>
</div>
<div id="right">
    <div id="content">
        <div id="title">学生列表</div>
        <div>
            <table width="700">
                <tr>
                    <td width="80">学号</td>
                    <td width="400">姓名</td>
                    <td width="50"></td>
                </tr>

                <s:iterator value="recordList" status="status">
                    <tr>
                        <td>${number}</td>
                        <td>${name}</td>
                        <td><s:a action="student_delete?id=%{id}" cssClass="delete">删除</s:a></td>
                    </tr>
                </s:iterator>
            </table>
        </div>
        <!--分页信息-->
        <%@ include file="/WEB-INF/jsp/public/pageView.jspf" %>
        <s:form action="student_list?id=%{id}" id="form"></s:form>
        <s:form action="student_likeDelete" id="deleteForm">按学号模糊删除：<input type="text" name="number"/></s:form>
        <div style="position:relative">
            <!-- button (形状) -->
            <div class="u" id="delete">
                <img class="u_img" src="style/images/button_u15.png"/>

                <div class="u1" class="text">
                    <p><span>删除</span></p>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="bottom">
    <jsp:include page="../public/bottom.jsp"></jsp:include>
</div>
</body>
</html>
