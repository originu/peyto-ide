package peyto.ide.dao.api;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import peyto.ide.dto.TemplateCategoryDto;

@Mapper
@Repository
public interface TemplateCategoryDao {

	int addLevel1(TemplateCategoryDto dto);
	List<TemplateCategoryDto> getListOfLevel1();
	int updateLevel1(TemplateCategoryDto dto);
	int removeLevel1(int level1Id);
	
	int addLevel2(TemplateCategoryDto dto);
	List<TemplateCategoryDto> getListOfLevel2(@Param("level1Id") long level1Id);
	int updateLevel2(TemplateCategoryDto dto);
	int removeLevel2(@Param("level1Id") long level1Id, @Param("level2Id") long level2Id);
	
}