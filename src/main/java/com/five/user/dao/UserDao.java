package com.five.user.dao;

import com.five.user.model.User;

/**
 * Created by haoye on 17-6-6.
 */
public interface UserDao {
    public User findByUsername(String username);
    public User save(User user);
    public User findById(int id);
}
