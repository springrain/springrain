package org.springrain.system.service.impl;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.system.entity.Stock;
import org.springrain.system.service.IStockService;
import org.springframework.stereotype.Service;


import java.util.List;
/**
 * TODO 在此加入类描述
 * @author springrain<Auto generate>
 * @version  2019-08-19 15:36:24
 */

@Service("stockService")
public class StockServiceImpl extends BaseSpringrainServiceImpl implements IStockService {

   
    @Override
	public String  save(IBaseEntity entity ) throws Exception{
	    Stock stock=(Stock) entity;
	    return super.save(stock).toString();
	}


	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
		Stock stock=(Stock) entity;
		return super.update(stock);
    }
	
    @Override
	public Stock findStockById(String id) throws Exception{
		return super.findById(id,Stock.class);
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

}
