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


import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.baomidou.mybatisplus.core.toolkit.ClassUtils;

import io.grpc.examples.helloworld.CarPb;
import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;

/**
 * @author simple <cuishifeng0207@163.com>
 * Created on 2021-09-06
 */
public class CopyObjectUtilsTest {

    private static Source source = new Source("hello", 15);


    @Test
    public void testPojo() throws Exception {
        Target target = CarMapper.INSTANCE.copy(source);
        System.out.println(target);
        Set set = new HashSet();
    }

    @Test
    public void testProto() throws Exception {
        CarPb carPb = CarMapper.INSTANCE.copyPb(source);
        System.out.println(carPb);
    }


    @Test
    public void testBeanCopier() throws Exception {
        BeanCopier beanCopier = BeanCopier.create(Source.class, Target.class, false);
        Target target = new Target();
        beanCopier.copy(source, target, null);
        System.out.println(target);
    }

    @Test
    public void testBeanCopier2() throws Exception {
        Target target = copy(Target.class, source);
        System.out.println(target);
    }

    public <S, T> T copy(Class<T> tClass, S source) {
        BeanCopier beanCopier = BeanCopier.create(source.getClass(), tClass, false);
        T t = ClassUtils.newInstance(tClass);
        beanCopier.copy(source, t, null);
        return t;
    }

    public <S, T> T copy(Class<T> tClass, S source, Converter converter) {
        BeanCopier beanCopier = BeanCopier.create(source.getClass(), tClass, false);
        T t = ClassUtils.newInstance(tClass);
        beanCopier.copy(source, t, null);
        return t;
    }

    public <S, T> T copy(Class<S> sClass, Class<T> tClass, S source, Converter converter) {
        BeanCopier beanCopier = BeanCopier.create(sClass, tClass, converter != null);
        T t = ClassUtils.newInstance(tClass);
        beanCopier.copy(source, t, converter);
        return t;
    }

}