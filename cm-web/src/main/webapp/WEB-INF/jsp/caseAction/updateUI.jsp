<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/ueditor1_2_6_1-utf8-jsp/ueditor.config.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/ueditor1_2_6_1-utf8-jsp/ueditor.all.min.js"></script>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath }/ueditor1_2_6_1-utf8-jsp/themes/default/css/ueditor.css"/>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/ueditor1_2_6_1-utf8-jsp/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            window.UEDITOR_HOME_URL = "${pageContext.request.contextPath }/ueditor1_2_6_1-utf8-jsp/";
            window.UEDITOR_CONFIG.initialFrameHeight = 250;
            window.UEDITOR_CONFIG.initialFrameWidth = 700;
            window.UEDITOR_CONFIG.autoHeightEnabled = false;
            UE.getEditor('content');

            $("#update").click(function () {
                $("#form1").submit();
            });
            $("#upload").click(function () {
                $("#form2").submit();
            });

        });

    </script>
</head>
<style>
    table {
        text-align: center;
        margin: auto;
        border-spacing: 1px;
        border-spadding: 1px;
        border: 0px;
        background-color: black;
        clear: right;
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

</style>
<body onload="setHeight();changeTitle('修改案例');">
<div id="title">修改案例</div>

<s:form action="case_update" id="form1" method="post">
    <s:hidden name="id"></s:hidden>
    <div>标题
        <div><s:textfield name="title" value="%{#Case.title}"></s:textfield></div>
    </div>
    <div>内容
        <div><s:textarea name="content" id="content" cssStyle="height:200px"
                         value="%{#Case.content}"></s:textarea></div>
    </div>
</s:form>
<div>
    添加附件:
    <s:iterator value="%{#Case.materials}">
        <div>　　　${name}　　 <a href="material_caseMatedelete.action?id=${id}">删除</a></div>
    </s:iterator>
    <form method="post" enctype="multipart/form-data" action="fileUpload_caseMaterial.action?id=${Case.id}" id="form2">
        <input type="file" name="file" value="选择文件"><input type="text" name="name">

        <div style="margin-top:50px;">
            <div style="position:relative;float:left">
                <!-- button (形状) -->
                <div class="u" id="upload">
                    <img class="u_img" src="style/images/button_u15.png"/>

                    <div class="u1" class="text">
                        <p><span>上传附件</span></p>
                    </div>
                </div>
            </div>
            <div style="position:relative;margin-left:350px;float:left">
                <!-- button (形状) -->
                <div class="u" id="update">
                    <img class="u_img" src="style/images/button_u15.png"/>

                    <div class="u1" class="text">
                        <p><span>保存</span></p>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>

</body>
</html>
