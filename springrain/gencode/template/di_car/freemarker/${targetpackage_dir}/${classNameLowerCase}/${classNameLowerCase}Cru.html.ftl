${r"<#escape x as x?html>"}

<SCRIPT type="text/javascript">
<!--

jQuery(document).ready(function(){
	//回选 下拉框
	//jQuery("#search_state option[value='${(returnDatas.queryBean.state)!'是'}']").prop("selected",true);
	
	/*
	$('.date-picker').datepicker({
		    autoclose: true,
		    format: 'yyyy-mm-dd',
		    language: 'zh-CN'
		    //,todayBtn: 'linked',
		    //startDate:new Date(2014,10,27),
			//endDate:'+60d',
		    //startDate: '+1d',
		}).next().on(ace.click_event, function(){
			$(this).prev().focus();
		});
	*/
	
	
	
	});
	
	
//-->
</SCRIPT>


<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>  
<#assign classNameLowerCase = className?lower_case>
<#assign from = basepackage?last_index_of(".")>
<#assign rootPagefloder = basepackage?substring(basepackage?last_index_of(".")+1)>

<div class="row">
	<div class="col-xs-12">
		<form class="form-horizontal" id="validation-form" method="POST" novalidate="novalidate" action="${r"${ctx}"}/${classNameLowerCase}/update" > 
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
					<#assign columnDataValue = "(("+classNameLower+"."+column.columnNameFirstLower+")?string('yyyy-MM-dd'))!'' ">
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="${column.columnNameFirstLower}" >${column.columnAlias}</label>
				<div class="col-xs-12 col-sm-9">
					<div class="clearfix">
					 <div class="input-group col-xs-12 col-sm-6">
						<input name="${column.columnNameFirstLower}"  class="form-control date-picker"  id="${column.columnNameFirstLower}" readonly="readonly" value="${r"${(returnDatas.data."}${column.columnNameFirstLower}${r")!''}"}"  type="text" />
					        <span class="input-group-addon">
								<i class="fa fa-calendar bigger-110"></i>
							</span>
					   </div>
					
					</div><div class="help-block" for="${column.columnNameFirstLower}"></div>
				</div>
			</div>
	        <div class="space-2"></div>
		<#else>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right" for="${column.columnNameFirstLower}" >${column.columnAlias}</label>
				<div class="col-xs-12 col-sm-9">
					<div class="clearfix">
						<input name="${column.columnNameFirstLower}" class="col-xs-12 col-sm-6" id="${column.columnNameFirstLower}" value="${r"${(returnDatas.data."}${column.columnNameFirstLower}${r")!''}"}"  type="text" />
					</div><div class="help-block" for="${column.columnNameFirstLower}"></div>
				</div>
			</div>
	        <div class="space-2"></div>
					</#if>
				</#if>
				</#list>
		</form>
		<div class="wizard-actions">
			<button class="btn btn-success btn-info" data-last="Finish" onclick="commonUpdateForm('validation-form','${r"${ctx}"}/${classNameLowerCase}/list');">
				保存
			</button>
		</div>
	</div>
</div>


${r"</#escape>"}