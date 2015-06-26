<%@ page language="java" pageEncoding="utf-8" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
</head>
<script type="text/javascript">

    $(document).ready(function () {
        var message;
        $("#h1").click(function () {
            if (${reExamAble}==true
            )
            message = "确定生成试题！";
            else
            message = "一旦生成试题，就不能重新生成试题，你确定操作！";
            var mes = confirm(message);
            if (mes == true) {
                $("#form").submit();
            }

        });
        if (${examAble}==false
        )
        {
            $("#area").html("考试尚未开启，请联系任课老师！");
        }
        else
        if (${examState}==true && ${reExamAble} == false
        )
        {
            $("#area").html("你已经考试完了,不能重新考试！");
        }
    });

</script>
<body>
<div id="top">
    <jsp:include page="../studentAction/top.jsp"></jsp:include>
</div>
<div id="left">
    <jsp:include page="../studentAction/menu.jsp"></jsp:include>
</div>
<div id="right">
    <div id="content">
        <div id="title">我的考试</div>
        <div id="area">
            <s:form action="student_getPaper" id="form">
                <div>考试注意事项：
                    <div style="height:130px">${examAttention}</div>
                </div>

                <div style="width: 101px;height: 76px;margin:auto; position: relative;">
                    <div class="u" id="h1">
                        <img class="u_img" src="style/images/student/button_u49.png"/>
                        <!-- Unnamed () -->
                        <div class="u1" style="margin-top:20px">
                            <p><span>生成试题</span></p>
                        </div>
                    </div>

                </div>
            </s:form>
        </div>
    </div>
</div>
<div id="bottom">
    <jsp:include page="../public/bottom.jsp"></jsp:include>
</div>
</body>
</html>
