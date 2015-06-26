<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
      <title>教师管理</title>
      <script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/resources/script/jquery.changeColor.js"></script>
      <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/css/forum.css" />
      <script type="text/javascript">
          $(document).ready(function(){
              $("#add").click(function(){
                  $("#add-form").submit();
              });
              $("tbody tr:odd").changeColor({oddColor:"#ccc"});
              $("#add-form").validate({
                  rules: {
                      name:"required",
                      number:"required"
                  },
                  messages: {
                      name:"请输入教师姓名",
                      number:"请输入教师工号"
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
              background-color:#900;
          }
          #addTeacher{
              margin-top:40px;
              margin-bottom: 20px;
          }
          #form label.error
          {
              color:red;
              font-size:15px;
              margin-left:5px;
              padding-left:16px;
          }
      </style>
  </head>

  <body>
					   <div id="title">教师列表</div>
					   <div>
					     <table width="700" >
					        <thead>
					        <tr>
					            <td width="80">序号</td>
					            <td width="310">教师</td>
					            <td width="310">工号（登录用）</td>
					        </tr>
					        </thead>
					        <tbody>
					        <c:forEach items="${page.recordList}" varStatus="status" var="teacher">
					            <tr>
					                <td>${status.count}</td>
					                <td>${teacher.name}</td>
					                <td>${teacher.number}</td>
					            </tr>
					        </c:forEach>
					        </tbody>
					       </table>
					   </div>

                       <!--分页信息-->
                       <%@ include file="/WEB-INF/jsp/public/pageView.jspf" %>
                       <sf:form method="get" id="form"></sf:form>

					   <sf:form action="teacher/add" id="add-form">
					   <div id="addTeacher">
					   新增教师<br>
					  教师名：&nbsp <input id="name" name="name" type="text" style="width:250px " /> <br>
					  工　号：&nbsp <input id="number" name="number" type="text" style="width:250px " /> 
					   </div>
					   <div style="position:relative">
					     <!-- button (形状) -->
					      <div class="u" id="add">
					        <img class="u_img" src="${pageContext.request.contextPath}/resources/style/images/button_u15.png"/>
					       
					        <div class="u1" class="text">
					          <p><span>新增</span></p>
					        </div>
					      </div>
					      </div>
					   </sf:form>
  </body>
</html>
