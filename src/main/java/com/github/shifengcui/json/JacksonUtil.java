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

package com.github.shifengcui.json;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * jackson工具
 * @author simple <cuishifeng0207@163.com>
 * Created on 2021-09-30
 */
public class JacksonUtil {

    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper()
                .findAndRegisterModules()
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public static <K, V> Map<K, V> toMap(String json) {
        try {
            return objectMapper.readValue(json, Map.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <K, V> Map<K, V> toMap(String json, Type type) {
        TypeReference<Map<K, V>> typeReference = new TypeReference<Map<K, V>>() {
            @Override
            public Type getType() {
                return type;
            }
        };
        try {
            return (Map<K, V>) objectMapper.readValue(json, typeReference);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> toList(String json) {
        try {
            return objectMapper.readValue(json, List.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> toList(String json, Type type) {
        TypeReference<List<T>> typeReference = new TypeReference<List<T>>() {
            @Override
            public Type getType() {
                return type;
            }
        };
        try {
            return (List<T>) objectMapper.readValue(json, typeReference);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toJsonString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toJsonString(Object object, String dateFormatPattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatPattern);
        try {
            return objectMapper.writer(dateFormat).writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T toObject(String json, Class<T> valueType) {
        try {
            return objectMapper.readValue(json, valueType);
        } catch (IOException e) {
            throw new RuntimeException(e);

        }
    }

    public static <K, V> Map<K, V> objectToMap(Object fromValue) {
        return objectMapper.convertValue(fromValue, Map.class);
    }

    public static <T> T mapToObject(Map fromMap, Class<T> toValueType) {
        return objectMapper.convertValue(fromMap, toValueType);
    }
}
