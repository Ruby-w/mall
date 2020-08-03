package com.macro.mall.search.service;

import com.macro.mall.search.domain.EsProduct;
import com.macro.mall.search.domain.EsProductRelatedInfo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @ClassName EsProductService
 * @Description 商品搜索管理Service
 * @Author AW
 * @Date 2020/8/2 000219:07
 * @Version V1.0
 **/
public interface EsProductService {
    /**
     * 从数据库中导入所有商品到Es
     *
     * @return
     */
    int importAll();

    /**
     * 根据id删除商品
     *
     * @param id
     */
    void delete(Long id);

    /**
     * 根据id创建商品
     *
     * @param id
     * @return
     */
    EsProduct create(Long id);

    /**
     * 批量删除
     *
     * @param ids
     */
    void delete(List<Long> ids);

    /**
     * 根据关键字搜索名称或者副标题
     *
     * @param keyword
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize);
    /**
     * 根据关键字搜索名称或者副标题复合查询
     */
    Page<EsProduct> search(String keyword, Long brandId, Long productCategoryId, Integer pageNum, Integer pageSize,Integer sort);
    /**
     * 根据商品id推荐相关商品
     *
     * @param id
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<EsProduct> recommend(Long id, Integer pageNum, Integer pageSize);

    /**
     * 获取搜索词相关品牌、分类、属性
     *
     * @param keyword
     * @return
     */
    EsProductRelatedInfo searchRelatedInfo(String keyword);
}
