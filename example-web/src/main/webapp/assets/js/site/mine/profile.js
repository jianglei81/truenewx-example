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
        this.initHeadImageUpload();
    },
    initHeadImageUpload : function() {
        var _this = this;
        var btnHeadImage = $("#btnHeadImage", _this.win);
        btnHeadImage.unstructuredUpload({
            authorizeType : "MANAGER_HEAD_IMAGE",
            serverPath : site.path.context + "/unstructured/upload",
            auto : true,
            messages : {
                "default" : {
                    "error.unstructured.upload.beyond_max_number" : "只能选择{0}个头像文件"
                }
            },
            events : {
                ready : function() {
                    var headImageUrl = $("#headImageUrl", _this.win).val();
                    if (headImageUrl) {
                        btnHeadImage.unstructuredUpload("addFile", [ headImageUrl ]);
                    }
                },
                filesQueued : function(files, updatingFileId) {
                    // 如果存在待更新文件id，则替换原文件显示
                    if (updatingFileId) {
                        // 只用加入队列中的第一个文件替换，其它的文件进行添加
                        $("#" + updatingFileId, $("#btnHeadImage").parent())
                                .attr("id", files[0].id);
                    }
                    $.each(files, function(i, file) {
                        if (file.getStatus() == "complete") { // 完成状态的添加文件显示
                            _this.addFileView(file.id, file.url);
                        } else { // 没完成的显示处理中效果图
                            _this.showFileView(file.id);
                        }
                    });
                },
                uploadAccept : function(file, result, updatedFileId) {
                    _this.addFileView(file.id, result.readUrl);
                    $("#headImageUrl", _this.win).val(result.storageUrl);
                    $.tnx.rpc.imports("mineController", function(rpc) {
                        rpc.updateHeadImage(result.storageUrl);
                    });
                },
                uploadError : function(file, reason) {
                    site.error(file.name + "上传失败，" + reason);
                },
                error : function(error) {
                    site.error(error.message);
                }
            }
        });
    },
    addFileView : function(fileId, fileUrl) {
        this.showFileView(fileId, fileUrl);

        var btnHeadImage = $("#btnHeadImage");
        var fileItem = $("#" + fileId, btnHeadImage.parent()).parent();
        var _this = this;
        // 注册文件修改事件
        fileItem.off("click");
        fileItem.click(function() {
            var updateFileId = $(".webuploader-res", fileItem).attr("id");
            btnHeadImage.unstructuredUpload("updateFile", updateFileId);
        });
        // 注册文件移除事件
        $("i.icon", fileItem).off("click");
        $("i.icon", fileItem).click(function() {
            window.event ? window.event.cancelBubble = true : el.stopPropagation(); // 阻止冒泡
            $.tnx.rpc.imports("mineController", function(rpc) {
                rpc.updateHeadImage(null, function() {
                    var removeFileId = $(".webuploader-res", fileItem).attr("id");
                    btnHeadImage.unstructuredUpload("removeFile", removeFileId);
                    _this.removeFileView(removeFileId);
                });
            });
        });
    },
    removeFileView : function(fileId) {
        $("#" + fileId, $("#btnHeadImage").parent()).parents(".webuploader-item").remove();
    },
    showFileView : function(fileId, fileUrl) {
        var btnHeadImage = $("#btnHeadImage");
        var container = btnHeadImage.parent();
        var fileObj = $("#" + fileId, container);
        if (fileObj.length) { // 已经存在则替换
            fileObj.removeAttr("src").removeAttr("data-original");
            fileUrl = fileUrl || "";
            fileObj.attr("data-src", fileUrl);
        } else { // 不存在则添加
            if (fileUrl) {
                fileUrl += "?v=" + Math.random();
            } else {
                fileUrl = "";
            }
            var imageItem = '<div class="webuploader-item"><img class="webuploader-res" id="'
                    + fileId + '" data-src="' + fileUrl + '">'
                    + '<div class="webuploader-headbar">' + '<i class="icon icon-times"></i></div>'
                    + '</div>';
            container.append(imageItem);
        }
        $.tnx.domain.initImageLazyLoad(container);
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
