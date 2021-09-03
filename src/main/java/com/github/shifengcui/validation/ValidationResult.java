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

import java.util.HashSet;
import java.util.Set;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;

import jakarta.validation.ConstraintViolation;

/**
 * 校验结果
 * @author simple <cuishifeng0207@163.com>
 * Created on 2021-08-12
 */
public class ValidationResult<T> {
    /**
     * 是否存在错误
     */
    private boolean hasErrorResult;
    /**
     * 第一个错误描述
     */
    private String firstErrorMessage;
    /**
     * 错误描述集合
     */
    private Set<String> errorMessages;
    /**
     * {@link jakarta.validation.ConstraintViolation}
     */
    private Set<ConstraintViolation<T>> constraintViolationSet;


    public ValidationResult(boolean hasErrorResult) {
        this.hasErrorResult = hasErrorResult;
    }


    public ValidationResult(Set<ConstraintViolation<T>> violations) {
        this.hasErrorResult = violations != null && CollectionUtils.isNotEmpty(violations);
        this.constraintViolationSet = violations;
    }


    public synchronized void addErrorMessage(String errorMessage) {
        if (errorMessages == null || errorMessages.isEmpty()) {
            errorMessages = new HashSet<String>();
        }
        errorMessages.add(errorMessage);
    }

    public synchronized void setErrorMessages(Set<String> errorMessages) {
        if (this.errorMessages == null || this.errorMessages.isEmpty()) {
            this.errorMessages = errorMessages != null ? errorMessages : new HashSet<>();
        } else {
            this.errorMessages.addAll(errorMessages);
        }
    }

    public boolean isHasErrorResult() {
        return hasErrorResult;
    }

    public void setHasErrorResult(boolean hasErrorResult) {
        this.hasErrorResult = hasErrorResult;
    }

    public Set<String> getErrorMessages() {
        return errorMessages;
    }

    public Set<ConstraintViolation<T>> getConstraintViolationSet() {
        return constraintViolationSet;
    }

    public void setConstraintViolationSet(Set<ConstraintViolation<T>> constraintViolationSet) {
        this.constraintViolationSet = constraintViolationSet;
    }

    public String getFirstErrorMessage() {
        return hasErrorResult ? (StringUtils.isBlank(firstErrorMessage) ?
                errorMessages.stream().findFirst().get() : this.firstErrorMessage) : StringUtils.EMPTY;
    }

    public void setFirstErrorMessage(String firstErrorMessage) {
        this.firstErrorMessage = firstErrorMessage;
    }
}
