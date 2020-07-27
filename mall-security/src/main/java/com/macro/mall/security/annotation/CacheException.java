package com.macro.mall.security.annotation;

import java.lang.annotation.*;

/**
  * 功能描述: <br>
  * <>
  * @Param:  自定义注解
  * @Return:
  * @Author: AW
  * @Date: 2020/7/27 0027
  */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
 public @interface CacheException {

}
