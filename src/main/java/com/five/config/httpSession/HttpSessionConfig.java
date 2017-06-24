package com.five.config.httpSession;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * Created by haoye on 17-6-7.
 */
//@EnableRedisHttpSession
//public class HttpSessionConfig {
//    @Bean
//    public JedisConnectionFactory connectionFactory() {
//        return new JedisConnectionFactory();
//    }
//
//    @Bean
//    public static ConfigureRedisAction configureRedisAction() {
//        return ConfigureRedisAction.NO_OP;
//    }
//}
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 86400*30)
public class HttpSessionConfig {
}

