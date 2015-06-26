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
            $("#upload").click(function () {
                $("#form").submit();
            });
            $("tbody tr:odd").changeColor({oddColor: "#ccc"});
        });
    </script>
    <style type="text/css">
        table {
            text-align: center;
            margin: auto;
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
        <div id="title">我的作业</div>
        <table>
            <thead>
            <tr>
                <td width="40px">序号</td>
                <td width="450px">作业</td>
                <td width="150px">操作</td>
            </tr>
            </thead>
            <tbody>
            <s:iterator value="jobs" status="status">
                <tr>
                    <td>${status.count}</td>
                    <td>${jobName}</td>
                    <td>
                        <s:a action="student_jobDelete?id=%{id}">删除</s:a></td>
                </tr>
            </s:iterator>
            </tbody>
        </table>

        <form method="post" enctype="multipart/form-data" action="fileUpload_job.action" id="form">
            <div style="margin-left:30px;margin-top:30px"><input type="file" name="file" value="选择文件"><input type="text"
                                                                                                             name="name">
            </div>
        </form>
        <div style="position: relative;margin: auto;width: 140px;height: 30px;">
            <!-- button (形状) -->
            <div class="u" id="upload">
                <img class="u_img" src="style/images/button_u15.png"/>

                <div class="u1" class="text">
                    <p><span>上传作业</span></p>
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
