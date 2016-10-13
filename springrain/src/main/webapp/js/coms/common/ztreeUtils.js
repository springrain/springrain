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