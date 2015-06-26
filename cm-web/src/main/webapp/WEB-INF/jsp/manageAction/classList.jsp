<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
    <script language="javascript" src="${pageContext.request.contextPath}/script/jquery.validate.js"></script>
</head>
<script type="text/javascript">
    $(document).ready(function () {
        $("#find_button").click(function () {
            $("#find").submit();
        });
        $("#add_button").click(function () {
            $("#add").submit();

        });

        $("#close").click(function (e) {
            message = "你确定关闭？";

        });
        $(".delete").click(function (e) {
            message = "你确定删除？";
            return confirm(message);
        });
        $("#form2").validate({
            rules: {
                className: "required"
            },
            messages: {
                className: "请输入班级名"
            },
        });
    });
</script>
<style>
    #table2 {
        text-align: center;
        border-spacing: 1px;
        border-spadding: 1px;
        border: 0px;
        background-color: black;
    }

    #table1 {
        text-align: center;
        border: 0px;
    }

    tr {
        background-color: white;
        height: 30px;
    }

    thead tr {
        background-color: #711;
        color: white;
    }

    #addCourse {
        margin-top: 40px;
        margin-bottom: 20px;
    }

    input {
        width: 250px;
    }

    #form2 label.error {
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
        <div id="title">班级列表</div>
        <div>
            <s:form action="class_get.action" id="find" style="float:left">
                课程名：&nbsp <input id="find_courseName" name="courseName" type="text"/> </br>
                教　师：&nbsp <input id="find_teacherName" name="teacherName" type="text"/> </br>
                班级名：&nbsp <input id="find_className" name="className" type="text"/>
            </s:form>
            <div style="float: left;margin-top:50px;margin-left:100px;position:relative">
                <div class="u" id="find_button">
                    <img class="u_img" src="style/images/button_u15.png"/>

                    <div class="u1" class="text">
                        <p><span>查找</span></p>
                    </div>
                </div>
            </div>
        </div>
        <div style="clear:both">
            <table width="700" id="table2">
                <thead>
                <tr>
                    <td width="30">序号</td>
                    <td width="100">班级</td>
                    <td width="100">课程</td>
                    <td width="80">教师</td>
                    <td width="100">操作</td>
                </tr>
                </thead>
                <tbody>
                <s:iterator value="#stateList" status="status">
                    <tr>
                        <td>${status.count}</td>
                        <td>${clas.name}</td>
                        <td>${course.name}</td>
                        <td>${teacher.name }</td>
                        <td><s:a action="state_delete?id=%{id}" cssClass="delete">删除</s:a> <s:a
                                action="class_off?id=%{id}" id="close">关闭</s:a></td>
                    </tr>
                </s:iterator>
                </tbody>
            </table>
        </div>
        <s:form action="class_add.action" id="add">
            <div id="addCourse">
                新增班级<br>

                <p>课程名：&nbsp
                    <s:select list="#courseList" id="add_courseName" cssStyle="width:153px" name="courseName"
                              listKey="%{name}" listValue="%{name}" headerKey="0" headerValue="请选择课程">
                    </s:select>
                </p>

                <p>教　师：&nbsp<s:select list="#teacherList" id="add_teacherName" cssStyle="width:153px" name="teacherName"
                                      listKey="%{name}" listValue="%{name}" headerKey="0" headerValue="请选择班级">
                </s:select>
                </p>

                <p> 班级名：&nbsp <input id="add_className" name="className" type="text"/></p>
            </div>
            <div style="position:relative">
                <!-- button (形状) -->
                <div class="u" id="add_button">
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