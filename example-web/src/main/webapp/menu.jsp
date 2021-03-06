<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tnx" uri="/truenewx-tags"%>
<nav class="menu" data-ride="menu">
    <ul class="nav nav-primary">
<c:forEach var="item" items="${items}" varStatus="status1">
    <c:if test="${not empty item.action.link.href}">
        <li<c:if test="${status1.index == level1ActiveIndex}"> class="active"</c:if>>
            <a href="${context}${item.action.link.href}">
                <i class="icon ${empty item.icon ? 'icon-blank' : item.icon}"></i> ${item.caption}
            </a>
        </li>
    </c:if><c:if test="${empty item.action.link.href}">
        <li class="nav-parent<c:if test="${status1.index == level1ActiveIndex}"> active show</c:if>">
            <a href="javascript:;">
                <i class="icon ${empty item.icon ? 'icon-blank' : item.icon}"></i> ${item.caption}
            </a>
        <c:if test="${not empty item.subs}">
            <ul class="nav">
            <c:forEach var="sub" items="${item.subs}" varStatus="status2">
                <li<c:if test="${status1.index == level1ActiveIndex && status2.index == level2ActiveIndex}"> class="active"</c:if>>
                    <a href="${context}${sub.action.link.href}">
                        <i class="icon ${empty sub.icon ? 'icon-blank' : sub.icon}"></i> ${sub.caption}
                    </a>
                </li>
            </c:forEach>
            </ul>
        </c:if>
        </li>
    </c:if>
</c:forEach>
    </ul>
</nav>
