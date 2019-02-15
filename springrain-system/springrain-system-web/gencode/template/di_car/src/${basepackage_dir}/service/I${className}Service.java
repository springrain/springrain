<#assign myParentDir="service">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>  
package ${basepackage}.service;

import ${basepackage}.entity.${className};
import org.springrain.system.service.IBaseSpringrainService;
import org.springrain.rpc.annotation.RpcServiceAnnotation;
<#include "/copyright_class.include" >
@RpcServiceAnnotation
public interface I${className}Service extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	${className} find${className}ById(Object id) throws Exception;
	
	
	
}
