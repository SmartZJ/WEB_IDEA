<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>修改</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>
<body>
    <h1>信息修改</h1>
    <hr>
    <form action="employeeServlet?mark=revise" method="post">
        编 号：<input type="number" name="id" readonly value="${emp.id}"><br>
        姓 名：<input type="text" name="name" value="${emp.name}"><br>
        年 龄：<input type="number" name="age" value="${emp.age}"><br>
        性 别：<input type="radio" name="gander" value="0" ${emp.gander==0?"checked":""}>女
        <input type="radio" name="gander" value="1" ${emp.gander==1?"checked":""}>男<br>
        <input type="submit" value="提交">
    </form>
</body>
</html>
