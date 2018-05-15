/**
 * 部分通用的echarts初始化方法
 */

/**
 * 加载折线图
 * @param xArr x轴数据 [t1,t2,t3...]
 * @param legendArr 图例名称 [a,b,c...]
 * @param yArr y轴数据 [[a1,a2,a3...],[b1,b2,b3...],[c1,c2,c3]...]
 * @param domId 加载echarts的dom的ID
 * @param title 标题
 * @param defaultNullMsg 数据为空时显示的文本，根据xArr的length判断是否有数据
 * @remark xArr的length应与yArr中的每一个元素的length保持一致，legendArr的length应与yArr的length保持
 * @returns
 */
function myLoadEChartsLine(xArr,legendArr,yArr,domId,title,defaultNullMsg){
	var chartsDom = jQuery("#" + domId).get(0);
	var myCharts = echarts.getInstanceByDom(chartsDom);
	
	var _title = "";
	if(title){
		_title = title;
	}
	
	if(xArr==null || xArr==undefined || xArr.length == 0){
		if(myCharts!=null){
			myCharts.dispose(); // 销毁实例
		}
		var _defaultNunMsg = "无数据";
		if(defaultNullMsg){
			_defaultNunMsg = defaultNullMsg;
		}
		jQuery("#" + domId).html(_defaultNunMsg);
		return;
	}
	if(myCharts == null || myCharts == undefined){
		myCharts = echarts.init(chartsDom);
	}
	
	var seriesArr = new Array();
	for(var i=0;i<legendArr.length;i++){
		var name = legendArr[i];
		var data = yArr[i];
		var series = { "name":name,"type":"line","data":data };
		
		seriesArr.push(series);
	}
	
	var option = {
		"title": { text: _title },
		"legend": { data: legendArr },
		"tooltip": { trigger: 'axis' },
		"xAxis": {  data: xArr } ,
		"yAxis": { name: '' },
		"series": seriesArr
	};
	myCharts.setOption(option);
	
}

/**
 * 加载饼图
 * @param title 标题
 * @param subTitle 子标题
 * @param seriesName 饼图统计的数据的名称
 * @param legendArr 图例数组
 * @param pieData 数据数组
 * @param tooltipFormatter 鼠标移动到饼图上的提示的字符串的格式
 * @param domId 绑定的div的id
 * @param noData 如果没数据则传递true，否则传递 false
 * @returns
 */
function myLoadEChartsPie(title,subTitle,seriesName,legendArr,pieData,tooltipFormatter,domId,noData){
	var chartsDom = $("#" + domId).get(0);
	var myCharts = echarts.getInstanceByDom(chartsDom);
	
	if(noData){
		myCharts.dispose(); // 销毁实例
		$("#" + domId).html("无数据");
		return;
	}
	if(myCharts == null || myCharts == undefined){
		myCharts = echarts.init(chartsDom);
	}
	if(tooltipFormatter == null || tooltipFormatter == undefined){
		tooltipFormatter = "{a} <br/>{b} : {c} ({d}%)";
	}
	var option = {
		title: {
			top: 'top',
			left: 'center',
			text: title ,
			subtext: subTitle
		},
		legend: { 
			type: 'scroll' , // 可滚动翻页的图例
			orient: 'vertical',
			left: 'left',
			data: legendArr
		},
		tooltip : {
	        trigger: 'item',
	        formatter: tooltipFormatter
	    },
		series: [{
			name: seriesName,
			type: 'pie',
            radius : '65%',
            center: ['50%', '50%'],
            selectedMode: 'single',
			data: pieData
		}]
	};
	myCharts.setOption(option);
}
