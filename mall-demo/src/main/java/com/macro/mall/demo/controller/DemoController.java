package com.macro.mall.demo.controller;

import com.macro.mall.common.api.CommonResult;
import com.macro.mall.demo.service.DemoService;
import com.macro.mall.model.PmsBrand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * @ClassName DemoController
 * @Description 品牌管理示例Controller
 * @Author AW
 * @Date 2020/7/30 003023:05
 * @Version V1.0
 **/
@Api(tags = "DemoController",description = "品牌管理示例接口")
@Controller
public class DemoController {
     @Autowired
      private DemoService demoService;

     private static final Logger LOGGER = LoggerFactory.getLogger(DemoController.class);
@ApiOperation(value = "获取全部品牌列表")
     public CommonResult<List<PmsBrand>> getBrandList(){
return CommonResult.success(demoService.listAllBrand());
     }

}
