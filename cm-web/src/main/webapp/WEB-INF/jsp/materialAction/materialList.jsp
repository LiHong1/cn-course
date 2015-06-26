<!--
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
<script language="javascript" src="${pageContext.request.contextPath}/fckeditor/fckeditor.js" charset="utf-8"></script>
<script type="text/javascript">

$(document).ready(function(){
$("#upload").click(function(){
$("#form").submit();

});

});
</script>
</head>
<style>
body{
width: 700px;
margin:100px;
margin-top:40px;
}
#area{
margin-top:30px;
margin-bottom: 40px;
}
span{
margin-top: -5px;
}
table{
text-align: center;
border-spacing: 1px;
border:0px;
}
thead tr{
background-color: #711;
color:white;
}
tr{
height: 30px;
}
</style>
<body onload="changeTitle('上传材料');setHeight();">
<div id="title">材料上传</div>
<div>
文件列表
<table>
<thead>
<tr><td style="width: 60px">序号1</td>
<td style="width: 300px">原始文件名</td>
<td style="width: 200px">保存文件名</td>
<td style="width: 100px">操作</td>
</tr>
</thead>
<s:iterator value="#materialList" status="status">
    <tr>
    <td>${status.count}</td>
    <td>${originalName}</td>
    <td>${name}</td>
    <td><s:a action="teacher_deleteMaterial?id=%{id}">删除</s:a></td>

    </tr>
</s:iterator>


</table>


</div>

<div>
<form method="post" enctype="multipart/form-data" action="fileUpload.action" id="form">
<input type="file" name="file" value="选择文件"><input type="text" name="name">

</form>


</div>
<div style="position:relative">
<div class="u" id="upload">
<img class="u_img" src="style/images/button_u15.png"/>
<div class="u1" class="text">
<p><span>上传文件</span></p>
</div>
</div>
</div>
</body>
</html>-->
