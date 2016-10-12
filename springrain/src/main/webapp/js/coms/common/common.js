/*
内容区页面跳转
*/
/*function myhref(_url,menuId) {
	mySubmitForm("centfor_sco_ajax_form", _url);
}*/

function mzywxhref(_url,menuId) {
	layer.load();
	mySubmitForm("centfor_sco_ajax_form", _url);
	layer.closeAll();
}

function gentimestampstr(){
	return new Date().getTime();
}

function getcurrentMenuId(){
	var currentPageUrl=window.location.href;
	var urlElementArr=currentPageUrl.split("?");
	var menuId='';
	if(urlElementArr.length>1){//非首页
		var paramElementArr=urlElementArr[1].split("#");
		menuId= paramElementArr[0].split("=")[1];
		menuId=menuId.replace("&t","");
	}
	return menuId;
}

function myhref(_url,menuId) {
	var t=gentimestampstr();
	if(menuId==null){
		menuId=getcurrentMenuId();
		if(menuId==''){
			menuId=t;
		}
	}
	var urlnew="index?id="+menuId+"&t="+t+"#href="+_url; 
	location.href=urlnew;
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
		if(!!$('#' + formId).html()){
			jQuery('#' + formId).ajaxSubmit({
				type:_type,
				target : '#ajax_target'
			});
		}
		
	}
	//去掉select2的
	jQuery("[role='status']").html('');
	jQuery(".select2-drop").remove();
}


//提交修改表单
function commonUpdateForm(formId,listurl) {
	if(!formId){
		formId="updateForm";
	}
	
	 var pageurl=$("#"+formId).attr('action'); 
		var mydata=$("#"+formId).serialize();
		ajaxpostonlayer(pageurl,listurl,mydata);
	
   /*
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
	*/
	
	
	
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
//打开新链接(相对路径)
function openUrl(_url) {
	if(_url=="#"){
		return;
	}
	window.location.href = _url;
}
//打开新链接(全路径)
function openUrlctx(_url) {
	if(_url=="#"){
		return;
	}
	window.location.href = ctx+_url;
}
/**
 * 带layer的提交 listurl跳转地址 为空不跳转  
 */
function submitonlayer(formId,listurl,msg){
    var pageurl=$("#"+formId).attr('action'); 
	var mydata=$("#"+formId).serialize();
	ajaxpostonlayer(pageurl,listurl,mydata,msg);
}

function ajaxpostonlayer(pageurl,listurl,mydata,msg){
	var index = layer.load(null, {shade: [0.8, '#393D49'] });
	if(pageurl==null||pageurl==''){
		layer.alert('提交地址不能为空！', {icon: 5});
		return false;
	}
	if(!msg){
		msg="操作成功!";
	}
	$.ajax({
		url :pageurl, 
	    type :"post",
		data:mydata,
		dataType : "json",
		success:function(ret){
			layer.closeAll('loading')
			if(ret.status=="success"){
				layer.alert(msg, {icon: 1},function(){
					layer.closeAll();
					if(listurl!=null&&listurl!=""){
				       myhref(listurl);
					}
				});
				
			}else{
				myerror('sorry,操作失败了 ...');
			}
		},
		error:function(){
			layer.closeAll('loading')
			myerror('sorry,操作失败了 ...');
		}
	});
}
