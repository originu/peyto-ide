package peyto.ide.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Controller
public class DBCatalogController {

	@GetMapping("/db/catalog")
	public ResponseEntity<ObjectNode> getSchema() {
		ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
		objectNode.put("name", "kevin");
		return new ResponseEntity<ObjectNode>(objectNode, HttpStatus.OK);
	}
}
