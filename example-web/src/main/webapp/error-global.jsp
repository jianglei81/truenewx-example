<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tnx" uri="/truenewx-tags"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>错误</title>
</head>

<body>
<c:if test="${not ajaxRequest}">
<h3 class="col-md-offset-3 col-md-6 text-danger">
    <div class="row">
        <div class="col-md-4 text-right">
            <i class="icon icon-3x icon-exclamation-sign"></i>
        </div>
        <div class="col-md-8">
            <strong>
                <p>
                    <tnx:errors suffix="<br/>"/>
                    <tnx:errors field="*"/>
                </p>
            </strong>
            <button class="btn btn-danger" onclick="history.back();">返回</button>
        </div>
    </div>
</h3>
</c:if>
<c:if test="${ajaxRequest}">
<div class="row">
    <h3 class="col-md-12 text-danger">
        <div class="col-md-4 text-right">
            <i class="icon icon-3x icon-exclamation-sign"></i>
        </div>
        <div class="col-md-8">
            <strong>
                <p>
                    <tnx:errors suffix="<br/>"/>
                    <tnx:errors field="*"/>
                </p>
            </strong>
            <button class="btn btn-danger" onclick="$(this).parents('.modal-dialog').find('.close').click();">关闭</button>
        </div>
    </h3>
</div>
</c:if>
</body>
</html>
