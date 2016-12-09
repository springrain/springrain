$(document).ready(function(){
	//加载部门列表
	loadOrgList();
});

function loadOrgList(){
	$.ajax({
		url: ctx+'/system/org/list/json',
		type: 'POST',
		dataType: 'json',
		success:function(ret){
			if(ret.status=="success"){
				
			}
		}
	});
	var orgId = '123';
	loadUserList(orgId);
}

function loadUserList(orgId){
	console.log("加载部门列表");
}