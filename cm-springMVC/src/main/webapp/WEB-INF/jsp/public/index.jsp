<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<html>
  <head>   
    <title>首页</title>
      <meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
    <%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
  </head>
  <body>
  <div id="top_title"><c:if test="${role != 'manage'&& islogin}">${user.cuState.course.name}</c:if>&nbsp<c:if test="${role=='manage'}">上机系统管理</c:if></div>
       <div id="container">
       <div id="top"><jsp:include page="top.jsp"></jsp:include> </div>
          <div id="left">
            <iframe width="100%" height="100%"  scrolling="no"  frameborder="0" style="" src="${pageContext.request.contextPath}/main/menu"  >
            </iframe>
          </div>
          <div id="right"></div>
       </div>
       <div id="bottom" style="height:50px"><span style="display: block;">版权所有</span><span style="display: block;">关于系统有任何问题请联系：zhenchun.lei@hotmail.com</span>   </div>
  </body>

</html>
