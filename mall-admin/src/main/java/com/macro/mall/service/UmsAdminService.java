package com.macro.mall.service;

import com.macro.mall.dto.UmsAdminParam;
import com.macro.mall.dto.UpdateAdminPasswordParam;
import com.macro.mall.model.UmsAdmin;
import com.macro.mall.model.UmsPermission;
import com.macro.mall.model.UmsResource;
import com.macro.mall.model.UmsRole;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName UmsAdminService
 * @Description 后台管理员Service
 * @Author AW
 * @Date 2020/8/8 000822:33
 * @Version V1.0
 **/
public interface UmsAdminService {
    /**
     * 根据用户名获取后台管理员
     *
     * @param username
     * @return
     */
    UmsAdmin getAdminByUserName(String username);

    /**
     * 注册功能
     *
     * @param umsAdminParam
     * @return
     */
    UmsAdmin register(UmsAdminParam umsAdminParam);

    /**
     * 登录功能
     *
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username, String password);

    /**
     * 刷新token的功能
     *
     * @param oldToken 旧的Token
     * @return
     */
    String refreshToken(String oldToken);

    /**
     * 根据用户id获取用户
     *
     * @param id
     * @return
     */
    UmsAdmin getItem(Long id);

    /**
     * 根据用户名或昵称分页查询用户
     *
     * @param keyword
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<UmsAdmin> list(String keyword, Integer pageSize, Integer pageNum);


    /**
     * 删除
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 修改
     *
     * @param id
     * @param umsAdmin
     * @return
     */
    int update(Long id, UmsAdmin umsAdmin);

    /**
     * 更改用户角色关系
     *
     * @param adminId
     * @param roleIds
     * @return
     */
    @Transactional
    int updateRole(Long adminId, List<Long> roleIds);

    /**
     * 获取用户对于角色
     *
     * @param adminId
     * @return
     */
    List<UmsRole> getRoleList(Long adminId);

    /**
     * 获取指定用户的可访问资源
     *
     * @param adminId
     * @return
     */
    List<UmsResource> getResourceList(Long adminId);

    /**
     * 修改用户的+ - 权限
     *
     * @param adminId
     * @param permission
     * @return
     */
    int updatePermission(Long adminId, List<Long> permission);

    /**
     * 获取用户所有权限(包括角色权限和+-权限)
     *
     * @param adminId
     * @return
     */
    List<UmsPermission> getPermissionList(Long adminId);

    /**
     * 修改密码
     *
     * @param updateAdminPasswordParam
     * @return
     */
    int updatePassword(UpdateAdminPasswordParam updateAdminPasswordParam);

    /**
     * 获取用户信息
     *
     * @param username
     * @return
     */
    UserDetails loadUserByUsername(String username);
}
