<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
     <title>学生列表</title>
     <script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/resources/script/jquery.changeColor.js"></script>
   <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/css/forum.css" />
      <script type="text/javascript">

          $(document).ready(function(){
              $("#delete").click(function(){
                  message="你确定删除？";
                  return confirm(message);
              });
              $("#resetPassword").click(function(){
                  message="你确定重置口令？";
                  return confirm(message);
              });
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
			   <div id="title">学生列表</div>
			         <table>
			         <thead>
			        <tr>
			            <td width="40px">序号</td>
			            <td width="150px">学号</td>
			            <td width="150">姓名</td>
			            <td width="250px">登录时间</td>
			            <td width="450px">操作</td>
			        </tr>
			        </thead>
			        <tbody>
			      <c:forEach items="${page.recordList}" var="application" varStatus="status">
						<tr>
                      <td>${status.count}</td>
                      <td>${application.student.number}</td>
                      <td>${application.student.name}</td>
                      <td><fmt:formatDate  value="${application.latestLoginDate}" type="both"/></td>
                      <td><a href="${pageContext.request.contextPath}/teacher/paper/show/${application.id}">查看答卷</a>
                          <a href="${pageContext.request.contextPath}/teacher/student/resetPassword/${application.student.id}" id="resetPassword">重置口令</a>
                          <a href="${pageContext.request.contextPath}/teacher/student/show/${application.student.id}">详情</a>
                          <a href="${pageContext.request.contextPath}/teacher/student/reExam/${application.id}">重考</a>
                          <a href="${pageContext.request.contextPath}/teacher/student/delete/${application.student.id}" class="delete" id="delete">删除</a></td>
                  </tr>
                  </c:forEach>
					</tbody>
			       </table>
			        <!--分页信息-->
			        <%@ include file="/WEB-INF/jsp/public/pageView.jspf" %>
			        <form action="students" id="form" ></form>
  </body>
</html>
