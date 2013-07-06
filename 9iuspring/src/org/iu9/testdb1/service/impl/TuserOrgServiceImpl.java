package org.iu9.testdb1.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.iu9.frame.util.Finder;
import org.iu9.testdb1.entity.Torg;
import org.iu9.testdb1.entity.Tuser;
import org.iu9.testdb1.service.BaseTestdb1ServiceImpl;
import org.iu9.testdb1.service.ITuserOrgService;
import org.springframework.stereotype.Service;

/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author 9iuspring<Auto generate>
 * @version  2013-07-06 15:28:18
 * @see org.iu9.testdb1.service.impl.TuserOrg
 */
@Service("tuserOrgService")
public class TuserOrgServiceImpl extends BaseTestdb1ServiceImpl implements ITuserOrgService {

	@Override
	public List<Tuser> findUsersByOrgId(String orgId) throws Exception {
		if(StringUtils.isBlank(orgId)){
			return null;
		}
		Finder finder=new Finder("SELECT u.* FROM T_user  u,T_user_org re where re.userId=u.id and re.orgId=:orgId");
		finder.setParam("orgId", orgId);
		return super.queryForList(finder,Tuser.class);
	}

	@Override
	public List<Tuser> findAllUsersByOrgId(String orgId) throws Exception {
		if(StringUtils.isBlank(orgId)){
			return null;
		}
		Finder f_code=new Finder("SELECT comcode FROM T_org where id=:orgId ");
		f_code.setParam("orgId", orgId);
		String comcode=super.queryForObject(f_code, String.class);
		
		Finder finder=new Finder("SELECT u.* FROM T_user u,T_user_org re re,T_org org WHERE org.id=re.orgId and u.id=re.userId and org.comcode like :comcode");
		finder.setParam("comcode", comcode+"%");
		return super.queryForList(finder,Tuser.class);
	}

	@Override
	public List<Torg> findOrgByUserId(String userId) throws Exception {
		if(StringUtils.isBlank(userId)){
			return null;
		}
		Finder finder=new Finder("SELECT org.* FROM  T_user_org re re,T_org org  WHERE re.userId=:userId and org.id=re.orgId  ");
		finder.setParam("userId", userId);
		return super.queryForList(finder, Torg.class);
	}

	@Override
	public Tuser findManagerByOrgId(String orgId) throws Exception {
		Finder finder=new Finder("SELECT u.* FROM T_user u,T_user_org re re WHERE re.orgId=:orgId and u.id=re.userId  ");
		finder.setParam("orgId", orgId);
		return super.queryForObject(finder, Tuser.class);
	}

   

		
}
