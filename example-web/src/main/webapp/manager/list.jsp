<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tnx" uri="/truenewx-tags"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>管理员清单</title>
</head>

<body script="manager/list.js">
<form class="form-horizontal">
    <div class="form-group">
        <label class="col-md-1" for="keyword">关键字</label>
        <div class="col-md-3">
            <input type="text" class="form-control" id="keyword" name="keyword" 
                placeholder="用户名/姓名" value="${param.keyword}">
        </div>
        <div class="col-md-3">
            <button type="submit" class="btn btn-default"><i class="icon icon-search"></i> 查询</button>
        </div>
    </div>
    <div class="form-group">
        <div class="col-md-12">
            <a class="btn btn-primary" href="${context}/manager/add">添加管理员</a>
        </div>
    </div>
    <table class="table table-striped table-bordered">
        <thead>
            <tr>
                <th>用户名</th>
                <th>姓名</th>
                <th class="text-center">可用</th>
                <th class="text-center">创建时间</th>
                <th>所属角色</th>
                <th class="text-center">操作</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="manager" items="${result.records}">
            <tr>
                <td>${manager.username}</td>
                <td>${manager.fullname}</td>
                <td class="text-center">
                    <a<c:if test="${manager.disabled}"> class="hidden"</c:if> 
                        id="iconDisable_${manager.id}" title="当前可用，点击禁用" render="tooltip"
                        href="javascript:site.manager.list.controller.reverseDisabled(${manager.id}, false)">
                        <i class="icon icon-ok"></i>
                    </a>
                    <a<c:if test="${!manager.disabled}"> class="hidden"</c:if> 
                        id="iconEnable_${manager.id}" title="当前禁用，点击启用" render="tooltip" 
                        href="javascript:site.manager.list.controller.reverseDisabled(${manager.id}, true)">
                        <i class="icon icon-ban-circle"></i>
                    </a>
                </td>
                <td class="text-center"><fmt:formatDate value="${manager.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td>
                <c:forEach var="role" items="${manager.roles}">
                    <a href="${context}/role/${role.id}/update">
                        <span class="label label-warning">${role.name}</span></a>
                </c:forEach>
                <c:if test="${empty manager.roles}">
                    <span class="text-muted">&lt;尚未分配角色&gt;</span>
                </c:if>
                </td>
                <td class="text-center">
                    <a href="${context}/manager/${manager.id}/update">修改</a> |
                    <a href="javascript:site.manager.list.controller.toResetPassword(${manager.id});">重置密码</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
        <tfoot>
            <tr class="text-right">
                <td colspan="6"><tnx:pager value="${result.paging}"/></td>
            </tr>
        </tfoot>
    </table>
</form>
</body>
</html>
