<%--
  Created by IntelliJ IDEA.
  User: SmartZ
  Date: 2020/3/6
  Time: 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加设备</title>
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
        <form action="equipmentServlet?mark=addEquipment" method="post">
            <table width="100%" >
                <tr>
                    <td align="right" width="50%">设备编号：</td>
                    <td><input type="text" name="number" width="50%"></td>
                </tr>
                <tr>
                    <td align="right">设备名称：</td>
                    <td><input type="text" name="name"></td>
                </tr>
                <tr>
                    <td align="right">制造商：</td>
                    <td><input type="text" name="manufacturer"></td>
                </tr>
                <tr>
                    <td align="right">设备购价：</td>
                    <td><input type="number"  step="0.01" name="price"></td>
                </tr>
                <tr>
                    <td align="right">设备状态：</td>
                    <td>
                        <select name="state">
                            <option value="1">可用</option>
                            <option value="0">不可用</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td align="right">设备类型：</td>
                    <td>
                        <select name="type">
                            <option value="1">固定资产</option>
                            <option value="0">流动资产</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td align="right">启用时间：</td>
                    <td><input type="datetime-local" name="startTime"></td>
                </tr>
                <tr>
                    <td align="right">采购人员：</td>
                    <td><input type="text" name="buyer"></td>
                </tr>
                <tr>
                    <td align="right">维修人员：</td>
                    <td><input type="text" name="maintainer"></td>
                </tr>
                <tr>
                    <td align="right"><input type="submit" value="添加"></td>
                    <td><input type="reset"></td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
