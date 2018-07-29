var view;
var pdfEn;
var pdfEnName;
var pdfCn;
var pdfCnName;
var pdfKo;
var pdfKoName;
var pdfJa;
var pdfJaName;
var projectNow;
var projectToken;
var tokenDecimal;
var logoStr;
var addMethod = true;
var channelAddMethod = true;
var channelId = '';
var exportTime;

function getNowFormatDate(date) {
    if (!date) {
        date = new Date();
    }
    if (date.getFullYear() == 2000) {
        return ""
    }
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    var hours = date.getHours();
    var minutes = date.getMinutes();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    if (hours >= 0 && hours <= 9) {
        hours = "0" + hours;
    }
    if (minutes >= 0 && minutes <= 9) {
        minutes = "0" + minutes;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
        + " " + hours + seperator2 + minutes;
    return currentdate;
}
function getNowFormatDay(date) {
    if (!date) {
        date = new Date();
    }
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    return date.getFullYear() + seperator1 + month + seperator1 + strDate;
}

function getAddDay(addDay) {
    var current = new Date();
    current.setTime(current.getTime() + addDay * 24 * 60 * 60 * 1000);
    return current;
}

function initTimer() {
    $('#startTimePicker').datetimepicker().val(getNowFormatDate(getAddDay(1))).datetimepicker('update');
    $('#endTimePicker').datetimepicker().val(getNowFormatDate(getAddDay(3))).datetimepicker('update');
}


function numberFormat(num) {
    var txt = numeral(num);
    var formatStr = '0,0';
    var value = txt.value();
    var split = value.toString().split('.');
    if (split.length > 1) {
        formatStr = formatStr + "." + split[1].toString().replace(/\d/g, '0');
    }
    return txt.format(formatStr);
}

var TableInit = function () {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#inner_table').bootstrapTable({
            url: contextPath + '/management/project/list',         //请求后台的URL（*）
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
                field: 'startTime',
                align: 'center',
                title: '开始时间',
                formatter: timeFormatter
            }, {
                field: 'endTime',
                align: 'center',
                title: '结束时间',
                formatter: timeFormatter
            }, {
                field: 'projectStatus',
                align: 'center',
                title: '项目状态',
                events: changeEvents,
                formatter: projectStatusFormatter
            }, {
                title: '开启状态',
                field: 'isAvailable',
                align: 'center',
                valign: 'middle',
                events: lockEvents,
                formatter: lockFormatter
            }, {
                title: '首页展示',
                field: 'defaultProject',
                align: 'center',
                valign: 'middle',
                events: defaultProjectEvents,
                formatter: defaultProjectFormatter
            }, {
                field: 'operate',
                title: '其他',
                align: 'left',
                halign: 'center',
                falign: 'left',
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

function lockFormatter(value, row, index) {
    var state = '--';
    var color = '#000';
    var color_red = 'btn-success';
    var color_green = 'btn-danger';
    if (value) {
        state = '开启';
        color = color_red;
    } else if (!value) {
        state = "关闭";
        color = color_green;
    }
    return [
        '<a class="lock" href="javascript:void(0)" title="切换状态">',
        '<button class="btn ' + color + '" >' + state + '</button>',
        '</a>'
    ].join('');
}

function defaultProjectFormatter(value, row, index) {
    var state = '';
    var color = '#000';

    if (!row.isAvailable) {
        return ''
    }

    if (value) {
        state = '是';
        color = 'btn-success';
    } else if (!value) {
        state = "否";
        // color = 'btn-default';
    }
    return [
        '<a class="lock" href="javascript:void(0)" title="切换状态">',
        '<button class="btn ' + color + '" >' + state + '</button>',
        '</a>'
    ].join('');
}


function projectStatusFormatter(value, row, index) {

    var status0 = '<option value="0">未开始</option> ';
    var status1 = '<option value="1">未到软顶</option> ';
    var status2 = '<option value="2">未到硬顶</option> ';
    var status3 = '<option value="3">已完成</option> ';
    var startTime = row.startTime;
    var endTime = row.endTime;
    var now = new Date().getTime();
    if (startTime < now) {
        status0 = '<option value="0" disabled="disabled">未开始</option> ';
    }
    if (endTime < now) {
        status0 = '<option value="0" disabled="disabled">未开始</option> ';
        status1 = '<option value="1" disabled="disabled">未到软顶</option> ';
        status2 = '<option value="2" disabled="disabled">未到硬顶</option> ';
    }

    switch (value) {
        case 0:
            status0 = '<option value="0" selected>未开始</option> ';
            break;
        case 1:
            status1 = '<option value="1" selected>未到软顶</option> ';
            break;
        case 2:
            status2 = '<option value="2" selected>未到硬顶</option> ';
            break;
        case 3:
        case 4:
            status3 = '<option value="3" selected>已完成</option> ';
            break;
        default:
            status0 = '<option value="0" selected>未开始</option> ';
            break;
    }
    return [
        '<select  class="changeStatus">' +
        status0 + status1 + status2 + status3 +
        '</select>'
    ].join('');
}

function timeFormatter(value, row, index) {
    var nowFormatDate = getNowFormatDate(new Date(value));
    return [
        '<label  >' + nowFormatDate + '</label>'
    ].join('');
}

function dayFormatter(value, row, index) {
    return [
        '<label  >' + getNowFormatDay(new Date(value)) + '</label>'
    ].join('');
}

window.lockEvents = {
    'click .lock': function (e, value, row, index) {
        console.log(row.isAvailable)
        console.log(typeof  row.isAvailable)
        console.log(row.defaultProject)
        if ((row.isAvailable == 1 || parseInt(row.isAvailable) == 1 ) && (row.defaultProject == 1 || parseInt(row.defaultProject) == 1)) {
            alert("该项目是首页展示项目,不能关闭");
            return;
        }
        bootbox.confirm("确认要更改状态", function (result) {
            if (result) {
                console.log(row);
                $.ajax({
                    url: contextPath + "/management/project/hide/" + row.projectGid,
                    type: "put",
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
        })
    }
};

window.changeEvents = {
    'change .changeStatus': function (e, value, row, index) {
        var status = parseInt($(this).val());
        console.log(status)
        console.log(typeof  status)
        bootbox.confirm("确认要更改项目状态", function (result) {
            if (result) {
                console.log(row);
                $.ajax({
                    url: contextPath + "/management/project/status/" + row.projectGid + "/" + status,
                    type: "put",
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
                            })
                            $('#inner_table').bootstrapTable('refresh');
                        } else {
                            layer.msg("任务失败，" + data.message, {
                                time: 2000,
                                icon: 0,
                                shift: 1
                            })
                            $('#inner_table').bootstrapTable('refresh');
                        }

                    }
                })
            } else {
                $('#inner_table').bootstrapTable('refresh');
            }

        })


    }
};

window.defaultProjectEvents = {
    'click .lock': function (e, value, row, index) {

        if (value == 1 || parseInt(value) == 1) {
            alert("该项目已是首页展示项目");
            return;
        }
        bootbox.confirm("确认要修改为首页项目", function (result) {
            if (result) {
                console.log(row);
                $.ajax({
                    url: contextPath + "/management/project/default/" + row.projectGid,
                    type: "put",
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
        })
    }
};

window.operateEvents = {
    'click .editRow': function (e, value, row, index) {
        getProject(row)
    },

    'click .btn-channel': function (e, value, row, index) {

        projectGid = row.projectGid;
        $('#channelProjectToken').html(row.projectToken);
        $('#channelProjectGid').val(row.projectGid);
        reloadChannelTable(1);
        $('#channelModal').modal('show');
    },

    'click .statistic': function (e, value, row, index) {
        $('#statisticProjectToken').html(row.projectToken);
        $('#statisticProjectGid').val(row.projectGid);
        reloadStatisticTable(1);
        $('#statisticModal').modal('show');
    },
    'click .export': function (e, value, row, index) {
        window.open(contextPath + "/management/project-info-export/excel/" + row.projectGid);
    }

};


function operateFormatter(value, row, index) {

    var arr = [];
    arr.push('<button type="button" id="editRow"  style="margin: 0 10px 0 0 " class="btn btn-primary  editRow" ><i class="fa fa-send " aria-hidden="true" ></i>编辑</button>');
    // if (row.projectStatus > 0) {
    //     arr.push('<button type="button" id="editRow1"  class="statistic btn  btn-info " projectGid="' + row.projectGid + '" >统计</button>')
    // }
    arr.push(' <a class="btn btn-info export" ">数据导出</button>');

    return arr.join('')
}

//重新加载统计表格
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


//加载单个项目
function getProject(row) {

    $('#addEvent')
        .bootstrapValidator('removeField', 'logo')
        .bootstrapValidator('removeField', 'view')
        .bootstrapValidator('removeField', 'pdfZh')
        .bootstrapValidator('removeField', 'pdfEn');

    $('#addEvent').bootstrapValidator('addField', 'logo', {
        validators: {
            file: {
                extension: 'jpg,jpeg,bmp,png,gif',
                type: 'image/jpg,image/jpeg,image/bmp,image/gif,image/png',
                maxSize: 10 * 1024 * 1024,
                message: '请上传10M以内的图像（jpg,jpeg,bmp,gif,png）'
            }
        }
    }).bootstrapValidator('addField', 'view', {
        validators: {
            file: {
                extension: 'jpg,jpeg,bmp,png,gif',
                type: 'image/jpg,image/jpeg,image/bmp,image/gif,image/png',
                maxSize: 10 * 1024 * 1024,
                message: '请上传10M以内的图像（jpg,jpeg,bmp,gif,png）'
            }
        }
    }).bootstrapValidator('addField', 'pdfZh', {
        validators: {
            file: {
                extension: 'pdf',
                type: 'application/pdf',
                maxSize: 10 * 1024 * 1024,
                message: '请上传10M以内的pdf文件'
            }
        }
    }).bootstrapValidator('addField', 'pdfEn', {
        validators: {
            file: {
                extension: 'pdf',
                type: 'application/pdf',
                maxSize: 10 * 1024 * 1024,
                message: '请上传10M以内的pdf文件'
            }
        }
    })


    $.ajax({
        url: contextPath + "/management/project/" + row.projectGid,
        type: "get",
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
                var project = data.data;
                projectNow = project;
                tokenDecimal = project.tokenDecimal;
                projectToken = project.projectToken;
                $('#projectToken').html(project.projectToken);
                $('#platformAddressDiv').show();
                $('#platformAddress').html(project.platformAddress);
                $('#tokenAddressDiv_').show();
                $('#tokenAddressDiv').hide();
                $('#tokenAddress_').html(project.tokenAddress);
                $('#tokenAddress').val(project.tokenAddress);
                $('#projectAddress').val(project.projectAddress);
                $('#softCap').val(numberFormat(project.softCap));
                $('#hardCap').val(numberFormat(project.hardCap));
                $('#minPurchaseAmount').val(numberFormat(project.minPurchaseAmount));
                $('#maxPurchaseAmount').val(numberFormat(project.maxPurchaseAmount));
                $('#priceRate').val(numberFormat(project.priceRate));
                $('#startTimePicker').val(getNowFormatDate(new Date(project.startTime)));
                $('#endTimePicker').val(getNowFormatDate(new Date(project.endTime)));

                $(' #instructionEn').val(project.descriptions.en.projectInstruction);
                $(' #contentEn').val(project.descriptions.en.projectContent);
                $(' #whitePaperLinkEn').val(project.descriptions.en.whitePaperLink);
                $(' #projectNameEn').val(project.descriptions.en.projectName);

                if (project.descriptions.cn) {
                    $(' #instructionCn').val(project.descriptions.cn.projectInstruction);
                    $(' #contentCn').val(project.descriptions.cn.projectContent);
                    $(' #whitePaperLinkCn').val(project.descriptions.cn.whitePaperLink);
                    $(' #projectNameCn').val(project.descriptions.cn.projectName);
                }

                if (project.descriptions.ko) {
                    $(' #instructionKo').val(project.descriptions.ko.projectInstruction);
                    $(' #contentKo').val(project.descriptions.ko.projectContent);
                    $(' #whitePaperLinkKo').val(project.descriptions.ko.whitePaperLink);
                    $(' #projectNameKo').val(project.descriptions.ko.projectName);
                }

                if (project.descriptions.ja) {
                    $(' #instructionJa').val(project.descriptions.ja.projectInstruction);
                    $(' #contentJa').val(project.descriptions.ja.projectContent);
                    $(' #whitePaperLinkJa').val(project.descriptions.ja.whitePaperLink);
                    $(' #projectNameJa').val(project.descriptions.ja.projectName);
                }

                $(' #officialLink').val(project.websites.officialLink);
                $(' #twitter').val(project.websites.twitter);
                $(' #facebook').val(project.websites.facebook);
                $(' #telegram').val(project.websites.telegram);
                $(' #reddit').val(project.websites.reddit);
                $(' #biYong').val(project.websites.biYong);
                $(' #gitHub').val(project.websites.gitHub);
                $(' #privacyPolicy').val(project.websites.privacyPolicy);
                $(' #tokenTerms').val(project.websites.tokenTerms);
                $(' #kyc').val(project.websites.kyc);

                $('#logoImg').attr('src', project.projectLogoLink);
                //根据项目状态判断是否能更改
                var projectStatus = project.projectStatus;
                if (projectStatus > 0) {
                    // $('#startPrice').disable();
                    // $('#startTimePicker').disable();
                }
                if (projectStatus > 1) {
                    // $('#softCap').disable();
                }
                if (projectStatus > 2) {
                    // $('#hardCap').disable();
                    // $('#endPrice').disable();
                    // $('#endTimePicker').disable();
                    // $('#minPurchaseAmount').disable();
                }

                $('#addModal').modal('show');
                addMethod = false
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


//重新加载项目表格
function reloadTable(pageNum) {
    $('#inner_table').bootstrapTable('refresh', {pageNumber: pageNum});
}

//查询按钮
$('#btn_query').click(function () {
    reloadTable(1);
});

//新增按钮
$('#btn_add').click(function () {
    $.ajax({
        url: contextPath + "/management/platform-addresses/count",
        type: "get",
        contentType: "application/json;charset=UTF-8",
        success: function (data) {
            console.log(data);
            if (data.success) {
                var addressCount = parseInt(data.data);
                if (addressCount < 1) {
                    layer.msg("平台地址还剩" + addressCount + "个，添加地址后再新增项目", {
                        time: 3000,
                        icon: 2,
                        shift: 1
                    }, function () {
                        $('.close').click()
                    })
                } else {
                    $('#addModal').modal('show');
                    addMethod = true;
                }
            }
        }
    });

})

//隐藏模态框
$('#addModal').on('hidden.bs.modal', function () {

    // 表单清空
    $('#addEvent')[0].reset();
    $('#addEvent')
        .bootstrapValidator("resetForm", true)
        .bootstrapValidator('addField', 'logo', {
            validators: {
                notEmpty: {
                    message: '图像不能为空'
                },
                file: {
                    extension: 'jpg,jpeg,bmp,png,gif',
                    type: 'image/jpg,image/jpeg,image/bmp,image/gif,image/png',
                    maxSize: 10 * 1024 * 1024,
                    message: '请上传10M以内的图像（jpg,jpeg,bmp,gif,png）'
                }
            }
        });
    //重新加载表格
    reloadTable(1);
    //初始化时间选择器
    initTimer();
    //log图片清空
    $('#logoImg').attr('src', "");
    logoStr = "";
    addMethod = true;
    $('#platformAddress').html('');
    $('#platformAddressDiv').hide();
    $('#projectToken').html('');
})


//file 文本变化获取值
$('input[type=file]').on('change', function (e) {
    var id = $(this).attr('id');
    var file = e.target.files[0];
    if (file) {
        var fileName = file.name;
        fileName = fileName.replace(".pdf", "").replace(/[^\dA-Za-z\.]/g, "_");
        var reader = new FileReader();
        reader.readAsDataURL(file);
        console.log(fileName);
        reader.onloadend = function () {
            switch (id) {
                case 'logo':
                    logoStr = reader.result;
                    $('#logoImg').attr('src', logoStr);
                    break;
                case 'view':
                    view = reader.result;
                    break;
                case 'pdfCn':
                    pdfCn = reader.result;
                    pdfCnName = fileName;
                    break;
                case 'pdfEn':
                    pdfEn = reader.result;
                    pdfEnName = fileName;
                    break;
                case 'pdfKo':
                    pdfKo = reader.result;
                    pdfKoName = fileName;
                    break;
                case 'pdfJa':
                    pdfJa = reader.result;
                    pdfJaName = fileName;
                    break;
                default:
                    break;
            }
        };
    } else {
        switch (id) {
            case 'logo':
                logoStr = "";
                break;
            case 'view':
                view = "";
                break;
            case 'pdfZh':
                pdfCn = "";
                pdfCnName = "";
                break;
            case 'pdfEn':
                pdfEn = "";
                pdfEnName = "";
                break;
            case 'pdfKo':
                pdfKo = "";
                pdfKoName = "";
                break;
            case 'pdfJa':
                pdfJa = "";
                pdfJaName = "";
                break;
            default:
                break;
        }
    }
});


$(function () {
    //加载项目table
    var oTable = new TableInit();
    oTable.Init();

    //加载统计table
    var oTable_ = new TableInit_();
    oTable_.Init();

    $('#addEvent').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        excluded: [':disabled'],
        fields: {

            token: {
                validators: {
                    notEmpty: {
                        message: 'token不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 9,
                        message: '请输入1-9个字符'
                    },
                    regexp: {
                        regexp: /^[0-9a-zA-Z]+$/,
                        message: '请输入英文或数字'
                    }
                }
            },

            projectNameEn: {
                validators: {
                    notEmpty: {
                        message: '英文项目名称不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 32,
                        message: '请输入1-32个字符'
                    }
                }
            },
            projectNameCn: {
                validators: {
                    stringLength: {
                        min: 1,
                        max: 32,
                        message: '请输入1-32个字符'
                    }
                }
            },
            projectNameKo: {
                validators: {
                    stringLength: {
                        min: 1,
                        max: 32,
                        message: '请输入1-32个字符'
                    }
                }
            },
            projectNameJa: {
                validators: {
                    stringLength: {
                        min: 1,
                        max: 32,
                        message: '请输入1-32个字符'
                    }
                }
            },


            tokenAddress: {
                validators: {
                    notEmpty: {
                        message: '合约地址不能为空'
                    },
                    regexp: {
                        regexp: tokenAddressReg,
                        message: '请输入正确的合约地址'
                    }
                }
            },

            projectAddress: {
                validators: {
                    notEmpty: {
                        message: '项目地址不能为空'
                    },
                    regexp: {
                        regexp: tokenAddressReg,
                        message: '请输入正确的地址'
                    }
                }
            },
            softCap: {
                validators: {
                    notEmpty: {
                        message: '软顶数量不能为空'
                    }
                }
            },
            hardCap: {
                validators: {
                    notEmpty: {
                        message: '硬顶数量不能为空'
                    }
                }
            },

            minPurchaseAmount: {
                validators: {
                    notEmpty: {
                        message: '最低认购数量不能为空'
                    }
                }
            },
            maxPurchaseAmount: {
                validators: {
                    notEmpty: {
                        message: '最高认购数量不能为空'
                    }
                }
            },
            startTimePicker: {
                validators: {
                    notEmpty: {
                        message: '开始时间不能为空'
                    }
                }
            },
            endTimePicker: {
                validators: {
                    notEmpty: {
                        message: '结束时间不能为空'
                    }
                }
            },
            priceRate: {
                validators: {
                    notEmpty: {
                        message: '单价比（ETH:TOKEN）不能为空'
                    }
                }
            },
            instructionEn: {
                validators: {
                    notEmpty: {
                        message: '项目简介不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 250,
                        message: '请输入250个字符以下的内容'
                    }
                }
            },

            instructionCn: {
                validators: {
                    stringLength: {
                        min: 1,
                        max: 250,
                        message: '请输入250个字符以下的内容'
                    }
                }
            },

            instructionKo: {
                validators: {
                    stringLength: {
                        min: 1,
                        max: 250,
                        message: '请输入250个字符以下的内容'
                    }
                }
            },

            instructionJa: {
                validators: {
                    stringLength: {
                        min: 1,
                        max: 250,
                        message: '请输入250个字符以下的内容'
                    }
                }
            },


            contentEn: {
                validators: {
                    notEmpty: {
                        message: '英文项目详情不能为空'
                    }
                }
            },
            contentCn: {
                validators: {}
            },
            contentKo: {
                validators: {}
            },
            contentJa: {
                validators: {}
            },


            whitePaperLinkEn: {
                validators: {
                    notEmpty: {
                        message: '英文白皮书地址不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 250,
                        message: '请输入250个字符以下的网址'
                    }
                }
            },

            whitePaperLinkCn: {
                validators: {
                    stringLength: {
                        min: 1,
                        max: 250,
                        message: '请输入250个字符以下的网址'
                    }
                }
            },
            whitePaperLinkJa: {
                validators: {
                    stringLength: {
                        min: 1,
                        max: 250,
                        message: '请输入250个字符以下的网址'
                    }
                }
            },
            whitePaperLinkKo: {
                validators: {
                    stringLength: {
                        min: 1,
                        max: 250,
                        message: '请输入250个字符以下的网址'
                    }
                }
            },

            officialLink: {
                validators: {
                    notEmpty: {
                        message: '官网地址不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 250,
                        message: '请输入250个字符以下的网址'
                    }
                }
            },

            twitter: {
                validators: {
                    notEmpty: {
                        message: '链接地址不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 250,
                        message: '请输入250个字符以下的网址'
                    }
                }
            },
            facebook: {
                validators: {
                    notEmpty: {
                        message: '链接地址不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 250,
                        message: '请输入250个字符以下的网址'
                    }
                }
            },
            telegram: {
                validators: {
                    notEmpty: {
                        message: '链接地址不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 250,
                        message: '请输入250个字符以下的网址'
                    }
                }
            },
            reddit: {
                validators: {
                    notEmpty: {
                        message: '链接地址不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 250,
                        message: '请输入250个字符以下的网址'
                    }
                }
            },
            biYong: {
                validators: {
                    notEmpty: {
                        message: '链接地址不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 250,
                        message: '请输入250个字符以下的网址'
                    }
                }
            },
            gitHub: {
                validators: {
                    notEmpty: {
                        message: '链接地址不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 250,
                        message: '请输入250个字符以下的网址'
                    }
                }
            },
            kyc: {
                validators: {
                    notEmpty: {
                        message: '链接地址不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 250,
                        message: '请输入250个字符以下的网址'
                    }
                }
            },
            tokenTerms: {
                validators: {
                    notEmpty: {
                        message: '链接地址不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 250,
                        message: '请输入250个字符以下的网址'
                    }
                }
            },
            privacyPolicy: {
                validators: {
                    notEmpty: {
                        message: '链接地址不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 250,
                        message: '请输入250个字符以下的网址'
                    }
                }
            },

            pdfEn: {
                validators: {
                    notEmpty: {
                        message: 'pdf不能为空'
                    },
                    file: {
                        extension: 'pdf',
                        type: 'application/pdf',
                        maxSize: 10 * 1024 * 1024,
                        message: '请上传10M以内的pdf文件'
                    }
                }
            },

            pdfCn: {
                validators: {
                    file: {
                        extension: 'pdf',
                        type: 'application/pdf',
                        maxSize: 10 * 1024 * 1024,
                        message: '请上传10M以内的pdf文件'
                    }
                }
            },
            pdfKo: {
                validators: {
                    file: {
                        extension: 'pdf',
                        type: 'application/pdf',
                        maxSize: 10 * 1024 * 1024,
                        message: '请上传10M以内的pdf文件'
                    }
                }
            },
            pdfJa: {
                validators: {
                    file: {
                        extension: 'pdf',
                        type: 'application/pdf',
                        maxSize: 10 * 1024 * 1024,
                        message: '请上传10M以内的pdf文件'
                    }
                }
            },

            log: {
                validators: {
                    notEmpty: {
                        message: '图像不能为空'
                    },
                    file: {
                        extension: 'jpg,jpeg,bmp,png,gif',
                        type: 'image/jpg,image/jpeg,image/bmp,image/gif,image/png',
                        maxSize: 10 * 1024 * 1024,
                        message: '请上传10M以内的图像（jpg,jpeg,bmp,gif,png）'
                    }
                }
            },
            view: {
                validators: {
                    notEmpty: {
                        message: '图像不能为空'
                    },
                    file: {
                        extension: 'jpg,jpeg,bmp,png,gif',
                        type: 'image/jpg,image/jpeg,image/bmp,image/gif,image/png',
                        maxSize: 10 * 1024 * 1024,
                        message: '请上传10M以内的图像（jpg,jpeg,bmp,gif,png）'
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
        console.log($form.serialize())
        // Use Ajax to submit form data
        var projectGid = addMethod ? "" : projectNow.projectGid;
        if (!checkPrice() || !checkCap()) {
            return;
        }
        var dataJson = {
            'projectGid': projectGid,
            'tokenDecimal': addMethod ? tokenDecimal : projectNow.tokenDecimal,
            'projectToken': addMethod ? projectToken : projectNow.projectToken,
            'tokenAddress': addMethod ? $(' #tokenAddress').val() : projectNow.tokenAddress,
            'projectNameEn': $(' #projectNameEn').val(),
            'projectNameCn': $(' #projectNameCn').val(),
            'projectNameKo': $(' #projectNameKo').val(),

            'projectNameJa': $(' #projectNameJa').val(),
            'projectAddress': $(' #projectAddress').val(),
            'softCap': numeral($('#softCap').val()).value(),
            'hardCap': numeral($('#hardCap').val()).value(),
            'minPurchaseAmount': numeral($('#minPurchaseAmount').val()).value(),
            'maxPurchaseAmount': numeral($('#maxPurchaseAmount').val()).value(),

            'priceRate': numeral($('#priceRate').val()).value(),
            'startTimeLong': new Date($('#startTimePicker').val()).getTime(),
            'endTimeLong': new Date($('#endTimePicker').val()).getTime(),

            'instructionEn': $(' #instructionEn').val(),
            'instructionCn': $(' #instructionCn').val(),
            'instructionKo': $(' #instructionKo').val(),
            'instructionJa': $(' #instructionJa').val(),

            'contentEn': $(' #contentEn').val(),
            'contentCn': $(' #contentCn').val(),
            'contentKo': $(' #contentKo').val(),
            'contentJa': $(' #contentJa').val(),

            'whitePaperLinkEn': $(' #whitePaperLinkEn').val(),
            'whitePaperLinkCn': $(' #whitePaperLinkCn').val(),
            'whitePaperLinkKo': $(' #whitePaperLinkKo').val(),
            'whitePaperLinkJa': $(' #whitePaperLinkJa').val(),

            'officialLink': $(' #officialLink').val(),
            'twitter': $(' #twitter').val(),
            'facebook': $(' #facebook').val(),
            'telegram': $(' #telegram').val(),
            'reddit': $(' #reddit').val(),
            'biYong': $(' #biYong').val(),
            'gitHub': $(' #gitHub').val(),
            'kyc': $(' #kyc').val(),
            'privacyPolicy': $(' #privacyPolicy').val(),
            'tokenTerms': $(' #tokenTerms').val(),

            'logo': logoStr,
            'view': view

        };

        console.log(dataJson);

        var requestType = addMethod ? "post" : "put";
        $.ajax({
            url: contextPath + "/management/project",
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


    $(function () {
        //  时间选择器
        $('.form_datetime').datetimepicker({
            language: 'zh-CN',
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            forceParse: 0,
            showMeridian: 1
        });
        //初始化时间选择器
        initTimer();

        $('#reset').click(function () {

        })

        $('.time').change(function () {
            var start = new Date($('#startTimePicker').val());
            var end = new Date($('#endTimePicker').val());
            if (start >= end) {
                layer.msg("时间设置有误", {
                    time: 2000,
                    icon: 0,
                    shift: 1
                });
                if ('startTimePicker' == $(this).attr('id')) {
                    $(this).val(getNowFormatDate()).datetimepicker('update');
                } else {
                    $(this).val(getNowFormatDate(getAddDay(3))).datetimepicker('update');
                }
            }
        });


        //将数字转化为千分位形式
        $('.thousands').change(function () {

            var val = $(this).val();
            var txt = numeral(val);
            if (!txt) {
                $(this).val(0);
            }
            var formatStr = '0,0';
            var value = txt.value();
            if (!value || value < 0) {
                $(this).val(0);
            }
            var split = value.toString().split('.');
            if (split.length > 1) {
                formatStr = formatStr + "." + split[1].toString().replace(/\d/g, '0');
            }
            var result = txt.format(formatStr);
            $(this).val(result)
        });

        $('#tokenAddress').change(function () {
            var tokenAddress = $(this).val().trim();
            $(this).val(tokenAddress);
            if (tokenAddress && regToken.test(tokenAddress)) {
                $.ajax({
                    url: moonPath + "/token/" + tokenAddress,
                    type: 'GET',
                    contentType: "application/json;charset=UTF-8",
                    dataType: 'jsonp',
                    crossDomain: true,
                    success: function (data) {
                        console.log(data);
                        if (data.code == "0" && data.result && data.result.symbol != "UN_SYMBOL") {
                            var result = data.result;
                            projectToken = result.symbol;
                            $('#projectToken').html(projectToken);
                            tokenDecimal = result.decimal;
                            $('#tokenDecimal').html(tokenDecimal);
                            $('#projectNameEn').val(data.name);
                            $.ajax({
                                url: contextPath + "/management/project?tokenAddress=" + tokenAddress,
                                type: "get",
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
                                        layer.msg("已有该项目，请检查后输入", {
                                            time: 2000,
                                            icon: 0,
                                            shift: 1
                                        }, function () {
                                            $('#tokenAddress').val('')
                                        })
                                    }
                                }
                            });
                        } else {
                            layer.msg("智能合约地址未找到，请再次输入", {
                                time: 2000,
                                icon: 0,
                                shift: 1
                            }, function () {
                                $('#tokenAddress').val('')
                            })
                        }
                    }
                })
            }
        })
    });
    /*    {
     "code": "0",
     "message": "成功",
     "result": {
     "name": "Bee Honey Token",
     "symbol": "HONEY",
     "decimal": "9"
     }
     }*/

    function checkPrice() {
        var priceRate = numeral($('#priceRate').val()).value();

        if (!priceRate) {
            layer.msg("价格设置有误", {
                time: 2000,
                icon: 0,
                shift: 1
            });
            return false;
        }
        return true;
    };

    function checkCap() {
        var softCap = numeral($('#softCap').val()).value();
        var hardCap = numeral($('#hardCap').val()).value();
        var minPurchaseAmount = numeral($('#minPurchaseAmount').val()).value();
        var maxPurchaseAmount = numeral($('#maxPurchaseAmount').val()).value();

        if (!hardCap || !softCap || hardCap < softCap) {
            layer.msg("软硬顶设置有误", {
                time: 2000,
                icon: 0,
                shift: 1
            });
            return false;
        }
        if (!minPurchaseAmount || !maxPurchaseAmount || maxPurchaseAmount < minPurchaseAmount) {
            layer.msg("认购限额设置有误", {
                time: 2000,
                icon: 0,
                shift: 1
            });
            return false;
        }
        return true;
    };


})
