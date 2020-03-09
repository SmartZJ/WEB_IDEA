<%--
  Created by IntelliJ IDEA.
  User: SmartZ
  Date: 2020/3/3
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>无标题文档</title>
    <style type="text/css">
        body {
            margin-left: 0px;
            margin-top: 0px;
            margin-right: 0px;
            margin-bottom: 0px;
        }
    </style>


    <link rel="StyleSheet" href="css/dtree.css" type="text/css" />
    <script type="text/javascript" src="js/dtree.js"></script>
    <script type="text/javascript">

        d = new dTree('d');

        d.add(0,-1,'爱情公寓物业系统');
        d.add(1,0,'住户管理','residentServlet?mark=queryResident&curPage=1','住户列表','I2');
        d.add(2,1,'住户添加','addResident.jsp','住户添加','I2');
        d.add(3,1,'住户列表','residentServlet?mark=queryResident&curPage=1','住户列表','I2');
        d.add(4,1,'学历报表','educharts.jsp','学历报表','I2');
        d.add(5,1,'年龄报表','agecharts.jsp','年龄报表','I2');
        d.add(6,1,'导出住户信息','residentServlet?mark=downloadResident');
        d.add(7,0,'设备管理','equipmentServlet?mark=showEquipments&curPage=1','设备列表','I2');
        d.add(8,7,'设备添加','addEquipment.jsp','设备添加','I2');
        d.add(9,7,'设备列表','equipmentServlet?mark=showEquipments&curPage=1','设备列表','I2');
        d.add(10,0,'房产管理');
        d.add(11,10,'房产查询','showRoom.jsp','房产查询','I2');
        d.add(12,10,'房产更改','estateChange.jsp','房产更改','I2');

        window.onload=function(){
            document.getElementById("menu").innerHTML=d;
        }

    </script>
</head>
<body>
<table width="173" height="100%" border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
    <tr>
        <td style="width:4px;" ></td>
        <td width="169" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td height="20" background="images/main_11.gif">&nbsp;</td>
            </tr>
            <tr>
                <td> <div id="menu"></div> </td>
            </tr>
        </table></td>
    </tr>
</table>
</body>
</html>
