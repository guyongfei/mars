/**
 * Created by user on 2018/4/15.
 */

var column1 = [{
    checkbox: true
}, {
    field: 'infoKeyZh',
    title: '配置名(中文)'
}, {
    field: 'infoKeyEn',
    title: '配置名(英文)'
}, {
    field: 'infoValueZh',
    title: '配置值(中文)'
}, {
    field: 'infoValueEn',
    title: '配置值(英文)'
}, {
    field: 'pictureUrl',
    title: '图片',
    formatter: function (value, row, index) {
        return '<img  src="' + value + '" class="img-rounded" >';
    }
}];


var column2 = [{
    checkbox: true
}, {
    field: 'partnerName',
    title: '合作伙伴'
}, {
    field: 'pictureUrl',
    title: '图片',
    formatter: function (value, row, index) {
        return '<img  src="' + value + '" class="img-rounded" >';
    }
}];

var column4 = [{
    checkbox: true
}, {
    field: 'linkName',
    title: '网站'
}, {
    field: 'pictureUrl',
    title: '图片',
    formatter: function (value, row, index) {
        return '<img  src="' + value + '" class="img-rounded" >';
    }
}];


var TableInit = function (tableId, url, columns, toolbar) {
    var oTableInit = new Object();
    //初始化Table
    oTableInit.Init = function () {
        $('#' + tableId).bootstrapTable({
            url: url,         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            toolbar: '#' + toolbar,                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            //queryParams: oTableInit.queryParams,//传递参数（*）
            sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            strictSearch: true,
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
            showToggle: false,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            responseHandler: function (res) {
                return res.data                 //数据
            },
            columns: columns
        });
    };

    /* //得到查询的参数
     oTableInit.queryParams = function (params) {
     var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
     limit: params.limit,   //页面大小
     offset: params.offset,  //页码
     departmentname: $("#txt_search_departmentname").val(),
     statu: $("#txt_search_statu").val()
     };
     return temp;
     };*/

    return oTableInit;
};

var ButtonInit = function (index, addUrl, updateUrl, updatePictureUrl, deleteUrl) {
    var oInit = new Object();
    var postdata = {};

    oInit.Init = function () {
        $("#btn_add" + index).click(function () {
            $("#form" + index)[0].reset();
            document.getElementById("pictureDiv" + index)
                .style.backgroundImage = '';
            $("#modalLabel" + index).text("新增");
            $('#modal' + index).modal();
            $("#btn_submit" + index).unbind();
            $("#btn_submit" + index).attr("disabled", false);
            $("#btn_submit" + index).click(function () {
                if (!checkValues(index, true)) {
                    toastr.warning('请填写必要项');
                    return;
                }
                ;
                if (!checkPictureSize(index)) {
                    return;
                }
                $.ajax({
                    url: addUrl,
                    type: 'POST',
                    data: JSON.stringify($("#form" + index).serializeObject()),
                    dataType: 'json',
                    contentType: 'application/json',
                    beforeSend: function () {
                        $("#btn_submit" + index).attr({disabled: "disabled"});
                    },
                    success: function (result) {
                        if (result.success) {
                            $("#modal" + index).modal("hide");
                            toastr.success('提交成功');
                            setTimeout(function () {
                                refreshTable(index);
                            }, 1500);
                        } else {
                            $("#modal" + index).modal("hide");
                            toastr.error('提交失败');
                        }
                    },
                    complete: function () {
                        $("#btn_submit" + index).attr("disabled", false);
                    }
                });
            })
        });
        $("#btn_edit" + index).click(function () {
            if (!getSelected(index))
                return;  //回填数据

            $("#modalLabel" + index).text("修改");
            $('#modal' + index).modal();


            $("#btn_submit" + index).unbind();
            $("#btn_submit" + index).attr("disabled", false);
            $("#btn_submit" + index).click(function () {
                if (!checkValues(index, false)) { //表单验证
                    toastr.warning('请填写必要项');
                    return;
                }
                ;
                if (!checkPictureSize(index)) {
                    return;
                }
                ;
                var showMsg = false;
                $.ajax({
                    url: updateUrl,
                    type: 'PUT',
                    data: JSON.stringify($("#form" + index).serializeObject()),
                    dataType: 'json',
                    async: false,
                    contentType: 'application/json',
                    beforeSend: function () {
                        $("#btn_submit" + index).attr({disabled: "disabled"});
                    },
                    success: function (result) {
                        if (result.success) {
                            $("#modal" + index).modal("hide");
                            showMsg = true;
                        } else {
                            $("#modal" + index).modal("hide");
                            showMsg = false;
                        }
                    },
                });

                if ($("#picture" + index).val() == null || $("#picture" + index).val() == '') {
                    showMsg ? toastr.success("修改成功") : toastr.error("修改失败");
                    setTimeout(function () {
                        refreshTable(index);
                    }, 1500);
                    return;
                }
                $.ajax({
                    url: updatePictureUrl,
                    type: 'PUT',
                    data: JSON.stringify($("#form" + index).serializeObject()),
                    dataType: 'json',
                    contentType: 'application/json',
                    success: function (result) {
                        if (result.success) {
                            $("#modal" + index).modal("hide");
                            toastr.success("修改成功");
                            setTimeout(function () {
                                refreshTable(index);
                            }, 1500);
                        } else {
                            $("#modal" + index).modal("hide");
                            toastr.warning("内容修改成功 图片上传失败");
                        }
                    },
                    complete: function () {
                        $("#btn_submit" + index).attr("disabled", false);
                    }
                });
            })
        });

        $("#btn_delete" + index).click(function () {
            var getSelectRows = $("#table" + index).bootstrapTable('getSelections', function (row) {
                return row;
            });
            if (getSelectRows.length == 0) {
                toastr.warning('请至少选择一项');
                return;
            }
            ;
            var ids = [];
            for (var i = 0; i < getSelectRows.length; i++) {
                for (var Key in getSelectRows[i]) {
                    if (Key == 'id') {
                        ids.push(getSelectRows[i][Key]);
                    }
                }
            }

            bootbox.confirm("确认提交", function (result) {
                if (result) {
                    $.ajax({
                        url: deleteUrl,
                        type: 'DELETE',
                        data: JSON.stringify({"id": ids}),
                        dataType: 'json',
                        contentType: 'application/json',
                        success: function (result) {
                            if (result.success) {
                                $("#modal" + index).modal("hide");
                                toastr.success('删除成功');
                                setTimeout(function () {
                                    refreshTable(index);
                                }, 1500);
                            } else {
                                $("#modal" + index).modal("hide");
                                toastr.error('删除失败');
                            }
                        }
                    })
                }
                ;
            })

        });


        $("#conf_picture" + index).change(function () {
            var
                fileInput = document.getElementById("conf_picture" + index),
                preview = document.getElementById("pictureDiv" + index);

            // 清除背景图片:
            $("#picture" + index).val('');
            preview.style.backgroundImage = '';
            // 检查文件是否选择:
            if (!fileInput.value) {
                return;
            }
            // 获取File引用:
            var file = fileInput.files[0];

            if (file.type !== 'image/jpeg' && file.type !== 'image/png' && file.type !== 'image/gif') {
                toastr.warning('请选择jpg, jpeg, png, gif等格式的文件!');
                return;
            } else {
                var imgSize = file.size;
                //alert("图片大小：" + imgSize + "B");
                if (imgSize > 1024 * 1024 * 1) {
                    toastr.warning('图片不能大于1m!');
                }
            }
            // 读取文件:
            var reader = new FileReader();
            reader.onload = function (e) {
                var
                    data = e.target.result; // 'data:image/jpeg;base64,/9j/4AAQSk...(base64编码)...'
                preview.style.backgroundImage = 'url(' + data + ')';
                $("#picture" + index).val(data);
            };
            // 以DataURL的形式读取文件:
            reader.readAsDataURL(file);

        });

    };

    return oInit;
};


function checkPictureSize(index) {
    var fileInput = document.getElementById("conf_picture" + index);

    if (fileInput.value == null || fileInput.value == '') {
        return true;
    }
    var file = fileInput.files[0];
    if (file.type !== 'image/jpg' && file.type !== 'image/jpeg' && file.type !== 'image/png' && file.type !== 'image/gif') {
        toastr.warning('请选择jpg, jpeg, png, gif等格式的文件!');
        return false;
    } else {
        var imgSize = file.size;
        //alert("图片大小：" + imgSize + "B");
        if (imgSize > 1024 * 1024 * 1) {
            toastr.warning('图片不能大于1m!');
            return false;
        }
    }
    return true;
}


function getSelected(index) {
    var getSelectRows = $("#table" + index).bootstrapTable('getSelections', function (row) {
        return row;
    });
    if (getSelectRows.length == 0) {
        toastr.warning('请选择一项');
        return false;
    } else if (getSelectRows.length > 1) {
        toastr.info('选择多项时, 默认为第一项');
    }
    var selectRow = getSelectRows[0];
    //console.log(selectRow);
    for (var Key in selectRow) {
        $("#form" + index + " input").each(function (index, element) {
            if (element.name == Key) {
                element.value = selectRow[Key];
            }
        });
        if (Key == "pictureUrl") {
            document.getElementById("pictureDiv" + index)
                .style.backgroundImage = 'url(' + selectRow[Key] + ')';
        }
    }
    $("#picture" + index).val('');
    return true;
}


function checkValues(index, insert) {
    var flag = true;
    $("#form" + index + " input").each(function (index, element) {
        if (element.name != 'id' && (insert || element.name != 'picture') && element.name != '' && (element.value == null || element.value == '')) {
            flag = false;
        }
    });
    return flag;
}


function refreshTable(index) {
    $('#table' + index).bootstrapTable('refresh');
}


function show(tab) {
    $("#nav").children(".active").removeClass("active");
    $(tab).parent().addClass("active");

    $("#container").children(".show").removeClass("show").addClass("hidden");
    $("#" + $(tab).attr("target")).removeClass("hidden").addClass("show");
}

$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};