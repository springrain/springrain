$(document).ready(function(){
	
	//初始化插件
	configLayui("global");
	//加载菜单
	loadMenu();
	init_sort_btn();
	init_button_action();
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
        /*处理/update时丢了菜单 状态*/
        var _url=window.location.pathname;
        if(_url.indexOf('/update/pre')!=-1){
        	_url=_url.substring(0,_url.indexOf("/update/pre"))+"/list";
        } 
        /*处理/update时丢了菜单 状态*/
        var naviMenuId = locache.get(_url);
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
    var _url=window.location.pathname;
    if(_url.indexOf('/update/pre')!=-1){
    	_url=_url.substring(0,_url.indexOf("/update/pre"))+"/list";
    } 
    for(var i=0;i<childrenMenuList.length;i++){
    	 var showItem = "";
        if((ctx+childrenMenuList[i].pageurl) ==_url){
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
/*
 * 跳转
 * */
function myhref(_url) {
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
	jQuery("#"+formId).submit();
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
		//按老的UI不动是这个
		//$("#" + name).val(val);
		//按新的layerui只能，模拟点击
		jQuery("#"+name).siblings("div").filter(".layui-form-select").eq(0).find("dd[lay-value='"+val+"']").trigger("click");
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
	//加载颜色
	var _sort=jQuery("#page_sort").val();
	var _order=jQuery("#page_order").val();
	if(_order){
		if("asc"==_sort){
			jQuery("#th_"+_order).find(".sort-icon-up").css("color","#333333");
		}else{
			jQuery("#th_"+_order).find(".sort-icon-down").css("color","#333333");
		}
	}
	
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
function init_button_action(){
	jQuery("button[data-action]").bind("click",function(){window.location.href=jQuery(this).attr("data-action")});
}
/*初始化表单验证，只需要在修改页面调用 即可*/
function init_valid(_before,_after){
	var index = null;
	$("#validForm").Validform({
		btnSubmit:"#smtbtn", 
		btnReset:"#rstbtn",
		tiptype:4, 
		tiptype:function(msg,o,cssctl){
			var _obj=jQuery(o.obj).parents(".layui-form-item").find(".valid-info");
			cssctl(_obj,o.type);
			_obj.text(msg);
		},
		ignoreHidden:false,
		dragonfly:false,
		tipSweep:false,
		label:".label",
		showAllError:true,
		postonce:true,
		ajaxPost:true,
		datatype:{
			"*6-20": /^[^\s]{6,20}$/,
			"z2-4" : /^[\u4E00-\u9FA5\uf900-\ufa2d]{2,4}$/,
			"username":function(gets,obj,curform,regxp){
				//参数gets是获取到的表单元素值，obj为当前表单元素，curform为当前验证的表单，regxp为内置的一些正则表达式的引用;
				var reg1=/^[\w\.]{4,16}$/,
					reg2=/^[\u4E00-\u9FA5\uf900-\ufa2d]{2,8}$/;
	 
				if(reg1.test(gets)){return true;}
				if(reg2.test(gets)){return true;}
				return false;
	 
				//注意return可以返回true 或 false 或 字符串文字，true表示验证通过，返回字符串表示验证失败，字符串作为错误提示显示，返回false则用errmsg或默认的错误提示;
			},
			"phone":function(){
				// 5.0 版本之后，要实现二选一的验证效果，datatype 的名称 不 需要以 "option_" 开头;	
			}
		},
		usePlugin:{
			swfupload:{},
			datepicker:{},
			passwordstrength:{},
			jqtransform:{
				selector:"select,input"
			}
		},
		beforeCheck:function(curform){
			 
			//在表单提交执行验证之前执行的函数，curform参数是当前表单对象。
			//这里明确return false的话将不会继续执行验证操作;	
		},
		beforeSubmit:function(curform){
			layer.load();
			if(_before!=null &&typeof(_before)=="function"){
				_before();
			}
			//在验证成功后，表单提交前执行的函数，curform参数是当前表单对象。
			//这里明确return false的话表单将不会提交;	
		},
		callback:function(data){
			if(_after!=null &&typeof(_after)=="function"){
				_after();
			}
			layer.close(index);
			//返回数据data是json对象，{"info":"demo info","status":"y"}
			//info: 输出提示信息;
			//status: 返回提交数据的状态,是否提交成功。如可以用"y"表示提交成功，"n"表示提交失败，在ajax_post.php文件返回数据里自定字符，主要用在callback函数里根据该值执行相应的回调操作;
			//你也可以在ajax_post.php文件返回更多信息在这里获取，进行相应操作；
			//ajax遇到服务端错误时也会执行回调，这时的data是{ status:**, statusText:**, readyState:**, responseText:** }；
	 
			//这里执行回调操作;
			//注意：如果不是ajax方式提交表单，传入callback，这时data参数是当前表单对象，回调函数会在表单验证全部通过后执行，然后判断是否提交表单，如果callback里明确return false，则表单不会提交，如果return true或没有return，则会提交表单。
		}
	});
}
function delWrap(_id,_url,_tips){
	var _pars={"id":_id};
	_tips=_tips?_tips:'是否删除?';
	layer.confirm(_tips, {icon: 3, title:'提示'}, function(index){
		  jQuery.ajax({
			  url:_url,
			  type:"post",
			  data:_pars,
			  dataType:"json",
			  async:false,
			  success:function(data){
				  if(data!=null&&"success"==data.status){
					  layer.msg(data.message, {
						  icon: 1,
						  time: 2000 //2秒关闭（如果不配置，默认是3秒）
						}, function(){
						   window.location.reload();
						}); 
				  }else{
					  layer.msg(data.message, {icon: 1,time: 1000}); 
				  }
			  }
		  });
		  layer.close(index);
	});
}


