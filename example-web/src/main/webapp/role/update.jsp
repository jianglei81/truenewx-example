<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tnx" uri="/truenewx-tags"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>修改角色</title>
</head>

<body component="validate" script="role/submit.js">
<form class="form-horizontal" role="form" action="${context}/role/${role.id}/update" method="post" 
    data-id="${role.id}" validate="true">
    <div class="form-group">
        <label class="col-md-2 control-label" for="name">角色名称</label>
        <div class="col-md-2">
            <input type="text" class="form-control" id="name" name="name" business="true"
                validation="${validation['name']}" value="${role.name}" />
        </div>
        <div id="nameError" class="alert alert-danger col-md-4 field-error<tnx:noError field="name">-hide</tnx:noError>"></div>
    </div>
    <div class="form-group">
        <label class="col-md-2 control-label" for="remark">备注</label>
        <div class="col-md-6">
            <input type="text" class="form-control" id="remark" name="remark" validation="${validation['remark']}" 
                value="${role.remark}"/>
        </div>
        <div id="remarkError" class="alert alert-danger col-md-3 field-error<tnx:noError field="name">-hide</tnx:noError>"></div>
    </div>
    <div class="form-group">
        <label class="col-md-2 control-label">菜单权限</label>
        <div class="col-md-9">
            <input type="hidden" id="permissions" value="${permissions}" />
            <ul class="tree tree-lines bottom-block" id="permissionTree">
            </ul>
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 control-label">包含管理员</label>
        <div class="col-md-9">
            <p class="form-control-static" id="managers">
            <c:forEach var="manager" items="${role.managers}">
                <span onclick="site.role.submit.controller.toggleManager(this)"
                    class="label" data-id="${manager.id}">${manager.username} (${manager.fullname})</span>
            </c:forEach>
            <c:if test="${not empty role.managers}"><br/></c:if>
            <c:forEach var="manager" items="${selectableManagers.records}">
                <span onclick="site.role.submit.controller.toggleManager(this)"
                    class="label" data-id="${manager.id}">${manager.username} (${manager.fullname})</span>
            </c:forEach>
            <c:if test="${selectableManagers.paging.total == 0}">系统中还没有普通管理员，可<a href="${context}/manager/add">点此添加普通管理员</a></c:if>
            <c:if test="${selectableManagers.paging.morePage}">
                <a href="javascript:site.role.submit.controller.loadMoreManager(${role.id})" 
                    page-no="${selectableManagers.paging.pageNo}">更多</a>
            </c:if>
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
<c:forEach var="manager" items="${role.managers}">
    <input type="hidden" name="managerIds" value="${manager.id}" />
</c:forEach>
</form>
</body>
</html>
