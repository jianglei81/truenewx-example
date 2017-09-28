$.tnx.namespace("site.login");

site.login.Controller = site.Controller.extend({
    onLoad : function() {
        var usernameObj = $("#username");
        var passwordObj = $("#password");
        if (usernameObj.val() != "" && passwordObj.val() == "") {
            passwordObj.focus();
        }
        var formObj = $("form");
        formObj.submit(function(event) {
            if (usernameObj.val().trim() == "") {
                usernameObj.val("");
                usernameObj.focus();
                event.preventDefault(); // 阻止提交
                return false;
            }
            var password = passwordObj.val();
            if (password.trim() == "") {
                passwordObj.focus();
                event.preventDefault(); // 阻止提交
                return false;
            }
            var md5Password = hex_md5(password);
            $("[name='password']").val(md5Password);
            return true;
        });
    }
});
