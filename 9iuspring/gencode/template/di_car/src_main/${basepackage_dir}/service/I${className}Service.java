<#assign myParentDir="service">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>  
package ${basepackage}.service;

import ${basepackage}.entity.${className};

<#include "/copyright_class.include" >
public interface I${className}Service extends IBaseTestdb1Service {
	String save${className}(${className} entity) throws Exception;
    String saveorupdate${className}(${className} entity) throws Exception;
	Integer update${className}(${className} entity) throws Exception;
	${className} find${className}ById(Object id) throws Exception;
	
}
