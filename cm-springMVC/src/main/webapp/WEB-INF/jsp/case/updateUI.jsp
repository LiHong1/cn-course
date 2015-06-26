<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>更新案例</title>
     <script type="text/javascript"
            src="${pageContext.request.contextPath }/resources/ueditor1_2_6_1-utf8-jsp/ueditor.config.js"></script>
     <script type="text/javascript"
            src="${pageContext.request.contextPath }/resources/ueditor1_2_6_1-utf8-jsp/ueditor.all.min.js"></script>
     <link rel="stylesheet"
            href="${pageContext.request.contextPath }/resources/ueditor1_2_6_1-utf8-jsp/themes/default/css/ueditor.css" />
     <script type="text/javascript"
            src="${pageContext.request.contextPath }/resources/ueditor1_2_6_1-utf8-jsp/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript">
    $(document).ready(function(){
    window.UEDITOR_HOME_URL ="${pageContext.request.contextPath }/resources/ueditor1_2_6_1-utf8-jsp/";
        window.UEDITOR_CONFIG.initialFrameHeight=250;
        window.UEDITOR_CONFIG.initialFrameWidth=700;
        window.UEDITOR_CONFIG.autoHeightEnabled=false;
        UE.getEditor('content_1');
   
	       $("#update").click(function(){
	        $("#form1").submit();
            });
	       $("#upload").click(function(){
	        $("#form2").submit();
            });

	});
	
    </script>
  </head>
  <body>
             <div id="title">修改案例</div>
  
                   <sf:form id="form1" modelAttribute="case" method="post" action="${pageContext.request.contextPath}/teacher/case/update">
                    <input type="hidden" name="id" value="${case.id}"/>
                   <div>标题<div><input  name="title" value="${case.title}" /></div></div>
                   <div>内容<div><sf:textarea path="content" id="content_1" value="${case.content}"></sf:textarea></div></div>
                   </sf:form>


                   <div>
                       添加附件:
                              <c:forEach items="${case.materials}" var="material">
                                 <div>${material.name}　　 <a href="${pageContext.request.contextPath}/teacher/case/material/delete/${material.id}">删除</a></div>
                              </c:forEach>
                             <form method="post" enctype="multipart/form-data" action="${pageContext.request.contextPath}/teacher/case/material/add/${case.id}" id="form2">
                                 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                               <input type="file" name="file" value="选择文件"><input type="text" name="name">
                                 <div style="margin-top:50px;">
                                 <div  style="position:relative;float:left">
                                 <!-- button (形状) -->
                                  <div class="u" id="upload">
                                    <img class="u_img" src="${pageContext.request.contextPath}/resources/style/images/button_u15.png"/>
                                    <div class="u1" class="text">
                                      <p><span>上传附件</span></p>
                                    </div>
                                  </div>
                                 </div>
                                  <div  style="position:relative;margin-left:350px;float:left">
                                      <!-- button (形状) -->
                                      <div class="u" id="update">
                                        <img class="u_img" src="${pageContext.request.contextPath}/resources/style/images/button_u15.png"/>

                                        <div class="u1" class="text">
                                          <p><span>保存</span></p>
                                        </div>
                                      </div>
                                   </div>
                                  </div>
                            </form>
                   </div>
  </body>
</html>
