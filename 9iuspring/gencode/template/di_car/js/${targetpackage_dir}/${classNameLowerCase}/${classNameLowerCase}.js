<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#assign classNameLowerCase = className?lower_case>
<#assign from = basepackage?last_index_of(".")>
<#assign rootPagefloder = basepackage?substring(basepackage?last_index_of(".")+1)>
<#include "/copyright_js.include" >


jQuery(document).ready(function(){
    //增加全选事件
   	jQuery(":checkbox[name='check_all']").checkbox().toggle(":checkbox[name='check_li']");
	//validateRules('saveForm');
});

function del${className}(id){
    var url = ctx + "/${classNameLowerCase}/delete?id=" + id;
        myconfirm("确定要删除么?",function(){
		 jQuery.get(url, null, function(data){
            if (data.status == "success") {
                myalert(data.message);
                myreloadpage();
            }
            else {
                myalert(data.message);
            }
          });
		});
       
    
}
function delMulti(){
     var records = jQuery(":checkbox[name='check_li']").checkbox().val();
    if (records.length == "") {
        myalert('未选中任何记录!');
        return;
    }
	var url = ctx + "/${classNameLowerCase}/delMulti";
    myconfirm("记录删除后将不能恢复,确定要删除选中的记录么?",function(){
	 jQuery.get(url, "records=" + records, function(data){
            myalert(data.message);
            myreloadpage();
        });
	});
 
}
