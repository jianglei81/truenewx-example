<ul class="pager pull-right" style="margin: 0px;">
    <li class="previous<#if pageNo = 1> text-muted</#if>">
        <a<#if pageNo != 1> <#if (tempHref != "")>href='${tempHref?replace("{pageNo}","${previousPage?c}")}'<#else>onclick="$.tnx.pager.toPage(this, ${previousPage?c})"</#if></#if>>&lt;</a>
    </li>
<#if (startPage > 1)>
    <li><a <#if (tempHref != "")>href='${tempHref?replace("{pageNo}","1")}'<#else>onclick="$.tnx.pager.toPage(this, 1)"</#if>>1</a></li>
</#if>
<#if (startPage > 2)>
    <li><a <#if (tempHref != "")>href='${tempHref?replace("{pageNo}","${(startPage-1)?c}")}'<#else>onclick="$.tnx.pager.toPage(this, ${(startPage-1)?c})"</#if>>&hellip;</a></li>
</#if>
<#list startPage..endPage as i>
    <li<#if pageNo = i> class="active"</#if>>
        <a<#if pageNo != i> <#if (tempHref != "")>href='${tempHref?replace("{pageNo}","${i?c}")}'<#else>onclick="$.tnx.pager.toPage(this, ${i?c})"</#if></#if>>${i}</a>
    </li>
</#list>
<#if (endPage  < pageCount-1 || !isCountable)>
    <li><a <#if (tempHref != "")>href='${tempHref?replace("{pageNo}","${(endPage+1)?c}")}'<#else>onclick="$.tnx.pager.toPage(this, ${(endPage+1)?c})"</#if>>&hellip;</a></li>
</#if>
<#if (isCountable  && endPage < pageCount)>
    <li><a <#if (tempHref != "")>href='${tempHref?replace("{pageNo}","${pageCount?c}")}'<#else>onclick="$.tnx.pager.toPage(this, ${pageCount?c})"</#if>>${pageCount}</a></li>
</#if>
    <li class="next<#if !isMorePage || !isCountable> text-muted</#if>">
        <a<#if isMorePage && isCountable > <#if (tempHref != "")>href='${tempHref?replace("{pageNo}","${nextPage?c}")}'<#else>onclick="$.tnx.pager.toPage(this, ${nextPage?c})"</#if></#if>>&gt;</a>
    </li>
<#if pageNoInputtable>
    <li><input type="text" id="pageNo" value="${pageNo?c}" class="input-mini"  
        onkeydown="$.tnx.pager.pageNoKeydown(event,this,${pageCount?c});" onkeyup="$.tnx.pager.pageNoKeyup(this);" onfocus="this.select()"<#if (pageCount > 0)> maxlength="${pageCount?length}"</#if> /></li>
    <#if goText !="">
        <span><a onclick="$.tnx.pager.toPage(this)">${goText}</a></span>
    </#if>
<#else>
    <input id="pageNo"  type="hidden" />
</#if>
</ul>
<input id="pageTotal" type="hidden" value="${pageCount?c}" />
<#if (pageSizeOptions?size !=0 && pageSizeOptions[0]!="") >
    <select name="pageSize" id="pageSize" class="form-control pull-right" style="width:${55+pageSizeOptions[pageSizeOptions?size -1]?length*10}px; margin-right:7px;" onchange="$.tnx.pager.changePageSize(this.value);">
    <#list pageSizeOptions as option>
        <option value="${option}"<#if (pageSize?string = option)> selected="selected"</#if>>${option}</option>
    </#list>
    </select>
<#else>
    <input type="hidden" name="pageSize" id="pageSize"  value="${pageSize?c}"/>
</#if>
<div class="pull-right" style="margin: 6px 10px;">
<#if isCountable >
    <span>共 ${total?c} 条</span>
</#if>
<#if (pageSizeOptions?size !=0 && pageSizeOptions[0]!="") >
    <span>每页</span>
</#if>
</div>
