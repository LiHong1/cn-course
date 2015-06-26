<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
        <title>增加试题</title>
		 <link rel="stylesheet"
            href="${pageContext.request.contextPath }/resources/ueditor1_2_6_1-utf8-jsp/themes/default/css/ueditor.css" />
     <script type="text/javascript"
            src="${pageContext.request.contextPath }/resources/ueditor1_2_6_1-utf8-jsp/ueditor.config.js"></script>
     <script type="text/javascript"
            src="${pageContext.request.contextPath }/resources/ueditor1_2_6_1-utf8-jsp/ueditor.all.min.js"></script>
     <script type="text/javascript"
            src="${pageContext.request.contextPath }/resources/ueditor1_2_6_1-utf8-jsp/lang/zh-cn/zh-cn.js"></script>
        <script type="text/javascript">
            $(document).ready(function(){
                window.UEDITOR_HOME_URL ="${pageContext.request.contextPath }/ueditor1_2_6_1-utf8-jsp/";
                window.UEDITOR_CONFIG.initialFrameHeight=200;
                window.UEDITOR_CONFIG.initialFrameWidth=700;
                window.UEDITOR_CONFIG.autoHeightEnabled=false;
                UE.getEditor('problem');
                UE.getEditor('answer');
                $("#save").click(function(){
                    $("#form").submit();
                });
            });
        </script>
    </head>

  <body>

                 <div id="title">新增试题</div>
                <form  id="form" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                  <div id="area">
                      <div>试题名称<input type="text" name="name"/></div>
                      <div><textarea name="problem" id="problem"></textarea></div>
                      <div style="margin:15px">参考答案</div>
                      <div><textarea name="answer" id="answer"></textarea></div>
                  </div>
                 </form>
                  <div style="position:relative">
                      <div class="u" id="save">
                        <img class="u_img" src="${pageContext.request.contextPath}/resources/style/images/button_u15.png"/>
                       
                        <div class="u1" class="text">
                          <p><span>新增</span></p>
                        </div>
                      </div>
                  </div>
</body>
</html>
