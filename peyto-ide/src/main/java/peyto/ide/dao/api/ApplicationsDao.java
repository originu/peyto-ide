package peyto.ide.dao.api;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import peyto.ide.dto.ApplicationDto;

import java.util.List;

@Mapper
@Repository
public interface ApplicationDao {

	int add(ApplicationDto dto);
	List<ApplicationDto> getItems();
	int update(ApplicationDto dto);
	int delete(@Param("applicationId") long applicationId);
}
