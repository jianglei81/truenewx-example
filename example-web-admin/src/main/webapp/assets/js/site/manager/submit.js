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
    }
});