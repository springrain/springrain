package org.springrain.demo.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import org.springrain.demo.entity.Org;
import org.springrain.demo.entity.User;
import org.springrain.demo.service.BaseDemoServiceImpl;
import org.springrain.demo.service.IUserOrgService;
import org.springrain.frame.util.Finder;

/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2013-07-06 15:28:18
 * @see org.springrain.demo.service.impl.TuserOrg
 */
@Service("userOrgService")
public class UserOrgServiceImpl extends BaseDemoServiceImpl implements IUserOrgService {

	@Override
	public List<User> findUserByOrgId(String orgId) throws Exception {
		if(StringUtils.isBlank(orgId)){
			return null;
		}
		Finder finder=new Finder("SELECT u.* FROM T_user  u,T_user_org re where re.userId=u.id and re.orgId=:orgId");
		finder.setParam("orgId", orgId);
		return super.queryForList(finder,User.class);
	}

	@Override
	public List<User> findAllUserByOrgId(String orgId) throws Exception {
		if(StringUtils.isBlank(orgId)){
			return null;
		}
		Finder f_code=new Finder("SELECT comcode FROM T_org where id=:orgId ");
		f_code.setParam("orgId", orgId);
		String comcode=super.queryForObject(f_code, String.class);
		
		Finder finder=new Finder("SELECT u.* FROM T_user u,T_user_org re re,T_org org WHERE org.id=re.orgId and u.id=re.userId and org.comcode like :comcode");
		finder.setParam("comcode", comcode+"%");
		return super.queryForList(finder,User.class);
	}

	@Override
	public List<Org> findOrgByUserId(String userId) throws Exception {
		if(StringUtils.isBlank(userId)){
			return null;
		}
		Finder finder=new Finder("SELECT org.* FROM  T_user_org re re,T_org org  WHERE re.userId=:userId and org.id=re.orgId  ");
		finder.setParam("userId", userId);
		return super.queryForList(finder, Org.class);
	}

	@Override
	public User findManagerByOrgId(String orgId) throws Exception {
		Finder finder=new Finder("SELECT u.* FROM T_user u,T_user_org re re WHERE re.orgId=:orgId and u.id=re.userId  ");
		finder.setParam("orgId", orgId);
		return super.queryForObject(finder, User.class);
	}

   

		
}
