package me.beldon.util;


import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

/**
 * Created by Beldon
 */
public class TokenUtils {
    private static final String STR_S = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final Random RANDOM = new Random();
    /**
     * 生成一个随机数
     * @return
     */
    public static String randomState(int size) {
        RandomStringUtils.random(size);
        int count = 24;
        char[] buffer = new char[count];
        for (int i = 0; i < count; i++) {
            buffer[i] = STR_S.charAt(RANDOM.nextInt(STR_S.length()));
        }
        return new String(buffer);
    }

    /**
     * 生成一个5位的随机数
     * @return
     */
    public static String randomState() {
        return randomState(5);
    }
}
