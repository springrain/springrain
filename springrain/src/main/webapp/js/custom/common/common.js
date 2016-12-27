$(document).ready(function(){
	var currentUrlHash = getLocationHashArr();
	//加载菜单
	ajaxmenu(currentUrlHash);
});

/**
 * 获取所有导航资源
 */
function ajaxmenu(currentUrlHash) {
	jQuery.ajax({
		url : ctx + "/system/menu/leftMenu",
		type : "post",
		cache : false,
		async : false,
		scriptCharset : "utf-8",
		dataType : "json",
		success : function(_json) {
			if(_json.status=="success"){
				buildModule(_json.data,currentUrlHash);
			}
		}
	});
}

function buildModule(data,currentUrlHash) {
	if (data != null && typeof (data) != "undefined") {
		var htmlStr = '';
		var firstPageHashVal = currentUrlHash[0];
		var childrenMenuList = null;
		for ( var i = 0; i < data.length; i++) {
			var url = data[i].pageurl;
			var hashVal = "#"+data[i].id;
			var tmpData = data[i]['leaf'][0];
			while(!!tmpData){
				url = tmpData.pageurl;
				hashVal += "#"+tmpData.id;
				tmpData = tmpData['leaf'][0];
			}
			url = ctx + url + hashVal;
			if((!!firstPageHashVal && data[i].id == firstPageHashVal) || (i==0)){//url中有第一个菜单的键值
				htmlStr += '<li class="layui-nav-item layui-this"><a href="'+url+'">'+data[i].name+'</a></li>';
				childrenMenuList = data[i]['leaf'];
			}else{
				htmlStr += '<li class="layui-nav-item"><a href="'+data[i]['leaf'][0].pageurl+'">'+data[i].name+'</a></li>';
			}
		}
		$("#naviHeaderMenu").html(htmlStr);
		$("#menu").html(getParentModule(childrenMenuList,currentUrlHash));
	}
}


function getParentModule(childrenMenuList,currentUrlHash) {
	var htmlStr = '';
	var secondMenuHashVal = currentUrlHash[1];
	var showItem = "";
	for(var i=0;i<childrenMenuList.length;i++){
		if((!!secondMenuHashVal && childrenMenuList[i].id == secondMenuHashVal) || i==0){
			showItem = "layui-nav-item";
		}
		htmlStr += '<li class="layui-nav-item '+showItem+'" id="'+childrenMenuList[i].id+'"><a href="';
		var _leaf=childrenMenuList[i]["leaf"];
		if(_leaf&&_leaf.length>0){
			htmlStr = htmlStr+ ' javascript:;"> '+childrenMenuList[i].name+'</a>';
			htmlStr = htmlStr+getChindModule(_leaf,currentUrlHash);
		}else{
			htmlStr = htmlStr+'javascript:;" >'+ childrenMenuList[i].name+'</a>';
		}
		htmlStr = htmlStr+'</li>';
	}
	return htmlStr;
}

function getChindModule(_leaf,currentUrlHash) {
	var thirdMenuHashVal = currentUrlHash[2];
	var showItem = "";
	var t = '<dl class="layui-nav-child">';
	for ( var menuObj in _leaf) {
		if((!!thirdMenuHashVal && _leaf[menuObj].id == thirdMenuHashVal) || menuObj==0){
			showItem = "layui-this";
		}
		t = t+'<dd class="'+showItem+'" pageUrl="'+_leaf[menuObj].pageurl+'" id="'+_leaf[menuObj].id+'"><a href="javascript:;"><i class="layui-icon">&#xe611;</i><cite>'+_leaf[menuObj].name+'</cite></a></dd>';
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


