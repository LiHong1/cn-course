<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	  <title>班级管理</title>
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/style/css/tab.css">
      <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/resources/style/css/forum.css" />
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/style/css/switch.css">
    <script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/tab.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/switch.js"></script>
	<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/resources/script/jquery.changeColor.js"></script>
    <script type="text/javascript">
		 $(function(){
			 $("#switch1").switch({
				 initValueId:"#joinAble"
			 });
			 $("#switch2").switch({
				 initValueId:"#loginAble"
			 });
		 
			 $("#save").click(function(){
				 $("#save_form").submit();
			  });
	  
			 $("#download").click(function(){
			   window.location.href="students/jobs/download";
			  });
	  
			 $("#type").change(function(){
				 window.location.href="classSet?type="+document.getElementById("type").value+"&option=1";
			 });
	    if(${option!=null}){
	    	if(${type==null}){
	    		$("#type option").eq(0).attr("selected",true);
	    	}else{
	    		$("#type option").eq(${type+1}).attr("selected",true);
	    	}
            $("#tab li").eq(0).removeClass("tabclick");
            $("#tab li").eq(1).addClass("tabclick");
            $("#area > div").eq(0).removeClass("contentin");
            $("#area > div").eq(1).addClass("contentin");
         }
	    $("tbody tr:odd").changeColor({oddColor:"#ccc"});
	     });
		
	
    </script>
	  <style>
		  #area{
			  margin-top:50px;
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
		  thead  tr{
			  background-color: #711;
			  color:white;
		  }
		  #download span{
			  margin-top: 8px;
		  }
		  .h1{
			  height:60px;
		  }
	  </style>
  </head>

  <body>
		 <div id="title">班级管理</div>
				    
				     <ul id="tab">
				     <li class="tabclick"><span>班级设置 </span></li>
				     <li><span>申请加入</span></li>
				     </ul>

				   <div id="area">
				       <input type="hidden" value="${teacher.cuState.id}" name="id"/>
					   <form id="save_form" method="post">
						   <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					   <input type="hidden" value="${teacher.cuState.joinAble}" name="joinAble" id="joinAble"/>
					   <input type="hidden" value="${teacher.cuState.loginAble}" name="loginAble" id="loginAble"/>
					   </form>
					   <div class="contentin contentfirst">
						   <div  class="h1">
							<div style="float: left;margin-right: 45px">1.允许学生登录</div>
							 <div style="float: left;">
							<div class="switch" id="switch1"></div>
							</div>
						   </div>
						   <div class="h1">
							<div style="float: left;margin-right: 10px">2.允许申请加入班级&nbsp</div>
							 <div style="float: left;">
							<div id="switch2" class="switch"></div>
							</div>
						   </div>
				     
				       
				       <div style="float: left;width: 101px;height: 26px;position:relative">
					        <!-- button (形状) -->
					      <div class="u"  id="save" style="width: 101px;height: 26px">
					        <img class="u_img" src="${pageContext.request.contextPath }/resources/style/images/button_u15.png"/>
					        <div class="u1" class="text">
					          <p><span>保存</span></p>
					        </div>
					       </div>
				       </div>
				       <div style="float: left;width: 131px;height: 41px;position:relative;margin-left:30px">
				           <div class="u" id="download" >
				            <img class="u_img" src="${pageContext.request.contextPath }/resources/style/images/teacher/button_u93.png"/>
				            <div class="u1" class="text" style="float: left;width: 131px;height: 41px">
				              <p><span>下载所以学生作业</span></p>
				            </div>
				          </div>
				      </div>
				      
				     
				      
				   </div>
				   <div class="contentfirst">
				       
				       <select id="type" style="width:200px" name="type">
				          <option selected value="-1">所有</option>
				          <option value="0">等待审核</option>
				          <option value="1">已通过</option>
				          <option value="2">已拒绝</option>
				        </select>
				      <table id="table">
				       <thead>
				        <tr>
				            <td width="40px">序号</td>
				            <td width="130px">学号</td>
				            <td width="130">姓名</td>
				            <td width="200px">申请时间</td>
				            <td width="110px">状态</td>
				            <td width="80px">操作</td>
				        </tr>
				        </thead>
				        <tbody>
				         <c:forEach items="${page.recordList}" var="application" varStatus="status">
							<tr>
				   			<td>${status.count}</td>
				            <td>${application.student.number}</td>
				            <td>${application.student.name}</td>
				            <td><fmt:formatDate  value="${application.applicationDate}" type="both"/></td>
				            <td><c:if test="${application.type==0}">等待审核</c:if><c:if test="${application.type==1}"> 已通过</c:if> <c:if test="${application.type==2}">已拒绝</c:if></td>
				             <td><c:if test="${application.type==0}"><a href="${pageContext.request.contextPath }/teacher/student/application/getThrough/${application.id}?type=${application.type}&option=1">通过</a><a href="${pageContext.request.contextPath }/teacher/student/application/refuse/${application.id}?type=${application.type}&option=1">拒绝</a></c:if></td>

							</tr>
						</c:forEach>
						</tbody>
				     </table>
				         <!--分页信息-->
				        <%@ include file="/WEB-INF/jsp/public/pageView.jspf" %>
				        <form action="teacher_classManageUI.action" id="form">
				        <s:hidden value="%{#teacher.cuState.id}" name="id"></s:hidden>
						<s:hidden value="-1" name="type"></s:hidden>
						<s:hidden value="1" name="option"></s:hidden>
				        </form>
				   </div>
				    </div>
  </body>
</html>
