package org.springrain.frame.util;

/**
 * 实体类的字段说明
 * @author caomei
 *
 */
public class FieldInfo {
	
    //字段名称
	private String fieldName=null;

	//字段类型
	private Class<?> fieldType;

	//是否是主键
	private Boolean pk=false;
	
	//是否是数据库字段
	private Boolean db=true;
	
	//是否是lucene全文检索
	private Boolean lucene=false;
	
	//wheresql的注解字符串
	private String whereSQL=null;
	
	//主键序列
	private String pkSequence=null;
	

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Class<?> getFieldType() {
        return fieldType;
    }

    public void setFieldType(Class<?> fieldType) {
        this.fieldType = fieldType;
    }

    public Boolean getPk() {
        return pk;
    }

    public void setPk(Boolean pk) {
        this.pk = pk;
    }

    public Boolean getDb() {
        return db;
    }

    public void setDb(Boolean db) {
        this.db = db;
    }

    public Boolean getLucene() {
        return lucene;
    }

    public void setLucene(Boolean lucene) {
        this.lucene = lucene;
    }

    public String getWhereSQL() {
        return whereSQL;
    }

    public void setWhereSQL(String whereSQL) {
        this.whereSQL = whereSQL;
    }

    public String getPkSequence() {
        return pkSequence;
    }

    public void setPkSequence(String pkSequence) {
        this.pkSequence = pkSequence;
    }
	

}
