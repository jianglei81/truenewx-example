<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tnx" uri="/truenewx-tags"%>
<c:set var="build" value="${profile == 'product' ? 'false' : 'only'}" />
<link href="${context}/assets/image/logo-32.ico" rel="shortcut icon">
<link href="${resContext}/vendor/zui/1.7.0/css/zui.min.css" rel="stylesheet">
<link href="${context}/assets/css/main.css?v=<tnx:version build="${build}"/>" rel="stylesheet">
