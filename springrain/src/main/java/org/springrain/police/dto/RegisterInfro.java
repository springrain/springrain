package org.springrain.police.dto;

/**
 * 注册提交表单
 * @author 跨时代
 *
 */
public class RegisterInfro {
	/**
	 * 身份证明名称 
	 */
	private String sfzmmc;
	/**
	 * 身份证明号码
	 */
	private String sfzmhm;
	/**
	 * 姓名
	 */
	private String xm;
	/**
	 * 手机号码
	 */
	private String sjhm;
	/**
	 * 注册城市
	 */
	private String city;
	/**
	 * 密码
	 */
	private String password1;
	/**
	 * 确认密码
	 */
	private String password2;
	/**
	 * 阅读
	 */
	private String agree;
	/**
	 * 车牌号
	 */
	private String hphm;
	
	/**
	 * 注册城市名称:河南
	 */
	private String dmsm3;
	/**
	 * 车牌加区好:豫A
	 */
	private String dmz;
	
	/**
	 * 验证失败还是成功
	 */
	private String status;
	/**
	 * 驾驶人用户状态
	 */
	private String dabh;
	
	/**
	 * 手机验证码
	 */
	private String yzm;
	
	/**
	 * 返回信息 
	 */
	private String returnMessage122;
	/**
	 * 新车车主用户和初次申领机动车和驾驶证提示
	 */
	private String tsnr;
	
	public String getSfzmmc() {
		return sfzmmc;
	}
	public void setSfzmmc(String sfzmmc) {
		this.sfzmmc = sfzmmc;
	}
	public String getSfzmhm() {
		return sfzmhm;
	}
	public void setSfzmhm(String sfzmhm) {
		this.sfzmhm = sfzmhm;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getSjhm() {
		return sjhm;
	}
	public void setSjhm(String sjhm) {
		this.sjhm = sjhm;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDmsm3() {
		return dmsm3;
	}
	public void setDmsm3(String dmsm3) {
		this.dmsm3 = dmsm3;
	}
	public String getPassword1() {
		return password1;
	}
	public void setPassword1(String password1) {
		this.password1 = password1;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getAgree() {
		return agree;
	}
	public void setAgree(String agree) {
		this.agree = agree;
	}
	public String getDmz() {
		return dmz;
	}
	public void setDmz(String dmz) {
		this.dmz = dmz;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getHphm() {
		return hphm;
	}
	public void setHphm(String hphm) {
		this.hphm = hphm;
	}
	public String getDabh() {
		return dabh;
	}
	public void setDabh(String dabh) {
		this.dabh = dabh;
	}
	public String getYzm() {
		return yzm;
	}
	public void setYzm(String yzm) {
		this.yzm = yzm;
	}
	

	public String getReturnMessage122() {
		return returnMessage122;
	}
	public void setReturnMessage122(String returnMessage122) {
		this.returnMessage122 = returnMessage122;
	}
	public String getTsnr() {
		return tsnr;
	}
	public void setTsnr(String tsnr) {
		this.tsnr = tsnr;
	}
	
	
	
}
