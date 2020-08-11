package com.macro.mall.service;

import com.macro.mall.model.UmsResourceCategory;

import java.util.List;

/**
 * @ClassName UmsResourceCategoryService
 * @Description 后台资源分类管理Service
 * @Author AW
 * @Date 2020/8/10 001022:33
 * @Version V1.0
 **/
public interface UmsResourceCategoryService {
    /**
     * 获取所有资源分类
     *
     * @return
     */
    List<UmsResourceCategory> listAll();

    /**
     * 创建资源分类
     *
     * @param umsResourceCategory
     * @return
     */
    int create(UmsResourceCategory umsResourceCategory);

    /**
     * 修改资源分类
     *
     * @param id
     * @param umsResourceCategory
     * @return
     */
    int update(Long id, UmsResourceCategory umsResourceCategory);

    /**
     * 删除资源分类
     *
     * @param id
     * @return
     */
    int delete(Long id);
}
