<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tnx" uri="/truenewx-tags"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>修改密码</title>
</head>

<body component="md5" width="360">
<form class="form-horizontal" role="form" action="${context}/person/password" method="post">
    <div class="col-md-12">
        <div class="alert alert-danger hidden"></div>
    </div>
    <div class="form-group">
        <label class="col-md-3 control-label" for="oldPassword">原密码</label>
        <div class="col-md-8">
            <input type="password" class="form-control" id="oldPassword">
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-3 control-label" for="newPassword">新密码</label>
        <div class="col-md-8">
            <input type="password" class="form-control" id="newPassword">
        </div>
    </div>
    <div class="form-group a-bottom-block">
        <label class="col-md-3 control-label" for="newPassword2">确认密码</label>
        <div class="col-md-8">
            <input type="password" class="form-control" id="newPassword2">
        </div>
    </div>
</form>
</body>
</html>
