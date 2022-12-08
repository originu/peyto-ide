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

import peyto.ide.dao.api.ApplicationsDao;
import peyto.ide.data.AddApplicationData;
import peyto.ide.data.IDData;
import peyto.ide.data.ResData;
import peyto.ide.data.UpdateApplicationData;
import peyto.ide.dto.ApplicationDto;

@Controller
@RequestMapping("/api/application")
public class ApplicationsController {

	@Autowired
	private ApplicationsDao applicationDao;

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