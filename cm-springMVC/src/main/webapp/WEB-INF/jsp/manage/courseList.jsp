<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
      <title>课程列表</title>
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
                      name:"required"
                  },
                  messages: {
                      name:"请输入课程名"
                  }
              });
          });
      </script>
      <style>
          table{
              text-align: center;
              border-spacing: 1px;
              border:0px;
              background-color: #555;
              color:#333;
              font-size: 14px;
          }
          tr{
              background-color: white;
              height: 30px;
          }
          table thead  tr{
              background-color: #711;
              color:white;
          }
          tr.odd {
              background: #ccc;
          }
          #addCourse{
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
      <div id="title">课程列表</div>
           <div>
             <table width="700" >
                <thead>
                    <tr>
                        <td width="150">序号</td>
                        <td width="550">课程名</td>
                    </tr>
                </thead>
                <tbody>
                <c:forEach items="${page.recordList}" varStatus="status" var="course">
                    <tr>
                        <td>${status.count}</td>
                        <td>${course.name}</td>
                    </tr>
                </c:forEach>
                </tbody>
               </table>
           </div>

      <!--分页信息-->
      <%@ include file="/WEB-INF/jsp/public/pageView.jspf" %>
      <sf:form method="get" id="form"></sf:form>

           <form action="course/add" id="add-form" method="post">
               <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
           <div id="addCourse">
           新增课程<br>
          课程名：&nbsp <input id="courseName" type="text" style="width:250px " name="name"/>
           </div>
             <div style="position:relative">
                 <!-- button (形状) -->
                  <div id="add" class="u">
                    <img class="u_img" src="${pageContext.request.contextPath}/resources/style/images/button_u15.png"/>
                    <div class="u1" class="text">
                      <p><span>新增</span></p>
                    </div>
                  </div>
              </div>
           </form>
</body>
</html>
