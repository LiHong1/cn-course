<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
      <title>增加案例</title>
     <link rel="stylesheet"
            href="${pageContext.request.contextPath }/resources/ueditor1_2_6_1-utf8-jsp/themes/default/css/ueditor.css" />
     <script type="text/javascript"
            src="${pageContext.request.contextPath }/resources/ueditor1_2_6_1-utf8-jsp/ueditor.config.js"></script>
     <script type="text/javascript"
            src="${pageContext.request.contextPath }/resources/ueditor1_2_6_1-utf8-jsp/ueditor.all.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath }/resources/ueditor1_2_6_1-utf8-jsp/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript">
$(function(){
 window.UEDITOR_HOME_URL ="${pageContext.request.contextPath }/resources/ueditor1_2_6_1-utf8-jsp/"
     window.UEDITOR_CONFIG.autoHeightEnabled=false;
     window.UEDITOR_CONFIG.initialFrameHeight=250;
     window.UEDITOR_CONFIG.initialFrameWidth=700;
     UE.getEditor('case_content');
 $("#add").click(function(){
        $("#form").submit();
  });
});

    </script>
</head>
  <body>
			   <div id="title">新增案例</div>
			   <sf:form id="form" method="post" modelAttribute="case">
			    <sf:hidden path="id" ></sf:hidden>
			   <div>标题<div><input  name="title" value="${case.title}" style="width: 300px"/></div></div>
			   <div>内容<div><textarea  name="content" id="case_content" value="${case.content}"></textarea></div></div>
			   </sf:form>
			   <div style="position:relative">
			      <div class="u" id="add">
			        <img class="u_img" src="${pageContext.request.contextPath}/resources/style/images/button_u15.png"/>
			       
			        <div class="u1" class="text">
			          <p><span>保存</span></p>
			        </div>
			      </div>
			   </div>
  </body>
</html>
