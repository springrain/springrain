package org.springrain.police.service;

import com.alibaba.druid.stat.TableStat.Name;

import java.util.List;
import java.util.Map;

import  org.springrain.frame.util.Page;
import org.springrain.police.dto.QueryNameResult;
import org.springrain.police.entity.Queryname;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-03-23 09:35:23
 * @see org.springrain.police.entity.base.service.Queryname
 */
public interface IQuerynameService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Queryname findQuerynameById(Object id) throws Exception;
	
	
	List<Map<String, Object>> findQueryNameResultList(String name, Class<QueryNameResult> clazz) throws Exception;
	
	
	
}
