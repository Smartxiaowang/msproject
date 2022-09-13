package bs.modules.student.dao;

import bs.common.dao.BaseDao;
import bs.modules.student.entity.OwnerEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OwnerDao extends BaseDao<OwnerEntity> {
}
