var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#inner_table').bootstrapTable({
            url: contextPath + '/management/statistics',         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: oTableInit.queryParams,//传递参数（*）
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: false,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 1,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
//                height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "projectGid",                     //每一行的唯一标识，一般为主键列
            showToggle: false,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            responseHandler: function (res) {
                return {
                    "total": res.data.total,//总页数
                    "rows": res.data.list   //数据
                };
            },
            columns: [{
                title: '序号',
                align: "center",
                formatter: function (value, row, index) {
                    //获取每页显示的数量
                    var pageSize = $('#inner_table').bootstrapTable('getOptions').pageSize;
                    //获取当前是第几页
                    var pageNumber = $('#inner_table').bootstrapTable('getOptions').pageNumber;
                    //返回序号，注意index是从0开始的，所以要加上1
                    return pageSize * (pageNumber - 1) + index + 1;
                }
            }, {
                field: 'projectToken',
                align: 'center',
                title: 'TOKEN'
            }, {
                field: 'getEthAmount',
                align: 'center',
                title: 'ETH数量'
            }, {
                field: 'actualGetEthAmount',
                align: 'center',
                title: '有效ETH'
            }, {
                field: 'payTokenAmount',
                align: 'center',
                title: 'Token数量'
            }, {
                field: 'actualPayTokenAmount',
                align: 'center',
                title: '有效Token'
            }, {
                field: 'userCount',
                align: 'center',
                title: '用户数量'
            }, {
                field: 'actualUserCount',
                align: 'center',
                title: '有效用户'
            }, {
                field: 'txCount',
                align: 'center',
                title: '申请数量'
            }, {
                field: 'actualTxCount',
                align: 'center',
                title: '有效申请'
            }, {
                field: 'operate',
                title: '详情',
                align: 'center',
                valign: 'middle',
                events: operateEvents,
                formatter: operateFormatter
            }]
        });
    };

    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = {
            pageSize: params.limit,   //页面大小
            pageNum: params.offset / params.limit + 1,  //页码
            queryStr: $("#txt_search").val().trim() //模糊查询
        };
        return temp;
    };
    return oTableInit;
};

function operateFormatter(value, row, index) {
    return [
        '<button type="button" id="editRow1"  class="statistic btn  btn-primary " projectGid="' + row.projectGid + '" >详情</button>'
    ].join('')
};


window.operateEvents = {
    'click .statistic': function (e, value, row, index) {
        $('#statisticProjectToken').html(row.projectToken);
        $('#statisticProjectGid').val(row.projectGid);
        console.log($("#statisticProjectGid").val())
        reloadStatisticTable(1);
        $('#statisticModal').modal('show');
    }
};


//重新加载表格
function reloadStatisticTable(pageNum) {
    $('#inner_table').bootstrapTable('refresh', {pageNumber: pageNum});
}

//查询按钮
$('#btn_query').click(function () {
    reloadTable(1);
});


//重新加载表格
function reloadStatisticTable(pageNum) {
    $('#statistic_table').bootstrapTable('refresh', {pageNumber: pageNum});
}

var TableInit_ = function () {
    var oTableInit_ = new Object();
    //初始化Table
    oTableInit_.Init = function () {
        $('#statistic_table').bootstrapTable({
            url: contextPath + '/management/statistic',         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: oTableInit_.queryParams,//传递参数（*）
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: false,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 1,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
//                height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "projectGid",                     //每一行的唯一标识，一般为主键列
            showToggle: false,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            responseHandler: function (res) {
                return {
                    "total": res.data.total,//总页数
                    "rows": res.data.list   //数据
                };
            },
            columns: [{
                title: '序号',
                align: "center",
                formatter: function (value, row, index) {
                    //获取每页显示的数量
                    var pageSize = $('#statistic_table').bootstrapTable('getOptions').pageSize;
                    //获取当前是第几页
                    var pageNumber = $('#statistic_table').bootstrapTable('getOptions').pageNumber;
                    //返回序号，注意index是从0开始的，所以要加上1
                    return pageSize * (pageNumber - 1) + index + 1;
                }
            }, {
                field: 'getEthAmount',
                align: 'center',
                title: 'ETH数量'
            }, {
                field: 'actualGetEthAmount',
                align: 'center',
                title: '有效ETH'
            }, {
                field: 'payTokenAmount',
                align: 'center',
                title: 'Token数量'
            }, {
                field: 'actualPayTokenAmount',
                align: 'center',
                title: '有效Token'
            }, {
                field: 'userCount',
                align: 'center',
                title: '用户数量'
            }, {
                field: 'actualUserCount',
                align: 'center',
                title: '有效用户'
            }, {
                field: 'txCount',
                align: 'center',
                title: '申请数量'
            }, {
                field: 'actualTxCount',
                align: 'center',
                title: '有效申请'
            }, {
                field: 'currentDay',
                align: 'center',
                title: '日期',
                formatter: dayFormatter
            }]
        });
    };

    //得到查询的参数
    oTableInit_.queryParams = function (params) {
        var temp = {
            pageSize: params.limit,   //页面大小
            pageNum: params.offset / params.limit + 1  //页码
        };
        var projectGid = $("#statisticProjectGid").val().trim();
        if (projectGid) {
            temp.projectGid = projectGid;
        }
        return temp;
    };
    return oTableInit_;
};


$(function () {
    //加载table
    var oTable = new TableInit();
    oTable.Init();

    //加载table
    var oTable_ = new TableInit_();
    oTable_.Init();

})

