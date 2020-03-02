<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>列表</title>
    <script src="jquery-3.4.1.js"></script>
    <style type="text/css">
        table{
            text-align: center;
        }
    </style>
    <script>
        $(function () {
            $("#sl").change(function () {
                window.location.href="employeeServlet?mark=queryByAges&curPage="+$(this).val()+"&Ages=${Ages}";

            })
        })
    </script>
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
    <tr>
        <td colspan="4">
            <select id="sl" size="1" style="overflow: hidden">
                <c:forEach begin="1" end="${page.lastPage}" var="i">
                    <c:choose>
                        <c:when test="${page.curPage==i }">
                            <option selected>第${i}页</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${i}">第${i}页</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </td>
    </tr>
</table>
</body>
</html>
