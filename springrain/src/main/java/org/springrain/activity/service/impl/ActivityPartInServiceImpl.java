package org.springrain.activity.service.impl;

import java.io.File;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springrain.activity.entity.ActivityMain;
import org.springrain.activity.entity.ActivityPartIn;
import org.springrain.activity.entity.Member;
import org.springrain.activity.service.IActivityPartInService;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.system.service.BaseSpringrainServiceImpl;

/**
 * 活动参与记录相关
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version 2017-03-13 16:48:04
 * @see org.springrain.activity.service.impl.ActivityPartIn
 */
@Service("activityPartInService")
public class ActivityPartInServiceImpl extends BaseSpringrainServiceImpl
		implements IActivityPartInService {

	@Override
	public String save(Object entity) throws Exception {
		ActivityPartIn activityPartIn = (ActivityPartIn) entity;
		return super.save(activityPartIn).toString();
	}

	@Override
	public String saveorupdate(Object entity) throws Exception {
		ActivityPartIn activityPartIn = (ActivityPartIn) entity;
		return super.saveorupdate(activityPartIn).toString();
	}

	@Override
	public Integer update(IBaseEntity entity) throws Exception {
		ActivityPartIn activityPartIn = (ActivityPartIn) entity;
		return super.update(activityPartIn);
	}

	@Override
	public ActivityPartIn findActivityPartInById(Object id) throws Exception {
		return super.findById(id, ActivityPartIn.class);
	}

	/**
	 * 列表查询,每个service都会重载,要把sql语句封装到service中,Finder只是最后的方案
	 * 
	 * @param finder
	 * @param page
	 * @param clazz
	 * @param o
	 * @return
	 * @throws Exception
	 */
	@Override
	public <T> List<T> findListDataByFinder(Finder finder, Page page,
			Class<T> clazz, Object o) throws Exception {
		return super.findListDataByFinder(finder, page, clazz, o);
	}

	/**
	 * 根据查询列表的宏,导出Excel
	 * 
	 * @param finder
	 *            为空则只查询 clazz表
	 * @param ftlurl
	 *            类表的模版宏
	 * @param page
	 *            分页对象
	 * @param clazz
	 *            要查询的对象
	 * @param o
	 *            querybean
	 * @return
	 * @throws Exception
	 */
	@Override
	public <T> File findDataExportExcel(Finder finder, String ftlurl,
			Page page, Class<T> clazz, Object o) throws Exception {
		return super.findDataExportExcel(finder, ftlurl, page, clazz, o);
	}

	@Override
	public ActivityPartIn findActivityPartInByOpenIdAndActivityMainId(
			String openId, String activityMainId,String siteId) throws Exception{
		Finder finder = Finder.getSelectFinder(ActivityPartIn.class);
		finder.append(" where activityId = :activityId and userId in ( select userId from ")
			.append(Finder.getTableName(Member.class)).append(" where openId=:openId and siteId=:siteId ) ")
			.setParam("activityId", activityMainId).setParam("openId", openId)
			.setParam("siteId", siteId);
		return super.queryForObject(finder,ActivityPartIn.class);
	}

	@Override
	public List<ActivityPartIn> findListDataByUserId(String userId, String siteId)
			throws Exception {
		Finder finder = Finder.getSelectFinder(ActivityPartIn.class);
		finder.append(" where userId = :userId and activityId in ( select id from ")
			.append(Finder.getTableName(ActivityMain.class)).append(" where siteId = :siteId ) ")
			.setParam("userId", userId).setParam("siteId", siteId);
		return super.queryForList(finder, ActivityPartIn.class);
	}

	@Override
	public boolean hasSignupForJunPolAca(String name, String telPhone,
			String activityId) throws Exception {
		Finder finder = Finder.getSelectFinder(ActivityPartIn.class);
		finder.append(" where name=:name and telPhone=:telPhone and activityId=:activityId ")
			.setParam("name", name).setParam("telPhone", telPhone).setParam("activityId", activityId);
		List<ActivityPartIn> apiList = super.queryForList(finder,ActivityPartIn.class);
		if(CollectionUtils.isEmpty(apiList)){
			return false;
		}else{
			return true;
		}
	}
	
}
