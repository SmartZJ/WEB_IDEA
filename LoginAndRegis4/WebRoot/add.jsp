<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
    <base href="<%=basePath%>">
    <title>添加</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>
<body>
    <h1>信息添加</h1>
 <hr>
 <form action="employeeServlet?mark=add" method="post">
     <c:if test="${msg!=null}">
         ${msg}<br>
     </c:if>
     编 号：<input type="number" name="id" value="001"><br>
     姓 名：<input type="text" name="name" value="无"><br>
     年 龄：<input type="number" name="age" value="20"><br>
     性 别：<input type="radio" name="gander" value="0" checked>女
            <input type="radio" name="gander" value="1">男<br>
     <input type="submit" value="提交">
 </form>


</body>
</html>
