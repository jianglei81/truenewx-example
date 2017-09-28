<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tnx" uri="/truenewx-tags"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>修改管理员</title>
</head>

<body component="validate" script="manager/submit.js">
<form class="form-horizontal" role="form" action="${context}/manager/${manager.id}/update" method="post" validate="true">
    <div class="form-group">
        <label class="col-md-2 control-label" for="username">用户名</label>
        <div class="col-md-3">
            <p class="form-control-static">${manager.username}</p>
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-2 control-label" for="fullname">姓名</label>
        <div class="col-md-3">
            <input type="text" class="form-control" id="fullname" name="fullname" value="${manager.fullname}"
                validation="${validation['fullname']}" />
        </div>
        <div id="fullnameError" class="alert alert-danger col-md-3 field-error-hide"></div>
    </div>
    <div class="form-group">
        <label class="col-md-2 control-label">所属角色</label>
        <div class="col-md-9">
            <p class="form-control-static" id="roles">
            <c:forEach var="role" items="${roles}">
                <span onclick="site.manager.submit.controller.toggleRole(this)"
                    class="label" data-id="${role.id}">${role.name}</span>
            </c:forEach>
                <c:if test="${empty roles}">系统中还没有角色，可<a href="${context}/role/add">点此添加角色</a></c:if>
            </p>
        </div>
    </div>
    <div class="form-group">
        <div class="col-md-offset-2 col-md-3">
            <input type="hidden" name="prev" value="<tnx:prev-url context="false" default=""/>" />
            <button type="submit" class="btn btn-primary">确定</button>
            <a class="btn btn-default" href="<tnx:prev-url default="${context}/manager/list"/>">取消</a>
        </div>
    </div>
<c:forEach var="role" items="${manager.roles}">
    <input type="hidden" name="roleIds" value="${role.id}" />
</c:forEach>
</form>
</body>
</html>
