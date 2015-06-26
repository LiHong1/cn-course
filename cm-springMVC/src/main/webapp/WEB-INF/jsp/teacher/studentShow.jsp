<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>学生信息</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style/css/tab.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/css/forum.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/tab.js"></script>
	
    <script type="text/javascript">
    $(document).ready(function(){
			 var i=0;
	  $("#save").click(function(){
	      $("#form").submit();
	  });
	  $("#h1").change(function(){
		  $("#tabfirst li").each(function(index){
			  alert($("#h1").val());
			  if($(this).hasClass("tabclick"))
				  window.location.href="${pageContext.request.contextPath}/student/show/"+$("#h1").val()+"?option="+index;
		  });
		  });

	  $("#tabfirst li").each(function(index){
		  $(this).removeClass("tabclick");
          $("#area > div").eq(index).removeClass("contentin");
		   if(${option} == index)
		   {
			    $("#tabfirst li").eq(index).addClass("tabclick");
		        $("#area > div").eq(index).addClass("contentin");
		   }
	  });
	 $("#nextStudent").click(function(){
             var id=$("#h1").val();
             var size=$("#h1 > option").length;
             window.location.href="${pageContext.request.contextPath}/student/show/"+id+"?nextStudent=true&option="+i;
        
	 });
	 $("#delete").click(function(){
         message="你确定删除？";
         return confirm(message);
     });

	});
		
    </script>
	  <style>
		  #area{
			  margin-top:30px;
			  margin-bottom: 40px;
		  }
		  table{
			  text-align: center;
			  border-spacing: 1px;
			  border:0px;
			  background-color: black;
		  }

		  tr{
			  background-color: white;
			  height: 30px;
		  }
		  .b1{
			  margin-top:10px;
			  margin-bottom:20px;
		  }
		  a{
			  text-decoration: none;
			  color:#000;
		  }
	  </style>
  </head>

     
  <body>
               <div id="title">学生详情</div>
				     <div>学号与姓名    <sf:select id="h1" path="student" items="${studentList}"  itemLabel="name" itemValue="id">

					                  </sf:select>
				     <span id="nextStudent" style="cursor: pointer">下一个</span></div>
				     <ul id="tabfirst">
				     <li class="tabclick"><span>学生信息</span></li>
				     <li><span>作业</span></li>
				     <li><span>登录历史</span></li>
				     </ul>
				      <div id="area">
				   <div class="contentin contentfirst">
				      <div class="b1">学号： <span id="number">${student.number}</span></div>
				      <div class="b1">姓名：<span id="name">${student.name }</span></div>
                      <div class="b1">上次登录时间：<span id="loginTime"><fmt:formatDate  value="${application.latestLoginDate }"  type="both"/>  </span></div>
				      <div class="b1">加入班级时间：<span id="joinTime"><fmt:formatDate value="${application.joinClassDate}"  type="both"/> </span></div>
				    <!-- button (形状) -->
				      <div style="position:relative">
				      <div class="u" id="save">
				        <img class="u_img" src="${pageContext.request.contextPath}/resources/style/images/button_u15.png"/>
				       
				        <div class="u1" class="text">
				          <p><span>重置登录口令</span></p>
				        </div>
				      </div>
				      </div>
				   </div>
				   <div class="contentfirst">
				     <table>
				       <tr>
				            <td width="40px">序号</td>
				            <td width="450px">作业</td>
				            <td width="150px"></td>
				        </tr>
				        <c:forEach items="${jobs}" var="job" varStatus="status">
				        <tr>
				            <td>${status.count}</td>
				            <td>${job.jobName}</td>
				            <td><a href="${pageContext.request.contextPath}/teacher/student/job/download/${job.id}">下载</a></td>
				        </tr>
				        
				        </c:forEach>
				     </table>
				   
				   </div>
				   <div class="contentfirst"> 
				    共计登录${application.timeCount}
				    <table>
				       <tr>
				            <td width="40px">序号</td>
				            <td width="350px">登录时间</td>
				            <td width="250px">退出时间</td>
				        </tr>
				   <c:forEach items="${page.recordList}" var="timeLogging" varStatus="status">
				        <tr>
				            <td>${status.count}</td>
				           <td><fmt:formatDate value="${timeLogging.loginDate}" type="both"/></td>
				            <td><fmt:formatDate value="${timeLogging.logoutDate}" type="both"/></td>
				        </tr>
				        
				        </c:forEach>
				   </table>
				        <!--分页信息-->
				        <%@ include file="/WEB-INF/jsp/public/pageView.jspf" %>
				        <sf:form action="${id}" id="form" method="get">
				         <input type="hidden" value="2" name="option"></input>
				        </sf:form>
				   </div>
				    </div>
  </body>
</html>
