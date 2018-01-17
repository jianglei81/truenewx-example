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
                ready : function() {
                    var headImageUrl = $("#headImageUrl").val();
                    btnHeadImage.unstructuredUpload("addFiles", [ headImageUrl ], function(files) {
                        $.each(files, function() {
                            var imageItem = '<div class="webuploader-item">'
                                    + '<img class="webuploader-res" id="' + this.id + '" '
                                    + 'src="' + this.url + '?v=' + Math.random() + '">'
                                    + '<div class="webuploader-headbar">'
                                    + '<i class="icon icon-times"></i></div>' + '</div>'
                            btnHeadImage.parent().append(imageItem);
                            var $thisfile = $("#" + this.id);
                            $thisfile.parent().click(function() {
                                _this.triggerBtn(this, btnHeadImage);
                            });
                            $thisfile.parent().find("i.icon").click(function() {
                                _this.removeThisFile(this, btnHeadImage);
                            });
                        });
                    });
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
                        var imageItem = '<div class="webuploader-item">'
                                + '<img class="webuploader-res" id="' + block.file.id + '" '
                                + 'src="' + result.readUrl + '?v=' + Math.random() + '">'
                                + '<div class="webuploader-headbar">'
                                + '<i class="icon icon-times"></i></div>' + '</div>'
                        btnHeadImage.parent().append(imageItem);
                        var $thisfile = $('#' + block.file.id)
                        $thisfile.parent().click(function() {
                            _this.triggerBtn(this, btnHeadImage);
                        });
                        $thisfile.parent().find('i.icon').click(function() {
                            _this.removeThisFile(this, btnHeadImage);
                        });
                    }
                    $("#headImageUrl", this.win).val(result.storageUrl);
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
    },
    // 点选已存在文件，更新文件
    triggerBtn : function(el, $obj) {
        var fileId = $(el).children().attr("id");
        $obj.unstructuredUpload("updateFile", fileId);
    },
    // 删除当前文件
    removeThisFile : function(el, $obj) {
        window.event ? window.event.cancelBubble = true : el.stopPropagation(); // 阻止冒泡
        var fileId = $(el).parents('.webuploader-item').find('.webuploader-res').attr('id');
        $obj.unstructuredUpload("deleteFile", fileId);
    }
});
