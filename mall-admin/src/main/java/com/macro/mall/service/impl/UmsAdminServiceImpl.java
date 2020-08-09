package com.macro.mall.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.macro.mall.bo.AdminUserDetails;
import com.macro.mall.dao.UmsAdminPermissionRelationDao;
import com.macro.mall.dao.UmsAdminRoleRelationDao;
import com.macro.mall.dto.UmsAdminParam;
import com.macro.mall.dto.UpdateAdminPasswordParam;
import com.macro.mall.mapper.UmsAdminMapper;
import com.macro.mall.mapper.UmsAdminPermissionRelationMapper;
import com.macro.mall.mapper.UmsAdminRoleRelationMapper;
import com.macro.mall.mapper.UmsAdminLoginLogMapper;
import com.macro.mall.model.*;
import com.macro.mall.security.util.JwtTokenUtil;
import com.macro.mall.service.UmsAdminCacheService;
import com.macro.mall.service.UmsAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName UmsAdminServiceImpl
 * @Description UmsAdminService实现类
 * @Author AW
 * @Date 2020/8/8 000822:45
 * @Version V1.0
 **/
@Service
public class UmsAdminServiceImpl implements UmsAdminService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UmsAdminServiceImpl.class);
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UmsAdminMapper adminMapper;
    @Autowired
    private UmsAdminRoleRelationMapper adminRoleRelationMapper;
    @Autowired
    private UmsAdminRoleRelationDao adminRoleRelationDao;
    @Autowired
    private UmsAdminPermissionRelationMapper adminPermissionRelationMapper;
    @Autowired
    private UmsAdminPermissionRelationDao adminPermissionRelationDao;

    private UmsAdminLoginLogMapper loginMapper;
    @Autowired
    private UmsAdminCacheService adminCacheService;


    @Override
    public UmsAdmin getAdminByUserName(String username) {
        UmsAdmin admin = adminCacheService.getAdmin(username);
        if (admin != null) return admin;
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UmsAdmin> adminList = adminMapper.selectByExample(example);
        if (adminList != null && adminList.size() > 0) {
            admin = adminList.get(0);
            adminCacheService.setAdmin(admin);
            return admin;
        }
        return null;
    }

    @Override
    public UmsAdmin register(UmsAdminParam umsAdminParam) {
        UmsAdmin admin = new UmsAdmin();
        BeanUtils.copyProperties(umsAdminParam, admin);
        admin.setCreateTime(new Date());
        admin.setStatus(1);
        // 查询是否有相同用户名的用户
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(admin.getUsername());
        List<UmsAdmin> adminList = adminMapper.selectByExample(example);
        if (adminList.size() > 0) {
            return null;
        }
        // 将密码进行加密操作
        String encodePassword = passwordEncoder.encode(admin.getPassword());
        admin.setPassword(encodePassword);
        adminMapper.insert(admin);
        return admin;
    }

    @Override
    public String login(String username, String password) {
        String token = null;
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = loadUserByUsername(username);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
            insertLoginLog(username);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    @Override
    public String refreshToken(String oldToken) {
        return jwtTokenUtil.refreshHeadToken(oldToken);
    }

    @Override
    public UmsAdmin getItem(Long id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<UmsAdmin> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        UmsAdminExample example = new UmsAdminExample();
        UmsAdminExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(keyword)) {
            criteria.andUsernameLike("%" + keyword + "%");
            example.or(example.createCriteria().andNickNameLike("%" + keyword + "%"));
        }
        return adminMapper.selectByExample(example);

    }

    @Override
    public int delete(Long id) {
        adminCacheService.deleteAdmin(id);
        int count = adminMapper.deleteByPrimaryKey(id);
        if (count > 0) {
            adminCacheService.deleteResourceList(id);
        }
        return count;

    }

    @Override
    public int update(Long id, UmsAdmin umsAdmin) {
        umsAdmin.setId(id);
        UmsAdmin admin = adminMapper.selectByPrimaryKey(id);
        if (admin.getPassword().equals(umsAdmin.getPassword())) {
            // 原加密密码x相同的不需要修改
            umsAdmin.setPassword(null);
        } else {
            // 与原始密码不同的需要加密修改
            if (StringUtils.isEmpty(umsAdmin.getPassword())) {
                umsAdmin.setPassword(null);
            } else {
                umsAdmin.setPassword(passwordEncoder.encode(umsAdmin.getPassword()));
            }
        }
        int count = adminMapper.updateByPrimaryKeySelective(umsAdmin);
        adminCacheService.deleteAdmin(id);
        return count;
    }

    @Override
    public int updateRole(Long adminId, List<Long> roleIds) {
        int count = roleIds == null ? 0 : roleIds.size();
        //先删除原来的关系
        UmsAdminRoleRelationExample example = new UmsAdminRoleRelationExample();
        example.createCriteria().andAdminIdEqualTo(adminId);
        adminRoleRelationMapper.deleteByExample(example);
        //建立新关系
        if (!CollectionUtils.isEmpty(roleIds)) {
            List<UmsAdminRoleRelation> list = new ArrayList<>();
            roleIds.forEach(roleId -> {
                UmsAdminRoleRelation relation = new UmsAdminRoleRelation();
                relation.setAdminId(adminId);
                relation.setRoleId(roleId);
                list.add(relation);
            });
            adminRoleRelationDao.insertList(list);
        }
        adminCacheService.deleteResourceList(adminId);
        return count;
    }

    @Override
    public List<UmsRole> getRoleList(Long adminId) {
        return adminRoleRelationDao.getRoleList(adminId);
    }

    @Override
    public List<UmsResource> getResourceList(Long adminId) {
        List<UmsResource> resourceList = adminCacheService.getResourceList(adminId);
        if (CollUtil.isNotEmpty(resourceList)) {
            return resourceList;
        }
        resourceList = adminRoleRelationDao.getResourceList(adminId);
        if (CollUtil.isNotEmpty(resourceList)) {
            adminCacheService.setResourceList(adminId, resourceList);
        }

        return resourceList;
    }

    @Override
    public int updatePermission(Long adminId, List<Long> permission) {
        //删除原所有权限关系
        UmsAdminPermissionRelationExample example = new UmsAdminPermissionRelationExample();
        example.createCriteria().andAdminIdEqualTo(adminId);
        adminPermissionRelationMapper.deleteByExample(example);
        //获取用户所有角色权限
        List<UmsPermission> permissionList = adminRoleRelationDao.getRolePermissionList(adminId);
        List<Long> rolePermissionList = permissionList.stream().map(UmsPermission::getId).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(permission)) {
            List<UmsAdminPermissionRelation> list = new ArrayList<>();
            //筛选出+权限
            List<Long> addPermissionIdList = permission.stream().filter(permissionId -> !rolePermissionList.contains(permissionId)).collect(Collectors.toList());
            //筛选出 - 权限
            List<Long> subPermissionIdList = rolePermissionList.stream().filter(permissionId ->
                    !permission.contains(permissionId)).collect(Collectors.toList());
            // 插入 +- 权限关系
            list.addAll(convert(adminId, 1, addPermissionIdList));
            list.addAll(convert(adminId, -1, subPermissionIdList));
            return adminPermissionRelationDao.insertList(list);

        }

        return 0;
    }

    @Override
    public List<UmsPermission> getPermissionList(Long adminId) {
        return adminRoleRelationDao.getRolePermissionList(adminId);
    }

    @Override
    public int updatePassword(UpdateAdminPasswordParam param) {
        if (StrUtil.isEmpty(param.getUsername()) || StrUtil.isEmpty(param.getOldPassword()) || StrUtil.isEmpty(param.getNewPassword())) {
            return -1;
        }
        UmsAdminExample example = new UmsAdminExample();
        example.createCriteria().andUsernameEqualTo(param.getUsername());
        List<UmsAdmin> admins = adminMapper.selectByExample(example);
        if (CollUtil.isEmpty(admins)) {
            return -2;
        }
        UmsAdmin admin = admins.get(0);
        if (!passwordEncoder.matches(param.getOldPassword(), admin.getPassword())) {
            return -3;
        }

        admin.setPassword(passwordEncoder.encode(param.getNewPassword()));
        adminMapper.updateByPrimaryKey(admin);
        adminCacheService.deleteAdmin(admin.getId());
        return 1;
    }

    /**
     * 将+- 权限关系转化为对象
     *
     * @param adminId
     * @param type
     * @param permissionIdList
     * @return
     */
    private List<UmsAdminPermissionRelation> convert(Long adminId, Integer type, List<Long> permissionIdList) {
        List<UmsAdminPermissionRelation> relationList = permissionIdList.stream().map(permissionId -> {
            UmsAdminPermissionRelation relation = new UmsAdminPermissionRelation();
            relation.setAdminId(adminId);
            relation.setType(type);
            relation.setPermissionId(permissionId);
            return relation;
        }).collect(Collectors.toList());
        return relationList;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        // 获取用户信息
        UmsAdmin admin = getAdminByUserName(username);
        if (admin != null) {
            List<UmsResource> resources = getResourceList(admin.getId());
            return new AdminUserDetails(admin, resources);
        }
        throw new UsernameNotFoundException("用户名或密码错误！");
    }

    /**
     * 添加登录记录
     *
     * @param username
     */
    private void insertLoginLog(String username) {
        UmsAdmin admin = getAdminByUserName(username);
        if (admin == null) return;
        UmsAdminLoginLog loginLog = new UmsAdminLoginLog();
        loginLog.setAdminId(loginLog.getId());
        loginLog.setCreateTime(new Date());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        loginLog.setIp(request.getRemoteAddr());
        loginMapper.insert(loginLog);
    }
}
