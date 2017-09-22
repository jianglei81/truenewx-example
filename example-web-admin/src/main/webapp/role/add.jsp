<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tnx" uri="/truenewx-tags"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>添加角色</title>
</head>

<body component="validate" script="role/submit.js">
<form class="form-horizontal" role="form" action="${context}/role/add" method="post" validate="true">
    <div class="form-group">
        <label class="col-md-2 control-label" for="name">角色名称</label>
        <div class="col-md-2">
            <input type="text" class="form-control" id="name" name="name" validation="${validation['name']}" />
        </div>
        <div id="nameError" class="alert alert-danger col-md-4 field-error-hide"></div>
    </div>
    <div class="form-group">
        <label class="col-md-2 control-label" for="remark">备注</label>
        <div class="col-md-6">
            <input type="text" class="form-control" id="remark" name="remark" validation="${validation['remark']}" />
        </div>
        <div id="remarkError" class="alert alert-danger col-md-3 field-error-hide"></div>
    </div>
    <div class="form-group">
        <label class="col-md-2 control-label">菜单权限</label>
        <div class="col-md-9">
            <input type="hidden" id="permissions" value="" />
            <ul class="tree tree-lines bottom-block" id="permissionTree">
            </ul>
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 control-label">包含管理员</label>
        <div class="col-md-9">
            <p class="form-control-static" id="managers">
            <c:forEach var="manager" items="${managers.records}">
                <span onclick="site.role.submit.controller.toggleManager(${manager.id})"
                    class="label" data-id="${manager.id}">${manager.username} (${manager.fullname})</span>
            </c:forEach>
            </p>
        </div>
    </div>
    <div class="form-group">
        <div class="col-md-offset-2 col-md-3">
            <input type="hidden" name="prev" value="<tnx:prev-url context="false" default=""/>" />
            <button type="submit" class="btn btn-primary">确定</button>
            <a class="btn btn-default" href="<tnx:prev-url default="${context}/role/list"/>">取消</a>
        </div>
    </div>
</form>
</body>
</html>
