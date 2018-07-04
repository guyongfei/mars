
var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#inner_table').bootstrapTable({
            url: contextPath + '/management/platform-tx-infos',         //请求后台的URL（*）
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
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 1,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
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
                title: 'TOKEN',
                visible: true
            }, {
                field: 'txHash',
                align: 'center',
                title: '交易号',
                visible: false
            }, {
                field: 'txType',
                align: 'center',
                title: '交易类型',
                visible: false
            }, {
                field: 'fromName',
                align: 'center',
                title: '发起方',
                visible: true
            }, {
                field: 'fromAddress',
                align: 'center',
                title: '发起方地址',
                visible: false
            }, {
                field: 'toName',
                align: 'center',
                title: '接收方',
                visible: true
            }, {
                field: 'toAddress',
                align: 'center',
                title: '接收方地址',
                visible: false
            }, {
                field: 'txTokenType',
                align: 'center',
                title: 'Token类型',
                visible: true
            }, {
                field: 'txAmount',
                align: 'center',
                title: '交易数额',
                visible: true
            }, {
                field: 'ethFee',
                align: 'center',
                title: '交易费用',
                visible: false
            }, {
                field: 'txStatus',
                align: 'center',
                title: '交易状态',
                formatter: platformTxStatusFormatter,
                visible: true
            }, {
                field: 'txTime',
                align: 'center',
                title: '交易时间',
                formatter: timeFormatter,
                visible: true
            }, {
                field: 'txVerificationTime',
                align: 'center',
                title: '验证时间',
                formatter: timeFormatter,
                visible: false
            }, {
                field: 'createTime',
                align: 'center',
                title: '创建时间',
                formatter: timeFormatter,
                visible: false
            }, {
                field: 'operate',
                title: '操作',
                align: 'center',
                valign: 'middle',
                events: operateEvents,
                formatter: operateFormatter,
                visible: true
            }]
        });
    };

    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = {
            pageSize: params.limit,   //页面大小
            pageNum: params.offset / params.limit + 1,  //页码
            txHash: $("#txHash_search").val().trim(), //模糊查询
            projectToken: $("#token_search").val().trim() //模糊查询
        };
        return temp;
    };
    return oTableInit;
};

function operateFormatter(value, row, index) {
    return [
        '<button type="button" id="delete"  class="platform-tx btn  btn-danger " txHash="' + row.txHash + '" >删除</button>'
    ].join('')
};
function platformTxStatusFormatter(value, row, index) {
    var state = platformTxStatusMap[value];
    var color = '#000';
    var color_green = 'btn-danger';
    return [
        '<button class="btn ' + color + '" >' + state + '</button>'
    ].join('');
}

window.operateEvents = {
    'click .platform-tx': function (e, value, row, index) {
        bootbox.confirm("确认要删除该交易", function (result) {
            if (result) {
                console.log(row);
                $.ajax({
                    url: contextPath + "/management/platform-tx-info/" + row.txHash,
                    type: "delete",
                    beforeSend: function () {
                        loadingIndex = layer.msg('处理中', {
                            icon: 16
                        });
                        return true;
                    },
                    success: function (data) {
                        console.log(data);
                        layer.close(loadingIndex);
                        if (data.success) {
                            layer.msg("任务成功", {
                                time: 1000,
                                icon: 1,
                                shift: 1
                            }, function () {
                                $('#inner_table').bootstrapTable('refresh');
                            })
                        } else {
                            layer.msg("任务失败，" + data.message, {
                                time: 2000,
                                icon: 0,
                                shift: 1
                            }, function () {
                            })
                        }
                    }
                })
            }
        });
    }
};

//查询按钮
$('#btn_query').click(function () {
    reloadTable(1);
});

$(function () {
    //加载table
    var oTable = new TableInit();
    oTable.Init();


    $('#btn_add').click(function () {
        $('#addModal').modal('show')
    });

    $('#addresses').on('hidden.bs.modal', function () {
        $(' #addresses').val('');
        $('#inner_table').bootstrapTable('refresh', {pageNumber: 1});
    });

    $('#addEvent').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        excluded: [':disabled'],
        fields: {
            txHashAdd: {
                validators: {
                    notEmpty: {
                        message: '交易号不能为空'
                    },
                    regexp: {
                        regexp: txHashReg,
                        message: '请输入正确的交易号'
                    }
                }
            }
        }
    }).on('success.form.bv', function (e) {
        // Prevent form submission
        e.preventDefault();
        var dataJson = {
            'txHash': $(' #txHashAdd').val()
        }
        $.ajax({
            url: contextPath + "/management/platform-tx-info",
            type: "post",
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(dataJson),
            beforeSend: function () {
                loadingIndex = layer.msg('处理中', {
                    icon: 16
                });
                return true;
            },
            success: function (data) {
                console.log(data);
                layer.close(loadingIndex);
                if (data.success) {
                    layer.msg("添加成功", {
                        time: 2000,
                        icon: 1,
                        shift: 1
                    }, function () {
                        $('#inner_table').bootstrapTable('refresh');
                        $('.close').click()
                    })
                } else {
                    layer.msg(data.message, {
                        time: 3000,
                        icon: 0,
                        shift: 1
                    }, function () {
                    })
                }
            }
        })
    });
})

