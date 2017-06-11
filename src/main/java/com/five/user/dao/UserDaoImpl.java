package com.five.user.dao;

import com.five.user.model.User;
import com.five.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
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
    @Cacheable(keyGenerator = "wiselyKeyGenerator")
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Caching(put = {
            @CachePut(key = "'user.findById'+#result.getId()", condition = "#result != null"),
            @CachePut(key = "'user.findByUsername'+#result.getUsername()", condition = "#result != null"),
            @CachePut(key = "'user.findbyEmail'+#result.getEmail()", condition = "#result != null"),
            @CachePut(key = "'user.findByCode'+#result.getCode()", condition = "#result != null")
    })
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    @Cacheable(keyGenerator = "wiselyKeyGenerator")
    public User findById(int id) {
        return userRepository.findById(id);
    }

    @Override
//    @CacheEvict(cacheNames = "user", allEntries = true)
    public void reload() {

    }

    @Override
    @Cacheable(keyGenerator = "wiselyKeyGenerator")
    public User findbyEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    @Cacheable(keyGenerator = "wiselyKeyGenerator")
    public User findByCode(String code) {
        return userRepository.findByCode(code);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(key = "'user.findByUsername'+#p0.getUsername()", condition = "#p0 != null"),
            @CacheEvict(key = "'user.findbyEmail'+#p0.getEmail()", condition = "#p0 != null"),
    })
    public void refreshOne(User user) {

    }
}
