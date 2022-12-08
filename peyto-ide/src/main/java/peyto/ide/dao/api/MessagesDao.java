package peyto.ide.dao.api;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import peyto.ide.dto.MessageDto;

import java.util.List;

@Mapper
@Repository
public interface MessageDao {

	int add(MessageDto dto);
	List<MessageDto> getItemsByApplicationId(@Param("applicationId") long applicationId);
	int update(MessageDto dto);
	int delete(@Param("messageId") long messageId);
}
