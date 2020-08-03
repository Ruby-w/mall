package com.macro.mall.search.dao;

import com.macro.mall.search.domain.EsProduct;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @ClassName EsProductDao
 * @Description 搜索系统中的商品管理自定义dao
 * @Author AW
 * @Date 2020/8/2 000219:04
 * @Version V1.0
 **/

public interface EsProductDao {
    /**
     * 获取指定id的搜索商品
     *
     * @param id
     * @return
     */
    List<EsProduct> getAllEsProductList(@Param("id") Long id);
}
