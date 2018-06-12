$(function () {
    appInfoId = $("#app-info-id").val();
    appKey = $("#appKey").text();
    var type, blackShow = false;
    $("#blackShow,#elementFShow").selected();
    $(".reset,.present,.start-use,.stop-use,.change").off().on("click", function () {
        appInfoDetailIndex = $(this).parents("tr").index();
        appInfoDetailId = Number($(".appInfo-table tbody tr").eq(appInfoDetailIndex).find("td").eq(0).data("id"));
    });
    //产品：价格
    $("body").on("click", ".price", function () {
        taskType = $(this).parents("tr").find("td").eq(2).text();
        $("#priceType").load(contextPath + '/appInfo/detail/price/' + taskType + '/' + appKey);
    });
    //启用
    $(".startUse").off().on("click", function () {
        getData("/appInfo/detail/" + appInfoDetailId + "?action=enable", "POST", {}, function (data) {
            if (data.successed) {
                $('#start-use').modal('close');
                loads("/appInfo/detail/" + $("#app-info-id").val());
            } else {

                layer.tips(data.message, $('.startUse'), {
                    tips: [1, '#d4202c'],
                    time: 1500
                });
            }
        })
    });
    //停用
    $(".stopUse").off().on("click", function () {
        getData("/appInfo/detail/" + appInfoDetailId + "?action=disable", "POST", {}, function (data) {
            if (data.successed) {
                $('#stop-use').modal('close');
                loads("/appInfo/detail/" + $("#app-info-id").val());
            } else {
                layer.tips(data.message, $('.stopUse'), {
                    tips: [1, '#d4202c'],
                    time: 1500
                });

            }
        })
    });
    //清零
    $(".resetZero").off().on("click", function () {
        getData("/appInfo/detail/zero/" + appInfoDetailId + "?action=disable", "POST", {}, function (data) {
            if (data.successed) {
                $('#reset').modal('close');
                loads("/appInfo/detail/" + $("#app-info-id").val());
            } else {
                layer.tips(data.message, $('.resetZero'), {
                    tips: [1, '#d4202c'],
                    time: 1500
                });

            }
        })
    });
    //赠送
    $(".present-times").off().on("click", function () {
        var presentTimes = $.trim($("#presentTimes").val());
        var presentDesc = $.trim($("#presentDesc").val());
        if (!presentTimes) {
            layer.tips("请输入赠送次数", $('.present-times'), {
                tips: [1, '#d4202c'],
                time: 1500
            });

            return false;
        }
        if (!presentDesc) {
            layer.tips("请输入描述内容", $('.present-times'), {
                tips: [1, '#d4202c'],
                time: 1500
            });
            return false;
        }
        if (presentDesc.length > 64) {
            layer.tips("请将描述内容控制在64个字符以内", $('.present-times'), {
                tips: [1, '#d4202c'],
                time: 1500
            });

            return false;
        }
        var times = Number(presentTimes).toFixed(0);
        getData("/appInfo/detail/give/" + appInfoDetailId, "POST", {
            freeCount: times,
            freeDesc: presentDesc
        }, function (data) {
            if (data.successed) {
                $('#present').modal('close');
                loads("/appInfo/detail/" + $("#app-info-id").val());
            } else {
                layer.tips(data.message, $('.present-times'), {
                    tips: [1, '#d4202c'],
                    time: 1500
                });

            }
        })
    });
    //修改每日最大调用次数
    $(".dailyMaxCount").off().on("click", function () {
        if (!$.trim($("#dailyMaxCount").val())) {
            layer.tips("请输入每日最大调用次数", $('.dailyMaxCount'), {
                tips: [1, '#d4202c'],
                time: 1500
            });

            return false;
        }
        var times = Number($.trim($("#dailyMaxCount").val())).toFixed(0);
        getData("/appInfo/detail/dailyMax/" + appInfoDetailId, "PUT", {dailyMax: times}, function (data) {
            if (data.successed) {
                $('#change').modal('close');
                loads("/appInfo/detail/" + $("#app-info-id").val());
            } else {
                layer.tips(data.message, $('.dailyMaxCount'), {
                    tips: [1, '#d4202c'],
                    time: 1500
                });
            }
        })
    });

    //开始计费
    $(".start-fees").off().on("click", function () {
        appInfoDetailIndex = $(this).parents("tr").index();
        appInfoDetailId = Number($(".appInfo-table tbody tr").eq(appInfoDetailIndex).find("td").eq(0).data("id"));
        type = $(this).parents("tr").find("td").eq(2).text();
        if (type === "black") {
            blackShow = 2;
            $(".blackShow").show();
            $(".elementFShow").hide();
        } else if (type === "element4" || type === "bank_element2" || type === "bank_element3") {
            blackShow = 1;
            $(".elementFShow").show();
            $(".blackShow").hide();
        } else {
            blackShow = 0;
            $(".blackShow,.elementFShow").hide();
        }
        var choose = $('#choose').data('choose');
        get_Data("/appInfo/detail/cycle/" + $("#app-info-id").val() + "/" + appInfoDetailId, function (data) {
//            if(data.successed){
            $('#blackShow').selected('destroy').val(data.chargeType).selected();
            if (data.currentStatus === 1) {
                $("#fee-select-div").html("<div>" + data.cycle + "</div>");
                $("#my-date-div").html("<div>" + data.startMonth + "</div>");
            } else {
                var str = '<select id="fee-select" data-am-selected="{maxHeight: 100}" style="width: 100%;">' +
                    '<option value="1">1个月</option>\n' +
                    '<option value="2">2个月</option>\n' +
                    '<option value="3">3个月</option>\n' +
                    '<option value="4">4个月</option>\n' +
                    '<option value="5">5个月</option>\n' +
                    '<option value="6">6个月</option>\n' +
                    '<option value="7">7个月</option>\n' +
                    '<option value="8">8个月</option>\n' +
                    '<option value="9">9个月</option>\n' +
                    '<option value="10">10个月</option>\n' +
                    '<option value="11">11个月</option>\n' +
                    '<option value="12">12个月</option></select>';
                $("#fee-select-div").html(str);
                $("#my-date-div").html('<input type="text" class="am-form-field" placeholder="' + choose + '" readonly id="my-date">');
                $('#fee-select').selected();
                $('#my-date').datepicker({format: 'yyyy-mm', viewMode: 'years', minViewMode: 'months'});
            }
//            }else{
//                alert(data.message);
//            }
        })
    });
    //开始计费
    $(".startFees").off().on("click", function () {
        if ($("#my-date").length > 0 && !$("#my-date").val()) {
            layer.tips("请选择起始计费月份", $('.startFees'), {
                tips: [1, '#d4202c'],
                time: 1500
            });

            return false;
        }

        var objData;
        var charge_type;
        if (blackShow === 0) charge_type = '0';
        if (blackShow === 2) charge_type = $("#blackShow").val();
        if (blackShow === 1) charge_type = $("#elementFShow").val();
        if ($("#my-date").length > 0) {
            objData = {
                cycle: $("#fee-select").val(),
                startMonth: $("#my-date").val() + "-01",
                id: $("#app-info-id").val(),
                chargeType: charge_type
            }
        } else {
            objData = {id: $("#app-info-id").val(), chargeType: charge_type}
        }
        getData("/appInfo/detail/cycle/" + appInfoDetailId, "POST", objData, function (data) {
            if (data.successed) {
                $('#start-fees').modal('close');
                loads("/appInfo/detail/" + $("#app-info-id").val());
            } else {
                layer.tips(data.message, $('.startFees'), {
                    tips: [1, '#d4202c'],
                    time: 1500
                });

            }
        })
    });
    //查看任务详情
    $(".a-detail").off().on("click", function () {
        var called = $('#calledCount').data('called');
        var called_succeed = $('#calledSucceed').data('succeed');
        var hitCount = $('#hitCount').data('hit');
        var today = $('#today').data('today');
        var toMonth = $('#toMonth').data('month');
        var total = $('#total').data('total');
        appInfoDetailIndex = $(this).parents("tr").index();
        var appInfoDetailTaskType = $(".appInfo-table tbody tr").eq(appInfoDetailIndex).find("td").eq(2).text();
        var textSpan = $(this).text();
        get_Data("/appInfo/detail/" + appKey + "/" + appInfoDetailTaskType, function (data) {
//            if(data.successed){
//                $('#task-detail').modal('close');
//                loads("/appInfo/detail/"+$("#app-info-id").val());
//            }else{
//                alert(data.message);
//            }        '+Number(data.unitPrice/100).toFixed(2)+'
            if (appInfoDetailTaskType == 'black' || appInfoDetailTaskType == 'blackcrime') {
                var str = '<thead><tr><th></th><th>' + called + '</th><th>' + called_succeed + '</th><th>' + hitCount + '</th></tr></thead>' +
                    '<tbody><tr><td style="text-align:center;">' + today + '</td><td style="text-align:center;">' + data.todayCallCount + '</td><td style="text-align:center;">' + data.todaySuccCallCount + '</td><td style="text-align:center;">' + data.todayMatchCallCount + '</td></tr>' +
                    '<tr><td style="text-align:center;">' + toMonth + '</td><td style="text-align:center;">' + data.monthCallCount + '</td><td style="text-align:center;">' + data.monthSuccCallCount + '</td><td style="text-align:center;">' + data.monthMatchCallCount + '</td></tr>' +
                    '<tr><td style="text-align:center;">' + total + '</td><td style="text-align:center;">' + data.totalCallCount + '</td><td style="text-align:center;">' + data.totalSuccCallCount + '</td><td style="text-align:center;">' + data.totalhMatchCallCount + '</td></tr>' +
                    '</tbody>';
            } else {
                var str = '<thead><tr><th></th><th>' + called + '</th><th>' + called_succeed + '</th></tr></thead>' +
                    '<tbody><tr><td style="text-align:center;">' + today + '</td><td style="text-align:center;">' + data.todayCallCount + '</td><td style="text-align:center;">' + data.todaySuccCallCount + '</td></tr>' +
                    '<tr><td style="text-align:center;">' + toMonth + '</td><td style="text-align:center;">' + data.monthCallCount + '</td><td style="text-align:center;">' + data.monthSuccCallCount + '</td></tr>' +
                    '<tr><td style="text-align:center;">' + total + '</td><td style="text-align:center;">' + data.totalCallCount + '</td><td style="text-align:center;">' + data.totalSuccCallCount + '</td></tr>' +
                    '</tbody>';
            }
            $(".taskType-title").text(textSpan + "数据信息");
            $(".taskType-table").html(str);
        });
    });
    //过滤查询、分页
    var pageSize = 10, startRowIndex = $("#startRowIndex").val(), totalRecords = $("#totalRecords").val(), param = 1;

    function renderData(param, start) {
        var obj = {
            appInfoId: appInfoId,
            "startRowIndex": start === 0 ? 0 : param * pageSize - 10,
            pageSize: pageSize,
            typeNameLike: $.trim($('#typeNameLike').val()),
            taskTypeLike: $.trim($('#taskTypeLike').val()),
            usable: $('input[name="isEnable"]:checked').val(),
            curStatus: $('input[name="feeStyle"]:checked').val(),
            chargeTypeEqual: $('input[name="chargeTypeEqual"]:checked').val()
        };
        getData('/appInfo/taskTypeList', 'POST', obj, function (data) {
            $("#content").html(data);
        })
    }

    layui.use(['laypage'], function () {
        var laypage = layui.laypage;
        laypage.render({
            elem: 'page',
            limit: pageSize,
            curr: Math.ceil(startRowIndex / pageSize) + 1,
            count: totalRecords,
            theme: '#1E9FFF',
            next: '>>',
            prev: '<<',
            jump: function (obj, first) {
                param = obj.curr;
                if (!first) {
                    renderData(param);
                }
            }
        });
    });
    $(".search-btn").on("click", function () {
        renderData(param, 0);
    });
    $(".reset-btn").on("click", function () {
        $("#typeNameLike").val("");
        $("#taskTypeLike").val("");
        $('input[name="feeStyle"]:checked').prop('checked', false);
        $('input[name="isEnable"]:checked').prop('checked', false);
        $('input[name="chargeTypeEqual"]:checked').prop('checked', false);
    });
});