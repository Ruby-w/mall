package com.macro.mall.controller;

import cn.hutool.core.collection.CollUtil;
import com.macro.mall.common.api.CommonResult;
import com.macro.mall.dto.UmsAdminLoginParam;
import com.macro.mall.dto.UmsAdminParam;
import com.macro.mall.dto.UpdateAdminPasswordParam;
import com.macro.mall.model.UmsAdmin;
import com.macro.mall.model.UmsPermission;
import com.macro.mall.model.UmsRole;
import com.macro.mall.service.UmsAdminService;
import com.macro.mall.service.UmsRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName UmsAdminController
 * @Description 后台用户管理
 * @Author AW
 * @Date 2020/8/11 001123:41
 * @Version V1.0
 **/

@RestController
@Api(tags = "umsAdminController", description = "后台用户管理")
@RequestMapping(value = "/admin")
public class UmsAdminController {
    @Value("{jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private UmsAdminService adminService;
    @Autowired
    private UmsRoleService roleService;

    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public CommonResult<?> register(@RequestBody UmsAdminParam adminParam, BindingResult result) {
        UmsAdmin register = adminService.register(adminParam);
        if (null == register) {
            CommonResult.failed();
        }
        return CommonResult.success(adminParam);
    }

    @ApiOperation(value = "登录之后返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public CommonResult login(@RequestBody UmsAdminLoginParam adminLoginParam, BindingResult result) {
        String token = adminService.login(adminLoginParam.getUsername(), adminLoginParam.getPassword());
        if (null == token) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("tokenHead", tokenHead);
        return CommonResult.success(map);
    }

    @ApiOperation(value = "刷新token")
    @RequestMapping(value = "/refreshToken", method = RequestMethod.GET)
    public CommonResult refreshToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshToken = adminService.refreshToken(token);
        if (null == refreshToken) return CommonResult.failed("token已经过期");
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("tokenHead", tokenHead);
        return CommonResult.success(map);
    }

    @ApiOperation(value = "获取当前登录用户信息")
    @RequestMapping(value = "getAdminInfo", method = RequestMethod.GET)
    public CommonResult getAdminInfo(Principal principal) {
        if (null == principal) return CommonResult.unauthorized(null);
        String username = principal.getName();
        UmsAdmin umsAdmin = adminService.getAdminByUserName(username);
        Map<String, Object> data = new HashMap<>();
        data.put("username", umsAdmin.getUsername());
        data.put("menus", roleService.getMenuList(umsAdmin.getId()));
        data.put("icon", umsAdmin.getIcon());
        List<UmsRole> roleList = adminService.getRoleList(umsAdmin.getId());
        if (CollUtil.isNotEmpty(roleList)) {
            List<String> collect = roleList.stream().map(UmsRole::getName).collect(Collectors.toList());
            data.put("roles", collect);
        }
        return CommonResult.success(data);
    }

    @ApiOperation(value = "登出功能")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public CommonResult logout() {
        return CommonResult.success(null);
    }

    @ApiOperation(value = "根据用户名或姓名分页获取用户列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult list(@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword, @RequestParam(value = "pageSize", required = false, defaultValue = "") Integer pageSize,
                             @RequestParam(value = "pageNum", required = false, defaultValue = "") Integer pageNum) {
        List<UmsAdmin> adminList = adminService.list(keyword, pageSize, pageNum);
        return CommonResult.success(adminList);

    }

    @ApiOperation(value = "修改指定用户信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public CommonResult update(@PathVariable Long id, @RequestBody UmsAdmin admin) {
        int count = adminService.update(id, admin);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "修改指定用户密码")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public CommonResult updatePassword(@RequestBody UpdateAdminPasswordParam adminPasswordParam) {
        int count = adminService.updatePassword(adminPasswordParam);
        if (count > 0) {
            return CommonResult.success(count);
        } else if (count == -1) {
            return CommonResult.failed("提交参数不合法");
        } else if (count == -2) {
            return CommonResult.failed("找不到用户");
        } else if (count == -3) {
            return CommonResult.failed("旧密码错误!");
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "修改账号状态")
    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    public CommonResult updateStatus(@PathVariable Long id, @RequestParam(value = "status") Integer status) {
        UmsAdmin admin = new UmsAdmin();
        admin.setStatus(status);
        int count = adminService.update(id, admin);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "给用户分配角色")
    @RequestMapping(value = "/updateRole", method = RequestMethod.POST)
    public CommonResult updateRole(@RequestParam(value = "adminId") Long adminId,
                                   @RequestParam(value = "roleIds") List<Long> roleIds) {
        int count = adminService.updateRole(adminId, roleIds);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "获取指定用户的角色")
    @RequestMapping(value = "/getRoleList", method = RequestMethod.GET)
    public CommonResult<List<UmsRole>> getRoleList(@PathVariable Long adminId) {
        List<UmsRole> roleList = adminService.getRoleList(adminId);
        if (StringUtils.isEmpty(roleList) && roleList.size() == 0) {
            return CommonResult.failed();
        }
        return CommonResult.success(roleList);
    }

    @ApiOperation(value = "给用户分配+-的权限")
    @RequestMapping(value = "/updatePermission", method = RequestMethod.POST)
    public CommonResult updatePermission(@RequestParam Long id,
                                         @RequestParam(value = "permissionIds") List<Long> permissionIds) {
        int count = adminService.updatePermission(id, permissionIds);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "获取用户所有权限(包括+-的权限)")
    @RequestMapping(value = "/getPermissionList", method = RequestMethod.GET)
    public CommonResult<List<UmsPermission>> getPermissionList(@PathVariable Long adminId) {
        List<UmsPermission> permissionList = adminService.getPermissionList(adminId);
        return CommonResult.success(permissionList);
    }
}
