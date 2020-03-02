<%--
  Created by IntelliJ IDEA.
  User: SmartZ
  Date: 2020/2/28
  Time: 18:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>主界面</title>
    <script src="jquery-3.4.1.js"></script>
    <script>
        $(function () {
            $("#del").click(del);
            $(":checkbox").change(change);
            $("#sl").change(function () {
                window.location="employeeServlet?mark=query&curPage="+$(this).val();
            })
            $("#opbn").click(function () {
                // window.location.href="employeeServlet?mark=query&curPage="+$(":selected").html();
                var pageNum;
                if ($("#pageNum").val()<${page.firstPage}){
                    pageNum = ${page.firstPage};
                }else if ($("#pageNum").val()>${page.lastPage}){
                    pageNum = ${page.lastPage}
                }else {
                    pageNum = $("#pageNum").val();
                }
                console.log(pageNum);
                window.location.href="employeeServlet?mark=query&curPage="+pageNum;
            })
            //删除
            function del() {
                $(":checked:not([id=allchec])").each(function (index,item) {
                    let id = $(item).closest("tr").children(":eq(1)").html();
                    console.log(id);
                    //上传数据
                    $.ajax({
                        url:"employeeServlet",
                        type:"get",
                        data:{mark:'del',id:id},
                    });
                }).closest("tr").remove();
            }
            //改
            function change() {
                let flag = $(this).prop("checked");
                if(flag){
                    //进入修改
                    $(this).closest("tr").children(":gt(1)").each(function (index,item) {
                        let str;
                        str = $(item).text();
                        $(item).html("<input type='text' value="+str+" >");
                    });
                }else {
                    //修改|添加标识
                    var mark
                    //获取数据
                    let name = $(this).closest("tr").children(":eq(2)").children(":first-child").val();
                    console.log(name);
                    let age = $(this).closest("tr").children(":eq(3)").children(":first-child").val();
                    console.log(age);
                    let gender =$(this).closest("tr").children(":eq(4)").children(":first-child").val()=="男"?1:0;
                    //判断修改还是添加
                    if ($(this).closest("tr").children(":eq(1)").children(":eq(0)").val()!=null){
                        //添加
                        mark = "add";
                        $(this).closest("tr").children(":gt(0)").each(function (index,item) {
                            let str;
                            str = $(item).children(":first-child").val();
                            $(item).html(str);
                        });
                    }else {
                        //修改
                        mark = "updata"
                        $(this).closest("tr").children(":gt(1)").each(function (index,item) {
                            let str;
                            str = $(item).children(":first-child").val();
                            $(item).html(str);
                        });
                    }
                    //获取id
                    let id = $(this).closest("tr").children(":eq(1)").html();
                    //上传数据
                    $.ajax({
                        url:"employeeServlet",
                        type:"post",
                        data:{mark:mark,id:id,name:name,age:age,gender:gender},
                    });
                }
            }
        })
    </script>
</head>
<body>
    <h1>欢迎用户&nbsp;${uname}&nbsp;使用员工管理系统</h1>
    <hr>
    <table border="1" width="700px" align="center">
        <tr>
            <td align="center" colspan="5">
                <h1>员工信息表</h1>
                <a href="addEmployee.jsp"><button id="add">添加员工</button></a>
                <a ><button id="del">删除员工</button></a>
                <a href='showAge.jsp'><button>年龄分布图</button></a>
            </td>
        </tr>
        <tr>
            <th></th>
            <th>编号</th>
            <th>姓名</th>
            <th>年龄</th>
            <th>性别</th>
        </tr>
        <c:forEach items="${list}" var="emp">
            <tr align="center">
                <td><input type="checkbox"></td>
                <td>${emp.id}</td>
                <td>${emp.name}</td>
                <td>${emp.age}</td>
                <td>${emp.gender==1?"男":"女"}</td>

            </tr>
        </c:forEach>
        <tr align="center">
            <td colspan="5">
                <c:if test="${page.curPage!=page.firstPage }">
                    <a href="employeeServlet?mark=query&curPage=${page.firstPage }"><button>首页</button></a>
                    <a href="employeeServlet?mark=query&curPage=${page.prevPage }"><button>上一页</button></a>
                </c:if>
                <c:forEach begin="${page.beginNav }" end="${page.endNav }" var="i">
                    <c:choose>
                        <c:when test="${page.curPage==i }">
                            <font color='red'>${i }</font>
                        </c:when>
                        <c:otherwise>
                            <a href="employeeServlet?mark=query&curPage=${i }">${i }</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${page.curPage!=page.lastPage }">
                    <a href="employeeServlet?mark=query&curPage=${page.nextPage }"><button>下一页</button></a>
                    <a href="employeeServlet?mark=query&curPage=${page.lastPage }"><button>尾页</button></a>
                </c:if>
                <select id="sl" size="1" style="overflow: hidden">
                <c:forEach begin="1" end="${page.lastPage}" var="i">
                    <c:choose>
                        <c:when test="${page.curPage==i }">
                            <option selected>第${i}页</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${i}">第${i}页</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                </select>

            </td>
        </tr>
        <tr>
            <td colspan="5">
                <input width="5px" id="pageNum" type="text">/${page.lastPage}
                <button id="opbn">跳转</button>
            </td>
        </tr>
    </table>
</body>

</html>
