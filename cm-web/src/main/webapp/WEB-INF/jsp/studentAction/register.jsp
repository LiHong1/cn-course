<%@ page language="java" pageEncoding="utf-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>注册</title>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/xiniu.css"></link>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/style/css/register.css"></link>
    <script language="javascript" src="${pageContext.request.contextPath}/script/jquery.validate.js"></script>
    <script language="javascript" src="${pageContext.request.contextPath}/script/register.js"></script>
</head>
<body>
<div id="container">
    <div id="area">
        <form action="student_add.action" id="form" method="post">
            <div id="title">学生注册</div>
            <div><s:actionmessage/></div>
            <div class="input">学　号：　　<input type="text" name="number" id="number"/></div>
            <div class="input">姓　名：　　<input type="text" name="name" id="name"/></div>
            <div class="input">口　令：　　<input type="password" name="password" id="password"/></div>
            <div class="input">再输一次：　<input type="password" name="password1" id="password1"/></div>

            <div style="position: relative;">
                <div style="float: left;width: 150px;height: 26;position: relative;">
                    <div class="u" id="register">
                        <img class="u_img" src="style/images/button_u15.png"/>

                        <div class="u1">
                            <p><span>注册</span></p>
                        </div>
                    </div>
                </div>
                <div style="float: left;width: 101px;height: 26px;position: relative;">

                    <div class="u" id="reset">
                        <img class="u_img" src="style/images/button_u15.png"/>

                        <div class="u1">
                            <p><span>重置</span></p>
                        </div>
                    </div>

                </div>
                <div style="text-align: center;width:250px"><a href="index.jsp">登录</a></div>

            </div>

        </form>
    </div>
    <div style="float:right;margin-top:50px">
        <img src="style/images/register/right.jpg" width="250px">

        <div style="width: 300px;height: 10px">
            <div class="ywz_zhuce_huixian" id='pwdLevel_1'></div>
            <div class="ywz_zhuce_huixian" id='pwdLevel_2'></div>
            <div class="ywz_zhuce_huixian" id='pwdLevel_3'></div>
        </div>
        <div>
            <div class="ywz_zhuce_hongxianwenzi"> 弱</div>
            <div class="ywz_zhuce_hongxianwenzi"> 中</div>
            <div class="ywz_zhuce_hongxianwenzi"> 强</div>
            <div style="clear: left">
                         <span id="pwd_tip" style="color: #898989">
                            <font style="color: #F00">*</font> 6-16位，由字母（区分大小写）、</br> &nbsp 数字、符号组成
                         </span>
                         <span id="pwd_err" style="color: #f00; display:none;">6-16位，由字母（区分大小写）、</br> &nbsp 数字、符号组成
                         </span>
            </div>
        </div>
    </div>
</div>
</body>
</html>
