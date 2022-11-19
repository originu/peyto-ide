package peyto.ide.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.databind.node.ObjectNode;

import peyto.ide.dao.api.TemplateCategoryDao;
import peyto.ide.data.ResData;
import peyto.ide.data.IDData;
import peyto.ide.dto.TemplateCategoryDto;

@Controller
@RequestMapping("/api/template-category")
public class TemplateCategoryController {

	@Autowired
	private TemplateCategoryDao dao;
	
	@PostMapping("/level1")
	public ResponseEntity<ResData<IDData>> addLevel1(@RequestBody String mm) {
		IDData idData = new IDData();
		return new ResponseEntity<ResData<IDData>>(ResData.success(idData), HttpStatus.OK);
	}
	
	@GetMapping("/level1")
	public ResponseEntity<ResData<List<TemplateCategoryDto>>> getLevel1List() {
		List<TemplateCategoryDto> listOfLevel1 = dao.getListOfLevel1();
		return new ResponseEntity<ResData<List<TemplateCategoryDto>>>(ResData.success(listOfLevel1), HttpStatus.OK);
	}

	@PutMapping("/level1/{level1Id}")
	public ResponseEntity<ResData<IDData>> updateLevel1(@PathVariable("levelId1") int levelId1) {
		IDData idData = new IDData();
		return new ResponseEntity<ResData<IDData>>(ResData.success(idData), HttpStatus.OK);
	}
	
	@DeleteMapping("/level1/{level1Id}")
	public ResponseEntity<ResData<IDData>> removeLevel1(@PathVariable("levelId1") int levelId1) {
		IDData idData = new IDData();
		return new ResponseEntity<ResData<IDData>>(ResData.success(idData), HttpStatus.OK);
	}
	
	// 

	@PostMapping("/level1/{level1Id}/level2")
	public ResponseEntity<ResData<IDData>> addLevel2(@RequestBody ObjectNode req) {
		IDData idData = new IDData();
		return new ResponseEntity<ResData<IDData>>(ResData.success(idData), HttpStatus.OK);
	}
	
	@GetMapping("/level1/{level1Id}/level2")
	public ResponseEntity<ResData<List<TemplateCategoryDto>>> getLevel2List(@PathVariable("level1Id") int levelId1) {
		List<TemplateCategoryDto> listOfLevel2 = dao.getListOfLevel2(levelId1);
		return new ResponseEntity<ResData<List<TemplateCategoryDto>>>(ResData.success(listOfLevel2), HttpStatus.OK);
	}

	@PutMapping("/level1/{level1Id}/level2/{level2Id}")
	public ResponseEntity<ResData<IDData>> updateLevel2(@PathVariable("levelId1") int levelId1, @PathVariable("level2Id") int levelId2) {
		IDData idData = new IDData();
		return new ResponseEntity<ResData<IDData>>(ResData.success(idData), HttpStatus.OK);
	}
	
	@DeleteMapping("/level1/{level1Id}/level2/{level2Id}")
	public ResponseEntity<ResData<IDData>> removeLevel2(@PathVariable("levelId1") int levelId1, @PathVariable("level2Id") int levelId2) {
		IDData idData = new IDData();
		return new ResponseEntity<ResData<IDData>>(ResData.success(idData), HttpStatus.OK);
	}

}