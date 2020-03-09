<%--
  Created by IntelliJ IDEA.
  User: SmartZ
  Date: 2020/3/4
  Time: 21:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>修改住户信息</title>
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
    <script src="js/jquery-3.4.1.js"></script>
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
                        if (i==${resident.edu}-1){
                            let newOption = $("<option></option>").html(edulist[i].name).attr("value",edulist[i].id).prop("selected",true);
                            $("#edusel").append(newOption);
                        }else {
                            let newOption = $("<option></option>").html(edulist[i].name).attr("value",edulist[i].id);
                            $("#edusel").append(newOption);
                        }
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
                        if (i==${resident.buildId}-1){
                            let newOption = $("<option></option>").html(buildlist[i].name).attr("value",buildlist[i].id).prop("selected",true);
                            $("#buildsel").append(newOption);
                        }else {
                            let newOption = $("<option></option>").html(buildlist[i].name).attr("value",buildlist[i].id);
                            $("#buildsel").append(newOption);
                        }
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
                        if (i==${resident.unitId}-1){
                            let newOption = $("<option></option>").html(unitslist[i].name).attr("value",unitslist[i].id).prop("selected",true);
                            $("#unitsel").append(newOption);
                        }else {
                            let newOption = $("<option></option>").html(unitslist[i].name).attr("value",unitslist[i].id);
                            $("#unitsel").append(newOption);
                        }
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
                        if (i==${resident.roomId}-1){
                            let newOption = $("<option></option>").html(roomslist[i].name).attr("value",roomslist[i].id).prop("selected",true);
                            $("#roomsel").append(newOption);
                        }else {
                            let newOption = $("<option></option>").html(roomslist[i].name).attr("value",roomslist[i].id);
                            $("#roomsel").append(newOption);
                        }
                    }
                }
            })
            <%--$("#rbtn").click(function () {--%>
            <%--    window.location = "residentServlet?mark=queryResidentById&curPage=${curPage}&id=${id}";--%>
            <%--})--%>
        })
    </script>
</head>
<body>
<h1 align="center">住户修改</h1>
<hr>
    <div>
        <form action="residentServlet?mark=updataResident&curPage=${curPage}" method="post" enctype="multipart/form-data">
            <table >
                <tr>
                    <td width="40%" align="right">住户名:</td>
                    <td width="60%">
                        <input type="text" name="id" value="${resident.id}" hidden>
                        <input type="text" name="name" value="${resident.name}">
                    </td>
                </tr>
                <tr>
                    <td align="right">性别：</td>
                    <td><input type="radio" value="1" name="gender"  ${resident.gender==1?"checked":""}>男<input type="radio" value="0" name="gender" ${resident.gender==0?"checked":""}> 女</td>
                </tr>
                <tr>
                    <td align="right">年龄：</td>
                    <td><input type="number" name="age" value="${resident.age}"></td>
                </tr>
                <tr>
                    <td align="right">生份证号：</td>
                    <td><input type="text" maxlength="18" name="idNum" value="${resident.idNum}"></td>
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
                    <td><input type="email" name="email" value="${resident.email}"></td>
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
                        </select>
                    </td>
                </tr>
                <tr>
                    <td align="right">手机号：</td>
                    <td><input type="text" name="phoneNum" maxlength="11" value="${resident.phoneNum}"></td>
                </tr>
                <tr>
                    <td align="right">合同有效期：</td>
                    <td>开始于<input type="date" name="startDate" value="${fn:substring(resident.startDate,0,10)}">截止于<input type="date" name="endDate" value="${fn:substring(resident.endDate,0,10)}"></td>
                </tr>
                <tr>
                    <td align="right">住户照片：</td>
                    <td><input type="file" name="img" ></td>
                </tr>
                <tr>
                    <td  align="right"><input type="submit" value="确认修改"></td>
                    <td>
                        <input type="reset">
<%--                        <a href="residentServlet?mark=queryResidentById&curPage=${curPage}&id=${id}">--%>
<%--                            --%>
<%--                        </a>--%>
<%--                        <button id="rbtn">重置</button>--%>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
