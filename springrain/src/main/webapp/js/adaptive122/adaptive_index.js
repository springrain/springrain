/**
 * 查看用户是否绑定机动车
 */
function bangCar() {
	if (loginsuccess122 != "true") {
		location.href = _ctx + "/f/mp/122/" + host + "/" + siteId
				+ "/login?yewuId=car"
		return;
	}
	var hphm = sessionStorage.getItem("hphm");
	var hpzl = sessionStorage.getItem("hpzl");
	if (hphm != null && hpzl != null) {
		location.href = _ctx + "/f/mp/122/" + host + "/" + siteId
				+ "/vehssurisQuery?hphm=" + hphm + "&hpzl=" + hpzl;
		return;
	}
	$.ajax({
		url : _ctx + "/f/mp/122/" + host + "/" + siteId + "/ajax/bangCar",
		type : "post",
		async : false,
		success : function(data) {
			if (data.status == "success") {
				location.href = _ctx + "/f/mp/122/" + host + "/" + siteId
						+ "/vehssurisQuery?hphm=" + data.data.hphm + "&hpzl="
						+ data.data.hpzl;
			} else {
				layer.msg(data.message, {
					shade : 0.1,
					area : "300px",
					shadeClose : true
				});
			}
		}
	})
}
/**
 * 查看用户是否绑定驾驶证
 */
function bangDriver() {
	if (loginsuccess122 != "true") {
		location.href = _ctx + "/f/mp/122/" + host + "/" + siteId
				+ "/login?yewuId=driver"
		return;
	}
	$.ajax({
		url : _ctx + "/f/mp/122/" + host + "/" + siteId + "/ajax/bangDriver",
		type : "post",
		async : false,
		success : function(data) {
			if (data.status == "success") {
				location.href = _ctx + "/f/mp/122/" + host + "/" + siteId
						+ "/drvvioQuery";
			} else {
				layer.msg(data.message, {
					shade : 0.1,
					area : "300px",
					time : 2000,
					shadeClose : false
				});
				// location.href=_ctx+"/f/mp/122/"+host+"/"+siteId+"/drvvioQuery";
			}

		}
	})
}