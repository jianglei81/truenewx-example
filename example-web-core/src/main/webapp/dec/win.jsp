<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<body title="<decorator:title />"<decorator:getProperty property="body.required-tag" writeEntireProperty="true"/><decorator:getProperty property="body.required-class" writeEntireProperty="true"/><decorator:getProperty property="body.component" writeEntireProperty="true"/><decorator:getProperty property="body.script" writeEntireProperty="true"/><decorator:getProperty property="body.width" writeEntireProperty="true"/>>
<decorator:body />
</body>
