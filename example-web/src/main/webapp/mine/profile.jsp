<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tnx" uri="/truenewx-tags"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>个人资料</title>
</head>

<body component="upload" script="mine/profile.js" width="360">
<form class="form-horizontal" role="form" method="get">
    <div class="form-group">
        <label class="col-md-3 control-label" for="headImageUrl">头像</label>
        <div class="col-md-8">
            <input type="file" class="form-control" id="headImageUrl" name="headImageUrl" />
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-3 control-label">用户名</label>
        <div class="col-md-8">
            <p class="form-control-static">${manager.username}</p>
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-3 control-label">顶级管理员</label>
        <div class="col-md-8">
            <p class="form-control-static">
                <tnx:enumtext type="boolean" value="${manager.top}"/>
            </p>
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-3 control-label">姓名</label>
        <div class="col-md-7">
            <input type="text" class="form-control" id="fullname" value="${manager.fullname}" />
        </div>
        <div class="col-md-1" style="padding-top: 5px;">
            <a class="hidden" id="btnSave" title="保存姓名变更"
                href="javascript:site.mine.profile.controller.updateFullname()">
                <i class="icon icon-save"></i></a>
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-3 control-label">创建时间</label>
        <div class="col-md-8">
            <p class="form-control-static">
                <fmt:formatDate value="${manager.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
            </p>
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-3 control-label">所属角色</label>
        <div class="col-md-8">
            <p class="form-control-static">
        <c:if test="${not empty manager.roles}">
            <c:forEach var="role" items="${manager.roles}">
                <a href="#">${role.name}</a>
            </c:forEach>
        </c:if><c:if test="${empty manager.roles}">
            <span class="text-muted">&lt;无&gt;</span>
        </c:if>
            </p>
        </div>
    </div>
</form>
</body>
</html>
