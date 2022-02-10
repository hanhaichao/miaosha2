package org.example.controller.Validation;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;
import java.util.function.Consumer;

@Component
//4、@component （把普通pojo实例化到spring容器中，相当于配置文件中的
//<bean id="" class=""/>）

public class ValidatorImpl implements InitializingBean {

    private Validator validator;

    /**
     * @param  bean: 任何bean对象]
     * @return ValidationResult
     * @description 实现校验方法并返回校验结果
     */
    public ValidationResult validate(Object bean) {
        ValidationResult validationResult = new ValidationResult();
        Set<ConstraintViolation<Object>> constraintViolationSet = validator.validate(bean);
        if (constraintViolationSet.size() > 0) {
            //有错误
            validationResult.setHasErrors(true);
//            /* 遍历每个字段发生了什么错误 */
            constraintViolationSet.forEach(new Consumer<ConstraintViolation<Object>>() {
                @Override
                public void accept(ConstraintViolation<Object> constraintViolation) {
                    String errMsg = constraintViolation.getMessage();
                    String propertyName = constraintViolation.getPropertyPath().toString();
                    validationResult.getErrorMsgMap().put(propertyName, errMsg);
                }
            });
        }
        return validationResult;
    }

    /**
     * @description Bean初始化后，会调用这个方法
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        //将hibernate validator通过工厂的初始化方式使其实例化
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
}
