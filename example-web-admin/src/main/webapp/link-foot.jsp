<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tnx" uri="/truenewx-tags"%>
<script type="text/javascript" src="${resContext}/vendor/core/LAB-2.0.3.min.js"></script>
<script type="text/javascript">
var lab = $LAB.script("${resContext}/vendor/core/extend-1.0.0.js")
    .script("${resContext}/vendor/core/sugar-1.4.1.min.js")
    .script("${resContext}/vendor/jquery/1.11.3/jquery.min.js").wait()
    .script("${resContext}/vendor/jquery/plugins/jquery.json-2.4.0.min.js")
    .script("${resContext}/vendor/zui/1.7.0/js/zui.min.js")
    .script("${resContext}/component/core/prototype.js?b=<tnx:placeholder key="res.build"/>")
    .script("${resContext}/component/core/truenewx.js?b=<tnx:placeholder key="res.build"/>").wait(function() {
        $.tnx.locale = "${pageContext.request.locale}";
        $.tnx.pager.contextPath = "${context}";
    }).script("${resContext}/component/core/truenewx-zui.js?b=<tnx:placeholder key="res.build"/>")
    .script("${resContext}/component/core/truenewx-domain.js?b=<tnx:placeholder key="res.build"/>").wait(function() {
        $.tnx.domain.site.path.context = "${context}";
        $.tnx.domain.site.path.resContext = "${resContext}";
        $.tnx.domain.site.version = "<tnx:version type="${profile == 'product' ? 'release' : 'build'}"/>";
    }).script("${context}/assets/js/site.js?v=<tnx:version type="${profile == 'product' ? 'release' : 'build'}"/>");
    
<c:if test="${not empty script}">
    var scripts = "${param.script}".split(",");
    for (var i = 0; i < scripts.length; i++) {
        lab.script("${context}/assets/js/site/" + scripts[i] + "?v=<tnx:version type="${profile == 'product' ? 'release' : 'build'}"/>");
    }
</c:if>
</script>
