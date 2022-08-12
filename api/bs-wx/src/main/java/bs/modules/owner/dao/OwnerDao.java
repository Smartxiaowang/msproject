package bs.modules.owner.dao;

import bs.common.dao.BaseDao;
import bs.modules.owner.entity.OwnerEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OwnerDao extends BaseDao<OwnerEntity> {
}
