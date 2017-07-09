package util;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import method.CapFirstMethod;
import method.ColumnNameWrapper;
import method.UncapFirstMethod;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Map;

public class TemplateUtil {

    public static final String DAO_TEMPLATE = "template/Dao.ftl";
    public static final String MODEL_TEMPLATE = "template/Model.ftl";
    public static final String MAPPER_TEMPLATE = "template/Mapper.ftl";
    public static final String DAO_TEST_TEMPLATE = "template/DaoTest.ftl";
    public static final String[] TEMPLATES = {DAO_TEMPLATE, MODEL_TEMPLATE, MAPPER_TEMPLATE, DAO_TEST_TEMPLATE};
    public static final Configuration CONFIG = new Configuration(Configuration.VERSION_2_3_23);

    static {
        StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
        for (String template : TEMPLATES) {
            stringTemplateLoader.putTemplate(template, FileUtil.getTemplateContent(template));
        }
        CONFIG.setTemplateLoader(stringTemplateLoader);
        CONFIG.setDefaultEncoding("UTF-8");
        CONFIG.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        CONFIG.setLogTemplateExceptions(true);
    }

    /**
     * 使用数据填充模版，生成代码文件
     * @param dstDirPath 输出目录路径
     * @param dstFileName 文件名称
     * @param templateName 模版名称
     * @param data 数据
     */
    public static void generate(String dstDirPath, String dstFileName, String templateName, Map<String, Object> data) {
        try {
            File dstDir = new File(dstDirPath);
            if (!dstDir.exists()) {
                dstDir.mkdirs();
            }
            File file = new File(dstDir, dstFileName);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            Template template = CONFIG.getTemplate(templateName);
            Writer out = new FileWriter(file);
            data.put("capFirst", new CapFirstMethod());
            data.put("uncapFirst", new UncapFirstMethod());
            data.put("wrapColumnName", new ColumnNameWrapper());
            template.process(data, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
