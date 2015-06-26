<%@ page language="java" pageEncoding="UTF-8" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/forum.css"/>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath }/ueditor1_2_6_1-utf8-jsp/themes/default/css/ueditor.css"/>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/ueditor1_2_6_1-utf8-jsp/ueditor.config.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/ueditor1_2_6_1-utf8-jsp/ueditor.all.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/ueditor1_2_6_1-utf8-jsp/lang/zh-cn/zh-cn.js"></script>
</head>

<script type="text/javascript">
    $(document).ready(function () {
        window.UEDITOR_HOME_URL = "${pageContext.request.contextPath }/ueditor1_2_6_1-utf8-jsp/";
        window.UEDITOR_CONFIG.autoHeightEnabled = false;
        window.UEDITOR_CONFIG.initialFrameHeight = 250;
        window.UEDITOR_CONFIG.initialFrameWidth = 700;
        //window.UEDITOR_CONFIG.imageUrl="servlet/myservlet"             
        // ,imagePath:URL + "jsp/" 
        UE.getEditor('content');

        $("#reply").click(function () {
            $("#form1").submit();
        });

    });
</script>
<style>
    table {
        text-align: center;
        margin: auto;
        border-spacing: 1px;
        border-spadding: 1px;
        border: 0px;
        background-color: #999;
    }

    tr {
        background-color: white;
        height: 30px;
    }

    body {
        width: 700px;
        margin: 100px;
        margin-top: 40px;
    }

    thead tr {
        background-color: #33f;
        color: white;
    }

</style>
<body onload="setHeight();changeTitle('讨论题')">
<div id="title">讨论题</div>
<table>
    <s:if test="currentPage == 1">
        <thead>
        <tr>
            <td width="200px">标题：</td>
            <td width="500px">${topic.title}</td>
        </tr>
        </thead>
        <tr>
            <td height="100px">${topic.state.teacher.name} [楼主]</td>
            <td height="100px">${topic.content}</td>
        </tr>
    </s:if>
    <s:iterator value="recordList" status="status">
        <tr>

            <td width="200px" height="100px">${teacher.name}${student.name} <font
                    color=#C30000>[${(currentPage - 1) * pageSize + status.count}楼]</font></td>
            <td width="500px" height="100px">${content}</td>
        </tr>
    </s:iterator>
</table>
<!--分页信息-->
<%@ include file="/WEB-INF/jsp/public/pageView.jspf" %>
<s:form action="topic_show?id=%{id}" id="form"></s:form>
<s:form action="reply_add?topicId=%{#topic.id}" method="post" id="form1">
    <div style="margin-bottom:20px">
        <div style="margin-left:40px;"><textarea name="content" id="content"></textarea></div>
    </div>
</s:form>
<div style="position: relative;">
    <!-- button (形状) -->
    <div class="u" id="reply" style="margin-left:40px">
        <img class="u_img" src="style/images/button_u15.png"/>

        <div class="u1" class="text">
            <p><span>回复</span></p>
        </div>
    </div>
</div>
</body>
</html>
