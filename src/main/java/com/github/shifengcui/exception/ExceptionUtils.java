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

package com.github.shifengcui.exception;

import java.util.List;

import com.google.common.base.Throwables;

/**
 * 异常处理工具类
 * @author simple <cuishifeng0207@163.com>
 * Created on 2021-09-30
 */
public class ExceptionUtils {


    /**
     * 返回异常链
     * @param throwable
     * @return
     */
    public static List<Throwable> getCausalChain(Throwable throwable) {
        return Throwables.getCausalChain(throwable);
    }

    /**
     * 异常堆栈信息字符串输出
     * @param throwable
     * @return
     */
    public static String getStackTraceAsString(Throwable throwable) {
        return Throwables.getStackTraceAsString(throwable);
    }


}
