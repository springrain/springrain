function myalert(message,fun){
	 if(fun){
		// bootbox.alert(message,fun);
			layer.alert(message, {icon: 1},fun);
	 }else{
			layer.alert(message, {icon: 1});
	 }
}

function myerror(message,fun){
	layer.alert(message, {icon: 5},fun);
}

function myinfo(message,fun){
	layer.alert(message, {icon: 0},fun);
}

function myquestion(message,fun){
	layer.alert(message, {icon: 3},fun);
}

function mywarning(message,fun){
	layer.alert(message, {icon: 2},fun);
}

function myconfirm(message,fun){
/*	
		 bootbox.confirm(message,function(result){
			 if(result&&fun){
				 fun();
			 }
			
		 });
*/
	
	layer.confirm(message, {
		  btn: ['确定','取消'] //按钮
		}, function(){
			fun();
		}, function(){
			
		});
		 
		 
		 
}

function myprompt(message,fun){
	
	/*
	 bootbox.prompt(message,function(result){
		 if(fun){
			 fun(result);
		 }
		
	 });
	 */
	
	layer.prompt({
		  title: message,
		  formType: 1 //prompt风格，支持0-2
		}, function(pass){
			fun(pass);
		});
	 
	
}















