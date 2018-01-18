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
        var btnHeadImage = $("#btnHeadImage", _this.win);
        btnHeadImage.unstructuredUpload({
            authorizeType : "MANAGER_HEAD_IMAGE",
            callbackContext : this,
            serverPath : site.path.context + "/unstructured/upload",
            auto : true,
            events : {
                ready : function() {
                    var headImageUrl = $("#headImageUrl", _this.win).val();
                    if (headImageUrl) {
                        btnHeadImage.unstructuredUpload("addFiles", [ headImageUrl ], function(
                                files) {
                            $.each(files, function(i, file) {
                                _this.appendImage(file.id, file.url);
                            });
                        });
                    }
                },
                uploadError : function(file) {
                    site.error(file.name + "上传失败");
                },
                uploadAccept : function(block, results, updateId) {
                    var result = results[0]; // 只有一个图片
                    // 如果存在待更新文件id，则更新文件
                    if (updateId) {
                        $("#" + updateId).attr({
                            "id" : block.file.id,
                            "src" : result.readUrl + "?v=" + Math.random(),
                        });
                    } else { // 否则添加文件
                        _this.appendImage(block.file.id, result.readUrl);
                    }
                    $("#headImageUrl", _this.win).val(result.storageUrl);
                    $.tnx.rpc.imports("mineController", function(rpc) {
                        rpc.updateHeadImage(result.storageUrl);
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
    appendImage : function(fileId, fileUrl) {
        var btnHeadImage = $("#btnHeadImage");
        var imageItem = '<div class="webuploader-item">' + '<img class="webuploader-res" id="'
                + fileId + '" ' + 'src="' + fileUrl + '?v=' + Math.random() + '">'
                + '<div class="webuploader-headbar">' + '<i class="icon icon-times"></i></div>'
                + '</div>'
        btnHeadImage.parent().append(imageItem);
        var parent = $("#" + fileId).parent();
        var _this = this;
        // 注册文件修改事件
        parent.click(function() {
            var updateFileId = $(".webuploader-res", parent).attr("id");
            btnHeadImage.unstructuredUpload("updateFile", updateFileId);
        });
        // 注册文件移除事件
        $("i.icon", parent).click(function() {
            window.event ? window.event.cancelBubble = true : el.stopPropagation(); // 阻止冒泡
            var deleteFileId = $(".webuploader-res", parent).attr("id");
            btnHeadImage.unstructuredUpload("deleteFile", deleteFileId);
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
        var _this = this;
        fullnameObj.disable();
        $.tnx.rpc.imports("mineController", function(rpc) {
            rpc.updateFullname(fullname, function() {
                _this.fullname = fullname;
                $("#managerName").text(fullname);
                fullnameObj.enable();
                btnSave.html('<i class="icon icon-ok text-success"></i>');
                setTimeout(function() {
                    btnSave.addClass("hidden");
                }, 3000);
            });
        });
    }
});
