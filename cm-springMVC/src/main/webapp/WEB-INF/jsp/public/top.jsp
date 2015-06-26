<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title></title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/css/pageCommon.css">
  </head>
  <body>
   <div id="top_content">
     <c:if test="${role != 'manage'}">
      <span style="margin-left:40px">
        <img  src="${pageContext.request.contextPath}/resources/style/images/top/class.gif"/>
        ${user.cuState.clas.name}
      </span>
       </c:if>
     <c:if test="${user!=null}">
     <li class="column_1">
     <a href="${pageContext.request.contextPath}/logout" target="_top">
     <img  alt="退出系统" src="${pageContext.request.contextPath}/resources/style/images/top/logout.gif" />
     </a>
     </li>
     <li class="column_2">
     <img src="${pageContext.request.contextPath}/resources/style/images/top/user.gif" />你好，${user.name}
     </li>
     </c:if>
    
   </div>
  </body>
</html>
