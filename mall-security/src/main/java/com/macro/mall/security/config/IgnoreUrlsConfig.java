package com.macro.mall.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName IgnoreUrlsConfig
 * @Description  用于配置不需要保护的资源路径
 * @Author AW
 * @Date 2020/7/27 002723:33
 * @Version V1.0
 **/
@Getter
@Setter
@ConfigurationProperties(prefix = "secure.ignored")
public class IgnoreUrlsConfig {

    private List<String> urls =new ArrayList<>();
}
