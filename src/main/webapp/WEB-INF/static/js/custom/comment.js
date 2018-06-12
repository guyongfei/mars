var commentNow = {};
var commentNowId;
var commentNowLevel;


var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#inner_table').bootstrapTable({
            url: contextPath + '/management/comment/list',         //请求后台的URL（*）
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
//                height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
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
                field: 'token',
                align: 'center',
                title: 'TOKEN'
            }, {
                field: 'content',
                align: 'center',
                title: '评论内容'
            }, {
                field: 'operate',
                width: 150,
                title: '操作',
                align: 'center',
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
            contentLike: $("#txt_search").val().trim() //模糊查询
        };
        return temp;
    };
    return oTableInit;
};


window.operateEvents = {
    'click .editRow': function (e, value, row, index) {
        getProject(row)
    },
    'click .deleteRow': function (e, value, row, index) {
        deleteComment(row)
    }
};
function operateFormatter(value, row, index) {
    return [
        '<button type="button" id="editRow"  class="btn am-btn-primary  editRow" ><i class="fa fa-send " aria-hidden="true" ></i>编辑</button>' +
        '<button type="button" id="deleteRow"  class="btn am-btn-danger deleteRow "><i class="fa fa-send " aria-hidden="true" ></i>删除</button>'
    ]
};

//加载单个评论
function getProject(row) {
    console.log(row)
    commentNowId = row.id;
    commentNowLevel = row.commentLevel;
    commentNow = row;
    $(' #commentContent').val(row.content);
    $('#addModal').modal('show')

}

function deleteComment(row) {

    bootbox.confirm("确认删除吗", function (result) {
        if (result) {
            $.ajax({
                url: contextPath + "/management/comment/" + row.commentLevel + "/" + row.id,
                type: 'DELETE',
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
                            time: 2000,
                            icon: 1,
                            shift: 1
                        }, function () {
                            reloadTable();
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

/*
 function modifyComment() {


 if (!commentNow.content || commentNow.content.length > 300) {
 layer.msg("请输入1-300个字符的评论", {
 time: 2000,
 icon: 0,
 shift: 1
 }, function () {
 })
 return false;
 }


 }*/


//重新加载表格
function reloadTable(pageNum) {
    $('#inner_table').bootstrapTable('refresh', {pageNumber: pageNum});
}

//查询按钮
$('#btn_query').click(function () {
    reloadTable(1);
})
//隐藏模态框
$('#addModal').on('hidden.bs.modal', function () {
    $('#addEvent')[0].reset();
    commentNow = {};
    reloadTable(1);
    $('#addEvent').bootstrapValidator("resetForm", true);
})

$(function () {
    //加载table
    var oTable = new TableInit();
    oTable.Init();


    $('#addEvent').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        excluded: [':disabled'],
        fields: {
            commentContent: {
                validators: {
                    notEmpty: {
                        message: '评论不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 300,
                        message: '请输入1-300个字符'
                    }
                }
            }
        }
    }).on('success.form.bv', function (e) {

        e.preventDefault();
        bootbox.confirm("确认修改吗", function (result) {
            if (result) {
                commentNow.content = $('#commentContent').val();
                $.ajax({
                    url: contextPath + "/management/comment",
                    type: 'PUT',
                    contentType: "application/json;charset=UTF-8",
                    data: JSON.stringify(commentNow),
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
                                $('#addModal').modal('hide');
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


    });
});
