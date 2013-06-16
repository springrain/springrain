var easyui_tabs_id = "#easyui_center_tabs";


jQuery(document).ready(function() {
	

});

function closeActiveTab(){
	//关闭当前标签页
		var currTitle=jQuery(easyui_tabs_id).tabs("getSelected").panel('options').title;
		jQuery(easyui_tabs_id).tabs('close',currTitle);
	
}
	

function addnode(nodes, _ul) {
	jQuery(nodes).each(function(i, _node) {

		var childrens = _node["children"];
		if (childrens && childrens.length > 0) {
			var childrenul = jQuery("<ul class='easyui-tree'></ul>");
			jQuery(_ul).append(_ul);
			addnode(_node["children"], childrenul);
		}else{
			jQuery(_ul).append("<li><span>"+_node["name"]+"</span></li>");
			
		}
	});

}

function onClick(event, treeId, treeNode, clickFlag) {
	menuTreeEvent(treeNode);
}

/**
 * 判断tab是否已经打开
 * 
 * @param node
 * @return
 */
function menuTreeEvent(treeNode) {

	var easyui_tabs = jQuery(easyui_tabs_id);

	var nodeId = treeNode["id"];
	var nodeName = treeNode["name"];

	var title = jQuery.trim(nodeName);

	if (easyui_tabs.tabs('exists', title)) {
		easyui_tabs.tabs('select', title);
		return;
	}

	var nodeHref = treeNode["moduleurl"];

	if (null == nodeHref || "undefined" == nodeHref || nodeHref.length < 1) {
		// alert("未找到节点连接!");
		return false;
	}
	var iframeId="_iframe_"+nodeId;
	newTab( nodeId,iframeId,title, nodeHref);

	// return false;
}

/**
 * 打开新标签
 * 
 * @return
 */
function newTab(tabId,iframeId,tabTitle,tabHref){

	var content = '<iframe id="'+iframeId+'" class="tabIframe" frameborder="0" src="'
		+ tabHref
		+ '" style="width:100%;height:100%;"></iframe>';
		
	var systemTabs = jQuery('#easyui_center_tabs'); 
	var title = jQuery.trim(tabTitle);
	if (systemTabs.tabs('exists', title)) {
		systemTabs.tabs('select', title);
		return;
	}		
		
	jQuery('#easyui_center_tabs').tabs('add', {
			id : "tabs_" + tabId,
			title : tabTitle,
			content : content,
			closable : true
		});	

	//	jQuery('#easyui_center_tabs').tabs('close',{forceClose:true});
		
	
};




