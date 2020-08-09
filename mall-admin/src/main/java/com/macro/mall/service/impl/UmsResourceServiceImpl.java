package com.macro.mall.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.macro.mall.mapper.UmsResourceMapper;
import com.macro.mall.model.UmsResource;
import com.macro.mall.model.UmsResourceExample;
import com.macro.mall.service.UmsAdminCacheService;
import com.macro.mall.service.UmsResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName UmsResourceService
 * @Description 后台资源管理Service实现类
 * @Author AW
 * @Date 2020/8/9 000923:11
 * @Version V1.0
 **/
@Service
public class UmsResourceServiceImpl implements UmsResourceService {
    @Autowired
    private UmsResourceMapper resourceMapper;
    @Autowired
    private UmsAdminCacheService adminCacheService;

    @Override
    public int create(UmsResource resource) {
        resource.setCreateTime(new Date());
        return resourceMapper.insert(resource);
    }


    @Override
    public int update(Long id, UmsResource resource) {
        resource.setId(id);
        int count = resourceMapper.updateByPrimaryKey(resource);
        adminCacheService.deleteResourceListByResource(id);
        return count;
    }

    @Override
    public UmsResource getItem(Long id) {

        return resourceMapper.selectByPrimaryKey(id);
    }

    @Override
    public int delete(Long id) {
        int count = resourceMapper.deleteByPrimaryKey(id);
        adminCacheService.deleteResourceListByResource(id);
        return count;
    }

    @Override
    public List<UmsResource> list(Long categoryId, String nameKeyWord, String urlKeyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        UmsResourceExample example = new UmsResourceExample();
        UmsResourceExample.Criteria criteria = example.createCriteria();
        if (categoryId != null) {
            criteria.andCategoryIdEqualTo(categoryId);
        }
        if (StrUtil.isNotEmpty(nameKeyWord)) {
            criteria.andNameLike('%' + nameKeyWord + '%');
        }
        if (StrUtil.isNotEmpty(urlKeyword)) {
            criteria.andUrlLike('%' + nameKeyWord + '%');
        }
        return resourceMapper.selectByExample(example);
    }

    @Override
    public List<UmsResource> listAll() {
        return resourceMapper.selectByExample(new UmsResourceExample());
    }
}
