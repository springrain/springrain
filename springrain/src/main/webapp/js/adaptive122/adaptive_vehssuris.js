//#dzjkgd		电子监控查看更多
//#wfclgd		违法记录查看更多
//#wfclUl		违法记录ul容器
//geshihua();	用来加载数据后重新规划样式
//.ckgd			公用查看更多
//获取电子监控记录
var dzjk_page = 1;
var dzjk_size = 10;
function dzjkRecord() {
	var datas = {
		page : dzjk_page,
		size : dzjk_size,
		recordType : "dzjk"
	}
	$.ajax({
		url : _ctx + "/f/mp/122/" + host + "/" + siteId + "/ajax/vehssuris?"
				+ query,
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
							+ dzjkstr.replace("{wfsj}",
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
											"{clbjStr}",
											data.data.content[i].clbjStr)
									.replace("{cjjgmc}",
											data.data.content[i].cjjgmc)
									.replace("{jkbj}",
											data.data.content[i].jkbj);
				}
				if (t == "") {
					t = "<p>无处理的电子监控记录</p>"
				}
				$("#dzjkgd").before(t);
				if (data.data.totalPages <= 1
						|| dzjk_page >= data.data.totalPages) {
					$("#dzjkgd").hide();
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

// 违法处理记录
var wfcl_page = 1;
var wfcl_size = 10;
function wfclRecord() {
	var datas = {
		page : wfcl_page,
		size : wfcl_size,
		recordType : "wfcl"
	}
	$.ajax({
		url : _ctx + "/f/mp/122/" + host + "/" + siteId + "/ajax/vehssuris?"
				+ query,
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
							+ wfclstr.replace("{wfsj}",
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
					t = "<p>无处理的违法处理记录</p>"
				}
				$("#wfclgd").before(t);
				if (data.data.totalPages <= 1
						|| wfcl_page >= data.data.totalPages) {
					$("#wfclgd").hide();
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
	dzjkRecord();
	wfclRecord()
	$("#wfclUl").hide();
	// 查看更多
	$(".ckgd").click(function() {
		if ($(this).attr("id") == "dzjkgd") {
			dzjk_page = dzjk_page + 1;
			dzjkRecord();
		} else if ($(this).attr("id") == "wfclgd") {
			wfcl_page = wfcl_page + 1;
			wfclRecord();
		}
	})
})
