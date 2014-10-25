package org.springrain.system.service.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.frame.util.Finder;
import org.springrain.system.entity.User;
import org.springrain.system.service.BaseSpringrainServiceImpl;
import org.springrain.system.service.IWeiXinSystemService;

@Service("weiXinSystemService")
public class WeiXinSystemServiceImpl  extends BaseSpringrainServiceImpl implements IWeiXinSystemService{

	@Override
	public User findMemberByweixinId(String weixinId) throws Exception {
		if(StringUtils.isBlank(weixinId)){
			return null;
		}
		Finder finder=Finder.getSelectFinder(User.class).append(" WHERE weixinId=:weixinId ").setParam("weixinId", weixinId);
		return super.queryForObject(finder, User.class);
	}

	@Override
	public void updateMemberinfoByweixinId(String weixinId, Integer state)
			throws Exception {
		if(StringUtils.isBlank(weixinId)){
			return;
		}
		User findMemberByweixinId = findMemberByweixinId(weixinId);
		if(findMemberByweixinId==null){
			User member=new User();
			member.setId(weixinId);
			member.setAccount(weixinId);
			member.setPassword("");
			member.setWeixinId(weixinId);
			super.save(member);
		}else{
			String memberId=findMemberByweixinId.getId();
			if(StringUtils.isBlank(memberId)){
				return;
			}
			Finder finder=Finder.getUpdateFinder(User.class," state=:state ").append(" WHERE weixinId=:weixinId and id=:id ");
			finder.setParam("state", state).setParam("weixinId", weixinId).setParam("id", memberId);
			super.update(finder);
		}
		
	}

}
