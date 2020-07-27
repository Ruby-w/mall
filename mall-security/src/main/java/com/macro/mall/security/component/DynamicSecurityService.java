package com.macro.mall.security.component;

import org.springframework.security.access.ConfigAttribute;

import java.util.Map;

/**
 * @ClassName DynamicSecurityService
 * @Description 动态权限相关业务类
 * @Author AW
 * @Date 2020/7/28 00280:38
 * @Version V1.0
 **/
public interface DynamicSecurityService {
    /**
     * 加载资源ANT通配符和资源对应MAP
     */
    Map<String, ConfigAttribute> loadDataSource();
}
