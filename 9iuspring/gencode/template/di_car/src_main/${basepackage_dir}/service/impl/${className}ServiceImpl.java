<#assign myParentDir="service.impl">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>  
package ${basepackage}.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.iu9.testdb1.service.BaseTestdb1ServiceImpl;
import ${basepackage}.entity.${className};
import ${basepackage}.service.I${className}Service;
import java.util.List;
import java.io.File;
import org.iu9.frame.service.IBaseService;
import org.iu9.frame.util.Page;
import org.iu9.frame.util.Finder;
import org.iu9.frame.entity.IBaseEntity;

<#include "/copyright_class.include" >
@Service("${classNameLower}Service")
public class ${className}ServiceImpl extends BaseTestdb1ServiceImpl implements I${className}Service {

   
    @Override
	public String  save${className}(${className} entity) throws Exception{
	       return (String) super.save(entity);
	}

    @Override
	public String  saveorupdate${className}(${className} entity) throws Exception{
	       return (String) super.saveorupdate(entity);
	}
	
	@Override
    public Integer update${className}(${className} entity) throws Exception{
	return super.update(entity);
    }
    @Override
	public ${className} find${className}ById(Object id) throws Exception{
	 return super.findById(id,${className}.class);
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
			 return super.findListDateByFinder(finder,page,clazz,o);
			}
	/**
	 * 根据查询列表的宏,导出Excel
	 * @param finder 为空则只查询 clazz表
	 * @param ftlurl 类表的模版宏
	 * @param page 分页对象
	 * @param clazz 要查询的对象
	 * @param baseService service 调用
	 * @param o  querybean
	 * @return
	 * @throws Exception
	 */
		@Override
	public <T> File findDataExportExcel(Finder finder,String ftlurl, Page page,
			Class<T> clazz, IBaseService baseService, Object o)
			throws Exception {
			 return super.findDataExportExcel(finder,ftlurl,page,clazz,baseService,o);
		}
		
}
