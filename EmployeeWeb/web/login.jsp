<%--
  Created by IntelliJ IDEA.
  User: SmartZ
  Date: 2020/2/28
  Time: 18:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
    <h1>登陆页面</h1>
    ${msg}
    <form action='userServlet' method='get'>
        <input type='hidden' name='mark' value='login' />
        用户名:<input type='text' name='uname' /><br />
        密 码:<input type='password' name='pwd' /><br />
        <input type='submit' value='登陆' />
        <a href='regis.jsp'>没有账号?去注册</a>
    </form>
</body>
</html>
