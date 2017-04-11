//#error		错误提示标签
//#submitbtn	button提交按钮
//#captchaImg	验证码的id  验证码必须是个div
/**
 * 提交
 */
function submit() {
	var username = $("[name='username']").val().trim();
	var password = $("[name='password']").val().trim();
	var captcha = $("[name='captcha']").val().trim();
	if (username == "" || password == "" || captcha == "") {
		$("#error").show().html("请填写表单信息！")
		return;
	}
	$("#submitbtn").attr("onclick", false).html("正在提交...");
	setTimeout(function() {
		var datas = {
			username : username,
			password : password,
			captcha : captcha,
			usertype : 1,
			systemid : "main",
		}
		$.ajax({
			url : _ctx + "/f/mp/122/" + host + "/" + siteId + "/login/submit",
			data : datas,
			async : false,
			type : "post",
			success : function(data) {
				$("#submitbtn").attr("onclick", "submit()").html("确定");
				if (data.status == "success") {
					$("#error").hide();
					if (yewuId == "car") {
						postCar();
						return;
					} else if (yewuId == "driver") {
						postDriver();
						return;
					}
					reload();
				} else {
					$("#error").show().html(data.message);
					reloadValidateCode122();
				}
			}
		})
	}, 100)
}

// 获取绑定机动车信息
function postCar() {
	$.ajax({
		url : _ctx + "/f/mp/122/" + host + "/" + siteId + "/ajax/bangCar",
		type : "post",
		async : false,
		success : function(data) {
			if (data.status == "success") {
				layer.msg(data.message, {
					shade : 0.1,
					area : "250px",
					shadeClose : false,
					time : 2000,
					icon : 1,
					end : function() {
						sessionStorage.setItem("hphm", data.data.hphm);
						sessionStorage.setItem("hpzl", data.data.hpzl);
						location.href = _ctx + "/f/mp/122/" + host + "/"
								+ siteId + "/vehssurisQuery?hphm="
								+ data.data.hphm + "&hpzl=" + data.data.hpzl;
					}
				});
			} else {
				layer.msg(data.message, {
					shade : 0.1,
					area : "300px",
					shadeClose : false,
					time : 2000,
					end : function() {
						location.href = _ctx + "/f/mp/122/" + host + "/"
								+ siteId + "/index"
					}
				});
			}
		}
	})
}

// 获取绑定驾驶证信息
function postDriver() {
	$.ajax({
		url : _ctx + "/f/mp/122/" + host + "/" + siteId + "/ajax/bangDriver",
		type : "post",
		async : false,
		success : function(data) {
			if (data.status == "success") {
				layer.msg(data.message, {
					shade : 0.1,
					area : "250px",
					shadeClose : false,
					time : 2000,
					icon : 1,
					end : function() {
						location.href = _ctx + "/f/mp/122/" + host + "/"
								+ siteId + "/drvvioQuery";
					}
				});
			} else {
				layer.msg(data.message, {
					shade : 0.1,
					area : "300px",
					shadeClose : false,
					end : function() {
						location.href = _ctx + "/f/mp/122/" + host + "/"
								+ siteId + "/index"
					}
				});
			}

		}
	})
}

/**
 * 刷新验证码
 */
function reloadValidateCode122() {
	$("#captchaImg").css(
			"background-image",
			"url('" + _ctx + "/f/mp/122/" + host + "/" + siteId
					+ "/getCaptcha122?loginCaptcha=true&date="
					+ new Date().getTime() + "')");
}
// 刷新当前
function reload(_href) {
	var _href = window.location.href;
	if (_href.indexOf("?") > -1) {
		window.location.href = _href + "&reload=" + new Date().getTime();
	} else {
		window.location.href = _href + "?reload=" + new Date().getTime();
	}
}