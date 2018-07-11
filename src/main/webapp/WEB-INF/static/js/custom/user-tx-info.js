var init = false;
var userTxStatus;
var txId;
var platformTxStatus;
var loadTableTime;


var oTableInit = new Object();

var TableInit = function () {
    //初始化Table
    oTableInit.Init = function () {
        $('#tx_table').bootstrapTable({
            url: contextPath + '/management/user-tx-infos',         //请求后台的URL（*）
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
            pageSize: 5,                       //每页的记录行数（*）
            pageList: [5, 10, 50,100,200],        //可供选择的每页的行数（*）
            search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 1,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
//                height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "projectGid",                     //每一行的唯一标识，一般为主键列
            showToggle: false,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            showColumns: true,
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
                field: 'userEmail',
                align: 'center',
                title: '邮箱',
                visible: false
            }, {
                field: 'payTxId',
                align: 'center',
                title: '交易编号'
            }, {
                field: 'payTx',
                align: 'center',
                halign: 'right',
                valign: 'left',
                title: '交易Hash',
                visible: false
            }, {
                field: 'createTime',
                align: 'center',
                title: '认购提交时间',
                formatter: timeFormatter,
                visible: false
            }, {
                field: 'actualTxTime',
                align: 'center',
                title: '实际交易时间',
                formatter: timeFormatter,
                visible: false
            }, {
                field: 'payEthAddress',
                align: 'center',
                title: '发送Eth地址',
                visible: false
            }, {
                field: 'platformAddress',
                align: 'center',
                title: '接受Eth地址',
                visible: false
            }, {
                field: 'payAmount',
                align: 'center',
                title: 'Eth数量'
            }, {
                field: 'actualSendingAddress',
                align: 'center',
                title: '实际发送Eth地址',
                visible: false
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
                events: txTableEvents,
                formatter: txTableFormatter
            }]
        });
    };

    return oTableInit;
};
//得到查询的参数
oTableInit.queryParams = function (params) {
    var temp = {};
    var pageSize = params.limit;
    if (pageSize) {
        temp.pageSize = pageSize;//页面大小
        temp.pageNum = params.offset / pageSize + 1  //页码
    }

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
window.txTableEvents = {};

function txTableFormatter(value, row, index) {
    var userTxStatus = parseInt(row.userTxStatus);
    var platformTxStatus = parseInt(row.platformTxStatus);
    if (((platformTxStatus == 3 || platformTxStatus == 0) && (userTxStatus == 2 || userTxStatus == 22 || userTxStatus == 23)) || (userTxStatus == 2 && platformTxStatus === 0)) {
        return [
            '<button type="button"   onclick="distribution(null,' + row.payTxId + ',null)" class="distribution  btn  btn-primary " " >打币</button>'
        ].join('')
    }
    return "";
}


var TableInit_tx_status = function () {
    var oTableInit_tx_status = new Object();
    //初始化Table
    oTableInit_tx_status.Init = function () {
        $('#tx_status_table').bootstrapTable({
            url: contextPath + '/management/tx-status-infos',         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            toolbar: "toolbar_",
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: oTableInit.queryParams,//传递参数（*）
            // queryParams: oTableInit_tx_status.queryParams,//传递参数（*）
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: false,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 1,             //最少允许的列数
            clickToSelect: false,                //是否启用点击选中行
//                height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "userTxStatus",                     //每一行的唯一标识，一般为主键列
            showToggle: false,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            checkboxHeader: false,
            responseHandler: function (res) {
                console.log(res)
                return {
                    "total": 10,//总页数
                    "rows": res.data.list   //数据
                };
            },
            columns: [{
                field: 'userTxStatus',
                align: 'center',
                title: '查询状态',
                width: '50%',
                formatter: userTxStatusFormatter
            }, {
                field: 'count',
                align: 'center',
                title: '数量',
                width: '20%',
                formatter: errorCountFormatter
            }, {
                field: 'needDistributeCount',
                title: '操作',
                align: 'center',
                valign: 'middle',
                events: distributionEvents,
                formatter: distributionUserTxFormatter
            }],

            formatShowingRows: function (pageFrom, pageTo, totalRows) {
                return '';
            },
        });
        //得到查询的参数
        oTableInit_tx_status.queryParams = function (params) {
            var temp = {};
            var pageSize = params.limit;
            if (pageSize) {
                temp.pageSize = pageSize;//页面大小
                temp.pageNum = params.offset / pageSize + 1  //页码
            }
            alert(projectGid)

            if (projectGid) {
                temp.projectGid = projectGid;
            }
            return temp;
        };
    };
    return oTableInit_tx_status;
};

var TableInit_ = function () {
    var oTableInit_ = new Object();
    //初始化Table
    oTableInit_.Init = function () {
        $('#error_table').bootstrapTable({
            url: contextPath + '/management/distribution-infos',         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            toolbar: "toolbar_",
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: oTableInit.queryParams,//传递参数（*）
            // queryParams: oTableInit_.queryParams,//传递参数（*）
            sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: false,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 1,             //最少允许的列数
            clickToSelect: false,                //是否启用点击选中行
//                height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "projectGid",                     //每一行的唯一标识，一般为主键列
            showToggle: false,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            checkboxHeader: false,
            responseHandler: function (res) {
                return {
                    "total": res.data.total,//总页数
                    "rows": res.data.list   //数据
                };
            },
            columns: [{
                field: 'platformTxStatus',
                align: 'center',
                title: '打币状态',
                width: '50%',
                formatter: platformStatusFormatter
            }, {
                field: 'count',
                align: 'center',
                title: '数量',
                width: '20%',
                formatter: platformCountFormatter
            }, {
                field: 'platformTxStatus',
                title: '操作',
                align: 'center',
                valign: 'middle',
                events: distributionEvents,
                formatter: distributionPlatFormatter
            }],
            formatShowingRows: function (pageFrom, pageTo, totalRows) {
                return '';
            }
        });
    };
    return oTableInit_;
};

function statePlatFormatter(value, row, index) {
    if (parseInt(row.platformTxStatus) != 0) {
        return {
            disabled: true
        };
    }
    return value;
}
function stateFormatter(value, row, index) {
    var userTxStatus = row.userTxStatus;
    if (!(userTxStatus == 2 || userTxStatus == 22 || userTxStatus == 23)) {
        return {
            disabled: true
        };
    }
    return value;
}


function distributionUserTxFormatter(value, row, index) {
    value = parseInt(value);
    if (value > 0) {
        var array = new Array();
        array.push(row.userTxStatus);
        console.log(array);
        return [
            '<button type="button" id="editRow1"  onclick="distribution(' + array + ',null,null)" class="distribution btn  btn-primary " " >打币</button>'
        ].join('')
    } else {
        return ""
    }
};

function distributionPlatFormatter(value, row, index) {
    if (parseInt(value) == 0 && row.needDistributeCount > 0) {
        var array = new Array();
        array.push(value);
        return [
            '<button type="button" id="editRow1"  onclick="distribution(null,null,' + array + ')" class="distribution btn  btn-primary " " >打币</button>'
        ].join('')
    } else {
        return ""
    }

};

function errorCountFormatter(value, row, index) {
    return [
        '<button type="button" id="btn-errorCount"  onclick="platformCountTable(null,' + row.userTxStatus + ',$(this))" class="errorCount btn " " >' + row.count + '</button>'
    ].join('')
};
function platformCountFormatter(value, row, index) {
    return [
        '<button type="button" id="btn-errorCount"  onclick="platformCountTable(' + row.platformTxStatus + ',null,$(this))" class="errorCount btn " " >' + row.count + '</button>'
    ].join('')
};


function platformCountTable(platformTxStatus, userTxStatus, obj) {
    var plat = parseInt($("#platformTxStatus_ option:selected").val());
    var user = parseInt($("#userTxStatus_ option:selected").val());

    reloadUserTxStatusSelect();
    reloadPlatformTxStatusSelect();
    $('.errorCount').removeClass(' btn-primary ')
    if ((platformTxStatus || platformTxStatus == 0)) {
        if (plat != platformTxStatus) {
            $(obj).addClass(' btn-primary ')
            $("#platformTxStatus_ option[value='" + platformTxStatus + "']").attr("selected", "selected");
        }
    }
    if (userTxStatus || userTxStatus == 0) {
        if (user !== userTxStatus) {
            $(obj).addClass(' btn-primary ')
            $("#userTxStatus_ option[value='" + userTxStatus + "']").attr("selected", "selected");
        }
    }


    reloadTxTable(1);
}

// error 表格上面的打币按钮
function error_table_distribution() {
    userTxStatus = new Array()
    platformTxStatus = new Array()

    var plat = $("#error_table").bootstrapTable('getSelections');

    $(".detail-view input:checkbox").each(function () {
        var parent = $(this).parent().parent();
        if ($(parent).hasClass('selected')) {
            var checkedNum = parseInt($(parent).attr('data-uniqueid'));
            userTxStatus.push(checkedNum);
        }
    })
    if (plat.length <= 0 && userTxStatus.length < 1) {
        alert("请至少选中下面表格的一行")
    } else {
        plat.forEach(function (e, index) {
            platformTxStatus.push(e.platformTxStatus);
        })
        console.log(userTxStatus)
        console.log(platformTxStatus)

        $('#distributionModal').modal('show');
    }
}

// 打币设置模态框
function distribution(txStatus_, txId_, platformStatus_) {

    var projectStatus = project.projectStatus;

    // if (projectStatus == 1 || projectStatus == 2) {
    //     bootbox.confirm("项目还在认筹中，确认需要提前打币？", function (result) {
    //         if (result) {
    //             return;
    //         }
    //     })
    // }
    userTxStatus = txStatus_;
    txId = parseInt(txId_);
    platformTxStatus = platformStatus_;


    $('#distributionModal').modal('show');
}


window.distributionEvents = {
    'click .distribution': function (e, value, row, index) {
    }
};

function userTxStatusFormatter(value, row, index) {
    var state = userTxStatusMap[value];
    var color = '#000';
    return [
        '<label class="btn " >' + state + '</label>'
    ].join('');
}


function platformStatusFormatter(value, row, index) {
    var state = disTributeStatusMap[value];
    var color = '#000';
    return [
        '<label class="btn  " >' + state + '</label>'
    ].join('');
}


function platformTxStatusFormatter(value, row, index) {
    var state = disTributeStatusMap[value];
    var color = '#000';
    var color_green = 'btn-danger';
    return [
        '<label class="btn  " >' + state + '</label>'
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
        placeholder: '请点击输入TOKEN',
        allowClear: true,//允许清空
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
        var i;
        var $txError = $('.tx-infos,.error-infos');
        for (i = 0; i < $txError.length; i++) {
            $txError[i].style.display = "none";
        }
        projectGid = repo.projectGid;
        project = repo;

        //如果 项目状态已经开始则加载 交易记录表
        if (repo.projectToken && repo.projectStatus >= 1 && !init) {

            //加载 交易详情表
            var oTable = new TableInit();
            oTable.Init();


            //加载 打币状态表
            var oTable_ = new TableInit_();
            oTable_.Init();

            //加载 查询状态表
            var oTable_tx_status = new TableInit_tx_status();
            oTable_tx_status.Init();

            var $tx = $('.tx-infos');
            for (i = 0; i < $tx.length; i++) {
                $tx[i].style.display = "block";
            }
        }

        if (projectGid) {
            var milliseconds = new Date().getMilliseconds();
            if (loadTableTime || milliseconds - loadTableTime > 100) {
                reloadTable(1);
            }
            if (!loadTableTime) {
                loadTableTime = milliseconds;
                reloadTable(1);
            }

        }

        return repo.projectToken || repo.text;
    }


    ////////////////////////////////////以下为打币异常表/////////////////////////////////////////////

    // 打币输入密码增加可视效果
    $('#password').password().on('show.bs.password', function (e) {
        $('#methods').prop('checked', true);
    }).on('hide.bs.password', function (e) {
        $('#methods').prop('checked', false);
    });
    $('#methods').click(function () {
        $('#password').password('toggle');
    });


    ////////////////////////////////////以下为tx表/////////////////////////////////////////////
//查询按钮
    $('#btn_query').click(function () {
        reloadTxTable(1);
    });

});

//重新加载表格
function reloadTable(pageNum) {
    reloadErrorTable(pageNum);
    reloadTxStatusTable(pageNum);
    reloadTxTable(pageNum);

}

function reloadErrorTable(pageNum) {
    $('#error_table').bootstrapTable('refresh', {pageNumber: pageNum});
}

function reloadTxTable(pageNum) {
    $('#tx_table').bootstrapTable('refresh', {pageNumber: pageNum});
}

function reloadTxStatusTable(pageNum) {
    $('#tx_status_table').bootstrapTable('refresh', {pageNumber: pageNum});
}

function reloadUserTxStatusSelect() {
    $('#userTxStatus_')
        .empty()
        .prepend("<option value='' >请选择</option>");//添加第一个option值
    for (var key in userTxStatusMap) {
        $("#userTxStatus_").append("<option value='" + key + "'>" + userTxStatusMap[key] + "</option>");
    }
}
function reloadPlatformTxStatusSelect() {
    $('#platformTxStatus_')
        .empty()
        .prepend("<option value='' >请选择</option>");//添加第一个option值
    for (var key in disTributeStatusMap) {
        $("#platformTxStatus_").append("<option value='" + key + "'>" + disTributeStatusMap[key] + "</option>");
    }
}

$(function () {
    reloadUserTxStatusSelect();
    reloadPlatformTxStatusSelect();

    var $table = $('#info-table');
    $(function () {
        $('#toolbar').find('select').change(function () {
            $table.bootstrapTable('destroy').bootstrapTable({
                exportDataType: $(this).val()
            });
        });
    })


    $("#export-excel").click(function () {
        window.open(contextPath + "/management/project-info-export/excel/" + projectGid);
    });

})

////////////////////////////////以下为打币///////////////////////////////////////////

$('#distributionModal').on('hidden.bs.modal', function () {
    $(' #password').val('');
    $(' #keystore').val('');
})

$(function () {
    $('#addEvent').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        excluded: [':disabled'],
        fields: {
            password: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    }

                }
            },
            keystore: {
                validators: {
                    notEmpty: {
                        message: 'keystore不能为空'
                    }

                }
            }
        }
    }).on('success.form.bv', function (e) {
        // Prevent form submission
        e.preventDefault();

        var dataJson = {
            'password': $(' #password').val(),
            'keystore': $(' #keystore').val(),
            'projectGid': projectGid
        }

        if (txId) {
            dataJson.id = txId - 10000;
        }
        if (userTxStatus) {
            dataJson.userTxStatusStr = userTxStatus + '';
        }
        console.log(platformTxStatus);
        if (platformTxStatus || parseInt(platformTxStatus) === 0) {
            dataJson.platformTxStatusStr = platformTxStatus + '';
        }
        console.log(dataJson);
        $.ajax({
            url: contextPath + "/pay-record/token/distribute",
            type: "post",
            traditional: true,
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
                    layer.msg("发送成功", {
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
