<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tnx" uri="/truenewx-tags"%>
<nav class="menu" data-ride="menu">
    <ul class="nav nav-primary">
<c:forEach var="item" items="${menu.items}" varStatus="status1">
    <c:if test="${empty item.subs}">
        <li<c:if test="${status1.index == level1ActiveIndex}"> class="active"</c:if>>
            <a href="${context}${item.href}">
                <c:if test="${not empty item.icon}"><i class="${item.icon}"></i> </c:if>${item.caption}
            </a>
        </li>
    </c:if><c:if test="${not empty item.subs}">
        <li class="nav-parent<c:if test="${status1.index == level1ActiveIndex}"> active show</c:if>">
            <a href="javascript:;">
                <c:if test="${not empty item.icon}"><i class="${item.icon}"></i> </c:if>${item.caption}
            </a>
            <ul class="nav">
            <c:forEach var="sub" items="${item.subs}" varStatus="status2">
                <li<c:if test="${status2.index == level2ActiveIndex}"> class="active"</c:if>>
                    <a href="${context}${sub.href}">
                        <c:if test="${not empty sub.icon}"><i class="${sub.icon}"></i> </c:if>${sub.caption}
                    </a>
                </li>
            </c:forEach>
            </ul>
        </li>
    </c:if>
</c:forEach>
    </ul>
</nav>
