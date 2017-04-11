package org.springrain.activity.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.activity.entity.ActivityMain;
import org.springrain.activity.entity.ActivityPartIn;
import org.springrain.activity.entity.Member;
import org.springrain.activity.service.IActivityMainService;
import org.springrain.activity.service.IMemberService;
import org.springrain.cms.entity.CmsChannelContent;
import org.springrain.cms.entity.CmsContent;
import org.springrain.cms.entity.CmsLink;
import org.springrain.cms.service.ICmsContentService;
import org.springrain.cms.util.ContentConstant;
import org.springrain.cms.util.DirectiveUtils;
import org.springrain.frame.common.SessionUser;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.system.service.BaseSpringrainServiceImpl;
import org.springrain.system.service.ITableindexService;

import freemarker.core.Environment;
import freemarker.ext.beans.StringModel;

/**
 * 活动内容相关
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version 2017-03-13 17:20:47
 * @see org.springrain.activity.service.impl.ActivityMain
 */
@Service("activityMainService")
public class ActivityMainServiceImpl extends BaseSpringrainServiceImpl
		implements IActivityMainService {

	@Resource
	private ITableindexService tableindexService;
	
	@Resource
	private ICmsContentService cmsContentService;
	
	@Resource
	private IMemberService memberService;
	
	@Override
	public String save(Object entity) throws Exception {
		ActivityMain activityMain = (ActivityMain) entity;
		return super.save(activityMain).toString();
	}

	@Override
	public String saveorupdate(Object entity) throws Exception {
		ActivityMain activityMain = (ActivityMain) entity;
		return super.saveorupdate(activityMain).toString();
	}

	@Override
	public Integer update(IBaseEntity entity) throws Exception {
		ActivityMain activityMain = (ActivityMain) entity;
		return super.update(activityMain);
	}

	@Override
	public ActivityMain findActivityMainById(Object id) throws Exception {
		return super.findById(id, ActivityMain.class);
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
	public List<ActivityMain> findListByIdsForTag(List<String> asList, int orderBy) throws Exception {
		Finder finder = Finder.getSelectFinder(ActivityMain.class);
		finder.append(" where id in (:ids) ").setParam("ids", asList);
		finder.append(" order by id ");
		List<ActivityMain> amList = super.queryForList(finder,ActivityMain.class);
		
		
		Finder finder_cms = Finder.getSelectFinder(CmsContent.class);
		finder_cms.append(" where id in (:ids) ").setParam("ids", asList);
		finder.append(" order by createDate desc");
		List<CmsContent> cmsList = super.queryForList(finder_cms, CmsContent.class);
		
		// 以cmsContent的排序为准
		List<ActivityMain> resList = new ArrayList<>();
		for(CmsContent cms:cmsList){
			for(ActivityMain am:amList){
				if(am.getId().equals(cms.getId())){
					am.setCmsContent(cms);
					resList.add(am);
					break;
				}
			}
		}
		
		return resList;
	}

	@Override
	public List<ActivityMain> findListForTag(Map params, Environment env,
			String siteId, String channelId) throws Exception{
		Finder finder_cms = Finder.getSelectFinder(CmsContent.class);
		finder_cms.append(" where 1=1 ");
		
		// 当前登录用户是否已经参与过 hasPartIn
		String hasPartIn = DirectiveUtils.getString("hasPartIn", params);
		if(StringUtils.isNotEmpty(hasPartIn)){
			if("true".equals(hasPartIn)){
				// 已经参与过
				finder_cms.append(" and id in ( select activityId from ");
			}else if("false".equals(hasPartIn)){
				// 未参与过
				finder_cms.append(" and id not in ( select activityId from ");
			}
			finder_cms.append(Finder.getTableName(ActivityPartIn.class))
			.append(" where userId = ( select userId from ").append(Finder.getTableName(Member.class))
			.append(" where openId=:openId and siteId=:siteId ) ) ")
			.setParam("openId", DirectiveUtils.getString("openId", params))
			.setParam("siteId", siteId);
		}
		
		// 去除某些ID
		String noids = DirectiveUtils.getString("noids", params);
		if(StringUtils.isNotBlank(noids)){
			finder_cms.append(" and id not in (:noids) ").setParam("noids", Arrays.asList(noids.split(",")));
		}
		
		// 查询某个站点某个栏目下的内容
		if(siteId != null && channelId != null){
			finder_cms.append(" and id in ( select contentId from ").append(Finder.getTableName(CmsChannelContent.class))
				.append(" where siteId = :siteId and channelId = :channelId ) ")
				.setParam("siteId", siteId).setParam("channelId", channelId);
		}
		
		// 其他参数
		Integer active = DirectiveUtils.getInt("active", params);
		if(active != null){
			finder_cms.append(" and active = :active ").setParam("active", active);
		}
		Integer commentPerm = DirectiveUtils.getInt("commentPerm", params);
		if(commentPerm != null){
			finder_cms.append(" and commentPerm = :commentPerm ").setParam("commentPerm", commentPerm);
		}
		
		// 先查询cmsContent便于排序
		CmsContent cmsParams = null;
		StringModel stringModel_am = (StringModel) params.get("queryBean");
		if(stringModel_am != null){
			ActivityMain am = (ActivityMain)stringModel_am.getAdaptedObject(ActivityMain.class);
			cmsParams = am.getCmsContent();
		}
		if(cmsParams != null){
			super.getFinderWhereByQueryBean(finder_cms, cmsParams);
		}
		
		// 分页
		StringModel stringModel = (StringModel) params.get("page");
		Page page = null;
		if(stringModel!=null){
			page = (Page) stringModel.getAdaptedObject(Page.class);
			
			// 修改分页大小
			Integer pageSize = DirectiveUtils.getInt("pageSize", params);
			if(pageSize!=null){
				page.setPageSize(pageSize);
			}
		}
		
		// 排序
		finder_cms.append(this.getOrderSql(DirectiveUtils.getInt("orderBy", params)));
		
		List<CmsContent> cmsContentList = super.queryForList(finder_cms,CmsContent.class,page);
		if(cmsContentList == null){
			return null;
		}
		
		// 查询activityMain，设置cmsContent
		List<ActivityMain> resList = new ArrayList<>();
		List<String> ids = new ArrayList<>();
		for(CmsContent cms:cmsContentList){
			ids.add(cms.getId());
		}
		
		if(ids.size()>0){
			Finder finder_am = Finder.getSelectFinder(ActivityMain.class);
			finder_am.append(" where id in (:ids)" ).setParam("ids", ids)
				.append(" order by id ");
			List<ActivityMain> amList = super.queryForList(finder_am, ActivityMain.class);
			
			for(CmsContent cc : cmsContentList ){
				for(ActivityMain am : amList){
					if(am.getId().equals(cc.getId())){
						am.setCmsContent(cc);
						resList.add(am);
					}
				}
			}
		}
		return resList;
	}

	/**
	 * 获取排序sql
	 * @param orderBy
	 * @return
	 */
	private String getOrderSql(Integer orderBy){
		if(orderBy == null){
			orderBy = 6;
		}
		String orderSql = "";
		switch (orderBy) {
		case 0: // 默认排序，默认以时间倒序排序
			orderSql = " order by createDate desc ";
			break;
		case 1: // title 标题 升序
			orderSql = " order by title asc ";
			break;
		case 2: // title 标题 降序
			orderSql = " order by title desc ";
			break;
		case 3: // lookcount 打开次数 升序
			orderSql = " order by lookcount asc ";
			break;
		case 4: // lookcount 打开次数 降序
			orderSql = " order by lookcount desc ";
			break;
		case 5: // createDate 创建时间 升序
			orderSql = " order by createDate asc ";
			break;
		case 6: // createDate 创建时间 降序
			orderSql = " order by createDate desc ";
			break;
		case 7: // status 审核状态 升序
			orderSql = " order by status asc ";
			break;
		case 8: // status 审核状态 降序
			orderSql = " order by status desc ";
			break;
		case 9: // active 是否可用 升序
			orderSql = " order by active asc ";
			break;
		case 10: // active 是否可用 降序
			orderSql = " order by active desc ";
			break;
		case 11: // commentPerm 评论开关 升序
			orderSql = " order by commentPerm asc ";
			break;
		case 12: // commentPerm 评论开关 降序
			orderSql = " order by commentPerm desc ";
			break;
		default:
			break;
		}
		return orderSql;
	}

	@Override
	public String saveActivityMain(ActivityMain activityMain) throws Exception{
		if(StringUtils.isBlank(activityMain.getCmsContent().getCreatePerson())){
			activityMain.getCmsContent().setCreatePerson(SessionUser.getUserId());
		}
		String id = cmsContentService.saveContent(activityMain.getCmsContent());
		activityMain.setId(id);
		activityMain.setTotalNumber(0);
		this.save(activityMain);
		
		//清除缓存
	    super.cleanCache(activityMain.getSiteId());
		
		return id;
	}

	@Override
	public Integer updateActivityMain(ActivityMain activityMain) throws Exception{
		/*if(StringUtils.isBlank(activityMain.getCmsContent().getCreatePerson())){
			activityMain.getCmsContent().setCreatePerson(SessionUser.getUserId());
		}*/
		activityMain.getCmsContent().setId(activityMain.getId());
		this.update(activityMain.getCmsContent(), true);

		// CMS_LINK
		Finder finder_cmslink = Finder.getUpdateFinder(CmsLink.class);
		finder_cmslink.append(" active = :active where businessId = :businessId and siteId = :siteId ")
			.setParam("active", activityMain.getCmsContent().getActive())
			.setParam("businessId", activityMain.getCmsContent().getId())
			.setParam("siteId", activityMain.getSiteId());
		super.update(finder_cmslink);
		
		//清除缓存
	    super.cleanCache(activityMain.getSiteId());
		
		return this.update(activityMain, true);
	}

	@Override
	public void deleteById(String id, String siteId) throws Exception{
		// 清除缓存
//		super.cleanCache(siteId);
	
		Finder finder_cms = Finder.getUpdateFinder(CmsContent.class);
		finder_cms.append(" active = 0 where id = :id")
			.setParam("id", id);
		super.update(finder_cms);
		
		// CMS_LINK
		Finder finder_cmslink = Finder.getUpdateFinder(CmsLink.class);
		finder_cmslink.append(" active = 0 where businessId = :businessId and siteId = :siteId ")
			.setParam("businessId", id).setParam("siteId", siteId);
		super.update(finder_cmslink);
		
		//super.deleteById(id, CmsContent.class);
		//super.deleteById(id, ActivityMain.class);
	}
	
	@Override
	public void deleteByIds(List<String> ids, String siteId) throws Exception {
		// 清除缓存
//		super.cleanCache(siteId);
		
		Finder finder_cms = Finder.getUpdateFinder(CmsContent.class);
		finder_cms.append(" active = 0 where id in (:ids)")
			.setParam("ids", ids);
		super.update(finder_cms);
		
		// CMS_LINK
		Finder finder_cmslink = Finder.getUpdateFinder(CmsLink.class);
		finder_cmslink.append(" active = 0 where businessId in (:businessId) and siteId = :siteId ")
			.setParam("businessId", ids).setParam("siteId", siteId);
		super.update(finder_cmslink);
		
//		super.deleteByIds(ids, CmsContent.class);
//		super.deleteByIds(ids, ActivityMain.class);
	}

	@Override
	public ActivityMain findActivityMainSide(String id, String siteId,
			String channelId, Boolean next) throws Exception {
		if(StringUtils.isBlank(siteId) && StringUtils.isBlank(channelId)){
			return null;
		}
		
		Finder finder_am = Finder.getSelectFinder(ActivityMain.class);
		finder_am.append(" where 1=1 and siteId = :siteId ").setParam("siteId", siteId);
		finder_am.append(" and id in ( select id from ").append(Finder.getTableName(CmsContent.class))
		.append(" where siteId=:siteId and active = :active ").append(" )")
		.setParam("siteId", siteId)
		.setParam("active", ContentConstant.CONTENT_ACTIVE_YES);
		if(next){
			// 下一篇
			finder_am.append(" and createDate > ( select createDate from ").append(Finder.getTableName(ActivityMain.class))
				.append(" where id = :id ) ")
				.append(" order by createDate asc limit 0,1 ");
		}else{
			// 上一篇
			finder_am.append(" and createDate < ( select createDate from ").append(Finder.getTableName(ActivityMain.class))
				.append(" where id = :id ) ")
				.append(" order by createDate desc limit 0,1 ");
		}
		
		ActivityMain activityMain = super.queryForObject(finder_am, ActivityMain.class);

		activityMain = this.setCmsContent(activityMain);
		return activityMain;
	}

	@Override
	public ActivityMain findActivityMainById(String siteId, String id) throws Exception {
		Finder finder = Finder.getSelectFinder(ActivityMain.class);
		finder.append(" where siteId = :siteId and id=:id ")
			.setParam("siteId", siteId).setParam("id", id);
		ActivityMain am = super.queryForObject(finder, ActivityMain.class);
		am = this.setCmsContent(am);
		return am;
	}
	
	/**
	 * 设置cmscontent
	 * @param am
	 * @return
	 * @throws Exception
	 */
	private ActivityMain setCmsContent(ActivityMain am) throws Exception{
		String id = am.getId();
		CmsContent cmsContent = super.findById(id, CmsContent.class);
		am.setCmsContent(cmsContent);
		return am;
	}

	@Override
	public void saveActivityPartIn(Member member,String activityMainId) throws Exception {
		ActivityPartIn api = new ActivityPartIn();
		api.setName(member.getName());
		api.setTelPhone(member.getTelephone());
		api.setPartInTime(new Date());
		api.setActivityId(activityMainId);
		api.setUserId(member.getUserId());
		super.save(api);
	}
	
	@Override
	public void saveActivityPartIn(String openId, String activityMainId, String siteId) throws Exception{
		Member member = memberService.findMemberByOpenId(openId, siteId);
		this.saveActivityPartIn(member, activityMainId);
		
		// 活动增加一条报名人数
		Finder finder = Finder.getUpdateFinder(ActivityMain.class);
		finder.append(" totalNumber = totalNumber+1 where id=:id ").setParam("id", activityMainId);
		super.update(finder);
	}

	@Override
	public List<ActivityMain> findActivityMainList(ActivityMain activityMain,
			String siteId, String channelId ,Page page) throws Exception {
		
		Finder finder_cms = Finder.getSelectFinder(CmsContent.class);
		finder_cms.append(" where 1=1 and id in ( select contentId from ")
			.append(Finder.getTableName(CmsChannelContent.class))
			.append(" where siteId=:siteId and channelId = :channelId )")
			.setParam("siteId", siteId).setParam("channelId", channelId);
		// 先查询cmsContent便于排序
		if(activityMain != null && activityMain.getCmsContent()!=null){
			super.getFinderWhereByQueryBean(finder_cms, activityMain.getCmsContent());
		}
		
		// 排序
		finder_cms.append(" order by createDate desc ");
		List<CmsContent> cmsContentList = super.queryForList(finder_cms,CmsContent.class,page);
		
		// 查询activityMain，设置cmsContent
		List<ActivityMain> resList = new ArrayList<>();
		if(cmsContentList!=null){
			List<String> ids = new ArrayList<>();
			for(CmsContent cms:cmsContentList){
				ids.add(cms.getId());
			}
			
			if(ids.size()>0){
				Finder finder_am = Finder.getSelectFinder(ActivityMain.class);
				finder_am.append(" where id in (:ids)" ).setParam("ids", ids)
					.append(" order by id ");
				List<ActivityMain> amList = super.queryForList(finder_am, ActivityMain.class);
				
				for(CmsContent cc : cmsContentList ){
					for(ActivityMain am : amList){
						if(am.getId().equals(cc.getId())){
							am.setCmsContent(cc);
							resList.add(am);
						}
					}
				}
			}
		}
		
		return resList;
	}

	@Override
	public void saveActivityPartInForJunPolAca(ActivityPartIn api, String siteId) throws Exception {
		api.setPartInTime(new Date());
		super.save(api);
		
		
		
		// 活动增加一条报名人数
		Finder finder = Finder.getUpdateFinder(ActivityMain.class);
		finder.append(" totalNumber = totalNumber+1 where id=:id ").setParam("id", api.getActivityId());
		super.update(finder);
	}
	
	
	
}
