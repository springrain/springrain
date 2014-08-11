<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#assign classNameLowerCase = className?lower_case>
<#assign from = basepackage?last_index_of(".")>
<#assign rootPagefloder = basepackage?substring(basepackage?last_index_of(".")+1)>
<#include "/copyright_js.include" >


jQuery(document).ready(function(){
    //增加全选事件

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

