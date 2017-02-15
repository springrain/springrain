package org.springrain.cms.service.impl;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;
import org.springrain.cms.entity.CmsPraise;
import org.springrain.cms.service.ICmsPraiseService;
import org.springrain.frame.common.SessionUser;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.system.service.BaseSpringrainServiceImpl;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-02-15 15:02:34
 * @see org.springrain.cms.base.service.impl.CmsPraise
 */
@Service("cmsPraiseService")
public class CmsPraiseServiceImpl extends BaseSpringrainServiceImpl implements ICmsPraiseService {

   
    @Override
	public String  save(Object entity ) throws Exception{
	      CmsPraise cmsPraise=(CmsPraise) entity;
	       return super.save(cmsPraise).toString();
	}
    
    @Override
	public String  saveorupdate(Object entity ) throws Exception{
    	CmsPraise cmsPraise=(CmsPraise) entity;
    	String userId = SessionUser.getUserId();
    	String businessId = cmsPraise.getBusinessId();
    	boolean exist = findPraiseIsExist(userId,businessId);
    	if(exist){//存在，执行删除
    		deletePraise(userId, businessId);
    	}else{//不存在，新增
    		return save(cmsPraise);
    	}
	    return "";
	}
	
	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
	 CmsPraise cmsPraise=(CmsPraise) entity;
	return super.update(cmsPraise);
    }
    @Override
	public CmsPraise findCmsPraiseById(Object id) throws Exception{
	 return super.findById(id,CmsPraise.class);
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
			 return super.findListDataByFinder(finder,page,clazz,o);
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
	public Integer findPraiseNumByBusinessId(String businessId) throws Exception {
		Finder finder = new Finder("SELECT COUNT(id)  FROM ")
				.append(Finder.getTableName(CmsPraise.class))
				.append(" WHERE businessId=:businessId");
		finder.setParam("businessId", businessId);
		Integer countNum = super.queryForObject(finder,Integer.class);
	    if(countNum==null){
	    	countNum=0;
	    }
	    return countNum;
	}

	@Override
	public boolean findPraiseIsExist(String userId, String businessId) throws Exception {
		Finder finder = Finder.getSelectFinder(CmsPraise.class).append(" where userId=:userId and businessId=:businessId");
		finder.setParam("userId", userId).setParam("businessId", businessId);
		Map<String, Object> queryResult = super.queryForObject(finder);
		if(MapUtils.isNotEmpty(queryResult))
			return true;
		return false;
	}

	@Override
	public void deletePraise(String userId, String businessId) throws Exception {
		Finder finder = Finder.getDeleteFinder(CmsPraise.class).append(" where userId=:userId and businessId=:businessId");
		finder.setParam("userId", userId).setParam("businessId", businessId);
		super.update(finder);
	}

}
