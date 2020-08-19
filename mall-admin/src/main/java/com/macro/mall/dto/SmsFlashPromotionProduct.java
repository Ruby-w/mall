package com.macro.mall.dto;

import com.macro.mall.model.PmsProduct;
import com.macro.mall.model.SmsFlashPromotionProductRelation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName SmsFlashPromotionProduct
 * @Description 限时购及商品信息封装
 * @Author AW
 * @Date 2020/8/17 001723:24
 * @Version V1.0
 **/
@Data
public class SmsFlashPromotionProduct extends SmsFlashPromotionProductRelation {
    @ApiModelProperty("关联商品")
    private PmsProduct pmsProduct;
}
