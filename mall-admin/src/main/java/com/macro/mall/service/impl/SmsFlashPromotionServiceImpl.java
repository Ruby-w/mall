package com.macro.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.macro.mall.mapper.SmsFlashPromotionMapper;
import com.macro.mall.model.SmsFlashPromotion;
import com.macro.mall.model.SmsFlashPromotionExample;
import com.macro.mall.service.SmsFlashPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @ClassName SmsFlashPromotionServiceImpl
 * @Description 限时购活动管理Service实现类
 * @Author AW
 * @Date 2020/8/20 002022:33
 * @Version V1.0
 **/
@Service
public class SmsFlashPromotionServiceImpl implements SmsFlashPromotionService {
    @Autowired
    private SmsFlashPromotionMapper promotionMapper;

    @Override
    public int create(SmsFlashPromotion flashPromotion) {
        flashPromotion.setCreateTime(new Date());
        return promotionMapper.insert(flashPromotion);
    }

    @Override
    public int update(Long id, SmsFlashPromotion flashPromotion) {
        flashPromotion.setId(id);
        return promotionMapper.updateByPrimaryKey(flashPromotion);
    }

    @Override
    public int delete(Long id) {
        return promotionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateStatus(Long id, Integer status) {
        SmsFlashPromotion promotion = new SmsFlashPromotion();
        promotion.setId(id);
        promotion.setStatus(status);
        return promotionMapper.updateByPrimaryKeySelective(promotion);
    }

    @Override
    public SmsFlashPromotion getItem(Long id) {

        return promotionMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SmsFlashPromotion> list(String keyword, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        SmsFlashPromotionExample example = new SmsFlashPromotionExample();
        if(!StringUtils.isEmpty(keyword)){
          example.createCriteria().andTitleEqualTo("%"+keyword+"%");
        }
        return promotionMapper.selectByExample(example);
    }
}
