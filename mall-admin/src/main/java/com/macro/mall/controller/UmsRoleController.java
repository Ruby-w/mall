package com.macro.mall.controller;

import com.macro.mall.common.api.CommonPage;
import com.macro.mall.common.api.CommonResult;
import com.macro.mall.model.UmsMenu;
import com.macro.mall.model.UmsPermission;
import com.macro.mall.model.UmsRole;
import com.macro.mall.service.UmsRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName UmsRoleController
 * @Description 后台用户角色管理
 * @Author AW
 * @Date 2020/8/16 001621:59
 * @Version V1.0
 **/
@Api(tags = "UmsRoleController", description = "后台用户角色管理")
@RequestMapping(value = "/role")
@RestController
public class UmsRoleController {
    @Autowired
    private UmsRoleService roleService;

    @ApiOperation(value = "添加角色")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody UmsRole role) {
        int count = roleService.create(role);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "更新角色")
    @RequestMapping(value = "/update//{id}", method = RequestMethod.POST)
    public CommonResult update(@PathVariable Long id, @RequestBody UmsRole role) {
        int update = roleService.update(id, role);
        if (update > 0) {
            return CommonResult.success(update);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "删除后台角色")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public CommonResult delete(@RequestParam(value = "ids") List<Long> ids) {
        int delete = roleService.delete(ids);
        if (delete > 0) {
            return CommonResult.success(delete);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "获取相应角色权限")
    @RequestMapping(value = "/getPermissionList/{roleId}", method = RequestMethod.GET)
    public CommonResult<List<UmsPermission>> getPermissionList(@PathVariable Long roleId) {
        List<UmsPermission> list = roleService.getPermissionList(roleId);
        return CommonResult.success(list);
    }

    @ApiOperation(value = "修改角色权限")
    @RequestMapping(value = "/updatePermission", method = RequestMethod.POST)
    public CommonResult updatePermission(@RequestParam Long ids,
                                         @RequestParam(value = "permissionIds") List<Long> permissionIds) {
        int count = roleService.updatePermission(ids, permissionIds);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "获取所有角色")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public CommonResult<List<UmsRole>> listAll() {
        List<UmsRole> list = roleService.list();
        return CommonResult.success(list);
    }

    @ApiOperation(value = "根据角色名称分页获取角色列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<UmsRole>> list(@RequestParam(value = "keyword", required = false) String keyword
            , @RequestParam(value = "pageSize", required = false, defaultValue = "5")
                                                          Integer pageSize,
                                                  @RequestParam(value = "pageNum", required = false, defaultValue = "1")
                                                          Integer pageNum) {
        List<UmsRole> list = roleService.list(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(list));
    }

    @ApiOperation(value = "修改角色状态")
    @RequestMapping(value = "/updateStatus/{id}", method = RequestMethod.POST)
    public CommonResult updateStatus(@PathVariable Long id, @RequestParam(value = "status") Integer status) {
        UmsRole role = new UmsRole();
        role.setStatus(status);
        int update = roleService.update(id, role);
        if (update > 0) {
            return CommonResult.success(update);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "获取角色相关资源")
    @RequestMapping(value = "/listMenu/{roleId}", method = RequestMethod.GET)
    public CommonResult<List<UmsMenu>> listMenu(@PathVariable Long roleId) {
        List<UmsMenu> list = roleService.listMenu(roleId);
        return CommonResult.success(list);
    }

    @ApiOperation(value = "给角色分配菜单")
    @RequestMapping(value = "/allocMenu", method = RequestMethod.GET)
    public CommonResult allocMenu(@RequestParam Long roleId, @RequestParam List<Long> menuIds) {
        int count = roleService.allocMenu(roleId, menuIds);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "给角色分配资源")
    @RequestMapping(value = "allocResource", method = RequestMethod.GET)
    public CommonResult allocResource(@RequestParam Long roleIds, @RequestParam List<Long> resourceIds) {
        int count = roleService.allocResource(roleIds, resourceIds);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }


}
