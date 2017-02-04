package org.springrain.frame.util;

public class Enumerations {
	
	public enum OrgType{
		部门(1),虚拟权限组(2),站长部门(10),微信订阅服务号(11),wap站(14),PC站(13),企业号(12),投票(15);
		int type;
		private OrgType(Integer type){
			this.type = type;
		}
		
		public int getType() {
			return type;
		}
		
		public static OrgType getOrgType(Integer type){
			switch (type) {
				case 1:
					return 部门;
				case 2:
					return 虚拟权限组;
				case 10:
					return 站长部门;
				case 11:
					return 微信订阅服务号;
				case 12:
					return 企业号;
				case 13:
					return PC站;
				case 14:
					return wap站;
				case 15:
					return 投票;
				default:
					return null;
			}
		}
		
		public static OrgType getOrgTypeByName(String name){
			if("微信订阅服务号".equals(name))
				return 微信订阅服务号;
			else if("wap站".equals(name))
				return wap站;
			else if("PC站".equals(name))
				return PC站;
			else if("企业号".equals(name))
				return 企业号;
			else if("投票".equals(name))
				return 投票;
			else 
				return null;
		}
	}
	public enum UserOrgType{
		会员(0),员工(10),主管(11),代理主管(12),虚拟主管(13);
		int type;
		private UserOrgType(Integer type){
			this.type = type;
		}
		
		public int getType() {
			return type;
		}
		
		public static UserOrgType getUserOrgType(Integer type){
			switch (type) {
				case 0:
					return 会员;
				case 10:
					return 员工;
				case 11:
					return 主管;
				case 12:
					return 代理主管;
				case 13:
					return 虚拟主管;
				default:
					return null;
			}
		}
		
		public static UserOrgType getUserOrgTypeByName(String name){
			if("会员".equals(name))
				return 会员;
			else if("员工".equals(name))
				return 员工;
			else if("主管".equals(name))
				return 主管;
			else if("代理主管".equals(name))
				return 代理主管;
			else if("虚拟主管".equals(name))
				return 虚拟主管;
			else 
				return null;
		}
	}
	
	/**
	 * @author Administrator
	 * 站点类型
	 */
	public enum SiteType{
		mp(11),cp(12),pc(13),wap(14),poll(15);
		int type;
		
		private SiteType(Integer type) {
			this.type = type;
		}
		
		public static SiteType getSiteType(int type){
			switch (type) {
				case 11:
					return mp;
				case 12:
					return cp;
				case 13:
					return pc;
				case 14:
					return wap;
				case 15:
					return poll;
				default:
					return null;
			}
		}
	}
}
