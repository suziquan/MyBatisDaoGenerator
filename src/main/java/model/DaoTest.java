package model;

import java.util.LinkedHashMap;

public class DaoTest {
    private String packageName;
    private String daoPackageName;
    private String daoClassName;
    private String modelPackageName;
    private String modelClassName;
    private LinkedHashMap<String,Object> valueMappings = new LinkedHashMap<>();

    public void addValueMapping(String fieldName, Object value){
        valueMappings.put(fieldName,value);
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getDaoPackageName() {
        return daoPackageName;
    }

    public void setDaoPackageName(String daoPackageName) {
        this.daoPackageName = daoPackageName;
    }

    public String getDaoClassName() {
        return daoClassName;
    }

    public void setDaoClassName(String daoClassName) {
        this.daoClassName = daoClassName;
    }

    public String getModelPackageName() {
        return modelPackageName;
    }

    public void setModelPackageName(String modelPackageName) {
        this.modelPackageName = modelPackageName;
    }

    public String getModelClassName() {
        return modelClassName;
    }

    public void setModelClassName(String modelClassName) {
        this.modelClassName = modelClassName;
    }

    public LinkedHashMap<String, Object> getValueMappings() {
        return valueMappings;
    }

    public void setValueMappings(LinkedHashMap<String, Object> valueMappings) {
        this.valueMappings = valueMappings;
    }
}
