package com.macro.mall.dao;

import com.macro.mall.model.UmsMenu;
import com.macro.mall.model.UmsResource;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @ClassName UmsRoleDao
 * @Description 自定义后台角色管理dao
 * @Author AW
 * @Date 2020/8/6 000622:28
 * @Version V1.0
 **/
public interface UmsRoleDao {
    /**
     * 根据后台用户Id获取菜单
     * @param adminId
     * @return
     */
    List<UmsMenu> getMenuList(@Param("adminId") Long adminId);

    /**
     * 根据角色Id获取菜单
     * @param roleId
     * @return
     */
    List<UmsMenu> getMenuListByRoleId(@Param("roleId") Long roleId);

    /**
     * 根绝角色Id 获取资源
     * @param roleId
     * @return
     */
    List<UmsResource> getResourceListByRoleId(@Param("roleId")Long roleId);
}
