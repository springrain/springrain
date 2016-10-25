jQuery("document").ready(function() {
	//调用ajax从后台获取菜单数据，加载菜单。
	ajaxmenu();
	//根据当前页面url显示内容区
	mzywxhref(urlHandler());
//	myhref(urlHandler());
	//更新当前菜单样式
	flushMenuStyle();
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
			}
		}
	});
}

function buildModule(data) {
	if (data != null && typeof (data) != "undefined") {
	
		for ( var i = 0; i < data.length; i++) {
			var html = '';
			html = getParentModule(data[i]);
			jQuery("#module").append(html);
		}
	}
}

function getParentModule(json) {
	
	var _leaf=json["leaf"];
	
	var t = '<li><a class="dropdown-toggle" ';
	if(_leaf&&_leaf.length>0){
		t =t+ ' href="#'+json["id"]+'">';
	}else{
//		t =t+ ' id= '+json["id"] + ' href="index?id=' +json["id"] + '#href=' + ctx+json["pageurl"] + '">';
		t =t+ ' id= '+json["id"] + ' href="index?id=' +json["id"] + '&t='+gentimestampstr()+'#href=' + ctx+json["pageurl"] + '">';
	}
	var _icon=json["menuIcon"];
	if(_icon==null||_icon==""||_icon.length==0){
		//_icon="icon_default";
		_icon="fa-folder-o";
	}
	t=t+'<i class="menu-icon fa  '+_icon+'"></i><span class="menu-text">'+ json["name"]+'</span>';

	if(_leaf&&_leaf.length>0){
	t=t+'<b class="arrow fa fa-angle-down"></b></a><b class="arrow fa fa-angle-down"></b>';
	var m = "<ul class='submenu'>";
	
	for ( var i = 0; i < _leaf.length; i++) {
		var n = getChindModule(_leaf[i], '');
		m = m+ n;
	}
	t = t + m + "</ul>";

	}else{
		t = t +"</a>";
	}
	t= t+"</li>";
	return t;
}

function getChindModule(json, html) {
	var _leaf=json["leaf"];
	var t = '<li><a  ';
	if(_leaf&&_leaf.length>0){
		t =t+ ' class="dropdown-toggle" href="#">';
	}else{
//		t =t+ '  id=' +json["id"] +' href="index?id=' +json["id"] + '#href=' + ctx+json["pageurl"] + '">';
		t =t+ '  id=' +json["id"] +' href="index?id=' +json["id"] + '&t='+gentimestampstr()+ '#href=' + ctx+json["pageurl"] + '">';
	}
	t=t+'<i class="menu-icon fa fa-caret-right"></i>'+ json["name"];
	
	if (_leaf&& _leaf.length > 0) {
		t=t+'<b class="arrow fa fa-angle-down"></b></a><b class="arrow"></b>';
		var m = "<ul class='submenu'>";
		for ( var i = 0; i < _leaf.length; i++) {
			var n = getChindModule(_leaf[i], '');
			m = m + n;
		}
		m = m + "</ul>";
		t=t+m+"</li>";
	}else{
		t=t+'</a>	<b class="arrow"></b></li>';
		
	}
	html = html + t;
	return html;
}

//
/**
 * url处理方法，返回url中#号后面的url
 * Example:
 * input>localhost:8080/springrain/index?id=idStr#href=hrefStr
 * output>hrefStr
 */

function urlHandler(){
	var currentPageUrl=window.location.href;
	var urlElementArr;
	if(currentPageUrl.indexOf("?")==-1 || (currentPageUrl.indexOf("?")>currentPageUrl.indexOf("#"))){
		urlElementArr="";
	}else{
		urlElementArr=currentPageUrl.substring(currentPageUrl.indexOf("?"));
	}
	if(!!urlElementArr){//非首页
		var paramElementArr=urlElementArr.split("#");
		return paramElementArr[1].substring(paramElementArr[1].indexOf("=")+1);
	}else{//是首页
		var firstMenu=$("#module").children('li').eq(0);
		if(!!firstMenu.html()){//有一级菜单
			var subMenus=firstMenu.contents("ul");
			if(!!subMenus.html()){//含有二级菜单
				var tempSubMenu=subMenus.children("li").eq(0);
				if(!!tempSubMenu){
					var paramElementArr=tempSubMenu.children("a").attr("href").split("#");
					return paramElementArr[1].substring(paramElementArr[1].indexOf("=")+1);
				}else{
					var paramElementArr=firstMenu.children("a").attr("href").split("#");
					return paramElementArr[1].substring(paramElementArr[1].indexOf("=")+1);
				}
			}else{//没有二级菜单
				return firstMenu.children("a").attr("href").split("?")[1].split("#")[1].split("=")[1];
			}
		}else{
			return "";
		}
	}
}



/**
 * 刷新左侧导航样式
 * */
function flushMenuStyle(){
	var currentPageUrl=window.location.href;
	var urlElementArr=currentPageUrl.split("?");
	if(urlElementArr.length>1){//非首页
		var paramElementArr=urlElementArr[1].split("#");
		var menuId= paramElementArr[0].split("=")[1];
		menuId=menuId.replace("&t","");
		$("#"+menuId).parent("li").parent("ul").parent("li").parent("ul").parent("li").addClass("active open");
		$("#"+menuId).parent("li").parent("ul").parent("li").parent("ul").attr("style","display:block;");
		$("#"+menuId).parent("li").parent("ul").parent("li").addClass("active open");
		$("#"+menuId).parent("li").addClass("active");
		//$("#rootMenuTitle").html($("#"+menuId).parent("li").parent("ul").parent("li").children("span.menu-text"));
		$("#rootMenuTitle").html($("#"+menuId).parent("li").parent("ul").parent("li").children("a").eq(0).text());
		$("#menuTitle").html($("#"+menuId).text());
	}else{//首页
		var menu=$("#module").children("li").eq(0);
		if(!!menu.html()){
			menu.addClass("active");
			menu.children("ul").eq(0).children("li").eq(0).addClass("active");
			$("#menuTitle").html(menu.children("ul").eq(0).children("li").eq(0).children('a').text());
			$("#rootMenuTitle").html(menu.children("a").eq(0).text());
		}
	}
	
	
}
