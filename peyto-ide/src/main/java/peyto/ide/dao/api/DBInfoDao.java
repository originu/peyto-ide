package peyto.ide.dao.api;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DBInfoDao {

    String getVersion();
}
