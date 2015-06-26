<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style/css/top.css">
</head>
<body>
<div id="top_title">上机系统管理</div>
<div id="top_content">
    <c:if test="${user!=null}">
        <li style="float:right;margin-right:20px;margin-bottom: 5px;" class="column"><s:a action="user_logout.action"
                                                                                          target="_top"> <img width="78"
                                                                                                              height="20"
                                                                                                              alt="退出系统"
                                                                                                              src="${pageContext.request.contextPath}/style/images/top/logout.gif"/></s:a>
        </li>
        <li style="float:right" class="column"><img border="0" width="13" height="14"
                                                    src="${pageContext.request.contextPath}/style/images/top/user.gif"/>你好，${user.name}
        </li>
    </c:if>
</div>
</body>
</html>
