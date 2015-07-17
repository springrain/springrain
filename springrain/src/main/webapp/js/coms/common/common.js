function myhref(_url) {
	mySubmitForm("centfor_sco_ajax_form", _url);
}

/**
 * 不告诉用户删除结果，直接刷新页面
 * 
 * @param _url
 */
function mydelete(_url) {
	myconfirm("确定删除数据?", function() {
		myhref(_url);
	});
}
/**
 * 列表页面删除，弹框提示用户删除结果，再刷新指定页面,一般为列表
 * 
 * @param _url
 * @param listage
 */
function mydelete(_url, listage,par) {
	myconfirm("确定删除数据?", function() {
		myhref2page(_url,listage,par);
		});
}


/**
 * 批量删除
 * @param _url
 * @param formId
 * @param listage
 * @returns {Boolean}
 */
function mydeletemore(_url, formId,listage) {
	var arr = new Array();
	var checks = jQuery(":checkbox[name='check_li']");
	checks.each(function(i, _obj) {
		if (_obj.checked) {
			arr.push(_obj.value);
		}

	});
	if (arr.length < 1) {
		myalert("请选择要删除的记录!");
		return false;
	}

	myconfirm("确定删除选中数据?", function() {
		myhref2page(_url,listage,"records=" + arr.join(","));
	});
}




function myexport(formId, _url) {
	var _form = document.getElementById(formId);
	var _action = _form.action;
	_form.action = _url;
	_form.submit();
	_form.action = _action;
}
//提交FORM
function mySubmitForm(formId, _url) {
	var _type=jQuery('#' + formId).attr("method");
	if(!_type){
		_type="POST";
	}
	
	if (_url) {
		if (_url.indexOf("?") > 0)
			_url += "&_=" + new Date().getTime();
		else
			_url += "?_=" + new Date().getTime();
		jQuery('#' + formId).ajaxSubmit({
			url : _url,
			type:_type,
			target : '#ajax_target'
		});
	} else {
		jQuery('#' + formId).ajaxSubmit({
			type:_type,
			target : '#ajax_target'
		});
	}
	//去掉select2的
	jQuery("[role='status']").html('');
	jQuery(".select2-drop").remove();
}


//提交修改表单
function commonUpdateForm(form,listurl) {
	if(!form){
		form="updateForm";
	}
	jQuery.post($('#' + form).attr('action'), $('#' + form).serialize(),
			function(_json) {
				if (_json.status == "success") {
					myalert(_json.message, function() {
						myhref(listurl);
					});
				} else {
					myalert(_json.message);
				}
			});
}


//提交保存表单
function commonSaveForm(form,listurl,_id) {
	if(!form){
		form="updateForm";
	}
	var id="#id";
	if(_id){
		id="#"+_id;
	}
	
	jQuery(id,jQuery("#"+form)).val("");
	jQuery.post($('#' + form).attr('action'), $('#' + form).serialize(),
			function(_json) {
				if (_json.status == "success") {
					myalert(_json.message, function() {
						myhref(listurl);
					});
				} else {
					myalert(_json.message);
				}
			});
}


//提交保存表单
function myhref2page(_url,listurl,par) {
	if(!par){
		par=null;
	}
	jQuery.post(_url, par,
			function(_json) {
				if (_json.status == "success") {
					myalert(_json.message, function() {
						myhref(listurl);
					});
				} else {
					myalert(_json.message);
				}
			});
}



/**
 * 根据节点ID,选中多个Ztree的节点的复选框
 * @param nodeIds
 * @param ztreeId
 */
function checkedZtreeNodes(nodeIds,ztreeId){
	if(nodeIds==null){
		return;
	}
	var array=nodeIds.split(",");

	if(!array||array.length<1){
		return;
	}
	var menuMultiSelectTree = $.fn.zTree.getZTreeObj(ztreeId);
 	var tempNodes=menuMultiSelectTree.transformToArray(menuMultiSelectTree.getNodes());

	for(var i=0;i<tempNodes.length;i++){
		
		if(jQuery.inArray(tempNodes[i].id, array)>=0){
			menuMultiSelectTree.checkNode(tempNodes[i],true,false);
		}
	}
}
/**
 * 选中Ztree的一个节点
 * @param nodeId
 * @param ztreeId
 */
function selectZtreeOneNode(nodeId,ztreeId){
	if(nodeId==null){
		return;
	}

	var orgTree = $.fn.zTree.getZTreeObj(ztreeId);
 	var tempNodes=orgTree.transformToArray(orgTree.getNodes());

	for(var i=0;i<tempNodes.length;i++){
		if(tempNodes[i].id==nodeId){
			orgTree.selectNode(tempNodes[i]);
		}
	}
}


/* 赋值 */

function set_val(name, val) {
	if ($("#" + name + " option").length > 0) {
		$("#" + name).val(val);
		return;
	}

	if (($("#" + name).attr("type")) === "checkbox") {
		if (val == 1) {
			$("#" + name).attr("checked", true);
			return;
		}
	}
	if ($("." + name).length > 0) {
		if (($("." + name).first().attr("type")) === "checkbox") {
			var arr_val = val.split(",");
			for ( var s in arr_val) {
				$("input." + name + "[value=" + arr_val[s] + "]").attr(
						"checked", true);
			}
		}
	}
	if (($("#" + name).attr("type")) === "text") {
		if(typeof val==="number"&&val.length>11){
			try{
				val=getSmpFormatDateByLong(val);
				$("#" + name).val(val);
				return;
			}catch(e){
				$("#" + name).val(val);
				return;
			}
		}
		$("#" + name).val(val);
		return;
	}
	if (($("#" + name).attr("type")) === "hidden") {
		$("#" + name).val(val);
		return;
	}
	if (($("#" + name).attr("rows")) > 0) {
		$("#" + name).val(val);
		return;
	}
}
/*20140902 */
function myvalidate(_fromName){
	return $("#"+_fromName+"").Validform({
		btnSubmit:null,
		tiptype:3,
		label:".label",
		showAllError:true,
		datatype:{
			"zh1-6":/^[\u4E00-\u9FA5\uf900-\ufa2d]{1,6}$/,
			"my-age": /^\d{0,8}\.{0,1}(\d{1,1})?$/,
			"my-phone":/^([0-9]|[\-])+$/,
			"my-num":/^([1-9]\d*|0)(\.\d{1,2})?$/
			
		},
		ajaxPost:true
	  });
}

