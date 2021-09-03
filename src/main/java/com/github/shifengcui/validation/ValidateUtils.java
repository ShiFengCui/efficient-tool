/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

/*
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.github.shifengcui.validation;

import java.util.Set;
import java.util.stream.Collectors;

import org.apache.ibatis.reflection.property.PropertyNamer;
import org.hibernate.validator.internal.util.Contracts;

import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.support.LambdaMeta;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.github.shifengcui.constant.Constants;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

/**
 * 参数校验工具
 * @author simple <cuishifeng0207@163.com>
 * Created on 2021-08-12
 */
public class ValidateUtils {

    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * 校验具体字段
     * @param object 对象
     * @param sFunction 字段get方法的lambda表达式
     * @param groups 分组
     * @param <T> T
     * @param <R> R
     * @return
     */
    public static <T, R> ValidationResult validateProperty(T object, SFunction<T, R> sFunction,
            Class<?>... groups) {
        return validateProperty(true, object, sFunction, groups);
    }

    /**
     * 校验具体字段
     * @param condition 判断条件 true 校验 false 不校验
     * @param object 对象
     * @param sFunction 字段get方法的lambda表达式
     * @param groups 分组
     * @param <T> T
     * @param <R> R
     * @return
     */
    public static <T, R> ValidationResult validateProperty(boolean condition, T object,
            SFunction<T, R> sFunction, Class<?>... groups) {
        return validateProperty(condition, object, getFiledName(sFunction), groups);
    }

    /**
     * 校验对象里的字段
     * @param condition 判断条件 true 校验 false 不校验
     * @param object 对象
     * @param groups 分组
     * @param <T> T
     * @return
     */
    public static <T> ValidationResult validateObj(boolean condition, T object, Class<?>... groups) {
        if (!condition) {
            return new ValidationResult(false);
        }
        Contracts.assertNotNull(object, "SuperValidator validateObj first parameter is null");
        Set<ConstraintViolation<T>> violationSet = VALIDATOR.validate(object, groups);
        return ofValidationResult(violationSet);
    }

    /**
     * 校验具体字段
     * @param condition 判断条件 true 校验 false 不校验
     * @param object 对象
     * @param propertyName 属性字段名称
     * @param groups 分组
     * @param <T> T
     * @return
     */
    public static <T> ValidationResult validateProperty(boolean condition, T object,
            String propertyName, Class<?>... groups) {
        if (!condition) {
            return new ValidationResult(false);
        }
        Contracts.assertNotNull(object, "SuperValidator validateProperty first parameter is null");
        Contracts.assertNotNull(object, "SuperValidator validateProperty second parameter is null");
        Set<ConstraintViolation<T>> violationSet = VALIDATOR.validateProperty(object, propertyName, groups);
        return ofValidationResult(violationSet);
    }

    /**
     * 处理返回的校验结果
     * @param violations
     * @param <T>
     * @return
     */
    private static <T> ValidationResult ofValidationResult(Set<ConstraintViolation<T>> violations) {
        ValidationResult<T> validationResult = new ValidationResult(violations);
        Set<String> set = violations.stream()
                .map(m -> String.format(Constants.Validation.FILED_CONTENT_IS_ERROR, m.getPropertyPath(),
                        m.getMessage()))
                .collect(Collectors.toSet());
        validationResult.setErrorMessages(set);
        return validationResult;
    }

    /**
     * 获取字段名称
     * @param sFunction
     * @param <R>
     * @param <T>
     * @return
     */
    public static <R, T> String getFiledName(SFunction<R, T> sFunction) {
        LambdaMeta lambdaMeta = LambdaUtils.extract(sFunction);
        return PropertyNamer.methodToProperty(lambdaMeta.getImplMethodName());
    }


}
