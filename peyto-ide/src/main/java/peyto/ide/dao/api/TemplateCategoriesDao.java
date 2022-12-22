package peyto.ide.dao.api;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import peyto.ide.dto.TemplateCategoryDto;

@Mapper
@Repository
public interface TemplateCategoriesDao {

	int add(TemplateCategoryDto dto);
	List<TemplateCategoryDto> getItems();
	TemplateCategoryDto getItem(@Param("id") long id);
	TemplateCategoryDto getItemByCategoryCode(@Param("categoryCode") String categoryCode);
	int update(TemplateCategoryDto dto);
	int delete(@Param("id") long id);

}