package ${packageName};

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 
 * @author 
 *
 */
public class ${className}Dto {

<#list tableColumns as tableColumn>
    private String ${tableColumn.columnName};
</#list>

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
