<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
       <title>班级列表</title>
	  <script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/resources/script/jquery.changeColor.js"></script>
	  <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/css/forum.css" />
	  <script type="text/javascript">
		  $(document).ready(function(){
			  $("#find_button").click(function(){
				  $("#find").submit();
			  });
			  $("#add_button").click(function(){
				  $("#add").submit();

			  });

			  $(".close").click(function(e){
				  message="你确定关闭？";

			  });
			  $(".delete").click(function(e){
				  message="你确定删除？";
				  return confirm(message);
			  });
			  $("#add").validate({
				  rules: {
					  className:"required"
				  },
				  messages: {
					  className:"请输入班级名"
				  }
			  });
		  });
	  </script>
	  <style>
		  table{
			  text-align: center;
			  border-spacing: 1px;
			  border-spadding:1px;
			  border:0px;
			  background-color: black;
		  }
		  tr{
			  background-color: white;
			  height: 30px;
		  }
		  thead tr{
			  background-color: #711;
			  color:white;
		  }
		  #addCourse{
			  margin-top:40px;
			  margin-bottom: 20px;
		  }
		  input{
			  width:250px;
		  }
		  #add label.error
		  {
			  color:red;
			  font-size:15px;
			  margin-left:5px;
			  padding-left:16px;
		  }
	  </style>
  </head>
  <body>
				   <div id="title">班级列表</div>
					     <div>
							 <sf:form action="${pageContext.request.contextPath}/manage/state/find" id="find" style="float:left">
							              课程名：&nbsp <input id="find_courseName" name="courseName" type="text" /> </br>               
							              教　师：&nbsp <input id="find_teacherName" name="teacherName" type="text"  /> </br>             
							            班级名：&nbsp <input  id="find_className" name="className" type="text"/> 
							 </sf:form>
							  <div style="float: left;margin-top:50px;margin-left:100px;position:relative">
							      <div class="u" id="find_button">
							        <img class="u_img" src="${pageContext.request.contextPath}/resources/style/images/button_u15.png"/>
							        <div class="u1" class="text">
							          <p><span>查找</span></p>
							        </div>
							      </div>
							   </div>
						  </div>
						   <div style="clear:both">
						     <table width="700">
						       <thead>
						        <tr>
						            <td width="30">序号</td>
						            <td width="100">班级</td>
						            <td width="100">课程</td>
						            <td width="80">教师</td>          
						            <td width="100">操作</td>
						        </tr>
						        </thead>
						        <tbody>
						   <c:forEach items="${page.recordList}" varStatus="status" var="state">
									<tr>
						   			<td>${status.count}</td>
						            <td>${state.clas.name}</td>
						            <td>${state.course.name}</td>
						            <td>${state.teacher.name }</td>
						            <td><a href="state/delete/${state.id}"  class="delete">删除</a> <a href="state/off/${state.id}" class="close">关闭</a></td>
									</tr>
								</c:forEach>
								</tbody>
						       </table>
						   </div>

				   <!--分页信息-->
				   <%@ include file="/WEB-INF/jsp/public/pageView.jspf" %>
				   <sf:form method="get" id="form"></sf:form>

						  <sf:form action="${pageContext.request.contextPath}/manage/state/add" id="add" commandName="courseName">
							   <div id="addCourse">
								   新增班级<br>
								  <p>课程名：&nbsp
									  <sf:select path="${courseId}" name="courseId" id="add_courseName" cssStyle="width:153px">
										  <option value="0">请选择课程</option>
										  <sf:options items="${courses}" itemValue="id" itemLabel="name"></sf:options>
									  </sf:select>


								  </p>
								  <p>教　师：&nbsp
									  <sf:select path="${teacherId}" name="teacherId" id="add_courseName" cssStyle="width:153px">
										  <option value="0">请选择班级</option>
										  <sf:options items="${teachers}" id="add_teacherName"  itemValue="id" itemLabel="name" ></sf:options>
									  </sf:select>
								  </p>
								 <p> 班级名：&nbsp <input id="add_className" name="className" type="text"/> </p>
							   </div>
						     <div style="position:relative">
							     <!-- button (形状) -->
							      <div class="u" id="add_button">
							        <img class="u_img" src="${pageContext.request.contextPath}/resources/style/images/button_u15.png"/>
							        <div class="u1" class="text">
							          <p><span>新增</span></p>
							        </div>
							      </div>
						      </div>
						</sf:form>

  </body>
</html>