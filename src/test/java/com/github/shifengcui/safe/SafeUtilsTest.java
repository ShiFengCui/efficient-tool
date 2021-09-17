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

import com.github.shifengcui.copy.Source;

import junit.framework.TestCase;

/**
 * @author simple <cuishifeng0207@163.com>
 * Created on 2021-09-17
 */
public class SafeUtilsTest extends TestCase {

    public void testBlankToDefault() {
    }

    public void testNullToDefault() {
    }

    public void testNullToEmpty() {
    }

    public void testEmptyToNull() {
    }

    public void testTestNullToEmpty() {
    }

    public void testTestNullToDefault() {
    }

    public void testTestNullToEmpty1() {
    }

    public void testTestNullToDefault1() {
    }

    public void testTestNullToEmpty2() {
    }

    public void testTestNullToDefault2() {
    }

    public void testGet() {
    }

    public void testTestGet() {
    }

    public void testSet() {
        Source source = null;
//        SafeUtils.set("123", source::setName);
        Consumer<String> consumer = source::setName;
        consumer.accept("123");
    }

    public void testMain() {
    }
}