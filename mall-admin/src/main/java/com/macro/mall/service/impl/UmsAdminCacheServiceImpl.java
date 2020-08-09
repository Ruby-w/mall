package com.macro.mall.service.impl;

import com.macro.mall.model.UmsAdmin;
import com.macro.mall.model.UmsResource;
import com.macro.mall.security.service.RedisService;
import com.macro.mall.service.UmsAdminCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UmsAdminCacheServiceImpl
 * @Description
 * @Author AW
 * @Date 2020/8/8 000822:32
 * @Version V1.0
 **/
@Service
public class UmsAdminCacheServiceImpl implements UmsAdminCacheService {

    @Autowired
    private RedisService redisService;


    @Override
    public void deleteAdmin(Long adminId) {

    }

    @Override
    public void deleteResourceList(Long adminId) {

    }

    @Override
    public void deleteResourceListByRole(Long roleId) {

    }

    @Override
    public void deleteResourceListByRoleIds(List<Long> roleIds) {

    }

    @Override
    public void deleteResourceListByResource(Long resource) {

    }

    @Override
    public UmsAdmin getAdmin(String username) {
        return null;
    }

    @Override
    public void setAdmin(UmsAdmin umsAdmin) {

    }

    @Override
    public List<UmsResource> getResourceList(Long adminId) {
        return null;
    }

    @Override
    public void setResourceList(Long adminId, List<UmsResource> resourceList) {

    }
}
