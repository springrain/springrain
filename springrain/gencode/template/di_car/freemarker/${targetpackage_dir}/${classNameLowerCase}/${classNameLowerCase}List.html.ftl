<#assign className = table.className>   
<#assign tableName = table.tableAlias>   
<#assign classNameLower = className?uncap_first>  
<#assign classNameLowerCase = className?lower_case>
<#assign from = basepackage?last_index_of(".")>
<#assign rootPagefloder = basepackage?substring(basepackage?last_index_of(".")+1)>

${r"<@h.easyui />"}
<script type="text/javascript" src="${r"${ctx}"}/js/plugins/jquery.checkbox.js"></script>
<script type="text/javascript" src="${r"${ctx}"}/js/${rootPagefloder}/${classNameLowerCase}/${classNameLowerCase}.js"></script>
	<#list table.columns as column>
	<#if column.isDateTimeColumn>	
		<script type="text/javascript" src="${r"${ctx}"}/js/my97/WdatePicker.js"></script>
		<#break>
	</#if>
</#list>
</head>

<script type="text/javascript">
<!--
jQuery(document).ready(function(){
     //初始化 排序图标
    initSortTable("listDataTable","searchForm");
	//添加颜色改变
	mouseTrColor("listDataTable");
	
});
//-->
</script>

<body>
<!-- 操作菜单 -->
	<div class="head">
		<div class="path">${tableName!''}管理</div>
	</div>
	<div class="contents">
<!-- 查询 -->
<form name="searchForm" id="searchForm" method="post" action="${r"${ctx}"}/${classNameLowerCase}/list" >
<input type="hidden" name="pageIndex" id="pageIndex" value="${r"${(page.pageIndex)!'1'}"}" />
<input type="hidden" name="commTabId" id="commTabId" value="${r"${commTabId!''}"}"  />
<input type="hidden" name="sort" id="page_sort" value="${r"${(page.sort)!'desc'}"}"  />
<input type="hidden" name="order" id="page_order" value="${r"${(page.order)!'id'}"}"  />
		<table class="tb_2">
			<tr>
			
			<#list table.columns as column>
			<#if !column.pk>
			<#if column.isDateTimeColumn>
			<#assign columnDataValue = "(("+classNameLower+"."+column.columnNameFirstLower+")?string('yyyy-MM-dd'))!'' ">
			  <td>${column.columnAlias}:<input type="text" id="${column.columnNameFirstLower}" name="${column.columnNameFirstLower}"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" value="${r"${"}${columnDataValue}${r"}"}"   class="inp_2" /></td>
			<#else>
	         <td>${column.columnAlias}:<input type="text" id="${column.columnNameFirstLower}" name="${column.columnNameFirstLower}"  value="${r"${"}(${classNameLower}.${column.columnNameFirstLower})!''${r"}"}"   class="inp_2" /></td>
	         </#if>
			 </#if>
		   </#list>
				<td>
					<input type="button" onclick="submitForm('searchForm');"  value="搜 索" class="btn_search"  />  
				</td>
			</tr>
		</table>
		</form>
		
<!-- 数据列表 -->
		<dl class="box_1">
			<dt>
				<div>数据</div>
				${r"<@shiro.hasPermission"} name="/${classNameLowerCase}/list/export" >
				<a class="a_1" href="javascript:export_excel('searchForm');">导出</a><div class="img_2" ></div>
				${r"</@shiro.hasPermission>"}
				${r"<@shiro.hasPermission"} name="/${classNameLowerCase}/update" >
				<a class="a_3" href="javascript:f_newTab('add_${classNameLowerCase}','add_${classNameLowerCase}','add_${classNameLowerCase}','${r"${ctx}"}/${classNameLowerCase}/update/pre');">添加</a><div class="img_2" ></div>
				${r"</@shiro.hasPermission>"}
				${r"<@shiro.hasPermission"} name="/${classNameLowerCase}/delMulti" >
				<a class="a_0" href="javascript:delMulti();">删除选中</a><div class="img_2"></div>
				${r"</@shiro.hasPermission>"}
			</dt>
			<dd>
			
			
			<!--start_export-->
				<table  id="listDataTable" border="1" class="tb_2">
			<!--end_no_export-->
			<!--first_start_export-->
					<tr id="table_first_tr"  bgcolor="#F1F1F1" >
					<!--first_start_no_export-->
						<th><input type="checkbox" name="check_all" id="check_all"/></th>
						<th width="100px;">操作</th>
					<!--first_end_no_export-->
					<#list table.columns as column>
						<#if !column.pk>
						<th id="th_${column.columnNameFirstLower}" >${column.columnAlias}</th>
						</#if>
					</#list>
						
					</tr>
				<!--first_end_export-->
				
				<!--start_export-->
				   ${r"<#if"} datas??&&((datas?size)>0)>
					${r"<#list"} datas as data>
						<tr>
				<!--start_no_export-->
						${r'<#if'} (datas?size > 0)>
							<td align="center">
								<input type="checkbox" name="check_li" value="${r'${data'}.id}" />
							</td>
						${r'</#if>'}
						
						<td style="text-align:center;">
								<a href="javascript:f_newTab('${r"${data.id}_update"}','${r"${data.id}_update"}','${r"${data.id}_update"}','${r"${ctx}"}/${classNameLowerCase}/update/pre?${table.pkColumn.columnNameFirstLower}=${r"${data.id}"}');">修改</a>
								  /  <a href="javascript:del${className}('${r"${data.id}"}');">删除</a>/<a href="javascript:f_newTab('${r"${data.id}_look"}','${r"${data.id}_look"}','${r"${data.id}_look"}','${r"${ctx}"}/${classNameLowerCase}/look?${table.pkColumn.columnNameFirstLower}=${r"${data.id}"}');">查看</a>
						</td>
				<!--end_no_export-->
						
						<#list table.columns as column>
							<#if !column.pk>
							<td >
								<#if column.isDateTimeColumn>
								<!--日期型-->
									<#assign columnDataValue = "((data."+column.columnNameFirstLower+")?string('yyyy-MM-dd'))!''"> 
							${r"${"}${columnDataValue}${r"}"}
								<#elseif column.javaType == 'java.lang.Boolean'>
									<!--布尔型-->
									<#assign columnBooleanValue = "(data."+column.columnNameFirstLower+")">
									${r'<#if'} ${columnBooleanValue}?? && ${columnBooleanValue} >
							真
									${r'<#else>'}
							假
									${r'</#if>'}
								<#elseif column.isNumberColumn>
								${r"${(data."}${column.columnNameFirstLower}${r")!0}"}
								<#else>
								${r"${(data."}${column.columnNameFirstLower}${r")!''}"}
								</#if>
							</td>
							</#if>
						</#list>
							
						</tr>
					${r"</#list>"}
					 ${r"</#if>"}
				</table>
			 <!--end_export-->
			</dd>
${r"<#if page??>"}		
<!-- 分页 -->
	  ${r"<@h.pagetoolbar page=page formName='searchForm'/>"}
${r"</#if>"}
		</dl>
	</div>
</body>
</html>
