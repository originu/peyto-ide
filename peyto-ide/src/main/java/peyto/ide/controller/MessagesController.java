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

import peyto.ide.dao.api.MessagesDao;
import peyto.ide.data.AddMessageData;
import peyto.ide.data.IDData;
import peyto.ide.data.ResData;
import peyto.ide.data.UpdateMessageData;
import peyto.ide.dto.MessageDto;

@Controller
@RequestMapping("/api/message")
public class MessagesController {

    @Autowired
    private MessagesDao messageDao;

    @PostMapping("")
    public ResponseEntity<ResData<IDData>> add(@RequestBody AddMessageData data) {
        MessageDto dto = new MessageDto();
        dto.setMessageChannelId(data.getMessageChannelId());
        dto.setMessageName(data.getMessageName());
        dto.setMessageDescription(data.getMessageDescription());
        messageDao.add(dto);
        IDData idData = new IDData();
        idData.setId(dto.getMessageChannelId());
        return new ResponseEntity<>(ResData.success(idData), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<ResData<List<MessageDto>>> getItemsByApplicationId(@RequestParam("messageChannelId") long messageChannelId) {
        List<MessageDto> items = messageDao.getItemsByMessageChannelId(messageChannelId);
        return new ResponseEntity<>(ResData.success(items), HttpStatus.OK);
    }

    @PutMapping("/{messageId}")
    public ResponseEntity<ResData<IDData>> update(
            @PathVariable("messageId") long messageId,
            @RequestBody UpdateMessageData data) {
        MessageDto dto = new MessageDto();
        dto.setMessageId(data.getMessageId());
        dto.setMessageChannelId(data.getMessageChannelId());
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