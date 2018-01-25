<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><decorator:title /> - 样例</title>
<jsp:include page="/link-head.jsp"></jsp:include>
<decorator:head/>
</head>

<body<decorator:getProperty property="body.required-tag" writeEntireProperty="true"/><decorator:getProperty property="body.required-class" writeEntireProperty="true"/><decorator:getProperty property="body.component" writeEntireProperty="true"/><decorator:getProperty property="body.script" writeEntireProperty="true"/>>
<div class="fullpage-container">
	<jsp:include page="/header"></jsp:include>
	<div class="container">
	    <div class="col-md-2">
	        <jsp:include page="/menu"></jsp:include>
	    </div>
	    <div class="col-md-10">
	        <jsp:include page="/breadcrumb"></jsp:include>
	        <decorator:body/>
	    </div>
	</div>
</div>
<jsp:include page="/footer.jsp"></jsp:include>
<jsp:include page="/link-foot.jsp"></jsp:include>
</body>
</html>
