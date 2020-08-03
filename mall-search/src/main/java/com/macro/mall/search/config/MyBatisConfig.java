package com.macro.mall.search.config;

import org.mapstruct.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MyBatisConfig
 * @Description Myabtis配置文件
 * @Author AW
 * @Date 2020/8/2 000219:03
 * @Version V1.0
 **/
@Configuration
@MapperScan({"com.macro.mall.mapper","com.macro.mall.search.dao"})
public class MyBatisConfig {
}
