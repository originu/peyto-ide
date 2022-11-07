package peyto.ide.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import peyto.ide.dao.api.DBCatalogDao;
import peyto.ide.data.ResData;
import peyto.ide.dto.DBSchemaDto;
import peyto.ide.dto.DBTableDto;

@Controller
@RequestMapping("/api")
public class DBCatalogController {

	@Autowired
	private DBCatalogDao dbCatalogDao;

	@GetMapping("/db/catalog/{catalogName}/schema")
	public ResponseEntity<ResData<List<DBSchemaDto>>> getSchema(@PathVariable("catalogName") String catalogName) {
		List<DBSchemaDto> schemas = dbCatalogDao.getSchemas(catalogName);
		return new ResponseEntity<ResData<List<DBSchemaDto>>>(ResData.success(schemas), HttpStatus.OK);
	}
	
	@GetMapping("/db/catalog/{catalogName}/schema/{schemaName}")
	public ResponseEntity<ResData<List<DBTableDto>>> getTables(
			@PathVariable("catalogName") String catalogName,
			@PathVariable("schemaName") String schemaName ) {
		List<DBTableDto> tables = dbCatalogDao.getTables(catalogName, schemaName);
		return new ResponseEntity<ResData<List<DBTableDto>>>(ResData.success(tables), HttpStatus.OK);
	}

}
