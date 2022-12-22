package peyto.ide.dao.api;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import peyto.ide.dto.TemplateCategoryDto;
import peyto.ide.dto.TemplateDto;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface TemplatesDao {
	int add(TemplateDto dto);
	Optional<Integer> getCountByCategoryCode(@Param("categoryCode") String categoryCode);
	TemplateDto getItem(@Param("id") long id);
	List<TemplateDto> getItemsByCategoryCode(@Param("categoryCode") String categoryCode);
	int update(TemplateDto dto);
	int delete(@Param("id") long id);
}