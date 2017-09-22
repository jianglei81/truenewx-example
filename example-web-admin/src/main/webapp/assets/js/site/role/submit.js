$.tnx.namespace("site.role.submit");

site.role.submit.Controller = site.Controller.extend({
    onLoad : function() {
        this.permissions = $("#permissions").val().split(",") || [];

        this.loadMenuItems();

        var nameObj = $("#name");
        nameObj.blur(function() {
            var name = nameObj.val();
            if (name) {
                var id = $("#id").val();
                if (id) {
                    id = parseInt(id);
                } else {
                    id = null;
                }
                $.tnx.rpc.imports("roleController", function(rpc) {
                    rpc.validateName(name, id, function() {
                        $(".btn-primary").removeAttr("disabled");
                    }, function(error) {
                        $.tnx.validator.showFieldErrors(nameObj, error.message);
                        $(".btn-primary").attr("disabled", "disabled");
                    });
                });
            }
        });

        this.selectManagers();
    },
    loadMenuItems : function() {
        var _this = this;
        $.tnx.rpc.imports("roleController", function(rpc) {
            rpc.getMenuItems(function(items) {
                var data = [];
                items.each(function(item, index) {
                    var node = _this.toTreeNode(item, index);
                    if (node) {
                        data.push(node);
                    }
                });

                $("#permissionTree").tree({
                    initialState : "expand",
                    data : data
                });

                $("#permissionTree input[name='permissions']").click(function(event) {
                    var checkbox = $(event.target);
                    if (checkbox.is(":checked")) { // 选中的节点，必须确保父级节点也被选中
                        var parentIndex = checkbox.attr("parent-index");
                        if (parentIndex != undefined) {
                            var parent = $("#permissionTree input[index='" + parentIndex + "']");
                            parent.prop("checked", "checked");
                        }
                    } else { // 未选中的节点，必须确保子级节点均不被选中
                        var index = checkbox.attr("index");
                        var subs = $("#permissionTree input[parent-index='" + index + "']");
                        subs.removeAttr("checked");
                    }
                });
            });
        });
    },
    toTreeNode : function(menuAction, index, parentIndex) {
        if (parentIndex != undefined) {
            index = parentIndex + "-" + index;
        }
        var node = {};
        node.html = '<div class="checkbox"><label><input type="checkbox" name="permissions"';
        if (parentIndex != undefined) {
            node.html += ' parent-index="' + parentIndex + '"';
        }
        node.html += ' index="' + index + '" value="' + menuAction.permission + '"';
        if ($.inArray(menuAction.permission, this.permissions) >= 0) {
            node.html += ' checked="checked"';
        }
        node.html += '/> ' + menuAction.caption + '</label></div>';

        var _this = this;
        node.children = [];
        if (menuAction.subs && menuAction.subs.length) {
            menuAction.subs.each(function(sub, subIndex) {
                var child = _this.toTreeNode(sub, subIndex, index);
                if (child) {
                    node.children.push(child);
                }
            });
        } else if (menuAction.operations && menuAction.operations.length) {
            menuAction.operations.each(function(operation, operationIndex) {
                var child = _this.toTreeNode(operation, operationIndex, index);
                if (child) {
                    node.children.push(child);
                }
            });
        }
        if (menuAction.permission == undefined && node.children.length == 0) {
            return undefined; // 没有权限限定，且没有子级，则无效
        }
        return node;
    },
    toggleManager : function(managerId) {
        var label = $("#managers [data-id='" + managerId + "']").toggleClass("label-primary");
        if (label.hasClass("label-primary")) {
            $("form").append('<input type="hidden" name="managerIds" value="' + managerId + '"/>');
        } else {
            $("input[name='managerIds'][value='" + managerId + "']").remove();
        }
    },
    selectManagers : function() {
        $("input[name='managerIds']").each(function(index, inputObj) {
            var managerId = $(inputObj).val();
            $("#managers [data-id='" + managerId + "']").addClass("label-primary");
        });
    }
});
