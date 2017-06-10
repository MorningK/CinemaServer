package com.five.Util;

import com.five.CinemaApplication;
import com.five.config.redisConfig.MapDecode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;
import java.util.Map;

/**
 * Created by haoye on 17-6-10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CinemaApplication.class)
@WebAppConfiguration
public class MapDecodeTest {

    @Test
    public void testMapDecode() {
        String str = "findByUserId, 1; findByCinemaId, 1, 2";
        Map<String, List<String>> temp = MapDecode.MapDecoder(str);
        for (String method : temp.keySet()) {
            List<String> fields = temp.get(method);
            System.out.print("method:" + method);
            System.out.print("field:");
            for (String field : fields) {
                System.out.print(field + " ");
            }
            System.out.println();
        }
    }

}
