<%--
  Created by IntelliJ IDEA.
  User: SmartZ
  Date: 2020/3/5
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人信息</title>
    <style>
        table{
            border: 1px solid green;
            width: 100%;
            align:center;
        }
        td{
            border: 1px solid green;
        }
    </style>
</head>
<body>
<div>
    <a href="residentServlet?mark=queryResident&curPage=${param.curPage}"><button>返回</button></a>
    <table>
        <tr>
            <td width="10%" align="center">姓名:</td>
            <td width="20%" align="center">${resident.name}</td>
            <td width="10%" align="center">${resident.gender==1?"男":"女"}</td>
            <td width="60%" align="center"><img height="500px" src="residentServlet?mark=showImg&path=${resident.imgPath}"></td>
        </tr>
    </table>
</div>
</body>
</html>
