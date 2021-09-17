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

package com.github.shifengcui.safe;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.reflect.Reflection;

/**
 * 安全操作工具类 - 例如操作对象空指针的安全处理
 * @author simple <cuishifeng0207@163.com>
 * Created on 2021-09-17
 */
public class SafeUtils {


    /**
     * 安全返回String对象
     * @param data
     * @param defaultStr 默认返回
     * @return
     */
    public static String blankToDefault(String data, String defaultStr) {
        return Strings.isNullOrEmpty(data) ? defaultStr : data;
    }

    /**
     * 安全返回String对象
     * @param data
     * @param defaultStr 默认返回
     * @return
     */
    public static String nullToDefault(String data, String defaultStr) {
        return Optional.ofNullable(data).orElse(defaultStr);
    }

    /**
     * 安全返回String对象
     * @param data
     * @return
     */
    public static String nullToEmpty(String data) {
        return Strings.nullToEmpty(data);
    }

    /**
     * 安全返回String对象
     * @param data
     * @return
     */
    public static String emptyToNull(String data) {
        return Strings.emptyToNull(data);
    }

    /**
     * 安全返回list对象
     * @param list
     * @param <T>
     * @return
     */
    public static <T> List<T> nullToEmpty(List<T> list) {
        return Optional.ofNullable(list).orElse(Lists.newArrayListWithExpectedSize(0));
    }

    /**
     * 安全返回list对象
     * @param list
     * @param defaultList
     * @param <T>
     * @return
     */
    public static <T> List<T> nullToDefault(List<T> list, List<T> defaultList) {
        return Optional.ofNullable(list).orElse(defaultList);
    }

    /**
     * 安全返回set对象
     * @param set
     * @param <T>
     * @return
     */
    public static <T> Set<T> nullToEmpty(Set<T> set) {
        return Optional.ofNullable(set).orElse(Sets.newHashSetWithExpectedSize(0));
    }


    /**
     * 安全返回set对象
     * @param set
     * @param defaultSet 指定默认的set
     * @param <T>
     * @return
     */
    public static <T> Set<T> nullToDefault(Set<T> set, Set<T> defaultSet) {
        return Optional.ofNullable(set).orElse(defaultSet);
    }


    /**
     * 安全返回map对象
     * @param map
     * @param <K,V>
     * @return
     */
    public static <K, V> Map<K, V> nullToEmpty(Map<K, V> map) {
        return Optional.ofNullable(map).orElse(Maps.newHashMapWithExpectedSize(0));
    }

    /**
     * 安全返回map对象
     * @param map
     * @param defaultMap 指定默认的map
     * @param <K,V>
     * @return
     */
    public static <K, V> Map<K, V> nullToDefault(Map<K, V> map, Map<K, V> defaultMap) {
        return Optional.ofNullable(map).orElse(defaultMap);
    }


    /**
     * 操作避免空指针
     * @param t 操作的对象
     * @param function {@link Function}
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> R get(T t, Function<T, R> function) {
        return get(t, function, null);
    }

    /**
     * 操作避免空指针
     * @param t 操作的对象
     * @param function {@link Function}
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> R get(T t, Function<T, R> function, R defaultR) {
        return t != null ? function.apply(t) : defaultR;
    }


}
