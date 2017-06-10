package com.five.user.dao;

import com.five.user.model.User;
import com.five.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
    @CachePut(keyGenerator = "wiselyKeyGenerator")
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
}
