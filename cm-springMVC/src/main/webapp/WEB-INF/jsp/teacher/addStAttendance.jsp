<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>出勤</title>
	  <%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
	  <script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
	  <script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/dwrService.js"></script>
	  <style type="text/css">
		  .number,.name,.miss,.late,.leave,.unKnow{
               float: left;
			   width: 80px;
			   height: 30px;
			   line-height: 30px;
			   border-left: 1px solid white;
			   border-bottom: 1px solid white;
			   background: #eee;
			   text-align: center;
		  }
		  .number{
			  width: 100px;
		  }
		  .item .miss,.item .late,.item .leave,.item .unKnow{
			  cursor:pointer;
		  }
		  #head div{
			  background:#711;
		  }
		  #head{
			  position: relative;
			  text-align: center;
			  width: 630px;
			  color:white;
		  }
		  .item img{
			  width:20px;
			  heigth:20px;
			  padding-top: 5px;
		  }
		  span img{
			  cursor: pointer;
			  width: 20px;
			  height: 20px;
		  }
		  #data,.item{
			  clear: both;
		  }
		  #head,#data{margin-left: 100px}
	  </style>
	  <script type="text/javascript">
		$(function(){
			$(".item").each(function(){

				$(this).children("div").each(function(index){
					if(index>1){
						$(this).click(function(){
							var stAttendanceItemId = $(this).parent().children("input").val();
							var img = $(this).find("img");
							$(this).parent().children("div").each(function(index){
								if(index>1){
									$(this).html("");
								}
							})
							if(img.length == 0){
								$(this).append("<img src='${pageContext.request.contextPath}/resources/style/images/check/check_alt.png'>")
								//dwrService.addToExam(stAttendanceItemId);
								dwrService.updateStAttendanceItem(stAttendanceItemId,(index-2));
							}else{
								dwrService.updateStAttendanceItem(stAttendanceItemId);
							}

						})
					}
				})
			});
		});

	  </script>
  </head>
  <body>


		  <div id="title">考勤登记</div>
		  <div>
			  <div id="head"><div class="number">学号</div><div class="name">姓名</div><div class="late">迟到</div><div class="leave">早退</div><div class="miss">请假</div><div class="unKnow">旷课</div></div>
			  <div id="data">
				  <c:forEach items="${stAttendanceItems}" varStatus="state" var="stAttendanceItem">
					  <div class="item">
						  <input type="hidden" value="${stAttendanceItem.id}">
						  <div class="number">${stAttendanceItem.student.number}</div>
						  <div class="name">${stAttendanceItem.student.name}</div>
						  <div class="late"><c:if test="${stAttendanceItem.type =='LATE'}"><img src="${pageContext.request.contextPath}/resources/style/images/check/check_alt.png"></c:if></div>
						  <div class="leave"><c:if test="${stAttendanceItem.type =='LEAVE'}"><img src="${pageContext.request.contextPath}/resources/style/images/check/check_alt.png"></c:if></div>
						  <div class="miss"><c:if test="${stAttendanceItem.type =='MISS'}"><img src="${pageContext.request.contextPath}/resources/style/images/check/check_alt.png"></c:if></div>
						  <div class="unKnow"><c:if test="${stAttendanceItem.type == 'UNKNOWN'}"><img src="${pageContext.request.contextPath}/resources/style/images/check/check_alt.png"></c:if></div>
					  </div>
				  </c:forEach>
			  </div>
		  </div>
  </body>
</html>
