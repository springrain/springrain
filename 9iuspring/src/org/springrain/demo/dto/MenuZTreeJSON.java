package org.springrain.demo.dto;

public class MenuZTreeJSON {
	private String id;
	private String text;
	private String pid;
	private String menuUrl;
	private Integer menuType;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public Integer getMenuType() {
		return menuType;
	}
	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}
}
