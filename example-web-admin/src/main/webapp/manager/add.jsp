<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tnx" uri="/truenewx-tags"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>添加管理员</title>
</head>

<body component="validate,md5" script="manager/submit.js">
<form class="form-horizontal" role="form" action="${context}/manager/add" method="post" validate="true">
    <div class="form-group">
        <label class="col-md-2 control-label" for="username">用户名</label>
        <div class="col-md-3">
            <input type="text" class="form-control" id="username" name="username" validation="${validation['username']}" />
        </div>
        <div id="usernameError" class="alert alert-danger col-md-3 field-error-hide"></div>
    </div>
    <div class="form-group">
        <label class="col-md-2 control-label" for="password">密码</label>
        <div class="col-md-3">
            <input type="hidden" name="password" />
            <input type="password" class="form-control" id="password" validation="${validation['password']}" />
        </div>
        <div id="passwordError" class="alert alert-danger col-md-3 field-error-hide"></div>
    </div>
    <div class="form-group">
        <label class="col-md-2 control-label" for="password2">确认密码</label>
        <div class="col-md-3">
            <input type="password" class="form-control" id="password2" validation="{'notBlank':true}"/>
        </div>
        <div id="password2Error" class="alert alert-danger col-md-3 field-error-hide"></div>
    </div>
    <div class="form-group">
        <label class="col-md-2 control-label" for="fullname">姓名</label>
        <div class="col-md-3">
            <input type="text" class="form-control" id="fullname" name="fullname" validation="${validation['fullname']}" />
        </div>
        <div id="fullnameError" class="alert alert-danger col-md-3 field-error-hide"></div>
    </div>
    <div class="form-group">
        <label class="col-md-2 control-label">角色</label>
        <div class="col-md-9">
        <c:forEach var="role" items="${roles}">
            <label class="checkbox-inline">
                <input type="checkbox" name="roldIds" value="${role.id}"/> ${role.name}
            </label>
        </c:forEach>
        <c:if test="${empty roles}">
            <p class="form-control-static">系统中还没有角色，可<a href="${context}/role/add">点此添加角色</a></p>
        </c:if>
        </div>
    </div>
    <div class="form-group">
        <div class="col-md-offset-2 col-md-3">
            <input type="hidden" name="prev" value="<tnx:prev-url context="false" default=""/>" />
            <button type="submit" class="btn btn-primary">确定</button>
            <a class="btn btn-default" href="<tnx:prev-url default="${context}/manager/list"/>">取消</a>
        </div>
    </div>
</form>
</body>
</html>
