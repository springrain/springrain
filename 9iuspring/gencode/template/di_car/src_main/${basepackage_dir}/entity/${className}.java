<#assign myParentDir="entity">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
package ${basepackage}.entity;

<#list table.columns as column>
	<#if column.isDateTimeColumn>
import org.iu9.frame.util.DateUtils;
import java.text.ParseException;
	<#break/>
	</#if>
</#list>
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import javax.persistence.Id;
import javax.persistence.Table;
import org.iu9.frame.annotation.WhereSQL;

import org.iu9.frame.entity.BaseEntity;
<#include "/copyright_class.include" >
@Table(name="${table.sqlName}")
public class ${className}  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "${table.tableAlias}";
	<#list table.columns as column>
	public static final String ALIAS_${column.constantName} = "${column.columnAlias}";
	</#list>
    */
	//date formats
	<#list table.columns as column>
	<#if column.isDateTimeColumn>
	//public static final String FORMAT_${column.constantName} = DateUtils.DATETIME_FORMAT;
	</#if>
	</#list>
	
	//columns START
	<#list table.columns as column>
	/**
	 * ${column.columnAlias}
	 */
	private ${column.javaType} ${column.columnNameFirstLower};
	</#list>
	//columns END
	
	//concstructor
	<@generateConstructor className/>

	//get and set
	<@generateJavaColumns/>
	
	public String toString() {
		return new StringBuffer()
		<#list table.columns as column>
			.append("${column.columnAlias}[").append(get${column.columnName}()).append("],")
		</#list>
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
		<#list table.pkColumns as column>
			.append(get${column.columnName}())
		</#list>
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof ${className} == false) return false;
		if(this == obj) return true;
		${className} other = (${className})obj;
		return new EqualsBuilder()
			<#list table.pkColumns as column>
			.append(get${column.columnName}(),other.get${column.columnName}())
			</#list>
			.isEquals();
	}
}

	
<#macro generateJavaColumns>
	<#list table.columns as column>
		<#if column.isDateTimeColumn>
		/*
	public String get${column.columnName}String() {
		return DateUtils.convertDate2String(FORMAT_${column.constantName}, get${column.columnName}());
	}
	public void set${column.columnName}String(String value) throws ParseException{
		set${column.columnName}(DateUtils.convertString2Date(FORMAT_${column.constantName},value));
	}*/
	
		</#if>	
	public void set${column.columnName}(${column.javaType} value) {
		this.${column.columnNameLower} = value;
	}
	
	<#if column.columnNameLower=="id">
	@Id
	</#if>
     @WhereSQL(sql="${column.columnNameLower}=:${className}_${column.columnNameLower}")
	public ${column.javaType} get${column.columnName}() {
		return this.${column.columnNameLower};
	}
	</#list>
</#macro>

<#macro generateJavaOneToMany>
	<#list table.exportedKeys.associatedTables?values as foreignKey>
	<#assign fkSqlTable = foreignKey.sqlTable>
	<#assign fkTable    = fkSqlTable.className>
	<#assign fkPojoClass = fkSqlTable.className>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>
	
	private Set ${fkPojoClassVar}s = new HashSet(0);
	public void set${fkPojoClass}s(Set<${fkPojoClass}> ${fkPojoClassVar}){
		this.${fkPojoClassVar}s = ${fkPojoClassVar};
	}
	
	public Set<${fkPojoClass}> get${fkPojoClass}s() {
		return ${fkPojoClassVar}s;
	}
	</#list>
</#macro>

<#macro generateJavaManyToOne>
	<#list table.importedKeys.associatedTables?values as foreignKey>
	<#assign fkSqlTable = foreignKey.sqlTable>
	<#assign fkTable    = fkSqlTable.className>
	<#assign fkPojoClass = fkSqlTable.className>
	<#assign fkPojoClassVar = fkPojoClass?uncap_first>
	
	private ${fkPojoClass} ${fkPojoClassVar};
	
	public void set${fkPojoClass}(${fkPojoClass} ${fkPojoClassVar}){
		this.${fkPojoClassVar} = ${fkPojoClassVar};
	}
	
	public ${fkPojoClass} get${fkPojoClass}() {
		return ${fkPojoClassVar};
	}
	</#list>
</#macro>
	
	

