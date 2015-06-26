<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>出勤管理</title>
	  <style type="text/css">
		  .order,.time,.miss,.late,.leave,.unKnow,.operate{
               float: left;
			   width: 70px;
			   height: 30px;
			   line-height: 30px;
			   border-left: 1px solid white;
			   border-bottom: 1px solid white;
		  }
		  .time,.operate{width: 160px;  }

		  #head div{background:#711;}
		  #head,.item{
			  position: relative;
			  text-align: center;
			  width: 700px;
			  color:white;
		  }
		  .item{color:black;}
		  #data,.item{clear: both}
		  span img{cursor: pointer;width: 20px; height: 20px;}
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
			  <div id="head"><div class="order">序号</div><div class="time">时间</div><div class="unKnow">旷课(人)</div><div class="miss">请假(人)</div><div class="late">迟到(人)</div><div class="leave">早退(人)</div><div class="operate">操作</div></div>
			  <div id="data">
				  <c:forEach items="${stAttendances}" varStatus="state" var="stAttendance">
					  <div class="item"><div class="order">${state.count}</div>
						               <div class="time"><fmt:formatDate value="${stAttendance.createTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></div>
						               <div class="unKnow">${stAttendance.unKnowCount}</div>
						               <div class="miss">${stAttendance.missCount}</div>
						               <div class="late">${stAttendance.lateCount}</div>
						               <div class="leave">${stAttendance.leaveCount}</div>
						               <div class="operate"><a href="${pageContext.request.contextPath}/teacher/stAttendance/delete/${stAttendance.id}">删除</a>
										                   <a href="${pageContext.request.contextPath}/teacher/stAttendance/update/${stAttendance.id}">更新</a>
									   </div></div>
				  </c:forEach>
			  </div>
			  <div style="float: right;margin-right: 80px"><span><img src="${pageContext.request.contextPath}/resources/style/images/menu/add.png" alt="增加" title="增加" class="add"></span></div>
		  </div>



  </body>
</html>
