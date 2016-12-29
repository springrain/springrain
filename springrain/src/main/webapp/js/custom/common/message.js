function myalert(message, fun) {
	if (fun) {
		layer.alert(message, {
			icon : 1
		}, fun);
	} else {
		layer.alert(message, {
			icon : 1
		});
	}
}

function myerror(message, fun) {
	layer.alert(message, {
		icon : 5
	}, fun);
}

function myinfo(message, fun) {
	layer.alert(message, {
		icon : 0
	}, fun);
}


function mywarning(message, fun) {
	layer.alert(message, {
		icon : 2
	}, fun);
}

function myconfirm(message, fun) {
	layer.confirm(message, {
		title:"确认",
		icon : 3,
		btn : [ '确定', '取消' ]
	// 按钮
	}, function() {
		fun();
	}, function() {

	});

}

function myprompt(message, fun) {
	layer.prompt({
		title : message,
		formType : 1
	// prompt风格，支持0-2
	}, function(pass) {
		fun(pass);
	});

}
