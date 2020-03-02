<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'regis.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <h1>注册页面</h1>
	${msg}
	<form action='userServlet?mark=regis' method='post' enctype="multipart/form-data">
		<input type='hidden' name='mark' value='regis' />
		用户名:<input type='text' name='uname' /><br>
		密 码:<input type='password' name='pwd' /><br>
		重复密码:<input type='password' name='repwd' /><br>
		上传头像:<input type="file" name="file"><br>
		<input type='submit' value='注册' />
		<a href='login.jsp'>已有账号?去登陆</a>
	</form>
  </body>
</html>
