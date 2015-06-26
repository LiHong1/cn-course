<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
       <title>案例列表</title>
    	<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/resources/script/jquery.changeColor.js"></script>
      <script type="text/javascript">
          $(document).ready(function(){
              $("tbody tr:odd").changeColor({oddColor:"#ccc"});
          });
      </script>
      <style>
          table{
              text-align: center;
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

			   <div id="title">案例列表</div>
			   <div style="float:right;margin-bottom: 20px"><a href="case/add">新增案例</a></div>
			   <table>
			       <thead>
			        <tr><td style="width: 60px">序号</td>
			            <td style="width: 500px">案例</td>
			            <td style="width: 130px">操作</td>
			        </tr>
					<thead>
					 <tbody>
			         <c:forEach items="${caseList}" varStatus="status" var="case">
						<tr>
			   			<td>${status.count}</td>
			            <td>${case.title}</td>
			            <td><a href="case/update/${case.id}">编辑</a>
			            <a href="case/delete/${case.id}">删除</a></td>
			          
						</tr>
					</c:forEach>
			     </tbody>
			   </table>
  </body>
</html>
