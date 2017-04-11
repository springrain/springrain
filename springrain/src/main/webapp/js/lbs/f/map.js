var winWidth = 0;
var winHeight = 0;
var map=null;
var infoWin=null;
function findDimensions(){ 
	//函数：获取尺寸
	//获取窗口宽度
	if (window.innerWidth)
		winWidth = window.innerWidth;
	else if ((document.body) && (document.body.clientWidth))
		winWidth = document.body.clientWidth;
			//获取窗口高度
	if (window.innerHeight)
		winHeight = window.innerHeight;
	else if ((document.body) && (document.body.clientHeight))
		winHeight = document.body.clientHeight;
		//通过深入Document内部对body进行检测，获取窗口大小
	if (document.documentElement  && document.documentElement.clientHeight && document.documentElement.clientWidth){
		winHeight = document.documentElement.clientHeight;
		winWidth = document.documentElement.clientWidth;
	}
		 
}
function init(list) {
	try{
		//适应手机屏幕，地图画到同样大的div中
		findDimensions();
		jQuery("#container").css('height',winHeight+"px");
	}catch(e){
		
	}
	var MarkerCluster = qq.maps.MarkerCluster;
	var MVCArray = qq.maps.MVCArray;
	var Marker = qq.maps.Marker;
	var Event = qq.maps.event;
	//中心点坐标，视项目所在地调整，以最后一个坐标点为中心
	var center = new qq.maps.LatLng(list[list.length-1].lat,list[list.length-1].lng);
    map = new qq.maps.Map(document.getElementById("container"), {
        center: center,
        zoom: 13,
        zoomControl: true,
        scaleControl: true,
        zoomControlOptions: {
            //设置缩放控件的位置为相对左方中间位置对齐.
            position: qq.maps.ControlPosition.TOP_LEFT,
            //设置缩放控件样式为仅包含放大缩小两个按钮
            style: qq.maps.ZoomControlStyle.DEFAULT
        },
        panControl:true,
        panControlOptions: {
            //设置平移控件的位置为相对右方中间位置对齐.
            position: qq.maps.ControlPosition.TOP_LEFT
 
        },
        mapTypeControlOptions: {
            //设置控件的地图类型ID，ROADMAP显示普通街道地图，SATELLITE显示卫星图像，HYBRID显示卫星图像上的主要街道透明层
            mapTypeIds: [
                qq.maps.MapTypeId.ROADMAP,
                qq.maps.MapTypeId.SATELLITE,
                qq.maps.MapTypeId.HYBRID
            ],
            //设置控件位置相对上方中间位置对齐
        }
        
    });
    var zoomLevelControl = new qq.maps.Control(
    	    {
    	        content: '<a onclick="dingwei()"><img src='+_ctx+'/u/'+_siteId+'/f/img/y_ico.png/></a>' ,
    	        align: qq.maps.ALIGN.BOTTOM_RIGHT,
    	        map: map
    	    }
    	);
    infoWin = new qq.maps.InfoWindow({
        map: map
    });
    
    var markers = new MVCArray();
    if(list!=null&&list!=""&&list!=undefined){
    	for(var i = 0;i < list.length; i++) {
            (function(i){
                	marker = new Marker({
                    position: new qq.maps.LatLng(list[i].lat,list[i].lng),
                    map: map
                });
                qq.maps.event.addListener(marker, 'click', function() {
                	map.setCenter(new qq.maps.LatLng(list[i].lat,list[i].lng));
                	infoWin.close();
                    infoWin.open();
                    infoWin.setContent('<div ><p>地址:' +
                            list[i].poiaddress+ '</p><p> 电话:'+list[i].tel+'</p></div>'+'<div style="text-align:right;"><input type="button" value="去这里" style="width:45px;background:#46a3ff;color:#ffffff;border:1px;" onclick=daohang(\''+list[i].poiaddress+'\',\''+list[i].lat+'\',\''+list[i].lng+'\')></div>');
                    infoWin.setPosition(new qq.maps.LatLng(list[i].lat,list[i].lng));
                });
            })(i);
            markers.push(marker);
        } 
    }else{
    	alert("暂未提供相关位置");
    }
     
    //聚合点
    markerClusterer = new MarkerCluster({
        map: map,
        minimumClusterSize: 2, //默认2
        markers: markers,
        zoomOnClick: true, //默认为true
        gridSize: 30, //默认60
        averageCenter: true, //默认false
        maxZoom: 18, //默认18
    });

    Event.addListener(markerClusterer, 'clusterclick', function(evt) {
        // writeLog("mouse event", eventType);
        var ss = evt;
        // alert('点击了聚合点');
    });
    
}
//默认当前位置为导航起点
//eword:终点的名称必传参数
//epointx:终点的经度，可以传null
//epointy:终点的维度，可以为null
//referer:调用来源，一般为您的应用名称，必传，可以随便传值
//key:申请的腾讯地图秘钥,必传
function daohang(eword,epointx,epointy){
		window.location.href="http://apis.map.qq.com/tools/routeplan/eword="+eword+"&epointx="+epointx+"&epointy="+epointy+"?referer=myapp&key=OB4BZ-D4W3U-B7VVO-4PJWW-6TKDJ-WPB77";
		
	}
/**
 * 自定义定位控件
 */
function dingwei(){
	var geolocation = new qq.maps.Geolocation("NS3BZ-CAOW3-CWI3L-YC332-JN7M5-RVBUH", "myapp");
	geolocation.getLocation(showPosition, showErr)
}
var markerdingwei=null;
/**
 * 定位成功将地图的中心点变为当前自己的位置
 */

function showPosition(position){
	//position是定位完成后返回的数据
	var _lat=position.lat;
	var _lng=position.lng;
	
	if(markerdingwei!=null){
		infoWin.close();
		markerdingwei.setPosition(new qq.maps.LatLng(_lat,_lng));
		markerdingwei.setMap(map);
	}else{
		infoWin.close();
		markerdingwei = new qq.maps.Marker({
	        position: new qq.maps.LatLng(_lat,_lng),
	        map: map
	    });
	}
	map.panTo(new qq.maps.LatLng(_lat,_lng));
	//设置标注的动画为从天而降
	markerdingwei.setAnimation(qq.maps.MarkerAnimation.DOWN);
	markerdingwei.setIcon(new qq.maps.MarkerImage(_ctx+'/u/'+_siteId+'/f/img/y_ico2.png'));
    infoWin.open();
    infoWin.setContent('<div ><p>当前位置:' +position.city+position.addr+position.district
    		+ '</p></div>');
    infoWin.setPosition(new qq.maps.LatLng(_lat,_lng));
    qq.maps.event.addListener(markerdingwei, 'click', function() {
    	infoWin.close();
        infoWin.open();
        infoWin.setContent('<div ><p>当前位置:' +position.city+position.addr+position.district
        		+ '</p></div>');
        infoWin.setPosition(new qq.maps.LatLng(_lat,_lng));
    });
	
}
/**
 * 定位失败给出提示
 */
function showErr(){
	alert("定位失败");
}
