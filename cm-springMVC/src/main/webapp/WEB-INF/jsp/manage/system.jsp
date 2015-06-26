<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style/css/switch.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/switch.js" ></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/Calendar5.js" ></script>
      <script type="text/javascript">
          $(function(){
              var switch1 = $("#switch1").switch({
                  initValueId:"#loginAble"
              });
              $("#switch2").switch({
                  initValueId:"#replaceMachineAble"
              });
              $("#save").click(function(){
                  $("#form").submit();
              });
          });
          var c = new Calendar("c");
          document.write(c);
      </script>
      <style>
          span{
              margin-top:5px;
              display: block;
          }
          #u2{
              position:absolute;
              font-family:'Helvetica Regular', 'Helvetica';
              font-weight:400;
              font-style:normal;
              font-size:14px;
              color:#AE5300;
              cursor: pointer;
          }
          #u2_img{
              position:absolute;
              left:0px;
              top:0px;
              width:101px;
              height:63px;
          }
          #u3 {
              position:absolute;
              left:2px;
              top:4px;
              width:101px;
              height:63px;
              word-wrap:break-word;
          }
          #u3 p{
              margin-top:10px;
              padding:0px;
          }
          #u3 span{
              text-align:center;
              display: block;
          }
      </style>
</head>
  <body>

					   <div id="title">系统设置</div>
					   <div  class="h">
					    <div style="float: left;margin-right: 10px">1.系统登录　　　　</div>
					     <div style="float: left;">
					         <div class="switch" id="switch1"></div>
					    </div>
					   </div>
					   <div class="h">
					    <div style="float: left;margin-right: 10px">2.允许学生换机器　</div>
					     <div style="float: left;">
					        <div id="switch2" class="switch"></div>
					    </div>
					   </div>
					   <sf:form id="form"  commandName="system">
                           <sf:hidden path="loginAble" id="loginAble" value="${system.loginAble}"></sf:hidden>
                           <sf:hidden path="replaceMachineAble" id="replaceMachineAble" value="${system.replaceMachineAble}"></sf:hidden>
					   <div class="h">
					     <div>3.学生作业区大小　　<sf:input value="${jobArea}"  path="jobArea" id="jobArea_size" cssStyle="width:80px"></sf:input>MB</div>
					   </div>
                       <div class="h">
                           <div>4.上传文件路径 　　&nbsp <sf:input value="${fileRoot}"  path="fileRoot"  cssStyle="width:80px"></sf:input><span style="color:red;display: inline;margin-left:30px">${fileRoot}</span></div>
                       </div>
					   <div  style="margin-left: 50px;margin-bottom:20px; height: 30px">
					     <div style="float: left;margin-right:30px ">5.学期开始日期 </div>
					     <div style="float: left;">
					        Date:<br>
					       <sf:input  id="startTermTime" path="startTermTime" cssStyle="width: 100px" onclick="c.showMoreDay = false;c.show(this);" value="${startTermTime}"></sf:input>
					     </div>
					   </div>
					   </sf:form>
					   <div class="h"  id="save">
					    <div id="u2">
					        <img id="u2_img" src="${pageContext.request.contextPath}/resources/style/images/manage/button_u41.png"/>
					       
					        <div id="u3" class="text">
					          <p><span>保存</span></p>
					        </div>
					      </div>
					   </div>
  </body>
</html>