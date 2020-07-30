package com.macro.mall.demo.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @ClassName FlagValidatorClass
 * @Description 状态标记校验器
 * @Author AW
 * @Date 2020/7/30 003022:54
 * @Version V1.0
 **/

public class FlagValidatorClass implements ConstraintValidator<FlagValidator, Integer> {
    private String[] values;

    @Override
    public void initialize(FlagValidator flagValidator) {
        this.values = flagValidator.value();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        boolean isValid = false;
        for (int i = 0; i < values.length; i++) {
            if (values[i].equals(String.valueOf(value))) {
                isValid = true;
                break;
            }
            return isValid;
        }

        return false;
    }
}
