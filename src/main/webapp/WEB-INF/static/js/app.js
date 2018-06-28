var commonId = null;
var commonIndex = null;
var taskTypeName = null;
var appInfoDetailIndex = null;
var appInfoDetailId = null;
var appInfoId = null;
var appInfoIndex = null;
var taskType = null;
var appKey = null;
var noticeId, noticeIndex;

var contextPath = $("#contextPath").text();
var frontPath = $("#frontPath").text();


$(function () {
    $("body").on("click", ".cancel,.am-close", function () {
        layer.closeAll();
    });
});

(function ($) {
    $.fn.serializeJson = function () {
        var serializeObj = {};
        var array = this.serializeArray();
        var str = this.serialize();
        $(array).each(function () {
            if (serializeObj[this.name]) {
                if ($.isArray(serializeObj[this.name])) {
                    serializeObj[this.name].push(this.value);
                } else {
                    serializeObj[this.name] = [serializeObj[this.name], this.value];
                }
            } else {
                serializeObj[this.name] = this.value;
            }
        });
        return serializeObj;
    };
})(jQuery);

//更新content视图
function loads(url) {
    setTimeout(function () {
        $("#content").load(contextPath + url);
        $(".am-dimmer").hide();
        $("body").css("padding-right", "0");
    }, 100)
}
// ==========================
// 侧边导航下拉列表
// ==========================

$('.tpl-left-nav-link-list').on('click', function () {
    $(this).siblings('.tpl-left-nav-sub-menu').slideToggle(80)
        .end()
        .find('.tpl-left-nav-more-ico').toggleClass('tpl-left-nav-more-ico-rotate');
});

$('.tpl-header-nav-hover-ico').on('click', function () {
    if (document.documentElement.clientWidth < 1000) $('.tpl-left-nav').slideToggle();
});


//页面初始化加载首页
// $("#content").load(contextPath + '/portal/home.html');

//左侧菜单
$("body").on("click", "a", function () {
    $(".datetimepicker,.am-datepicker").remove();
    $(this).parent().siblings().find(".tpl-left-nav-sub-menu").hide();
    $(this).addClass("active").parent().siblings().find("a").removeClass("active").find("i").removeClass("tpl-left-nav-more-ico-rotate");
    if ($(this).data("href") === "#" || !$(this).data("href")) return false;
    $("#content").load($(this).data("href"));
});
$(".tpl-left-nav a").on("click", function () {
    if ($(this).data("href") !== "#" && $(this).data("href")) {
        if (document.documentElement.clientWidth < 1000) $('.tpl-left-nav').slideToggle();
    }
});
/*公共ajax请求*/
function getData(url, type, dataJson, callback) {
    $.ajax({
        type: type,
        url: contextPath + url,
        contentType: "application/json",
        data: JSON.stringify(dataJson),
        success: function (data) {
            callback(data);
        },
        error: function (err) {
            alert("系统出错!")
        }
    })
};

function get_Data(url, callback) {
    $.ajax({
        type: 'GET',
        url: contextPath + url,
        contentType: "application/json",
        success: function (data) {
            callback(data);
        },
        error: function (err) {
            alert("系统出错!")
        }
    })
};


$("li").on("click", "#indexPage", function () {
    window.location.replace(frontPath);
});
$("li").on("click", "#gotoProjectPage", function () {
    $('#content').load(contextPath + '/management/page/project');
});
$("li").on("click", "#gotoPlatformAddressPage", function () {
    $('#content').load(contextPath + '/management/page/platformAddress');
});
$("li").on("click", "#gotoStarUser", function () {
    $('#content').load(contextPath + '/management/page/user');
});
$("li").on("click", "#gotoBillBoard", function () {
    $('#content').load(contextPath + '/management/page/billboard');
});
$("li").on("click", "#gotoProjectType", function () {
    $('#content').load(contextPath + '/management/page/projecttype');
});
$("li").on("click", "#gotoSensitive", function () {
    $('#content').load(contextPath + '/management/page/sensitive');
});
$("li").on("click", "#gotoComment", function () {
    $('#content').load(contextPath + '/management/page/comment');
});
$("li").on("click", "#gotoBaseConfig", function () {
    $('#content').load(contextPath + '/management/page/baseconfig');
});























