<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>讨论主题</title>
     <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/css/forum.css" />
    <link rel="stylesheet"
            href="${pageContext.request.contextPath }/resources/ueditor1_2_6_1-utf8-jsp/themes/default/css/ueditor.css" />
     <script type="text/javascript"
            src="${pageContext.request.contextPath }/resources/ueditor1_2_6_1-utf8-jsp/ueditor.config.js"></script>
     <script type="text/javascript"
            src="${pageContext.request.contextPath }/resources/ueditor1_2_6_1-utf8-jsp/ueditor.all.min.js"></script>
     <script type="text/javascript"
            src="${pageContext.request.contextPath }/resources/ueditor1_2_6_1-utf8-jsp/lang/zh-cn/zh-cn.js"></script>

      <script type="text/javascript">
          $(document).ready(function(){
              window.UEDITOR_HOME_URL ="${pageContext.request.contextPath }/ueditor1_2_6_1-utf8-jsp/";
              window.UEDITOR_CONFIG.autoHeightEnabled=false;
              window.UEDITOR_CONFIG.initialFrameHeight=250;
              window.UEDITOR_CONFIG.initialFrameWidth=700;
              //UE.getEditor('add_replay');

              $("#reply").click(function(){
                  $("#form1").submit();
              });
              $(".reply").each(function(index){
                  var divs=$(this).find("div");
                  divs.eq(0).css("height", divs.eq(1).css("height"));
              });
              $(this).setHeight();
          });
      </script>
      <style>
          .reply{
              clear: left;
          }
          .reply_name,.head_name{
              float:left;
              width: 200px;
              text-align: center;
              height: auto;
          }
          .reply_content,.head_content{
              float:right;
              width: 500px;
              padding-left: 7px;
              height: auto;
          }
          .reply_name{
              border-left: 1px solid #cccccc;
              border-top: 1px solid #cccccc;
              border-bottom: 1px solid #cccccc;

          }
          .reply_content{
              border: 1px solid #cccccc;
              min-height: 70px;

          }
          .head_name{
              border-right:1px solid white;
          }
          #head{
              background-color:#33f;
              color:white;
              min-width: 40px;
              border: 1px solid black;
          }

      </style>
  </head>

  <body>
      <div id="title">讨论题</div>
          <div id="head">
              <c:if test="${page.currentPage == 1}">
                  <div>
                      <div class="head_name">标题：</div>
                      <div class="head_content">　　　　${topic.title}</div>
                  </div>
              </c:if>
          </div>
          <div class="reply">
              <div class="reply_name">${topic.state.teacher.name} <font color="#C30000">[楼主]</font></div>
              <div class="reply_content">${topic.content}</div>
          </div>
          <c:forEach items="${page.recordList}" varStatus="status" var="reply">
              <div class="reply">
                  <div class="reply_name">${reply.student.name}<c:if test="${reply.student==null}" >${topic.state.teacher.name} </c:if><font color=#C30000>[${(page.currentPage - 1) * page.pageSize + status.count}楼]</font></div>
                  <div class="reply_content">　${reply.content}</div>
              </div>
          </c:forEach>
        <!--分页信息-->
		<%@ include file="/WEB-INF/jsp/public/pageView.jspf" %>
		<form action="${id}" id="form"></form>
        <form action="${pageContext.request.contextPath}/${role}/topic/reply/add/${topic.id}" method="post" id="form1">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <div style="margin-bottom:20px"><textarea name="content" id="add_replay"></textarea></div>
         </form>
     <div style="position: relative">
         <!-- button (形状) -->
          <div class="u" id="reply" style="margin-left:40px">
            <img class="u_img" src="${pageContext.request.contextPath}/resources/style/images/button_u15.png"/>

            <div class="u1" class="text">
              <p><span>回复</span></p>
            </div>
          </div>
     </div>
  </body>

</html>
