package com.macro.mall.dto;

import com.macro.mall.model.SmsCoupon;
import com.macro.mall.model.SmsCouponProductCategoryRelation;
import com.macro.mall.model.SmsCouponProductRelation;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @ClassName SmsCouponParam
 * @Description 优惠券信息封装，包括绑定商品和绑定分类
 * @Author AW
 * @Date 2020/8/17 001723:21
 * @Version V1.0
 **/

public class SmsCouponParam extends SmsCoupon {

    @Getter
    @Setter
    @ApiModelProperty("优惠券绑定的商品")
    private List<SmsCouponProductRelation> productRelationList;
    @Getter
    @Setter
    @ApiModelProperty("优惠券绑定的商品分类")
    private List<SmsCouponProductCategoryRelation> productCategoryRelations;
}
