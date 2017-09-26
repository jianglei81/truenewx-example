$.tnx.namespace("site.manager.submit");

site.manager.submit.Controller = site.Controller.extend({
    onLoad : function() {
        var formObj = $("form");
        formObj.submit(function(event) {
            var passwordObj = $("#password");
            var password = passwordObj.val();
            var password2 = $("#password2").val();
            if (password != password2) {
                $.tnx.validator.showFieldErrors(passwordObj, "两次密码输入不一致")
                event.preventDefault(); // 阻止提交
                return false;
            }
            var md5Password = hex_md5(password);
            $("[name='password']").val(md5Password);
            return true;
        });

        this.selectRoles();
    },
    toggleRole : function(obj) {
        var roleId = $(obj).attr("data-id");
        var label = $("#roles [data-id='" + roleId + "']").toggleClass("label-warning");
        if (label.hasClass("label-warning")) {
            $("form").append('<input type="hidden" name="roleIds" value="' + roleId + '"/>');
        } else {
            $("input[name='roleIds'][value='" + roleId + "']").remove();
        }
    },
    selectRoles : function() {
        $("input[name='roleIds']").each(function(index, inputObj) {
            var roleId = $(inputObj).val();
            $("#roles [data-id='" + roleId + "']").addClass("label-warning");
        });
    }
});