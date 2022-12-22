package peyto.ide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import peyto.ide.dao.api.TemplatesDao;
import peyto.ide.data.IDData;
import peyto.ide.data.ResData;
import peyto.ide.dto.TemplateDto;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/template")
public class TemplateController {

	@Autowired
	private TemplatesDao dao;
	
	@PostMapping("")
	public ResponseEntity<ResData<IDData>> add(@RequestBody TemplateDto dto) {
		Optional<Integer> countByCategoryCode = dao.getCountByCategoryCode(dto.getCategoryCode());
		dto.setRevision(countByCategoryCode.orElse(0) + 1);
		dto.setCreatedBy("kevin");
		dto.setCreatedDate(new Date());
		dao.add(dto);
		return new ResponseEntity<>(ResData.success(new IDData(dto.getId())), HttpStatus.OK);
	}

	@GetMapping("")
	public ResponseEntity<ResData<List<TemplateDto>>> getItemsByCategoryCode(@RequestParam("categoryCode") String categoryCode) {
		List<TemplateDto> items = dao.getItemsByCategoryCode(categoryCode);
		return new ResponseEntity<>(ResData.success(items), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResData<TemplateDto>> getItem(@PathVariable("id") long id) {
		TemplateDto item = dao.getItem(id);
		return new ResponseEntity<>(ResData.success(item), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ResData<IDData>> update(@PathVariable("id") long id, @RequestBody TemplateDto dto) {
		dto.setUpdatedBy("kevin");
		dto.setUpdatedDate(new Date());
		dao.update(dto);
		return new ResponseEntity<ResData<IDData>>(ResData.success(new IDData(dto.getId())), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResData<IDData>> remove(@PathVariable("id") long id) {
		dao.delete(id);
		return new ResponseEntity<ResData<IDData>>(ResData.success(new IDData(id)), HttpStatus.OK);
	}
	
}