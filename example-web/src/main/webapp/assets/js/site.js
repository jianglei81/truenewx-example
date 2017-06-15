$.tnx.namespace("site");

site = $.tnx.domain.site;
site.namespace = "site";
site.path.assets = site.path.context + "/assets";
site.path.js = site.path.assets + "/js";
site.requiredClass = "required";

$.extend(site.components, {
    "jquery-ui" : site.path.context + "/third/jquery/ui/js/jquery-ui.js",
    "validate" : [ site.path.resContext + "/component/core/truenewx-validate.js"],
    "md5" : site.path.resContext + "/vendor/core/md5-2.1.js",
    "input-limiter" : site.path.resContext + "/component/input-limiter/js/input-limiter.js",
    "ckeditor" : site.path.resContext + "/vendor/ckeditor/4.5.10/ckeditor.js",
    "chart" : [ site.path.resContext + "/vendor/echarts/3.2.2/echarts.min.js" ]
});

$.extend(site, {
    confirm : function(message, callback, options) {
        var content = $("<div></div>").html(message);
        $.tnx.confirm(content, callback, options);
    },
    alert : function(message, title, callback) {
        if ($.isFunction(title)) {
            callback = title;
            title = undefined;
        }
        title = title || "提示";
        if (typeof title == "string") {
            title = $("<strong></strong>").addClass("text-info").text(title);
        }
        $.tnx.alert(message, title, callback);
    },
    flash : function(message, timeout, callback) {
        var content = $("<div></div>").html(message);
        $.tnx.flash(content, timeout, callback);
    },
    success : function(message, title, callback) {
        if ($.isFunction(title)) {
            callback = title;
            title = undefined;
        }
        title = title || "成功";
        if (typeof title == "string") {
            title = $("<strong></strong>").addClass("text-success").text(title);
        }
        message = $("<div></div>").html(message);
        $.tnx.alert(message, title, callback);
    },
    error : function(message, title, callback) {
        if ($.isFunction(title)) {
            callback = title;
            title = undefined;
        }
        title = title || "错误";
        if (typeof title == "string") {
            title = $("<strong></strong>").addClass("text-danger").text(title);
        }
        message = $("<div></div>").html(message);
        $.tnx.alert(message, title, callback);
    },
    getOpenDialogButtons : function(btnPrimaryClick) {
        return [ {
            text : "确定",
            "class" : "btn-primary",
            click : btnPrimaryClick
        }, {
            text : "取消",
            "class" : "btn-default",
            click : function() {
                this.close();
            }
        } ]
    }
});

$.extend(site.open, {
    unLogined : function() {
        site.error("需重新登录后才可进行本操作", "登录会话过期", function() {
            window.location.href = site.path.context + "/logout";
        });
    }
});

$.extend($.tnx.rpc, {
    showErrorMessage : function(message) {
        site.error(message);
    }
});

$(function() {
    site.init();
});