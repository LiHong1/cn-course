<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>材料列表</title>
  </head> 
  <body>
           <div id="title">学习材料</div>
        <c:forEach items="${materialList}" var="material" varStatus="status">
            <div style="height: 30px"> 
            ${status.count}.<c:if test="${material.type ==1}"><a href="material/show/${material.id}">${material.name}</a> &nbsp</c:if>
              <c:if test="${material.type!=1&&material.type!=0}">${material.name}</c:if>
             <c:if test="${material.type==0}"><a href="material/player/${material.id}">播放</a> &nbsp</c:if>
            <a href="material/download/${material.id}">下载</a>
            </div>
        </c:forEach>
  </body>
</html>
