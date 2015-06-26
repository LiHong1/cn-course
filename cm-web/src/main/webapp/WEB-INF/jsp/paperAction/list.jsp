<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
    <script language="javascript" type="text/javascript"
            src="${pageContext.request.contextPath}/script/jquery.changeColor.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/dwrService.js"></script>
    <link type="text/css" rel="stylesheet" href="style/css/table.css">
</head>
<script type="text/javascript">
    $(function () {
        $("#delete").click(function () {
            message = "你确定删除吗？";
            return confirm(message);
        });
        $(".paper").click(function () {
            alert("fth");
            dwrService.addToExam($(this).val());
            //$("#form").submit();
        });
        $("tbody tr:odd").changeColor({oddColor: "#ccc"});
    });
</script>
<body>
<div id="top">
    <jsp:include page="../teacherAction/top.jsp"></jsp:include>
</div>
<div id="left">
    <jsp:include page="../teacherAction/menu.jsp"></jsp:include>
</div>
<div id="right">
    <div id="content">
        <div id="title">试题列表</div>
        <div style="float:right;margin:10px;margin-right:20px;"><s:a action="paper_addUI.action">新增试题</s:a></div>
        <s:form action="paper_addToExam" id="form">
            <table>
                <thead>
                <tr>
                    <td width="40px">加入考试</td>
                    <td width="40px">序号</td>
                    <td width="450px">试题</td>
                    <td width="150px">操作</td>
                </tr>
                </thead>
                <tbody>
                <s:iterator value="#papers" status="status">
                    <tr>
                        <td><s:checkbox name="paperIds" cssClass="paper" value="%{type}"
                                        fieldValue="%{id}"></s:checkbox></td>
                        <td>${status.count}</td>
                        <td>${name}</td>

                        <td>
                            <s:a action="paper_editUI?id=%{id}">编辑</s:a>
                            <s:a action="paper_delete?id=%{id}" cssClass="delete" id="delete">删除</s:a></td>
                    </tr>
                </s:iterator>
                </tbody>
            </table>
        </s:form>
    </div>
</div>
<div id="bottom">
    <jsp:include page="../public/bottom.jsp"></jsp:include>
</div>
</body>
</html>
