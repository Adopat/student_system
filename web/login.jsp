<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
    application.setAttribute("path", basePath);
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${path}/static/css/login.css">
    <script type="text/javascript" src="${path}/static/js/jquery.js"></script>
    <title>学生管理系统-后台登录</title>
</head>
<body>
<div id="container">
    <div id="main">
        <img width="100%" height="500px" src="${path}/static/image/background.jpg">
        <div id="login">
            <form action="${path}/login" method="POST">
                <h4 style="color:black;text-align: center;margin-top:35px">学生管理系统</h4>
                <div class="form">
                    <img src="${path}/static/image/person.png" alt="账号">
                    <input type="text" name="username" placeholder="请输入账号">
                </div>
                <div class="form">
                    <img src="${path}/static/image/password.png" alt="密码">
                    <input type="password" name="password" maxlength="6" placeholder="请输入密码"></div>
                <input type="submit" value="登录">
            </form>
            <%--登录失败提示信息--%>
            <p id="error" style="color: red;text-align: center">${error}</p>
        </div>
    </div>
    <div id="footer">
        <p> 关于知否技术 隐私权 政策 法律声明 知识产权 | © 2003-现在 zhifou.com 版权所有</p>
    </div>
</div>
<script>
    $(function () {
        // 监听input输入框，清除 error 信息
        $("input").focus(function () {
            $("#error").text("");
        });
    });
</script>
</body>
</html>