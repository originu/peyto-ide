package peyto.ide.controller;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import peyto.ide.dao.api.DBInfoDao;
import peyto.ide.data.ResData;

@Controller
@RequestMapping("/api")
public class HealthController {

	@Autowired
	private DBInfoDao dbInfoDao;

	@GetMapping("/health")
	public ResponseEntity<ResData<ObjectNode>> getHealth() {
		ObjectNode jsonNodes = JsonNodeFactory.instance.objectNode();
		jsonNodes.put("version", "0.1.0");
		return new ResponseEntity<>(ResData.success(jsonNodes), HttpStatus.OK);
	}

	@GetMapping("/health/db")
	public ResponseEntity<ResData<ObjectNode>> getDBHealth() {
		ObjectNode jsonNodes = JsonNodeFactory.instance.objectNode();
		jsonNodes.put("version", dbInfoDao.getVersion());
		return new ResponseEntity<>(ResData.success(jsonNodes), HttpStatus.OK);
	}

}
