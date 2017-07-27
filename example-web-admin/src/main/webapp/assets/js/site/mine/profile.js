$.tnx.namespace("site.mine.profile");

site.mine.profile.Controller = site.Controller.extend({
    onLoad : function(win) {
        this.win = win;
        var fullnameObj = $("#fullname", win);
        this.fullname = fullnameObj.val();
        var _this = this;
        fullnameObj.blur(function() {
            var btnSave = $("#btnSave");
            if (fullnameObj.val() != _this.fullname) {
                btnSave.html('<i class="icon icon-save"></i>');
                btnSave.removeClass("hidden");
            } else {
                btnSave.addClass("hidden");
            }
        });
    },
    updateFullname : function() {
        var fullnameObj = $("#fullname", this.win);
        var fullname = fullnameObj.val();
        if (!fullname) {
            fullnameObj.focus();
            return;
        }
        var btnSave = $("#btnSave");
        btnSave.html('<img src="' + site.path.context + '/assets/image/loading-32.gif"/>');
        var rpc = $.tnx.rpc.imports("mineController");
        fullnameObj.disable();
        var _this = this;
        rpc.updateFullname(fullname, function() {
            _this.fullname = fullname;
            $("#managerName").text(fullname);
            fullnameObj.enable();
            btnSave.html('<i class="icon icon-ok text-success"></i>');
            setTimeout(function() {
                btnSave.addClass("hidden");
            }, 3000);
        });
    }
});
