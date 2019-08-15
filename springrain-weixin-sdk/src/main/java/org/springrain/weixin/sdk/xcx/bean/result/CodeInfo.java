package org.springrain.weixin.sdk.xcx.bean.result;

import java.util.HashMap;
import java.util.Map;

public class CodeInfo {
    
	/**
	 * 最大32个可见字符，只支持数字，大小写英文以及部分特殊字符：!#$&'()*+,/:;=?@-._~，其它字符请自行编码为合法字符（因不支持%，中文无法使用 urlencode 处理，请使用其他编码方式）
	 */
	private String scene;

	/**
	 * 必须是已经发布的小程序存在的页面（否则报错），例如 "pages/index/index" ,根路径前不要填加'/',不能携带参数（参数请放在scene字段里），如果不填写这个字段，默认跳主页面
	 */
	private String page;

	/**
	 * 二维码的宽度
	 */
	private Integer width = 430;
	
	/**
	 * 自动配置线条颜色，如果颜色依然是黑色，则说明不建议配置主色调
	 */
	private boolean auto_color = false;
	
	/**
	 * auto_color 为 false 时生效，使用 rgb 设置颜色 例如 {"r":"xxx","g":"xxx","b":"xxx"} 十进制表示  默认是：{"r":"0","g":"0","b":"0"}
	 */
	private Map<String,Object> line_color = new HashMap<>();

	
	public String getScene() {
		return scene;
	}

	public void setScene(String scene) {
		this.scene = scene;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public boolean getAuto_color() {
		return auto_color;
	}

	public void setAuto_color(boolean auto_color) {
		this.auto_color = auto_color;
	}

	public Map<String, Object> getLine_color() {        
		return line_color;
	}

	public void setLine_color(Map<String, Object> line_color) {
		this.line_color = line_color;
	}
	
	
	

}
