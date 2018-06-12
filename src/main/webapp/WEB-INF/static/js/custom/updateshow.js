/**
 * Created by user on 2018/4/11.
 */

jQuery(document).ready(function ($) {
    $("#undo_redo").multiselect({
        afterMoveToRight: function () {
            afterMove();
        },
        afterMoveToLeft: function () {
            afterMove();
        }
    });

    $("#submit_order").click(function () {
        layer.msg('提交中', {
            icon: 16,
            time: 500 //2秒关闭（如果不配置，默认是3秒）
        }, function () {
            submit(CHANGETYPE);
            $("#undo_redo_to").attr("disabled", false);
        });
    });

    /*            var cpLock = false;
     $('#searchthis').on('compositionstart', function () {
     cpLock = true;
     });
     $('#searchthis').on('compositionend', function () {
     cpLock = false;
     });
     */
    toastr.options.positionClass = 'toast-bottom-right';
});

function thefinalFuc(type) {

    CHANGETYPE = type;

    initData(type);

    $("#loadMore").click(function () {
        loadMore(type);
    });

    $("#searchthat").on('click', function () {
        search(type);
    });


}


function afterMove() {
    $("#undo_redo_to").attr("disabled", true);

    layer.msg('提交中', {
        icon: 16,
        time: 500 //2秒关闭（如果不配置，默认是3秒）
    }, function () {
        submit(CHANGETYPE);
        $("#undo_redo_to").attr("disabled", false);
    });
}


function getAllBillBoard(pageNum, pageSize, indexShow, name, type) {
    $.getJSON(contextPath + '/management/homepage/option', {
            'pageNum': pageNum, 'pageSize': pageSize,
            'indexShow': indexShow, 'name': name, 'showType': type
        },
        function (result) {
            result.data.list.forEach(function (item) {
                if (indexShow == 0) {
                    $("#undo_redo").append('<option value="' + item.id + '">' + item.title + '</option>');
                }
                else {
                    $("#undo_redo_to").append('<option value="' + item.id + '">' + item.title + '</option>');
                }
            });
            if (indexShow == 0) {
                $("#pageNum").val(1 + Number($("#pageNum").val()))
                $("#totalNum").html(result.data.total);
                if (!result.data.hasNextPage) {
                    var loadbutton = $("#loadMore");
                    loadbutton.attr("disabled", true);
                    loadbutton.html("无更多数据");
                } else {
                    var loadbutton = $("#loadMore");
                    loadbutton.attr("disabled", false);
                    loadbutton.html("点击加载更多");
                }
            }
            if (indexShow == 1) {
                $("#selectNum").html(result.data.total);
            }
        }
    )
}


function submit(type) {
    var array = new Array();
    $('#undo_redo_to option').each(function () {
        array.push($(this).val())
    });
    var maxNum = Number($('input[name="maxNum"]').val());
    if (maxNum == null || maxNum == '') {
        toastr.warning("请输入最大条数");
        return;
    } else if (array.length > maxNum) {
        toastr.warning("选择条数太多");
        setTimeout(function () {
            initData(type);
        }, 1000);
        return;
    }
    var obj = {'showList': array, 'showType': type, 'maxNum': maxNum};
    $.ajax({
        url: contextPath + '/management/homepage/option',
        type: 'PUT',
        data: JSON.stringify(obj),
        dataType: 'json',
        contentType: 'application/json',
        success: function (result) {
            if (result.success) {
                toastr.success(result.message);
                setTimeout(function () {
                    initData(type);
                }, 1000);
            } else {
                toastr.error(result.message);
                setTimeout(function () {
                    initData(type);
                }, 1000);
            }
        }
    })
}

function loadMore(type) {
    var pageNum = $("#pageNum").val();
    getAllBillBoard(pageNum, 20, 0, null, type);
}

function search(type) {
    $("#pageNum").val(0);
    var name = $("#searchthis").val();
    $("#undo_redo").html('');
    if (name == null || name == "")
        getAllBillBoard(1, 20, 0, null, type);
    else {
        getAllBillBoard(1, 20, 0, name, type);
    }
}

function initData(type) {

    $("#undo_redo").html('');
    $("#undo_redo_to").html('');

    getAllBillBoard(1, 20, 0, null, type);

    getAllBillBoard(1, 20, 1, null, type);

}