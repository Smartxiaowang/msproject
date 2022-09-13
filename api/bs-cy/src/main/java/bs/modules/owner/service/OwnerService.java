package bs.modules.owner.service;

import bs.common.utils.BaseService;
import bs.common.utils.PageData;
import bs.modules.owner.entity.OwnerEntity;

import java.util.Map;

public interface OwnerService extends BaseService<OwnerEntity> {
    PageData<OwnerEntity> page(Map<String, Object> params);
}
