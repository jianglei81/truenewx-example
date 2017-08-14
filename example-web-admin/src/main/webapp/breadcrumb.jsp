<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty items}">
    <ol class="breadcrumb">
        <li><a href="${context}"><i class="icon icon-home"></i> 首页</a></li>
    <c:forEach var="item" items="${items}">
        <li>
        <c:if test="${not empty item.href}">
            <a href="${context}${item.href}">${item.caption}</a>
        </c:if><c:if test="${empty item.href}">${item.caption}</c:if>
        </li>
    </c:forEach>
    <c:if test="${not empty operation}">
        <li>${operation.caption}</li>
    </c:if>
    </ol>
</c:if>
