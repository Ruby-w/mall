package com.macro.mall.service;

import com.macro.mall.dto.SmsFlashPromotionProduct;
import com.macro.mall.model.SmsFlashPromotionProductRelation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName SmsFlashPromotionProductRelationService
 * @Description 限时购商品关联管理Service
 * @Author AW
 * @Date 2020/8/20 002021:33
 * @Version V1.0
 **/
public interface SmsFlashPromotionProductRelationService {
    /**
     * 批量添加关联
     *
     * @param relationList
     * @return
     */
    @Transactional
    int create(List<SmsFlashPromotionProductRelation> relationList);

    /**
     * 修改关联相关信息
     *
     * @param id
     * @param relation
     * @return
     */
    int update(Long id, SmsFlashPromotionProductRelation relation);

    /**
     * 删除关联
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 根据id获取关联详情
     *
     * @param id
     * @return
     */
    SmsFlashPromotionProductRelation getItem(Long id);

    /**
     * 分页查询相关商品及促销信息
     *
     * @param flashPromotionId        限时购id
     * @param flashPromotionSessionId 限时购场次id
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<SmsFlashPromotionProduct> list(Long flashPromotionId, Long flashPromotionSessionId, Integer pageSize, Integer pageNum);

    /**
     * 根据活动和场次id获取商品关系数量
     *
     * @param flashPromotionId
     * @param flashPromotionSessionId
     * @return
     */
    long getCount(Long flashPromotionId, Long flashPromotionSessionId);

}
