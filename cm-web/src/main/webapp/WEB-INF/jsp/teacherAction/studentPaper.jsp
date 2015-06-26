<%@ page language="java" pageEncoding="utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
</head>
<script type="text/javascript">
    $(document).ready(function () {
        if (${examState==false}) {
            $("body").html("该学生尚未答题");
        }
    });

</script>
<style>
    body {
        width: 700px;
        margin: 100px;
        margin-top: 40px;
    }

    p {
        font-size: 20px;
        font-weight: bold;
    }
</style>

<body onload="setHeight();changeTitle('学生答卷');">
<div id="title">学生答卷</div>
<div><p>考试试题：</p>

    <div style="height:130px">${paper.problem}</div>
</div>
<div><p>学生答案：</p>

    <div>${answer}</div>
</div>
</body>
</html>
