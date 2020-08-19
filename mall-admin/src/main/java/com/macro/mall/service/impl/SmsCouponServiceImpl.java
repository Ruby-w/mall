package com.macro.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.macro.mall.dao.SmsCouponDao;
import com.macro.mall.dao.SmsCouponProductCategoryRelationDao;
import com.macro.mall.dao.SmsCouponProductRelationDao;
import com.macro.mall.dto.SmsCouponParam;
import com.macro.mall.mapper.SmsCouponMapper;
import com.macro.mall.mapper.SmsCouponProductCategoryRelationMapper;
import com.macro.mall.mapper.SmsCouponProductRelationMapper;
import com.macro.mall.model.*;
import com.macro.mall.service.SmsCouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @ClassName SmsCouponServiceImpl
 * @Description 优惠券管理Service实现类
 * @Author AW
 * @Date 2020/8/19 001922:05
 * @Version V1.0
 **/
@Service
public class SmsCouponServiceImpl implements SmsCouponService {
    @Autowired
    private SmsCouponMapper couponMapper;
    @Autowired
    private SmsCouponProductCategoryRelationMapper productCategoryRelationMapper;
    @Autowired
    private SmsCouponProductRelationMapper productCategoryRelation;
    @Autowired
    private SmsCouponProductRelationDao productRelationDao;
    @Autowired
    private SmsCouponProductCategoryRelationDao productCategoryRelationDao;
    @Autowired
    private SmsCouponDao couponDao;

    @Override
    public int create(SmsCouponParam couponParam) {
        couponParam.setCount(couponParam.getPublishCount());
        couponParam.setUseCount(0);
        couponParam.setReceiveCount(0);
        // 插入优惠券表
        int count = couponMapper.insert(couponParam);
        //插入优惠券和商品关系表
        if (couponParam.getUseType().equals(2)) {
            for (SmsCouponProductRelation smsCouponProductRelation : couponParam.getProductRelationList()) {
                smsCouponProductRelation.setCouponId(couponParam.getId());
            }
            productRelationDao.insertList(couponParam.getProductRelationList());
        }
        //插入优惠券和商品分类关系表
        if (couponParam.getUseType().equals(1)) {
            for (SmsCouponProductCategoryRelation relation : couponParam.getProductCategoryRelations()) {
                relation.setCouponId(couponParam.getId());
            }
            productCategoryRelationDao.insertList(couponParam.getProductCategoryRelations());
        }
        return count;
    }

    @Override
    public int delete(Long id) {
        //删除优惠券
        int count = couponMapper.deleteByPrimaryKey(id);
        // 删除商品关联
        deleteProductRelation(id);
        //删除商品分类关联
        deleteProductCategoryRelation(id);
        return count;
    }


    @Override
    public int update(Long id, SmsCouponParam couponParam) {
        couponParam.setId(id);
        int count = couponMapper.updateByPrimaryKey(couponParam);
        //删除后插入优惠券和商品关系表
        if (couponParam.getUseType().equals(2)) {
            for (SmsCouponProductRelation relation : couponParam.getProductRelationList()) {
                relation.setCouponId(couponParam.getId());
            }
            deleteProductRelation(id);
            productRelationDao.insertList(couponParam.getProductRelationList());
        }
        // 删除后插入优惠券和商品分类关系表
        if (couponParam.getUseType().equals(1)) {
            for (SmsCouponProductCategoryRelation relation : couponParam.getProductCategoryRelations()) {
                relation.setCouponId(couponParam
                        .getId());
                deleteProductCategoryRelation(id);
                productCategoryRelationDao.insertList(couponParam.getProductCategoryRelations());
            }
        }
        return count;
    }

    @Override
    public List<SmsCoupon> list(String name, Integer type, Integer pageSize, Integer pageNum) {
        SmsCouponExample example = new SmsCouponExample();
        SmsCouponExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        if (type != null) {
            criteria.andTypeEqualTo(type);
        }
        PageHelper.startPage(pageNum, pageSize);
        return couponMapper.selectByExample(example);
    }

    @Override
    public SmsCouponParam getItem(Long id) {
        return couponDao.getItem(id);
    }

    private void deleteProductCategoryRelation(Long id) {
        SmsCouponProductCategoryRelationExample example = new SmsCouponProductCategoryRelationExample();
        example.createCriteria().andCouponIdEqualTo(id);
        productCategoryRelationMapper.deleteByExample(example);
    }

    private void deleteProductRelation(Long id) {
        SmsCouponProductRelationExample productRelationExample = new SmsCouponProductRelationExample();
        productRelationExample.createCriteria().andCouponIdEqualTo(id);
        productCategoryRelation.deleteByExample(productRelationExample);
    }


}
