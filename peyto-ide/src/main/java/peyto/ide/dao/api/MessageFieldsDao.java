package peyto.ide.dao.api;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import peyto.ide.dto.MessageDto;
import peyto.ide.dto.MessageFieldDto;

import java.util.List;

@Mapper
@Repository
public interface MessageFieldDao {

	int add(List<MessageFieldDto> items);
	List<MessageFieldDto> getItemsByMessageId(@Param("messageId") long messageId);
	int deleteByMessageId(@Param("messageId") long messageId);
}
