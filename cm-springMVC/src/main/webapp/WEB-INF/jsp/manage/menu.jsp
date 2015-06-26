<%--
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/css/menu.css">
  </head>

  <body>
    <div id="menu">
     <c:forEach items="${menuItems}" var="menu">
         <div><a href="${menu.url}" target="_self">${menu.name}</a></div>
     </c:forEach>
    </div>
  </body>
</html>
--%>
