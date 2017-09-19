$.tnx.namespace("site.log.system");

site.log.system.Controller = site.Controller.extend({
    pageSize : 100,
    onLoad : function() {
        var interval = $.zui.store.get("site.log.system.refresh_interval") || 3000;
        $("#interval option[value='" + interval + "']").attr("selected", "selected");

        this.loadLast();

        var _this = this;
        var btnPause = $("#btnPause");
        btnPause.click(function() {
            btnPause.toggleClass("icon-pause icon-play");
            if (btnPause.hasClass("icon-pause")) { // 启动
                $("#interval").disable();
                $.zui.store.set("site.log.system.refresh_interval", _this.getRefreshInterval());
                _this.loadLast();
            } else { // 暂停
                $("#interval").enable();
            }
        });

        var logContainer = $("#logContainer");
        logContainer.scroll(function() {
            var pre = $("pre:first", logContainer);
            if (pre.length) {
                if (logContainer.scrollTop() == 0) {
                    _this.loadBefore();
                }
            }
        });
    },
    getRefreshInterval : function() {
        return parseInt($("#interval option:selected").val());
    },
    appendLines : function(lines) {
        var logContainer = $("#logContainer");
        lines.each(function(line) {
            var pre = $("<pre></pre>").attr("data-pos", line.pos).text(line.content);
            logContainer.append(pre);
        });
        if (lines.length) {
            logContainer[0].scrollTop = logContainer[0].scrollHeight;
        }

        if ($("#btnPause").hasClass("icon-pause")) {
            var _this = this;
            setTimeout(function() {
                _this.loadLast();
            }, this.getRefreshInterval());
        }
    },
    loadLast : function() {
        var appender = this.getAppender();
        var rpc = $.tnx.rpc.imports("systemLogController");
        var _this = this;
        var pre = $("#logContainer pre:last");
        if (pre.length) { // 已有日志内容，则取最后一条之后的
            var pos = parseInt(pre.attr("data-pos"));
            rpc.getAfter(appender, pos, function(lines) {
                _this.appendLines(lines);
            });
        } else { // 没有日志内容，则取最近的
            rpc.getLast(appender, this.pageSize, function(lines) {
                _this.appendLines(lines);
            });
        }
    },
    getAppender : function() {
        return $("#appenders li.active a").text();
    },
    loadBefore : function() {
        var logContainer = $("#logContainer");
        var pre = $("pre:first", logContainer);
        if (pre.length) {
            var pos = parseInt(pre.attr("data-pos"));
            if (pos > 0) {
                var appender = this.getAppender();
                var rpc = $.tnx.rpc.imports("systemLogController");
                rpc.getBefore(appender, pos, this.pageSize, function(lines) {
                    lines.reverse(); // 反转清单以便于生成记录
                    var scrollHeight = 0;
                    lines.each(function(line) {
                        var pre = $("<pre></pre>").attr("data-pos", line.pos).text(line.content);
                        logContainer.prepend(pre);
                        scrollHeight += pre.outerHeight(true);
                    });
                    logContainer[0].scrollTop = scrollHeight;
                });
            }
        }
    }
});