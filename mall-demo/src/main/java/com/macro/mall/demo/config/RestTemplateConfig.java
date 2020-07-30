package com.macro.mall.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName RestTemplateConfig
 * @Description RestTemplate的配置
 * @Author AW
 * @Date 2020/7/30 003021:53
 * @Version V1.0
 **/
@Configuration
public class RestTemplateConfig {

    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
