<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
    <script language="javascript" type="text/javascript"
            src="${pageContext.request.contextPath}/script/jquery.changeColor.js"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/jquery.validate.js"></script>
</head>
<script type="text/javascript">
    $(document).ready(function () {
        $("#add").click(function () {
            $("#form").submit();
        });

        $("tbody tr:odd").changeColor({oddColor: "#ccc"});
        $("#form").validate({
            rules: {
                name: "required"
            },
            messages: {
                name: "请输入课程名"
            },
        });
    });
</script>
<style>
    table {
        text-align: center;
        border-spacing: 1px;
        border: 0px;
        background-color: #555;
        color: #333;
        font-size: 14px;
    }

    tr {
        background-color: white;
        height: 30px;
    }

    table thead tr {
        background-color: #711;
        color: white;
    }

    tr.odd {
        background: #ccc;
    }

    #addCourse {
        margin-top: 40px;
        margin-bottom: 20px;
    }

    #form label.error {
        color: red;
        font-size: 15px;
        margin-left: 5px;
        padding-left: 16px;
    }
</style>

<body>
<div id="top">
    <jsp:include page="top.jsp"></jsp:include>
</div>
<div id="left">
    <jsp:include page="menu.jsp"></jsp:include>
</div>
<div id="right" style="">
    <div id="content">
        <div id="title">课程列表</div>
        <div>
            <table width="700">
                <thead>
                <tr>
                    <td width="150">序号</td>
                    <td width="550">课程名</td>
                </tr>
                </thead>
                <tbody>
                <s:iterator value="#courseList" status="status">
                    <tr>
                        <td>${status.count}</td>
                        <td>${name}</td>
                    </tr>
                </s:iterator>
                </tbody>
            </table>
        </div>
        <s:form action="course_add.action" id="form">
            <div id="addCourse">
                新增课程<br>
                课程名：&nbsp <input id="courseName" type="text" style="width:250px " name="name"/>
            </div>
            <div style="position:relative">
                <!-- button (形状) -->
                <div id="add" class="u">
                    <img class="u_img" src="style/images/button_u15.png"/>

                    <div class="u1" class="text">
                        <p><span>新增</span></p>
                    </div>
                </div>
            </div>
        </s:form>
    </div>
</div>
<div id="bottom">
    <jsp:include page="../public/bottom.jsp"></jsp:include>
</div>
</body>
</html>
