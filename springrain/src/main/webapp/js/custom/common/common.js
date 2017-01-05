$(document).ready(function(){
	
	//初始化插件
	configLayui("global");
	//加载菜单
	loadMenu();
	init_sort_btn();
});

function loadMenu(){
	//加载菜单
    if(!(!!locache.get("menuData"))){//没有数据
    	ajaxmenu();
    	var tmpData = locache.get("menuData");
    	setCacheUrlPath("",tmpData);
    	setCacheLeftMenu(tmpData);
    }
    var menuData = locache.get("menuData");
    buildModule(menuData);
}




function exit(){
	myconfirm("确定退出？", function(){
		try{
			locache.flush();
		}catch(e){}
		window.location = ctx+"/logout";
	});
	
}

function configLayui(par){
	layui.config({
		  base: ctx+"/layui/lay/modules/"
		}).use(par);
}

function setCacheUrlPath(pid,tmpData){	
	for ( var index in tmpData) {
		if(!(!!pid) || !(!!tmpData[index].pid)){
			pid = tmpData[index].id;
		}
		var cacheVal = pid;
		var cacheKey = ctx+tmpData[index]['pageurl']; 
		var tmpLeaf = tmpData[index]['leaf'];
		if(!!tmpLeaf){
			setCacheUrlPath(pid,tmpLeaf);
		}
		if(!!cacheKey){
			locache.set(cacheKey,cacheVal);
		}
	}
}

function setCacheLeftMenu(tmpData){
	for ( var index in tmpData) {
		locache.set(tmpData[index].id,tmpData[index]['leaf']);
	}
}

/**
 * 获取所有导航资源
 */
function ajaxmenu() {
    jQuery.ajax({
        url : ctx + "/system/menu/leftMenu",
        type : "post",
        cache : false,
        async : false,
        scriptCharset : "utf-8",
        dataType : "json",
        success : function(_json) {
            if(_json.status=="success"){
            	locache.set("menuData",_json.data);
            }
        }
    });
}

function buildModule(data) {
    if (data != null && typeof (data) != "undefined") {
        var htmlStr = '';
        var naviMenuId = locache.get(window.location.pathname);
        var childrenMenuList = null;
        for ( var i = 0; i < data.length; i++) {
            var url = data[i].pageurl;
            var tmpData = data[i]['leaf'][0];
            while(!!tmpData){
                url = tmpData.pageurl;
                tmpData = tmpData['leaf'][0];
            }
            url = ctx + url;
            if((data[i].id == naviMenuId) || (!(!!naviMenuId) && i==0)){//url中有第一个菜单的键值
                htmlStr += '<li id="pmenu'+data[i].id+'" class="layui-nav-item layui-this"><a href="'+url+'">'+data[i].name+'</a></li>';
                childrenMenuList = data[i]['leaf'];
            }else{
                htmlStr += '<li id="pmenu'+data[i].id+'" class="layui-nav-item"><a href="'+url+'">'+data[i].name+'</a></li>';
            }
        }
        $("#naviHeaderMenu").html(htmlStr);
        $("#menu").html(getParentModule(childrenMenuList));
    }
}


function getParentModule(childrenMenuList) {
    var htmlStr = '';
   
    for(var i=0;i<childrenMenuList.length;i++){
    	 var showItem = "";
        if((ctx+childrenMenuList[i].pageurl) == window.location.pathname){
            showItem = "layui-this";
        }
       
        var _leaf=childrenMenuList[i]["leaf"];
        if(_leaf&&_leaf.length>0){
        	 htmlStr += '<li class="layui-nav-item '+showItem+'" id="'+childrenMenuList[i].id+'"><a href="';
            htmlStr = htmlStr+ ' javascript:;"> <i class="layui-icon">'+childrenMenuList[i].menuIcon+'</i><cute>'+childrenMenuList[i].name+'</cute></a>';
            htmlStr = htmlStr+getChindModule(_leaf);
        }else{
        	htmlStr += '<li class="layui-nav-item '+showItem+'" id="'+childrenMenuList[i].id+'"><a href="';
        	 var url = childrenMenuList[i].pageurl;
             var tmpData = childrenMenuList[i]['leaf'][0];
             while(!!tmpData){
                 url = tmpData.pageurl;
                 tmpData = tmpData['leaf'][0];
             }
             url = ctx+url;
            htmlStr = htmlStr+url+'" ><i class="layui-icon">'+childrenMenuList[i].menuIcon+'</i><cute>'+childrenMenuList[i].name+'</cute></a>';
        }
        htmlStr = htmlStr+'</li>';
    }
    return htmlStr;
}

function getChindModule(_leaf) {
    var showItem = "";
    var t = '<dl class="layui-nav-child">';
    for ( var menuObj in _leaf) {
        if((ctx+_leaf[menuObj].pageurl)==window.location.pathname){
            showItem = "layui-this";
        }
        t = t+'<dd class="'+showItem+'" pageUrl="'+_leaf[menuObj].pageurl+'" id="'+_leaf[menuObj].id+'"><a href="javascript:;"><i class="layui-icon">'+_leaf[menuObj].menuIcon+'</i><cite>'+_leaf[menuObj].name+'</cite></a></dd>';
    }
    t = t+'</dl>';
    return t;
}


/**
 * 获取url中的hash值，例如http://www.baidu.com#keyword,返回['keyword']
 * @returns 数组
 */
function getLocationHashArr(){
    var urlHash = window.location.hash;
    if(!!urlHash){
        var urlHashArr = urlHash.split("#");
        urlHashArr.splice(0,1);
        return urlHashArr;
    }
    return new Array();
}


/*
内容区页面跳转
*/
/*function myhref(_url,menuId) {
	mySubmitForm("springrain_default_init_ajax_form", _url);
}*/

function mzywxhref(_url,menuId) {
	layer.load();
	mySubmitForm("springrain_default_init_ajax_form", _url);
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
	/*var t=gentimestampstr();
	if(menuId==null){
		menuId=getcurrentMenuId();
		if(menuId==''){
			menuId=t;
		}
	}
	var urlnew="index?id="+menuId+"&t="+t+"#href="+_url; */
	location.href=_url;
}

/**
 * 不告诉用户删除结果，直接刷新页面
 * 
 * @param _url
 */
function mydelete(_url) {
	mydelete(_url,"确定删除数据?");
}

/**
 * 不告诉用户删除结果，直接刷新页面
 * 
 * @param _url
 */
function mydelete(_url,message) {
	myconfirm(message, function() {
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
	mydelete(_url, listage,par,"确定删除数据?");
}

/**
 * 列表页面删除，弹框提示用户删除结果，再刷新指定页面,一般为列表
 * 
 * @param _url
 * @param listage
 */
function mydelete(_url, listage,par,message) {
	myconfirm(message, function() {
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
function commonUpdateForm(formId,listurl,message) {
	if(!formId){
		formId="updateForm";
	}
	
	
	var _validate=jQuery("#"+formId).Validform({
		tiptype:3
	});
	if(!_validate.check(false)){
		return false;
	}
	
	
	
	 var pageurl=$("#"+formId).attr('action'); 
		var mydata=$("#"+formId).serialize();
		ajaxpostonlayer(pageurl,listurl,mydata,message);
	
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
function commonSaveForm(form,listurl,message,_id) {
	if(!form){
		form="updateForm";
	}
	var id="#id";
	if(_id){
		id="#"+_id;
	}
	
	jQuery(id,jQuery("#"+form)).val("");
	

	var _validate=jQuery("#"+form).Validform({
		tiptype:3
	});
	if(!_validate.check(false)){
		return false;
	}
	
	
	
	
	 var pageurl=$("#"+form).attr('action'); 
		var mydata=$("#"+form).serialize();
		ajaxpostonlayer(pageurl,listurl,mydata,message);
	
	
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
function myhref2page(_url,listurl,par) {
	if(!par){
		par=null;
	}
	
	
	ajaxpostonlayer(_url,listurl,par);
	
	
	/*
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
	*/
	
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
function  init_sort_btn(){
	jQuery(".sort-icon").bind("mouseenter",function(){
		jQuery(this).addClass("sort-icon-on");
	}).bind("mouseout",function(){
		jQuery(this).removeClass("sort-icon-on");
	});
	//单击事件
	jQuery(".sort-icon").bind("click",function(){
		if(jQuery(this).hasClass("sort-icon-down")){
			jQuery("#page_sort").val("desc");
			jQuery("#page_order").val(jQuery(this).parent("th").attr("id").split("_")[1]);
		}else{
			jQuery("#page_sort").val("asc");
			jQuery("#page_order").val(jQuery(this).parent("th").attr("id").split("_")[1]);
		}
		//提交表单
		$("#searchForm").submit();
	});
}





