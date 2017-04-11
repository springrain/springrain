package org.springrain.police.dto;

/**
 * 报废汽车信息
 * @author 跨时代
 *
 */
public class DiscardCarInfro {
	/**
	 * 强制报废期止
	 */
	private String qzbfqz;
	/**
	 * 是否强制
	 */
	private String sfbf;
	/**
	 * 应去检验强制报废期止
	 */
	private  String yqjyqzbfqz;
	/**
	 * 错误信息
	 */
	public String getQzbfqz() {
		return qzbfqz;
	}
	public void setQzbfqz(String qzbfqz) {
		this.qzbfqz = qzbfqz;
	}
	public String getSfbf() {
		return sfbf;
	}
	public void setSfbf(String sfbf) {
		this.sfbf = sfbf;
	}
	public String getYqjyqzbfqz() {
		return yqjyqzbfqz;
	}
	public void setYqjyqzbfqz(String yqjyqzbfqz) {
		this.yqjyqzbfqz = yqjyqzbfqz;
	}
}
