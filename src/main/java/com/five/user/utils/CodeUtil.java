package com.five.user.utils;

import java.util.UUID;

/**
 * Created by MyComputer on 2017/6/9.
 */
public class CodeUtil {

    public static String generateUniqueCode(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
