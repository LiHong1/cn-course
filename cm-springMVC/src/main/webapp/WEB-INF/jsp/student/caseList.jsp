<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>案例列表</title>
     <script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/resources/script/jquery.changeColor.js"></script>
    <script type="text/javascript">
    $(document).ready(function(){
      $("tbody tr:odd").changeColor({oddColor:"#ccc"});
    });
    </script>
<style type="text/css">
 table{
   text-align: center;
   border-spacing: 1px;
   border-spadding:1px;
   border:0px;
   background-color: #999;
  
  }
  tr{
  background-color: white;
  height: 30px;
  }
a{
  text-decoration: none;
  color: #333333;
  font-size: 13px;
}
 table thead  tr{
  background-color: #711;
   color:white;
  }
</style>
  </head>
  <body>
	      <div id="title">工程案例</div>
	      <table>
	      <thead>
	        <tr>
	            <td width="100px">序号</td>
	            <td width="500px">案例</td>
	            
	        </tr>
	        </thead>
	        <tbody>
	         <c:forEach items="${caseList}" var="case" varStatus="status">
				<tr>
	   			<td>${status.count}</td>
	             <td><a href="showCase/${case.id}">${case.title}</a></td>
				</tr>
			</c:forEach>
			</tbody>
	     </table>
  </body>
</html>
