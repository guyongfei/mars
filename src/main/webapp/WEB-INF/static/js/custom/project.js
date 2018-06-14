var logStr;
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
var addMethod = true;
var projectTypeArr = new Array();

function getNowFormatDate(date) {
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
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
        + " " + date.getHours() + seperator2 + date.getMinutes();
    return currentdate;
}

function getAddDay(addDay) {
    var current = new Date();
    current.setTime(current.getTime() + addDay * 24 * 60 * 60 * 1000);
    return current;
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
                formatter: projectStatusFormatter
            }, {
                title: '是否启用',
                field: 'isAvailable',
                align: 'center',
                valign: 'middle',
                events: lockEvents,
                formatter: lockFormatter
            }, {
                field: 'operate',
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
        state = '已启用';
        color = color_red;
    } else if (!value) {
        state = "已关闭";
        color = color_green;
    }
    return [
        '<a class="lock" href="javascript:void(0)" title="切换状态">',
        '<button class="btn ' + color + '" >' + state + '</button>',
        '</a>'
    ].join('');
}

function projectStatusFormatter(value, row, index) {
    var state = '--';
    var color = '#000';
    var color_green = 'btn-danger';
    switch (value) {
        case 0:
            state = '未开始';
            break;
        case 1:
            state = '未到软顶';
            break;
        case 2:
            state = '未到硬顶';
            break;
        case 3:
            state = '成功';
            break;
        case 4:
            state = '失败';
            break;
    }
    return [
        '<button class="btn ' + color + '" >' + state + '</button>'
    ].join('');
}

function timeFormatter(value, row, index) {
    return [
        '<label  >' + getNowFormatDate(new Date(value)) + '</label>'
    ].join('');
}

window.lockEvents = {
    'click .lock': function (e, value, row, index) {
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

window.operateEvents = {
    'click .editRow': function (e, value, row, index) {
        getProject(row)
    }
};
function operateFormatter(value, row, index) {
    return [
        '<button type="button" id="editRow"  class="btn am-btn-primary  editRow" ><i class="fa fa-send " aria-hidden="true" ></i>编辑</button>'
    ]
};

//加载单个项目
function getProject(row) {

    $('#addEvent')
        .bootstrapValidator('removeField', 'log')
        .bootstrapValidator('removeField', 'view')
        .bootstrapValidator('removeField', 'pdfZh')
        .bootstrapValidator('removeField', 'pdfEn');

    $('#addEvent').bootstrapValidator('addField', 'log', {
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

                $('#tokenAddress').val(project.tokenAddress);
                $('#projectToken').val(project.projectToken);
                $('#projectAddress').val(project.projectAddress);
                $('#softCap').val(project.softCap);
                $('#hardCap').val(project.hardCap);
                $('#minPurchaseAmount').val(project.minPurchaseAmount);
                $('#startPrice').val(project.startPrice);
                $('#endPrice').val(project.endPrice);
                $('#startTimePicker').val(getNowFormatDate(new Date(project.startTime)));
                $('#endTimePicker').val(getNowFormatDate(new Date(project.endTime)));

                $(' #instructionEn').val(project.descriptions.en.projectInstruction);
                $(' #instructionCn').val(project.descriptions.cn.projectInstruction);
                $(' #instructionKo').val(project.descriptions.ko.projectInstruction);
                $(' #instructionJa').val(project.descriptions.ja.projectInstruction);

                $(' #contentEn').val(project.descriptions.en.projectContent);
                $(' #contentCn').val(project.descriptions.cn.projectContent);
                $(' #contentKo').val(project.descriptions.ko.projectContent);
                $(' #contentJa').val(project.descriptions.ja.projectContent);

                $(' #officialLink').val(project.websites.officialLink);
                $(' #twitter').val(project.websites.twitter);
                $(' #facebook').val(project.websites.facebook);
                $(' #telegram').val(project.websites.telegram);
                $(' #reddit').val(project.websites.reddit);
                $(' #biYong').val(project.websites.biYong);
                $(' #gitHub').val(project.websites.gitHub);

                $(' #whitePaperLinkCn').val(project.descriptions.cn.whitePaperLink);
                $(' #whitePaperLinkEn').val(project.descriptions.en.whitePaperLink);
                $(' #whitePaperLinkKo').val(project.descriptions.ko.whitePaperLink);
                $(' #whitePaperLinkJa').val(project.descriptions.ja.whitePaperLink);

                $(' #projectNameCn').val(project.descriptions.cn.projectName);
                $(' #projectNameEn').val(project.descriptions.en.projectName);
                $(' #projectNameJa').val(project.descriptions.ja.projectName);
                $(' #projectNameKo').val(project.descriptions.ko.projectName);

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

    reloadTable(1);

    logStr = "";
    view = "";
    pdfEn = "";
    pdfEnName = "";
    pdfCn = "";
    pdfCnName = "";
    pdfKo = "";
    pdfKoName = "";
    pdfJa = "";
    pdfJaName = "";
    addMethod = true;

    $('#addEvent').bootstrapValidator("resetForm", true);

    $('#addEvent').bootstrapValidator('addField', 'log', {
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
    }).bootstrapValidator('addField', 'view', {
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
    }).bootstrapValidator('addField', 'pdfEn', {
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
    })


})

//新增按钮
$('#btn_add').click(function () {
    $('#addModal').modal('show');
    addMethod = true
})

//file 文本变化获取值
$('input[type=file]').on('change', function (e) {
    var id = $(this).attr('id');
    var file = e.target.files[0];
    if (file) {
        var fileName = file.name;
        fileName = fileName.replace(".pdf", "").replace(/[^\dA-Za-z\.]/g, "_");
        console.log(fileName);
        var reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onloadend = function () {
            switch (id) {
                case 'log':
                    logStr = reader.result;
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
            case 'log':
                logStr = "";
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
                    stringLength: {
                        min: 1,
                        max: 1,
                        message: ''
                    }
                }
            },

            projectAddress: {
                validators: {
                    notEmpty: {
                        message: '项目地址不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 1,
                        message: ''
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
            startPrice: {
                validators: {
                    notEmpty: {
                        message: '开始单价（ETH）不能为空'
                    }
                }
            },
            endPrice: {
                validators: {
                    notEmpty: {
                        message: '结束单价（ETH）不能为空'
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
            'projectToken': $(' #projectToken').val(),
            'projectNameEn': $(' #projectNameEn').val(),
            'projectNameCn': $(' #projectNameCn').val(),
            'projectNameKo': $(' #projectNameKo').val(),
            'projectNameJa': $(' #projectNameJa').val(),

            'tokenAddress': $(' #tokenAddress').val(),
            'projectAddress': $(' #projectAddress').val(),
            'softCap': numeral($('#softCap').val()).value(),
            'hardCap': numeral($('#hardCap').val()).value(),
            'minPurchaseAmount': numeral($('#minPurchaseAmount').val()).value(),

            'startPrice': numeral($('#startPrice').val()).value(),
            'endPrice': numeral($('#endPrice').val()).value(),
            'startTimeLong': new Date($('#startTimePicker').val()).getMilliseconds(),
            'endTimeLong': new Date($('#endTimePicker').val()).getMilliseconds(),

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

            'log': logStr,
            'view': view

            // 'pdfEn': pdfEn,
            // 'pdfEnName': pdfEnName,
            // 'pdfCn': pdfCn,
            // 'pdfCnName': pdfCnName,
            // 'pdfKo': pdfKo,
            // 'pdfKoName': pdfKoName,
            // 'pdfJa': pdfJa,
            // 'pdfJaName': pdfJaName
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

        $('#startTimePicker').datetimepicker().val(getNowFormatDate(getAddDay(1))).datetimepicker('update');
        $('#endTimePicker').datetimepicker().val(getNowFormatDate(getAddDay(3))).datetimepicker('update');

        $('.time').change(function () {
            var start = new Date($('#startTimePicker').val());
            var end = new Date($('#endTimePicker').val());
            if (start >= end || start < new Date()) {
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


    });
    function checkPrice() {
        var startPrice = numeral($('#startPrice').val()).value();
        var endPrice = numeral($('#endPrice').val()).value();

        if (!startPrice || !endPrice || startPrice > endPrice) {
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

        if (!hardCap || !softCap || !minPurchaseAmount || hardCap <= softCap || minPurchaseAmount >= softCap) {
            layer.msg("软硬顶或认购数量设置有误", {
                time: 2000,
                icon: 0,
                shift: 1
            });
            return false;
        }
        return true;
    };


})
