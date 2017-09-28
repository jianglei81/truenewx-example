$.tnx.namespace("site.header");

site.header.Controller = site.Controller.extend({
    onLoad : function(win) {
    },
    profile : function() {
        var url = site.path.context + "/mine/profile.win";
        site.open(url, [ {
            text : "确定",
            "class" : "btn-primary",
            click : function() {
                this.close();
            }
        } ]);
    },
    updatePassword : function() {
        var url = site.path.context + "/mine/password.win";
        var _this = this;
        site.open(url, site.getOpenDialogButtons(function() {
            var dialog = this;
            var oldPasswordObj = $("#oldPassword", dialog);
            var oldPassword = oldPasswordObj.val();
            if (oldPassword == "") {
                oldPasswordObj.focus();
                return;
            }
            var newPasswordObj = $("#newPassword", dialog);
            var newPassword = newPasswordObj.val();
            if (newPassword == "") {
                newPasswordObj.focus();
                return;
            }
            if (oldPassword == newPassword) {
                _this.showError("新密码不能与原密码相同", dialog);
                newPasswordObj.focus();
                return;
            }
            var newPassword2Obj = $("#newPassword2", dialog);
            var newPassword2 = newPassword2Obj.val();
            if (newPassword2 == "") {
                newPassword2Obj.focus();
                return;
            }
            if (newPassword != newPassword2) {
                _this.showError("两次输入的新密码不一致", dialog);
                newPassword2Obj.focus();
                return;
            }
            $(".alert-danger", dialog).hide();

            oldPassword = hex_md5(oldPassword);
            newPassword = hex_md5(newPassword);
            var rpc = $.tnx.rpc.imports("mineController");
            rpc.updatePassword(oldPassword, newPassword, function() {
                $(".modal-body", dialog).text("密码修改成功，请使用新密码重新登录"); // 替换对话框内容
                $(".modal-footer .btn-default", dialog).remove(); // 移除取消按钮
                $(".modal-footer .btn-primary", dialog).click(function() { // 重新确定按钮的处理
                    window.location.href = site.path.context + "/logout";
                });
            });
        }));
    },
    showError : function(errorMessage, dialog) {
        var errorObj = $(".alert-danger", dialog);
        errorObj.text(errorMessage);
        errorObj.show();
    }
});

$(function() {
    site.header.controller = new site.header.Controller();
    site.header.controller.onLoad();
});
