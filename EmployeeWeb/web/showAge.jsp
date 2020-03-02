<%--
  Created by IntelliJ IDEA.
  User: SmartZ
  Date: 2020/2/29
  Time: 2:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>报表</title>
    <script src="jquery-3.4.1.js"></script>
    <script src="echarts.min.js"></script>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;">

</div>
<script type="text/javascript">
    $(function () {
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));
        // 指定图表的配置项和数据
        var option = {
            title: {
                text: '年龄分布图'
            },
            tooltip: {},
            legend: {
                data:['人数']
            },
            xAxis: {
                data: [],

            },
            yAxis: {},
            series: [{
                name: '人数',
                type: 'bar',
                data: []
            }],
            color:"orange"
        };

        $.ajax({
            url:"employeeServlet?mark=queryAges",
            async:false,
            success:function(data){
                eval("var list="+data );
                console.log(list);
                for (let i=0 ; i<list.length;i++){
                    option.xAxis.data.push(list[i].ageArea);
                    option.series[0].data.push(list[i].count);
                }
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
                myChart.on('click', function (params) {
                    window.location.href = "employeeServlet?mark=queryByAges&curPage=1&Ages="+params.name;
                });
            }
        })
    })
</script>
</body>
</html>
