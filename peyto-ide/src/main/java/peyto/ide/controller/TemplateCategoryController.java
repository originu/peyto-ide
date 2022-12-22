package peyto.ide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import peyto.ide.dao.api.TemplateCategoriesDao;
import peyto.ide.data.IDData;
import peyto.ide.data.ResData;
import peyto.ide.dto.TemplateCategoryDto;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/api/template-category")
public class TemplateCategoryController {

	@Autowired
	private TemplateCategoriesDao dao;
	
	@PostMapping("")
	public ResponseEntity<ResData<IDData>> add(@RequestBody TemplateCategoryDto dto) {
		dto.setCreatedBy("kevin");
		dto.setCreatedDate(new Date());
		dao.add(dto);
		return new ResponseEntity<>(ResData.success(new IDData(dto.getId())), HttpStatus.OK);
	}

	@GetMapping("")
	public ResponseEntity<ResData<List<TemplateCategoryDto>>> getItems() {
		List<TemplateCategoryDto> items = dao.getItems();
		return new ResponseEntity<>(ResData.success(items), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResData<TemplateCategoryDto>> getItem(@PathVariable("id") long id) {
		TemplateCategoryDto item = dao.getItem(id);
		return new ResponseEntity<>(ResData.success(item), HttpStatus.OK);
	}

	@GetMapping("/categoryCode")
	public ResponseEntity<ResData<TemplateCategoryDto>> getItemByCategoryCode(@RequestParam("categoryCode") String categoryCode) {
		TemplateCategoryDto item = dao.getItemByCategoryCode(categoryCode);
		return new ResponseEntity<>(ResData.success(item), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ResData<IDData>> update(@PathVariable("id") long id, @RequestBody TemplateCategoryDto dto) {
		dto.setUpdatedBy("kevin");
		dto.setUpdatedDate(new Date());
		dao.update(dto);
		return new ResponseEntity<>(ResData.success(new IDData(dto.getId())), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResData<IDData>> remove(@PathVariable("id") long id) {
		dao.delete(id);
		return new ResponseEntity<>(ResData.success(new IDData(id)), HttpStatus.OK);
	}
	
}