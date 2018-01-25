<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tnx" uri="/truenewx-tags"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>登录 - 样例</title>
<jsp:include page="/link-head.jsp"></jsp:include>
</head>

<body component="md5" script="login.js">
<div class="fullpage-container">
    <jsp:include page="/header"></jsp:include>
    <div class="container">
        <div class="col-md-offset-4 col-md-4">
            <h2 class="title text-center">登录</h2>
            <form class="form-horizontal" role="form" action="${context}/login" method="post">
                <input type="hidden" name="next" value="${param.next}">
                <div class="form-group<tnx:noError> hidden</tnx:noError>">
                    <div class="col-md-offset-3 col-md-8">
                        <div class="alert alert-danger bottom-block" role="alert">
                            <tnx:errors/>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label" for="username">用户名</label>
                    <div class="col-md-8">
                        <input type="text" class="form-control" id="username" name="username" 
                            value="${param.username}" autofocus="autofocus">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-3 control-label" for="password">密码</label>
                    <div class="col-md-8">
                        <input type="hidden" name="password">
                        <input type="password" class="form-control" id="password">
                    </div>
                </div>
                <div class="form-group a-bottom-block">
                    <div class="col-md-offset-3 col-md-8">
                        <button type="submit" class="btn btn-primary">登录</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<jsp:include page="/footer.jsp"></jsp:include>
<jsp:include page="/link-foot.jsp"></jsp:include>
</body>
</html>
