$(document).ready(function(){
	
	//初始化插件
	configLayui("global");
	//加载菜单
	loadMenu();
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
            htmlStr = htmlStr+ ' javascript:;"> '+childrenMenuList[i].name+'</a>';
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
            htmlStr = htmlStr+url+'" >'+ childrenMenuList[i].name+'</a>';
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


