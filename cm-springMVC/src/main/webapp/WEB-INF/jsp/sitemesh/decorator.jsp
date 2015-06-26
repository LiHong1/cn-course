<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>  <sitemesh:write property='title' /></title>
  <sitemesh:write property='head' />
</head>
<body>
<div id="container">
<div id="top"><jsp:include page="../public/top.jsp"></jsp:include> </div>
  <div id="left"><iframe frameborder="0"  scrolling="no" src="${pageContext.request.contextPath}/main/menu" width="100%" height="100%"></iframe></div>
  <div id="right">
    <div id="content">
      <sitemesh:write property='body' />
    </div>
  </div>
  <div id="bottom" style="height:50px"><span style="display: block;">版权所有</span><span style="display: block;">关于系统有任何问题请联系：zhenchun.lei@hotmail.com</span>   </div>
</div>
</body>
</html>
