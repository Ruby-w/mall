package com.macro.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.macro.mall.mapper.SmsHomeBrandMapper;
import com.macro.mall.model.SmsHomeBrand;
import com.macro.mall.model.SmsHomeBrandExample;
import com.macro.mall.service.SmsHomeBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @ClassName SmsHomeBrandServiceImpl
 * @Description
 * @Author AW
 * @Date 2020/8/20 002023:06
 * @Version V1.0
 **/
@Service
public class SmsHomeBrandServiceImpl implements SmsHomeBrandService {
    @Autowired
    private SmsHomeBrandMapper homeBrandMapper;


    @Override
    public int create(List<SmsHomeBrand> homeBrandList) {
        for (SmsHomeBrand brand : homeBrandList) {
            brand.setRecommendStatus(1);
            brand.setSort(0);
            homeBrandMapper.insert(brand);
        }
        return homeBrandList.size();
    }

    @Override
    public int updateSort(Long id, Integer sort) {
        SmsHomeBrand brand = new SmsHomeBrand();
        brand.setId(id);
        brand.setSort(sort);
        return homeBrandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public int delete(List<Long> ids) {
        SmsHomeBrandExample example = new SmsHomeBrandExample();
        example.createCriteria().andBrandIdIn(ids);
        return homeBrandMapper.deleteByExample(example);
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        SmsHomeBrandExample example = new SmsHomeBrandExample();
        example.createCriteria().andIdIn(ids);
        SmsHomeBrand brand = new SmsHomeBrand();
        brand.setRecommendStatus(recommendStatus);
        return homeBrandMapper.updateByExampleSelective(brand,example);
    }

    @Override
    public List<SmsHomeBrand> list(String brandName, Integer recommendStatus, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageSize,pageNum);
        SmsHomeBrandExample example = new SmsHomeBrandExample();
        SmsHomeBrandExample.Criteria criteria = example.createCriteria();
       if(!StringUtils.isEmpty(brandName)){
     criteria.andBrandNameLike("%"+recommendStatus+"%");
       }
       if(recommendStatus != null){
criteria.andRecommendStatusEqualTo(recommendStatus);
       }
       example.setOrderByClause("sort desc");
        return homeBrandMapper.selectByExample(example);
    }
}
