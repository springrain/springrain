package org.springrain.system.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
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

	@Override
	public synchronized String updateNewId(Class clazz,String prefix) throws Exception {
		if(clazz==null){
			return null;
		}
		
		String tableName=Finder.getTableName(clazz);
		if(StringUtils.isEmpty(tableName)){
			return null;
		}
		
		
		Finder finder=Finder.getSelectFinder(Tableindex.class).append(" WHERE id=:id ");
		finder.setParam("id", tableName);
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
		f_update.setParam("maxIndex", maxIndex).setParam("id", tableName);
		super.update(f_update);
		return newId;
	}
	
	
}
