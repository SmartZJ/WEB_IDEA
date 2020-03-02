<%--
  Created by IntelliJ IDEA.
  User: SmartZ
  Date: 2020/2/21
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="lib/jquery-3.4.1.js"></script>

    <script>
        $(function(){
            $("#search").focus(ajax2);
            $("#search").blur(del);
        });
        function ajax(method,postdata,getdata) {
            var request;
            if(window.XMLHttpRequest){
                request=new XMLHttpRequest();
            }else if(window.ActiveXObject){
                request=new ActiveXObject("Msxml2.XMLHTTP");
            }
            if (method=="get"){
                request.open(method,"ajaxServlet?"+getdata);
                request.send(null);
            }else {
                request.open(method,"ajaxServlet");
                request.setRequestHeader("content-type","application/x-www-form-urlencoded");
                request.send(postdata);
            }
            request.onreadystatechange=function(){
                //获取服务器状态
                var state=request.readyState;
                //数据接收完成
                if(state==4){
                    //获取服务器的状态码
                    var status=request.status;
                    if(status==200){
                        //获取服务器响应的内容
                        var result=request.responseText;
                        return result;
                    }else if(status==404){
                        alert("找不到服务器资源");
                    }else if(status==500){
                        alert("服务器报错");
                    }
                }
            };
        }

        function ajax2() {
            var request;
            if(window.XMLHttpRequest){
                request=new XMLHttpRequest();
            }else if(window.ActiveXObject){
                request=new ActiveXObject("Msxml2.XMLHTTP");
            }
            request.open("post","ajaxServlet");
            request.setRequestHeader("content-type","application/x-www-form-urlencoded");
            request.send("uname="+$("#uname").val());



            //接收响应
            request.onreadystatechange=function(){
                //获取服务器状态
                var state=request.readyState;
                //数据接收完成
                if(state==4){
                    //获取服务器的状态码
                    var status=request.status;
                    if(status==200){
                        //获取服务器响应的内容
                        var result=request.responseText;
                        console.log(result);
                        eval("var list ="+result);
                        for (var i=0;i<list.length;i++){
                            $("#list").append($("<div></div>").append(list[i]).mousedown(function () {
                                $("#search").val($(this).html());
                            }).mouseover(function () {
                                $(this).css("background-color","gray");
                            }).mouseout(function () {
                                $(this).removeAttr("style")
                            }));
                        }

                    }else if(status==404){
                        alert("找不到服务器资源");
                    }else if(status==500){
                        alert("服务器报错");
                    }
                }
            };

        }

        function del() {
            $("#list").empty();
        }
    </script>
</head>
<body>

<div>

    <input type="text" id="search" style="width: 300px">
    <input type="submit" value="搜索">
    <div id="list" style="border: 1px solid blue;width: 298px" > </div>
</div>

</body>
</html>
