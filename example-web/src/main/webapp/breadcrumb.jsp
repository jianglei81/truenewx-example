<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty items}">
<h4 class="header-dividing" style="margin-top: 0px; padding-bottom: 6px;">
    <ol class="breadcrumb bottom-block">
        <li><a href="${context}"><i class="icon icon-home"></i> 首页</a></li>
    <c:forEach var="item" items="${items}">
        <li>
        <c:if test="${not empty item.link.href}">
            <a href="${context}${item.link.href}">${item.caption}</a>
        </c:if><c:if test="${empty item.link.href}">${item.caption}</c:if>
        </li>
    </c:forEach>
    <c:if test="${not empty operation}">
        <li>${operation.caption}</li>
    </c:if>
    </ol>
</h4>
</c:if>
