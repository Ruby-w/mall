package com.macro.mall.controller;

import com.macro.mall.common.api.CommonPage;
import com.macro.mall.common.api.CommonResult;
import com.macro.mall.model.UmsResource;
import com.macro.mall.security.component.DynamicSecurityMetadataSource;
import com.macro.mall.service.UmsResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName UmsResourceController
 * @Description
 * @Author AW
 * @Date 2020/8/16 001621:44
 * @Version V1.0
 **/
@Api(tags = "UmsResourceController", description = "后台资源管理")
@RequestMapping(value = "/resource")
@RestController
public class UmsResourceController {
    @Autowired
    private UmsResourceService resourceService;
    @Autowired
    private DynamicSecurityMetadataSource metadataSource;

    @ApiOperation(value = "查询所有后台资源")
    @RequestMapping(value = "listAll", method = RequestMethod.GET)
    public CommonResult listAll() {
        List<UmsResource> umsResources = resourceService.listAll();
        return CommonResult.success(umsResources);
    }

    @ApiOperation(value = "添加后台资源")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody UmsResource resource) {
        int count = resourceService.create(resource);
        if (count > 0) {
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "修改后台资源")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult update(@PathVariable Long id,
                               @RequestBody UmsResource resource) {

        int update = resourceService.update(id, resource);
        if (update > 0) {
            return CommonResult.success(update);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "删除后台资源")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public CommonResult delete(@PathVariable Long id) {
        int delete = resourceService.delete(id);
        if (delete > 0) {
            return CommonResult.success(delete);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "根据Id获取后台资源")
    @RequestMapping(value = "/getItem", method = RequestMethod.GET)
    public CommonResult getItem(@RequestParam(value = "id") Long id) {
        UmsResource item = resourceService.getItem(id);
        return CommonResult.success(item);
    }

    @ApiOperation(value = "分页模糊查询后台资源")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public CommonResult<CommonPage<UmsResource>> list(@RequestParam(required = false) Long categoryId,
                                                      @RequestParam(required = false) String nameKeyword,
                                                      @RequestParam(required = false) String urlKeyword,
                                                      @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<UmsResource> list = resourceService.list(categoryId, nameKeyword, urlKeyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(list));


    }
}




