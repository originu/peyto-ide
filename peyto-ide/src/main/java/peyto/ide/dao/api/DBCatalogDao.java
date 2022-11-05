package peyto.ide.dao.api;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import peyto.ide.dto.DBColumnDto;
import peyto.ide.dto.DBSchemaDto;
import peyto.ide.dto.DBTableDto;

@Mapper
@Repository
public interface DBCatalogDao {

	List<DBSchemaDto> getSchemas(@Param("catalogName") String catalogName);

	List<DBTableDto> getTables(
			@Param("tableCatalog") String tableCatalog, 
			@Param("tableSchema") String tableSchema);
	
	DBTableDto getTable(
			@Param("tableCatalog") String tableCatalog, 
			@Param("tableSchema") String tableSchema,
			@Param("tableName") String tableName);
	

	List<DBColumnDto> getColumns(
			@Param("catalogName") String catalogName, 
			@Param("schemaName") String schemaName,
			@Param("tableName") String tableName);
}
