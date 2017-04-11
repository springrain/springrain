package org.springrain.frame.util;

public class Enumerations {
	
	public enum OrgType{
		部门(1),虚拟权限组(2),站长部门(10),mp(11),cp(12),pc(13),wap(14),minsoft(14);
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
					return mp;
				case 12:
					return cp;
				case 13:
					return pc;
				case 14:
					return wap;
				
				default:
					return null;
			}
		}
		
		public static OrgType getOrgTypeByName(String name){
			if(mp.name().equals(name))
				return mp;
			else if(wap.name().equals(name))
				return wap;
			else if(pc.name().equals(name))
				return pc;
			else if(cp.name().equals(name))
				return cp;
			else if(minsoft.name().equals(name))
				return minsoft;
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
	 * 链接类型
	 */
	public enum CmsLinkModeType{
		/*
		 * 0站点
		 * 1开头微信相关
		 * 3投票相关
		 * 4开头站工后相关
		 * 
		 * */
		站点(0),前台(1),前台栏目(10),前台栏目DOM(11),前台内容(12),投票(30),站长后台(4),站长后台列表(40),站长后台更新(41),站长后台查看(42);
		int type;
		
		private CmsLinkModeType(Integer type) {
			this.type = type;
		}
		
		public static CmsLinkModeType getCmsLinkModeType(int type){
			switch (type) {
				case 0:
					return 站点;
				case 1:
					return 前台;
				case 10:
					return 前台栏目;
				case 11:
					return 前台栏目DOM;
				case 12:
					return 前台内容;
				case 30:
					return 投票;
				case 4:
					return 站长后台;
				case 40:
					return 站长后台列表;
				case 41:
					return 站长后台更新;
				case 42:
					return 站长后台查看;
				default:
					return null;
			}
		}

		public int getType() {
			return type;
		}
	}
	
	//public static void main(String[] args) {
	//	System.out.println(CmsLinkModeType.投票.getType());
	//}
	
}
