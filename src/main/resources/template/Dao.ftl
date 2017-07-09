package ${dao.packageName};

import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

<#list dao.importPackages as packageName>
import ${packageName};
</#list>

@Repository
public interface ${dao.className}{

    Integer create(${dao.modelClassName} ${uncapFirst(dao.modelClassName)});

    Integer creates(List<${dao.modelClassName}> ${uncapFirst(dao.modelClassName)}s);

    Integer delete(Long id);

    Integer deletes(List<Long> ids);

    Integer update(${dao.modelClassName} ${uncapFirst(dao.modelClassName)});

    ${dao.modelClassName} load(Long id);

    List<${dao.modelClassName}> loads(List<Long> ids);

    List<${dao.modelClassName}> paging(Map<String, Object> criteria);

    Long count(Map<String, Object> criteria);

}
