<%--
  Created by IntelliJ IDEA.
  User: SmartZ
  Date: 2020/3/6
  Time: 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>添加设备</title>
    <script src="js/jquery-3.4.1.js"></script>
    <style type="text/css">
        /*取消number输入上下键*/
        input::-webkit-outer-spin-button,
        input::-webkit-inner-spin-button {
            -webkit-appearance: none;
        }
        input[type="number"]{
            -moz-appearance: textfield;
        }

        body {
            margin-left: 0px;
            margin-top: 0px;
            margin-right: 0px;
            margin-bottom: 0px;
        }
    </style>
</head>
<body>
<h1 align="center">设备添加</h1>
<hr>
    <div>
        <form action="equipmentServlet?mark=updataEquipment&curPage=${requestScope.curPage}" method="post">
            <table width="100%" >
                <tr>
                    <td align="right" width="50%" >设备编号：</td>
                    <td>
                        <input type="text" name="id" value="${item.id}" hidden>
                        <input type="text" name="number" width="50%" value="${item.number}">
                    </td>
                </tr>
                <tr>
                    <td align="right">设备名称：</td>
                    <td><input type="text" name="name" value="${item.ename}"></td>
                </tr>
                <tr>
                    <td align="right">制造商：</td>
                    <td><input type="text" name="manufacturer" value="${item.manufacturer}"></td>
                </tr>
                <tr>
                    <td align="right">设备购价：</td>
                    <td><input type="number"  step="0.01" name="price" value="${item.price}"></td>
                </tr>
                <tr>
                    <td align="right">设备状态：</td>
                    <td>
                        <select name="state">
                            <option value="1" ${item.state==1?"seleced":""}>可用</option>
                            <option value="0" ${item.state==0?"seleced":""}>不可用</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td align="right">设备类型：</td>
                    <td>
                        <select name="type">
                            <option value="1" ${item.type==1?"seleced":""}>固定资产</option>
                            <option value="0" ${item.type==0?"seleced":""}>流动资产</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td align="right">启用时间：</td>


                    <td><input type="datetime-local" name="startTime" value="${item.startTime}"></td>
                </tr>
                <tr>
                    <td align="right">采购人员：</td>
                    <td><input type="text" name="buyer" value="${item.buyer}"></td>
                </tr>
                <tr>
                    <td align="right">维修人员：</td>
                    <td><input type="text" name="maintainer" value="${item.maintainer}"></td>
                </tr>
                <tr>
                    <td align="right"><input type="submit" value="修改"></td>
                    <td><input type="reset"></td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
