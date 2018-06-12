var logStr;
var view;
var pdfZh;
var pdfZhName;
var pdfEn;
var pdfEnName;
var projectNow;
var addMethod = true;
var projectTypeArr = new Array();

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
                field: 'projectNameZh',
                align: 'center',
                title: '项目名称'
            }, {
                field: 'projectNameEn',
                align: 'center',
                title: 'ProjectName'
            }, {
                title: '是否启用',
                field: 'projectStatus',
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

window.lockEvents = {
    'click .lock': function (e, value, row, index) {
        bootbox.confirm("确认要更改状态", function (result) {
            if (result) {
                $.ajax({
                    url: contextPath + "/management/project/hide/" + row.id,
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
        url: contextPath + "/management/project/" + row.id,
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
                $(' #projectNameZh').val(project.projectNameZh);
                $(' #projectNameEn').val(project.projectNameEn);
                $(' #token').val(project.token);
                $('#grade').val(project.gradeStr);
                $('#projectTypeP').val(project.typePId);
                $(":radio[name='icoV'][value='" + project.ico + "']").prop("checked", "checked");
                $(' #instructionZh').val(project.instructionZh);
                $(' #instructionEn').val(project.instructionEn);
                $(' #contentZh').val(project.contentZh);
                $(' #contentEn').val(project.contentEn);
                $(' #officialLink').val(project.officialLink);
                $(' #whitePaperLinkZh').val(project.whitePaperLinkZh);
                $(' #whitePaperLinkEn').val(project.whitePaperLinkEn);

                $(' #teamScore').val(project.teamScore / 10.0);
                $(' #productScore').val(project.productScore / 10.0);
                $(' #scheduleScore').val(project.scheduleScore / 10.0);
                $(' #commercialSubstanceScore').val(project.commercialSubstanceScore / 10.0);
                $(' #tokensOperationScore').val(project.tokensOperationScore / 10.0);
                //  社交网站列表
                $.each(project.socialList, function (index, value) {
                    var linkUrl = value.linkUrl;
                    var id = value.typeId;
                    console.log(value)
                    if (linkUrl) {
                        $("input[name='socialWebsite'][ids='" + id + "']").val(linkUrl)
                    }
                });
                //项目类型
                var childrenArr = projectTypeArr[project.typePId]
                $("#projectTypeC").empty();
                if (childrenArr) {
                    $.each(childrenArr.children, function (index, obj) {
                        if (obj) {
                            $("#projectTypeC").append("<option value='" + obj.id + "'>" + obj.projectTypeZh + "</option>");
                        }
                    });
                }
                $('#projectTypeC').val(project.typeId);

                $('#addModal').modal('show')
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

//初始化加载下拉框
$(function () {
    //项目类型
    $.get(contextPath + "/management/projecttype/list", function (data) {
        projectTypeArr = data.data;
        if (projectTypeArr) {
            $("#projectTypeP").empty();
            $("#projectTypeC").empty();
            $("#projectTypeP").append("<option value=''>请选择</option>");
            $.each(projectTypeArr, function (index, obj) {
                if (obj) {
                    if (obj.pId == 0) {
                        $("#projectTypeP").append("<option value='" + obj.id + "'>" + obj.projectTypeZh + "</option>");
                    }
                }
            });
        }
    });
    $("#projectTypeP").bind("change", function () {
        var id = $(this).val();
        var childrenArr = projectTypeArr[id]
        $("#projectTypeC").empty();
        $("#projectTypeC").append("<option value=''>请选择</option>");
        if (childrenArr) {
            $.each(childrenArr.children, function (index, obj) {
                if (obj) {
                    $("#projectTypeC").append("<option value='" + obj.id + "'>" + obj.projectTypeZh + "</option>");
                }
            });
        }
    })
});

//项目类型
$.get(contextPath + "/project/type", function (data) {
    var list = data.data;
    if (list) {
        $("#projectType").empty();
        $.each(list, function (index, obj) {
            $("#projectType").append();
        });
    }
});

//社交网站
$.get(contextPath + "/management/social/list", function (data) {
    var list = data.data;
    if (list) {
        $("#socialWebSite").empty();
        $.each(list, function (index, obj) {
            $('#' + obj.linkName).attr('ids', obj.id);
        });
    }
});
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
    pdfZh = "";
    pdfEn = "";
    pdfZhName = "";
    pdfEnName = "";
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
    }).bootstrapValidator('addField', 'pdfZh', {
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
        console.log(fileName)
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
                case 'pdfZh':
                    pdfZh = reader.result;
                    pdfZhName = fileName;
                    break;
                case 'pdfEn':
                    pdfEn = reader.result;
                    pdfEnName = fileName;
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
                pdfZh = "";
                pdfZhName = "";
                break;
            case 'pdfEn':
                pdfEn = "";
                pdfEnName = "";
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
            projectNameZh: {
                validators: {
                    notEmpty: {
                        message: '中文项目名称不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 16,
                        message: '请输入1-16个字符'
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
            grade: {
                validators: {
                    notEmpty: {
                        message: '项目评级不能为空'
                    }
                }
            },

            icoV: {
                validators: {
                    notEmpty: {
                        message: 'ICO不能为空'
                    }
                }
            },
            projectTypeP: {
                validators: {
                    notEmpty: {
                        message: '项目一级类型不能为空'
                    }
                }
            },
            projectTypeC: {
                validators: {
                    notEmpty: {
                        message: '项目二级类型不能为空'
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

            instructionZh: {
                validators: {
                    notEmpty: {
                        message: '项目简介不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 250,
                        message: '请输入250个字符以下的网址'
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
                        message: '请输入250个字符以下的网址'
                    }
                }
            },

            contentZh: {
                validators: {
                    notEmpty: {
                        message: '中文项目详情不能为空'
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

            whitePaperLinkZh: {
                validators: {
                    notEmpty: {
                        message: '中文白皮书地址不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 250,
                        message: '请输入250个字符以下的网址'
                    }
                }
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
            socialWebsite: {
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
            scheduleScore: {
                validators: {
                    notEmpty: {
                        message: '项目进展得分不能为空'
                    },
                    regexp: {
                        regexp: /^15$|^(\d|1[0-4])$|^(\d|1[0-4])\.\d$/,
                        message: '请输入0-15之间的整数或小数，小数保留一位'
                    }
                }
            }, commercialSubstanceScore: {
                validators: {
                    notEmpty: {
                        message: '商业实质得分不能为空'
                    },
                    regexp: {
                        regexp: /^40$|^(\d|[1-3]\d)$|^(\d|[1-3]\d)\.\d$/,
                        message: '请输入0-40之间的整数或小数，小数保留一位'
                    }
                }
            }, tokensOperationScore: {
                validators: {
                    notEmpty: {
                        message: '代币运营得分不能为空'
                    },
                    regexp: {
                        regexp: /^10$|^(\d)$|^(\d)\.\d$/,
                        message: '请输入0-10之间的整数或小数，小数保留一位'
                    }
                }
            }, productScore: {
                validators: {
                    notEmpty: {
                        message: '技术及应用得分不能为空'
                    },
                    regexp: {
                        regexp: /^15$|^(\d|1[0-4])$|^(\d|1[0-4])\.\d$/,
                        message: '请输入0-15之间的整数或小数，小数保留一位'
                    }
                }
            }, teamScore: {
                validators: {
                    notEmpty: {
                        message: '团队得分不能为空'
                    },
                    regexp: {
                        regexp: /^20$|^(\d|1\d)$|^(\d|1\d)\.\d$/,
                        message: '请输入0-20之间的整数或小数，小数保留一位'
                    }
                }
            },
            pdfZh: {
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
        var id = addMethod ? 0 : projectNow.id;

        var dataJson = {
            'id': id,
            'projectNameZh': $(' #projectNameZh').val(),
            'projectNameEn': $(' #projectNameEn').val(),
            'token': $(' #token').val(),
            'ico': $("input[name='icoV']:checked").val(),
            'gradeStr': $(' [name=grade]').val(),
            'projectType': $(' [name=projectTypeC]').val(),
            'instructionZh': $(' #instructionZh').val(),
            'instructionEn': $(' #instructionEn').val(),
            'contentZh': $(' #contentZh').val(),
            'contentEn': $(' #contentEn').val(),
            'officialLink': $(' #officialLink').val(),
            'whitePaperLinkZh': $(' #whitePaperLinkZh').val(),
            'whitePaperLinkEn': $(' #whitePaperLinkEn').val(),

            'teamScore': $(' #teamScore').val(),
            'productScore': $(' #productScore').val(),
            'scheduleScore': $(' #scheduleScore').val(),
            'commercialSubstanceScore': $(' #commercialSubstanceScore').val(),
            'tokensOperationScore': $(' #tokensOperationScore').val(),

            'logStr': logStr,
            'view': view,
            'pdfZh': pdfZh,
            'pdfZhName': pdfZhName,
            'pdfEn': pdfEn,
            'pdfEnName': pdfEnName
        };

        console.log(dataJson);


        //获取社交网站和交易所网站
        var socialList = new Array()
        var socialId = 0;
        $.each($("input[name='socialWebsite']"), function () {
            var typeId = $(this).attr('ids')
            var linkUrl = $(this).val()
            if (linkUrl) {
                var social = {}
                social.typeId = typeId;
                social.linkUrl = linkUrl;
                socialList[socialId++] = social;
            }
        });
        dataJson.socialList = socialList;
        dataJson.exchangeList = new Array();
        dataJson.locationEn = 'ZH';
        dataJson.locationZh = '中国';
        dataJson.accepting = 'btc';


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


})
