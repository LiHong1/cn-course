<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
      <%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
	  <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/css/menu.css">
  <script>
   $(function(){
       $("#getCourse").click(function(){
                var url = "${pageContext.request.contextPath}/getCourse?number=${user.number}";
               var span = $(this).parent().find("span");
               if(span.hasClass("arrow-close")){
                   span.removeClass("arrow-close");
                   span.addClass("arrow-open");
                   $.get(url,function(datas){
                       for(var index in datas){
                           $("#courses").append("<div><div class='course'><span class='arrow arrow-close'></span><a href='#' class='getClass' data-id='"+datas[index].id+"'>"+datas[index].name+"</a></div></div>");
                       }
                   })
               }else{
                   span.removeClass("arrow-open");
                   span.addClass("arrow-close");
                   $(".course").parent().remove();
                   $(".class").parent().remove();
               }
               return false;
       })
       $("#courses").on("click",".getClass",function(){
           var courseId = $(this).attr("data-id");
           var url = "${pageContext.request.contextPath}/getClass?number=${user.number}&courseId="+courseId;
           var span = $(this).parent().find("span");
           var course = $(this).parent();
           if(span.hasClass("arrow-close")){
               span.removeClass("arrow-close");
               span.addClass("arrow-open");
               $.get(url,function(datas){
                   for(var index in datas){
                       course.parent().append("<div class='class'><a href='#' class='class-a' data-id='"+datas[index].id+"'>"+datas[index].name+"</a></div>");
                   }
               })
           }else{
               span.removeClass("arrow-open");
               span.addClass("arrow-close");
               $(".class").remove();
           }
           return false;
       })
       $("#courses").on("click",".class-a",function(){
           var courseId =$(this).parent().parent().find(".getClass").attr("data-id");
           var classId = $(this).attr("data-id");

           if(confirm("确定进入该班级！")){
               var url = "${pageContext.request.contextPath}/join?classId="+classId+"&courseId="+courseId;
               $("#classId").val(classId);
               $("#courseId").val(courseId);
               $("#join-form").submit();
              /* window.location=url;*/
           }
       });
   });
  </script>
  </head>
  <body>
   <div id="menu">
     <c:forEach items="${menuItems}" var="menu">
         <c:if test="${menu.authority eq 'user'}">
             <c:if test="${(menu.name != '选择课程')}">
                 <div><a href="${pageContext.request.contextPath}/${menu.url}" target="_parent">${menu.name}</a></div>
             </c:if>
             <c:if test="${(menu.name eq '选择课程')&&((role != 'manage' ))}">
                 <div  id="courses"><div><span class="arrow arrow-close"></span><a href="#" id="getCourse">${menu.name}</a></div></div>
             </c:if>

         </c:if>
         <%--  如果登入后选择了课程班级及有相应的权限--%>
         <c:if test="${((role == 'manage')&&(menu.authority eq role))||(menu.authority eq role && islogin)}">
             <div><a href="${pageContext.request.contextPath}/${menu.url}" target="_parent">${menu.name}</a></div>
         </c:if>
         <c:if test="${(menu.name eq '导入数据')&&((menu.authority == role  && !islogin))}">
         <div><a href="${pageContext.request.contextPath}/${menu.url}" target="_parent">${menu.name}</a></div>
         </c:if>
             </c:forEach>
       <form action="${pageContext.request.contextPath}/join" method="post" style="display: none" id="join-form" target="_parent">
           <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
           <input type="text" value="" name="courseId" id="courseId">
           <input type="text" value="" name="classId" id="classId">
       </form>
    </div>
  </body>
</html>
