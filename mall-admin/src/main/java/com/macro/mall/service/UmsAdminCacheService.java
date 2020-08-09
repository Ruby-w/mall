package com.macro.mall.service;

import com.macro.mall.model.UmsAdmin;
import com.macro.mall.model.UmsResource;

import java.util.List;

/**
 * @ClassName UmsAdminCacheService
 * @Description 后台用户缓存操作类
 * @Author AW
 * @Date 2020/8/8 000822:23
 * @Version V1.0
 **/
public interface UmsAdminCacheService {
    /**
     *  根据用户Id删除后台用户缓存
     * @param adminId
     */
   void deleteAdmin(Long adminId);

    /**
     * 删除后台用户资源列表缓存
     * @param adminId
     */
  void deleteResourceList(Long adminId);

    /**
     * 当角色相关资源信息改变时删除相关后台用户缓存
     * @param roleId
     */
  void deleteResourceListByRole(Long roleId);

    /**
     * 当角色相关资源信息改变时删除相关后台用户缓存
     * @param roleIds
     */
 void deleteResourceListByRoleIds(List<Long> roleIds);

    /**
     * 当资源信息改变时，删除资源项目后台用户缓存
     * @param resource
     */
 void deleteResourceListByResource(Long resource);

    /**
     * 获取缓存后台用户信息
     * @param username
     * @return
     */
    UmsAdmin getAdmin(String username);

    /**
     *  设置缓存后台用户信息
     * @param umsAdmin
     */
  void setAdmin(UmsAdmin umsAdmin);

    /**
     * 获取缓存后台用户资源列表
     * @param adminId
     * @return
     */
  List<UmsResource> getResourceList(Long adminId);

    /**
     * 设置后台用户资源列表
     * @param adminId
     * @param resourceList
     */
  void setResourceList(Long adminId,List<UmsResource> resourceList);
}