package peyto.ide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import peyto.ide.dao.api.ApplicationDao;
import peyto.ide.data.AddApplicationData;
import peyto.ide.data.IDData;
import peyto.ide.data.ResData;
import peyto.ide.data.UpdateApplicationData;
import peyto.ide.dto.*;

import java.util.List;

@Controller
@RequestMapping("/api/application")
public class ApplicationController {

	@Autowired
	private ApplicationDao applicationDao;

	@PostMapping("")
	public ResponseEntity<ResData<IDData>> add(@RequestBody AddApplicationData data) {
		ApplicationDto dto = new ApplicationDto();
		dto.setApplicationName(data.getApplicationName());
		dto.setApplicationDescription(data.getApplicationDescription());
		applicationDao.add(dto);
		IDData idData = new IDData();
		idData.setId(dto.getApplicationId());
		return new ResponseEntity<>(ResData.success(idData), HttpStatus.OK);
	}

	@GetMapping("")
	public ResponseEntity<ResData<List<ApplicationDto>>> getItems() {
		List<ApplicationDto> items = applicationDao.getItems();
		return new ResponseEntity<>(ResData.success(items), HttpStatus.OK);
	}

	@PutMapping("/{applicationId}")
	public ResponseEntity<ResData<IDData>> update(
			@PathVariable("applicationId") long applicationId,
			@RequestBody UpdateApplicationData data) {
		ApplicationDto dto = new ApplicationDto();
		dto.setApplicationId(data.getApplicatoinId());
		dto.setApplicationName(data.getApplicationName());
		dto.setApplicationDescription(data.getApplicationDescription());
		applicationDao.update(dto);
		IDData idData= new IDData();
		idData.setId(data.getApplicatoinId());
		return new ResponseEntity<>(ResData.success(idData), HttpStatus.OK);
	}

	@DeleteMapping("/{applicationId}")
	public ResponseEntity<ResData<IDData>> delete(
			@PathVariable("applicationId") long applicationId) {
		applicationDao.delete(applicationId);
		IDData idData = new IDData();
		idData.setId(applicationId);
		return new ResponseEntity<>(ResData.success(idData), HttpStatus.OK);
	}

}