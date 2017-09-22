<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>角色清单</title>
</head>

<body script="role/list.js">
<form class="form-horizontal">
    <div class="form-group">
        <label class="col-md-1" for="name">角色名称</label>
        <div class="col-md-3">
            <input type="text" class="form-control" id="name" name="name" value="${param.name}">
        </div>
        <div class="col-md-3">
            <button type="submit" class="btn btn-default"><i class="icon icon-search"></i> 查询</button>
        </div>
    </div>
    <div class="form-group">
        <div class="col-md-12">
            <a class="btn btn-primary" href="${context}/role/add">添加角色</a>
        </div>
    </div>
    <table class="table table-striped table-bordered">
        <thead>
            <tr>
                <th class="text-center">排序</th>
                <th>角色名称</th>
                <th>包含管理员</th>
                <th>备注</th>
                <th class="text-center">操作</th>
            </tr>
        </thead>
        <tbody>
        <c:if test="${empty roles}">
            <tr class="text-center">
                <td colspan="5">系统中暂无角色，<a href="${context}/role/add">点此添加角色</a></td>
            </tr>
        </c:if>
        <c:forEach var="role" items="${roles}" varStatus="status">
            <tr data-id="${role.id}">
                <td class="text-center">
                    <a onclick="site.role.list.controller.moveUp(${role.id})"<c:if test="${status.first}"> style="visibility: hidden;"</c:if>>
                        <i class="icon icon-arrow-up"></i></a>
                    <a onclick="site.role.list.controller.moveDown(${role.id})"<c:if test="${status.last}"> style="visibility: hidden;"</c:if>>
                        <i class="icon icon-arrow-down"></i></a>
                </td>
                <td>${role.name}</td>
                <td>
                <c:if test="${empty role.managers}">
                    <span class="text-muted">&lt;无&gt;</span>
                </c:if>
                <c:forEach var="manager" items="${role.managers}">
                    <a href="${context}/manager/${manager.id}/update">${manager.fullname}</a>
                </c:forEach>
                </td>
                <td>${role.remark}</td>
                <td class="text-center">
                    <a href="${context}/role/${role.id}/update">修改</a> |
                    <a href="javascript:site.role.list.controller.toDelete(${role.id})">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</form>
</body>
</html>
