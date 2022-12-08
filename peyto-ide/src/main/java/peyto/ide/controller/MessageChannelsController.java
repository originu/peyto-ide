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
import org.springframework.web.bind.annotation.RequestParam;

import peyto.ide.dao.api.MessageChannelsDao;
import peyto.ide.data.AddMessageChannelData;
import peyto.ide.data.IDData;
import peyto.ide.data.ResData;
import peyto.ide.data.UpdateMessageChannelData;
import peyto.ide.dto.MessageChannelDto;

@Controller
@RequestMapping("/api/message-channel")
public class MessageChannelsController {

	@Autowired
	private MessageChannelsDao messageChannelsDao;

	@PostMapping("")
	public ResponseEntity<ResData<IDData>> add(@RequestBody AddMessageChannelData data) {
		MessageChannelDto dto = new MessageChannelDto();
		dto.setMessageChannelName(data.getMessageChannelName());
		dto.setMessageChannelDescription(data.getMessageChannelDescription());
		messageChannelsDao.add(dto);
		IDData idData = new IDData();
		idData.setId(dto.getApplicationId());
		return new ResponseEntity<>(ResData.success(idData), HttpStatus.OK);
	}

	@GetMapping("")
	public ResponseEntity<ResData<List<MessageChannelDto>>> getItemsByApplications(@RequestParam("applicationId") long applicationId) {
		List<MessageChannelDto> items = messageChannelsDao.getItemsByApplicationId(applicationId);
		return new ResponseEntity<>(ResData.success(items), HttpStatus.OK);
	}

	@PutMapping("/{messageChannelId}")
	public ResponseEntity<ResData<IDData>> update(
			@PathVariable("messageChannelId") long messageChannelId,
			@RequestBody UpdateMessageChannelData data) {
		MessageChannelDto dto = new MessageChannelDto();
		dto.setApplicationId(data.getApplicatoinId());
		dto.setMessageChannelName(data.getMessageChannelName());
		dto.setMessageChannelDescription(data.getMessageChannelDescription());
		messageChannelsDao.update(dto);
		IDData idData= new IDData();
		idData.setId(data.getApplicatoinId());
		return new ResponseEntity<>(ResData.success(idData), HttpStatus.OK);
	}

	@DeleteMapping("/{messageChannelId}")
	public ResponseEntity<ResData<IDData>> delete(
			@PathVariable("messageChannelId") long messageChannelId) {
		messageChannelsDao.delete(messageChannelId);
		IDData idData = new IDData();
		idData.setId(messageChannelId);
		return new ResponseEntity<>(ResData.success(idData), HttpStatus.OK);
	}

}