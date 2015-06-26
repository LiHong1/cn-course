<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>考试设置</title>
       <link rel="stylesheet"
            href="${pageContext.request.contextPath }/resources/ueditor1_2_6_1-utf8-jsp/themes/default/css/ueditor.css" />
       <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/style/css/switch.css">
     <script type="text/javascript"
            src="${pageContext.request.contextPath }/resources/ueditor1_2_6_1-utf8-jsp/ueditor.config.js"></script>
     <script type="text/javascript"
            src="${pageContext.request.contextPath }/resources/ueditor1_2_6_1-utf8-jsp/ueditor.all.min.js"></script>
     <script type="text/javascript"
            src="${pageContext.request.contextPath }/resources/ueditor1_2_6_1-utf8-jsp/lang/zh-cn/zh-cn.js"></script>
     <script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/switch.js"></script>
	  
    <script type="text/javascript">
  
		 $(document).ready(function(){
		     window.UEDITOR_HOME_URL ="${pageContext.request.contextPath }/ueditor1_2_6_1-utf8-jsp/";
		     window.UEDITOR_CONFIG.initialFrameHeight=250;
		     window.UEDITOR_CONFIG.initialFrameWidth=700;
		     window.UEDITOR_CONFIG.autoHeightEnabled=false;
             UE.getEditor('examAttention');
			 $("#switch1").switch({
				 initValueId:"#examAble"
			 });
			 $("#switch2").switch({
				 initValueId:"#reExamAble"
			 });
			 $("#save").click(function(){
				 $("#form").submit();
			  }); 
			 $("#reExam").click(function(){
				 if(confirm("确定全部从新考试？")){
					  window.location.href="${pageContext.request.contextPath}/teacher/student/reExam";
				 }
	          }); 
});
	 
    </script>
	  <style>
		  #area{
			  margin-top:30px;
			  margin-bottom: 40px;
		  }
		  ul li span{
			  margin-top: -5px;
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
		  .h{
			  margin-left:0px;
		  }
		  #download span{
			  margin-top: 8px;
		  }
	  </style>
  </head>
  <body>
			   <div id="title">考试管理</div>
			   <div id="area">
			      <sf:form id="form" method="post" modelAttribute="state">
			          <input type="hidden" value="${state.id}" name="id"/>
					  <input type="hidden" value="${state.examAble}" name="examAble" id="examAble"/>
					  <input type="hidden" value="${state.reExamAble}" name="reExamAble" id="reExamAble"/>
			   <div class="contentin contentfirst">
			        <div  class="h">
			    <div style="float: left;margin-right: 45px">1.考试开启</div>
			     <div style="float: left;">
			        <div class="switch" id="switch1"></div>
			    </div>
			   </div>
			   <div class="h">
			    <div style="float: left;margin-right: 10px">2.允许重考　　&nbsp</div>
			     <div style="float: left;">
			        <div id="switch2" class="switch"></div>
			    </div>
			   </div>
			      <div>考试注意事项：<sf:textarea  value="${state.examAttention}" path="examAttention" id="examAttention"/></div>
			      <div style="float: left;width: 101px;height: 26px;position: relative;">
			        <!-- button (形状) -->
			      <div class="u"  id="save" style="width: 101px;height: 26px">
			        <img class="u_img" src="${pageContext.request.contextPath }/resources/style/images/button_u15.png"/>
			        <div class="u1" class="text">
			          <p><span>保存</span></p>
			        </div>
			       </div>
			      </div>

			      <div style="float: left;width: 131px;height: 41px;margin-left: 80px;position: relative;">
			      <div class="u" id="reExam" >
			        <img class="u_img" src="${pageContext.request.contextPath }/resources/style/images/teacher/button_u93.png"/>
			        <div class="u1" class="text" style="float: left;width: 131px;height: 41px">
			          <p><span>全部重新考试</span></p>
			        </div>
			      </div>
			     </div>
			   </div>
			  
			  </sf:form>
			    </div>
  </body>
</html>
