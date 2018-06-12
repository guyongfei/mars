var userId;
var user = {};
var view;


function getUser() {
    if (!userId) {
        layer.msg("请选中一个用户", {
            time: 2000,
            icon: 0,
            shift: 1
        })
        return false;
    }
    $('#addEvent').bootstrapValidator('removeField', 'view');

    addMethod = false;
    $.ajax({
        url: contextPath + "/management/user/" + userId,
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
                user = data.data;
                $(' #email').text(user.email);
                $(' #nickname').val(user.nickname);
                $(' #password').val('');
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


function hideUser() {
    if ($('#undo_redo_to option:selected').length == 0 && $('#undo_redo option:selected').length == 0) {
        layer.msg("请选中一个用户", {
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
    bootbox.confirm("确认要状态变更吗？", function (result) {

        if (result) {
            $.ajax({
                url: contextPath + "/management/user/hide/" + userId,
                type: 'PUT',
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
                            user = {};
                            $('.close').click();
                            initData(3)
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
    $('#updateUser').click(function () {
        getUser();
    })

    $('#undo_redo_to,#undo_redo').change(function () {
        userId = $(this).children('option:selected').val();
    });


    $("#updateUser").tooltip();

    $("#hideUser").click(function () {
        hideUser();
    });

    $("#undo_redo").click(function () {
        var $undoRedoTo = $('#undo_redo option:selected');
        $undoRedoTo.each(function () {
            if ($(this).html().slice(-5) == '(已冻结)') {
                $('#hideUser').removeClass('btn-danger').addClass('btn-success');
            } else {
                $('#hideUser').removeClass('btn-success').addClass('btn-danger');
            }
        })
    });

    $("#undo_redo_to").click(function () {
        var $undoRedoTo = $('#undo_redo_to option:selected');
        $undoRedoTo.each(function () {
            if ($(this).html().slice(-5) == '(已冻结)') {
                $('#hideUser').removeClass('btn-danger').addClass('btn-success');
            } else {
                $('#hideUser').removeClass('btn-success').addClass('btn-danger');
            }
        })
    });


    //隐藏模态框
    $('#addModal').on('hidden.bs.modal', function () {

        initData(3);

        userId = '';

        $('#addEvent')[0].reset();

        $('#addEvent').bootstrapValidator("resetForm", true);

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
            nickname: {
                validators: {
                    notEmpty: {
                        message: '昵称不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 15,
                        message: '请输入1-15个字符'
                    }
                }
            },
            password: {
                validators: {
                    stringLength: {
                        min: 6,
                        max: 20,
                        message: '请输入6-20个字符'
                    }
                }
            },

        }
    }).on('success.form.bv', function (e) {

        e.preventDefault();
        var dataJson = {
            'nickname': $(' #nickname').val(),
            'password': $(' #password').val()
        };
        $.ajax({
            url: contextPath + "/management/user/" + userId,
            type: 'PUT',
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
                        user = {};
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