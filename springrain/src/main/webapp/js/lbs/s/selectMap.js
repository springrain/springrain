 var markerArray = [];
 var curCity =null;
 var isNoValue = false;
function initMap(){
	var container = document.getElementById("container");
	var map = new qq.maps.Map(container, {
	            zoom: 10

	        }),
	    label = new qq.maps.Label({
	         map: map,
	         offset: new qq.maps.Size(15,-12),
	         draggable: false,
	         clickable: false
	    }),
	    markerArray = [],
	    
	   // btnSearch = document.getElementById("btn_search"),
	    url, //query_city,
	    cityservice = new qq.maps.CityService({
	        complete: function (result) {
	        	curCity=result.detail.name;
	        	jQuery('[data-toggle="distpicker"]').find("select").val(findProvinceByCity(curCity)).trigger("change");
	            //curCity.children[0].innerHTML = result.detail.name;
	            map.setCenter(result.detail.latLng);
	        }
	    });
	cityservice.searchLocalCity();
	map.setOptions({
	    draggableCursor: "crosshair"
	});
	$(container).mouseenter(function () {
	    label.setMap(map);
	});
	$(container).mouseleave(function () {
	    label.setMap(null);
	});
	qq.maps.event.addListener(map, "mousemove", function (a) {
	    var latlng = a.latLng;
	    label.setPosition(latlng);
	    label.setContent(latlng.getLat().toFixed(6) + "," + latlng.getLng().toFixed(6));
	});
	
	var url3;
	qq.maps.event.addListener(map, "click", function (a) {
	    document.getElementById("weidu").value = a.latLng.getLat().toFixed(6);
        document.getElementById("jingdu").value = a.latLng.getLng().toFixed(6);
	    url3 = encodeURI("http://apis.map.qq.com/ws/geocoder/v1/?location=" + a.latLng.getLat() + "," + a.latLng.getLng() + "&key=NS3BZ-CAOW3-CWI3L-YC332-JN7M5-RVBUH&output=jsonp&&callback=?");
	    try{
	    	jQuery.ajax({
		    	url:url3,
		    	dataType:"json",
		    	type:"post",
		    	async:false,
		    	success:function(result){
		    		if(result.result!=undefined){
			            document.getElementById("addressName").value = result.result.address;
			        }else{
			            document.getElementById("addressName").value = "";
			        }
			        
		    	},
		    	error:function(data){
		    		layer.alert('请求数据失败', {
						  icon: 2,
						})
	            }
		    });
	    }catch(a){
	    	
	    }
 
	});
	//curCity = document.getElementById("cur_city");
	btnSearch = document.getElementById("shousuo");
	var listener_arr = [];
	
	qq.maps.event.addDomListener(btnSearch, 'click', function () {
		var search_city=document.getElementById("search_city").value;
		if(search_city==""||search_city==undefined){
			layer.alert('请先选择城市', {
				  icon: 2,
				})
				return false;
		}
	    var value = document.getElementById("address").value;
	    var latlngBounds = new qq.maps.LatLngBounds();
	    for(var i= 0,l=listener_arr.length;i<l;i++){
	        qq.maps.event.removeListener(listener_arr[i]);
	    }
	    listener_arr.length = 0;
	    //query_city = curCity.children[0].innerHTML;
	    url = encodeURI("http://apis.map.qq.com/ws/place/v1/search?keyword=" + value + "&boundary=region("+curCity+",0)&page_size=9&page_index=1&key=NS3BZ-CAOW3-CWI3L-YC332-JN7M5-RVBUH&output=jsonp&&callback=?");
	    $.getJSON(url, function (result) {
	        if (result.count) {
	            isNoValue = false;
	            each(markerArray, function (n, ele) {
	                ele.setMap(null);
	            });
	            markerArray.length = 0;
	            each(result.data, function (n, ele) {
	                var latlng = new qq.maps.LatLng(ele.location.lat, ele.location.lng);
	                latlngBounds.extend(latlng);
	                var left = n * 27;
	                var marker = new qq.maps.Marker({
	                    map: map,
	                    position: latlng,
	                  
	                    zIndex: 10
	                });
	                marker.index = n;
	                marker.isClicked = false;
	                setAnchor(marker, true);
	                markerArray.push(marker);
	                var listener1 = qq.maps.event.addDomListener(marker, "mouseover", function () {
	                    var n = this.index;
	                    setCurrent(markerArray, n, false);
	                    setCurrent(markerArray, n, true);
	                    label.setContent(this.position.getLat().toFixed(6) + "," + this.position.getLng().toFixed(6));
	                    label.setPosition(this.position);
	                    label.setOptions({
	                        offset: new qq.maps.Size(15, -20)
	                    })

	                });
	                listener_arr.push(listener1);
	                var listener2 = qq.maps.event.addDomListener(marker, "mouseout", function () {
	                    var n = this.index;
	                    setCurrent(markerArray, n, false);
	                    setCurrent(markerArray, n, true);
	                    label.setOptions({
	                        offset: new qq.maps.Size(15, -12)
	                    })
	                });
	                listener_arr.push(listener2);
	                var listener3 = qq.maps.event.addDomListener(marker, "click", function (a) {
	                	marker.setAnimation(null);
	                	marker.setAnimation(qq.maps.MarkerAnimation.DROP);
	                    var n = this.index;
	                    setFlagClicked(markerArray, n);
	                    setCurrent(markerArray, n, false);
	                    setCurrent(markerArray, n, true);
	                    document.getElementById("addressName").value = ele.address;
	                    document.getElementById("weidu").value = a.latLng.getLat().toFixed(6);
	                    document.getElementById("jingdu").value = a.latLng.getLng().toFixed(6);
	                });
	                listener_arr.push(listener3);
	                map.fitBounds(latlngBounds);
	            });
	           
	            
	           
	        } else {
	        	springrain.info("没有找到相关数据");
	        }
	    });
	});
}
function each(obj, fn) {
    for (var n = 0, l = obj.length; n < l; n++) {
        fn.call(obj[n], n, obj[n]);
    }
}

function setAnchor(marker, flag) {
    var left = marker.index * 27;
    if (flag == true) {
        var anchor = new qq.maps.Point(10, 30),
                origin = new qq.maps.Point(left, 0),
                size = new qq.maps.Size(27, 33),
                icon = new qq.maps.MarkerImage(_ctx+"/u/s_10007/s/img/marker10.png", size, origin, anchor);
        marker.setIcon(icon);
    } else {
        var anchor = new qq.maps.Point(10, 30),
                origin = new qq.maps.Point(left, 35),
                size = new qq.maps.Size(27, 33),
                icon = new qq.maps.MarkerImage(_ctx+"/u/s_10007/s/img/marker10.png", size, origin, anchor);
        marker.setIcon(icon);
    }
}

function setCurrent(arr, index, isMarker) {
    if (isMarker) {
        each(markerArray, function (n, ele) {
            if (n == index) {
                setAnchor(ele, false);
                ele.setZIndex(10);
            } else {
                if (!ele.isClicked) {
                    setAnchor(ele, true);
                    ele.setZIndex(9);
                }
            }
        });
    } else {
        each(markerArray, function (n, ele) {
            if (n == index) {
                ele.div.style.background = "#DBE4F2";
            } else {
                if (!ele.div.isClicked) {
                    ele.div.style.background = "#fff";
                }
            }
        });
    }
}

function setFlagClicked(arr, index) {
    each(markerArray, function (n, ele) {
        if (n == index) {
            ele.isClicked = true;
            ele.div.isClicked = true;
            var str = '<div style="width:250px;">' + ele.div.children[1].innerHTML.toString() + '</div>';
            var latLng = ele.getPosition();
            document.getElementById("weidu").value = a.latLng.getLat().toFixed(6);
            document.getElementById("jingdu").value = a.latLng.getLng().toFixed(6);
        } else {
            ele.isClicked = false;
            ele.div.isClicked = false;
        }
    });
}

