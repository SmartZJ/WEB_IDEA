<%--
  Created by IntelliJ IDEA.
  User: SmartZ
  Date: 2020/3/3
  Time: 17:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>住户添加</title>
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

        -->
    </style>
    <script>
        $(function () {
            //获取学历下拉框
            $.ajax({
                url:"residentServlet",
                type:"post",
                data:{
                    mark:"queryEduList"
                },
                success:function (data) {
                    eval("var edulist ="+data);
                    for (let i=0;i<edulist.length;i++){
                        let newOption = $("<option></option>").html(edulist[i].name).attr("value",edulist[i].id);
                        $("#edusel").append
                        (newOption);
                    }
                }
            })
            //获取楼信息
            $.ajax({
                url:"residentServlet",
                type:"post",
                data:{
                    mark:"queryBuilds"
                },
                success:function (data) {
                    eval("var buildlist ="+data);
                    for (let i=0;i<buildlist.length;i++){
                        let newOption = $("<option></option>").html(buildlist[i].name).attr("value",buildlist[i].id);
                        $("#buildsel").append(newOption);
                    }
                }
            })
            //获取单元信息
            $.ajax({
                url:"residentServlet",
                type:"post",
                data:{
                    mark:"queryUnits"
                },
                success:function (data) {
                    eval("var unitslist ="+data);
                    for (let i=0;i<unitslist.length;i++){
                        let newOption = $("<option></option>").html(unitslist[i].name).attr("value",unitslist[i].id);
                        $("#unitsel").append(newOption);
                    }
                }
            })
            //获取房间信息
            $.ajax({
                url:"residentServlet",
                type:"post",
                data:{
                    mark:"queryRooms"
                },
                success:function (data) {
                    eval("var roomslist ="+data);
                    for (let i=0;i<roomslist.length;i++){
                        let newOption = $("<option></option>").html(roomslist[i].name).attr("value",roomslist[i].id);
                        $("#roomsel").append(newOption);
                    }
                }
            })
            $("#roomsel").change(function () {

                    $.ajax({
                        url:"residentServlet?mark=qureyRoomIsEmpty&build="+$("#buildsel").val()+"&unit="+$("#unitsel").val()+"&room="+$("#roomsel").val(),
                        type:"get",
                        success:function (data) {
                            console.log(data)
                            $("#roommsg").html(data)
                        }
                    })
            })

        })
    </script>
</head>
<body>
<h1 align="center">住户添加</h1>
<hr>
<form method="post" action="residentServlet?mark=addResident" enctype="multipart/form-data">
    <table border="1px" width="100%" align="center" style="border: green">
        <tr>
            <td width="40%" align="right">住户名:</td>
            <td width="60%"><input type="text" name="name"></td>
        </tr>
        <tr>
            <td align="right">性别：</td>
            <td>
                <input type="radio" value="1" name="gender">男
                <input type="radio" value="0" name="gender"> 女
            </td>
        </tr>
        <tr>
            <td align="right">年龄：</td>
            <td><input type="number" name="age"></td>
        </tr>
        <tr>
            <td align="right">生份证号：</td>
            <td><input type="text" maxlength="18" name="idNum">${idmsg}</td>
        </tr>
        <tr>
            <td align="right">学历：</td>
            <td>
                <select name="edu" id="edusel" >
                    <option >--请选择学历--</option>
                </select>
            </td>
        </tr>
        <tr>
            <td align="right">邮箱：</td>
            <td><input type="email" name="email"></td>
        </tr>
        <tr>
            <td align="right">所租房子：</td>
            <td>
                <select name="build" id="buildsel">
                    <option >--请选择楼栋--</option>
                </select>
                <select  name="unit" id="unitsel">
                    <option>--请选择单元--</option>
                </select>
                <select name="room" id="roomsel">
                    <option>--请选择房间--</option>
                </select><span id="roommsg"></span>
            </td>
        </tr>
        <tr>
            <td align="right">手机号：</td>
            <td><input type="text" name="phoneNum" maxlength="11"></td>
        </tr>
        <tr>
            <td align="right">合同有效期：</td>
            <td>开始于<input type="date" name="startDate">截止于<input type="date" name="endDate"></td>
        </tr>
        <tr>
            <td align="right">住户照片：</td>
            <td><input type="file" name="img"></td>
        </tr>
        <tr>
            <td  align="right"><input type="submit" value="确认添加"></td>
            <td><input type="reset"></td>
        </tr>
    </table>



</form>
</body>
</html>
