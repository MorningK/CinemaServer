package com.five.user.repository;

import com.five.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by haoye on 17-6-6.
 */
public interface UserRepository extends JpaRepository<User, Integer>{
    public User findByUsername(String username);

    public User findById(int id);

    public User findByEmail(String email);

    public User findByCode(String code);

    public User save(User user);
}
