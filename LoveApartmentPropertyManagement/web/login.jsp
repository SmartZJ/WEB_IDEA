<%--
  Created by IntelliJ IDEA.
  User: SmartZ
  Date: 2020/3/3
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>物业管理系统_用户登录</title>
    <script src="js/jquery-3.4.1.js"></script>
    <script>
        //验证码判断标识
        var flag;
        $(function () {
            //点击验证码刷新
            $("#img").click(function () {
                $.ajax({
                    url:"image.jsp"
                })
                $(this).attr("src","image.jsp");
            })
            //验证码验证
            $("[name='validate']").blur(function () {
                console.log($(this).val());
                if ($(this).val()!=""){
                    $.ajax({
                            url:"userServlet",
                            type:"post",
                            data:{
                                mark:"vailcode",
                                vailcode:$(this).val()
                            },
                            success:function (data) {
                                console.log(data);
                                if ( "true"==data){
                                    $("#valimsg").removeAttr("hidden").html("验证通过！").attr("style","color:green");
                                    flag = true;
                                }else {
                                    $("#valimsg").removeAttr("hidden").html("验证码不正确！").attr("style","color:red");
                                    flag = false;
                                }
                            }
                    })
                }else {
                    $("#valimsg").attr("hidden",true);
                }

            })
            //点击登录提交信息
            $("#submit").click(function () {
                let uname = $("[name ='uname']").val();
                let pwd = $("[name ='pwd']").val();
                if (uname != "" && pwd != "" && flag) {
                    $.ajax({
                        url: "userServlet",
                        type: "post",
                        data: {mark: "login", uname: uname, pwd: pwd},
                        success: function (result) {
                            console.log(result);
                            if (result == "true") {
                                window.location = "main.jsp"
                            } else {
                                $("#pwdmsg").removeAttr("hidden")
                            }
                        }
                    })
                } else {
                    if (uname != "" || pwd != ""){
                        $("#pwdmsg").removeAttr("hidden").html("请输入用户名或密码")
                    }else if ($("[name='validate']").val()==""){
                        $("#valimsg").removeAttr("hidden").html("未输入验证码！").attr("style","color:red");
                    }

                }
            })
        })

    </script>
    <style type="text/css">
        body {
            margin-left: 0px;
            margin-top: 0px;
            margin-right: 0px;
            margin-bottom: 0px;
            overflow: hidden;
        }

        .STYLE1 {
            font-size: 12px
        }
    </style>
</head>
<body>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td bgcolor="9fc967">&nbsp;</td>
    </tr>
    <tr>
        <td height="604">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td height="604" background="images/login_02.gif">&nbsp;</td>
                    <td width="989">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td height="345">
                                    <div style="position:relative; height:345px;">
                                        <div style="position:absolute;left:0px;top:0px"><img src="images/login_1.jpg"/>
                                        </div>
                                        <div style="position:absolute;left:480px;top:300px">
                                            <table width="380" border="0" cellspacing="0" cellpadding="0">
                                                <tr>
                                                    <td width="25%" height="22">
                                                        <div align="right"><span class="STYLE1">用户:</span></div>
                                                    </td>
                                                    <td width="35%" height="22">
                                                        <div align="left">
                                                            <%--                                                            用户--%>
                                                            <input name="uname" type="text" size="15"
                                                                   style="height:20px;border:solid 1px #bbbbbb">
                                                        </div>
                                                    </td>
                                                    <td width="40%" height="22">&nbsp;</td>
                                                </tr>
                                                <tr>
                                                    <td height="22">
                                                        <div align="right"><span class="STYLE1">密码:</span></div>
                                                    </td>
                                                    <td height="22">
                                                        <div align="left">
                                                            <%--                                                            密码--%>
                                                            <input name="pwd" type="password" size="15"
                                                                   style="height:20px; border:solid 1px #bbbbbb">
                                                        </div>
                                                    </td>
                                                    <td height="22">
                                                        <span class="STYLE1" style="color: red" hidden id="pwdmsg">用户名或密码不对！</span>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td height="22">
                                                        <div align="right"><span class="STYLE1">验证码:</span></div>
                                                    </td>
                                                    <td height="22">
                                                        <div align="left">
                                                            <div style="float: left">
                                                                <%--                                                                验证输入--%>
                                                                <input name="validate" type="text" size="4"
                                                                       maxlength="4"
                                                                       style="height:20px; border:solid 1px #bbbbbb">&nbsp;
                                                            </div>
                                                            <div style="float: left">
                                                                <img alt="" src="image.jsp" id="img">
                                                            </div>
                                                        </div>
                                                    </td>
                                                    <td height="22">
                                                        <div align="left" class="STYLE1">
                                                            <%--                                                            信息提示--%>
                                                            <span id="valimsg" class="STYLE1"  hidden></span>

                                                        </div>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td height="22"></td>
                                                    <td height="22">
                                                        <div align="left">
                                                            <img src="images/dl.gif" width="39" height="18" id="submit">
                                                        </div>
                                                    </td>
                                                    <td height="22"></td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div>

                                </td>
                            </tr>
                            <tr>
                                <td height="47">
                                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                        <tr>
                                            <td width="539" height="47" background="images/login_05.gif"
                                                nowrap="nowrap">&nbsp;
                                            </td>
                                            <td width="206" background="images/login_06.gif" nowrap="nowrap"></td>
                                            <td width="244" background="images/login_07.gif" nowrap="nowrap">&nbsp;</td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                            <tr>
                                <td height="212" background="images/login_08.gif">&nbsp;</td>
                            </tr>
                        </table>


                    </td>
                    <td background="images/login_04.gif">&nbsp;</td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td bgcolor="70ad21">&nbsp;</td>
    </tr>
</table>
</body>
</html>
