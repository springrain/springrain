package org.springrain.police.dto;

/**
 * 违法汽车信息
 * @author 跨时代
 *
 */
public class CarDriverInfro {
	/**
	 * 违法行为
	 */
	private String wfms;
	/**
	 * 罚款金额
	 */
	private String fkje;
	/**
	 * 违法事间
	 */
	private String wfsj;
	/**
	 * 违法地址
	 */
	private String wfdz;
	/**
	 * 违法号码
	 */
	private String hphm;
	/**
	 * 采集机关 
	 */
	private String cjjgmc;
	/**
	 * 处理机关
	 */
	private String cljgmc;
	/**
	 * 处理标记
	 */
	private String clbjStr;
	/**
	 * 处时间
	 */
	private String clsj;
	/**
	 * 交款标记
	 */
	private String jkbj;
	
	/**
	 * 汽车名称
	 */
	private String hpzlStr;
	/**
	 * 汽车类型
	 */
	private String hpzl;
	
	/**
	 * 决定书编号
	 */
	private String jdsbh;
	
	/**
	 * 违法记分数
	 */
	private String wfjfs;
	
	
	
	
	/**
	 * 违法列表，总条数
	 */
	private CarDriverData dataDto;
	
	public String getWfms() {
		return wfms;
	}
	public void setWfms(String wfms) {
		this.wfms = wfms;
	}
	public String getFkje() {
		return fkje;
	}
	public void setFkje(String fkje) {
		this.fkje = fkje;
	}
	public String getWfsj() {
		return wfsj;
	}
	public void setWfsj(String wfsj) {
		this.wfsj = wfsj;
	}
	public String getWfdz() {
		return wfdz;
	}
	public void setWfdz(String wfdz) {
		this.wfdz = wfdz;
	}
	public String getHphm() {
		return hphm;
	}
	public void setHphm(String hphm) {
		this.hphm = hphm;
	}
	public String getCjjgmc() {
		return cjjgmc;
	}
	public void setCjjgmc(String cjjgmc) {
		this.cjjgmc = cjjgmc;
	}
	
	public String getClbjStr() {
		return clbjStr;
	}
	public void setClbjStr(String clbjStr) {
		this.clbjStr = clbjStr;
	}
	public String getClsj() {
		return clsj;
	}
	public void setClsj(String clsj) {
		this.clsj = clsj;
	}
	
	public String getJkbj() {
		return jkbj;
	}
	public void setJkbj(String jkbj) {
		this.jkbj = jkbj;
	}
	public String getHpzlStr() {
		return hpzlStr;
	}
	public void setHpzlStr(String hpzlStr) {
		this.hpzlStr = hpzlStr;
	}
	public String getHpzl() {
		return hpzl;
	}
	public void setHpzl(String hpzl) {
		this.hpzl = hpzl;
	}
	public CarDriverData getDataDto() {
		return dataDto;
	}
	public void setDataDto(CarDriverData dataDto) {
		this.dataDto = dataDto;
	}
	public String getJdsbh() {
		return jdsbh;
	}
	public void setJdsbh(String jdsbh) {
		this.jdsbh = jdsbh;
	}
	public String getWfjfs() {
		return wfjfs;
	}
	public void setWfjfs(String wfjfs) {
		this.wfjfs = wfjfs;
	}
	public String getCljgmc() {
		return cljgmc;
	}
	public void setCljgmc(String cljgmc) {
		this.cljgmc = cljgmc;
	}
	
	
}
