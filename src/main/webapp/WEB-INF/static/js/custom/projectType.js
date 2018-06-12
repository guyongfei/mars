var nowId = 0;
var nowPId = 0;
var requestType = "post";

var setting = {
    async: {
        enable: true,
        type: "post",
        url: contextPath + "/management/projecttype/list",
        contentType: 'application/json',
        autoParam: ["id", "name=n", "level=lv"],
        otherParam: {},
        dataFilter: filter
    },
    view: {
        expandSpeed: "",
        showIcon: false,
        addHoverDom: addHoverDom,
        removeHoverDom: removeHoverDom,
        selectedMulti: false,
        showTitle: true// 2 这个开关也要打开，默认是关闭的
    },
    edit: {
        drag: {
            isCopy: false,
            isMove: false
        },
        removeTitle: "删除项目类型",
        enable: true//设置 zTree 是否处于编辑状态
    },
    data: {
        keep: {
            leaf: false,//表示叶子节点不能变成根节点
            parent: true//表示 根节点不能变成叶子节点
        },
        simpleData: {
            enable: true
        },
        key: {
            title: "title"
        }
    },
    callback: {
        beforeRemove: beforeRemove,
        onDblClick: onDblClick
    }
};

function filter(treeId, parentNode, childNodes) {
    if (!childNodes) return null;
    var data = childNodes.data;
    var newChildNodes = new Array();
    for (var i = 0, l = data.length; i < l; i++) {
        var obj = {}
        obj.id = data[i].id;
        obj.pId = data[i].pId;
        obj.pid = data[i].pId;
        obj.eName = data[i].projectTypeEn.replace(/\.n/g, '.');
        obj.cname = data[i].projectTypeZh.replace(/\.n/g, '.');
        obj.name = obj.cname + '【' + obj.eName + '】';
        obj.noRemoveBtn = true;
        obj.noEditBtn = false;
        obj.open = true;
        obj.title = '双击后编辑';
        newChildNodes[i] = obj;
    }
    return newChildNodes;
}
//删除节点
function beforeRemove(treeId, treeNode) {
    if (treeNode.isParent) {
        layer.msg("存在子节点，不能删除", {
            time: 1000,
            icon: 0,
            shift: 1
        }, function () {
        });
        return false;
    } else {
        bootbox.confirm("确认要删除【" + treeNode.name + "】", function (result) {
            if (result) {
                $.ajax({
                    url: contextPath + "/management/projecttype/" + treeNode.id,
                    type: "delete",
                    contentType: "application/json;charset=UTF-8",
//                        data: JSON.stringify(dataJson),
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

                                reloadTree()
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
            reloadTree()
        })
        return false;
    }

}
//修改节点
function onDblClick(e, treeId, treeNode) {
    if (treeNode.isNew) {
        $('#projectTypeNameZh').val('');
        $('#projectTypeNameEn').val('');
        requestType = "post";
        nowPId = treeNode.pId;
    } else {
        $('#projectTypeNameZh').val(treeNode.cname);
        $('#projectTypeNameEn').val(treeNode.eName);
        requestType = "put";
        nowId = treeNode.id;
        nowPId = treeNode.pid;
    }
    $('#addModal').modal('show');
    return false;
}


var newCount = 1;
function addHoverDom(treeId, treeNode) {
    var sObj = $("#" + treeNode.tId + "_span");
    var pid = treeNode.pid;
    if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0) return;
    var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
        + "' title='add node' onfocus='this.blur();'></span>";
    if (pid == 0) {
        sObj.after(addStr);
    }
    var btn = $("#addBtn_" + treeNode.tId);
    if (btn) btn.bind("click", function () {

        if (pid != 0) {
            return false;
        }
        var zTree = $.fn.zTree.getZTreeObj("treeDemo");
        zTree.addNodes(treeNode, {
            id: (100 + newCount),
            pId: pid,
            isNew: true,
            name: "双击后编辑有效"
        });
        return false;
    });
};
function removeHoverDom(treeId, treeNode) {
};

$('#btn_add').click(function () {
    $('#addModal').modal('show');
})


//新增和修改
/*$('#addEvent').validator().on('submit', function (e) {
 if (e.isDefaultPrevented()) {
 layer.msg("请先完成必要项", {
 time: 2000,
 icon: 0,
 shift: 1
 }, function () {
 })
 } else {
 var dataJson = {
 "projectTypeZh": $('#projectTypeNameZh').val(),
 "projectTypeEn": $('#projectTypeNameEn').val(),
 "pId": nowPId,
 "id": nowId
 };
 console.log(dataJson);
 $.ajax({
 url: contextPath + "/management/projecttype",
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
 }
 reloadTree()
 return false;
 })*/


//重新加载树
function reloadTree() {
    $.fn.zTree.init($("#treeDemo"), setting);
}
//清除输入框内容
function clearEvent() {
    $('#projectTypeNameZh').val('');
    $('#projectTypeNameEn').val('');
}
$(function () {
    //加载树
    reloadTree();
    //模态框监听
    $('#addModal').on('hidden.bs.modal', function () {
        clearEvent();
        reloadTree();
        requestType = "post";
        nowPId = 0;
        $('#addEvent').bootstrapValidator("resetForm", true);
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
            projectTypeNameZh: {
                validators: {
                    notEmpty: {
                        message: '中文名称不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 16,
                        message: '请输入1-16个字符'
                    }
                }
            },
            projectTypeNameEn: {
                validators: {
                    notEmpty: {
                        message: '英文名称不能为空'
                    },
                    stringLength: {
                        min: 1,
                        max: 32,
                        message: '请输入1-32个字符'
                    }
                }
            }
        }
    }).on('success.form.bv', function (e) {
        // Prevent form submission
        e.preventDefault();

        /*  // Get the form instance
         var $form = $(e.target);

         // Get the BootstrapValidator instance
         var bv = $form.data('bootstrapValidator');
         console.log($form.serialize())*/

        var dataJson = {
            "projectTypeZh": $('#projectTypeNameZh').val(),
            "projectTypeEn": $('#projectTypeNameEn').val(),
            "pId": nowPId,
            "id": nowId
        };
        console.log(dataJson);
        $.ajax({
            url: contextPath + "/management/projecttype",
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

});