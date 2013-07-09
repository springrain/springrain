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
		<div class="path">${tableName!''} &gt; ${tableName!''}</div>
	</div>
	<div class="contents">
<!-- 查询 -->
<form name="searchForm" id="searchForm" method="post" action="${r"${ctx}"}/${classNameLowerCase}/list" >
<input type="hidden" name="pageIndex" id="pageIndex" value="${r"${(page.pageIndex)!'1'}"}" />
<input type="hidden" name="commTabId" id="commTabId" value="${r"${commTabId!''}"}"  />
<input type="hidden" name="sort" id="page_sort" value="${r"${(page.sort)!'desc'}"}"  />
<input type="hidden" name="order" id="page_order" value="${r"${(page.order)!'id'}"}"  />
			<table border="1" class="tb_2">
			<tr>
				<td>名称:<input type="text" id="name"  name="name" value="${r"${("}${classNameLowerCase}${r".name)!''}"}" class="inp_2" /></td>
				<td>
					<a href="javascript:submitForm('searchForm');"  class="btn_1" ></a>
				</td>
			</tr>
		</table>
		</form>
		
<!-- 数据列表 -->
		<dl class="box_1">
			<dt>
				<div>数据</div>
				<a class="a_1" href="javascript:export_excel('searchForm');">导出</a><div class="img_2"></div>
				<a class="a_3" href="javascript:f_newTab('add_${classNameLowerCase}','${r"${ctx}"}/${classNameLowerCase}/update/pre');">添加</a><div class="img_2"></div>
				<a class="a_0" href="javascript:delMulti();">删除选中</a><div class="img_2"></div>
			</dt>
			<dd>
			
			
			<!--start_export-->
				<table  id="listDataTable" border="1" class="tb_2">
			<!--end_no_export-->
			<!--first_start_export-->
					<tr id="table_first_tr"  bgcolor="#F1F1F1" >
					<!--first_start_no_export-->
						<th><input type="checkbox" name="check_all" id="check_all"/></th>
						<th width="70px;">操作</th>
					<!--first_end_no_export-->
					<#list table.columns as column>
						<#if !column.pk>
						<th id="th_${column.columnNameLower}" >${column.columnAlias}</th>
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
								<a href="javascript:f_newTab('${r"${data.id}"}','${r"${ctx}"}/${classNameLowerCase}/update/pre?id=${r"${data.id}"}');">修改</a>
								  /  <a href="javascript:del${className}('${r"${data.id}"}');">删除</a>
						</td>
				<!--end_no_export-->
						
						<#list table.columns as column>
							<#if !column.pk>
							<td >
								<#if column.isDateTimeColumn>
								<!--日期型-->
									<#assign columnDataValue = "((data."+column.columnNameLower+")?string('yyyy-MM-dd'))!''"> 
							${r"${"}${columnDataValue}${r"}"}
								<#elseif column.javaType == 'java.lang.Boolean'>
									<!--布尔型-->
									<#assign columnBooleanValue = "(data."+column.columnNameLower+")">
									${r'<#if'} ${columnBooleanValue}?? && ${columnBooleanValue} >
							真
									${r'<#else>'}
							假
									${r'</#if>'}
								<#elseif column.isNumberColumn>
								${r"${(data."}${column.columnNameLower}${r")!0}"}
								<#else>
								${r"${(data."}${column.columnNameLower}${r")!''}"}
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
