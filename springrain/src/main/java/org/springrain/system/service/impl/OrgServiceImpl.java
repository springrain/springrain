package org.springrain.system.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.SecUtils;
import org.springrain.system.entity.Org;
import org.springrain.system.entity.User;
import org.springrain.system.entity.UserOrg;
import org.springrain.system.service.BaseSpringrainServiceImpl;
import org.springrain.system.service.IOrgService;

/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2013-07-06 16:02:58
 * @see org.springrain.springrain.service.impl.Org
 */
@Service("orgService")
public class OrgServiceImpl extends BaseSpringrainServiceImpl implements IOrgService {

   
    @Override
	public String  saveOrg(Org entity) throws Exception{
    	
    	   String id=SecUtils.getUUID();
    	   entity.setId(id);
    	   
    	   String comcode= findOrgNewComcode(id, entity.getPid());
    	   
    	   entity.setComcode(comcode);
    	   
    	   super.save(entity);
    	   
    	   updateOrgManager(id,entity.getManagerRoleId());
    	   
	       return id;
	}

   
	@Override
    public Integer updateOrg(Org entity) throws Exception{
		String pid=entity.getPid();
		entity.setComcode(null);
		
		String id=entity.getId();
		if(StringUtils.isEmpty(id)){
			return null;
		}
		
		updateOrgManager(id,entity.getManagerRoleId());
		
		
		Finder f_old_c=Finder.getSelectFinder(Org.class, "comcode").append(" WHERE id=:id ").setParam("id", id);
		
		String old_c=super.queryForObject(f_old_c, String.class);
		
		String new_c=findOrgNewComcode(id, pid);
		
		if(new_c.equalsIgnoreCase(old_c)){//编码没有改变
			return super.update(entity,true);
			
		}
		entity.setComcode(new_c);
		Integer update = super.update(entity,true);
		//级联更新
		Finder f_s_list=Finder.getSelectFinder(Org.class, "id,comcode").append(" WHERE comcode like :comcode and id<>:id ").setParam("comcode", old_c+"%").setParam("id", id);
	    List<Org> list = super.queryForList(f_s_list, Org.class);
	    if(CollectionUtils.isEmpty(list)){
	    	 return update;
	    }
		
	    for(Org org:list){
	    	String _id=org.getId();
	     String _c=	findOrgNewComcode(_id, id);
	     org.setComcode(_c);
	     org.setPid(id);
	    	
	    }
		
		super.update(list,true);
		 
	    return update;
    }
	
	
	/**
	 * 更新部门的主管Id
	 * @param orgId
	 * @param managerId
	 * @return
	 * @throws Exception
	 */
	
	private void updateOrgManager(String orgId,String managerId) throws Exception{
		if(StringUtils.isBlank(orgId)||StringUtils.isBlank(managerId)){
			return;
		}
		
		Finder finder =Finder.getDeleteFinder(UserOrg.class).append(" WHERE orgId=:orgId and manager=1 ");
		finder.setParam("orgId", orgId);
        super.update(finder);
        UserOrg userOrg=new UserOrg();
        userOrg.setOrgId(orgId);
        userOrg.setUserId(managerId);
        
        super.save(userOrg);
		}

	
	
	
	
	
	
    @Override
	public Org findOrgById(Object id) throws Exception{
    	
    	Org org=super.findById(id,Org.class);
    	if(org==null){
    		return null;
    	}
    	
    	Finder f=new Finder("SELECT u.id id,u.name name FROM  ").append(Finder.getTableName(User.class)).append(" u,").append(Finder.getTableName(UserOrg.class)).append(" re ");
    	f.append(" WHERE re.userId=u.id and re.manager=1 and re.orgId=:orgId order by u.id asc ");
    	f.setParam("orgId", org.getId());
    	
    	Page page=new Page(1);
    	page.setPageSize(1);
    	page.setSelectpagecount(false);
    	
    	List<User> list = super.queryForList(f, User.class, page);
    	
    	if(CollectionUtils.isEmpty(list)){
    		return org;
    	}
    	
    	User u=list.get(0);
    	org.setManagerRoleId(u.getId());
    	org.setManagerName(u.getName());
    	
    	
    	
	 return org;
	}

/**
 * 列表查询,每个service都会重载,要把sql语句封装到service中,Finder只是最后的方案
 * @param finder
 * @param page
 * @param clazz
 * @param o
 * @return
 * @throws Exception
 */
        @Override
    public <T> List<T> findListDataByFinder(Finder finder, Page page, Class<T> clazz,
			Object o) throws Exception{
        	
        	finder=new Finder("SELECT o.* FROM ").append(Finder.getTableName(Org.class)).append(" o ");
        	finder.append(" WHERE   o.active=:active order by o.sortno asc ");
        	 finder.setParam("active", 1);
			 return super.queryForList(finder, clazz);
			}
	/**
	 * 根据查询列表的宏,导出Excel
	 * @param finder 为空则只查询 clazz表
	 * @param ftlurl 类表的模版宏
	 * @param page 分页对象
	 * @param clazz 要查询的对象
	 * @param o  querybean
	 * @return
	 * @throws Exception
	 */
		@Override
	public <T> File findDataExportExcel(Finder finder,String ftlurl, Page page,
			Class<T> clazz, Object o)
			throws Exception {
			
			 return super.findDataExportExcel(finder,ftlurl,page,clazz,o);
		}

	@Override
	public List<Org> findTreeOrg() throws Exception {
		
		return findTreeByPid(null);
	}
	
	private List<Org> diguiwrapList(List<Org> from,List<Org> tolist,String parentId){
		if(CollectionUtils.isEmpty(from)){
			return null;
		}
		
		for(int i=0;i<from.size();i++){
			Org m=from.get(i);
			if(m==null||(m.getOrgType()-0==0)){
				//from.remove(i);
			//	i=i-1;
				continue;
			}
		
			String pid=m.getPid();
			if((pid==null)&&(parentId!=null)){
				continue;
			}
		
			if((parentId==m.getPid())||(m.getPid().equals(parentId))){
				List<Org> leaf=new ArrayList<Org>();
				m.setLeafOrg(leaf);
				tolist.add(m);
				//from.remove(i);
			//	i=i-1;
			  diguiwrapList(from, leaf, m.getId());
				
				
			}
			
			
		}
		
		return tolist;

	}

	@Override
	public String deleteOrgById(String orgId) throws Exception {
		if(StringUtils.isEmpty(orgId)){
			return null;
		}
		Finder finder=Finder.getSelectFinder(Org.class, "comcode").append(" WHERE id=:orgId ").setParam("orgId", orgId);
		String comcode = super.queryForObject(finder, String.class);
		
		Finder f_update=Finder.getUpdateFinder(Org.class, " active=:active ").append(" WHERE comcode like :comcode ").setParam("active", 0).setParam("comcode", comcode+"%");
		
		super.update(f_update);
		
		
		
		return null;
	}

	@Override
	public List<Org> findTreeByPid(String pid) throws Exception {
        Finder finder=Finder.getSelectFinder(Org.class).append(" WHERE active=:active ").setParam("active", 1);
        
        if(StringUtils.isNotBlank(pid)){
        	finder.append(" and comcode like :comcode and id<>:pid ").setParam("comcode", "%,"+pid+",%").setParam("pid", pid);
        }
        finder.append(" order by sortno asc ");
        
        
        
		List<Org> list=super.queryForList(finder, Org.class);
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		List<Org> wrapList=new ArrayList<Org>();
		diguiwrapList(list, wrapList, null);
		
		return wrapList;
	}


	@Override
	public String findOrgNewComcode(String id,String pid) throws Exception {
		
		if(StringUtils.isEmpty(id)){
			return null;
		}
		
		String comcode=null;
		Finder f_p_c=Finder.getSelectFinder(Org.class, "comcode").append(" WHERE id=:pid ").setParam("pid", pid);
		String p_c=super.queryForObject(f_p_c, String.class);
		//如果没有上级部门
		if(StringUtils.isEmpty(p_c)){
			p_c=",";
		}
		
		comcode=p_c+id+",";
		
		return comcode;
	}
	
	
	
	
	
		
}
