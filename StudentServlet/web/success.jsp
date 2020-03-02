<%--
  Created by IntelliJ IDEA.
  User: SmartZ
  Date: 2020/2/24
  Time: 17:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <script src="jquery-3.4.1.js"></script>
    <title>管理界面</title>
    <style>
        table{
            text-align: center;
            width: 100%;
        }
        td{
            width: 25%;
        }
    </style>
    <script>
        $(function () {

            $("td").on("click","button",function () {
                //取消不可选的按钮
                $("button:disabled").prop("disabled",false);
                //设置本按钮不可选
                $(this).prop("disabled",true);


                var request;
                if(window.XMLHttpRequest){
                    request=new XMLHttpRequest();
                }else if(window.ActiveXObject){
                    request=new ActiveXObject("Msxml2.XMLHTTP");
                }

                //设置当前请求的请求方式和请求地址
                request.open("get","studentServlet?mark=change&classNum="+$(this).val());
                //监听服务器响应状态的改变
                request.onreadystatechange=function(){
                    //获取服务器状态
                    var state=request.readyState;
                    if(state==4){
                        var status=request.status;
                        if(status==200){
                            var result=request.responseText;
                            eval("var studentArray="+result);
                            //删除原表数据
                            $("table tr:gt(2)").remove();
                            //添加新数据
                            for(let i=0; i<studentArray.length; i++){
                                let newTr = $("<tr></tr>");
                                newTr.append("<td>"+studentArray[i].classNum+"</td>");
                                newTr.append("<td>"+studentArray[i].id+"</td>");
                                newTr.append("<td>"+studentArray[i].name+"</td>");
                                newTr.append("<td>"+studentArray[i].age+"</td>");
                                $("table").append(newTr);
                            }
                        }
                    }
                };

                //给请求绑定参数
                request.send(null);
            })
        })
    </script>
</head>
<body>
    <h1>欢迎${uname}使用学生管理系统</h1>
    <hr>
    <div>
        <table border="1px">
            <tr>
                <td colspan="4">
                    学生表
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <button disabled value="1">一班</button>
                    <button value="2">二班</button>
                    <button value="3">三班</button>
                </td>
            </tr>
            <tr>
                <td>班级</td>
                <td>编号</td>
                <td>姓名</td>
                <td>年龄</td>
            </tr>
            <c:forEach items="${stuclass}" var="stu">
                <tr>
                    <td>${stu.classNum}</td>
                    <td>${stu.id}</td>
                    <td>${stu.name}</td>
                    <td>${stu.age}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
