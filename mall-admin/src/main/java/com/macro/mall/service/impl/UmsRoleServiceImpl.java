package com.macro.mall.service.impl;

import com.macro.mall.model.UmsMenu;
import com.macro.mall.model.UmsPermission;
import com.macro.mall.model.UmsResource;
import com.macro.mall.model.UmsRole;
import com.macro.mall.service.UmsRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName UmsRoleServiceImpl
 * @Description
 * @Author AW
 * @Date 2020/8/10 001022:28
 * @Version V1.0
 **/
@Service
public class UmsRoleServiceImpl implements UmsRoleService {
    @Override
    public int create(UmsRole role) {
        return 0;
    }

    @Override
    public int update(Long id, UmsRole role) {
        return 0;
    }

    @Override
    public int delete(List<Long> ids) {
        return 0;
    }

    @Override
    public List<UmsPermission> getPermissionList(Long roleId) {
        return null;
    }

    @Override
    public int updatePermission(Long roleId, List<Long> permissionIds) {
        return 0;
    }

    @Override
    public List<UmsRole> list() {
        return null;
    }

    @Override
    public List<UmsRole> list(String keyword, Integer pageSize, Integer pageNum) {
        return null;
    }

    @Override
    public List<UmsMenu> getMenuList(Long adminId) {
        return null;
    }

    @Override
    public List<UmsMenu> listMenu(Long roleId) {
        return null;
    }

    @Override
    public List<UmsResource> listResource(Long roleId) {
        return null;
    }

    @Override
    public int allocMenu(Long roleId, List<Long> menuIds) {
        return 0;
    }

    @Override
    public int allocResource(Long roleId, List<Long> resourceIds) {
        return 0;
    }
}
