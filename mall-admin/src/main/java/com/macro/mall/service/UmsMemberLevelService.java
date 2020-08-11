package com.macro.mall.service;

import com.macro.mall.model.UmsMemberLevel;

import java.util.List;

/**
 * @ClassName UmsMemberLevelService
 * @Description 会员等级管理Service
 * @Author AW
 * @Date 2020/8/10 001022:29
 * @Version V1.0
 **/
public interface UmsMemberLevelService {
    /**
     * 获取所有会员登录
     * @param defaultStatus
     * @return
     */
    List<UmsMemberLevel> list(Integer defaultStatus);
}
