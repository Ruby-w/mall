package com.macro.mall.controller;

import cn.hutool.core.collection.CollUtil;
import com.macro.mall.common.api.CommonResult;
import com.macro.mall.dto.UmsAdminLoginParam;
import com.macro.mall.dto.UmsAdminParam;
import com.macro.mall.model.UmsAdmin;
import com.macro.mall.model.UmsRole;
import com.macro.mall.service.UmsAdminService;
import com.macro.mall.service.UmsRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
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
}
