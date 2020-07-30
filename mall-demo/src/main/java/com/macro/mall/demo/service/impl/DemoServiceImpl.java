package com.macro.mall.demo.service.impl;

import com.github.pagehelper.PageHelper;
import com.macro.mall.demo.dto.PmsBrandDto;
import com.macro.mall.demo.service.DemoService;
import com.macro.mall.mapper.PmsBrandMapper;
import com.macro.mall.model.PmsBrand;
import com.macro.mall.model.PmsBrandExample;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName DemoServiceImpl
 * @Description
 * @Author AW
 * @Date 2020/7/30 003022:58
 * @Version V1.0
 **/
@Service
public class DemoServiceImpl implements DemoService {

    private PmsBrandMapper brandMapper;

    @Override
    public List<PmsBrand> listAllBrand() {
        return brandMapper.selectByExample(new PmsBrandExample());
    }

    @Override
    public int createBrand(PmsBrandDto pmsBrandDto) {
        PmsBrand brand = new PmsBrand();
        BeanUtils.copyProperties(pmsBrandDto, brand);
        return brandMapper.insertSelective(brand);
    }

    @Override
    public int updateBrand(Long id, PmsBrandDto pmsBrandDto) {
        PmsBrand brand = new PmsBrand();
        BeanUtils.copyProperties(pmsBrandDto, brand);
        return brandMapper.updateByPrimaryKeySelective(brand);
    }

    @Override
    public int deleteBrand(Long id) {
        return brandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<PmsBrand> listBrand(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return brandMapper.selectByExample(new PmsBrandExample());
    }

    @Override
    public PmsBrand getBrand(Long id) {

        return brandMapper.selectByPrimaryKey(id);
    }
}
