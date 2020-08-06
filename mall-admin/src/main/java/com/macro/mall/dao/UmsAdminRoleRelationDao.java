package com.macro.mall.dao;

import com.macro.mall.model.UmsAdminRoleRelation;
import com.macro.mall.model.UmsPermission;
import com.macro.mall.model.UmsResource;
import com.macro.mall.model.UmsRole;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @ClassName UmsAdminRoleRelationDao
 * @Description 自定义后台用户与角色管理
 * @Author AW
 * @Date 2020/8/6 000621:38
 * @Version V1.0
 **/
public interface UmsAdminRoleRelationDao {
    /**
     * 批量插入用户角色关系
     *
     * @param adminRoleRelationsList
     * @return
     */
    int insertList(@Param("list") List<UmsAdminRoleRelation> adminRoleRelationsList);

    /**
     * 获取用于所有的角色
     * @param adminId
     * @return
     */
    List<UmsRole> getRoleList(@Param("adminId") Long adminId);

    /**
     * 获取用户所有角色权限
     * @param adminId
     * @return
     */
    List<UmsPermission> getRolePermissionList(@Param("adminId") Long adminId);

    /**
     * 获取用户所有权限(包括+-权限)
     * @param adminId
     * @return
     */
   List<UmsPermission> getPermissionList(@Param("adminId")Long adminId);

    /**
     * 获取用户所有可访问资源
     * @param adminId
     * @return
     */
  List<UmsResource> getResourceList(@Param("adminId")Long adminId);

    /**
     * 获取资源相关用户Id列表
     * @param resourceId
     * @return
     */
  List<Long> getAdminIdList(Long resourceId);
}
