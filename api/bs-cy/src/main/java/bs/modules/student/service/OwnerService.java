package bs.modules.student.service;

import bs.common.utils.BaseService;
import bs.common.utils.PageData;
import bs.modules.student.entity.OwnerEntity;

import java.util.Map;

public interface OwnerService extends BaseService<OwnerEntity> {
    PageData<OwnerEntity> page(Map<String, Object> params);
}
