package com.macro.mall.dao;

import com.macro.mall.model.UmsPermission;
import com.macro.mall.model.UmsRolePermissionRelation;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @ClassName UmsRolePermissionRelationDao
 * @Description 自定义角色权限关系管理dao
 * @Author AW
 * @Date 2020/8/6 000623:08
 * @Version V1.0
 **/
public interface UmsRolePermissionRelationDao {
    /**
     * 批量插入角色和权限关系
     * @param list
     * @return
     */
    int insertList(@Param("list") List<UmsRolePermissionRelation> list);

    /**
     *  根据角色Id获取权限
     * @param roleId
     * @return
     */
    List<UmsPermission> getPermissionList(@Param("roleId") Long roleId);

}
