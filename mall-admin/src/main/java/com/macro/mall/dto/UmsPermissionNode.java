package com.macro.mall.dto;

import com.macro.mall.model.UmsPermission;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @ClassName UmsPermissionNode
 * @Description 后台权限节点封装
 * @Author AW
 * @Date 2020/8/6 000621:18
 * @Version V1.0
 **/

public class UmsPermissionNode extends UmsPermission {
    @Getter
    @Setter
    @ApiModelProperty(value = "子级权限")
    private List<UmsPermissionNode> children;
}
