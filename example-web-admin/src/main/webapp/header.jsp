<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tnx" uri="/truenewx-tags"%>
<header class="navbar navbar-default" role="banner">
    <div class="container">
        <div class="navbar-header">
            <button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">切换导航</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <div class="navbar-brand logo">
                <a href="${context}/">Example</a>
            </div>
        </div>
        <nav class="collapse navbar-collapse" role="navigation">
        <c:if test="${manager != null}">
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        <span id="managerName">${manager.fullname}</span>
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="javascript:site.header.controller.profile()">个人资料</a></li>
                        <li><a href="javascript:site.header.controller.updatePassword()" id="linkUpdatePassword">修改密码</a></li>
                        <li class="divider"></li>
                        <li><a href="${context}/logout">退出登录</a></li>
                    </ul>
                </li>
            </ul>
        </c:if>
        </nav>
    </div>
</header>
