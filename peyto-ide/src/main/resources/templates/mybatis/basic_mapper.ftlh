<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${namespace}">
    
    <select id="${selectId}" resultType="${selectResultType}">
        select
        <#list tableColumns as tableColumn>
            <#if tableColumn?counter == 1>
            ${tableColumn.columnName}    as ${tableColumn.columnName}  /* ${tableColumn.columnName} */ 
            <#else>
            , ${tableColumn.columnName}  as ${tableColumn.columnName}  /* ${tableColumn.columnName} */ 
            </#if>
        </#list>
        from ${tableName}
    </select>

</mapper>