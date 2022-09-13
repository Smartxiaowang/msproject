package bs.modules.student.service.impl;

import bs.common.utils.BaseServiceImpl;
import bs.common.utils.Constant;
import bs.common.utils.ConvertUtils;
import bs.common.utils.PageData;
import bs.modules.student.dao.OwnerDao;
import bs.modules.student.entity.OwnerEntity;
import bs.modules.student.service.OwnerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ClassName OwnerServiceImpl
 * @Description TODO
 * @Author Dear lin
 * @Date 17:21 2022/7/29
 * @Version 1.0
 **/
@Service
public class OwnerServiceImpl extends BaseServiceImpl<OwnerDao, OwnerEntity> implements OwnerService {
    @Autowired
    private OwnerDao ownerDao;

    @Override
    public PageData<OwnerEntity> page(Map<String, Object> params) {
        IPage<OwnerEntity> page = baseDao.selectPage(
                getPage(params, Constant.CREATE_DATE, false),null
        );
        return getPageData(page, OwnerEntity.class);
    }
    protected <T> PageData<T> getPageData(List<?> list, long total, Class<T> target){
        List<T> targetList = ConvertUtils.sourceToTarget(list, target);

        return new PageData<>(targetList, total);
    }

    protected <T> PageData<T> getPageData(IPage page, Class<T> target){
        return getPageData(page.getRecords(), page.getTotal(), target);
    }
    private QueryWrapper<OwnerEntity> getWrapper(Map<String, Object> params){
        QueryWrapper<OwnerEntity> wrapper = new QueryWrapper<>();
        return wrapper;
    }
}
