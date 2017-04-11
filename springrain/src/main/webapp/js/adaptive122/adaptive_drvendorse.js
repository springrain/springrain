//#drivergd		驾驶证的查看更多
//geshihua();	用来加载数据后重新规划样式
//.ckgd			公用查看更多
var driver_page = 1;
var driver_size = 10;
function driverRecord() {
	var datas = {
		page : driver_page,
		size : driver_size,
	}
	$.ajax({
		url : _ctx + "/f/mp/122/" + host + "/" + siteId + "/ajax/drvendorse",
		type : "post",
		data : datas,
		async : false,
		success : function(data) {
			if (data.status == "success") {
				var t = "";
				for (var i = 0; i < data.data.content.length; i++) {
					if("0"==data.data.content[i].jkbj){
						data.data.content[i].jkbj="未处理"
					}else if("1"==data.data.content[i].jkbj){
						data.data.content[i].jkbj="已处理"
					}else if("9"==data.data.content[i].jkbj){
						data.data.content[i].jkbj="无需交款"
					}
					t = t
							+ driverstr.replace("{wfsj}",
									data.data.content[i].wfsj).replace(
									"{fkje}", data.data.content[i].fkje)
									.replace("{hphm}",
											data.data.content[i].hphm).replace(
											"{wfjfs}",
											data.data.content[i].wfjfs)
									.replace("{wfms}",
											data.data.content[i].wfms)
									.replace("{wfdz}",
											data.data.content[i].wfdz).replace(
											"{jdsbh}",
											data.data.content[i].jdsbh)
									.replace("{cljgmc}",
											data.data.content[i].cljgmc)
									.replace("{jkbj}",
											data.data.content[i].jkbj);
				}
				if (t == "") {
					t = "<p>无处理的驾驶证扣分记录</p>"
				}
				$("#drivergd").before(t);
				if (data.data.totalPages <= 1
						|| driver_page >= data.data.totalPages) {
					$("#drivergd").hide();
				}
			} else {
				layer.msg(data.message, {
					shade : 0.1,
					area : "300px",
					shadeClose : true
				});
			}
		}
	})
	geshihua();
}
$(function() {
	driverRecord()
	// 查看更多
	$(".ckgd").click(function() {
		driver_page = driver_page + 1;
		driverRecord();
	})
})
