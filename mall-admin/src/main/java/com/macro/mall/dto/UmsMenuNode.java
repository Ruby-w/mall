package com.macro.mall.dto;

import com.macro.mall.model.UmsMenu;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


/**
 * @ClassName UmsMenuNode
 * @Description 后台菜单节点封装
 * @Author AW
 * @Date 2020/8/6 000621:17
 * @Version V1.0
 **/
@Data
public class UmsMenuNode extends UmsMenu {
    @ApiModelProperty(value = "子级菜单")
    private List<UmsMenuNode> children;
}
