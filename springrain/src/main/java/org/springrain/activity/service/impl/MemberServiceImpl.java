package org.springrain.activity.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.activity.entity.ActivityStarDic;
import org.springrain.activity.entity.Member;
import org.springrain.activity.service.IActivityPartInService;
import org.springrain.activity.service.IMemberService;
import org.springrain.cms.directive.CmsContentListDirective;
import org.springrain.cms.util.DirectiveUtils;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.system.entity.User;
import org.springrain.system.service.BaseSpringrainServiceImpl;
import org.springrain.system.service.ITableindexService;
import org.springrain.system.service.IUserService;
import org.springrain.weixin.sdk.common.api.IWxMpConfig;
import org.springrain.weixin.sdk.common.api.IWxMpConfigService;
import org.springrain.weixin.sdk.mp.api.IWxMpUserService;
import org.springrain.weixin.sdk.mp.bean.result.WxMpUser;

import freemarker.core.Environment;
import freemarker.ext.beans.StringModel;

/**
 * 会员相关
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version 2017-03-21 14:34:32
 * @see org.springrain.activity.entity.base.service.impl.Member
 */
@Service("memberService")
public class MemberServiceImpl extends BaseSpringrainServiceImpl implements
		IMemberService {
	
	@Resource
	IWxMpUserService wxMpUserService;
	
	@Resource
	IWxMpConfigService wxMpConfigService;
	
	@Resource
	private ITableindexService tableindexService;
	
	@Resource
	private IActivityPartInService activityPartInService;
	
	@Resource
	private IUserService userService;
	
	@Override
	public String save(Object entity) throws Exception {
		Member member = (Member) entity;
		return super.save(member).toString();
	}

	@Override
	public String saveorupdate(Object entity) throws Exception {
		Member member = (Member) entity;
		return super.saveorupdate(member).toString();
	}

	@Override
	public Integer update(IBaseEntity entity) throws Exception {
		Member member = (Member) entity;
		return super.update(member);
	}

	@Override
	public Member findMemberById(Object id) throws Exception {
		return super.findById(id, Member.class);
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
	public List<Member> findMemberList(String siteId, Page page,
			Member queryBean) throws Exception {
		if(StringUtils.isEmpty(siteId)){
			return null;
		}
		
		Finder finder = Finder.getSelectFinder(Member.class);
		finder.append(" where siteId = :siteId ").setParam("siteId", siteId);
		super.getFinderWhereByQueryBean(finder, queryBean);
		
		List<Member> resList = super.queryForList(finder, Member.class, page);
		
		return resList;
	}

	@Override
	public void saveMemberAndUser(Member member) throws Exception {
		// 先保存t_user再保存member
		User user = new User();
		String id = tableindexService.updateNewId(User.class);
		String account = member.getSiteId() + "-" + member.getOpenId();
		String password = UUID.randomUUID().toString();
		user.setId(id);
		user.setName(member.getName());
		user.setAccount(account);
		user.setPassword(password);
		user.setMobile(member.getTelephone());
		user.setWeixinId(member.getOpenId());
		user.setUserType(1); // 会员用户
		user.setActive(1); // 有效
		userService.saveUser(user);
	
		// 获取微信中的信息
		String openId = member.getOpenId();
		IWxMpConfig wxmpconfig = wxMpConfigService.findWxMpConfigById(member.getSiteId());
		WxMpUser wxMpUser = wxMpUserService.userInfo(wxmpconfig, openId);
		member.setNickname(wxMpUser.getNickname());
		member.setPortrait(wxMpUser.getHeadImgUrl());
		
		member.setId(user.getId());
		member.setUserId(user.getId());
		member.setPoint(0);
		member.setCreateTime(new Date());
		super.save(member);
	}

	@Override
	public void saveMemberPoint(String id, int point,String siteId) throws Exception{
		// 获取星级信息
		String star = "";
		Finder finder_star = Finder.getSelectFinder(ActivityStarDic.class);
		finder_star.append(" where (:point between minVal and maxVal) and siteId = :siteId ")
			.setParam("point", point).setParam("siteId", siteId);
		ActivityStarDic asd = super.queryForObject(finder_star,ActivityStarDic.class);
		if(asd!=null){
			star = asd.getStar();
		}
		
		// 更新积分与星级
		Finder finder = Finder.getUpdateFinder(Member.class);
		finder.append(" point = :point , star = :star where id = :id ")
			.setParam("point", point).setParam("star", star).setParam("id", id);
		super.update(finder);
	}

	@Override
	public Member findMemberByOpenId(String openId, String siteId)
			throws Exception {
		if(StringUtils.isEmpty(openId) || StringUtils.isEmpty(siteId)){
			return null;
		}
		Finder finder = Finder.getSelectFinder(Member.class);
		finder.append(" where openId =:openId and siteId =:siteId ")
			.setParam("openId", openId).setParam("siteId", siteId);
		
		Member member = super.queryForObject(finder, Member.class);
		
		if(member!=null){
			// 活动参与的记录
			member.setActivityPartInList(activityPartInService.findListDataByUserId(member.getId(),siteId));
		}
		
		return member;
	}

	@Override
	public List<Member> findListByIdsForTag(List<String> idList, int orderBy) throws Exception {
		Finder finder = Finder.getSelectFinder(Member.class);
		finder.append(" where id in (:ids)").setParam("ids", idList);
		finder.append(getOrderSql(orderBy));
		List<Member> memberList = super.queryForList(finder,Member.class);
		return memberList;
	}
	
	@Override
	public List<Member> findListForTag(Map params, Environment env,
			String siteId) throws Exception {
		if(StringUtils.isEmpty(siteId)){
			return null;
		}
		Finder finder = Finder.getSelectFinder(Member.class);
		finder.append(" where 1=1 ");
		
		// 常用参数（表与实体类直接对应的字段）
		Member mParams = new Member();
		BeanUtils.populate(mParams, params);
		super.getFinderWhereByQueryBean(finder, mParams);
		
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
		finder.append(getOrderSql(CmsContentListDirective.getOrderBy(params)));
		
		List<Member> memberList = super.queryForList(finder, Member.class, page);
		
		return memberList;
	}
	
	/**
	 * 获取排序sql
	 * @param orderBy
	 * @return
	 */
	private String getOrderSql(int orderBy){
		String orderSql = "";
		switch (orderBy) {
		case 0: // 默认排序
			orderSql = " order by point desc ";
			break;
		case 1: // nickname 昵称 升序
			orderSql = " order by nickname asc ";
			break;
		case 2: // nickname 昵称  降序
			orderSql = " order by nickname desc ";
			break;
		case 3: // point 积分  升序
			orderSql = " order by point asc ";
			break;
		case 4: // point 积分 降序
			orderSql = " order by point desc ";
			break;
		case 5: // createTime 创建时间 升序
			orderSql = " order by createTime asc ";
			break;
		case 6: // createTime 创建时间 降序
			orderSql = " order by createTime desc ";
			break;
		case 7: // star 星级 升序
			orderSql = " order by star asc ";
			break;
		case 8: // star 星级  降序
			orderSql = " order by star desc ";
			break;
		default:
			break;
		}
		return orderSql;
	}
	
	
}
