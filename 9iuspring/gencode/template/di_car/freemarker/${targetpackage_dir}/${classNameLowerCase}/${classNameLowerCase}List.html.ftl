<#assign className = table.className>   
<#assign tableName = table.tableAlias>   
<#assign classNameLower = className?uncap_first>  
<#assign classNameLowerCase = className?lower_case>
<#assign from = basepackage?last_index_of(".")>
<#assign rootPagefloder = basepackage?substring(basepackage?last_index_of(".")+1)>

${r"<@h.easyui />"}
<script type="text/javascript" src="${r"${ctx}"}/js/plugins/jquery.checkbox.js"></script>
<script type="text/javascript" src="${r"${ctx}"}/js/${rootPagefloder}/${classNameLowerCase}/${classNameLowerCase}.js"></script>
<script type="text/javascript" src="${r"${ctx}"}/js/frame/list.js"></script>
	<#list table.columns as column>
	<#if column.isDateTimeColumn>	
		<script type="text/javascript" src="${r"${ctx}"}/js/my97/WdatePicker.js"></script>
		<#break>
	</#if>
</#list>
</head>


<script type="text/javascript">
	 $(function(){
         var pg = $("#listDataTable").datagrid("getPager");   
         if(pg)   
         {   
            $(pg).pagination({   
                 onBeforeRefresh:function(){   

                 },   
                 onRefresh:function(pageNumber,pageSize){   
                	  document.getElementById("pageIndex").value=pageNumber;
                      document["searchForm"].submit();
                 },   
                 onChangePageSize:function(){   
                 },   
                 onSelectPage:function(pageNumber,pageSize){   
                        document.getElementById("pageIndex").value=pageNumber;
                        document["searchForm"].submit();
                 }   
            });   
         }  
     });
	 
 </script>

<body>

<div class="easyui-layout" style="width: 100%; height: 100%;" data-options="fit:true">
	<div data-options="region:'north',title:'查询条件',border:false"  style="height:100%;overflow: hidden; margin:0px;padding:0px;">
			
<form name="searchForm" id="searchForm" method="post" action="${r"${ctx}"}/${classNameLowerCase}/list"  style="margin:0px;padding:0px; width: 100%;height: 100%">
<input type="hidden" name="pageIndex" id="pageIndex" value="${r"${(page.pageIndex)!'1'}"}" />
<input type="hidden" name="commTabId" id="commTabId" value="${r"${commTabId!''}"}"  />
<input type="hidden" name="sort" id="page_sort" value="${r"${(page.sort)!'desc'}"}"  />
<input type="hidden" name="order" id="page_order" value="${r"${(page.order)!'id'}"}"  />
			  <table class="tb_2">
                       <tr>
                                <td>名称:<input type="text" id="name"  name="name" value="${(users.name)!''}" class="inp_2" /></td>
                                <td>
                                    <a href="javascript:submitForm('searchForm');"  class="btn_1" ></a>
                                </td>
                       </tr>
                </table>
 </form>
	 </div>
    <div data-options="region:'center',title:'${classNameLowerCase}列表', border:false" style="margin:0px;padding:0px;">			
<!-- 数据列表 -->
			
			<!--start_export-->
				<table id="listDataTable" class="easyui-datagrid" border="false"
					fit="true" fitColumns="false" idField="id" sortName="id"
					sortOrder="asc" pagination="true" pageSize="10"
					pageList="[10]" nowrap="false"
					checkOnSelect="false" selectOnCheck="false" loadMsg="正在为您加载数据"
					toolbar="#auditlog_toolbar">
					
			<!--end_no_export-->
			<!--first_start_export-->
			     <thead>
					<tr id="table_first_tr">
					<!--first_start_no_export-->
						<th field="id" data-options="checkbox : true"><input type="checkbox" name="check_all" id="check_all"/></th>
						<th field="action" width="80" align="center">操作</th>
					<!--first_end_no_export-->
					<#list table.columns as column>
						<#if !column.pk>
						<th field="${column.columnNameLower}"  width="80" align="center" >${column.columnAlias}</th>
						</#if>
					</#list>
						
					</tr>
				</thead>
				<!--first_end_export-->
				
				<!--start_export-->
				<tbody>
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
									<#assign columnDataValue = "((data."+column.columnNameLower+")!\"0000-00-00 00:00:00\")?datetime">
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
				</tbody>
				</table>
			 <!--end_export-->
			 </div>
</div>

<!-- 工具条 -->
<div id="auditlog_toolbar" style="display: none;">
	<a class="easyui-linkbutton"  data-options="iconCls:'icon-undo',plain:true" style="float: left;" href="javascript:export_excel('searchForm');"  >导出</a>
    <div class="datagrid-btn-separator"></div>
    <a class="easyui-linkbutton"  data-options="iconCls:'icon-add',plain:true"  style="float: left;" href="javascript:f_newTab('add_${classNameLowerCase}','${r"${ctx}"}/${classNameLowerCase}/update/pre');">添加</a>
    <div class="datagrid-btn-separator"></div>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true"  href="javascript:delMulti();">删除选中</a>
</div>
</body>
</html>
