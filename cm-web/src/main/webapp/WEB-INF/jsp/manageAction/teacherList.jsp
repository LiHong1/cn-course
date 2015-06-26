<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
    <script language="javascript" type='text/javascript'
            src='${pageContext.request.contextPath}/script/jquery-2.1.1.min.js'></script>
    <script language="javascript" type="text/javascript"
            src="${pageContext.request.contextPath}/script/jquery.changeColor.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/core.js"></script>
    <script language="javascript" type="text/javascript"
            src="${pageContext.request.contextPath}/script/jquery.validate.js"></script>
</head>
<script type="text/javascript">
    $(document).ready(function () {
        $("#add").click(function () {
            $("#form").submit();
        });
        $("tbody tr:odd").changeColor({oddColor: "#ccc"});
        $("#form").validate({
            rules: {
                name: "required",
                number: "required"
            },
            messages: {
                name: "请输入教师姓名",
                number: "请输入教师工号"
            }
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

    thead tr {
        background-color: #900;
    }

    #addTeacher {
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
<div id="right">
    <div id="content">
        <div id="title">教师列表</div>
        <div>
            <table width="700">
                <thead>
                <tr>
                    <td width="80">序号</td>
                    <td width="310">教师</td>
                    <td width="310">工号（登录用）</td>
                </tr>
                </thead>
                <tbody>
                <s:iterator value="#teacherList" status="status">
                    <tr>
                        <td>${status.count}</td>
                        <td>${name}</td>
                        <td>${number}</td>
                    </tr>
                </s:iterator>
                </tbody>
            </table>
        </div>
        <s:form action="teacher_add.action" id="form">
            <div id="addTeacher">
                新增教师<br>
                教师名：&nbsp <input id="name" name="name" type="text" style="width:250px "/> <br>
                工　号：&nbsp <input id="number" name="number" type="text" style="width:250px "/>
            </div>
            <div style="position:relative">
                <!-- button (形状) -->
                <div class="u" id="add">
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
