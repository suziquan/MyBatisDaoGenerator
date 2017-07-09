package model;

import util.NameUtil;

import java.util.LinkedHashMap;
import java.util.Map;

public class Mapper {

    private String tableName;
    private String modelClassName;

    private String idColumnName;
    private String idFieldName;

    private String createTimeColumnName;
    private String createTimeFieldName;

    private String updateTimeColumnName;
    private String updateTimeFieldName;

    private Map<String,String> commonColumnMapping = new LinkedHashMap<>();

    public Mapper(String idColumnName,String createTimeColumnName ,String updateTimeColumnName){
        this.idColumnName = idColumnName;
        this.idFieldName = NameUtil.underline2Camel(idColumnName,true);
        this.createTimeColumnName = createTimeColumnName;
        this.createTimeFieldName = NameUtil.underline2Camel(createTimeColumnName,true);
        this.updateTimeColumnName = updateTimeColumnName;
        this.updateTimeFieldName = NameUtil.underline2Camel(updateTimeColumnName,true);
    }

    public void addMapping(String columnName,String fieldName){
        //对于主键，创建时间，更新时间这三列特殊处理
        if (!(columnName.equals(idColumnName) || columnName.equals(createTimeColumnName) || columnName.equals(updateTimeColumnName))){
            commonColumnMapping.put(columnName,fieldName);
        }
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getModelClassName() {
        return modelClassName;
    }

    public void setModelClassName(String modelClassName) {
        this.modelClassName = modelClassName;
    }

    public String getIdColumnName() {
        return idColumnName;
    }

    public void setIdColumnName(String idColumnName) {
        this.idColumnName = idColumnName;
    }

    public String getIdFieldName() {
        return idFieldName;
    }

    public void setIdFieldName(String idFieldName) {
        this.idFieldName = idFieldName;
    }

    public String getCreateTimeColumnName() {
        return createTimeColumnName;
    }

    public void setCreateTimeColumnName(String createTimeColumnName) {
        this.createTimeColumnName = createTimeColumnName;
    }

    public String getCreateTimeFieldName() {
        return createTimeFieldName;
    }

    public void setCreateTimeFieldName(String createTimeFieldName) {
        this.createTimeFieldName = createTimeFieldName;
    }

    public String getUpdateTimeColumnName() {
        return updateTimeColumnName;
    }

    public void setUpdateTimeColumnName(String updateTimeColumnName) {
        this.updateTimeColumnName = updateTimeColumnName;
    }

    public String getUpdateTimeFieldName() {
        return updateTimeFieldName;
    }

    public void setUpdateTimeFieldName(String updateTimeFieldName) {
        this.updateTimeFieldName = updateTimeFieldName;
    }

    public Map<String, String> getCommonColumnMapping() {
        return commonColumnMapping;
    }

    public void setCommonColumnMapping(Map<String, String> commonColumnMapping) {
        this.commonColumnMapping = commonColumnMapping;
    }
}
