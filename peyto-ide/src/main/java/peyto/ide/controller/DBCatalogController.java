package peyto.ide.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import peyto.ide.dao.api.DBCatalogDao;
import peyto.ide.dto.DBSchemaDto;

@Controller
public class DBCatalogController {

	@Autowired
	private DBCatalogDao dbCatalogDao;
	
	@GetMapping("/db/catalog")
	public ResponseEntity<ObjectNode> getSchema() {
		
		List<DBSchemaDto> schemas = dbCatalogDao.getSchemas("postgres");
		
		ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
		objectNode.put("name", "kevin");
		return new ResponseEntity<ObjectNode>(objectNode, HttpStatus.OK);
	}
}
