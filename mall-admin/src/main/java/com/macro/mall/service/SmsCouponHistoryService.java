package com.macro.mall.service;

import com.macro.mall.model.SmsCouponHistory;

import java.util.List;

/**
 * @ClassName SmsCouponHistoryService
 * @Description 优惠券领取记录管理Service
 * @Author AW
 * @Date 2020/8/19 001921:35
 * @Version V1.0
 **/
public interface SmsCouponHistoryService {
    /**
     * 分页查询优惠券领取记录
     *
     * @param couponId 优惠券id
     * @param useStatus  使用状态
     * @param orderSn 使用订单号码
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<SmsCouponHistory> list(Long couponId, Integer useStatus, String orderSn, Integer pageSize, Integer pageNum);
}
