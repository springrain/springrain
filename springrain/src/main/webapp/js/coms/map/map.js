jQuery("document").ready(function() {
	// jQuery("#allmap").hide();
	
});

var map;
function buildMap() {
	map = new BMap.Map("allmap");
	var point = new BMap.Point(113.66028, 34.760999);
	map.centerAndZoom(point, 13);
	map.addControl(new BMap.NavigationControl()); // 添加平移缩放控件
	// map.addControl(new BMap.ScaleControl()); // 添加比例尺控件
	map.addControl(new BMap.OverviewMapControl()); // 添加缩略地图控件
	map.enableScrollWheelZoom(); // 启用滚轮放大缩小
	map.addControl(new BMap.MapTypeControl());
	getPoint(map);
	// 添加地图类型控件
	function myFun(result) {
		var cityName = result.name;
		map.setCenter(cityName);
		// alert(cityName);
	}
	var myCity = new BMap.LocalCity();
	myCity.get(myFun);

}
function search() {
	var local = new BMap.LocalSearch(map, {
		renderOptions : {
			map : map
		}
	});
	var address = jQuery("#seachrAddress").val();
	if (address != "" && address != null) {
		local.search(address);
	}
}

function showMap(_ctr) {
	//jQuery("#mapdialog").find(".mydialog-okBtn").hide();
	//jQuery("#mapdialog").find(".mydialog-cancelBtn").hide();
	//myshow("#mapdialog", null, _ctr);
	buildMap();
	jQuery("#allmap").show();

}

function getPoint(t) {
	t.addEventListener("click", function(e) {
		jQuery("#jingdu").val(e.point.lng);
		jQuery("#weidu").val(e.point.lat);
		// document.getElementById("r-result").innerHTML = + ", " + e.point.lat;
	});
}