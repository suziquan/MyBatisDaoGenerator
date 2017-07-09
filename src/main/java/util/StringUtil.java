package util;

public class StringUtil {

    /**
     * 首字母改小写
     * @param s 字符串
     * @return 首字母改小写的结果
     */
    public static String uncapFirst(String s) {
        if (Character.isLowerCase(s.charAt(0)))
            return s;
        return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    /**
     * 首字母改大写
     * @param s 字符串
     * @return 首字母改大写的结果
     */
    public static String capFirst(String s) {
        if (Character.isUpperCase(s.charAt(0)))
            return s;
        return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }
}
