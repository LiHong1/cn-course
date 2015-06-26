<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
      <title>更改口令</title>
   <%@ include file="/WEB-INF/jsp/public/commons.jspf"%>
   <script type="text/javascript" src="${pageContext.request.contextPath}/resources/script/jquery.validate.js"></script>
      <script type="text/javascript">
          $(document).ready(function(){
              $("#change").click(function(){
                  $("#form").submit();
              });
              $("#reset").click(function(){

                  $("#form")[0].reset();
              });

              $("#form").validate({
                  rules: {
                      password:"required",
                      newPassword:"required",
                      reNewPassword:{
                          required:true,
                          equalTo: "#newPassword"
                      }
                  },
                  messages: {
                      password: "请输入原始密码",
                      newPassword: "请输入新密码",
                      reNewPassword: {
                          required: "请再输入新密码",
                          equalTo: "两次输入密码不一致"
                      }
                  }
              });

          });
      </script>
  </head>

  <body>
  <div id="top">
      <div id="top_title">${user.cuState.course.name}&nbsp</div>
      <div id="top_content">
      <span style="margin-left:40px">
	    <img width="23" height="17"  src="${pageContext.request.contextPath}/resources/style/images/top/class.gif"/>
	    ${user.cuState.clas.name}
      </span>
          <c:if test="${user!=null}">
              <li style="float:right;margin-right:20px;margin-bottom: 5px;" class="column">
                  <a href="${pageContext.request.contextPath}/logout" target="_top">
                      <img width="78" height="20" alt="退出系统" src="${pageContext.request.contextPath}/resources/style/images/top/logout.gif" />
                  </a>
              </li>
              <li style="float:right" class="column">
                  <img border="0" width="13" height="14" src="${pageContext.request.contextPath}/resources/style/images/top/user.gif" />你好，${user.name}
              </li>
          </c:if>
      </div>
  </div>
  <div id="left"><jsp:include page="menu.jsp"></jsp:include></div>
  <div id="right">
      <div id="content">
			     <div id="title">更改口令</div>

					 <form method="post" id="form">
                         <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                           <div style="color: red;text-align: center;width: 300px;height: 30px">${info}</div>
                          　<input type="hidden" name="number" value="${user.number}">
                           <div class="input">旧口令　　　　<input type="password" name="password" id="password" style="width: 250px"></div>
                           <div class="input">新口令　　　　<input type="password" name="newPassword"  id="newPassword" style="width:250px">
                           </div>
                           <div class="input">再输一次　　　<input type="password" name="reNewPassword" id="reNewPassword"style="width: 250px">
                           </div>
					 </form>
			      <div>
			       <div style="position:relative;float:left">
					  <!-- button (形状) -->
					  <div class="u" id="change">
							<img class="u_img" src="${pageContext.request.contextPath}/resources/style/images/button_u15.png"/>
							<!-- Unnamed () -->
							<div class="u1" >
							  <p><span>更改</span></p>
							</div>
					   </div>
			        </div>
						<div style="position:relative;float:left;margin-left:60px">
							<div class="u" id="reset">
								<img class="u_img" src="${pageContext.request.contextPath}/resources/style/images/button_u15.png"/>
								<!-- Unnamed () -->
								<div class="u1" >
								  <p><span>重置</span></p>
								</div>
							</div>
						</div>
			        </div>
      </div>
  </div>
  <div id="bottom" style="height:50px"><span style="display: block;">版权所有</span><span style="display: block;">关于系统有任何问题请联系：zhenchun.lei@hotmail.com</span>   </div>
  </body>
</html>
