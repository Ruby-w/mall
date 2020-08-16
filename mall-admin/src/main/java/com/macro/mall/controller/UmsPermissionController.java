package com.macro.mall.controller;

import com.macro.mall.common.api.CommonResult;
import com.macro.mall.dto.UmsPermissionNode;
import com.macro.mall.model.UmsPermission;
import com.macro.mall.service.UmsPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName UmsPermissionController
 * @Description 后台用户权限管理
 * @Author AW
 * @Date 2020/8/16 001621:04
 * @Version V1.0
 **/
@Api(tags = "UmsPermissionController", description = "后台用户权限管理")
@RestController
@RequestMapping("/permission")
public class UmsPermissionController {
    @Autowired
    private UmsPermissionService permissionService;

    @ApiOperation(value = "添加权限")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(UmsPermission permission) {
        int count = permissionService.create(permission);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "更新权限")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult update(@PathVariable Long id, @RequestBody UmsPermission permission) {
        int count = permissionService.update(id, permission);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "删除权限")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public CommonResult delete(@RequestParam(value = "ids") List<Long> ids) {
        int count = permissionService.delete(ids);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "以层级结构返回所有权限")
    @RequestMapping(value = "/treeList", method = RequestMethod.GET)
    public CommonResult<List<UmsPermissionNode>> treeList() {
        List<UmsPermissionNode> treeList = permissionService.treeList();
        return CommonResult.success(treeList);
    }

    @ApiOperation(value = "获取所有权限列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<List<UmsPermission>> list() {
        List<UmsPermission> list = permissionService.list();
        return CommonResult.success(list);
    }
}
