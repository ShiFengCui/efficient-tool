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

package com.github.shifengcui.codec;

import java.util.Random;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * MD5加密
 * @author simple <cuishifeng0207@163.com>
 * Created on 2021-09-08
 */
public class MD5Utils {

    private static final int BEGIN_INDEX = 8;
    private static final int END_INDEX = 24;
    private static final int BOUND = 100000;
    private static final String EMPTY = "";
    private static final Random RANDOM = new Random();


    /**
     * md5加密生成16进制16位字符
     *
     * @param data 要加密的字符
     * @param toLowerCase 是否小写
     * @return 加密字符
     */
    public static String md5Hex16String(final String data, final boolean toLowerCase) {
        return md5Hex32String(data, toLowerCase, false).substring(BEGIN_INDEX, END_INDEX);
    }

    /**
     * md5加密生成16进制16位字符
     *
     * @param data 要加密的字符
     * @param toLowerCase 是否小写
     * @param addSalt 是否要加盐
     * @return 加密字符
     */
    public static String md5Hex16String(final String data, final boolean toLowerCase, final boolean addSalt) {
        return md5Hex32String(data, toLowerCase, addSalt).substring(BEGIN_INDEX, END_INDEX);
    }

    /**
     * md5加密生成16进制32位字符
     *
     * @param data 要加密的字符
     * @param toLowerCase 是否小写
     * @return 加密字符
     */
    public static String md5Hex32String(final String data, final boolean toLowerCase) {
        return md5Hex32String(data, toLowerCase, false);
    }

    /**
     * md5加密生成16进制32位字符
     *
     * @param data 要加密的字符
     * @param toLowerCase 是否小写
     * @param addSalt 是否添加盐
     * @return 加密字符
     */
    public static String md5Hex32String(final String data, final boolean toLowerCase, final boolean addSalt) {
        String str = data + (addSalt ? String.valueOf(RANDOM.nextInt(BOUND)) : EMPTY);
        return Hex.encodeHexString(DigestUtils.md5(StringUtils.getBytesUtf8(str)), toLowerCase);
    }
}
