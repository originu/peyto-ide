package peyto.ide.dao.api;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import peyto.ide.dto.MessageDto;

@Mapper
@Repository
public interface MessagesDao {

	int add(MessageDto dto);
	List<MessageDto> getItemsByMessageChannelId(@Param("messageChannelId") long messageChannelId);
	int update(MessageDto dto);
	int delete(@Param("messageId") long messageId);
}
