package org.springrain.frame.entity;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 使用Map作为实体类的积累,set方法用于设置数据库字段,put方法用于自定义属性
 */
public  class BaseMapEntity implements Serializable {

    private String tableName;




    private Map<String,Object> dbField=new ConcurrentHashMap<>() ;

    private Map<String,Object> all=new ConcurrentHashMap<>();


    public BaseMapEntity set(String key,Object object){
        dbField.put(key,object);
        all.put(key,object);
        return this;
    }
    public BaseMapEntity put(String key,Object object){
        all.put(key,object);
        return this;
    }

    public Object get(String key,Object object){
        return all.get(key);
    }



    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public  String  getTableName(){
        return tableName;
    }

    public Map<String, Object> getDbField() {
        return dbField;
    }

    public void setDbField(Map<String, Object> dbField) {
        this.dbField = dbField;
        all.putAll(dbField);
    }

    public Map<String, Object> getAll() {
        return all;
    }

}
