<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>作业列表</title>
    <script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/resources/script/jquery.changeColor.js"></script>
    <script type="text/javascript">
    $(document).ready(function(){
  	  $("#upload").click(function(){
  	   $("#form").submit();
  	  });
  	$("tbody tr:odd").changeColor({oddColor:"#ccc"});
    });
    </script>
    <style type="text/css">
 table{
   text-align: center;
   margin: auto;
   border-spacing: 1px;
   border-spadding:1px;
   border:0px;
   background-color: #ccc;
   clear: right;
  }
  tr{
  background-color: white;
  height: 30px;
  }
  table thead  tr{
  background-color: #711;
   color:white;
  }
    </style>
  </head>
  <body>
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
			      <c:forEach items="${jobs}" var="job" varStatus="status">
						<tr>
			   			<td>${status.count}</td>
			            <td>${job.jobName}</td>
			            <td>
			            <a href="job/delete/${job.id}">删除</a></td>
			  			</tr>
					</c:forEach>
					</tbody>
			       </table>
			        
			           <form method="post" enctype="multipart/form-data" action="job/add" id="form">
			           <div style="margin-left:30px;margin-top:30px"><input type="file" name="file" value="选择文件"><input type="text" name="name"></div>
			           </form> 
			        <div style="position: relative;margin: auto;width: 140px;height: 30px;">
				        <!-- button (形状) -->
				        <div class="u" id="upload">
				           <img class="u_img" src="${pageContext.request.contextPath}/resources/style/images/button_u15.png"/>
				           <div class="u1" class="text">
				           <p><span>上传作业</span></p>
				           </div>
				        </div>
			        </div>
  </body>
</html>
