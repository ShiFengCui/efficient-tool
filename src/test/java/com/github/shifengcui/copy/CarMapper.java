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

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import io.grpc.examples.helloworld.CarPb;

/**
 * @author simple <cuishifeng0207@163.com>
 * Created on 2021-09-06
 */
@Mapper
public interface CarMapper<S, T> {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);


    @Mapping(target = "year", source = "old")
    Target copy(Source source);


    // 注释
    @Mapping(target = "year", source = "old")
    CarPb copyPb(Source source);

}
