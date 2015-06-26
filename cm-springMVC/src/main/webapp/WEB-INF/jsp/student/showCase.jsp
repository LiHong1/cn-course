<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>工程案例</title>
  </head>
  <body>
              <div id="title">${Case.title}</div>
                    案例内容：
             <div style="margin-bottom: 100px;">${Case.content}</div>
             <div>
              附件：
              <div>
              <c:forEach items="${Case.materials}" varStatus="status" var="material">

                    <div style="height: 30px">
                        <c:if test="${material.type ==1}"><a href="material/show/${material.id}">${material.name}</a> &nbsp</c:if>
                        <c:if test="${material.type!=1&&material.type!=0}">${material.name}</c:if>
                        <c:if test="${material.type==0}"><a href="${pageContext.request.contextPath}/student/material/player/${material.id}">播放</a> &nbsp</c:if>
                        <a href="${pageContext.request.contextPath}/student/material/download/${material.id}">下载</a>
                    </div>

                    </div>
                </c:forEach>
             </div>
  </body>
</html>
