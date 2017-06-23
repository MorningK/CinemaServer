package com.five.user.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * Created by MyComputer on 2017/6/9.
 */
public class CodeUtil {
    public static Map<String, String> codeMap = new HashMap<>();
    public static Map<String, String> secondCode = new HashMap<>();

    public static String generateUniqueCode(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String generateRandomSixCode() {
        Random random = new Random();
        String ans = "";
        for (int i = 0; i < 6; i++) {
            ans += Integer.toString(random.nextInt(10));
        }
        return ans;
    }
}
