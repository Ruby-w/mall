package com.macro.mall.security.aspect;

import com.macro.mall.security.annotation.CacheException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @ClassName RedisCacheAspect
 * @Description Redis缓存切面，防止Redis宕机影响正常业务逻辑
 * @Author AW
 * @Date 2020/7/29 002921:40
 * @Version V1.0
 **/
@Aspect
@Component
@Order(2)
public class RedisCacheAspect {

    private static Logger LOGGER = LoggerFactory.getLogger(RedisCacheAspect.class);

    @Pointcut("execution(public * com.macro.mall.security.service.portal.service.*CacheService.*(..)) || execution(public * com.macro.mall.service.*CacheService.*(..))")
    public void cacheAspect() {
    }

    @Around("cacheAspect()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        Object result = null;

        try {
            result = joinPoint.proceed();
        } catch (Throwable throwable) {

            if (method.isAnnotationPresent(CacheException.class)) {
                throw throwable;
            } else {
                LOGGER.error(throwable.getMessage());
            }

            throwable.printStackTrace();
        }
        return result;
    }

}
