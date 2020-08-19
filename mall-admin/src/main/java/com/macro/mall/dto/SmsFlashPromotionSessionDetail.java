package com.macro.mall.dto;

import com.macro.mall.model.SmsFlashPromotionSession;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName SmsFlashPromotionSessionDetail
 * @Description 包含商品数量的场次信息
 * @Author AW
 * @Date 2020/8/17 001723:25
 * @Version V1.0
 **/
@Data
public class SmsFlashPromotionSessionDetail extends SmsFlashPromotionSession {
    @ApiModelProperty("商品数量")
    private Long productCount;
}
