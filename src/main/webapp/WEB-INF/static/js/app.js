var taskType = null;
var projectGid;
var project;

var contextPath = $("#contextPath").text();
var frontPath = $("#frontPath").text();
var moonPath = $("#moonPath").text();


var tokenAddressReg = /^0x[a-fA-F0-9]{40}$/;
var tokenAddressesReg = /^(0x[a-fA-F0-9]{40}(\s+)*)*$/;
var regToken = new RegExp(tokenAddressReg);


var txHashReg = /^0x[a-fA-F0-9]{64}$/;
//平台交易
var platformTxStatusMap =
    {
        0: '未验证',
        1: '交易中',
        2: '交易成功',
        3: '交易失败',
        4: '交易作废'
    };

var userTxStatusMap =
    {
        0: '初始状态',
        1: '交易还未被打包',
        2: '验证成功',
        21: '验证失败（to不是平台地址)',
        22: '验证失败（from不匹配）',
        23: '验证失败（金额不匹配）',
        3: '交易失败',
        4: '交易不存在'
    };

var disTributeStatusMap =
    {
        0: '初始状态',
        1: '打币中',
        2: '成功',
        3: '失败',
        4: '交易作废'
    };

var projectTxTypeMap =
    {
        0: '初始状态',
        1: '项目方-->平台',
        2: '平台-->项目方',
        3: '其他类型'
    };


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
    projectGid = '';
    project = '';
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
            if (callback) {
                callback(data);
            }
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
$("li").on("click", "#gotoProjectStatisticPage", function () {
    $('#content').load(contextPath + '/management/page/project-statistic');
});
$("li").on("click", "#gotoUserTxInfoPage", function () {
    $('#content').load(contextPath + '/management/page/user-tx-info');
});
$("li").on("click", "#gotoProjectTxInfoPage", function () {
    $('#content').load(contextPath + '/management/page/platform-tx-info');
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























