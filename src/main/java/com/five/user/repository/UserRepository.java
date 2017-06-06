package com.five.user.repository;

import com.five.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by haoye on 17-6-6.
 */
public interface UserRepository extends JpaRepository<User, Integer>{
    public User findByUsername(String username);
}
