<%--
  Created by IntelliJ IDEA.
  User: SmartZ
  Date: 2020/2/28
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>列表</title>
    <style type="text/css">
        table{
            text-align: center;
        }
    </style>
</head>
<body>
<table border="1" width="700px">
    <tr>
        <td colspan="4">
            <h1>${param.queryDAge}年龄段员工信息表</h1>
        </td>
    </tr>
    <tr>
        <td>编号</td>
        <td>姓名</td>
        <td>年龄</td>
        <td>性别</td>
    </tr>
    <c:forEach items="${list}" var="emp">
    <tr>
      <td>
         ${emp.id}
      </td>
      <td>
         ${emp.name}
      </td>
      <td>
         ${emp.age}
      </td>
      <td>
         ${emp.gender==1?"男":"女"}
      </td>
    </tr>
    </c:forEach>
</table>
</body>
</html>
