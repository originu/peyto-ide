package peyto.ide.dao.api;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import peyto.ide.dto.MessageFieldDto;

@Mapper
@Repository
public interface MessageFieldsDao {

	int add(List<MessageFieldDto> items);
	List<MessageFieldDto> getItemsByMessageIdAndMessageFieldType(@Param("messageId") long messageId, @Param("messageFieldType") int messageFieldType);
	int deleteByMessageId(@Param("messageId") long messageId);
}
