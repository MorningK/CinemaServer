package com.five.config.redisConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by haoye on 17-6-10.
 */
public class MapDecode {

    // "findByUserId, 1; findByCinemaId, 1, 2"
    public static Map<String, List<String>> MapDecoder(String map) {
        Map<String, List<String>> decodeMap = new HashMap<>();
        String[] methodAndFields = map.split(";");
        for (String methodAndField : methodAndFields) {
            String[] temp = methodAndField.split(",");
            String method = temp[0].trim();
            List<String> fields = new ArrayList<>();
            for (int i = 1; i < temp.length; i++) {
                String field = temp[i].trim();
                fields.add(field);
            }
            decodeMap.put(method, fields);
        }
        return decodeMap;
    }
}
