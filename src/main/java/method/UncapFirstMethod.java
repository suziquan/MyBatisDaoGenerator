package method;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import util.StringUtil;

import java.util.List;

/**
 * 用于freemarker模版：首字母改小写
 */
public class UncapFirstMethod implements TemplateMethodModelEx {
    @Override
    public Object exec(List arguments) throws TemplateModelException {
        SimpleScalar simpleScalar = (SimpleScalar) arguments.get(0);
        return StringUtil.uncapFirst(simpleScalar.getAsString());
    }
}
