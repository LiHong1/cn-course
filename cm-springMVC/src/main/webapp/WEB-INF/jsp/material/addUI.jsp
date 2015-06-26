<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>上传材料</title>
    <script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/script/jquery.changeColor.js"></script>
     <link type="text/css" rel="stylesheet" href="style/css/table.css">
    <script type="text/javascript">
    var uploadAble=false;
	$(document).ready(function(){
		var fileInput=$("#file");
	  $("#upload").click(function(){
		  if(fileInput.val()=="")
			  alert("你必须要先指定上传的文件！！");
		  else if(uploadAble==false)
			  alert("你上传的文件超过最大值200M");
		  else  $("#form").submit();
	  });
	});
		 function getFileSize(target)
	      { 
	      var fileSize = target.files[0].size;         
	    	if(fileSize>200*1024*1024)
	    		uploadAble=false;
	    	else uploadAble=true;
	      }
    </script>
	  <style>
		  #area{
			  margin-top:30px;
			  margin-bottom: 40px;
		  }
		  span{
			  margin-top: -5px;
		  }
		  thead  tr{
			  background-color: #711;
			  color:white;
		  }
	  </style>
  </head>

  <body>
               <div id="title">材料上传</div>
				   <div>
				                      文件列表
					       <table>
						      <tbody>
						      <thead>
						        <tr><td style="width: 60px">序号</td>
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
						             <td><s:a action="material_delete?id=%{id}">删除</s:a></td>
						          
									</tr>
								</s:iterator>
						     </tbody>
					       </table>
				    </div>
				    <div>
						   <form method="post" enctype="multipart/form-data" action="fileUpload.action" id="form">
							   <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							   <input type="file" name="file" value="选择文件" id="file" onchange="getFileSize(this)"> 　　　　 保存文件名：<input type="text" name="name">
						   <br><br>　　　　　　　　　　　　　　　　　　　　　　　　　　　　（注意在线视频观看只支持.flv和.mp4两种格式）
						
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
</html>
