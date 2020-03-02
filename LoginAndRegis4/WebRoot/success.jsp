<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
      <base href="<%=basePath%>">
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
      <script src="jquery-3.4.1.js"></script>
      <style type="text/css">
          table{
              text-align: center;
          }
      </style>
      <script type="text/javascript">
          $(function () {
              $("#add").click(add);
              $("#del").click(del);
              // $(":checkbox:not([id=allcheck])").change(change);
              $("table").on("change",":checkbox:not([id=allcheck])",change);
              $("#allcheck").change(allcheck);

              $.ajax({
                  url:"employeeServlet?mark=query",
                  success:function (data) {
                        eval("var empList="+data);
                        for (let i=0;i<empList.length;i++){
                            let newTr = $("<tr></tr>");
                            let newTd = $("<td></td>").html("<input type='checkbox'>");
                            let newIdTd = $("<td></td>").html(empList[i].id);
                            let newNameTd = $("<td></td>").html(empList[i].name);
                            let newAgeTd = $("<td></td>").html(empList[i].age);
                            let newGanderTd = $("<td></td>").html(empList[i].gander==1?"男":"女");
                            newTr.append(newTd,newIdTd,newNameTd,newAgeTd,newGanderTd);
                            $("table").append(newTr);
                        }
                  }
              })
          });


          function add() {
              let newTr = $("<tr>" +
                  "<td><input type='checkbox' checked></td>" +
                  "<td><input type='text'></td>" +
                  "<td><input type='text'></td>" +
                  "<td><input type='text'></td>" +
                  "<td><input type='text'></td>" +
                  "</tr>");
              $("table").append(newTr);
          }
          function del() {
              $(":checked:not([id=allchec])").each(function (index,item) {
                  let id = $(item).closest("tr").children(":eq(1)").html();
                  console.log(id);
                  //上传数据
                  $.ajax({
                      url:"employeeServlet",
                      type:"get",
                      data:{mark:'delete',id:id},
                  });
              }).closest("tr").remove();
          }
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
                      mark = "revise"
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
          function allcheck(){
              let flag=$(this).prop("checked");
              $(":checkbox").prop("checked",flag)
          }
      </script>
      <title>员工管理系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <style type="text/css">
    </style>
  </head>
  
  <body>
  <div class="container">
    <h1>登陆成功，欢迎${sessionScope.uname}使用本系统!</h1>
    <hr/>
    <table  class="table table-striped">
        <tr>
            <td colspan="5">
                <h1>员工信息表</h1>
                <a id="add"><button>添加</button></a>
                <a id="del"><button>删除</button></a>
                <a href="echarts.html"><button>生成报表</button></a>
            </td>
        </tr>
    <tr>
        <td><input type="checkbox" id="allcheck">全选</td>
        <td>编号</td>
        <td>姓名</td>
        <td>年龄</td>
        <td>性别</td>
    </tr>
    </table>
    </div>

  </body>
</html>
