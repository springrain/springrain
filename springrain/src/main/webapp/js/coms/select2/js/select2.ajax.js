function select2ajax(obj,_url,options){
	if(!options){
		options={};
	}
	
	options.placeholder = options.placeholder||"请输入检索关键字";
	options.key = options.key||"id";
	options.value = options.value||"name";
	options.type = options.type||"POST";
	options.multiple = options.multiple||false;
	options.allowClear = options.allowClear||true;
	
	
	$(obj).select2({
		 theme: "bootstrap",
		 multiple:options.multiple,
		 allowClear:options.allowClear,
		 minimumInputLength:1,
		 placeholder: options.placeholder,
		 //ajax查询
		ajax: {
	        url:_url,
	        dataType: "json",
	        type:options.type,
	        
	        data: function (params) {
	            var queryParameters = {
	                q: params.term
	            }
	            return queryParameters;
	        },
	        processResults: function (data) {
	            return {
	                results: $.map(data, function (item) {
	                    return {
	                    	 id: item[options.key],
	                         text: item[options.value]
	                    }
	                })
	            };
	        }
	    }
	  //ajax结束
	
		  
   });
	
	
	
	
}