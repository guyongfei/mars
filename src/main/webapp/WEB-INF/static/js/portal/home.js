$(function () {
    var called = $('#admin').attr('data-called');
    var task = $('.row').attr('data-task');
    var date = $('.today').eq(0).text();
    $('.today').click(function () {
        date = $(this).text();
    })
    $('.recently').click(function () {
        date = $(this).text();
    })
    function renderData(data) {
        if (!data) return;
        var dataAxis = data.xData;
        var seriesData = data.yData;
        var echartsA = echarts.init(document.getElementById('adminIndexPage'));
        var options = {
            tooltip: {
                trigger: 'axis'
            },
            grid: {
                left: '3%',
                right: '4%',
                containLabel: true
            },
            xAxis: {
                name: task,
                data: dataAxis,
                nameRoate: 30,
                type: 'category',
                axisLabel: {
                    interval: 0,
                    rotate: 45,
                    margin: 2,
                    textStyle: {
                        color: "#222"
                    }
                }
            },
            yAxis: {
                name: called,
                type: 'value'
            },
            series: [
                {
                    name: date,
                    type: 'bar',
                    stack: '总量',
                    barWidth: 30,
                    data: seriesData,
                    itemStyle: {
                        normal: {
                            color: function (params) {
                                var colorList = ['#E64853', '#F77135', '#F7B035', '#7CD6DF', '#7CD6DF',
                                    '#7CD6DF', '#7CD6DF', '#7CD6DF', '#7CD6DF', '#7CD6DF',
                                    '#7CD6DF', '#7CD6DF', '#7CD6DF', '#7CD6DF', '#7CD6DF'];
                                return colorList[params.dataIndex]
                            }
                        }
                    }
                }
            ]
        };
        echartsA.setOption(options);
    }

    if ($("#admin").length > 0) {
        get_Data('/portal/queryTaskOrder/1d', renderData);
    }
    //管理类表格数据
    function tableData(data) {
        if (data.successed) {
            var str = '';
            if (!data.accountList) return;
            data.accountList.forEach(function (t) {
                str += '<tr><td style="text-align:center;">' + t.accountName + '</td>' +
                    '<td style="text-align:center;">' + t.orgName + '</td>' +
                    '<td style="text-align:center;">' + (t.accountBalance / 100) + '</td>' +
                    '<td style="text-align:center;">' + t.accountCallCount + '</td></tr>';
            });
            $(".portal-table tbody").html(str);
        } else {
            alert(data.message);
        }
    }

    //管理类表格数据
    function userTableData(data) {
        if (data.successed) {
            var str = '';
            if (!data.taskInvokingList) return;
            data.taskInvokingList.forEach(function (t) {
                str += '<tr><td style="text-align:center;">' + t.taskType + '</td>' +
                    '<td style="text-align:center;">' + t.taskCallCount + '</td>';
                if (t.taskTypeCode == 'black') {
                    str += '<td><span>成功:</span><span>' + t.taskSuccCount + '</span><span>/命中:</span><span>' + t.taskMatchCount + '</span></td></tr>';
                } else {
                    str += '<td><span>成功:</span><span>' + t.taskSuccCount + '</span></td></tr>';
                }
            });
            $(".portal-table tbody").html(str);
        } else {
            alert(data.message);
        }
    }

    //日月选择
    $(".actions-btn li").on("click", function () {
        //管理
        if ($(this).parent().data("mark") === "taskType") {
            $(this).addClass("active").siblings().removeClass("active");
            var totalParamLimit = $(this).data("param");
            get_Data('/portal/queryTaskOrder/' + totalParamLimit, renderData);
        }
        if ($(this).parent().data("mark") === "product") {
            $(this).addClass("active").siblings().removeClass("active");
            var successParamLimit = $(this).data("param");
            getData('/portal/queryAccountOrder', 'POST', {activeAccount: {timeCode: successParamLimit}}, tableData);
        }
        //客户
        if ($(this).parent().data("mark") === "userTaskType") {
            $(this).addClass("active").siblings().removeClass("active");
            var userTotalParamLimit = $(this).data("param");
            get_Data('/portal/queryTaskRanking/' + userTotalParamLimit, userTableData);
        }
    });
});