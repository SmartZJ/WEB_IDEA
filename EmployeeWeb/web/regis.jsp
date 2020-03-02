<%--
  Created by IntelliJ IDEA.
  User: SmartZ
  Date: 2020/2/28
  Time: 18:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <script src="jquery-3.4.1.js"></script>
    <script>
        $(function () {
            $("#uname").blur(function () {
                console.log($(this).val());
                if ($(this).val()!=""){
                    $.ajax({
                        url:"userServlet",
                        type:"post",
                        data:{mark:"checkuname",uname:$(this).val()},
                        success:function (result) {
                            if (result=="true"){
                                $("#unameSpan").html("该用户名已被注册").attr("style","color:red");
                            }else {
                                $("#unameSpan").html("该用户名可以使用").attr("style","color:green");
                            }
                        }
                    })
                }else {
                    $("#unameSpan").html("");
                }
            })

        })
    </script>
</head>
<body>
<!-- 尝试添加记住密码 -->
    <h1>注册页面</h1>
    ${msg}
    <form action='userServlet' method='get'>
        <input type='hidden' name='mark' value='regis' />
        用户名:<input type='text' name='uname' id="uname"/><span id="unameSpan"></span><br/>
        密 码:<input type='password' name='pwd' /><br />
        重复密码:<input type='password' name='repwd' /><br />
        <input type='submit' value='注册' />
        <a href='login.jsp'>已有账号?去登陆</a>
    </form>
</body>
</html>
