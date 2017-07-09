package ${daoTest.packageName};

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

import ${daoTest.modelPackageName}.${daoTest.modelClassName};
import ${daoTest.daoPackageName}.${daoTest.daoClassName};

@RunWith(SpringRunner.class)
@SpringBootTest
public class ${daoTest.daoClassName}Test{
    @Autowired
    private ${daoTest.daoClassName} ${uncapFirst(daoTest.daoClassName)};

    @Test
    @Rollback(false)
    public void testCreate() {
        ${daoTest.modelClassName} ${uncapFirst(daoTest.modelClassName)} = new ${daoTest.modelClassName}();
        <#list daoTest.valueMappings as fieldName,value>
        ${uncapFirst(daoTest.modelClassName)}.set${capFirst(fieldName)}(${value});
        </#list>
        ${uncapFirst(daoTest.daoClassName)}.create(${uncapFirst(daoTest.modelClassName)});
    }


    @Test
    @Rollback(false)
    public void testCreates() {
        ${daoTest.modelClassName} ${uncapFirst(daoTest.modelClassName)}2 = new ${daoTest.modelClassName}();
        <#list daoTest.valueMappings as fieldName,value>
        ${uncapFirst(daoTest.modelClassName)}2.set${capFirst(fieldName)}(${value});
        </#list>

        ${daoTest.modelClassName} ${uncapFirst(daoTest.modelClassName)}3 = new ${daoTest.modelClassName}();
        <#list daoTest.valueMappings as fieldName,value>
        ${uncapFirst(daoTest.modelClassName)}3.set${capFirst(fieldName)}(${value});
        </#list>

        List<${daoTest.modelClassName}> ${uncapFirst(daoTest.modelClassName)}s = new ArrayList<>();
        ${uncapFirst(daoTest.modelClassName)}s.add(${uncapFirst(daoTest.modelClassName)}2);
        ${uncapFirst(daoTest.modelClassName)}s.add(${uncapFirst(daoTest.modelClassName)}3);
        ${uncapFirst(daoTest.daoClassName)}.creates(${uncapFirst(daoTest.modelClassName)}s);
    }

    @Test
    @Rollback(false)
    public void testLoad() {
        System.out.println(${uncapFirst(daoTest.daoClassName)}.load(1L));
    }


    @Test
    @Rollback(false)
    public void testLoads() {
        List<Long> ids = new ArrayList<>();
        ids.add(2L);
        ids.add(3L);
        ${uncapFirst(daoTest.daoClassName)}.loads(ids).forEach(e->System.out.println(e));
    }

    @Test
    @Rollback(false)
    public void testDelete() {
        ${uncapFirst(daoTest.daoClassName)}.delete(4L);
    }

    @Test
    @Rollback(false)
    public void testDeletes() {
        List<Long> ids = new ArrayList<>();
        ids.add(2L);
        ids.add(3L);
        ${uncapFirst(daoTest.daoClassName)}.deletes(ids);
    }

    @Test
    @Rollback(false)
    public void testUpdate() {
        ${daoTest.modelClassName} ${uncapFirst(daoTest.modelClassName)} = new ${daoTest.modelClassName}();
        <#list daoTest.valueMappings as fieldName,value>
        ${uncapFirst(daoTest.modelClassName)}.set${capFirst(fieldName)}(${value});
        </#list>
        ${uncapFirst(daoTest.daoClassName)}.update(${uncapFirst(daoTest.modelClassName)});
    }

    @Test
    @Rollback(false)
    public void testPaging() {
        Map<String,Object> criteria = new HashMap<>();
        criteria.put("???",3L);
        Paging<${daoTest.modelClassName}> paging = ${uncapFirst(daoTest.daoClassName)}.paging(0, 5, criteria);
        paging.getData().forEach(e->System.out.println(e));
    }

    @Test
    @Rollback(false)
    public void testCount() {
        Map<String,Object> criteria = new HashMap<>();
        criteria.put("???",233L);
        Long count = ${uncapFirst(daoTest.daoClassName)}.count(criteria);
        System.out.println(count);
    }

}
