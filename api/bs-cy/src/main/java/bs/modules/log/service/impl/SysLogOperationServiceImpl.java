/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package bs.modules.log.service.impl;

import bs.modules.log.entity.SysLogOperationEntity;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import bs.common.utils.BaseServiceImpl;
import bs.common.utils.Constant;
import bs.common.utils.ConvertUtils;
import bs.common.utils.PageData;
import bs.modules.log.dao.SysLogOperationDao;
import bs.modules.log.dto.SysLogOperationDTO;
import bs.modules.log.service.SysLogOperationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * 操作日志
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
@Service
public class SysLogOperationServiceImpl extends BaseServiceImpl<SysLogOperationDao, SysLogOperationEntity> implements SysLogOperationService {

    @Override
    public PageData<SysLogOperationDTO> page(Map<String, Object> params) {
        IPage<SysLogOperationEntity> page = baseDao.selectPage(
            getPage(params, Constant.CREATE_DATE, false),
            getWrapper(params)
        );

        return getPageData(page, SysLogOperationDTO.class);
    }

    @Override
    public List<SysLogOperationDTO> list(Map<String, Object> params) {
        List<SysLogOperationEntity> entityList = baseDao.selectList(getWrapper(params));

        return ConvertUtils.sourceToTarget(entityList, SysLogOperationDTO.class);
    }

    private QueryWrapper<SysLogOperationEntity> getWrapper(Map<String, Object> params){
        String status = (String) params.get("status");

        QueryWrapper<SysLogOperationEntity> wrapper = new QueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(status), "status", status);

        return wrapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(SysLogOperationEntity entity) {
        insert(entity);
        return false;
    }

    @Override
    public boolean saveBatch(Collection<SysLogOperationEntity> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<SysLogOperationEntity> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(SysLogOperationEntity entity) {
        return false;
    }

    @Override
    public SysLogOperationEntity getOne(Wrapper<SysLogOperationEntity> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<SysLogOperationEntity> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<SysLogOperationEntity> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<SysLogOperationEntity> getBaseMapper() {
        return null;
    }

    @Override
    public Class<SysLogOperationEntity> getEntityClass() {
        return null;
    }

}