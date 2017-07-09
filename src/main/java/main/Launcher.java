package main;

import model.Dao;
import model.DaoTest;
import model.Mapper;
import model.Model;
import util.FileUtil;
import util.NameUtil;
import util.TemplateUtil;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 启动类
 */
public class Launcher {

    public static String url = "jdbc:mysql://127.0.0.1:3306/demo?useUnicode=true&characterEncoding=UTF8&useSSL=false";
    public static String driverClassName = "com.mysql.jdbc.Driver";
    public static String username = "root";
    public static String password = "admin";

    public static String daoPackageName = "com.example.demo.dao";
    public static String modelPackageName = "com.example.demo.model";
    public static String sqlSessionFactoryName = "defaultMarketingSqlSessionFactory";

    //生成的代码输出到该目录
    public static String dstDirPath = "d:/daodemo";

    //主键列名
    public static String idColumnName = "id";
    //创建时间列名
    public static String createTimeColumnName = "created_at";
    //更新时间列名
    public static String updateTimeColumnName = "updated_at";

    //从数据库列类型到model字段类型用到的几个映射
    public static Map<String, String> typeMap = new HashMap<>();
    static {
        typeMap.put("INT", "Integer");
        typeMap.put("BIGINT", "Long");
        typeMap.put("TINYINT", "Boolean");
        typeMap.put("VARCHAR", "String");
        typeMap.put("DATE", "Date");
        typeMap.put("DATETIME", "Date");
    }

    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        props.setProperty("user", username);
        props.setProperty("password", password);

        DriverManager.setLoginTimeout(2);
        Driver driver = (Driver) Class.forName(driverClassName).newInstance();
        Connection connection = driver.connect(url, props);

        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet tableResultSet;
        tableResultSet = metaData.getTables(null, null
                , null, null);
        while (tableResultSet.next()) {
            String tableName = tableResultSet.getString(3);
            ResultSet columnResultSet = metaData.getColumns(null, null, tableName, null);

            String modelClassName = NameUtil.underline2Camel(tableName, false);
            String modelFileName = modelClassName + ".java";
            String mapperFileName = modelClassName + "Mapper.xml";
            String daoClassName = modelClassName+"Dao";
            String daoFileName = daoClassName + ".java";
            String daoTestClassName = daoClassName + "Test";
            String daoTestFileName = daoTestClassName + ".java";

            Model model = new Model();
            model.setPackageName(modelPackageName);
            model.setClassName(modelClassName);

            Mapper mapper = new Mapper(idColumnName,createTimeColumnName,updateTimeColumnName);
            mapper.setTableName(tableName);
            mapper.setModelClassName(modelClassName);

            Dao dao = new Dao();
            dao.setPackageName(daoPackageName);
            dao.setClassName(daoClassName);
            dao.setModelClassName(modelClassName);
            dao.addPackage(modelPackageName + "." + modelClassName);
            dao.setSqlSessionFactoryName(sqlSessionFactoryName);

            DaoTest daoTest = new DaoTest();
            daoTest.setPackageName(daoPackageName);
            daoTest.setDaoPackageName(daoPackageName);
            daoTest.setDaoClassName(daoClassName);
            daoTest.setModelPackageName(modelPackageName);
            daoTest.setModelClassName(modelClassName);

            while (columnResultSet.next()) {
                String columnName = columnResultSet.getString("COLUMN_NAME");
                String columnType = columnResultSet.getString("TYPE_NAME");
                String comment = columnResultSet.getString("REMARKS");
                String fieldName = NameUtil.underline2Camel(columnName,true);
                String fieldType = typeMap.get(columnType.split("\\s+")[0]);
                Object defaultValue = getFieldDefaultValue(fieldName,fieldType);
                model.addField(fieldName,fieldType,comment);
                mapper.addMapping(columnName,fieldName);
                daoTest.addValueMapping(fieldName,defaultValue);
            }

            Map<String,Object> data = new HashMap<>();
            data.put("model",model);
            data.put("mapper",mapper);
            data.put("dao",dao);
            data.put("daoTest",daoTest);
            File dstDir = new File(dstDirPath);
            if (!dstDir.exists()) {
                dstDir.mkdirs();
            }
            TemplateUtil.generate(new File(dstDir,"model").getPath(),modelFileName,TemplateUtil.MODEL_TEMPLATE, data);
            TemplateUtil.generate(new File(dstDir, "mapper").getPath(),mapperFileName,TemplateUtil.MAPPER_TEMPLATE, data);
            TemplateUtil.generate(new File(dstDir, "dao").getPath(),daoFileName,TemplateUtil.DAO_TEMPLATE, data);
            TemplateUtil.generate(new File(dstDir,"daoTest").getPath(),daoTestFileName,TemplateUtil.DAO_TEST_TEMPLATE,data);
        }
        connection.close();
    }

    /**
     * 给每个字段一个默认的值，用于DaoTest模版
     * @param fieldName 字段名
     * @param fieldType 字段类型
     * @return 默认值
     */
    private static Object getFieldDefaultValue(String fieldName,String fieldType){
        Object value = null;
        switch (fieldType){
            case "Integer":
                value = "1";
                break;
            case "Long":
                value = "1L";
                break;
            case "Boolean":
                value = "false";
                break;
            case "String":
                value = "\""+fieldName+"\"";
                break;
            case "Date":
                value = "new Date()";
                break;
            default:
                break;
        }
        return value;
    }

}
