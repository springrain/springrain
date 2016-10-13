${r"<#escape x as x?html>"}
<#assign className = table.className>   
<#assign tableName = table.tableAlias>   
<#assign classNameLower = className?uncap_first>  
<#assign classNameLowerCase = className?lower_case>
<#assign from = basepackage?last_index_of(".")>
<#assign rootPagefloder = basepackage?substring(basepackage?last_index_of(".")+1)>

<script type="text/javascript" src="${r"${ctx}"}/js/${rootPagefloder}/${classNameLowerCase}/${classNameLowerCase}.js"></script>

<script type="text/javascript">


jQuery(document).ready(function(){

	$(".sub_left_menu tbody tr").click(function() {
			$(".sub_left_menu tbody tr").removeClass("active");
			$(this).attr("class", "active");
			var _url=ctx + "/${classNameLowerCase}/look/json?id="+ $(this).attr("id");
		
			jQuery.ajax({
				url : _url,
				type : "post",
				dataType : "json",
				success : function(_json) {
					if(_json.status=="success"){
						showdata(_json);
					}
				}
			});
			return false;
	})

});

	
	
	

function showdata(result) {
	for (var s in result.data) {
		set_val(s, result.data[s]);
	}
}






var listurl="${r"${ctx}"}/${classNameLowerCase}/list";
function delete${className}(){
	var id=jQuery("#id").val();
	if(!id||id==""){
		myalert("请选择你要删除的记录");
		return;
	}else{
		var _url="${r"${ctx}"}/${classNameLowerCase}/delete?id="+id;
		mydelete(_url,listurl);
	}
}

function update${className}(){
    var id=jQuery("#id").val();
	if(!id||id==""){
		myalert("请选择你要更新的记录");
		return;
	}else{
		var _url="${r"${ctx}"}/${classNameLowerCase}/update?id="+id;
		commonUpdateForm("updateForm",_url,listurl);
	}

}

function save${className}(){
	commonUpdateForm("updateForm",listurl);
}

</script>

<div class="operate panel panel-default" style="height: 65px;">
	<div class="panel-body">
	
		<div class="pull-left">
			<form class="form-horizontal" method="post"  action="${r"${ctx}"}/${classNameLowerCase}/list" name="searchForm" id="searchForm" onkeydown="if(event.keyCode==13)return false;">
				 <input type="hidden" name="pageIndex" id="pageIndex" value="${r"${(returnDatas.page.pageIndex)!'1'}"}"/>
				 <input type="hidden" name="sort" id="page_sort" value="${r"${(returnDatas.page.sort)!'desc'}"}"/>
				 <input type="hidden" name="order" id="page_order" value="${r"${(returnDatas.page.order)!'id'}"}"/>
				 
				 <label for="search_name"><b>名称:</b></label> 
				 <input type="text" id="search_name" name="name" placeholder="请输入名称" value="${r"${(returnDatas.queryBean.name)!''}"}"> 
				 <label for="search_state"><b>是否可用:</b></label> 
				 <select id="search_state" name="state">
					<option value="是">是</option>
					<option value="否">否</option>
				</select> 
				<a href="javascript:mySubmitForm('searchForm');" class="btn btn-purple btn-sm"> 
				    查询 
					<iclass="ace-icon fa fa-search icon-on-right bigger-10"></i>
				</a>

			</form>
		</div>



		<div class="pull-right">
			 ${r"<@shiro.hasPermission"} name="/${classNameLowerCase}/update" >
				<a id="btn_reload" href="javascript:location.reload();" class="btn  btn-sm  btn-primary " style="display: none;"> 
					跳转至新增>>
				</a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	
				<a id="btn_add" href="javascript:save${className}();" class="btn  btn-sm  btn-primary "> 
					<img src="${r"${ctx}"}/images/btn_save.png" style="vertical-align: middle;" />保存新增
				</a>
				
				 <a id="btn_update" href="javascript:update${className}();" class="btn  btn-sm  btn-primary " style="display: none;">
				    <img src="${r"${ctx}"}/images/btn_save.png" style="vertical-align: middle;" />
				  	保存修改
				 </a> 
			 ${r"</@shiro.hasPermission>"} 
			
			 ${r"<@shiro.hasPermission"} name="/${classNameLowerCase}/delete" > 
				 <a id="btn_delete" href="javascript:delete${className}();" class="btn btn-sm btn-danger " style="display: none;">
					删除
				 </a> 
			 ${r"</@shiro.hasPermission>"} 
		</div>

	</div>
</div>




<!-- /.page-header -->



<div class="row">
	<div class="col-xs-12">
		<div class="row">
			<div class="col-xs-12">
				<div class="col-sm-5 sub_left_menu ">
					<div class="widget-box">
						<div class="widget-header">
							<h4 class="widget-title">数据列表</h4>
						</div>
						<div class="widget-body">
							<div class="widget-main">
							 <!--start_export-->
								<table  class="table table-striped table-bordered table-hover">
								  <!--end_no_export-->
                                  <!--first_start_export-->
									<thead>
										<tr>
										 <!--first_start_no_export-->
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
									<!--end_no_export-->
									 
										<tr id="${r'${_data'}.id}">
										
										
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
						</div>
					</div>
				</div>
				<div class="col-sm-7">
					<div class="widget-box">
						<div class="widget-header">
							<h4 class="widget-title">详细信息</h4>
						</div>
						<div class="widget-body">
							<div class="widget-main">
								<form class="form-horizontal clearfix" method="post" action="${r"${ctx}"}/${classNameLowerCase}/update"  name="updateForm" id="updateForm">
								
								<#list table.columns as column>
									<#if column.pk>
										<#assign columnValue = "("+classNameLower+"."+column.columnNameFirstLower+")!''">
										<input type="hidden" id="${column.columnNameFirstLower}" name="${column.columnNameFirstLower}" value=""/>	
									</#if>
								</#list>
									
									<div class="col-sm-6">
								
								    <#list table.columns as column>
									 <#if !column.pk>
										<#assign columnValue = "("+classNameLower+"."+column.columnNameFirstLower+")!''">
											 <#if column.isDateTimeColumn>
												<!--日期型-->
												<#assign columnDataValue = "(("+classNameLower+"."+column.columnNameFirstLower+")?string('yyyy-MM-dd'))!'' ">
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right" for="${column.columnNameFirstLower}" >${column.columnAlias}</label>
										
												 <div class="col-sm-9">
													<input name="${column.columnNameFirstLower}"  class="form-control date-picker"  id="${column.columnNameFirstLower}" readonly="readonly" value=""  type="text" />
												 </div> 
										</div>
										<#else>
										
										<div class="form-group">
											<label class="col-sm-3 control-label no-padding-right" for="${column.columnNameFirstLower}" >${column.columnAlias}</label>
												 <div class="col-sm-9">
													<input name="${column.columnNameFirstLower}"  id="${column.columnNameFirstLower}" value="" placeholder="${column.columnAlias}" class="form-control"  type="text" />
												 </div> 
										</div>
										</#if>
									  </#if>
								  </#list>
																		
									</div>

								</form>
							</div>
						</div>
					</div>


				</div>



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