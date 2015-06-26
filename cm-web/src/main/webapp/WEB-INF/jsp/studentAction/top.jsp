<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title></title>
    <link type="text/css" rel="stylesheet" href="style/css/top.css">
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>

</head>
<body>
<div id="top_title">${user.cuState.course.name}&nbsp</div>

<div id="top_content">
      <span style="margin-left:40px">
        <img width="23" height="17" src="${pageContext.request.contextPath}/style/images/top/class.gif"/>
        ${user.cuState.clas.name}
      </span>
    <c:if test="${user!=null}">
        <li style="float:right;margin-right:20px;margin-bottom: 5px;" class="column">
            <s:a action="user_logout" target="_top">
                <img width="78" height="20" alt="退出系统"
                     src="${pageContext.request.contextPath}/style/images/top/logout.gif"/>
            </s:a>
        </li>
        <li style="float:right" class="column">
            <img border="0" width="13" height="14" src="${pageContext.request.contextPath}/style/images/top/user.gif"/>你好，${user.name}
        </li>
    </c:if>

</div>
</body>
</html>
