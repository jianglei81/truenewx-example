<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tnx" uri="/truenewx-tags"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>重置密码</title>
</head>

<body component="md5" width="400">
<form class="form-horizontal" role="form">
    <input type="hidden" id="managerId" value="${id}"/>
    <div class="form-group hidden" id="formError">
        <div class="col-md-offset-1 col-md-10">
            <div class="alert alert-danger bottom-block">两次密码输入不一致</div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-md-3 control-label" for="password">密码</label>
        <div class="col-md-8">
            <input type="password" class="form-control" id="password" />
            <span class="required"></span>
        </div>
    </div>
    <div class="form-group bottom-block">
        <label class="col-md-3 control-label" for="password2">确认密码</label>
        <div class="col-md-8">
            <input type="password" class="form-control" id="password2" />
            <span class="required"></span>
        </div>
    </div>
</form>
</body>
</html>
