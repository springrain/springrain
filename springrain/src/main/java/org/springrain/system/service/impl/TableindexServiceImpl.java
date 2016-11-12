package org.springrain.system.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.cms.base.entity.CmsChannel;
import org.springrain.cms.base.entity.CmsContent;
import org.springrain.frame.util.Finder;
import org.springrain.system.entity.Tableindex;
import org.springrain.system.service.BaseSpringrainServiceImpl;
import org.springrain.system.service.ITableindexService;


/**
 * TODO 在此加入类描述
 * @copyright {@link 9iu.org}
 * @author springrain<Auto generate>
 * @version  2016-11-10 14:16:38
 * @see org.springrain.demo.service.impl.Tableindex
 */
@Service("tableindexService")
public class TableindexServiceImpl extends BaseSpringrainServiceImpl implements ITableindexService {
	
	
	private Integer startIndex=10;
	
	

	@Override
	public synchronized String updateNewId(Class clazz,String prefix) throws Exception {
		if(clazz==null){
			return null;
		}
		
		String indexId=Finder.getTableName(clazz);
		if(StringUtils.isEmpty(indexId)){
			return null;
		}
		
		if(StringUtils.isNotBlank(prefix)){
			indexId=prefix+"_"+indexId;
		}
		
		
		Finder finder=Finder.getSelectFinder(Tableindex.class).append(" WHERE id=:id ");
		finder.setParam("id", indexId);
		Tableindex tableindex =super.queryForObject(finder, Tableindex.class);
		
		if(tableindex==null){
			return null;
		}
		
		Integer maxIndex=tableindex.getMaxIndex();
		maxIndex=maxIndex+1;
		String newId=tableindex.getPrefix()+maxIndex;
		if(StringUtils.isNotBlank(prefix)){
		newId=prefix+"_"+newId;
		}
		
		
		Finder f_update=Finder.getUpdateFinder(Tableindex.class, " maxIndex=:maxIndex ").append(" WHERE id=:id ");
		f_update.setParam("maxIndex", maxIndex).setParam("id", indexId);
		super.update(f_update);
		return newId;
	}

	@Override
	public String saveIndexBySiteId(String siteId) throws Exception {
		
		//站点栏目的编号
		Tableindex channelIndex=new Tableindex();
		channelIndex.setId(siteId+"_"+Finder.getTableName(CmsChannel.class));
		channelIndex.setMaxIndex(startIndex);
		channelIndex.setPrefix(siteId+"_c_");
		super.save(channelIndex);
		
		//站点内容的编号
		Tableindex contentIndex=new Tableindex();
		contentIndex.setId(siteId+"_"+Finder.getTableName(CmsContent.class));
		contentIndex.setMaxIndex(startIndex);
		contentIndex.setPrefix(siteId+"_n_");
		super.save(contentIndex);
		
		
		return null;
	}
	
	
}
