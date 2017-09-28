$.tnx.namespace("site.role.list");

site.role.list.Controller = site.Controller.extend({
    onLoad : function() {
    },
    moveUp : function(id) {
        var _this = this;
        $.tnx.rpc.imports("roleController", function(rpc) {
            rpc.move(id, false, function() {
                var tr = $("tr[data-id='" + id + "']");
                var prev = tr.prev();
                prev.before(tr);
                _this.resetMoveButtons(tr);
                _this.resetMoveButtons(prev);
            });
        });
    },
    moveDown : function(id) {
        var _this = this;
        $.tnx.rpc.imports("roleController", function(rpc) {
            rpc.move(id, true, function() {
                var tr = $("tr[data-id='" + id + "']");
                var next = tr.next();
                next.after(tr);
                _this.resetMoveButtons(tr);
                _this.resetMoveButtons(next);
            });
        });
    },
    resetMoveButtons : function(tr) {
        if (tr.prev().length) { // 有上一个，则显示上移按钮
            $(".icon-arrow-up", tr).parent().css("visibility", "visible");
        } else { // 没有则隐藏上移按钮
            $(".icon-arrow-up", tr).parent().css("visibility", "hidden");
        }

        if (tr.next().length) { // 有下一个，则显示下移按钮
            $(".icon-arrow-down", tr).parent().css("visibility", "visible");
        } else { // 没有则隐藏下移按钮
            $(".icon-arrow-down", tr).parent().css("visibility", "hidden");
        }
    },
    toDelete : function(id) {
        var _this = this;
        $.tnx.rpc.imports("roleController", function(rpc) {
            rpc.countManagers(id, function(count) {
                var message = "";
                if (count) {
                    message = "该角色下包含" + count + "个管理员，如果删除该角色，这些管理员将解除与该角色的关系。<br/>";
                }
                message += "是否确认删除该角色？";
                site.confirm(message, function(yes) {
                    if (yes) {
                        rpc.del(id, function() {
                            var tr = $("tr[data-id='" + id + "']");
                            var prev = tr.prev();
                            var next = tr.next();
                            tr.remove();
                            _this.resetMoveButtons(prev);
                            _this.resetMoveButtons(next);
                        });
                    }
                });
            });
        });
    }
});