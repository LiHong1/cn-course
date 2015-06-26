<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
     <%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
     <link rel="stylesheet"
            href="${pageContext.request.contextPath }/resources/ueditor1_2_6_1-utf8-jsp/themes/default/css/ueditor.css" />
     <script type="text/javascript"
            src="${pageContext.request.contextPath }/resources/ueditor1_2_6_1-utf8-jsp/ueditor.config.js"></script>
     <script type="text/javascript"
            src="${pageContext.request.contextPath }/resources/ueditor1_2_6_1-utf8-jsp/ueditor.all.min.js"></script>
     <script type="text/javascript"
            src="${pageContext.request.contextPath }/resources/ueditor1_2_6_1-utf8-jsp/lang/zh-cn/zh-cn.js"></script>
     <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style/css/tab.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/tab.js"></script>
    <script type="text/javascript">
	   $(document).ready(function(){
         window.UEDITOR_HOME_URL ="${pageContext.request.contextPath }/ueditor1_2_6_1-utf8-jsp/";
         window.UEDITOR_CONFIG.initialFrameHeight=250;
         window.UEDITOR_CONFIG.initialFrameWidth=700;
         window.UEDITOR_CONFIG.autoHeightEnabled=false;
         UE.getEditor('information');
         UE.getEditor('schedule');
         UE.getEditor('material');
         $("#save").click(function(){
            $("#form").submit();
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
      </style>
  </head>

  <body>
  	  <div id="title">课程设置</div>
				     <ul id="tab">
				     <li class="tabclick"><span>课程介绍</span></li>
				     <li><span>进度安排</span></li>
				     <li><span>学习材料</span></li>
				     </ul>
				   <div style="float:right"><a href="materials">上传材料</a></div>
				     <sf:form action="courseSet" id="form" method="post" modelAttribute="course">
				      <div id="area">
				     <input type="hidden" value="${course.id}" name="id"></inputhidden>
				   <div class="contentin contentfirst"><sf:textarea path="information" id="information" value="${course.information}"></sf:textarea></div>
				   <div class="contentfirst"><sf:textarea path="schedule" id="schedule" value="${course.schedule}"></sf:textarea></div>
				   <div class="contentfirst"><textarea  name="material" id="material"></textarea></div>
				    </div>
				     </sf:form>
				     <div style="position: relative;">
				    <!-- button (形状) -->
				      <div class="u" id="save">
				        <img class="u_img" src="${pageContext.request.contextPath}/resources/style/images/button_u15.png"/>
				       
				        <div class="u1" class="text">
				          <p><span>保存</span></p>
				        </div>
				      </div>
				    </div>
  </body>
</html>
