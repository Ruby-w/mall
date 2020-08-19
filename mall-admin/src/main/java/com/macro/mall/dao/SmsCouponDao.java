package com.macro.mall.dao;

import com.macro.mall.dto.SmsCouponParam;
import org.springframework.data.repository.query.Param;

/**
 * @ClassName SmsCouponDao
 * @Description 自定义优惠券管理Dao
 * @Author AW
 * @Date 2020/8/17 001723:26
 * @Version V1.0
 **/
public interface SmsCouponDao {
    /**
     * 获取优惠券详情b包括绑定关系
     */
    SmsCouponParam getItem(@Param("id") Long id);
}
