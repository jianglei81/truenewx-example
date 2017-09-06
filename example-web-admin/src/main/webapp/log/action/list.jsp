<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tnx" uri="/truenewx-tags"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
<title>操作日志清单</title>
</head>

<body>
<form class="form-horizontal">
    <div class="form-group">
        <label class="col-md-1" for="managerKeyword">管理员</label>
        <div class="col-md-3">
            <input type="text" class="form-control" id="managerKeyword" name="managerKeyword" 
                placeholder="管理员的用户名/姓名" value="${param.managerKeyword}">
        </div>
        <div class="col-md-3">
            <button type="submit" class="btn btn-default"><i class="icon icon-search"></i> 查询</button>
        </div>
    </div>
    <table class="table table-striped table-bordered">
        <thead>
            <tr>
                <th class="text-center">时间</th>
                <th>管理员</th>
                <th>名称</th>
                <th class="text-center">类型</th>
                <th>内容</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="log" items="${result.records}">
            <tr>
                <td class="text-center"><fmt:formatDate value="${log.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td>${log.manager.username}</td>
                <td>${log.action.caption}</td>
                <td class="text-center">
                    <c:set var="labelType" value="${log.action.type == 'URL' ? 'primary' : 'warning'}"/>
                    <span class="label label-badge label-${labelType}">${log.action.type}</span>
                <td>
                <c:if test="${log.action.type == 'URL'}">
                    <span class="label label-info" title="请求方式" render="tooltip">${log.action.method}</span>
                    ${log.action.url}
                    <span class="label" title="请求参数" render="tooltip">${log.action.params}</span>
                </c:if>
                <c:if test="${log.action.type == 'RPC'}">
                    <c:set var="argString" value="${log.action.args} "/>
                    <c:set var="argStringLength" value="${fn:length(argString)}"/>
                    ${log.action.beanId}.${log.action.methodName}(${fn:substring(argString, 1, argStringLength - 2)})
                </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
        <tfoot>
            <tr class="text-right">
                <td colspan="5"><tnx:pager value="${result.paging}"/></td>
            </tr>
        </tfoot>
    </table>
</form>
</body>
</html>
