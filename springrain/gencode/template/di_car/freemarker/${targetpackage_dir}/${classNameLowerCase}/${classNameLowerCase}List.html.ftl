${r"<#escape x as x?html>"}
<#assign className = table.className>   
<#assign tableName = table.tableAlias>   
<#assign classNameLower = className?uncap_first>  
<#assign classNameLowerCase = className?lower_case>
<#assign from = basepackage?last_index_of(".")>
<#assign rootPagefloder = basepackage?substring(basepackage?last_index_of(".")+1)>

<script type="text/javascript" src="${r"${ctx}"}/js/${rootPagefloder}/${classNameLowerCase}/${classNameLowerCase}.js"></script>

<script type="text/javascript">

function delete${className}(){
	var id=jQuery("#id").val();
	if(!id||id==""){
		myalert("请选择你要删除的记录");
		return;
	}else{
		var _url="${r"${ctx}"}/${classNameLowerCase}/delete?id="+id;
		var listurl="${r"${ctx}"}/${classNameLowerCase}/list";
		mydelete(_url,listurl);
	}
}
</script>


	

<!-- /.page-header -->

<div class="row">
    <div class="col-xs-12">
<div class="operate panel panel-default" style="height:65px;">
	<div class="panel-body">
		<div class="pull-left">
 <form class="form-horizontal" name="searchForm" id="searchForm" action="${r"${ctx}"}/${classNameLowerCase}/list" role="form">
            <input type="hidden" name="pageIndex" id="pageIndex" value="${r"${(returnDatas.page.pageIndex)!'1'}"}"/>
            <input type="hidden" name="sort" id="page_sort" value="${r"${(returnDatas.page.sort)!'desc'}"}"/>
            <input type="hidden" name="order" id="page_order" value="${r"${(returnDatas.page.order)!'id'}"}"/>
			<label for="search_state"><b>是否可用:</b></label> 
			 <select id="search_state" name="state" class="col-10" >
				<option value="是">是</option>
				<option value="否">否</option>
			</select>
			<a  href="javascript:mySubmitForm('searchForm');"
				class="btn btn-purple btn-sm">
					查询 <i class="ace-icon fa fa-search icon-on-right bigger-10"></i>
				</a>

</form>
		</div>
		<div class="pull-right">
			    ${r"<@shiro.hasPermission"} name="/${classNameLowerCase}/list/export" >
				  <button onclick="myexport('searchForm','${r"${ctx}"}/${classNameLowerCase}/list/export');" class="btn  btn-sm  btn-primary">
                      
                        导出
                    </button>
		    	${r"</@shiro.hasPermission>"}
			    ${r"<@shiro.hasPermission"} name="/${classNameLowerCase}/update" >
				  <button onclick="myhref('${r"${ctx}"}/${classNameLowerCase}/update/pre');" class="btn  btn-sm  btn-primary">
                        添加
                    </button>
			    ${r"</@shiro.hasPermission>"}
			    ${r"<@shiro.hasPermission"} name="/${classNameLowerCase}/delete" >
			    |
				  <button onclick="delete${className}();" class="btn btn-sm btn-danger">
                        删除
                    </button>
			    ${r"</@shiro.hasPermission>"}
			
		</div>
	</div>
</div>

   <div class="row">
            <div class="col-xs-12">
	            <!--start_export-->
                <table id="listDataTable" border="1" class="table table-striped table-bordered table-hover">
                    <!--end_no_export-->
                    <!--first_start_export-->
                    <thead>
                    <tr>
                        <!--first_start_no_export-->
                        <th class="center">
                            <label class="position-relative">
                                <input class="ace" type="checkbox">
                                <span class="lbl"></span>
                            </label>
                        </th>
                        <th>操作</th>
                        <!--first_end_no_export-->
                       	<#list table.columns as column>
						<#if !column.pk>
						<th id="th_${column.columnNameFirstLower}" >${column.columnAlias}</th>
						</#if>
					</#list>
                    </tr>
                    </thead>
                    <!--first_end_export-->

                    <!--start_export-->
                    <tbody>
           
				   ${r"<#if"} (returnDatas.data??)&&(returnDatas.data?size>0)>
					 ${r"<#list"} returnDatas.data as _data>
                            <!--start_no_export-->
                            <tr class="">
                                <td class="center">
                                    <label class="position-relative">
                                        <input name="check_li" value="${r'${_data'}.id}" class="ace" type="checkbox">
                                        <span class="lbl"></span>
                                    </label>
                                </td>
                                <td>
                                    <div class="hidden-sm hidden-xs btn-group">
                                        <button class="btn btn-xs btn-info"
                                                onclick="myhref('${r"${ctx}"}/${classNameLowerCase}/update/pre?${table.pkColumn.columnNameFirstLower}=${r"${(_data.id)!''}"}');">
                                            <i class="ace-icon fa fa-pencil bigger-120"></i>
                                        </button>
                                        <button class="btn btn-xs btn-danger"
                                                onclick="mydelete('${r"${ctx}"}/${classNameLowerCase}/delete?${table.pkColumn.columnNameFirstLower}=${r"${(_data.id)!''}"}','${r"${ctx}"}/${classNameLowerCase}/list');">
                                            <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                        </button>
                                    </div>
                                    <div class="hidden-md hidden-lg">
                                        <div class="inline position-relative">
                                            <button class="btn btn-minier btn-primary dropdown-toggle"
                                                    data-toggle="dropdown" data-position="auto">
                                                <i class="ace-icon fa fa-cog icon-only bigger-110"></i>
                                            </button>
                                            <ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
	                                          <li>
													<a  href="javascript:myhref('${r"${ctx}"}/${classNameLowerCase}/look?${table.pkColumn.columnNameFirstLower}=${r"${(_data.id)!''}"}');"  class="tooltip-info" data-rel="tooltip" title="View">
														<span class="blue">
															<i class="ace-icon fa fa-search-plus bigger-120"></i>
														</span>
													</a>
											  </li>	
                                                <li>
                                                    <a data-original-title="Edit"
                                                       href="javascript:myhref('${r"${ctx}"}/${classNameLowerCase}/update/pre?${table.pkColumn.columnNameFirstLower}=${r"${(_data.id)!''}"}');"
                                                       class="tooltip-success" data-rel="tooltip" title="">
																		<span class="green">
																			<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																		</span>
                                                    </a>
                                                </li>
                                                <li>
                                                    <a data-original-title="Delete"
                                                       href="javascript:mydelete('${r"${ctx}"}/${classNameLowerCase}/delete?${table.pkColumn.columnNameFirstLower}=${r"${(_data.id)!''}"}','${r"${ctx}"}/${classNameLowerCase}/list');"
                                                       class="tooltip-error" data-rel="tooltip" title="">
																		<span class="red">
																			<i class="ace-icon fa fa-trash-o bigger-120"></i>
																		</span>
                                                    </a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </td>
                                <!--end_no_export-->

                           <#list table.columns as column>
							<#if !column.pk>
							<td >
								<#if column.isDateTimeColumn>
								<!--日期型-->
									<#assign columnDataValue = "((_data."+column.columnNameFirstLower+")?string('yyyy-MM-dd'))!''"> 
							${r"${"}${columnDataValue}${r"}"}
								<#elseif column.javaType == 'java.lang.Boolean'>
									<!--布尔型-->
									<#assign columnBooleanValue = "(_data."+column.columnNameFirstLower+")">
									${r'<#if'} ${columnBooleanValue}?? && ${columnBooleanValue} >
							真
									${r'<#else>'}
							假
									${r'</#if>'}
								<#elseif column.isNumberColumn>
								${r"${(_data."}${column.columnNameFirstLower}${r")!0}"}
								<#else>
								${r"${(_data."}${column.columnNameFirstLower}${r")!''}"}
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
            <!-- /.span -->
        </div>
        ${r"<#if returnDatas.page??>"}	
            ${r"<@h.pagetoolbar page=returnDatas.page formId='searchForm' />"}
         ${r"</#if>"}
    </div>
</div>
<!-- /.main-container -->
${r"</#escape>"}