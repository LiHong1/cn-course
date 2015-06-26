<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>已经答卷</title>
    <style>
      p{
        font-size:20px;
        font-weight:bold;
      }
    </style>
  </head>


  <body>
	       <div id="title">我的考试</div>
	        <div><p>考试试题：</p>
	                  <div style="height:130px">${answer.paper.problem}</div>
	        </div>
	        <div><p>我的答卷：</p>
	                  <div>${answer.answer}</div>
	        </div>
  </body>
</html>
