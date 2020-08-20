package com.macro.mall.service;

import com.macro.mall.model.SmsFlashPromotion;

import java.util.List;

/**
 * @ClassName SmsFlashPromotionService
 * @Description 限时购活动管理Service
 * @Author AW
 * @Date 2020/8/20 002022:19
 * @Version V1.0
 **/
public interface SmsFlashPromotionService {
    /**
     * 添加活动
     * @param flashPromotion
     * @return
     */
   int create(SmsFlashPromotion flashPromotion);

    /**
     *  修改指定活动
     * @param id
     * @param flashPromotion
     * @return
     */
   int update(Long id,SmsFlashPromotion flashPromotion);

    /**
     *  删除单个活动
     * @param id
     * @return
     */
   int delete(Long id);

    /**
     *  修改上下状态
     * @param id
     * @param status
     * @return
     */
  int updateStatus(Long id,Integer status);

    /**
     * 获取详细信息
     * @param id
     * @return
     */
  SmsFlashPromotion getItem(Long id);

    /**
     * 分页查询活动
     * @param keyword
     * @param pageSize
     * @param pageNum
     * @return
     */
  List<SmsFlashPromotion> list(String keyword,Integer pageSize,Integer pageNum);
}
