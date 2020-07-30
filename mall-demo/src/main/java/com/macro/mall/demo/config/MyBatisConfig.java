package com.macro.mall.demo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MyBatisConfig
 * @Description  Mybatis配置类
 * @Author AW
 * @Date 2020/7/30 003021:43
 * @Version V1.0
 **/
@Configuration
@MapperScan("com.macro.mall.mapper")
public class MyBatisConfig {
}
