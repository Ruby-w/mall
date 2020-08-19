package com.macro.mall.dao;

import com.macro.mall.dto.SmsFlashPromotionProduct;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @ClassName SmsFlashPromotionProductRelationDao
 * @Description 自定义限时购商品关系管理Dao
 * @Author AW
 * @Date 2020/8/17 001723:29
 * @Version V1.0
 **/
public interface SmsFlashPromotionProductRelationDao {
    /**
     * 获取限时购及相关商品信息
     *
     * @param flashPromotionId
     * @param flashPromotionSessionId
     * @return
     */
    List<SmsFlashPromotionProduct> getList(@Param("flashPromotionId") Long flashPromotionId, @Param("flashPromotionSessionId") Long flashPromotionSessionId);
}
