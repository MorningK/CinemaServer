package com.five.user.dao;

import com.five.user.model.User;

import java.util.List;

/**
 * Created by haoye on 17-6-6.
 */
public interface UserDao {
    public User findByUsername(String username);
    public User save(User user);
    public User findById(int id);
    void reload();
    public User findbyEmail(String email);
    public User findByCode(String code);
    void refreshOne(User user);
}
