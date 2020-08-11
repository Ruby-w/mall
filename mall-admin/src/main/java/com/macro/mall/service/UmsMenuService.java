package com.macro.mall.service;

import com.macro.mall.dto.UmsMenuNode;
import com.macro.mall.model.UmsMenu;

import java.util.List;

/**
 * @ClassName UmsMenuService
 * @Description 后台菜单管理Service
 * @Author AW
 * @Date 2020/8/10 001022:38
 * @Version V1.0
 **/
public interface UmsMenuService {
    /**
     * 创建后台菜单
     *
     * @param umsMenu
     * @return
     */
    int create(UmsMenu umsMenu);

    /**
     * 修改后台菜单
     *
     * @param id
     * @param umsMenu
     * @return
     */
    int update(Long id, UmsMenu umsMenu);

    /**
     * 根据Id获取菜单详情
     *
     * @param id
     * @return
     */
    UmsMenu getItem(Long id);

    /**
     * 根据Id删除菜单
     *
     * @param id
     * @return
     */
    int delete(Long id);

    /**
     * 分页查询后台菜单
     *
     * @param parentId
     * @param pageSize
     * @param pageNum
     * @return
     */
    List<UmsMenu> list(Long parentId, Integer pageSize, Integer pageNum);

    /**
     * 树形结构返回所有菜单列表
     *
     * @return
     */
    List<UmsMenuNode> treeList();

    /**
     * 修改菜单显示状态
     *
     * @param id
     * @param hidden
     * @return
     */
    int updateHidden(Long id, Integer hidden);
}
