var billboardId;
var billboard = {};
var view;
var addMethod = true;

/*
 $('#addEvent').validator().on('submit', function (e) {
 var id = addMethod ? 0 : billboardId;
 var dataJson = {
 'id': billboard.id,
 'titleZh': $(' #titleZh').val(),
 'titleEn': $(' #titleEn').val(),
 'contentZh': $(' #contentZh').val(),
 'contentEn': $(' #contentEn').val(),
 'linkUrl': $(' #linkUrl').val(),
 'picture': view

 }
 console.log(dataJson);
 if (!dataJson.titleZh
 || !dataJson.titleEn
 || !dataJson.contentZh
 || !dataJson.contentEn
 || !dataJson.linkUrl
 ) {
 layer.msg("请先完成必要项", {
 time: 2000,
 icon: 0,
 shift: 1
 }, function () {

 })
 return false;
 }
 if (addMethod) {
 if (!view) {
 layer.msg("请检查图片是否上传完毕", {
 time: 2000,
 icon: 0,
 shift: 1
 }, function () {

 })
 return false;
 }
 }
 var requestType = addMethod ? "post" : "put";
 $.ajax({
 url: contextPath + "/management/billboard",
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
 billboard = {};
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
 return false;
 })
 */

function getBillBoard() {
    if (!billboardId) {
        layer.msg("请选中一个广告项", {
            time: 2000,
            icon: 0,
            shift: 1
        })
        return false;
    }
    $('#addEvent').bootstrapValidator('removeField', 'view');

    addMethod = false;
    $.ajax({
        url: contextPath + "/management/billboard/" + billboardId,
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
                billboard = data.data;
                $(' #titleZh').val(billboard.titleZh);
                $(' #titleEn').val(billboard.titleEn);
                $(' #contentZh').val(billboard.contentZh);
                $(' #contentEn').val(billboard.contentEn);
                $(' #linkUrl').val(billboard.linkUrl);
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


function deleteBillBoard() {
    if ($('#undo_redo_to option:selected').length == 0 && $('#undo_redo option:selected').length == 0) {
        layer.msg("请选中一个广告项", {
            time: 2000,
            icon: 0,
            shift: 1
        })
        return;
    }
    ;
    var array = new Array();
    $('#undo_redo_to option:selected').each(function () {
        array.push($(this).val())
    });
    $('#undo_redo option:selected').each(function () {
        array.push($(this).val())
    });
    bootbox.confirm("确认删除吗", function (result) {
        if (result) {
            $.ajax({
                url: contextPath + "/management/billboard",
                type: 'DELETE',
                contentType: "application/json;charset=UTF-8",
                data: JSON.stringify({'id': array}),
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
                            billboard = {};
                            $('.close').click();
                            initData(1)
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


$(function () {
    $('#updateBillboard').click(function () {
        getBillBoard();
    })

    $('#undo_redo_to,#undo_redo').change(function () {
        billboardId = $(this).children('option:selected').val();
    });


    $("#updateBillboard").tooltip();
    $("#deleteBillboard").click(function () {
        deleteBillBoard();
    });


    //隐藏模态框
    $('#addModal').on('hidden.bs.modal', function () {

        initData(1);

        billboardId = '';

        $('#addEvent')[0].reset();

        $('#addEvent').bootstrapValidator("resetForm", true);

        $('#addEvent').bootstrapValidator('addField', 'view', {
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

    });


    //file 文本变化获取值
    $('input[type=file]').on('change', function (e) {
        var id = $(this).attr('id');
        var file = e.target.files[0];
        var reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onloadend = function () {
            switch (id) {
                case 'view':
                    view = reader.result;
                    break;
                default:
                    break;
            }
        };
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
            titleZh: {
                validators: {
                    notEmpty: {
                        message: '中文标题不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 40,
                        message: '请输入1-40个字符'
                    }
                }
            },
            titleEn: {
                validators: {
                    notEmpty: {
                        message: '英文标题不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 80,
                        message: '请输入1-80个字符'
                    }
                }
            },
            contentZh: {
                validators: {
                    notEmpty: {
                        message: '中文内容不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 500,
                        message: '请输入1-500个字符'
                    }
                }
            },
            contentEn: {
                validators: {
                    notEmpty: {
                        message: '英文内容不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 1000,
                        message: '请输入1-1000个字符'
                    }
                }
            },
            linkUrl: {
                validators: {
                    notEmpty: {
                        message: '链接地址不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 250,
                        message: '请输入1-250个字符'
                    }
                }
            }, view: {
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
        var id = addMethod ? 0 : billboardId;
        var dataJson = {
            'id': billboard.id,
            'titleZh': $(' #titleZh').val(),
            'titleEn': $(' #titleEn').val(),
            'contentZh': $(' #contentZh').val(),
            'contentEn': $(' #contentEn').val(),
            'linkUrl': $(' #linkUrl').val(),
            'picture': view

        };
        var requestType = addMethod ? "post" : "put";
        $.ajax({
            url: contextPath + "/management/billboard",
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
                        billboard = {};
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