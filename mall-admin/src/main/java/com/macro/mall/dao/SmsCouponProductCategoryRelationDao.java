package com.macro.mall.dao;

import com.macro.mall.model.SmsCouponProductCategoryRelation;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @ClassName SmsCouponProductCategoryRelationDao
 * @Description 自定义优惠券和商品分类关系管理Dao
 * @Author AW
 * @Date 2020/8/17 001723:28
 * @Version V1.0
 **/
public interface SmsCouponProductCategoryRelationDao {
    /**
     * 批量创建
     *
     * @param productCategoryRelations
     * @return
     */
    int insertList(@Param("list") List<SmsCouponProductCategoryRelation> productCategoryRelations);
}
