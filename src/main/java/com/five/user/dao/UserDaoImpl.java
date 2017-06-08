package com.five.user.dao;

import com.five.user.model.User;
import com.five.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by haoye on 17-6-6.
 */
@Repository
@CacheConfig(cacheNames = "user")
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }


    /*Todo
    Sometimes this exception will happen
    Exception:
    java.lang.ClassCastException: java.util.ArrayList cannot be cast to com.five.user.model.User
    * */
    @Override
    public User findById(int id) {
        return userRepository.findById(id);
    }

    @Override
    @CacheEvict(cacheNames = "user", allEntries = true)
    public void reload() {

    }
}
