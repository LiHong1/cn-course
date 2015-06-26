
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
     <title>学生列表</title>
     <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/css/forum.css" />
	  <script type="text/javascript">

		  $(function(){

			  $("#delete").click(function(){
				  $("#deleteForm").submit();
			  });
			  $(".delete").click(function(e){
				  message="你确定删除?";
				  return confirm(message);
			  });

		  });
	  </script>
	  <style>
		  table{
			  text-align: center;
			  border-spacing: 1px;
			  border-spadding:1px;
			  border:0px;
			  background-color: black;
		  }
		  tr{
			  background-color: white;
			  height: 30px;
		  }
	  </style>
  </head>
  <body>

					   <div id="title">学生列表</div>
					   <div>
					     <table width="700" >
					        <tr>
					            <td width="80">学号</td>
					            <td width="400">姓名</td>
					            <td width="50"></td>
					        </tr>
					        
					        <c:forEach items="${page.recordList}" varStatus="status" var="student">
								<tr>
									<td>${student.number}</td>
									<td>${student.name}</td>
									<td><a href="student/delete/${student.id}" class="delete">删除</a></td>
								</tr>
							</c:forEach>
					       </table>
					   </div>
					       <!--分页信息-->
							<%@ include file="/WEB-INF/jsp/public/pageView.jspf" %>
							<sf:form method="get" id="form"></sf:form>
							<sf:form action="student/delete" id="deleteForm">按学号模糊删除：<input type="text" name="number"/></sf:form>
					       <div style="position:relative">
					       <!-- button (形状) --> 
					      <div class="u" id="delete">
					        <img class="u_img" src="${pageContext.request.contextPath}/resources/style/images/button_u15.png"/>
					        <div class="u1" class="text">
					          <p><span>删除</span></p>
					        </div>
					      </div>
					       </div>
  </body>
</html>
