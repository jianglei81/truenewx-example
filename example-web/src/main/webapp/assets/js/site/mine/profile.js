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

        var btnHeadImage = $("#btnHeadImage");
        btnHeadImage.unstructuredUpload({
            authorizeType : "MANAGER_HEAD_IMAGE",
            callbackContext : this,
            serverPath : site.path.context + "/unstructured/upload",
            auto : true,
            events : {
                uploadError : function(file) {
                    site.error(file.name + "上传失败");
                },
                uploadAccept : function(block, results) {
                    results.each(function(result) {
                        site.success(result.filename + " 上传成功\nstorageUrl=" + result.storageUrl
                                + "\nreadUrl=" + result.readUrl);
                    });
                },
                error : function(error) {
                    site.error(error.message);
                }
            },
            messages : {
                "default" : {
                    "error.unstructured.upload.beyond_max_number" : "只能选择{0}个头像文件"
                }
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
