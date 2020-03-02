<%--
  Created by IntelliJ IDEA.
  User: SmartZ
  Date: 2020/2/29
  Time: 2:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加员工</title>
    <script src="jquery-3.4.1.js"></script>
    <script>
        $(function () {
            $("#id").blur(function () {
                if ($(this).val()!=""){
                    $.ajax({
                        url:"employeeServlet",
                        type:"post",
                        data:{mark:"queryid",id:$(this).val() },
                        success:function (result) {
                            console.log(result);
                                if (result=="true"){
                                    $("#msg").html("该ID已添加").attr("style","color:red");
                                }else {
                                    $("#msg").html("该ID可以添加").attr("style","color:green");
                                }
                        }
                    })
                }else {
                    $("#msg").html("");
                }
            })
        })
    </script>
</head>
<body>
<h1>添加员工</h1>
${msg }
<form action="employeeServlet?mark=add" method="post">
    编 号:<input type="text" name="id" id="id"/><span id="msg"></span><br/>
    姓 名:<input type="text" name="name" /><br/>
    年 龄:<input type="text" name="age" /><br/>
    性 别:
    <input type="radio" name="gender" value="1" checked />男
    <input type="radio" name="gender" value="0" />女<br/>
    <input type="submit" value="确认添加" />
</form>
</body>
</html>
