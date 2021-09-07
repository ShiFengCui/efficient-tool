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

package com.github.shifengcui.copy;

import com.baomidou.mybatisplus.core.toolkit.ClassUtils;

import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;

/**
 * 基于 {@link BeanCopier} 封装的对象复制工具类
 * @author simple <cuishifeng0207@163.com>
 * Created on 2021-09-06
 */
public class CopyObjectUtils {


    /**
     * 对象复制
     * @param targetClass 目标对象类型
     * @param source 源对象引用
     * @param <S> 源对象泛型
     * @param <T> 目标对象泛型
     * @return T 目标对象
     */
    public static <S, T> T copy(Class<T> targetClass, S source) {
        BeanCopier beanCopier = BeanCopier.create(source.getClass(), targetClass, false);
        T t = ClassUtils.newInstance(targetClass);
        beanCopier.copy(source, t, null);
        return t;
    }

    /**
     * 对象复制
     * @param targetClass 目标对象类型
     * @param source 源对象引用
     * @param converter 自定义的转换实现类
     * @param <S> 源对象泛型
     * @param <T> 目标对象泛型
     * @return T 目标对象
     */
    public static <S, T> T copy(Class<T> targetClass, S source, Converter converter) {
        BeanCopier beanCopier = BeanCopier.create(source.getClass(), targetClass, false);
        T t = ClassUtils.newInstance(targetClass);
        beanCopier.copy(source, t, null);
        return t;
    }

    /**
     * 对象复制
     * @param sClass 源对象类型
     * @param targetClass 目标对象类型
     * @param source 源对象引用
     * @param converter 自定义的转换实现类
     * @param <S> 源对象泛型
     * @param <T> 目标对象泛型
     * @return T 目标对象
     */
    public static <S, T> T copy(Class<S> sClass, Class<T> targetClass, S source, Converter converter) {
        BeanCopier beanCopier = BeanCopier.create(sClass, targetClass, converter != null);
        T t = ClassUtils.newInstance(targetClass);
        beanCopier.copy(source, t, converter);
        return t;
    }

}
