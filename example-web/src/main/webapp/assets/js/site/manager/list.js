$.tnx.namespace("site.manager.list");

site.manager.list.Controller = site.Controller.extend({
    onLoad : function() {
        $.tnx.rpc.imports("managerController", true);
    },
    toResetPassword : function(id) {
        var url = site.path.context + "/manager/" + id + "/password.win";
        site.open(url, site.getOpenDialogButtons(function() {
            var passwordObj = $("#password", this);
            var password = passwordObj.val();
            if (password == "") {
                passwordObj.focus();
                return;
            }

            var password2Obj = $("#password2", this);
            var password2 = password2Obj.val();
            if (password2 == "") {
                password2Obj.focus();
                return;
            }

            if (password != password2) {
                $("#formError", this).removeClass("hidden");
                return;
            }

            var managerId = parseInt($("#managerId").val());
            var md5Password = hex_md5(password);
            var _this = this;
            $.tnx.rpcs.managerController.resetPassword(managerId, md5Password, function() {
                _this.close();
                site.success("重置密码成功");
            });
        }));
    },
    reverseDisabled : function(managerId, disabled) {
        var _this = this;
        $.tnx.rpcs.managerController.reverseDisabled(managerId, disabled, function(newDisabled) {
            if (newDisabled) {
                $("#iconDisable_" + managerId).addClass("hidden");
                $("#iconEnable_" + managerId).removeClass("hidden");
            } else {
                $("#iconEnable_" + managerId).addClass("hidden");
                $("#iconDisable_" + managerId).removeClass("hidden");
            }
        });
    }
});