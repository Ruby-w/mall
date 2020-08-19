package com.macro.mall.dao;

import com.macro.mall.model.SmsCouponProductRelation;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @ClassName SmsCouponProductRelationDao
 * @Description 自定义优惠券和商品关系关系Dao
 * @Author AW
 * @Date 2020/8/17 001723:28
 * @Version V1.0
 **/
public interface SmsCouponProductRelationDao {
    /**
     * 批量创建
     *
     * @param productRelationList
     * @return
     */
    int insertList(@Param("list") List<SmsCouponProductRelation> productRelationList);
}
