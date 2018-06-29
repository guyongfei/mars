var init = false;


var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#tx_table').bootstrapTable({
            url: contextPath + '/management/tx-infos',         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: true,                     //是否启用排序
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
            uniqueId: "projectGid",                     //每一行的唯一标识，一般为主键列
            showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: true,                    //是否显示详细视图
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
                    var pageSize = $('#tx_table').bootstrapTable('getOptions').pageSize;
                    //获取当前是第几页
                    var pageNumber = $('#tx_table').bootstrapTable('getOptions').pageNumber;
                    //返回序号，注意index是从0开始的，所以要加上1
                    return pageSize * (pageNumber - 1) + index + 1;
                }
            }, {
                field: 'projectToken',
                align: 'center',
                title: 'TOKEN'
            }, {
                field: 'userEmail',
                align: 'center',
                title: '邮箱'
            }, {
                field: 'payTxId',
                align: 'center',
                title: '交易编号'
            }, {
                field: 'payTx',
                align: 'center',
                title: '交易Hash'
            }, {
                field: 'createTime',
                align: 'center',
                title: '提交时间',
                formatter: timeFormatter
            }, {
                field: 'actualTxTime',
                align: 'center',
                title: '实际交易时间',
                formatter: timeFormatter
            }, {
                field: 'payEthAddress',
                align: 'center',
                title: '发送Eth地址'
            }, {
                field: 'platformAddress',
                align: 'center',
                title: '接受Eth地址'
            }, {
                field: 'payAmount',
                align: 'center',
                title: 'Eth数量'
            }, {
                field: 'actualSendingAddress',
                align: 'center',
                title: '实际发送Eth地址'
            }, {
                field: 'actualPayAmount',
                align: 'center',
                title: '实际Eth数量'
            }, {
                field: 'userTxStatus',
                align: 'center',
                title: '查询状态',
                formatter: userTxStatusFormatter
            }, {
                field: 'platformTxStatus',
                align: 'center',
                title: '打币状态',
                formatter: platformTxStatusFormatter
            }, {
                field: 'operate',
                title: '其他',
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
            pageNum: params.offset / params.limit + 1  //页码
        };

        if (projectGid) {
            temp.projectGid = projectGid;
        }
        var email = $('#email_').val().trim();
        if (email) {
            temp.email = email;
        }
        var payTxId = $('#payTxId_').val().trim();
        if (payTxId) {
            temp.payTxId = payTxId;
        }
        var payTx = $('#payTx_').val().trim();
        if (payTx) {
            temp.payTx = payTx;
        }
        var userTxStatus = $('#userTxStatus_ option:selected').val();

        if (userTxStatus) {
            temp.userTxStatus = userTxStatus;
        }
        var platformTxStatus = $('#platformTxStatus_ option:selected').val();
        if (platformTxStatus) {
            temp.platformTxStatus = platformTxStatus;
        }
        return temp;
    };
    return oTableInit;
};


var TableInit_ = function () {
    var oTableInit_ = new Object();
    //初始化Table
    oTableInit_.Init = function () {
        $('#error_table').bootstrapTable({
            url: contextPath + '/management/distribution-fail-infos',         //请求后台的URL（*）
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
            showRefresh: false,                  //是否显示刷新按钮
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
                    var pageSize = $('#error_table').bootstrapTable('getOptions').pageSize;
                    //获取当前是第几页
                    var pageNumber = $('#error_table').bootstrapTable('getOptions').pageNumber;
                    //返回序号，注意index是从0开始的，所以要加上1
                    return pageSize * (pageNumber - 1) + index + 1;
                }
            }, {
                field: 'status',
                align: 'center',
                title: '打币状态',
                formatter: userTxStatusFormatter
            }, {
                field: 'count',
                align: 'center',
                title: '数量'
            }, {
                field: 'status',
                title: '操作',
                align: 'center',
                valign: 'middle',
                events: operateEvents,
                formatter: operateFormatter
            }]
        });
    };
    //得到查询的参数
    oTableInit_.queryParams = function (params) {
        var temp = {};
        if (projectGid) {
            temp.projectGid = projectGid;
        }
        return temp;
    };
    return oTableInit_;
};


function userTxStatusFormatter(value, row, index) {
    var state = '--';
    var color = '#000';
    var color_green = 'btn-danger';
    switch (value) {
        case 0:
            state = '未查询';
            break;
        case 3:
            state = 'tx_not_exist:交易号不存在';
            break;
        case 11:
            state = 'not_to_platform：to地址不是平台地址';
            break;
        case 12:
            state = 'from_not_match：from地址与用户填写的不一致';
            break;
        case 13:
            state = 'eth_not_match：eth数量与用户填写的不一致';
            break;
        case 1:
            state = 'comfirmed：确认信息正确';
            break;
    }
    return [
        '<button class="btn ' + color + '" >' + state + '</button>'
    ].join('');
}
function platformTxStatusFormatter(value, row, index) {
    var state = '--';
    var color = '#000';
    var color_green = 'btn-danger';
    switch (value) {
        case 0:
            state = 'not_sending：未打币';
            break;
        case 3:
            state = 'sending：打币中';
            break;
        case 1:
            state = 'complete：打币完成';
            break;
        case 2:
            state = 'failed：打币失败';
            break;
    }
    return [
        '<button class="btn ' + color + '" >' + state + '</button>'
    ].join('');
}


$(function () {

    // select 2
    $(".js-project-data-ajax").select2({
        ajax: {
            url: contextPath + '/management/project/list',
            dataType: 'json',
            delay: 250,
            data: function (params) {
                return {
                    queryStr: params.term, // search term
                    pageSize: params.page
                };
            },
            processResults: function (data, params) {
                params.page = params.page || 1;
                return {
                    results: data.data.list,
                    pagination: {
                        more: (params.page * 10) < data.data.total
                    }
                };
            },
            cache: true
        },
        placeholder: 'Search for a repository',
        escapeMarkup: function (markup) {
            return markup;
        }, // let our custom formatter work
        minimumInputLength: 1,
        templateResult: formatRepo,
        templateSelection: formatRepoSelection
    });

    function formatRepo(repo) {
        if (repo.loading) {
            return repo.text;
        }

        var markup = "<div class='select2-result-repository clearfix'>" +
            "<div class='select2-result-repository__meta'>" +
            "<div class='select2-result-repository__title'>" + repo.projectToken + "</div>";

        markup += "<div class='select2-result-repository__statistics'>" +
            "</div>" +
            "</div></div>";
        return markup;
    }

    function formatRepoSelection(repo) {
        projectGid = repo.projectGid;
        if (projectGid && !init) {
            //加载table
            var oTable = new TableInit();
            oTable.Init();

            //加载table
            var oTable_ = new TableInit_();
            oTable_.Init();
        }
        reloadTable(1);
        //如果 项目状态已经开始则加载 交易记录表
        if (repo.projectToken && repo.projectStatus >= 1) {


        }
        //如果 项目状态为已完成则加载打币异常表
        if (repo.projectToken && repo.projectStatus >= 3) {


        }
        return repo.projectToken || repo.text;
    }

    ////////////////////////////////////以下为打币异常表/////////////////////////////////////////////


    ////////////////////////////////////以下为tx表/////////////////////////////////////////////


});

//重新加载表格
function reloadTable(pageNum) {
    $('#tx_table').bootstrapTable('refresh', {pageNumber: pageNum});
    $('#error_table').bootstrapTable('refresh', {pageNumber: pageNum});
}