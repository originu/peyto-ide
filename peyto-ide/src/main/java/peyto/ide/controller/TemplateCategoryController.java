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
import peyto.ide.dto.IDInfo;
import peyto.ide.dto.TemplateCategoryDto;

@Controller
@RequestMapping("/api/template-category")
public class TemplateCategoryController {

	@Autowired
	private TemplateCategoryDao dao;
	
	@PostMapping("/level1")
	public ResponseEntity<ResData<IDInfo>> addLevel1(@RequestBody String mm) {
		IDInfo dto = new IDInfo();
		return new ResponseEntity<ResData<IDInfo>>(ResData.success(dto), HttpStatus.OK);
	}
	
	@GetMapping("/level1")
	public ResponseEntity<ResData<List<TemplateCategoryDto>>> getLevel1List() {
		List<TemplateCategoryDto> listOfLevel1 = dao.getListOfLevel1();
		return new ResponseEntity<ResData<List<TemplateCategoryDto>>>(ResData.success(listOfLevel1), HttpStatus.OK);
	}

	@PutMapping("/level1/{level1Id}")
	public ResponseEntity<ResData<IDInfo>> updateLevel1(@PathVariable("levelId1") int levelId1) {
		IDInfo dto = new IDInfo();
		return new ResponseEntity<ResData<IDInfo>>(ResData.success(dto), HttpStatus.OK);
	}
	
	@DeleteMapping("/level1/{level1Id}")
	public ResponseEntity<ResData<IDInfo>> removeLevel1(@PathVariable("levelId1") int levelId1) {
		IDInfo dto = new IDInfo();
		return new ResponseEntity<ResData<IDInfo>>(ResData.success(dto), HttpStatus.OK);
	}
	
	// 

	@PostMapping("/level1/{level1Id}/level2")
	public ResponseEntity<ResData<IDInfo>> addLevel2(@RequestBody ObjectNode req) {
		IDInfo dto = new IDInfo();
		return new ResponseEntity<ResData<IDInfo>>(ResData.success(dto), HttpStatus.OK);
	}
	
	@GetMapping("/level1/{level1Id}/level2")
	public ResponseEntity<ResData<List<TemplateCategoryDto>>> getLevel2List(@PathVariable("level1Id") int levelId1) {
		List<TemplateCategoryDto> listOfLevel2 = dao.getListOfLevel2(levelId1);
		return new ResponseEntity<ResData<List<TemplateCategoryDto>>>(ResData.success(listOfLevel2), HttpStatus.OK);
	}

	@PutMapping("/level1/{level1Id}/level2/{level2Id}")
	public ResponseEntity<ResData<IDInfo>> updateLevel2(@PathVariable("levelId1") int levelId1, @PathVariable("level2Id") int levelId2) {
		IDInfo dto = new IDInfo();
		return new ResponseEntity<ResData<IDInfo>>(ResData.success(dto), HttpStatus.OK);
	}
	
	@DeleteMapping("/level1/{level1Id}/level2/{level2Id}")
	public ResponseEntity<ResData<IDInfo>> removeLevel2(@PathVariable("levelId1") int levelId1, @PathVariable("level2Id") int levelId2) {
		IDInfo dto = new IDInfo();
		return new ResponseEntity<ResData<IDInfo>>(ResData.success(dto), HttpStatus.OK);
	}

}