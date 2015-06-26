<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>出勤管理</title>
	  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style/css/top.css">
	  <%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
	  <style type="text/css">
		  .order,.time,.record{
               float: left;
			   width: 120px;
			   height: 30px;
			   line-height: 30px;
			   border-left: 1px solid white;
			   border-bottom: 1px solid white;
		  }
		  .time{width: 200px;  }

		  #head div{background:#711;}
		  #head,.item{
			  position: relative;
			  text-align: center;
			  width: 450px;
			  color:white;
		  }
		  .item{color:black;}
		  #data,.item{clear: both}
		  #data,#head{
			  margin-left: 100px;}
	  </style>
	  <script type="text/javascript">
		$(function(){
			$(".add").click(function(){
				window.location.href="${pageContext.request.contextPath}/teacher/addStAttendance";
			});
		});

	  </script>
  </head>
  <body>


		  <div id="title">出勤管理</div>
		  <div>
			  <div id="head"><div class="order">序号</div><div class="time">时间</div><div class="record">记录</div></div>
			  <div id="data">
				  <c:forEach items="${stAttendanceItems}" varStatus="state" var="stAttendanceItem">
					  <div class="item"><div class="order">${state.count}</div>
						               <div class="time"><fmt:formatDate value="${stAttendanceItem.createTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></div>
						               <div class="record"><c:if test="${stAttendanceItem.type eq null}">到课</c:if>${stAttendanceItem.type.name}</div>
									   </div>
				  </c:forEach>
			  </div>
          </div>


  </body>
</html>
