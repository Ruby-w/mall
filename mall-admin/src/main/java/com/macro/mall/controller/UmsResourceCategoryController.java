package com.macro.mall.controller;

import com.macro.mall.common.api.CommonResult;
import com.macro.mall.model.UmsResourceCategory;
import com.macro.mall.service.UmsResourceCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName UmsResourceCategoryController
 * @Description 后台资源分类管理Controller
 * @Author AW
 * @Date 2020/8/16 001621:17
 * @Version V1.0
 **/
@Api(tags = "UmsResourceCategoryController", description = "后台资源分类管理")
@RequestMapping(value = "resourceCategory")
@RestController
public class UmsResourceCategoryController {
    @Autowired
    private UmsResourceCategoryService resourceCategoryService;


    @ApiOperation(value = "获取后台所有资源")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public CommonResult listAll() {
        List<UmsResourceCategory> umsResourceCategories = resourceCategoryService.listAll();
        return CommonResult.success(umsResourceCategories);
    }


    @ApiOperation(value = "添加后台资源")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(UmsResourceCategory resourceCategory) {
        int count = resourceCategoryService.create(resourceCategory);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "修改后台资源分类")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult update(@PathVariable Long id, @RequestBody UmsResourceCategory resourceCategory) {
        int count = resourceCategoryService.update(id, resourceCategory);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "删除后台资源分类")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public CommonResult delete(@PathVariable Long id) {
        int delete = resourceCategoryService.delete(id);
        if (delete > 0) {
            return CommonResult.success(delete);
        }
        return CommonResult.failed();
    }

}
