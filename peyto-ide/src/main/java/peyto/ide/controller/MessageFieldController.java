package peyto.ide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import peyto.ide.dao.api.MessageFieldDao;
import peyto.ide.data.AddMessageFieldData;
import peyto.ide.data.ResData;
import peyto.ide.data.IDData;
import peyto.ide.dto.MessageFieldDto;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/message")
public class MessageFieldController {

    @Autowired
    private MessageFieldDao messageFieldDao;

    @PostMapping("/{messageId}/messagefield")
    public ResponseEntity<ResData<List<IDData>>> add(@RequestBody List<AddMessageFieldData> items) {
        List<MessageFieldDto> _items = items.stream().map((data) -> {
            MessageFieldDto dto = new MessageFieldDto();
            dto.setMessageId(data.getMessageId());
            dto.setMessageFieldOrder(data.getMessageFieldOrder());
            dto.setMessageFieldName(data.getMessageFieldName());
            dto.setMessageFieldDescription(data.getMessageFieldDescription());
            dto.setMessageFieldDataType(data.getMessageFieldDataType());
            dto.setMessageFieldLength(data.getMessageFieldLength());
            return dto;
        }).collect(Collectors.toList());
        messageFieldDao.add(_items);
        List<IDData> results = _items.stream().map((dto) -> {
            IDData idData = new IDData();
            idData.setId(dto.getMessageFieldId());
            return idData;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(ResData.success(results), HttpStatus.OK);
    }

    @GetMapping("/{messageId}/messagefield")
    public ResponseEntity<ResData<List<MessageFieldDto>>> getItemsByMessageId(@PathVariable("messageId") long messageId) {
        List<MessageFieldDto> items = messageFieldDao.getItemsByMessageId(messageId);
        return new ResponseEntity<>(ResData.success(items), HttpStatus.OK);
    }

    @DeleteMapping("/{messageId}/messagefield")
    public ResponseEntity<ResData<IDData>> deleteByMessageId(@PathVariable("messageId") long messageId) {
        messageFieldDao.deleteByMessageId(messageId);
        IDData idData = new IDData();
        idData.setId(messageId);
        return new ResponseEntity<>(ResData.success(idData), HttpStatus.OK);
    }

}