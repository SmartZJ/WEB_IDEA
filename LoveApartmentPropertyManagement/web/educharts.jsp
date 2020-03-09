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
    <script src="js/jquery-3.4.1.js"></script>
    <script src="js/echarts.min.js"></script>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div>
    <select id="buildsel">

    </select>
</div>

<div id="main" style="width: 800px;height:600px;">

</div>
<script type="text/javascript">
    $(function () {
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
                    if (i==0){
                        let newOption = $("<option></option>").html(buildlist[i].name).attr("value",buildlist[i].id).prop("selected",true);
                        $("#buildsel").append(newOption);
                    }else {
                        let newOption = $("<option></option>").html(buildlist[i].name).attr("value",buildlist[i].id);
                        $("#buildsel").append(newOption);
                    }
                }
            }
        })
        $("#buildsel").change(function () {
            $.ajax({
                url:"residentServlet?mark=queryCountEduByBuildId&buildId="+$(this).val(),
                async:false,
                success:function(data){

                    eval("var list="+data );
                    option.legend.data=[];
                    option.series[0].data=[];
                    for (let i=0 ; i<list.length;i++){
                        option.legend.data.push(list[i].name);
                        option.series[0].data.push({value:list[i].count,name:list[i].name});
                    }
                    myChart.setOption(option);
                }
            })
        });
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));
        // 指定图表的配置项和数据
        option = {
            tooltip: {
                trigger: 'item',
                formatter: '{a} <br/>{b}: {c} ({d}%)'
            },
            legend: {
                orient: 'vertical',
                left: 10,
                data: []
            },
            series: [
                {
                    name: '学历信息',
                    type: 'pie',
                    radius: ['30%', '70%'],
                    avoidLabelOverlap: false,
                    label: {
                        normal: {
                            show: false,
                            position: 'center'
                        },
                        emphasis: {
                            show: true,
                            textStyle: {
                                fontSize: '30',
                                fontWeight: 'bold'
                            }
                        }
                    },
                    labelLine: {
                        normal: {
                            show: false
                        }
                    },
                    data: []
                }
            ]
        };

        $.ajax({
            url:"residentServlet?mark=queryCountEduByBuildId&buildId=1",
            async:false,
            success:function(data){

                eval("var list="+data );
                for (let i=0 ; i<list.length;i++){
                    option.legend.data.push(list[i].name);
                    option.series[0].data.push({value:list[i].count,name:list[i].name});
                }
                myChart.setOption(option);
            }
        })
    })
</script>
</body>
</html>
