package org.springrain.system.service;
  
import org.springrain.system.entity.Stock;
import org.springrain.system.service.IBaseSpringrainService;
import org.springrain.rpc.annotation.RpcServiceAnnotation;
/**
 * TODO 在此加入类描述
 * @author springrain<Auto generate>
 * @version  2019-08-19 15:36:24
 */
@RpcServiceAnnotation
public interface IStockService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Stock findStockById(String id) throws Exception;
	
	
	
}