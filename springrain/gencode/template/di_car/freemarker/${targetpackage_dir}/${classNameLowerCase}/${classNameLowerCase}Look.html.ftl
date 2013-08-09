<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>  
<#assign classNameLowerCase = className?lower_case>
<#assign from = basepackage?last_index_of(".")>
<#assign rootPagefloder = basepackage?substring(basepackage?last_index_of(".")+1)>

${r"<@h.easyui />"}


<input type="hidden" name="commTabId" id="commTabId" value="${r"${commTabId!''}"}"  />
<!--input  hidden  Start-->
	<#list table.columns as column>
		<#if column.pk>
			<#assign columnValue = "("+classNameLower+"."+column.columnNameFirstLower+")!''">
	<input type="hidden" id="${column.columnNameFirstLower}" name="${column.columnNameFirstLower}" value="${r"${"}${columnValue}${r"}"}"/>	
		</#if>
	</#list>
<!--input  hidden  End-->
	<table class="tb_6">	
		<!-- Start-->
		<#list table.columns as column>
			<#if !column.pk>
				<#assign columnValue = "("+classNameLower+"."+column.columnNameFirstLower+")!''">
			<tr>
				<th><#if !column.nullable><span >*</span></#if>${column.columnAlias}:</th>	
				<#if column.isDateTimeColumn>
					<!--日期型-->
					<#assign columnDataValue = "(("+classNameLower+"."+column.columnNameFirstLower+")?string('yyyy-MM-dd'))!'' ">
					<td>
					  ${r"${"}${columnDataValue}${r"}"}
					</td>
		
				<#else>
					<!-- 字符型 -->
					<td>
					${r"${"}${columnValue}${r"}"} 
					</td>	
				</#if>		
			</tr>
			</#if>
		</#list>
	</table>
	