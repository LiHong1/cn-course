<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
     <title>试题列表</title>
     <script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/resources/script/jquery.changeColor.js"></script>
     <script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
     <script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/dwrService.js"></script>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/css/table.css">
	  <script type="text/javascript">
		  $(function(){
			  $("#delete").click(function(){
				  message="你确定删除吗？";
				  return confirm(message);
			  });
			  $(".paper").click(function(){
				  alert($(this).val());
				  dwrService.addToExam($(this).val());
				  //$("#form").submit();
			  });
			  $("tbody tr:odd").changeColor({oddColor:"#ccc"});
		  });
	  </script>
  </head>

  <body>
		   <div id="title">试题列表</div>
		        <div style="float:right;margin:10px;margin-right:20px;"><a href="paper/add">新增试题</a></div>
		         <table>
		         <thead>
		        <tr>
		            <td width="40px">加入考试</td>
		            <td width="40px">序号</td>
		            <td width="450px">试题</td>
		            <td width="150px">操作</td>
		        </tr>
		        </thead>
		        <tbody>
		      <c:forEach items="${papers}" varStatus="status" var="paper">
					<tr>
					<td><input type="checkbox"  class="paper" <c:if test='${paper.type}'>checked='checked'</c:if> value="${paper.id}" ></input></td>
		   			<td>${status.count}</td>
		            <td>${paper.name}</td>
		            
		            <td>
		            <a href="paper/update/${paper.id}">编辑</a>
		            <a href="paper/delete/${paper.id}" cssClass="delete" id="delete">删除</a></td>
		  			</tr>
				</c:forEach>
				</tbody>
		       </table>
  </body>
</html>
