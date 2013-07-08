<#assign myParentDir="service">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>  
package ${basepackage}.service;

import ${basepackage}.entity.${className};

<#include "/copyright_class.include" >
public interface I${className}Service extends IBaseTestdb1Service {

	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	${className} find${className}ById(Object id) throws Exception;
	
	
}
