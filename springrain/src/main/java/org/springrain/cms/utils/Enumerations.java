package org.springrain.cms.utils;

public class Enumerations {
	
	public enum SiteType {
		微信订阅服务号(0),wap站(1),PC站(2),企业号(3),投票(4);
		int type;
		private SiteType(Integer type){
			this.type = type;
		}
		public int getType() {
			return type;
		}
		
		public static SiteType getSiteType(Integer type){
			switch (type) {
			case 0:
				return 微信订阅服务号;
			case 1:
				return wap站;
			case 2:
				return PC站;
			case 3:
				return 企业号;
			case 4:
				return 投票;
			default:
				return null;
			}
		}
		
		public static SiteType getSiteByName(String name){
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
	
	public enum OrgType{
		微信订阅服务号(11),wap站(14),PC站(13),企业号(12),投票(15);
		int type;
		private OrgType(Integer type){
			this.type = type;
		}
		
		public int getType() {
			return type;
		}
		
		public static OrgType getOrgType(Integer type){
			switch (type) {
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
}
