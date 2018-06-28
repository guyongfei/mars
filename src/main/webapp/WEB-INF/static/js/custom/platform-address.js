var TableInit = function () {
    var oTableInit = new Object();
    oTableInit.Init = function () {
        $('#inner_table').bootstrapTable({
            url: contextPath + '/management/platform-addresses',         //请求后台的URL（*）
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
            showRefresh: false,                  //是否显示刷新按钮
            minimumCountColumns: 1,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
            showToggle: false,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
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
                field: 'address',
                align: 'center',
                title: '地址'
            }, {
                field: 'operate',
                title: '操作',
                align: 'center',
                events: operateEvents,
                formatter: operateFormatter
            }],
            responseHandler: function (res) {
                return {
                    "total": res.data.total,//总页数
                    "rows": res.data.list   //数据
                };
            }
        });
    };
    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = {
            pageSize: params.limit,   //页面大小
            pageNum: params.offset / params.limit + 1  //页码
        };
        return temp;
    };
    return oTableInit;
};

window.operateEvents = {
    'click .aaa': function (e, value, row, index) {
        bootbox.confirm("确认提交", function (result) {
            if (result) {
                $.ajax({
                    url: contextPath + "/management/platform-address/" + row.address,
                    type: "delete",
                    contentType: "application/json;charset=UTF-8",
                    beforeSend: function () {
                        loadingIndex = layer.msg('处理中', {
                            icon: 16
                        });
                        return true;
                    },
                    success: function (data) {
                        layer.close(loadingIndex);
                        if (data.success) {
                            $('#inner_table').bootstrapTable('refresh', {pageNumber: 1});
                            layer.msg("操作成功", {
                                time: 1000,
                                icon: 1,
                                shift: 1
                            }, function () {
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
            }
        })
    }
};


function operateFormatter(value, row, index) {
    return [
        '<button type="button" id="aaa"  class="btn am-btn-danger  aaa" ><i class="fa fa-send " aria-hidden="true" ></i>删除</button>']
};

$('#btn_query').click(function () {
    $('#inner_table').bootstrapTable('refresh', {pageNumber: 1});
})



$(function () {
    var oTable = new TableInit();
    oTable.Init();
    $('#btn_add').click(function () {
        $('#addModal').modal('show')
    });
    $('#addModal').on('hidden.bs.modal', function () {
        $(' #words').val('');
        $('#inner_table').bootstrapTable('refresh', {pageNumber: 1});
    })


    $('#words').change(function () {
        var data = $(this).val();
        var arr = new Array(); //定义一数组
        arr = data.split("\n"); //字符分割
        var str = ''
        for (i = 0; i < arr.length; i++) {
            var word = arr[i].trim();
            if (word.length > 1) {
                str += arr[i] + "\n"; //分割后的字符输出
            }
        }
        $('#addEvent').bootstrapValidator("resetForm", true);
        $(this).val(str)
    })

    $('#addEvent').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        excluded: [':disabled'],
        fields: {
            words: {
                validators: {
                    notEmpty: {
                        message: '地址不能为空'
                    },
                    regexp: {
                        regexp: /^(0x\S{40}\s?)*$/,
                        message: '请输入正确的地址'
                    }
                }
            }
        }
    }).on('success.form.bv', function (e) {
        // Prevent form submission
        e.preventDefault();

        var dataJson = {
            'data': $(' #addresses').val()
        }
        $.ajax({
            url: contextPath + "/management/platform-address",
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
                        time: 1000,
                        icon: 1,
                        shift: 1
                    }, function () {
                        $('.close').click()
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


})
