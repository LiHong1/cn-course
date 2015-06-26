<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>导入课程与学生</title>
	  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style/css/top.css">
	  <%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
	  <style type="text/css">
		  #notice{height: 30px;margin-bottom: 30px;}
		  #head { color:#FFFFCC;font-weight:bold;text-align: center;height: 20px;width: 504px;border-top:1px solid #CC9966;  }
		  #head .clazz,#head .course,#head .operate{background-color:#990000;border-right:1px solid #CC9966;  }
		  .clazz{float: left;width: 200px;height: 20px;border-right:1px solid #CC9966;}
		  .course{float: left;width: 200px;height: 20px;border-right:1px solid #CC9966;}
		  .operate{float: left;width: 100px;height: 20px;border-right:1px solid #CC9966;}
		  .item{clear: both;height: 20px;width: 504px;text-align: center;border-left:1px solid #CC9966;border-bottom:1px solid #CC9966; }
		  .loading { width: 500px;  position: absolute;left: 0px;display: none;
			  top: 200px;z-index: 100;opacity: 0.6;  }
		  .loading p { text-align: center;  }
		   p {  margin: 0;  }
		  #content{
			  position: relative;
		  }
	  </style>
	  <script type="text/javascript">

		  $(function(){
			  $(".item").on("click","a",function(event){
				  var parent = $(this).parent().parent();
				  var dates = parent.children();
				  var courseName = $(dates[0]).html();
				  var className = $(dates[1]).html();
				  var url = $(dates[2]).find("input").val();
				  url = url.replace(/'/g,"");
				  $(".loading").show();
				  $.get("importStudent",{courseName:courseName,className:className,url:url},function(date){
					  alert(date.msg);
					  $(".loading").hide();
				  },"json");
				  //window.location.href="${pageContext.request.contextPath}/teacher/importStudent?class";
				  event.preventDefault();
			  });
		  })

	  </script>
  </head>
  <body>
  <%--<div id="top_title"><c:if test="${role != 'manage'}"></c:if>&nbsp<c:if test="${role=='manage'}">上机系统管理</c:if></div>
  <div id="container">
		  <div id="top">
			  <jsp:include page="../public/top.jsp"></jsp:include>
		  </div>
		  <div id="left"><jsp:include page="menu.jsp"></jsp:include></div>
		  <div id="right">--%>
				  <div class="loading">
					  <p><img src="${pageContext.request.contextPath}/resources/style/images/import/img-loading.gif" alt="正在导入中" /></p>
					  <p>正在导入中......</p>
				  </div>
				  <div id="title">导入课程与学生</div>
				  <div id="notice">请选择您要导入的班级</div>
				  <div>
					  <div id="head"><div class="course">课程</div><div class="clazz">班级</div><div class="operate">操作</div></div>
					  <div id="data">
						  <c:forEach items="${informations}" varStatus="state" var="information">
							  <div class="item"><div class="course">${information.courseName}</div><div class="clazz">${information.className}</div> <div class="operate"><input type="hidden" value="${information.url}"><a href="#">导入数据</a></div></div>
						  </c:forEach>
					  </div>
				  </div>
				  <div>${error}</div>
<%--		  </div>
	  </div>--%>

  </body>
</html>
