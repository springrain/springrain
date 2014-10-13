<#assign myParentDir="service">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>  
package ${basepackage}.service;

import ${basepackage}.entity.${className};
import org.springrain.system.service.IBaseDemoService;
<#include "/copyright_class.include" >
public interface I${className}Service extends IBaseDemoService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	${className} find${className}ById(Object id) throws Exception;
	
	
	
}
