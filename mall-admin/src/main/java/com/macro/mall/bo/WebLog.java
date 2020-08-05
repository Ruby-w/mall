package com.macro.mall.bo;

import lombok.Data;

/**
 * @ClassName WebLog
 * @Description Controller层的日志封装类
 * @Author AW
 * @Date 2020/8/5 000523:48
 * @Version V1.0
 **/
@Data
public class WebLog {
    /**
     * 操作描述
     */
    private String description;
    /**
     * 操作用户
     */
    private String username;
    /**
     * 操作时间
     */
    private Long startTime;
    /**
     * 消耗时间
     */
    private Integer spendTime;
    /**
     * 根路径
     */
    private String basePath;
    /**
     * URI
     */
    private String uri;
    /**
     * URL
     */
    private String url;
    /**
     * 请求类型
     */
    private String method;
    /**
     * ip
     */
    private String ip;

    private Object parameter;

    private Object result;
}
