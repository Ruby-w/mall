package com.macro.mall.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @ClassName GlobalCorsConfig
 * @Description 全局跨域配置
 * @Author AW
 * @Date 2020/8/8 000822:02
 * @Version V1.0
 **/
@Configuration
public class GlobalCorsConfig {

    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        // 允许所有域名进行跨域调用
        config.addAllowedOrigin("*");
        // 允许跨域发送Cookie
        config.setAllowCredentials(true);
        //放行全部原始头信息
        config.addExposedHeader("*");
        // 允许所有请求头方法跨域调用
        config.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
