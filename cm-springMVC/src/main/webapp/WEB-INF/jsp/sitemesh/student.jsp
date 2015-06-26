<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
      <title>
          <sitemesh:write property='title' />
      </title>
      <meta http-equiv="Content-Type" content="text/html;charset=UTF-8" />
      <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/style/css/top.css">
      <%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
      <sitemesh:write property='head' />
  </head>
  <body>
  <div id="top_title">${user.cuState.course.name}</div>
  <div id="container">
      <div id="top"><jsp:include page="../public/top.jsp"></jsp:include> </div>
   <div id="left"><iframe  scrolling="no" src="${pageContext.request.contextPath}/main/menu" height="100%" width="100%" frameborder="0"></iframe></div>
   <div id="right">
       <div id="content">
          <sitemesh:write property='body' />
       </div>
   </div>
  </div>
   <div id="bottom" style="height:50px"><span style="display: block;">版权所有</span><span style="display: block;">关于系统有任何问题请联系：zhenchun.lei@hotmail.com</span>   </div>
  </body>
</html>
