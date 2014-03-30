package org.springrain.demo.service.impl;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springrain.demo.entity.DicData;
import org.springrain.demo.service.BaseDemoServiceImpl;
import org.springrain.demo.service.IDicDataService;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;


/**
 * TODO 在此加入类描述
 * @copyright {@link springrain}
 * @author 9iu.org<Auto generate>
 * @version  2013-07-31 15:56:45
 * @see org.springrain.demo.service.impl.DicData
 */
@Service("dicDataService")
public class DicDataServiceImpl extends BaseDemoServiceImpl implements IDicDataService {

   
    @Override
	public String  save(Object entity ) throws Exception{
	      DicData dicData=(DicData) entity;
	       return (String) super.save(dicData);
	}

    @Override
	public String  saveorupdate(Object entity ) throws Exception{
	      DicData dicData=(DicData) entity;
		 return super.saveorupdate(dicData).toString();
	}
	
	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
	 DicData dicData=(DicData) entity;
	return super.update(dicData);
    }
    @Override
	public DicData findDicDataById(Object id) throws Exception{
	 return super.findById(id,DicData.class);
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
        	if(finder==null){
        	 finder=Finder.getSelectFinder(DicData.class).append(" WHERE 1=1 ");
        	}
        	if(o!=null){
        		getFinderWhereByQueryBean(finder, o);
        	}
        	
        	
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
	public List<String> findAjax(String pathtypekey) throws Exception {
		// TODO Auto-generated method stub
		//Finder finder=new Finder("select name from t_dic_data where typekey=:typekey order by name");
		Finder finder=Finder.getSelectFinder(DicData.class,"name").append("  where typekey=:typekey order by name ");
		finder.setParam("typekey",pathtypekey);
		List<String> datas=super.queryForList(finder, String.class);
		return datas;
	}

	

}
