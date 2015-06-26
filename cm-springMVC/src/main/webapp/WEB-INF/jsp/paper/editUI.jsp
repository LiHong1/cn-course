<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	  <title>修改试题</title>
     <link rel="stylesheet"
            href="${pageContext.request.contextPath }/resources/ueditor1_2_6_1-utf8-jsp/themes/default/css/ueditor.css" />
     <script type="text/javascript"
            src="${pageContext.request.contextPath }/resources/ueditor1_2_6_1-utf8-jsp/ueditor.config.js"></script>
     <script type="text/javascript"
            src="${pageContext.request.contextPath }/resources/ueditor1_2_6_1-utf8-jsp/ueditor.all.min.js"></script>
     <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/ueditor1_2_6_1-utf8-jsp/lang/zh-cn/zh-cn.js"></script>
     <script type="text/javascript">
       
	$(function(){
		  window.UEDITOR_HOME_URL ="${pageContext.request.contextPath }/resources/ueditor1_2_6_1-utf8-jsp/";
		       window.UEDITOR_CONFIG.autoHeightEnabled=false;
		       window.UEDITOR_CONFIG.initialFrameHeight=250;
		       window.UEDITOR_CONFIG.initialFrameWidth=700;
		       UE.getEditor('problem');
		       UE.getEditor('answer');
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
	  </style>
  </head>

  <body>
		   <div id="title">编辑试题</div>
		     <div>最后更新时间：<%--<fmt:formatDate value="" type="both"/>--%></div>
		     <sf:form action="${pageContext.request.contextPath}/teacher/paper/update" modelAttribute="paper" id="form" method="post">
		      <div id="area">
		     <div>试题名称<input name="name" value="${paper.name}"/></div>
		     <input type="hidden" value="${paper.id}" name="id"/>
		     <div><sf:textarea path="problem" id="problem" value="${paper.problem}"></sf:textarea></div>
		     <div style="height: 40px">参考答案</div>
		     <div><sf:textarea path="answer" id="answer" value="${paper.answer}"></sf:textarea></div>
		  
		    </div>
		     </sf:form>
		     <div style="position:relative">
			    <!-- button (形状) -->
			      <div class="u" id="save">
			        <img class="u_img" src="${pageContext.request.contextPath }/resources/style/images/button_u15.png"/>
			        <div class="u1" class="text">
			          <p><span>保存</span></p>
			        </div>
			      </div>
		    </div>
  </body>
</html>
