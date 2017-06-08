package com.five;

import com.five.user.model.User;
import com.five.user.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by haoye on 17-6-7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CinemaApplication.class)
@WebAppConfiguration
public class SpringcloudRedis2ApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Before
    public void before() {
        userRepository.save(new User("username", "password"));
    }
    @Test
    public void test() throws Exception {
        User u1 = userRepository.findByUsername("username");
        System.out.println("第一次查询：" + u1.getPassword());
        User u2 = userRepository.findByUsername("username");
        System.out.println("第二次查询：" + u2.getPassword());
        System.out.println(userRepository.findOne(u1.getId()));
        System.out.println(userRepository.findOne(u1.getId()));
    }

}