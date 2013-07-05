<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>  
<#assign classNameLowerCase = className?lower_case>
<#assign from = basepackage?last_index_of(".")>
<#assign rootPagefloder = basepackage?substring(basepackage?last_index_of(".")+1)>

${r"<@h.easyui />"}
<script type="text/javascript" src="${r"${ctx}"}/js/${rootPagefloder}/${classNameLowerCase}/${classNameLowerCase}Cru.js"></script>
<#list table.columns as datecolumn>
	<#if datecolumn.isDateTimeColumn>	
		<script type="text/javascript" src="${r"${ctx}"}/js/my97/WdatePicker.js"></script>
		<#break>
	</#if>
</#list>
<body>

<div class="easyui-layout" style="width: 100%; height: 100%;"
    data-options="fit:true">
    <div data-options="region:'center',title:'Auditlog &gt; add_auditlog'" style="padding: 2px;">
        <div class="easyui-layout" data-options="fit : true,border : false">
            <div data-options="region:'center',border:false">
<form id="updateForm" name="updateForm"  method="post" action="${r"${ctx}"}/${classNameLowerCase}/update" >
<input type="hidden" name="commTabId" id="commTabId" value="${r"${commTabId!''}"}"  />
<!--input  hidden  Start-->
	<#list table.columns as column>
		<#if column.pk>
			<#assign columnValue = "("+classNameLower+"."+column.columnNameLower+")!''">
	<input type="hidden" id="${column.columnNameLower}" name="${column.columnNameLower}" value="${r"${"}${columnValue}${r"}"}"/>	
		</#if>
	</#list>
<!--input  hidden  End-->
	<table class="tb_6">	
		<!-- Start-->
		<#list table.columns as column>
			<#if !column.pk>
				<#assign columnValue = "("+classNameLower+"."+column.columnNameLower+")!''">
			<tr>
				<th><#if !column.nullable><span >*</span></#if>${column.columnAlias}:</th>	
				<#if column.isDateTimeColumn>
					<!--日期型-->
					<#assign columnDataValue = "(("+classNameLower+"."+column.columnNameLower+")!\"0000-00-00\")?date">
					<td>
					<input type="text" id="${column.columnNameLower}" name="${column.columnNameLower}" 
					<#if !column.nullable> class="easyui-validatebox" data-options="required:true" </#if> 
					style="width:150px;" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" value="${r"${"}${columnDataValue}${r"}"}" />
					</td>
				<#elseif column.javaType == 'java.lang.Boolean'>
					<!--布尔型-->
					<#assign columnBooleanValue = classNameLower+"."+column.columnNameLower>
					${r'<#if'} ${columnBooleanValue}??>
					${r'<#if'} ${columnBooleanValue}>
					<input type="radio" id="${column.columnNameLower}" name="${column.columnNameLower}" checked value="0"/>是
					<input type="radio" id="${column.columnNameLower}" name="${column.columnNameLower}" value="1"/>否
					${r'<#else>'}
					<input type="radio" id="${column.columnNameLower}" name="${column.columnNameLower}"  value="0"/>是
					<input type="radio" id="${column.columnNameLower}" name="${column.columnNameLower}" checked value="1"/>否
					${r'</#if>'}
					${r'<#else>'}
					<input type="radio" id="${column.columnNameLower}" name="${column.columnNameLower}" checked value="0"/>是
					<input type="radio" id="${column.columnNameLower}" name="${column.columnNameLower}"  value="1"/>否
					${r'</#if>'}	
				<#else>
					<!-- 字符型 -->
					<td>
					<input type="text" id="${column.columnNameLower}" name="${column.columnNameLower}" value="${r"${"}${columnValue}${r"}"}" maxlength="${column.size}" <#if !column.nullable> class="easyui-validatebox" data-options="required:true,validType:['length[1,${column.size}]']" </#if> />
					</td>	
				</#if>		
			</tr>
			</#if>
		</#list>
	</table>
					<input type="button" onclick="submitUpdateForm();" class="btn_7"/>
				</form>
            </div>
        </div>
    </div>
</div>
</body>
</html>