package org.springrain.frame.entity;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 使用Map作为实体类的积累,set方法用于设置数据库字段,put方法用于自定义属性
 */
public class BaseMapEntity implements Serializable {

    private String tableName;

    private String pkName = "id";


    private Map<String, Object> dbFieldValue = new HashMap<>();

    private Map<String, Object> allValue = new HashMap<>();


    public BaseMapEntity set(String key, Object object) {
        if (!checkSqlStr(key)) {
            return this;
        }
        dbFieldValue.put(key, object);
        allValue.put(key, object);
        return this;
    }

    public BaseMapEntity put(String key, Object object) {
        if (!checkSqlStr(key)) {
            return this;
        }
        allValue.put(key, object);
        return this;
    }

    public Object get(String key) {
        return allValue.get(key);
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        if (!checkSqlStr(tableName)) {
            return;
        }


        this.tableName = tableName;
    }

    public Object getPkValue() {

        return get(getPkName());
    }

    public void setPkValue(Object pkValue) {
        set(getPkName(), getPkName());
    }

    public String getPkName() {
        return pkName;
    }

    public void setPkName(String pkName) {
        this.pkName = pkName;
    }


    public Map<String, Object> getDbFieldValue() {
        return dbFieldValue;
    }

    public void setDbFieldValue(Map<String, Object> dbFieldValue) {

        if (dbFieldValue == null || dbFieldValue.size() < 1) {
            return;
        }

        /*
        for (Map.Entry<String, Object> m : dbField.entrySet()) {

            if (m.getKey() == null || m.getValue() == null) {
            //if (m.getKey() == null) {
                this.dbField.put(m.getKey(), null);
                this.all.put(m.getKey(), null);
                continue;
            }
            this.dbField.put(m.getKey(), m.getValue());
            this.all.put(m.getKey(), m.getValue());
        }

         */

        this.dbFieldValue = dbFieldValue;
        this.allValue.putAll(dbFieldValue);
    }

    public Map<String, Object> getAllValue() {
        return allValue;
    }

    private boolean checkSqlStr(String sqlStr) {

        if (StringUtils.isBlank(sqlStr)) {
            return false;
        }


        // 如果包含特殊字符
        if (sqlStr.contains(" ") || sqlStr.contains(";") || sqlStr.contains("'") || sqlStr.contains("\\") || sqlStr.contains("(")) {
            return false;
        }
        return true;
    }


}
