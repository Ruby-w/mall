package com.macro.mall.service;

import com.macro.mall.model.UmsResource;

import java.util.List;

/**
 * @ClassName UmsResourceService
 * @Description 后台资源管理Service
 * @Author AW
 * @Date 2020/8/9 000923:02
 * @Version V1.0
 **/
public interface UmsResourceService {
    /**
     * 添加资源
     *
     * @param resource
     * @return
     */
    int create(UmsResource resource);

    /**
     * 修改资源
     *
     * @param id
     * @param resource
     * @return
     */
    int update(Long id, UmsResource resource);

    /**
     * 获取资源详细
     *
     * @param id
     * @return
     */
    UmsResource getItem(Long id);

    /**
     *  删除资源
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 分页查询资源
     * @param categoryId
     * @param nameKeyWord
     * @param urlKeyword
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<UmsResource> list(Long categoryId,String nameKeyWord,String urlKeyword,Integer pageSize,Integer pageNum);

    /**
     *  查询全部资源
     * @return
     */
    List<UmsResource> listAll();
}
