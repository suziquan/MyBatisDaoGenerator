package model;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private String packageName;
    private List<String> importPackages = new ArrayList<>();
    private String className;

    private List<String> fieldNames = new ArrayList<>();
    private List<String> fieldTypes = new ArrayList<>();
    private List<String> fieldComments = new ArrayList<>();

    public void addPackage(String packageName){
        importPackages.add(packageName);
    }

    public void addField(String fieldName,String fieldType,String fieldComment) {
        fieldNames.add(fieldName);
        fieldTypes.add(fieldType);
        fieldComments.add(fieldComment);
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<String> getImportPackages() {
        return importPackages;
    }

    public void setImportPackages(List<String> importPackages) {
        this.importPackages = importPackages;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<String> getFieldNames() {
        return fieldNames;
    }

    public void setFieldNames(List<String> fieldNames) {
        this.fieldNames = fieldNames;
    }

    public List<String> getFieldTypes() {
        return fieldTypes;
    }

    public void setFieldTypes(List<String> fieldTypes) {
        this.fieldTypes = fieldTypes;
    }

    public List<String> getFieldComments() {
        return fieldComments;
    }

    public void setFieldComments(List<String> fieldComments) {
        this.fieldComments = fieldComments;
    }
}
