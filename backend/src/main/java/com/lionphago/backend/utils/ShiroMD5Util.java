package com.lionphago.backend.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

public class ShiroMD5Util {
    private static final String ALGORITHM = "md5";
    private static final int ITERATIONS = 2;

    public static String encrypt(String password) {
        return new SimpleHash(ALGORITHM, password, null, ITERATIONS).toString();
    }
}
