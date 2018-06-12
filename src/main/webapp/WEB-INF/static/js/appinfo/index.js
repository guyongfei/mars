//产品-任务-阶梯价格增加
var oldTimes, newTimes, priceValue, prevTimes, prevValue, arrayValue = [], oldPrice = 0, id = 0;
var count = $('#appinfo_count').data('count');
var singlePrice = $('#appinfo_sinPrice').data('sinprice');
var confirm = $('#appinfo_confirm').data('confirm');
var cancel = $('#appinfo_cancel').data('cancel');
var add = $('#appinfo_add').data('add');
var del = $('#appinfo_del').data('del');
var more = $('#appinfo_more').data('more');
function makeTr(oldtimes, newTimes, value) {
    return '<tr id="newAdd" data-value="2">\n' +
        '<td class="price-td">\n' +
        '<input class="price-input old-times" type="text" disabled value="' + oldtimes + '">\n' +
        '<span class="span-position">~</span>\n' +
        '<input class="price-input new-times" type="number" placeholder="' + count + '" value="' + newTimes + '">\n' +
        '</td>\n' +
        '<td><input class="price-input price-stairs" type="number" placeholder="' + singlePrice + '" value="' + value + '"></td>\n' +
        '<td class="price-btn">\n' +
        '<div class="am-btn am-btn-default am-btn-xs am-text-secondary" id="priceAdd">' + confirm + '</div>\n' +
        '<div class="am-btn am-btn-default am-btn-xs am-text-danger" id="priceCancel">' + cancel + '</div>\n' +
        '</td>' +
        '</tr>';
}
//按钮
var btnStr = '<div class="am-btn am-btn-default am-btn-xs am-text-secondary" id="showAdd">' + add + '</div>' +
    '<div class="am-btn am-btn-default am-btn-xs am-text-danger" id="priceDel">' + del + '</div>';
//添加
$("body").on("click", "#showAdd", function () {
    if ($(this).parents("tr").find("td").eq(0).find("em").length > 0 && $(this).parents("tr").find("td").eq(0).find("em").data("value") == 0) {
        $(this).parents("tr").remove();
        var start = '<tr data-value="1">\n' +
            ' <td class="price-td">\n' +
            ' <input class="price-input old-times" type="text" disabled value="0">\n' +
            ' <span class="span-position">~</span>\n' +
            ' <input class="price-input new-times" type="number" name="times_0" placeholder="' + count + '" value="">\n' +
            ' </td>\n' +
            ' <td><input class="price-input price-stairs" type="number" name="price_0" placeholder="' + singlePrice + '"\n' +
            '  value=""></td>\n' +
            '  <td class="price-btn">\n' +
            ' <div class="am-btn am-btn-default am-btn-xs am-text-secondary" id="priceAdd">' + confirm + '</div>\n' +
            '  <div class="am-btn am-btn-default am-btn-xs am-text-danger" style="display: none;"\n' +
            '  id="priceDel">' + cancel + '\n' +
            '  </div>\n' +
            '  </td>\n' +
            '  </tr>';
        $("#assignTaskTable tbody,#assignTable tbody").append(start);
    } else {
        var newTime = Number($(this).parents("tr").find("td").eq(0).find("span").eq(1).text());
        // if($(".price-btn").length>0) return false;
        $("#priceDel").hide();
        $(this).parents("tr").after(makeTr(newTime + 1, '', ''));
    }
});
//取消
$("body").on("click", "#priceCancel", function () {
    $(this).parents("tr").remove();
    $("#priceDel").show();
});
//确定
$("body").on("click", "#priceAdd", function () {
    if (!$(this).parents("tr").find(".new-times").val()) {
        alert("请输入次数");
        return false;
    }
    if (!$(this).parents("tr").find(".price-stairs").val()) {
        alert("请输入价格，价格应小于上一级价格");
        return false;
    }
    oldTimes = Number($(this).parents("tr").find(".old-times").val());
    newTimes = Number($(this).parents("tr").find(".new-times").val());
    var priceValue = $("#assignTable #prices").val() || $("#assignTable .price-stairs").val() || $("#assignTaskTable #prices").val() || $("#assignTaskTable .price-stairs").val();
    if ($(this).parents("tr").data("value") == 1 && newTimes == -1) {
        var str = '<tr><td>' +
            '<em data-value="0" style="display: none;"></em>' +
            '<span>0</span>~' +
            '<span>' + more + '</span></td>' +
            '<td><span>' + priceValue + '</span><span>' + singlePrice + '</span></td><td>' +
            '<div class="am-btn am-btn-default am-btn-xs am-text-secondary" id="showAdd">' + add + '</div>' +
            '</td></tr>';
        $("#assignTaskTable tbody,#assignTable tbody").html(str);
    } else {
        if (oldTimes >= newTimes) {
            alert("次数不能小于或等于起始次数");
            return false;
        }
        id++;
        // if($("#assignTaskTable tbody #lastPrice").length > 0){
        // }
        $("#assignTaskTable tbody tr,#assignTable tbody tr").last().remove();
        if ($(this).parents("tr").index() >= 1) {
            $(this).parents("tr").siblings().find("#showAdd").remove();
            $(this).parents("tr").siblings().find("#priceDel").remove();
        }
        prevTimes = newTimes;
        prevValue = priceValue;
        $(this).parents("tr").remove();
        var str = '<tr><td><span>' + oldTimes + '</span>~<span>' + newTimes + '</span></td><td><span>' + priceValue + '</span><span>' + singlePrice + '</span></td><td>' + btnStr + '</td></tr>';
        $("#assignTaskTable tbody,#assignTable tbody").append(str);
        // $(this).parents("tr").hide();
        // $("#assignTaskTable tbody").append(makeTr(newTimes,'',''));
        var lastPrice = '<tr id="lastPrice">\n' +
            '<td class="price-td">\n' +
            '<span id="lastTimes">' + (newTimes + 1) + '</span>~' + more + '\n' +
            '</td>\n' +
            '<td><input class="price-input price-stairs" type="number" name="price_0" placeholder="' + singlePrice + '" value=""></td>\n' +
            '<td class="price-btn"></td>\n' +
            '</tr>';
        $("#assignTaskTable tbody,#assignTable tbody").append(lastPrice);
    }

    // $("#priceDel").hide();
});

//产品-任务-阶梯价格删除
$("body").on("click", "#priceDel", function () {
    // id--;
    // arrayValue.pop();
    var index = $(this).parents("tr").index();
    $(this).parents("tr").remove();
    if (index === 0) {
        $("#assignTaskTable tbody tr,#assignTable tbody tr").remove();
        var start = '<tr data-value="1">\n' +
            ' <td class="price-td">\n' +
            ' <input class="price-input old-times" type="text" disabled value="0">\n' +
            ' <span class="span-position">~</span>\n' +
            ' <input class="price-input new-times" type="number" name="times_0" placeholder="' + count + '" value="">\n' +
            ' </td>\n' +
            ' <td><input class="price-input price-stairs" id="prices" type="number" name="price_0" placeholder="' + singlePrice + '"\n' +
            '  value=""></td>\n' +
            '  <td class="price-btn">\n' +
            ' <div class="am-btn am-btn-default am-btn-xs am-text-secondary" id="priceAdd">' + add + '</div>\n' +
            '  <div class="am-btn am-btn-default am-btn-xs am-text-danger" style="display: none;"\n' +
            '  id="priceDel">' + del + '\n' +
            '  </div>\n' +
            '  </td>\n' +
            '  </tr>';
        $("#assignTaskTable tbody,#assignTable tbody").append(start);
        $("#assignTaskTable tbody tr,#assignTable tbody tr").eq(0).find("td").last().html('<div class="am-btn am-btn-default am-btn-xs am-text-secondary" id="priceAdd">' + confirm + '</div>')
        arrayValue = [];
    } else {
        $("#assignTaskTable tbody tr,#assignTable tbody tr").last().remove();
        $("#assignTaskTable tbody tr,#assignTable tbody tr").last().find("td").last().html(btnStr);
        var firstTimes = $("#assignTaskTable tbody tr,#assignTable tbody tr").last().find("td").eq(0).text().split("~")[0];
        var secondTimes = Number($("#assignTaskTable tbody tr,#assignTable tbody tr").last().find("td").eq(0).text().split("~")[1]) + 1;
        $("#priceDel").show();
        var nowTime = $("#assignTaskTable tbody tr,#assignTable tbody tr").last().find("td").eq(0).find("input").eq(1).val();
        var lastPrice = '<tr id="lastPrice">\n' +
            '<td class="price-td">\n' +
            '<span id="lastTimes">' + secondTimes + '</span>~' + more + '\n' +
            '</td>\n' +
            '<td><input class="price-input price-stairs" type="number" name="price_0" placeholder="' + singlePrice + '" value=""></td>\n' +
            '<td class="price-btn"></td>\n' +
            '</tr>';
        $("#assignTaskTable tbody,#assignTable tbody").append(lastPrice);
    }
});

// $("body").on("input",".new-times",function () {
//     if($(this).val() > newTimes){
//         $("#lastTimes").text($(this).val());
//     } else{
//         $("#lastTimes").text(newTimes);
//     }
// });