package ${model.packageName};

import lombok.Data;
import java.io.Serializable;

import java.util.Date;
<#list model.importPackages as packageName>
import ${packageName};
</#list>

@Data
public class ${model.className} implements Serializable {

<#list model.fieldNames as item>
    /**
    *  ${model.fieldComments[item_index]}
    */
    private ${model.fieldTypes[item_index]} ${model.fieldNames[item_index]};

</#list>
}

