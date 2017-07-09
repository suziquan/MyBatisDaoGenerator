package model;

import java.util.ArrayList;
import java.util.List;

public class Dao {
    private String packageName;
    private String className;
    private List<String> importPackages = new ArrayList<>();
    private String modelClassName;
    private String sqlSessionFactoryName;

    public void addPackage(String packageName){
        importPackages.add(packageName);
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<String> getImportPackages() {
        return importPackages;
    }

    public void setImportPackages(List<String> importPackages) {
        this.importPackages = importPackages;
    }

    public String getModelClassName() {
        return modelClassName;
    }

    public void setModelClassName(String modelClassName) {
        this.modelClassName = modelClassName;
    }

    public String getSqlSessionFactoryName() {
        return sqlSessionFactoryName;
    }

    public void setSqlSessionFactoryName(String sqlSessionFactoryName) {
        this.sqlSessionFactoryName = sqlSessionFactoryName;
    }
}
