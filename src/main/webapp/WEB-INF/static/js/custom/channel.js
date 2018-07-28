var channelAddMethod = true;
var channelGid = '';

//重新加载表格
function reloadChannelTable(pageNum) {
    $('#channel_table').bootstrapTable('refresh', {pageNumber: pageNum});
}
var TableInitChannel = function () {
    var oTableInitChannel = new Object();
    //初始化Table
    oTableInitChannel.Init = function () {
        $('#channel_table').bootstrapTable({
            url: contextPath + '/management/channels',         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: oTableInitChannel.queryParams,//传递参数（*）
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
            uniqueId: "channelGid",                     //每一行的唯一标识，一般为主键列
            showToggle: false,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            responseHandler: function (res) {
                if (res.data) {
                    return {
                        "total": res.data.total,//总页数
                        "rows": res.data.list   //数据
                    };
                } else {
                    return {
                        "total": 0,//总页数
                        "rows": []   //数据
                    };
                }

            },
            columns: [{
                title: '序号',
                align: "center",
                valign: 'middle',
                formatter: function (value, row, index) {
                    //获取每页显示的数量
                    var pageSize = $('#channel_table').bootstrapTable('getOptions').pageSize;
                    //获取当前是第几页
                    var pageNumber = $('#channel_table').bootstrapTable('getOptions').pageNumber;
                    //返回序号，注意index是从0开始的，所以要加上1
                    return pageSize * (pageNumber - 1) + index + 1;
                }
            }, {
                field: 'name',
                align: 'center',
                valign: 'middle',
                title: '名称'
            }, {
                field: 'channel',
                align: 'center',
                valign: 'middle',
                title: '渠道号'
            }, {
                field: 'channel',
                align: 'center',
                valign: 'middle',
                title: '首页链接',
                formatter: channelLinkFormatter
            }, {
                field: 'note',
                width: '20%',
                align: 'center',
                title: '备注',
                formatter: channelNoteFormatter
            }, {
                field: 'registerCount',
                align: 'right',
                valign: 'middle',
                title: '注册人数',
                formatter: channelRegisterCountFormatter
            }, {
                field: 'channel',
                align: 'center',
                valign: 'middle',
                title: '操作',
                formatter: channelOperatorFormatter
            }]
        });
    };


    //得到查询的参数
    oTableInitChannel.queryParams = function (params) {
        var temp = {
            pageSize: params.limit,   //页面大小
            pageNum: params.offset / params.limit + 1  //页码
        };
        var queryStr = $('#channelSearch').val();
        if (queryStr || queryStr === '0') {
            temp.queryStr = queryStr;
        }
        return temp;
    };
    return oTableInitChannel;
};


function channelLinkFormatter(value, row, index) {
    return [
        '<label>' + frontPath + '?channel=' + value + '</label>'
    ].join('');
}
function channelNoteFormatter(value, row, index) {
    return [
        '<textarea rows="3" style="width: 100%;" disabled>' + value + '</textarea>'
    ].join('');
}

function channelRegisterCountFormatter(value, row, index) {
    return [
        '<label >' + value + "/" + row.totalRegisterCount + '</label>'
    ].join('');
}

function channelOperatorFormatter(value, row, index) {

    var arr = [];
    arr.push('<button type="button" id="channelEdit"  style="margin: 0 10px 0 0 "  channelGid="' + row.channelGid + '" channel="' + row.channel + '" name="' + row.name + '" note="' + row.note + '" onclick="editChannel($(this))" class="btn btn-primary  channelEdit" ><i class="fa fa-send " aria-hidden="true" ></i>编辑</button>');
    arr.push('<button type="button" id="channelDelete"  style="margin: 0 10px 0 0 "  channelGid="' + row.channelGid + '" onclick="deleteChannel($(this))"   class="btn btn-danger  channelDelete" ><i class="fa fa-send " aria-hidden="true" ></i>删除</button>');
    return arr.join('')
}

function editChannel(obj) {
    channelGid = $(obj).attr('channelGid');
    $('#channelShow').show();
    $('#channelAdd').hide();
    $('#channel').val($(obj).attr('channel'));
    $('#channelShow').html($(obj).attr('channel'));
    $('#channelName').val($(obj).attr('name'));
    $('#channelNote').val($(obj).attr('note'));
    $('#channelInfoModal').modal('show');
    channelAddMethod = false;
}


function deleteChannel(obj) {
    channelId = $(obj).attr('channelId');
    bootbox.confirm("删除渠道后，既有用户及交易数据将不会纳入统计", function (result) {
        if (result) {
            $.ajax({
                url: contextPath + "/management/channel/" + channelGid,
                type: "DELETE",
                contentType: "application/json;charset=UTF-8",
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
                        });
                        $('#channel_table').bootstrapTable('refresh');
                    } else {
                        layer.msg("任务失败，" + data.message, {
                            time: 2000,
                            icon: 0,
                            shift: 1
                        });
                        $('#channel_table').bootstrapTable('refresh');
                    }

                }
            })
        } else {
            $('#channel_table').bootstrapTable('refresh');
        }

    })

}


//新增按钮
$('#channelAddBtn').click(function () {
    $('#channelInfoModal').modal('show');
    channelAddMethod = true;
});

//隐藏模态框
$('#channelInfoModal').on('hidden.bs.modal', function () {
    // 表单清空
    $('#channelInfoEvent')[0].reset();
    channelAddMethod = true;
    //重新加载表格
    reloadChannelTable(1);
    $('#channel').val('');
    $('#channelAdd').show();
    $('#channelName').val('');
    $('#channelNote').val('');
    $('#channelShow').hide()
    $('#channelInfoEvent').bootstrapValidator("resetForm", true);

})

//查询按钮
$('#channelQueryBtn').click(function () {
    reloadChannelTable(1);
});


$(function () {

    //加载统计table
    var oTableChannel = new TableInitChannel();
    oTableChannel.Init();
    //表格加载完成
    $('#channel_table').on('load-success.bs.table', function (data) {
        $("td,th").addClass("text-center");
    });

    //渠道新增
    $('#channelInfoEvent').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        excluded: [':disabled'],
        fields: {
            channel: {
                validators: {
                    notEmpty: {
                        message: '渠道号不能为空'
                    },
                    regexp: {
                        regexp: /^[0-9a-zA-Z]{6}$/,
                        message: '请输入数字或字母的组合，长度为6'
                    }
                }
            },
            channelName: {
                validators: {
                    notEmpty: {
                        message: '渠道名不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 10,
                        message: '请输入1-10个字符'
                    }
                }
            },
            channelNote: {
                validators: {
                    notEmpty: {
                        message: '备注不能为空'
                    },
                    stringLength: {
                        max: 250,
                        message: '请输入250个字符以下的备注'
                    }
                }
            }
        }
    }).on('success.form.bv', function (e) {
        // Prevent form submission
        e.preventDefault();
        // Get the form instance
        var $form = $(e.target);
        // Get the BootstrapValidator instance
        var bv = $form.data('bootstrapValidator');
        // Use Ajax to submit form data
        var dataJson = {
            'channel': $(' #channel').val(),
            'name': $(' #channelName').val(),
            'note': $(' #channelNote').val(),
            'channelGid': channelGid
        };
        console.log(dataJson);
        var requestType = channelAddMethod ? "post" : "put";
        $.ajax({
            url: contextPath + "/management/channel",
            type: requestType,
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
                    layer.msg("任务成功", {
                        time: 2000,
                        icon: 1,
                        shift: 1
                    }, function () {
                        $('#channelInfoModalCloseBtn').click();
                        reloadChannelTable(1);
                    })
                } else {
                    layer.msg(data.message, {
                        time: 2000,
                        icon: 0,
                        shift: 1
                    }, function () {
                    })
                }
            }
        })
    });
});
