<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>    
    <title>学生试卷</title>
      <script type="text/javascript">
          $(document).ready(function(){
              if(${application.examState==false}){
                  $("#content").html("<div style='margin-top:100px;font-size: 25px'>该学生尚未答题</div>");
              }
          });

      </script>
      <style>

          p{
              font-size:20px;
              font-weight:bold;
          }
      </style>
  </head>


  <body>
       <div id="title">学生答卷</div>
        <div><p>考试试题：</p>
                  <div style="height:130px">${application.paper.problem}</div>
        </div>
        <div><p>学生答案：</p>
                  <div>${application.answer}</div>
        </div>
  </body>
</html>
