package com.macro.mall.service;

import com.macro.mall.model.UmsMenu;
import com.macro.mall.model.UmsPermission;
import com.macro.mall.model.UmsResource;
import com.macro.mall.model.UmsRole;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName UmsRoleService
 * @Description 后台角色管理Service
 * @Author AW
 * @Date 2020/8/10 001022:16
 * @Version V1.0
 **/
public interface UmsRoleService {
    /**
     * 添加角色
     *
     * @param role
     * @return
     */
    int create(UmsRole role);

    /**
     * 修改角色信息
     *
     * @param id
     * @param role
     * @return
     */
    int update(Long id, UmsRole role);

    /**
     * 批量删除角色
     *
     * @param ids
     * @return
     */
    int delete(List<Long> ids);

    /**
     * 获取指定角色权限
     *
     * @param roleId
     * @return
     */
    List<UmsPermission> getPermissionList(Long roleId);

    /**
     * 修改指定角色的权限
     *
     * @param roleId
     * @param permissionIds
     * @return
     */
    @Transactional
    int updatePermission(Long roleId, List<Long> permissionIds);

    /**
     * 获取所有角色列表
     *
     * @return
     */
    List<UmsRole> list();

    /**
     * 分页获取角色列表
     *
     * @param keyword
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<UmsRole> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 根绝角色Id获取对应的菜单
     *
     * @param adminId
     * @return
     */
    List<UmsMenu> getMenuList(Long adminId);

    /**
     * @param roleId
     * @return
     */
    List<UmsMenu> listMenu(Long roleId);

    /**
     * 获取角色相关资源
     *
     * @param roleId
     * @return
     */
    List<UmsResource> listResource(Long roleId);

    /**
     * 给角色分配菜单
     *
     * @param roleId
     * @param menuIds
     * @return
     */
    @Transactional
    int allocMenu(Long roleId, List<Long> menuIds);

    /**
     * 给角色分配资源
     *
     * @param roleId
     * @param resourceIds
     * @return
     */
    @Transactional
    int allocResource(Long roleId, List<Long> resourceIds);
}
