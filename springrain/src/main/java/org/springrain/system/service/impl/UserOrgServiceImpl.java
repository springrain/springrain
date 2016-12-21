package org.springrain.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.system.entity.Org;
import org.springrain.system.entity.RoleOrg;
import org.springrain.system.entity.User;
import org.springrain.system.entity.UserOrg;
import org.springrain.system.entity.UserRole;
import org.springrain.system.service.BaseSpringrainServiceImpl;
import org.springrain.system.service.IUserOrgService;

/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2013-07-06 15:28:18
 * @see org.springrain.springrain.service.impl.TuserOrg
 */
@Service("userOrgService")
public class UserOrgServiceImpl extends BaseSpringrainServiceImpl implements IUserOrgService {

	@Override
	public List<User> findUserByOrgId(String orgId) throws Exception {
		if(StringUtils.isBlank(orgId)){
			return null;
		}
		Finder finder=new Finder("SELECT u.* FROM ").append(Finder.getTableName(User.class)).append("  u,").append(Finder.getTableName(UserOrg.class)).append(" re where re.userId=u.id and re.orgId=:orgId order by u.id asc ");
		finder.setParam("orgId", orgId);
		return super.queryForList(finder,User.class);
	}
	
	
	@Override
	public List<String> findUserIdsByOrgId(String orgId) throws Exception {
		if(StringUtils.isBlank(orgId)){
			return null;
		}
		Finder finder=new Finder("SELECT re.userId FROM ").append(Finder.getTableName(UserOrg.class)).append(" re where  re.orgId=:orgId order by re.userId asc ");
		finder.setParam("orgId", orgId);
		return super.queryForList(finder,String.class);
	}
	
	

	@Override
	public List<User> findAllUserByOrgId(String orgId) throws Exception {
		if(StringUtils.isBlank(orgId)){
			return null;
		}
		Finder f_code=Finder.getSelectFinder(Org.class, "comcode").append(" where id=:orgId ");
		f_code.setParam("orgId", orgId);
		String comcode=super.queryForObject(f_code, String.class);
		
		Finder finder=new Finder("SELECT u.* FROM ").append(Finder.getTableName(User.class)).append("  u,").append(Finder.getTableName(UserOrg.class)).append(" re,").append(Finder.getTableName(Org.class)).append(" org WHERE org.id=re.orgId and u.id=re.userId and org.comcode like :comcode  order by u.id asc ");
		finder.setParam("comcode", comcode+"%");
		return super.queryForList(finder,User.class);
	}
	
	@Override
	public List<String> findAllUserIdsByOrgId(String orgId) throws Exception {
		if(StringUtils.isBlank(orgId)){
			return null;
		}
		Finder f_code=Finder.getSelectFinder(Org.class, "comcode").append(" where id=:orgId ");
		f_code.setParam("orgId", orgId);
		String comcode=super.queryForObject(f_code, String.class);
		
		Finder finder=new Finder("SELECT re.userId FROM ").append(Finder.getTableName(UserOrg.class)).append(" re,").append(Finder.getTableName(Org.class)).append(" org WHERE org.id=re.orgId and org.comcode like :comcode  order by re.userId asc ");
		finder.setParam("comcode", comcode+"%");
		return super.queryForList(finder,String.class);
	}

	@Override
	public List<Org> findOrgByUserId(String userId) throws Exception {
		if(StringUtils.isBlank(userId)){
			return null;
		}
		Finder finder=new Finder("SELECT org.* FROM  ").append(Finder.getTableName(UserOrg.class)).append(" re ,").append(Finder.getTableName(Org.class)).append(" org  WHERE re.userId=:userId and org.id=re.orgId   order by org.id asc   ");
		finder.setParam("userId", userId);
		return super.queryForList(finder, Org.class);
	}
	
	
	@Override
	public List<String> findOrgIdsByUserId(String userId) throws Exception {
		if(StringUtils.isBlank(userId)){
			return null;
		}
		Finder finder=new Finder("SELECT re.orgId FROM  ").append(Finder.getTableName(UserOrg.class)).append(" re ").append("   WHERE re.userId=:userId    order by re.orgId asc   ");
		finder.setParam("userId", userId);
		return super.queryForList(finder, String.class);
	}
	
	
	

	@Override
	public List<User> findManagerUserByOrgId(String orgId) throws Exception {
		if(StringUtils.isBlank(orgId)){
			return null;
		}
		Finder finder=new Finder("SELECT u.* FROM ").append(Finder.getTableName(User.class)).append(" u,").append(Finder.getTableName(Org.class)).append(" org,").append(Finder.getTableName(UserRole.class)).append(" re  WHERE re.roleId=org.managerRoleId and org.id=:orgId and u.id=re.userId order by re.userId asc   ");
		finder.setParam("orgId", orgId);
		return super.queryForList(finder, User.class);
	}
	
	
	

	@Override
	public List<String> findManagerUserIdsByOrgId(String orgId) throws Exception {
		if(StringUtils.isBlank(orgId)){
			return null;
		}
		Finder finder=new Finder("SELECT re.userId FROM ").append(Finder.getTableName(Org.class)).append(" org,").append(Finder.getTableName(UserRole.class)).append(" re  WHERE re.roleId=org.managerRoleId and org.id=:orgId order by re.userId asc   ");
		finder.setParam("orgId", orgId);
		return super.queryForList(finder, String.class);
	}
	
	

	@Override
	public Integer findAllUserCountByOrgId(String orgId) throws Exception {
		if(StringUtils.isBlank(orgId)){
			return null;
		}
		//Finder f_code=new Finder("SELECT comcode FROM t_org where id=:orgId ");
		Finder f_code=Finder.getSelectFinder(Org.class, "comcode").append(" where id=:orgId ");
		f_code.setParam("orgId", orgId);
		String comcode=super.queryForObject(f_code, String.class);
		
		Finder finder=new Finder("SELECT count(re.userId) FROM ").append(Finder.getTableName(UserOrg.class)).append(" re,").append(Finder.getTableName(Org.class)).append(" org WHERE org.id=re.orgId and org.comcode like :comcode");
		finder.setParam("comcode", comcode+"%");
		return super.queryForObject(finder,Integer.class);
	}


	@Override
	public List<String> findOrgIdsByManagerUserId(String managerUserId,Page page) throws Exception {
		if(StringUtils.isEmpty(managerUserId)){
			return null;
		}
		
		String findOrgIdsSQLByManagerUserId = findOrgIdsSQLByManagerUserId(managerUserId);
		if(StringUtils.isEmpty(findOrgIdsSQLByManagerUserId)){
			return null;
		}
		Finder finder=new Finder(findOrgIdsSQLByManagerUserId);
		
		
		return super.queryForList(finder, String.class,page);
	}


	@Override
	public List<Org> findOrgByManagerUserId(String managerUserId,Page page) throws Exception {
		if(StringUtils.isEmpty(managerUserId)){
			return null;
		}
		
		
		String findOrgIdsSQLByManagerUserId = findOrgIdsSQLByManagerUserId(managerUserId);
		if(StringUtils.isEmpty(findOrgIdsSQLByManagerUserId)){
			return null;
		}
		
		
		
       String sql=wrapWheresSQLByManagerUserId(managerUserId);
		
		if(StringUtils.isEmpty(sql)){
			return null;
		}
		
		StringBuffer hasLeafBuffer=new StringBuffer();
		hasLeafBuffer.append(" SELECT _system_temp_org.*  FROM ").append(Finder.getTableName(Org.class));
		hasLeafBuffer.append(" _system_temp_org WHERE  ").append(sql);
		hasLeafBuffer.append(" order by _system_temp_org.id  asc ");
		
		Finder finder=new Finder(hasLeafBuffer.toString());
		
		return super.queryForList(finder,Org.class,page);
		
	}


	@Override
	public List<String> findUserIdsByManagerUserId(String managerUserId,Page page) throws Exception {
		
		String userIdsSQLByManagerUserId = findUserIdsSQLByManagerUserId(managerUserId);
		
		if(StringUtils.isEmpty(userIdsSQLByManagerUserId)){
			return null;
		}
		
		userIdsSQLByManagerUserId=userIdsSQLByManagerUserId+" order by  _system_temp_user_org.userId asc ";
		
		Finder finder=new Finder(userIdsSQLByManagerUserId);
		
		return super.queryForList(finder, String.class,page);
	}


	@Override
	public List<User> findUserByManagerUserId(String managerUserId,Page page) throws Exception {
		String wheresql=wrapWheresSQLByManagerUserId(managerUserId);
		if(StringUtils.isBlank(wheresql)){
			return null;
		}
		Finder finder=new Finder("SELECT u.* FROM ");
		finder.append(Finder.getTableName(User.class)).append(" u,").append(Finder.getTableName(UserOrg.class)).append(" re,").append(Finder.getTableName(Org.class)).append(" _system_temp_org ");
		finder.append(" WHERE u.id=re.userId and re.orgId=_system_temp_org.id and ");
		finder.append(wheresql);
		finder.append(" order by  u.id asc ");
		return super.queryForList(finder, User.class,page);
	}


	@Override
	public List<String> findOrgIdsByManagerUserId(String managerUserId) throws Exception {
		return findOrgIdsByManagerUserId(managerUserId, null);
	}


	@Override
	public List<Org> findOrgByManagerUserId(String managerUserId) throws Exception {
		return findOrgByManagerUserId(managerUserId, null);
	}


	@Override
	public List<String> findUserIdsByManagerUserId(String managerUserId) throws Exception {
		return findUserIdsByManagerUserId(managerUserId, null);
	}


	@Override
	public List<User> findUserByManagerUserId(String managerUserId) throws Exception {
		return findUserByManagerUserId(managerUserId, null);
	}
	
	
	

	@Override
	public String findOrgIdsSQLByManagerUserId(String managerUserId) throws Exception {
		String sql=wrapWheresSQLByManagerUserId(managerUserId);
		
		if(StringUtils.isEmpty(sql)){
			return null;
		}
		StringBuffer hasLeafBuffer=new StringBuffer();
		hasLeafBuffer.append(" SELECT _system_temp_org.id  FROM ").append(Finder.getTableName(Org.class));
		hasLeafBuffer.append(" _system_temp_org WHERE  ").append(sql);
		hasLeafBuffer.append(" order by _system_temp_org.id  asc ");
		
		 return hasLeafBuffer.toString();
		
		
	}
	
	@Override
	public String findUserIdsSQLByManagerUserId(String managerUserId) throws Exception {
		String wheresql=wrapWheresSQLByManagerUserId(managerUserId);
		if(StringUtils.isBlank(wheresql)){
			return null;
		}
		
		
		StringBuffer sb=new StringBuffer("SELECT  _system_temp_user_org.userId FROM ");
		sb.append(Finder.getTableName(UserOrg.class)).append(" _system_temp_user_org,").append(Finder.getTableName(Org.class)).append(" _system_temp_org ");
		sb.append(" WHERE _system_temp_user_org.orgId=_system_temp_org.id and ");
		sb.append(wheresql);
		
		 return sb.toString();
		
		
	}
	
	
	
	
	private String wrapWheresSQLByManagerUserId(String managerUserId) throws Exception {

		if(StringUtils.isEmpty(managerUserId)){
			return null;
		}
		Finder f1=new Finder("SELECT re.* FROM  ").append(Finder.getTableName(RoleOrg.class)).append(" re,").append(Finder.getTableName(UserOrg.class)).append(" uo WHERE re.orgId=uo.orgId and uo.userId=:userId   order by re.id asc ");
		f1.setParam("userId", managerUserId);
		
		List<RoleOrg> list = super.queryForList(f1, RoleOrg.class);
		
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		
		
		List<String> noLeafList=new ArrayList<String>();
		StringBuffer hasLeafBuffer=new StringBuffer();
		
		hasLeafBuffer.append("   ( 1=1  ");
		
		for(RoleOrg re:list){
			String orgId=re.getOrgId();
			Integer hasLeaf = re.getHasLeaf();
			if(hasLeaf==0){//不包含子部门
				noLeafList.add(orgId);
			}else if(hasLeaf==1){//包含子部门
				hasLeafBuffer.append(" or _system_temp_org.comcode like '%,").append(orgId).append(",%' ");
			}
		}
		
		
		if(CollectionUtils.isEmpty(noLeafList)){
			return hasLeafBuffer.toString();
		}
		
		hasLeafBuffer.append(" or _system_temp_org.id in (");
	
		for (int i = 0; i < noLeafList.size(); i++) {
			hasLeafBuffer.append("'").append(noLeafList.get(i)).append("'");
			if(i<(noLeafList.size()-1)){
				hasLeafBuffer.append(",");
			}
		}
		
		hasLeafBuffer.append(") )  ");
		
		return hasLeafBuffer.toString();
	
		
		
		
	}


	

   

		
}
