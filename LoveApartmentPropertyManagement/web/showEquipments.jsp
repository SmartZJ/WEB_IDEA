<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>设备显示</title>
    <script src="js/jquery-3.4.1.js"></script>
    <style type="text/css">
        body {
            margin-left: 0px;
            margin-top: 0px;
            margin-right: 0px;
            margin-bottom: 0px;
        }
        .STYLE1 {
            font-size: 12px
        }
        .STYLE4 {
            font-size: 12px;
            color: #1F4A65;
            font-weight: bold;
        }
        a:link {
            font-size: 12px;
            color: #06482a;
            text-decoration: none;

        }
        a:visited {
            font-size: 12px;
            color: #06482a;
            text-decoration: none;
        }
        a:hover {
            font-size: 12px;
            color: #FF0000;
            text-decoration: underline;
        }
        a:active {
            font-size: 12px;
            color: #FF0000;
            text-decoration: none;
        }
    </style>
    <script>
        $(function () {
            $(".adel").click(function () {
                <%--equipmentServlet?mark=delEquipment&curPage=${page.curPage}&--%>
                var result = window.confirm("确定删除该设备？");
                if (result){
                    location.href = "equipmentServlet?mark=delEquipment&curPage=${page.curPage}&id="+$(this).attr("value");
                }
            })
            //全选
            $("#allcheck").click(function () {
                $(":checkbox").prop("checked",true);
            });
            //全不选
            $("#allrecheck").click(function () {
                $(":checkbox").prop("checked",false);
            });
            //反选
            $("#recheck").click(function () {
                $(":checkbox").each(function (index,element) {
                    if ($(element).prop("checked")==true){
                        $(element).prop("checked",false);
                    }else {
                        $(element).prop("checked",true);
                    }
                })
            });
            //选择删除
            $("#delbtn").click(function () {
                $(":checkbox").each(function (index,element) {
                    if ($(element).prop("checked")==true){
                        console.log("equipmentServlet?mark=delEquipment&curPage=${page.curPage}&id="+$(element).val())
                        $.ajax({
                            url: "equipmentServlet?mark=delEquipment&curPage=${page.curPage}&id="+$(element).val(),
                            type:"get",
                            success:function () {
                                window.location.href="equipmentServlet?mark=showEquipments&curPage="+${page.curPage};
                            }
                        })
                    }
                })
            });
            $("#qureybtn").click(function () {
                location.href = "equipmentServlet?mark=queryEquipmentByOption&count=${page.count}&curPage=${page.curPage}&flag="+$("#querysel").val()+"&value="+$("#queryvalue").val();
            })

        })
    </script>
</head>
<body>

<div>
    <select id="querysel">
        <option value="0">按设备名称查询</option>
        <option value="1">按编号查询</option>
    </select>
    <input type="text" id="queryvalue">
    <button id="qureybtn">查询</button>
</div>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
        <td height="30">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td width="15" height="30">
                    <img src="tab/images/tab_03.gif" width="15" height="30" />
                </td>
                <td background="tab/images/tab_05.gif">
                    <img src="tab/images/311.gif" width="16" height="16" />
                    <span class="STYLE4">住户列表</span>
                    <a href="addEquipment.jsp"><button>添加</button></a>
                    <button id="allcheck">全选</button>
                    <button id="allrecheck">全不选</button>
                    <button id="recheck">反选</button>
                    <button id="delbtn">选择删除</button>
                </td>
                <td width="14">
                    <img src="tab/images/tab_07.gif" width="14" height="30" />
                </td>
            </tr>
        </table>
        </td>
    </tr>
    <tr>
        <td>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td width="9" background="tab/images/tab_12.gif">&nbsp;</td>
                <td bgcolor="e5f1d6"><table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#CECECE">
                    <tr>
                        <td width="5%" height="26" background="tab/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE1">选择</div></td>
                        <td width="5%" height="18" background="tab/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE1">序号</div></td>
                        <td width="5%" height="18" background="tab/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE1">设备名</div></td>
                        <td width="5%" height="18" background="tab/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE1">设备编号</div></td>
                        <td width="17%" height="18" background="tab/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE1">设备启用时间</div></td>
                        <td width="15%" height="18" background="tab/images/tab_14.gif" class="STYLE1"><div align="center" >设备状态</div></td>
                        <td width="16%" height="18" background="tab/images/tab_14.gif" class="STYLE1"><div align="center" >设备类型</div></td>
                        <td width="7%" height="18" background="tab/images/tab_14.gif" class="STYLE1"><div align="center" >设备负责人</div></td>
                        <td width="15%" height="18" background="tab/images/tab_14.gif" class="STYLE1"><div align="center" >设备维修人</div></td>
                        <td width="5%" height="18" background="tab/images/tab_14.gif" class="STYLE1"><div align="center" >编辑</div></td>
                        <td width="5%" height="18" background="tab/images/tab_14.gif" class="STYLE1"><div align="center" >删除</div></td>
                    </tr>
                <c:forEach var="item" items="${list}">
                    <tr>
                        <td height="18" bgcolor="#FFFFFF">
                            <div align="center" class="STYLE1">
                                <input name="checkbox" type="checkbox" class="STYLE2" value="${item.id}" />
                            </div>
                        </td>
                        <td height="18" bgcolor="#FFFFFF" class="STYLE2">
                            <div align="center" class="STYLE2 STYLE1">
                                <a href="" style="text-decoration: none">${item.id}</a>
                            </div>
                        </td>
                        <td height="18" bgcolor="#FFFFFF">
                            <div align="center" class="STYLE2 STYLE1">${item.ename}</div>
                        </td>
                        <td height="18" bgcolor="#FFFFFF">
                            <div align="center" class="STYLE2 STYLE1">${item.number}</div>
                        </td>
                        <td height="18" bgcolor="#FFFFFF">
                            <div align="center" class="STYLE2 STYLE1">${item.startTime}</div>
                        </td>
                        <td height="18" bgcolor="#FFFFFF">
                            <div align="center" >${item.state==1?"可用":"不可用"}</div>
                        </td>
                        <td height="18" bgcolor="#FFFFFF">
                            <div align="center" >${item.type==1?"流动资产":"固定资产"}</div>
                        </td>
                        <td height="18" bgcolor="#FFFFFF">
                            <div align="center" >${item.buyer}</div>
                        </td>
                        <td height="18" bgcolor="#FFFFFF">
                            <div align="center" >${item.maintainer}</div>
                        </td>
                        <td height="18" bgcolor="#FFFFFF">
                            <div align="center">
                                <img src="tab/images/037.gif" width="9" height="9" />
                                <span class="STYLE1"> [</span>

                                <a href="equipmentServlet?mark=qureyEquipmentById&id=${item.id}&curPage=${page.curPage}">编辑</a>

                                <span class="STYLE1">]</span>
                            </div>
                        </td>
                        <td height="18" bgcolor="#FFFFFF">
                            <div align="center">
                                <span class="STYLE2">
                                    <img src="tab/images/010.gif" width="9" height="9" />
                                </span>
                                <span class="STYLE1" >[</span>

                                <a class="adel" value="${item.id}">删除</a>

                                <span class="STYLE1">]</span>
                            </div>
                        </td>
                    </tr>
                </c:forEach>



                </table>
                </td>
                <td width="9" background="tab/images/tab_16.gif">&nbsp;</td>
            </tr>
        </table></td>
    </tr>
    <tr>
        <td height="29">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td width="15" height="29"><img src="tab/images/tab_20.gif" width="15" height="29" /></td>
                <td background="tab/images/tab_21.gif">
                    <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="40%">
                            <div align="left">
                                <span class="STYLE1">共${page.count}条纪录，当前第${page.curPage}/${page.pageCount}页，每页${page.pageRow}条纪录</span>
                            </div>
                        </td>
                        <td width="60%" class="STYLE1">&nbsp;
                            <jsp:include page="PageUtil.jsp">
                                <jsp:param value="equipmentServlet?mark=showEquipments" name="url"/>
                            </jsp:include>
                        </td>
                    </tr>
                </table>
                </td>
                <td width="14"><img src="tab/images/tab_22.gif" width="14" height="29" /></td>
            </tr>
        </table>
        </td>
    </tr>
</table>
</body>
</html>
