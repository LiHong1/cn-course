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
        <%-- <c:if test="menu.authority eq 'user'}">--%>
             <div><a href="${pageContext.request.contextPath}/${menu.url}" target="_self">${menu.name}</a></div>
         <%--</c:if>--%>
     </c:forEach>
        <%--<div><a href="${pageContext.request.contextPath}/teacher/import" target="_self">导入数据</a></div>
        <div><a href="${pageContext.request.contextPath}/teacher/changePassword" target="_self">更改口令</a></div>
        <div><a href="${pageContext.request.contextPath}/logout" target="_self">退出</a></div>--%>
    </div>
  </body>
</html>
