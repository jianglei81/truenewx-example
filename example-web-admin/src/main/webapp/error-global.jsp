<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="tnx" uri="/truenewx-tags"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>错误</title>
</head>

<body>
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
        <button class="btn btn-danger" onclick="history.back();">返回</button>
    </div>
</h3>
</body>
</html>
