package peyto.ide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import peyto.ide.dao.api.MessageDao;
import peyto.ide.data.*;
import peyto.ide.dto.*;

import java.util.List;

@Controller
@RequestMapping("/api/message")
public class MessageController {

    @Autowired
    private MessageDao messageDao;

    @PostMapping("")
    public ResponseEntity<ResData<IDData>> add(@RequestBody AddMessageData data) {
        MessageDto dto = new MessageDto();
        dto.setApplicationId(data.getApplicationId());
        dto.setMessageName(data.getMessageName());
        dto.setMessageDescription(data.getMessageDescription());
        messageDao.add(dto);
        IDData idData = new IDData();
        idData.setId(dto.getApplicationId());
        return new ResponseEntity<>(ResData.success(idData), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<ResData<List<MessageDto>>> getItemsByApplicationId(@RequestParam("applicationId") long applicationId) {
        List<MessageDto> items = messageDao.getItemsByApplicationId(applicationId);
        return new ResponseEntity<>(ResData.success(items), HttpStatus.OK);
    }

    @PutMapping("/{messageId}")
    public ResponseEntity<ResData<IDData>> update(
            @PathVariable("messageId") long messageId,
            @RequestBody UpdateMessageData data) {
        MessageDto dto = new MessageDto();
        dto.setMessageId(data.getMessageId());
        dto.setApplicationId(data.getApplicationId());
        dto.setMessageName(data.getMessageName());
        dto.setMessageDescription(data.getMessageDescription());
        messageDao.update(dto);
        IDData idData = new IDData();
        idData.setId(data.getMessageId());
        return new ResponseEntity<>(ResData.success(idData), HttpStatus.OK);
    }

    @DeleteMapping("/{messageId}")
    public ResponseEntity<ResData<IDData>> delete(
            @PathVariable("messageId") long messageId) {
        messageDao.delete(messageId);
        IDData idData = new IDData();
        idData.setId(messageId);
        return new ResponseEntity<>(ResData.success(idData), HttpStatus.OK);
    }

}