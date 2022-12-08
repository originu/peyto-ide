package peyto.ide.dao.api;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import peyto.ide.dto.MessageChannelDto;

@Mapper
@Repository
public interface MessageChannelsDao {

	int add(MessageChannelDto dto);
	List<MessageChannelDto> getItemsByApplicationId(@Param("applicationId") long applicationId);
	int update(MessageChannelDto dto);
	int delete(@Param("messageChannelId") long messageChannelId);
}
