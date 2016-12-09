var firstPage;
var element;
$(document).ready(function(){
	//删除缓存
	locache.remove('tabPage');
	//加载element模块
	ajaxmenu();
	//刷新页面加载
	loadCachePage(firstPage);
	watchEvent();
});

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
				buildModule(_json.data);
				loadLayUiElement();
			}
		}
	});
}

function buildModule(data) {
	if (data != null && typeof (data) != "undefined") {
		for ( var i = 0; i < data.length; i++) {
			var html = '';
			html = getParentModule(data[i]);
			$("#menu").append(html);
		}
	}
}

function getParentModule(json) {
	var _leaf=json["leaf"];
	var t = '<li class="layui-nav-item layui-nav-itemed" id="'+json.id+'"><a href="';
	if(_leaf&&_leaf.length>0){
		t =t+ ' javascript:;"> '+json.name+'</a>';
		t = t+getChindModule(_leaf);
	}else{
		//t = t+ctx+'/index#'+json.id+'" >'+ json.name+'</a>';
		t = t+'javascript:;" >'+ json.name+'</a>';
		if(!(!!firstPage)){
			firstPage = json.id;
		}
	}
	t =t+'</li>';
	return t;
}

function getChindModule(_leaf) {
	var t = '<dl class="layui-nav-child">';
	for ( var menuObj in _leaf) {
		//t = t+'<dd pageUrl="'+_leaf[menuObj].pageurl+'" id="'+_leaf[menuObj].id+'"><a href="'+ctx+'/index?lang=zh_CN#'+_leaf[menuObj].id+'"><i class="layui-icon">&#xe611;</i><cite>'+_leaf[menuObj].name+'</cite></a></dd>';
		t = t+'<dd pageUrl="'+_leaf[menuObj].pageurl+'" id="'+_leaf[menuObj].id+'"><a href="javascript:;"><i class="layui-icon">&#xe611;</i><cite>'+_leaf[menuObj].name+'</cite></a></dd>';
		if(!(!!firstPage)){
			firstPage = _leaf[menuObj].id;
		}
	}
	t = t+'</dl>';
	return t;
}

function loadLayUiElement(){
	layui.use('element', function(){
		element = layui.element();
		element.on('nav(leftMenu)', function(data){
			addTabPage(data);
		});
		
		element.on('tab(tabPage)', function(data){
		});
	});
}


function addTabPage(data){
	var menuName = data.children("a").children("cite").html();
	var menuId = data.attr("id");
	var pageUrl = data.attr("pageUrl");
	var tabMenuIdObj = locache.get('tabPage');
	if(!(!!tabMenuIdObj)){
		tabMenuIdObj = new Object();
	}
	
	$("#tabPage").children("li").removeClass("layui-this");
	$("#tabPageContent").children("div").removeClass("layui-show");
	if(!!tabMenuIdObj[menuId]){
		if(!(!!$("#tab"+menuId)) || $("#tab"+menuId).length<=0){
			$("#tabPage").append('<li class="layui-this" id="tab'+menuId+'">'+menuName+' </li>');
		}
		if(!(!!$("#content"+menuId)) || $("#content"+menuId).length<=0){
			$("#tabPageContent").append('<div class="layui-tab-item layui-show" id="content'+menuId+'"></div>');
		}
		$("#tab"+menuId).addClass("layui-this");
		$("#content"+menuId).addClass("layui-show");
		
		loadPageContent(menuId,pageUrl);
	}else{
		$("#tabPage").append('<li class="layui-this" id="tab'+menuId+'">'+menuName+' </li>');
		$("#tabPageContent").append('<div class="layui-tab-item layui-show" id="content'+menuId+'"></div>');
		tabMenuIdObj[menuId] = new Object();
		tabMenuIdObj[menuId].id=menuId;
		tabMenuIdObj[menuId].name=menuName;
		tabMenuIdObj[menuId].url = pageUrl;
		locache.set('tabPage',tabMenuIdObj);
		loadPageContent(menuId,pageUrl);
	}
	
}

function loadCachePage(){
	var tabMenuIdObj = locache.get('tabPage');
	if(!!tabMenuIdObj){//加载当前url中的页面
		var currentPageId = getCurrentPageId(); 
		for(var field in tabMenuIdObj){
			if(currentPageId == tabMenuIdObj[field].id){
				$("#tabPage").append('<li class="layui-this" id="tab'+tabMenuIdObj[field].id+'">'+tabMenuIdObj[field].name+' </li>');
				$("#tabPageContent").append('<div class="layui-tab-item layui-show"></div>');
				loadPageContent(tabMenuIdObj[field].id,tabMenuIdObj[field].url);
			}else{
				$("#tabPage").append('<li id="tab'+tabMenuIdObj[field].id+'">'+tabMenuIdObj[field].name+' </li>');
				$("#tabPageContent").append('<div class="layui-tab-item"></div>');
			}
		}
	}else{//加载菜单中的第一个页面
		var pageUrl = $("#"+firstPage).attr("pageurl");
		var pageName = $("#"+firstPage).children("a").children("cite").text();
		$("#tabPage").append('<li class="layui-this" id="tab'+firstPage+'">'+pageName+' </li>');
		$("#tabPageContent").append('<div class="layui-tab-item layui-show" id="content'+firstPage+'"></div>');
		loadPageContent(firstPage,pageUrl);
		tabMenuIdObj = new Object();
		tabMenuIdObj[firstPage] = new Object();
		tabMenuIdObj[firstPage].id=firstPage;
		tabMenuIdObj[firstPage].name=pageName;
		tabMenuIdObj[firstPage].url = pageUrl;
		locache.set('tabPage',tabMenuIdObj);
	}
}

function loadPageContent(pageId,pageUrl){
	$.ajax({
		url: ctx+pageUrl,
		type: 'POST',
		dataType: 'html',
		success:function(ret){
			$("#content"+pageId).html(ret);
			element.init();
		}
	});
}

/*该方法监听浏览器url状态变更，暂时不实现 */
function watchEvent(){
    if (window.history && window.history.pushState) {
         $(window).on('popstate', function () {
        	//TODO
        	//
            console.log("url状态更改");
        });
    }
}

