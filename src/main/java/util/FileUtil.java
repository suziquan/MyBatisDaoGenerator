package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUtil {

    /**
     * 获得模版文件中的内容
     * @param templatePath 模版文件路径
     * @return 模版文件内容
     */
    public static String getTemplateContent(String templatePath){
        InputStream inputStream = FileUtil.class.getClassLoader().getResourceAsStream(templatePath);
        String content = "";
        BufferedReader bufferedReader = null;
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content += line;
                content += System.getProperty("line.separator");
            }
        } catch (Exception e) {
        } finally {
            if (bufferedReader != null)
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return content;
    }

}