//如果图片为null,设置默认图片
template.helper('defaultImg', function (_imgUrl) {
    if((_imgUrl!=undefined) &&　(_imgUrl!="") && (_imgUrl!=null)){  
    	return _imgUrl;  
    }   
    return  '../img/no.jpg';  
});

template.config("escape", false);

//对日期进行格式化为指定的字符串格式
template.helper('dateFormat', function (date, format) {
	date=Date.parse(date.replace(/-/g,"/"));  
	date = new Date(date);  
	
    var map = {  
        "M": date.getMonth() + 1, //月份   
        "d": date.getDate(), //日   
        "h": date.getHours(), //小时   
        "m": date.getMinutes(), //分   
        "s": date.getSeconds(), //秒   
        "q": Math.floor((date.getMonth() + 3) / 3), //季度   
        "S": date.getMilliseconds() //毫秒   
    };  
    format = format.replace(/([yMdhmsqS])+/g, function(all, t){  
        var v = map[t];  
        if(v !== undefined){  
            if(all.length > 1){  
                v = '0' + v;  
                v = v.substr(v.length-2);  
            }  
            return v;  
        }  
        else if(t === 'y'){  
            return (date.getFullYear() + '').substr(4 - all.length);  
        }  
        return all;  
    });  
    return format;  
});

//判断是否为null
function isNull(t) {
	if (t == null || t == "null" || t == "" || typeof(t) == "undefined") {
		return true;
	}
	return false;
}

template.helper('formatPrice', function(price, type) {
	if(price.toString()=="0"){
		return "0";
	}
    if(price){
        var arrayPrice = price.toString().split(".");
        if(type == 'integer') {
            return arrayPrice[0]?arrayPrice[0]:"0";
        }else if (type =='decimal') {
        	var str = arrayPrice[0];
            var str2 = arrayPrice.length > 1?"."+arrayPrice[1].substring(0,2):".00";
            return str+str2;
        }
    }else{
        if(type == 'integer') {
            return "0";
        }else if (type =='decimal') {
            return ".00";
        }
    }
});
