package org.springrain.police.dto;

/**
 * 驾驶证信息
 * @author 跨时代
 *
 */
public class DriverInfro {
	/**
	 * 驾驶证号码
	 */
	private String sfzmhm;
	
	/**
	 * 审验有效期止
	 */
	private String syyxqz;
	
	/**
	 * 	驾驶证有效期止
	 */
	private String yxqz;
	
	/**
	 * 	下一清分日期
	 */
	private String qfrq;
	
	/**
	 * 累积记分
	 */
	private String ljjf;
	
	/**
	 * 驾驶证状态
	 */
	private String ztstr;
	
	/**
	 * 是否挂靠
	 */
	private String sfgk;
	
	public String getSfzmhm() {
		return sfzmhm;
	}

	public void setSfzmhm(String sfzmhm) {
		this.sfzmhm = sfzmhm;
	}

	public String getSyyxqz() {
		return syyxqz;
	}

	public void setSyyxqz(String syyxqz) {
		this.syyxqz = syyxqz;
	}

	public String getYxqz() {
		return yxqz;
	}

	public void setYxqz(String yxqz) {
		this.yxqz = yxqz;
	}

	public String getQfrq() {
		return qfrq;
	}

	public void setQfrq(String qfrq) {
		this.qfrq = qfrq;
	}

	public String getLjjf() {
		return ljjf;
	}

	public void setLjjf(String ljjf) {
		this.ljjf = ljjf;
	}

	public String getZtstr() {
		return ztstr;
	}

	public void setZtstr(String ztstr) {
		this.ztstr = ztstr;
	}

	public String getSfgk() {
		if ("0".equals(this.sfgk)) {
			return "未挂靠";
		}else if ("1".equals(this.sfgk)) {
			return "已挂靠";
		}
		return sfgk;
	}

	public void setSfgk(String sfgk) {
		this.sfgk = sfgk;
	}

	
}
