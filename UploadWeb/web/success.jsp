<%--
  Created by IntelliJ IDEA.
  User: SmartZ
  Date: 2020/2/20
  Time: 17:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>使用</title>
</head>
<body>
    <h1>欢迎${uname}<img src="${path}" width="80px" height="100px">使用系统</h1>
    <hr>
    ${msg}
    <form action="userServlet?mark=upload&uname=${uname}" method="post" enctype="multipart/form-data">
    <input type="file" name="file">
        <input type="submit" value="上传图片">
    </form>
<c:forEach items="${list}" var="img">
    <img src="userServlet?mark=show&path=${img}" width="100px"> <a href="userServlet?mark=show&path=${img}">${fn:split(img,"/")[fn:length(fn:split(img,"/"))-1]}</a> <a href="userServlet?mark=download&path=${img}">下载图片</a><br>
</c:forEach>
</body>
</html>
