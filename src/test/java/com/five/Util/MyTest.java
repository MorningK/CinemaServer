package com.five.Util;

import com.five.CinemaApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.net.InetAddress;

/**
 * Created by haoye on 17-6-11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CinemaApplication.class)
@WebAppConfiguration
public class MyTest {

    @Test
    public void LocalIpTest() throws Exception {
//        String ip = LocalIp.getLocalIp();
//        System.out.println(ip);
//        System.out.println(ip.length());
//        System.out.println(ip);
    }
}
