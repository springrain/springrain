${r"<#escape x as x?html>"}
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>  
<#assign classNameLowerCase = className?lower_case>
<#assign from = basepackage?last_index_of(".")>
<#assign rootPagefloder = basepackage?substring(basepackage?last_index_of(".")+1)>
<div class="row">
	<div class="col-xs-12">
		<!--input  hidden  Start-->
	<#list table.columns as column>
		<#if column.pk>
			<#assign columnValue = "("+classNameLower+"."+column.columnNameFirstLower+")!''">
	<input type="hidden" id="${column.columnNameFirstLower}" name="${column.columnNameFirstLower}" value="${r"${(returnDatas.data."}${column.columnNameFirstLower}${r")!''}"}"/>	
		</#if>
	</#list>
<!--input  hidden  End-->
			<#list table.columns as column>
			  <#if !column.pk>
				<#assign columnValue = "("+classNameLower+"."+column.columnNameFirstLower+")!''">
		         <#if column.isDateTimeColumn>
					<!--日期型-->
					<#assign columnDataValue = column.columnNameFirstLower+")?string('yyyy-MM-dd'))!'' ">
						<div class="form-group">
							<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="${column.columnNameFirstLower}" >${column.columnAlias}</label>
							<div class="col-xs-12 col-sm-9">
								<div class="clearfix">
									${r"${((returnDatas.data."}${columnDataValue}${r"}"}
								</div>
							</div>
						</div>
	                    <div class="space-2"></div>
					<#else>
						<div class="form-group">
							<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="${column.columnNameFirstLower}" >${column.columnAlias}</label>
							<div class="col-xs-12 col-sm-9">
								<div class="clearfix">
								${r"${(returnDatas.data."}${column.columnNameFirstLower}${r")!''}"}
								</div>
							</div>
						</div>
	                    <div class="space-2"></div>
					</#if>
				</#if>
				</#list>
	</div><!-- /.col -->
</div>
${r"</#escape>"}