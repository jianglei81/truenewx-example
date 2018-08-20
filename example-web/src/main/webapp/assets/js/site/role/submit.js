$.tnx.namespace("site.role.submit");

site.role.submit.Controller = site.Controller.extend({
    onLoad : function() {
        this.permissions = $("#permissions").val().split(",") || [];

        this.loadMenuItems();
        this.bindBusinessValidate("roleController");
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
    toTreeNode : function(item, index, parentIndex) {
        if (parentIndex != undefined) {
            index = parentIndex + "-" + index;
        }
        var node = {};
        node.html = '<div class="checkbox"><label><input type="checkbox" name="permissions"';
        if (parentIndex != undefined) {
            node.html += ' parent-index="' + parentIndex + '"';
        }
        node.html += ' index="' + index + '"';
        if (item.authority && item.authority.permission) {
            node.html += ' value="' + item.authority.permission + '"';
            if ($.inArray(item.authority.permission, this.permissions) >= 0) {
                node.html += ' checked="checked"';
            }
        }
        node.html += '/> ' + item.caption;
        if (item.authority && item.authority.caption) {
            node.html += '<span class="text-muted">（' + item.authority.caption + '）</span>';
        }
        node.html += '</label>';
        var _this = this;
        node.children = [];
        if (item.subs && item.subs.length) {
            node.html += '<button type="button" class="btn btn-link" style="padding: 0px;"'
                    + ' onclick="controller.selectNodeAndChildren(this)">全选</button>';
            item.subs.each(function(sub, subIndex) {
                var child = _this.toTreeNode(sub, subIndex, index);
                if (child) {
                    node.children.push(child);
                }
            });
        }
        node.html += '</div>';
        if ((!item.authority || !item.authority.permission) && node.children.length == 0) {
            return undefined; // 没有权限限定，且没有子级，则无效
        }
        return node;
    },
    selectNodeAndChildren : function(element, checked) {
        element = $(element);
        var checkbox = $("input[name='permissions']", element.parent());
        if (checked == undefined) {
            checked = !this.isChecked(checkbox);
        }
        if (checked) {
            checkbox.prop("checked", "checked");
        } else {
            checkbox.removeAttr("checked");
        }
        var index = checkbox.attr("index");
        var _this = this;
        $("#permissionTree input[parent-index='" + index + "']").each(function(i, sub) {
            _this.selectNodeAndChildren(sub, checked);
        });
    },
    isChecked : function(checkbox) {
        if (!checkbox.is(":checked")) {
            return false;
        }
        var index = checkbox.attr("index");
        var subs = $("#permissionTree input[parent-index='" + index + "']");
        for (var i = 0; i < subs.length; i++) {
            var sub = $(subs[i]);
            if (!this.isChecked(sub)) {
                return false;
            }
        }
        return true;
    },
    toggleManager : function(obj) {
        var managerId = $(obj).attr("data-id");
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
    },
    loadMoreManager : function(roleId) {
        var pageNoObj = $("#managers [page-no]");
        var pageNo = parseInt(pageNoObj.attr("page-no"));
        if (pageNo++) {
            $.tnx.rpc.imports("roleController", function(rpc) {
                rpc.getSelectableManagers(pageNo, roleId, function(result) {
                    result.records.each(function(manager) {
                        var span = '<span'
                                + ' onclick="site.role.submit.controller.toggleManager(this)"'
                                + ' class="label" data-id="' + manager.id + '">' + manager.username
                                + ' (' + manager.fullname + ')' + '</span>\n';
                        pageNoObj.before(span);
                    });
                    if (result.paging.morePage) {
                        pageNoObj.attr("page-no", pageNo);
                    } else {
                        pageNoObj.remove();
                    }
                });
            });
        }
    }
});
