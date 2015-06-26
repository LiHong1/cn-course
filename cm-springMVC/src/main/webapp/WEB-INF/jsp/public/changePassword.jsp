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
			     <div id="title">更改口令</div>
                    <div id="ch_password">
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

			      <div style="margin-left: 50px">
                       <div style="position:relative;float:left">
                          <!-- button (形状) -->
                          <div class="u" id="change">
                                <img class="u_img" src="resources/style/images/button_u15.png"/>
                                <!-- Unnamed () -->
                                <div class="u1" >
                                  <p><span>更改</span></p>
                                </div>
                           </div>
                        </div>
                            <div style="position:relative;float:left;margin-left:60px">
                                <div class="u" id="reset">
                                    <img class="u_img" src="resources/style/images/button_u15.png"/>
                                    <!-- Unnamed () -->
                                    <div class="u1" >
                                      <p><span>重置</span></p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
  </body>
</html>
