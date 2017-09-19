<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tnx" uri="/truenewx-tags"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>系统日志</title>
</head>

<body script="log/system.js">
<ul class="nav nav-tabs" id="appenders">
<c:forEach var="a" items="${appenders}">
    <c:if test="${a == appender}">
    <li class="active"><a>${a}</a></li>
    </c:if><c:if test="${a != appender}">
    <li><a href="${context}/log/system/${a}">${a}</a></li>
    </c:if>
</c:forEach>
    <li class="pull-right" style="padding: 8px;">
        <select id="interval" style="margin-right: 5px;" disabled="disabled" title="自动刷新间隔" render="tooltip">
            <option value="1000">1秒</option>
            <option value="2000">2秒</option>
            <option value="3000">3秒</option>
            <option value="5000">5秒</option>
            <option value="10000">10秒</option>
            <option value="20000">20秒</option>
            <option value="30000">30秒</option>
            <option value="60000">60秒</option>
        </select>
        <i class="icon icon-pause" id="btnPause"></i>
    </li>
</ul>
<div class="tab-content">
    <div class="tab-pane active">
        <div class="row">
            <div class="col-md-12" style="height: 400px; overflow: auto;" id="logContainer">
            </div>
        </div>
    </div>
</div>
</body>
</html>
