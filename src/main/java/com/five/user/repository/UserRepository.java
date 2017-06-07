package com.five.user.repository;

import com.five.user.model.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by haoye on 17-6-6.
 */
@CacheConfig(cacheNames = "user")
public interface UserRepository extends JpaRepository<User, Integer>{
    @Cacheable
    public User findByUsername(String username);
    @Cacheable
    public User findOne(Integer id);
}
