<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>材料列表</title>
	<script language="javascript" src="${pageContext.request.contextPath}/resources/fckeditor/fckeditor.js" charset="utf-8"></script>
    <script type="text/javascript">
		
 $(document).ready(function(){
       $("#upload").click(function(){
          $("#form").submit();
	  });
});
    </script>
      <style>
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
          thead  tr{
              background-color: #711;
              color:white;
          }
          tr{
              height: 30px;
          }
      </style>
  </head>

  <body>
           <div id="title">材料上传</div>
           <div>
                       文件列表
               <table>
                <thead>
                <tr><td style="width: 60px">序号</td>
                    <td style="width: 300px">原始文件名</td>
                    <td style="width: 200px">保存文件名</td>
                     <td style="width: 100px">操作</td>
                </tr>
                </thead>
                 <c:forEach items="${materialList}" varStatus="status" var="material">
                    <tr>
                    <td>${status.count}</td>
                    <td>${material.originalName}</td>
                    <td>${material.name}</td>
                     <td><a href="material/delete/${material.id}">删除</a></td>
                    </tr>
                </c:forEach>
               </table>
           </div>

            <div>
           <form method="post" enctype="multipart/form-data" action="course/material/add?_csrf=${_csrf.token}" id="form">
               <input type="file" name="file" value="选择文件"><input type="text" name="name">
           </form>


            </div>
             <div style="position:relative">
              <div class="u" id="upload">
                <img class="u_img" src="${pageContext.request.contextPath}/resources/style/images/button_u15.png"/>
                <div class="u1" class="text">
                  <p><span>上传文件</span></p>
                </div>
              </div>
             </div>
  </body>
</html>
