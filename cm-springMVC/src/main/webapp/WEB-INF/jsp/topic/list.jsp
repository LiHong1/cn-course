<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <title>讨论主题</title>
	  <script type="text/javascript">

		  $(document).ready(function(){
			  $("#add").click(function(){
				  window.location.href="topic/add";
			  });
		  });

	  </script>
	  <style>
		  table{
			  text-align: center;
			  border-spacing: 1px;
			  border-spadding:1px;
			  border:0px;
			  background-color: #999;
		  }
		  tr{
			  background-color: white;
			  height: 30px;
		  }
		  thead tr{
			  background-color:#33f;
			  color:white;
		  }
		  a{
			  text-decoration:none;
			  color:black;
		  }

	  </style>
  </head>
  <body>
			   <div id="title">讨论区</div>
			   <div>
			     <table width="700" >
			       <thead>
			        <tr>
			            <td width="80">序号</td>
			            <td width="400">标题</td>
			            <c:if test="${teacher}"><td width="50"></td></c:if>
			        </tr>
			       </thead>
			        <c:forEach items="${topics}" varStatus="status" var="topic">
						<tr>
							<td>${status.count}</td>
							<td><a href="topic/show/${topic.id}">${topic.title}</a></td>
							<c:if test="${teacher}"><td><a href="topic/delete/${topic.id}" class="delete">删除</a></td>	</c:if>
						</tr>
					</c:forEach>
			       </table>
			   </div>
			   <c:if test="${teacher}">
			    <div style="position: relative;">
			     <!-- button (形状) -->
			      <div class="u" id="add">
			        <img class="u_img" src="${pageContext.request.contextPath}/resources/style/images/button_u15.png"/>
			        <div class="u1" class="text">
			          <p><span>新增讨论题目</span></p>
			        </div>
			      </div>
			    </div>
			    </c:if>
  </body>
</html>
